package com.wowodc.ui.components;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.wowodc.model.BlogEntry;

import er.extensions.eof.ERXEC;
import er.rest.routes.IERXRouteComponent;

public class BlogEntryIndexPage extends WOComponent implements IERXRouteComponent {
  
  private EOEditingContext editingContext;
  private BlogEntry entryItem;

  public BlogEntryIndexPage(WOContext context) {
    super(context);
  }
  
  public EOEditingContext editingContext() {
    if (editingContext == null) {
      editingContext = ERXEC.newEditingContext();
    }
    return editingContext;
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