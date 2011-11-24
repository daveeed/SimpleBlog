package com.wowodc.rest.delegates;

import com.webobjects.eocontrol.EOClassDescription;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;
import com.wowodc.model.BlogEntry;

import er.extensions.eof.ERXEC;
import er.extensions.eof.ERXQ;
import er.rest.ERXAbstractRestDelegate;
import er.rest.ERXRestContext;

public class BlogEntryRestDelegate extends ERXAbstractRestDelegate {

  public Object primaryKeyForObject(Object obj, ERXRestContext context) {
    return ((BlogEntry)obj).uniqueTitle();
  }

  public Object createObjectOfEntityWithID(EOClassDescription entity, Object id, ERXRestContext context) {
    return BlogEntry.createBlogEntry(ERXEC.newEditingContext(), null, new NSTimestamp(), new NSTimestamp(), null, null, null);
  }

  public Object objectOfEntityWithID(EOClassDescription entity, Object id, ERXRestContext context) {
    NSArray<BlogEntry> entries = ERXQ.filtered(BlogEntry.fetchAllBlogEntries(ERXEC.newEditingContext()), ERXQ.is("uniqueTitle", id));
    return entries.size() == 0 ? null : entries.objectAtIndex(0);  
  }

}
