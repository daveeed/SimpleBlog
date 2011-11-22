package com.wowodc.ui.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.wowodc.model.BlogEntry;

import er.extensions.components.ERXStatelessComponent;
import er.extensions.eof.ERXEC;
import er.rest.format.ERXRestFormat;
import er.rest.routes.ERXRouteUrlUtils;

public class RssFeedPage extends ERXStatelessComponent {

  public BlogEntry item;
  private EOEditingContext _editingContext;

  public RssFeedPage(WOContext context) {
    super(context);
  }
  
  public String linkToPost() {
    return ERXRouteUrlUtils.actionUrlForRecord(this.context(), item, "show", ERXRestFormat.html().name(), null, false, false);
  }
  
  private EOEditingContext editingContext() {
    if (_editingContext == null) {
      _editingContext = ERXEC.newEditingContext();
    }
    return _editingContext;
  }
  
  public NSArray<BlogEntry> blogEntries() {
    return BlogEntry.fetchAllBlogEntries(editingContext(), BlogEntry.LAST_MODIFED.descs());
  }
  
}