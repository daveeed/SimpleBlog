package com.wowodc.rest.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.webobjects.foundation.NSArray;
import com.wowodc.model.BlogCategory;
import com.wowodc.model.BlogEntry;
import com.wowodc.model.Person;
import com.wowodc.model.SyncInfo;
import com.wowodc.ui.components.BlogEntryDetailPage;

import er.extensions.appserver.ERXHttpStatusCodes;
import er.extensions.appserver.ERXResponse;
import er.extensions.eof.ERXKeyFilter;
import er.rest.ERXRestContext;

public class BlogEntryController extends BaseRestController {

  public static final SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);  // Last-Modified: Wed, 15 Nov 1995 04:58:08 GMT

  public BlogEntryController(WORequest request) {
    super(request);
  }

  @Override
  public WOActionResults createAction() throws Throwable {
    BlogEntry entry = create(filter());
    editingContext().saveChanges();
    return response(entry, filter());
  }

  @Override
  public WOActionResults updateAction() throws Throwable {
    BlogEntry entry = routeObjectForKey("blogEntry");
    update(entry, filter());
    editingContext().saveChanges();
    return response(entry, filter());
  }
  
  @Override
  protected ERXRestContext createRestContext() {
    ERXRestContext restContext = new ERXRestContext(editingContext());
    restContext.setUserInfoForKey("yyyy-MM-dd", "er.rest.dateFormat");
    restContext.setUserInfoForKey("yyyy-MM-dd", "er.rest.timestampFormat");
    return restContext;
  }

  protected ERXKeyFilter filter() {
    ERXKeyFilter filter = ERXKeyFilter.filterWithAttributes();
    filter.include(BlogEntry.CATEGORIES.dot(BlogCategory.CATEGORY_DESCRIPTION));
    filter.include(BlogEntry.CATEGORIES.dot(BlogCategory.URL_FRIENDLY_DESCRIPTION));
    filter.include(BlogEntry.PERSON.dot(Person.FIRST_NAME));
        
    ERXKeyFilter personFilter = ERXKeyFilter.filterWithAttributes();
    personFilter.exclude(Person.PASSWORD);
    personFilter.setAnonymousUpdateEnabled(true);
    filter.include(BlogEntry.PERSON, personFilter);
    filter.setUnknownKeyIgnored(true);
    
    return filter;
  }

  @Override
  public WOActionResults showAction() throws Throwable {
    String ifModifiedSinceHeader = this.request().headerForKey("If-Modified-Since");
    String ifNoneMatch = this.request().headerForKey("If-None-Match");
    SyncInfo syncDetails = null;
    BlogEntry post = null;

    if (ifNoneMatch != null) {
      syncDetails = SyncInfo.fetchSyncInfo(editingContext(), SyncInfo.ETAG.eq(ifNoneMatch));
      if (syncDetails != null) {
        return errorResponse(ERXHttpStatusCodes.NOT_MODIFIED);
      } else {
        post = routeObjectForKey("blogEntry");        
      }
    } else {
      post = routeObjectForKey("blogEntry");
      syncDetails = SyncInfo.fetchSyncInfo(editingContext(), SyncInfo.TOKEN.eq(post.entityName() + ":" + post.primaryKey()));
    }
    
    if (syncDetails != null) {
      if (ifModifiedSinceHeader != null) {
        java.util.Date clientDateHeader;
        try {
          clientDateHeader = (java.util.Date)formatter.parseObject(ifModifiedSinceHeader);
          if ((clientDateHeader.equals(syncDetails.lastModified())) || (clientDateHeader.after(syncDetails.lastModified()))) {
            return errorResponse(ERXHttpStatusCodes.NOT_MODIFIED);
          } 
        } catch (ParseException e) {
        }
      }
    }
    
    ERXResponse response = null;
    
    if (isHTMlFormat()) {
      BlogEntryDetailPage nextPage = (BlogEntryDetailPage)pageWithName(BlogEntryDetailPage.class);
      nextPage.setBlogEntry(post);
      nextPage.syncDetails = syncDetails;
      response = (ERXResponse)nextPage.generateResponse();
    } else {
      response = (ERXResponse) response(post, filter());
    }

    if (syncDetails != null) {
      response.setHeader(formatter.format(syncDetails.lastModified()), "Last-Modified");
      response.setHeader(syncDetails.etag(), "Etag");
    }
    
    return response;
  }
  
  public WOActionResults indexAction() throws Throwable {
    NSArray<BlogEntry> entries = BlogEntry.fetchAllBlogEntries(editingContext());
    return response(entries, filter());
  }

  public WOActionResults showByTitleAction() throws Throwable {
    return showAction();
  }

}
