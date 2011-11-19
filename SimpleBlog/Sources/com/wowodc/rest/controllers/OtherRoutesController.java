package com.wowodc.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.wowodc.ui.components.BlogEntryIndexPage;
import com.wowodc.ui.components.Main;
import com.wowodc.ui.components.RssFeedPage;

import er.rest.format.ERXRestFormat;

public class OtherRoutesController extends BaseRestController {

  public OtherRoutesController(WORequest request) {
    super(request);
  }
  
  public WOActionResults mainPageAction() {
    return pageWithName(BlogEntryIndexPage.class);
  }
  
  public WOActionResults adminPageAction() {
    return pageWithName(Main.class);
  }
  
  @Override
  protected ERXRestFormat defaultFormat() {
    return ERXRestFormat.html();
  }

}
