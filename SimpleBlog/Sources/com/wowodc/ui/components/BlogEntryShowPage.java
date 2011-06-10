package com.wowodc.ui.components;

import com.webobjects.appserver.WOContext;
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
}