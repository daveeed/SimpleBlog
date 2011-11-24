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
import com.wowodc.ui.components.BlogEntryListPage;

import er.extensions.appserver.ERXHttpStatusCodes;
import er.extensions.appserver.ERXResponse;
import er.extensions.eof.ERXKeyFilter;
import er.rest.ERXRestContext;
import er.rest.format.ERXRestFormat;
import er.rest.routes.ERXRouteResults;
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
  
  public String ifNoneMatchHeader() {
    return this.request().headerForKey("If-None-Match");
  }
  
  public NSTimestamp ifModifiedSinceHeader() {
    String ifModifiedSinceHeader = this.request().headerForKey("If-Modified-Since");
    if (ifModifiedSinceHeader != null) {
      java.util.Date ifModifiedSince;
      try {
        ifModifiedSince = formatter.parse(ifModifiedSinceHeader);
        return new NSTimestamp(ifModifiedSince);
      }
      catch (ParseException e) {
        return null;
      }
    }
    return null;
  }

  public SyncInfo syncInfoForETag() {
    return SyncInfo.fetchSyncInfo(editingContext(), SyncInfo.ETAG.eq(ifNoneMatchHeader()));
  }
  
  public SyncInfo syncInfoForLastModified() throws Throwable {
    return SyncInfo.fetchSyncInfo(editingContext(), SyncInfo.LAST_MODIFIED.eq(ifModifiedSinceHeader()));
  }
  
  public WOActionResults responseForNotModified(SyncInfo syncDetails) {
    if (syncDetails.status() == SyncInfoStatus.DELETED) {
      return response(ERXHttpStatusCodes.GONE);
    } else {
      return response(ERXHttpStatusCodes.NOT_MODIFIED);
    }
  }
  
  public boolean isDeleted(String delegatedPK) {
    SyncInfo syncDetails = SyncInfo.fetchSyncInfo(editingContext(), SyncInfo.DELEGATED_PRIMARY_KEY_VALUE.eq(delegatedPK));
    if (syncDetails != null) {
      if (syncDetails.status() == SyncInfoStatus.DELETED) {
        return true;
      }
    }
    return false;
  }
  
  public SyncInfo delegatedPKInHistory(String delegatedPK) {
    NSArray<DelegatePKHistory> histories = DelegatePKHistory.fetchDelegatePKHistories(editingContext(), DelegatePKHistory.DELEGATED_PRIMARY_KEY_VALUE.eq(delegatedPK), null);
    if (histories.count() > 0) {
      return histories.lastObject().syncInfo();
    }
    return null;
  }
  
  @Override
  public WOActionResults showAction() throws Throwable {
    SyncInfo syncDetails = null;
    BlogEntry post = null;
    String uniqueTitle = routeObjectForKey("uniqueTitle");

    syncDetails = syncInfoForETag();
    if (syncDetails != null) {
      return responseForNotModified(syncDetails);
    } else {
      syncDetails = syncInfoForLastModified();
      if (syncDetails != null) {
        return responseForNotModified(syncDetails);
      } else {
        syncDetails = SyncInfo.fetchSyncInfo(editingContext(), SyncInfo.DELEGATED_PRIMARY_KEY_VALUE.eq(uniqueTitle));
        post = BlogEntry.fetchBlogEntry(editingContext(), BlogEntry.UNIQUE_TITLE.eq(uniqueTitle));
      }
    }
    
    if (post != null) {
      if (isDeleted(post.uniqueTitle())) {
        return response(ERXHttpStatusCodes.GONE);
      }
    } else {
      syncDetails = delegatedPKInHistory(uniqueTitle);
      if (syncDetails != null) {
        post = BlogEntry.fetchBlogEntry(editingContext(), BlogEntry.UNIQUE_TITLE.eq(syncDetails.delegatedPrimaryKeyValue()));
        if (post != null) {
          WORedirect redirect = new WORedirect(this.context());
          redirect.setUrl(ERXRouteUrlUtils.actionUrlForRecord(this.context(), post, "show", ERXRestFormat.html().name(), null, false, false));
        } else {
          return errorResponse(ERXHttpStatusCodes.NOT_FOUND);          
        }
      } else {
        return errorResponse(ERXHttpStatusCodes.NOT_FOUND);
      }
    }
    
    ERXResponse response = null;
    
    if (isHTMlFormat()) {
      BlogEntryDetailPage nextPage = (BlogEntryDetailPage)pageWithName(BlogEntryDetailPage.class);
      nextPage.setBlogEntry(post);
      nextPage.setSyncDetails(syncDetails);
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
    ERXRouteResults response = null;

    NSArray<SyncInfo> syncDetails = SyncInfo.fetchSyncInfos(editingContext(), SyncInfo.TOKEN.like(BlogEntry.ENTITY_NAME + ":*"), SyncInfo.LAST_MODIFIED.ascs());
    SyncInfo moreRecentSync = syncDetails.lastObject();

    if (moreRecentSync != null) {
      if (moreRecentSync.etag().equals(ifNoneMatchHeader())) {
        return responseForNotModified(moreRecentSync);
      }

      if (moreRecentSync.lastModified().equals(ifModifiedSinceHeader())) {
        return responseForNotModified(moreRecentSync);
      }
    }
        
    if (isHTMlFormat()) {
      BlogEntryListPage nextPage = (BlogEntryListPage)pageWithName(BlogEntryListPage.class);
      nextPage.setSyncDetails(moreRecentSync);
      return nextPage;
    } else {
      NSArray<BlogEntry> entries = BlogEntry.fetchAllBlogEntries(editingContext());
      response = (ERXRouteResults) response(entries, filter());
    }
    
    if (moreRecentSync != null) {
      response.setHeaderForKey(formatter.format(moreRecentSync.lastModified()), "Last-Modified");
      response.setHeaderForKey(moreRecentSync.etag(), "Etag");
    }
    
    return response;
  }

  public WOActionResults showByTitleAction() throws Throwable {
    return showAction();
  }

}
