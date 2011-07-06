package com.wowodc.app;

import com.webobjects.appserver.WOComponent;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.EditPageInterface;
import com.webobjects.directtoweb.ErrorPageInterface;
import com.webobjects.directtoweb.InspectPageInterface;
import com.webobjects.directtoweb.ListPageInterface;
import com.webobjects.directtoweb.QueryPageInterface;
import com.webobjects.eoaccess.EODatabaseDataSource;
import com.webobjects.eocontrol.EODataSource;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.wowodc.model.BlogComment;

import er.extensions.eof.ERXEC;

public class MainNavigationController {

  private Session _session;
  public String BLOGENTRY = "BlogEntry";
  public String BLOGCATEGORY = "BlogCategory";
  public String BLOGCOMMENT = "BlogComment";
  public String PERSON = "Person";
  public String ROLE = "Role";

  public MainNavigationController(Session s) {
    super();
    _session = s;
  }

  // NAV ACTIONS

  public WOComponent homeAction() {
    return D2W.factory().pageForConfigurationNamed("Instructions", session());
  }

  // BLOG ENTRY ACTIONS

  public WOComponent listBlogAction() {
    return listPageForEntityNamed(BLOGENTRY);
  }
  
  public WOComponent listMyBlogAction() {
    ListPageInterface lpi = (ListPageInterface) D2W.factory().pageForConfigurationNamed("ListMyBlogEntry", session());
    ERXEC ec = (ERXEC) (session()._user.editingContext());
    EODataSource ds = new EODatabaseDataSource(ec, "BlogEntry");
    // limit the blog entries returned to the logged in user
    ds.qualifyWithRelationshipKey("blogEntries", session()._user);
    lpi.setDataSource(ds);
    WOComponent nextPage = (WOComponent) lpi;
    return nextPage;
  }
  
  public WOComponent listPublicBlogAction() {
    ListPageInterface lpi = (ListPageInterface) D2W.factory().pageForConfigurationNamed("ListPublicBlogEntry", session());
    EOEditingContext ec = ERXEC.newEditingContext();
    EODataSource ds = new EODatabaseDataSource(ec, "BlogEntry");
    lpi.setDataSource(ds);
    WOComponent nextPage = (WOComponent) lpi;
    return nextPage;
  }

  public WOComponent listGroupedBlogAction() {
    EOEditingContext ec = ERXEC.newEditingContext();
    ec.lock();
    try {
      EODatabaseDataSource ds = new EODatabaseDataSource(ec, BLOGENTRY);
      ListPageInterface lpi = (ListPageInterface) pageForConfigurationNamed("ListGroupBlogEntry");
      lpi.setDataSource(ds);
      return (WOComponent) lpi;
    }
    finally {
      ec.unlock();
    }
  }

  public WOComponent listXMLBlogAction() {
    EOEditingContext ec = ERXEC.newEditingContext();
    ec.lock();
    try {
      EODatabaseDataSource ds = new EODatabaseDataSource(ec, BLOGENTRY);
      ListPageInterface lpi = (ListPageInterface) pageForConfigurationNamed("ListXMLBlogEntry");
      lpi.setDataSource(ds);
      return (WOComponent) lpi;
    }
    finally {
      ec.unlock();
    }
  }

  public WOComponent listGroupedCSVBlogAction() {
    EOEditingContext ec = ERXEC.newEditingContext();
    ec.lock();
    try {
      EODatabaseDataSource ds = new EODatabaseDataSource(ec, BLOGENTRY);
      ListPageInterface lpi = (ListPageInterface) pageForConfigurationNamed("ListGroupCSVBlogEntry");
      lpi.setDataSource(ds);
      return (WOComponent) lpi;
    }
    finally {
      ec.unlock();
    }
  }

  public WOComponent listTableBlogAction() {
    EOEditingContext ec = ERXEC.newEditingContext();
    ec.lock();
    try {
      EODatabaseDataSource ds = new EODatabaseDataSource(ec, BLOGENTRY);
      ListPageInterface lpi = (ListPageInterface) pageForConfigurationNamed("ListTableBlogEntry");
      lpi.setDataSource(ds);
      return (WOComponent) lpi;
    }
    finally {
      ec.unlock();
    }
  }

  public WOComponent createBlogAction() {
    return newObjectForEntityName(BLOGENTRY);
  }
  
  // BLOG COMMENTS
  public WOComponent editBlogComment(BlogComment aBlogComment) {
    return editPageForEntityName(BLOGCOMMENT, aBlogComment);
  }
  
  // BLOG CATEGORY
  
  public WOComponent listBlogCategoryAction() {
    return listPageForEntityNamed(BLOGCATEGORY);
  }
  
  public WOComponent createBlogCategoryAction() {
    return newObjectForEntityName(BLOGCATEGORY);
  }
  
  // Person
  
  public WOComponent listPersonAction() {
    return listPageForEntityNamed(PERSON);
  }
  
  public WOComponent createPersonAction() {
    return newObjectForEntityName(PERSON);
  }
  
  // Role
  
  public WOComponent listRoleAction() {
    return listPageForEntityNamed(ROLE);
  }
  
  public WOComponent createRoleAction() {
    return newObjectForEntityName(ROLE);
  }

  // GENERIC ACTIONS
  
  public WOComponent inspectPageForEntityName(String entityName, EOEnterpriseObject eo) {
    InspectPageInterface newInspectPage = D2W.factory().inspectPageForEntityNamed(entityName, session());
    newInspectPage.setObject(eo);
    return (WOComponent) newInspectPage;
  }

  public WOComponent editPageForEntityName(String entityName, EOEnterpriseObject eo) {
    EditPageInterface newEditPage = D2W.factory().editPageForEntityNamed(entityName, session());
    newEditPage.setObject(eo);
    return (WOComponent) newEditPage;
  }

  public WOComponent queryPageForEntityName(String entityName) {
    QueryPageInterface newQueryPage = D2W.factory().queryPageForEntityNamed(entityName, session());
    return (WOComponent) newQueryPage;
  }

  public WOComponent listPageForEntityNamed(String entityName) {
    EOEditingContext ec = ERXEC.newEditingContext();
    ec.lock();
    try {
      ListPageInterface newListPage = D2W.factory().listPageForEntityNamed(entityName, _session);
      EODataSource ds = new EODatabaseDataSource(ec, entityName);
      newListPage.setDataSource(ds);
      return (WOComponent) newListPage;
    }
    finally {
      ec.unlock();
    }

  }

  public WOComponent newObjectForEntityName(String entityName) {
    WOComponent nextPage = null;
    try {
      EditPageInterface epi = D2W.factory().editPageForNewObjectWithEntityNamed(entityName, session());
      epi.setNextPage(session().context().page());
      nextPage = (WOComponent) epi;
    }
    catch (IllegalArgumentException e) {
      ErrorPageInterface epf = D2W.factory().errorPage(session());
      epf.setMessage(e.toString());
      epf.setNextPage(session().context().page());
      nextPage = (WOComponent) epf;
    }
    return nextPage;
  }

  protected WOComponent pageForConfigurationNamed(String name) {
    WOComponent page = D2W.factory().pageForConfigurationNamed(name, session());
    return page;
  }

  // ACCESSORS

  public Session session() {
    return _session;
  }

  public void setSession(Session s) {
    _session = s;
  }
}
