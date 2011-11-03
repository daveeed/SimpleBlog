package com.wowodc.ui.components;

import com.webobjects.appserver.WOContext;

import er.extensions.components.ERXStatelessComponent;

public class PublicWrapper extends ERXStatelessComponent {
    
  public PublicWrapper(WOContext context) {
    super(context);
  }
  
  public String pageTitle() {
    Object pageTitle = valueForBinding("pageTitle");
    return (String)pageTitle;
  }
  
}