package com.wowodc.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.wowodc.ui.components.RssFeedPage;

import er.rest.format.ERXRestFormat;

public class RssController extends BaseRestController {

  public RssController(WORequest request) {
    super(request);
  }
  
  public WOActionResults mainAction() {
    return pageWithName(RssFeedPage.class);
  }
  
  @Override
  protected ERXRestFormat defaultFormat() {
    return ERXRestFormat.xml();
  }

}
