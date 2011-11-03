package com.wowodc.ui.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.wowodc.app.Application;
import com.wowodc.model.BlogEntry;

import er.rest.routes.ERXRouteParameter;

public class BlogEntryShowPage extends RestComponent {

  private BlogEntry entryItem;
  
  public BlogEntryShowPage(WOContext context) {
    super(context);
  }

  public BlogEntry blogEntry() {
    return entryItem;
  }

  @ERXRouteParameter
  public void setBlogEntry(BlogEntry entryItem) {
    this.entryItem = entryItem;
  }
  
  @Override
  public void appendToResponse(WOResponse response, WOContext context) {
    response.setHeader(Application.dateFormatter.format(entryItem.lastModifed()), "Last-Modified");
    super.appendToResponse(response, context);
  }
}