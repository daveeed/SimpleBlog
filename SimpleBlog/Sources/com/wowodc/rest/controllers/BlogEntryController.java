package com.wowodc.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.wowodc.model.BlogCategory;
import com.wowodc.model.BlogEntry;
import com.wowodc.model.Person;
import com.wowodc.ui.components.BlogEntryShowPage;

import er.extensions.eof.ERXKeyFilter;

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
    update(entry, null);
    editingContext().saveChanges();
    return response(entry, filter());
  }

  protected ERXKeyFilter filter() {
    ERXKeyFilter filter = ERXKeyFilter.filterWithAttributes();
    filter.include(BlogEntry.CATEGORIES.dot(BlogCategory.NAME));
    filter.include(BlogEntry.CATEGORIES.dot(BlogCategory.SHORT_NAME));
    filter.include(BlogEntry.PERSON.dot(Person.FIRST_NAME));
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

  public WOActionResults showByTitleAction() throws Throwable {
    return showAction();
  }

}
