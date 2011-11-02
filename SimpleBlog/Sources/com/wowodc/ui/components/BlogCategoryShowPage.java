package com.wowodc.ui.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.wowodc.model.BlogCategory;
import com.wowodc.model.BlogEntry;

import er.rest.routes.ERXRouteParameter;

public class BlogCategoryShowPage extends RestComponent {

  private BlogCategory category;
  private BlogEntry entryItem;

  public BlogCategoryShowPage(WOContext context) {
    super(context);
  }

  @ERXRouteParameter
  public void setBlogCategory(BlogCategory category) {
    this.category = category;
  }

  public BlogCategory blogCategory() {
    return category;  
  }

  public void setEntryItem(BlogEntry entryItem) {
    this.entryItem = entryItem;
  }

  public BlogEntry entryItem() {
    return entryItem;
  }

  public NSArray<BlogEntry> postsForCategory() {
    return BlogEntry.fetchBlogEntries(editingContext(), BlogEntry.CATEGORIES.eq(category), BlogEntry.TIMESTAMP_CREATION.descs());
  }
}