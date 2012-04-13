package com.wowodc.ui.components;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.wowodc.model.BlogEntry;
import com.wowodc.model.SyncInfo;
import com.wowodc.rest.controllers.BlogEntryController;

import er.rest.routes.ERXRouteParameter;

public class BlogEntryDetailPage extends RestComponent {

  private BlogEntry entryItem;
  private SyncInfo syncDetails = null;
  
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

    super.appendToResponse(response, context);
  }
}