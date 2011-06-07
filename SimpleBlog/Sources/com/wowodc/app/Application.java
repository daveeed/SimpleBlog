package com.wowodc.app;

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

    ERXRestNameRegistry.registry().setExternalNameForInternalName("Post", BlogEntry.ENTITY_NAME);
    ERXRestNameRegistry.registry().setExternalNameForInternalName("Category", BlogCategory.ENTITY_NAME);

    ERXRouteRequestHandler requestHandler = new ERXRouteRequestHandler();
    requestHandler.addDefaultRoutes(BlogEntry.ENTITY_NAME);
    requestHandler.addDefaultRoutes(BlogCategory.ENTITY_NAME);
    ERXRouteRequestHandler.register(requestHandler);
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
