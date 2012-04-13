package com.wowodc.ui.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;
import com.wowodc.model.BlogCategory;
import com.wowodc.model.BlogEntry;
import com.wowodc.model.Person;
import com.wowodc.model.SyncInfo;
import com.wowodc.rest.controllers.BlogEntryController;

public class BlogEntryListPage extends RestComponent {

  private BlogEntry entryItem;
  private BlogCategory categoryItem;
  private Person authorItem;
  private BlogCategory entryCategory;
  private SyncInfo syncDetails = null;

  public BlogEntryListPage(WOContext context) {
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

  public NSArray<BlogCategory> categories() {
    return BlogCategory.fetchAllBlogCategories(editingContext());
  }

  public BlogCategory categoryItem() {
    return categoryItem;
  }

  public void setCategoryItem(BlogCategory categoryItem) {
    this.categoryItem = categoryItem;
  }
  
  public NSArray<Person> authors() {
    return Person.fetchPersons(editingContext(), Person.BLOG_ENTRIES.isNotNull(), Person.LAST_NAME.ascs());
  }
  
  public Person authorItem() {
    return authorItem;
  }

  public void setAuthorItem(Person authorItem) {
    this.authorItem = authorItem;
  }
  
  public BlogCategory entryCategory() {
    return entryCategory;
  }

  public void setEntryCategory(BlogCategory entryCategory) {
    this.entryCategory = entryCategory;
  }
  
  public SyncInfo syncDetails() {
    return syncDetails;
  }
  
  public void setSyncDetails(SyncInfo syncDetails) {
    this.syncDetails = syncDetails;
  }
  
  @Override
  public void appendToResponse(WOResponse response, WOContext context) {
    if (syncDetails != null) {
      response.setHeader(BlogEntryController.formatter.format(syncDetails.lastModified()), "Last-Modified");
      response.setHeader(syncDetails.etag(), "Etag");
    }

    response.setHeader("max-age=300", "Cache-Control");

    super.appendToResponse(response, context);
  }
  
}