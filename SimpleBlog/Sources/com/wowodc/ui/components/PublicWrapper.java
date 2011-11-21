package com.wowodc.ui.components;

import com.webobjects.appserver.WOContext;

import er.extensions.components.ERXStatelessComponent;
import er.rest.format.ERXRestFormat;
import er.rest.routes.ERXRouteUrlUtils;

public class PublicWrapper extends ERXStatelessComponent {
    
  public PublicWrapper(WOContext context) {
    super(context);
  }
  
  public String pageTitle() {
    Object pageTitle = valueForBinding("pageTitle");
    return (String)pageTitle;
  }
  
  public String linkToRssFeed() {
    return ERXRouteUrlUtils.actionUrlForEntityType(this.context(), "Rss", "main", ERXRestFormat.xml().name(), null, false, false);
  }
  
}