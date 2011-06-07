package com.wowodc.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.wowodc.model.BlogCategory;
import com.wowodc.model.BlogEntry;

import er.extensions.eof.ERXKeyFilter;

public class BlogEntryController extends BaseRestController {

  public BlogEntryController(WORequest request) {
    super(request);
  }
  
  @Override
  public WOActionResults createAction() throws Throwable {
    BlogEntry entry = create(filter());
    editingContext().saveChanges();
    return response(entry, null);
  }
  
  @Override
  public WOActionResults updateAction() throws Throwable {
    BlogEntry entry = routeObjectForKey("blogEntry");
    update(entry, null);
    editingContext().saveChanges();
    return response(entry, null);
  }
  
  protected ERXKeyFilter filter() {
    ERXKeyFilter filter = ERXKeyFilter.filterWithAttributes();
    filter.include(BlogEntry.CATEGORIES.dot(BlogCategory.NAME));
    filter.include(BlogEntry.CATEGORIES.dot(BlogCategory.SHORT_NAME));
    return filter;
  }
  
}
