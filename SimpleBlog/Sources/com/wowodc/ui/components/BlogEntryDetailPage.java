package com.wowodc.ui.components;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.wowodc.model.BlogEntry;
import com.wowodc.model.SyncInfo;

import er.rest.routes.ERXRouteParameter;

public class BlogEntryDetailPage extends RestComponent {

  private BlogEntry entryItem;
  SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);  // Last-Modified: Wed, 15 Nov 1995 04:58:08 GMT
  public SyncInfo syncDetails = null;
  
  public BlogEntryDetailPage(WOContext context) {
    super(context);
  }

  public BlogEntry blogEntry() {
    return entryItem;
  }

  @ERXRouteParameter
  public void setBlogEntry(BlogEntry _entryItem) {
    this.entryItem = _entryItem;
  }
  
  @Override
  public void appendToResponse(WOResponse response, WOContext context) {
    if (syncDetails != null) {
      response.setHeader(formatter.format(syncDetails.lastModified()), "Last-Modified");
      response.setHeader(syncDetails.etag(), "Etag");
    }

    super.appendToResponse(response, context);
  }
}