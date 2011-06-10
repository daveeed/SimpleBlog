package com.wowodc.ui.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.wowodc.model.BlogEntry;

public class BlogEntryIndexPage extends RestComponent {
  
  private BlogEntry entryItem;

  public BlogEntryIndexPage(WOContext context) {
    super(context);
  }
    
  public NSArray<BlogEntry> entries() {
    return BlogEntry.fetchAllBlogEntries(editingContext(), BlogEntry.TIMESTAMP_CREATION.descs());
  }

  public BlogEntry entryItem() {
    return entryItem;
  }

  public void setEntryItem(BlogEntry entryItem) {
    this.entryItem = entryItem;
  }
  
}