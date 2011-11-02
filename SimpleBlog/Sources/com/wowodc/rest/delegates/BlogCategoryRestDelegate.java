package com.wowodc.rest.delegates;

import com.webobjects.eocontrol.EOClassDescription;
import com.webobjects.foundation.NSArray;
import com.wowodc.model.BlogCategory;

import er.extensions.eof.ERXEC;
import er.extensions.eof.ERXQ;
import er.rest.ERXAbstractRestDelegate;
import er.rest.ERXRestContext;

public class BlogCategoryRestDelegate extends ERXAbstractRestDelegate {

  public Object primaryKeyForObject(Object obj, ERXRestContext context) {
    return ((BlogCategory)obj).categoryDescription();
  }

  public Object createObjectOfEntityWithID(EOClassDescription entity, Object id, ERXRestContext context) {
    return BlogCategory.createBlogCategory(ERXEC.newEditingContext(), null);
  }

  public Object objectOfEntityWithID(EOClassDescription entity, Object id, ERXRestContext context) {
    NSArray<BlogCategory> entries = ERXQ.filtered(BlogCategory.fetchAllBlogCategories(ERXEC.newEditingContext()), ERXQ.is("categoryDescription", id));
    return entries.size() == 0 ? null : entries.objectAtIndex(0);  
  }

}
