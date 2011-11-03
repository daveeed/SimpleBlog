package com.wowodc.app;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.webobjects.directtoweb.D2W;
import com.wowodc.model.BlogCategory;
import com.wowodc.model.BlogEntry;
import com.wowodc.model.Person;

import er.extensions.appserver.ERXApplication;
import er.extensions.appserver.navigation.ERXNavigationManager;
import er.rest.ERXRestNameRegistry;
import er.rest.routes.ERXRouteRequestHandler;

public class Application extends ERXApplication {
  
  public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);

  public static void main(String[] argv) {
    ERXApplication.main(argv, Application.class);
  }

  public Application() {
    ERXApplication.log.info("Welcome to " + name() + " !");
    setDefaultRequestHandler(requestHandlerForKey(directActionRequestHandlerKey()));
    D2W.setFactory(new Factory());

    ERXRestNameRegistry.registry().setExternalNameForInternalName("Post", BlogEntry.ENTITY_NAME);
    ERXRestNameRegistry.registry().setExternalNameForInternalName("Category", BlogCategory.ENTITY_NAME);
    ERXRestNameRegistry.registry().setExternalNameForInternalName("Author", Person.ENTITY_NAME);

    ERXRouteRequestHandler restRequestHandler = new ERXRouteRequestHandler();
    restRequestHandler.addDefaultRoutes(BlogCategory.ENTITY_NAME);
    restRequestHandler.addDefaultRoutes(BlogEntry.ENTITY_NAME);
    restRequestHandler.addDefaultRoutes(Person.ENTITY_NAME);
    
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
