package com.wowodc.app;

import com.webobjects.appserver.WOComponent;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.EditPageInterface;
import com.webobjects.directtoweb.ErrorPageInterface;
import com.webobjects.directtoweb.ListPageInterface;
import com.webobjects.directtoweb.QueryPageInterface;
import com.webobjects.eoaccess.EODatabaseDataSource;
import com.webobjects.eocontrol.EODataSource;
import com.webobjects.eocontrol.EOEditingContext;

import er.extensions.eof.ERXEC;

public class MainNavigationController {

  private Session _session;
  public String BLOGENTRY = "BlogEntry";

  public MainNavigationController(Session s) {
    super();
    _session = s;
  }

  // NAV ACTIONS

  public WOComponent homeAction() {
    return D2W.factory().defaultPage(session());
  }

  // BLOG ENTRY ACTIONS

  public WOComponent listBlogAction() {
    return listPageForEntityNamed(BLOGENTRY);
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

  // GENERIC ACTIONS

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
