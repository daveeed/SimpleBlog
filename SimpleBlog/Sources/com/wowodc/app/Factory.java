package com.wowodc.app;

import com.webobjects.appserver.WOApplication;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.D2WContext;
import com.webobjects.directtoweb.D2WPage;
import com.webobjects.directtoweb.EditPageInterface;
import com.webobjects.directtoweb.InspectPageInterface;
import com.webobjects.directtoweb.ListPageInterface;
import com.webobjects.eocontrol.EODataSource;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.foundation.NSKeyValueCoding;
import com.wowodc.model.BlogComment;
import com.wowodc.model.BlogEntry;
import com.wowodc.model.Person;

import er.directtoweb.ERD2WFactory;
import er.directtoweb.pages.ERD2WInspectPage;
import er.extensions.appserver.ERXSession;
import er.extensions.appserver.navigation.ERXNavigationManager;
import er.extensions.eof.EOEnterpriseObjectClazz;
import er.extensions.eof.ERXEC;
import er.extensions.foundation.ERXStringUtilities;

/**
 * Central page creation class. All workflow things should go here or in the
 * super classes. The session
 * and the current user will get retrieved via thread storage.
 * 
 * @author ak
 * 
 */
public class Factory extends ERD2WFactory implements NSKeyValueCoding {

  public void takeValueForKey(Object value, String key) {
    throw new UnsupportedOperationException("Can't takeValueForKey");
  }

  public Object valueForKey(String key) {
    key = ERXStringUtilities.uncapitalize(key);
    return NSKeyValueCoding.DefaultImplementation.valueForKey(this, key);
  }

  /**
   * Bottleneck for the page creation. Applies the current navigation item if
   * the actual item starts with the new one. This leads to ListRecentBug
   * staying selected, even when
   * the user goes to an edit page.
   */
  public WOComponent pageWithContextTaskEntity(D2WContext d2wcontext, String task, String entity, WOContext wocontext) {
    WOComponent nextPage = super.pageWithContextTaskEntity(d2wcontext, task, entity, wocontext);
    if (nextPage instanceof D2WPage) {
      String oldState = ERXNavigationManager.manager().navigationStateForSession(wocontext.session()).stateAsString();
      D2WPage page = (D2WPage) nextPage;
      page.setNextPage(currentPage());
      String newState = (String) page.d2wContext().valueForKey("navigationState");
      if (oldState != null) {
        if (newState == null || oldState.startsWith(newState)) {
          page.d2wContext().takeValueForKey(oldState, "navigationState");
        }
      }
      log.debug("Create page: " + page.d2wContext().dynamicPage() + " old: " + oldState + " news: " + newState);

    }
    return nextPage;
  }

  public EditPageInterface editPageNamed(String pageConfiguration, EOEnterpriseObject eo) {
    EditPageInterface epi = (EditPageInterface) inspectPageNamed(pageConfiguration, eo);
    epi.setObject(eo);
    return epi;
  }

  public InspectPageInterface inspectPageNamed(String pageConfiguration, EOEnterpriseObject eo) {
    InspectPageInterface epi = (InspectPageInterface) pageForConfigurationNamed(pageConfiguration, session());
    epi.setObject(eo);
    return epi;
  }

  protected InspectPageInterface createPageNamed(String name) {
    EditPageInterface epi = editPageForNewObjectWithConfigurationNamed(name, session());
    epi.setNextPage(homePage());
    return epi;
  }

  protected ListPageInterface listPageNamed(String name, EODataSource ds) {
    ListPageInterface lpi = (ListPageInterface) pageForConfigurationNamed(name);
    lpi.setDataSource(ds);
    return lpi;
  }

  protected ListPageInterface listPageNamed(String name, EOEnterpriseObjectClazz clazz) {
    EOEditingContext ec = ERXEC.newEditingContext();
    ec.lock();
    try {
      EODataSource ds = clazz.newDatabaseDataSource(ec);
      return listPageNamed(name, ds);
    }
    finally {
      ec.unlock();
    }
  }

  protected WOComponent pageForConfigurationNamed(String name) {
    WOComponent page = D2W.factory().pageForConfigurationNamed(name, session());
    page.takeValueForKey(pageWithName("HomePage"), "nextPage");
    return page;
  }

  private Session session() {
    return (Session) ERXSession.anySession();
  }

  /**
   * Singleton of this class.
   * 
   * @return
   */
  public static Factory simpleBlog() {
    return (Factory) D2W.factory();
  }

  protected void applyCurrentUser(EOEnterpriseObject eo, String relationshipName) {
    EOEditingContext ec = eo.editingContext();
    Person user = currentUser(ec);
    eo.addObjectToBothSidesOfRelationshipWithKey(user, relationshipName);
  }

  public WOComponent currentPage() {
    return session().context().page();
  }

  private Person currentUser(EOEditingContext ec) {
    ec = (ec == null ? session().defaultEditingContext() : ec);
    return Person.currentUser(ec);
  }

  protected WOComponent pageWithName(String name) {
    return WOApplication.application().pageWithName(name, session().context());
  }

  public WOComponent homePage() {
    return session().navController().homeAction();
  }

  // BLOGENTRY stuff

  public WOComponent createBlogEntry() {
    ERD2WInspectPage page = (ERD2WInspectPage) createPageNamed("CreateBlogEntry");
    BlogEntry aBlogEntry = (BlogEntry) page.object();
    applyCurrentUser(aBlogEntry, BlogEntry.PERSON_KEY);
    return (WOComponent) page;
  }

  public WOComponent editBlogEntry(BlogEntry aBlogEntry) {
    EditPageInterface epi = editPageNamed("Edit" + aBlogEntry.entityName(), aBlogEntry);
    epi.setNextPage(homePage());
    return (WOComponent) epi;
  }

  public WOComponent inspectBlogEntry(BlogEntry aBlogEntry) {
    InspectPageInterface epi = inspectPageNamed("Inspect" + aBlogEntry.entityName(), aBlogEntry);
    epi.setNextPage(homePage());
    return (WOComponent) epi;
  }

  public WOComponent commentBlogEntry(BlogEntry aBlogEntry) {
    EOEditingContext peer = ERXEC.newEditingContext(aBlogEntry.editingContext().parentObjectStore());
    EditPageInterface epi = null;
    peer.lock();
    try {
      aBlogEntry = (BlogEntry) aBlogEntry.localInstanceIn(peer);
      epi = (EditPageInterface) editPageNamed("Edit" + aBlogEntry.entityName() + "ToComment", aBlogEntry);
      epi.setObject(aBlogEntry);
      epi.setNextPage(currentPage());
    }
    finally {
      peer.unlock();
    }

    return (WOComponent) epi;
  }

  public WOComponent editBlogComment(BlogComment newComment) {
    EOEditingContext peer = ERXEC.newEditingContext(newComment.editingContext().parentObjectStore());
    EditPageInterface epi = null;
    peer.lock();
    try {
      newComment = (BlogComment) newComment.localInstanceIn(peer);
      epi = (EditPageInterface) editPageNamed("EditBlogComment", newComment);
      epi.setObject(newComment);
      epi.setNextPage(currentPage());
    }
    finally {
      peer.unlock();
    }

    return (WOComponent) epi;
  }

  public WOComponent newBlogComment(BlogEntry aBlogEntry) {

    // Create a new editing context and setup validation
    EOEditingContext peer = ERXEC.newEditingContext(aBlogEntry.editingContext().parentObjectStore());
    EditPageInterface epi = null;
    peer.lock();
    try {
      aBlogEntry = (BlogEntry) aBlogEntry.localInstanceIn(peer);
      Person currentPerson = Person.currentUser(peer);

        if (currentPerson == null) {
          currentPerson = (Person.fetchRequiredPerson(ERXEC.newEditingContext(), "login", "system")).localInstanceIn(peer);
        }

      BlogComment newComment = BlogComment.createBlogComment(peer, aBlogEntry, currentPerson);
      epi = (EditPageInterface) editPageNamed("EditBlogComment", newComment);
      epi.setObject(newComment);
      epi.setNextPage(currentPage());
    }
    finally {
      peer.unlock();
    }

    return (WOComponent) epi;
  }

}
