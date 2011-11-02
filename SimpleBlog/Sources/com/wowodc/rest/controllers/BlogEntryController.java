package com.wowodc.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.webobjects.foundation.NSArray;
import com.wowodc.model.BlogCategory;
import com.wowodc.model.BlogEntry;
import com.wowodc.model.Person;
import com.wowodc.ui.components.BlogEntryShowPage;

import er.extensions.eof.ERXKeyFilter;
import er.rest.ERXRestContext;

public class BlogEntryController extends BaseRestController {

  public BlogEntryController(WORequest request) {
    super(request);
  }

  @Override
  public WOActionResults createAction() throws Throwable {
    BlogEntry entry = create(filter());
    editingContext().saveChanges();
    return response(entry, filter());
  }

  @Override
  public WOActionResults updateAction() throws Throwable {
    BlogEntry entry = routeObjectForKey("blogEntry");
    update(entry, filter());
    editingContext().saveChanges();
    return response(entry, filter());
  }
  
  @Override
  protected ERXRestContext createRestContext() {
    ERXRestContext restContext = new ERXRestContext(editingContext());
    restContext.setUserInfoForKey("yyyy-MM-dd", "er.rest.dateFormat");
    restContext.setUserInfoForKey("yyyy-MM-dd", "er.rest.timestampFormat");
    return restContext;
  }

  protected ERXKeyFilter filter() {
    ERXKeyFilter filter = ERXKeyFilter.filterWithAttributes();
    filter.include(BlogEntry.CATEGORIES.dot(BlogCategory.CATEGORY_DESCRIPTION));
    filter.include(BlogEntry.CATEGORIES.dot(BlogCategory.URL_FRIENDLY_DESCRIPTION));
    filter.include(BlogEntry.PERSON.dot(Person.FIRST_NAME));
        
    ERXKeyFilter personFilter = ERXKeyFilter.filterWithAttributes();
    personFilter.exclude(Person.PASSWORD);
    personFilter.setAnonymousUpdateEnabled(true);
    filter.include(BlogEntry.PERSON, personFilter);
    filter.setUnknownKeyIgnored(true);
    
    return filter;
  }

  public WOActionResults showAction() throws Throwable {
    String title = routeObjectForKey("title");
    if (title == null) {
      return errorResponse(404);
    }
    BlogEntry post = BlogEntry.fetchRequiredBlogEntry(editingContext(), BlogEntry.TITLE_KEY, title);
    if (isHTMlFormat()) {
      BlogEntryShowPage nextPage = (BlogEntryShowPage)pageWithName(BlogEntryShowPage.class);
      nextPage.setBlogEntry(post);
      return nextPage;
    }
    return response(post, filter());
  }
  
  public WOActionResults indexAction() throws Throwable {
    NSArray<BlogEntry> entries = BlogEntry.fetchAllBlogEntries(editingContext());
    return response(entries, filter());
  }

  public WOActionResults showByTitleAction() throws Throwable {
    return showAction();
  }

}
