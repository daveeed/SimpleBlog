package com.wowodc.rest.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORedirect;
import com.webobjects.appserver.WORequest;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;
import com.wowodc.model.BlogCategory;
import com.wowodc.model.BlogEntry;
import com.wowodc.model.DelegatePKHistory;
import com.wowodc.model.Person;
import com.wowodc.model.SyncInfo;
import com.wowodc.model.enums.SyncInfoStatus;
import com.wowodc.ui.components.BlogEntryDetailPage;

import er.extensions.appserver.ERXHttpStatusCodes;
import er.extensions.appserver.ERXResponse;
import er.extensions.eof.ERXKeyFilter;
import er.rest.ERXRestContext;
import er.rest.format.ERXRestFormat;
import er.rest.routes.ERXRouteUrlUtils;

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
    String ifNoneMatchHeader = this.request().headerForKey("If-None-Match");
    SyncInfo syncDetails = null;
    BlogEntry post = null;
    String uniqueTitle = routeObjectForKey("uniqueTitle");

    if (ifNoneMatchHeader != null) {
      syncDetails = SyncInfo.fetchSyncInfo(editingContext(), SyncInfo.ETAG.eq(ifNoneMatchHeader));
      if (syncDetails != null) {
        if (syncDetails.status() == SyncInfoStatus.DELETED) {
          return response(ERXHttpStatusCodes.GONE);
        } else {
          return response(ERXHttpStatusCodes.NOT_MODIFIED);
        }
      } else {
        post = BlogEntry.fetchBlogEntry(editingContext(), BlogEntry.UNIQUE_TITLE.eq(uniqueTitle));
      }
    } else if (ifModifiedSinceHeader != null) {
      java.util.Date ifModifiedSince = formatter.parse(ifModifiedSinceHeader);
      syncDetails = SyncInfo.fetchSyncInfo(editingContext(), SyncInfo.LAST_MODIFIED.eq(new NSTimestamp(ifModifiedSince)));
      if (syncDetails != null) {
        if (syncDetails.status() == SyncInfoStatus.DELETED) {
          return response(ERXHttpStatusCodes.GONE);
        } else {
          return response(ERXHttpStatusCodes.NOT_MODIFIED);
        }
      } else {
        post = BlogEntry.fetchBlogEntry(editingContext(), BlogEntry.UNIQUE_TITLE.eq(uniqueTitle));
      }
    } else {
      post = BlogEntry.fetchBlogEntry(editingContext(), BlogEntry.UNIQUE_TITLE.eq(uniqueTitle));
      if (post != null) {
        syncDetails = SyncInfo.fetchSyncInfo(editingContext(), SyncInfo.DELEGATED_PRIMARY_KEY_VALUE.eq(post.uniqueTitle()));
        if (syncDetails != null) {
          if (syncDetails.status() == SyncInfoStatus.DELETED) {
            return response(ERXHttpStatusCodes.GONE);
          }
        }
      } else {
        NSArray<DelegatePKHistory> histories = DelegatePKHistory.fetchDelegatePKHistories(editingContext(), DelegatePKHistory.DELEGATED_PRIMARY_KEY_VALUE.eq(uniqueTitle), null);
        if (histories.count() > 0) {
          syncDetails = histories.lastObject().syncInfo();
          post = BlogEntry.fetchBlogEntry(editingContext(), BlogEntry.UNIQUE_TITLE.eq(syncDetails.delegatedPrimaryKeyValue()));
          WORedirect redirect = new WORedirect(this.context());
          redirect.setUrl(ERXRouteUrlUtils.actionUrlForRecord(this.context(), post, "show", ERXRestFormat.html().name(), null, false, false));
          return redirect;
        } else {
          return errorResponse(ERXHttpStatusCodes.NOT_FOUND);
        }
      }
    }
    
    if (syncDetails != null) {
      if (ifModifiedSinceHeader != null) {
        java.util.Date clientDateHeader;
        try {
          clientDateHeader = (java.util.Date)formatter.parseObject(ifModifiedSinceHeader);
          if ((clientDateHeader.equals(syncDetails.lastModified())) || (clientDateHeader.after(syncDetails.lastModified()))) {
            return response(ERXHttpStatusCodes.NOT_MODIFIED);
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

    response.setHeader("max-age=300", "Cache-Control");
    
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
