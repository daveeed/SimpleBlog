package com.wowodc.ui.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.wowodc.model.BlogCategory;
import com.wowodc.model.BlogEntry;
import com.wowodc.model.Person;

public class BlogEntryIndexPage extends RestComponent {

  private BlogEntry entryItem;
  private BlogCategory categoryItem;
  private Person authorItem;

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

}