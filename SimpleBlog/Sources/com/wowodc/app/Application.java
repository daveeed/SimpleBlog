package com.wowodc.app;

import com.webobjects.directtoweb.D2W;
import com.wowodc.model.BlogCategory;
import com.wowodc.model.BlogEntry;

import er.extensions.appserver.ERXApplication;
import er.extensions.appserver.navigation.ERXNavigationManager;
import er.rest.ERXRestNameRegistry;
import er.rest.routes.ERXRouteRequestHandler;

public class Application extends ERXApplication {
  public static void main(String[] argv) {
    ERXApplication.main(argv, Application.class);
  }

  public Application() {
    ERXApplication.log.info("Welcome to " + name() + " !");
    setDefaultRequestHandler(requestHandlerForKey(directActionRequestHandlerKey()));
    D2W.setFactory(new Factory());

    ERXRestNameRegistry.registry().setExternalNameForInternalName("Post", BlogEntry.ENTITY_NAME);
    ERXRestNameRegistry.registry().setExternalNameForInternalName("Category", BlogCategory.ENTITY_NAME);

    ERXRouteRequestHandler restRequestHandler = new ERXRouteRequestHandler();
    restRequestHandler.addDefaultRoutes(BlogCategory.ENTITY_NAME);
    restRequestHandler.addDefaultRoutes(BlogEntry.ENTITY_NAME);

    /*
    restRequestHandler.insertRoute(new ERXRoute(BlogEntry.ENTITY_NAME, "/posts", Method.Get, BlogEntryController.class, "index"));
    restRequestHandler.insertRoute(new ERXRoute(BlogEntry.ENTITY_NAME, "/posts/{title:String}", Method.Get, BlogEntryController.class, "showByTitle"));
    restRequestHandler.insertRoute(new ERXRoute(BlogEntry.ENTITY_NAME, "/posts/{title:String}", Method.Put, BlogEntryController.class, "update"));
    restRequestHandler.insertRoute(new ERXRoute(BlogEntry.ENTITY_NAME, "/posts", Method.Post, BlogEntryController.class, "create"));
    */
    
    ERXRouteRequestHandler.register(restRequestHandler);
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
}
