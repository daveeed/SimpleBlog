package com.wowodc.app;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.webobjects.directtoweb.D2W;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOKeyGlobalID;
import com.webobjects.eocontrol.EOObjectStoreCoordinator;
import com.webobjects.eocontrol.EOQualifierEvaluation;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSLog;
import com.webobjects.foundation.NSNotification;
import com.webobjects.foundation.NSNotificationCenter;
import com.webobjects.foundation.NSSelector;
import com.webobjects.foundation.NSTimestamp;
import com.wowodc.model.BlogCategory;
import com.wowodc.model.BlogEntry;
import com.wowodc.model.Person;
import com.wowodc.model.SyncInfo;
import com.wowodc.model.enums.SyncInfoStatus;
import com.wowodc.rest.controllers.OtherRoutesController;
import com.wowodc.rest.controllers.RssController;

import er.extensions.appserver.ERXApplication;
import er.extensions.appserver.navigation.ERXNavigationManager;
import er.extensions.eof.ERXEC;
import er.extensions.eof.ERXEnterpriseObject;
import er.extensions.foundation.ERXArrayUtilities;
import er.extensions.foundation.ERXRandomGUID;
import er.rest.ERXRestNameRegistry;
import er.rest.routes.ERXRoute;
import er.rest.routes.ERXRouteRequestHandler;

public class Application extends ERXApplication {
  
  public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);

  public static void main(String[] argv) {
    ERXApplication.main(argv, Application.class);
  }

  public Application() {
    ERXApplication.log.info("Welcome to " + name() + " !");
    D2W.setFactory(new Factory());

    ERXRestNameRegistry.registry().setExternalNameForInternalName("Post", BlogEntry.ENTITY_NAME);
    ERXRestNameRegistry.registry().setExternalNameForInternalName("Category", BlogCategory.ENTITY_NAME);
    ERXRestNameRegistry.registry().setExternalNameForInternalName("Author", Person.ENTITY_NAME);

    ERXRouteRequestHandler restRequestHandler = new ERXRouteRequestHandler();
    restRequestHandler.addDefaultRoutes(BlogCategory.ENTITY_NAME);
    restRequestHandler.addDefaultRoutes(BlogEntry.ENTITY_NAME);
    restRequestHandler.addDefaultRoutes(Person.ENTITY_NAME);
    restRequestHandler.insertRoute(new ERXRoute("Other", "", ERXRoute.Method.Get, OtherRoutesController.class, "mainPage"));
    restRequestHandler.insertRoute(new ERXRoute("Other", "/admin", ERXRoute.Method.Get, OtherRoutesController.class, "adminPage"));
    restRequestHandler.insertRoute(new ERXRoute("Rss", "/rsses/main", ERXRoute.Method.Get, RssController.class, "main"));
    
    ERXRouteRequestHandler.register(restRequestHandler);
    
    setDefaultRequestHandler(restRequestHandler);
  }

  @Override
  public void finishInitialization() {
    super.finishInitialization();

    // Setup main navigation
    ERXNavigationManager.manager().configureNavigation();
  }

  public String _rewriteURL(String url) {
    String processedURL = url;
    if (url != null && _replaceApplicationPathPattern != null && _replaceApplicationPathReplace != null) {
      processedURL = processedURL.replaceFirst(_replaceApplicationPathPattern, _replaceApplicationPathReplace);
    }
    return processedURL;
  }
  
  @Override
  public void didFinishLaunching() {
    super.didFinishLaunching();
    NSSelector selector = new NSSelector("coordinateChanges", new Class[] { NSNotification.class } );
    NSNotificationCenter.defaultCenter().addObserver( this, selector, EOObjectStoreCoordinator.ObjectsChangedInStoreNotification, EOObjectStoreCoordinator.defaultCoordinator());
  }
  
  @SuppressWarnings("unchecked")
  public void coordinateChanges(NSNotification notification) {
    NSDictionary userInfo = (NSDictionary)notification.userInfo();
    EOEditingContext ec = ERXEC.newEditingContext(new EOObjectStoreCoordinator());
    ec.lock();

    log.debug("Change Notification " + userInfo);
    try {
      NSLog.out.appendln("deleted" + (NSArray)userInfo.objectForKey("deleted"));
      NSLog.out.appendln("inserted: " + (NSArray)userInfo.objectForKey("inserted"));
      NSLog.out.appendln("updated: " + (NSArray)userInfo.objectForKey("updated"));
            
      NSArray result = ERXArrayUtilities.filteredArrayWithQualifierEvaluation((NSArray)userInfo.objectForKey("inserted"), new EOSyncEntityFilter() );
      for ( Object id : result ) {
        EOKeyGlobalID gid = (EOKeyGlobalID)id;  
        ERXEnterpriseObject eo = (ERXEnterpriseObject)ec.faultForGlobalID( gid, ec);
        
        SyncInfo.createSyncInfo(ec, ERXRandomGUID.newGid(), new NSTimestamp(), SyncInfoStatus.INSERTED, eo.entityName() + ":" + eo.primaryKey());
      }

      ec.saveChanges();
    }
    finally {
      ec.unlock();
    }
  }
  
  public class EOSyncEntityFilter implements EOQualifierEvaluation
  {
        public boolean evaluateWithObject(Object object) 
        {
          EOKeyGlobalID eokgid = (EOKeyGlobalID)object;
            return syncEntityNames().containsObject(eokgid.entityName());
        }
  }
  
  public NSArray<String> syncEntityNames() {
    return new NSArray<String>( BlogEntry.ENTITY_NAME );
  }
  
}
