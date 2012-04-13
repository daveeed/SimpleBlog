// DO NOT EDIT.  Make changes to com.wowodc.model.SyncInfo.java instead.
package com.wowodc.model.generated;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

import er.extensions.eof.*;
import er.extensions.foundation.*;

@SuppressWarnings("all")
public abstract class _SyncInfo extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "SyncInfo";

  // Attribute Keys
  public static final ERXKey<String> DELEGATED_PRIMARY_KEY_VALUE = new ERXKey<String>("delegatedPrimaryKeyValue");
  public static final ERXKey<String> ETAG = new ERXKey<String>("etag");
  public static final ERXKey<NSTimestamp> LAST_MODIFIED = new ERXKey<NSTimestamp>("lastModified");
  public static final ERXKey<com.wowodc.model.enums.SyncInfoStatus> STATUS = new ERXKey<com.wowodc.model.enums.SyncInfoStatus>("status");
  public static final ERXKey<String> TOKEN = new ERXKey<String>("token");
  // Relationship Keys
  public static final ERXKey<com.wowodc.model.DelegatePKHistory> DELEGATE_PK_HISTORIES = new ERXKey<com.wowodc.model.DelegatePKHistory>("delegatePKHistories");

  // Attributes
  public static final String DELEGATED_PRIMARY_KEY_VALUE_KEY = DELEGATED_PRIMARY_KEY_VALUE.key();
  public static final String ETAG_KEY = ETAG.key();
  public static final String LAST_MODIFIED_KEY = LAST_MODIFIED.key();
  public static final String STATUS_KEY = STATUS.key();
  public static final String TOKEN_KEY = TOKEN.key();
  // Relationships
  public static final String DELEGATE_PK_HISTORIES_KEY = DELEGATE_PK_HISTORIES.key();

  private static Logger LOG = Logger.getLogger(_SyncInfo.class);

  public com.wowodc.model.SyncInfo localInstanceIn(EOEditingContext editingContext) {
    com.wowodc.model.SyncInfo localInstance = (com.wowodc.model.SyncInfo)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String delegatedPrimaryKeyValue() {
    return (String) storedValueForKey(_SyncInfo.DELEGATED_PRIMARY_KEY_VALUE_KEY);
  }

  public void setDelegatedPrimaryKeyValue(String value) {
    if (_SyncInfo.LOG.isDebugEnabled()) {
    	_SyncInfo.LOG.debug( "updating delegatedPrimaryKeyValue from " + delegatedPrimaryKeyValue() + " to " + value);
    }
    takeStoredValueForKey(value, _SyncInfo.DELEGATED_PRIMARY_KEY_VALUE_KEY);
  }

  public String etag() {
    return (String) storedValueForKey(_SyncInfo.ETAG_KEY);
  }

  public void setEtag(String value) {
    if (_SyncInfo.LOG.isDebugEnabled()) {
    	_SyncInfo.LOG.debug( "updating etag from " + etag() + " to " + value);
    }
    takeStoredValueForKey(value, _SyncInfo.ETAG_KEY);
  }

  public NSTimestamp lastModified() {
    return (NSTimestamp) storedValueForKey(_SyncInfo.LAST_MODIFIED_KEY);
  }

  public void setLastModified(NSTimestamp value) {
    if (_SyncInfo.LOG.isDebugEnabled()) {
    	_SyncInfo.LOG.debug( "updating lastModified from " + lastModified() + " to " + value);
    }
    takeStoredValueForKey(value, _SyncInfo.LAST_MODIFIED_KEY);
  }

  public com.wowodc.model.enums.SyncInfoStatus status() {
    return (com.wowodc.model.enums.SyncInfoStatus) storedValueForKey(_SyncInfo.STATUS_KEY);
  }

  public void setStatus(com.wowodc.model.enums.SyncInfoStatus value) {
    if (_SyncInfo.LOG.isDebugEnabled()) {
    	_SyncInfo.LOG.debug( "updating status from " + status() + " to " + value);
    }
    takeStoredValueForKey(value, _SyncInfo.STATUS_KEY);
  }

  public String token() {
    return (String) storedValueForKey(_SyncInfo.TOKEN_KEY);
  }

  public void setToken(String value) {
    if (_SyncInfo.LOG.isDebugEnabled()) {
    	_SyncInfo.LOG.debug( "updating token from " + token() + " to " + value);
    }
    takeStoredValueForKey(value, _SyncInfo.TOKEN_KEY);
  }

  public NSArray<com.wowodc.model.DelegatePKHistory> delegatePKHistories() {
    return (NSArray<com.wowodc.model.DelegatePKHistory>)storedValueForKey(_SyncInfo.DELEGATE_PK_HISTORIES_KEY);
  }

  public NSArray<com.wowodc.model.DelegatePKHistory> delegatePKHistories(EOQualifier qualifier) {
    return delegatePKHistories(qualifier, null, false);
  }

  public NSArray<com.wowodc.model.DelegatePKHistory> delegatePKHistories(EOQualifier qualifier, boolean fetch) {
    return delegatePKHistories(qualifier, null, fetch);
  }

  public NSArray<com.wowodc.model.DelegatePKHistory> delegatePKHistories(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<com.wowodc.model.DelegatePKHistory> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.wowodc.model.DelegatePKHistory.SYNC_INFO_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray<EOQualifier> qualifiers = new NSMutableArray<EOQualifier>();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = com.wowodc.model.DelegatePKHistory.fetchDelegatePKHistories(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = delegatePKHistories();
      if (qualifier != null) {
        results = (NSArray<com.wowodc.model.DelegatePKHistory>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.wowodc.model.DelegatePKHistory>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToDelegatePKHistories(com.wowodc.model.DelegatePKHistory object) {
    includeObjectIntoPropertyWithKey(object, _SyncInfo.DELEGATE_PK_HISTORIES_KEY);
  }

  public void removeFromDelegatePKHistories(com.wowodc.model.DelegatePKHistory object) {
    excludeObjectFromPropertyWithKey(object, _SyncInfo.DELEGATE_PK_HISTORIES_KEY);
  }

  public void addToDelegatePKHistoriesRelationship(com.wowodc.model.DelegatePKHistory object) {
    if (_SyncInfo.LOG.isDebugEnabled()) {
      _SyncInfo.LOG.debug("adding " + object + " to delegatePKHistories relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToDelegatePKHistories(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, _SyncInfo.DELEGATE_PK_HISTORIES_KEY);
    }
  }

  public void removeFromDelegatePKHistoriesRelationship(com.wowodc.model.DelegatePKHistory object) {
    if (_SyncInfo.LOG.isDebugEnabled()) {
      _SyncInfo.LOG.debug("removing " + object + " from delegatePKHistories relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	removeFromDelegatePKHistories(object);
    }
    else {
    	removeObjectFromBothSidesOfRelationshipWithKey(object, _SyncInfo.DELEGATE_PK_HISTORIES_KEY);
    }
  }

  public com.wowodc.model.DelegatePKHistory createDelegatePKHistoriesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName( com.wowodc.model.DelegatePKHistory.ENTITY_NAME );
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, _SyncInfo.DELEGATE_PK_HISTORIES_KEY);
    return (com.wowodc.model.DelegatePKHistory) eo;
  }

  public void deleteDelegatePKHistoriesRelationship(com.wowodc.model.DelegatePKHistory object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _SyncInfo.DELEGATE_PK_HISTORIES_KEY);
    editingContext().deleteObject(object);
  }

  public void deleteAllDelegatePKHistoriesRelationships() {
    Enumeration<com.wowodc.model.DelegatePKHistory> objects = delegatePKHistories().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteDelegatePKHistoriesRelationship(objects.nextElement());
    }
  }


  public static com.wowodc.model.SyncInfo createSyncInfo(EOEditingContext editingContext, String etag
, NSTimestamp lastModified
, com.wowodc.model.enums.SyncInfoStatus status
, String token
) {
    com.wowodc.model.SyncInfo eo = (com.wowodc.model.SyncInfo) EOUtilities.createAndInsertInstance(editingContext, _SyncInfo.ENTITY_NAME);    
		eo.setEtag(etag);
		eo.setLastModified(lastModified);
		eo.setStatus(status);
		eo.setToken(token);
    return eo;
  }

  public static ERXFetchSpecification<com.wowodc.model.SyncInfo> fetchSpec() {
    return new ERXFetchSpecification<com.wowodc.model.SyncInfo>(_SyncInfo.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<com.wowodc.model.SyncInfo> fetchAllSyncInfos(EOEditingContext editingContext) {
    return _SyncInfo.fetchAllSyncInfos(editingContext, null);
  }

  public static NSArray<com.wowodc.model.SyncInfo> fetchAllSyncInfos(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _SyncInfo.fetchSyncInfos(editingContext, null, sortOrderings);
  }

  public static NSArray<com.wowodc.model.SyncInfo> fetchSyncInfos(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<com.wowodc.model.SyncInfo> fetchSpec = new ERXFetchSpecification<com.wowodc.model.SyncInfo>(_SyncInfo.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<com.wowodc.model.SyncInfo> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static com.wowodc.model.SyncInfo fetchSyncInfo(EOEditingContext editingContext, String keyName, Object value) {
    return _SyncInfo.fetchSyncInfo(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.wowodc.model.SyncInfo fetchSyncInfo(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<com.wowodc.model.SyncInfo> eoObjects = _SyncInfo.fetchSyncInfos(editingContext, qualifier, null);
    com.wowodc.model.SyncInfo eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one SyncInfo that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.wowodc.model.SyncInfo fetchRequiredSyncInfo(EOEditingContext editingContext, String keyName, Object value) {
    return _SyncInfo.fetchRequiredSyncInfo(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.wowodc.model.SyncInfo fetchRequiredSyncInfo(EOEditingContext editingContext, EOQualifier qualifier) {
    com.wowodc.model.SyncInfo eoObject = _SyncInfo.fetchSyncInfo(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no SyncInfo that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.wowodc.model.SyncInfo localInstanceIn(EOEditingContext editingContext, com.wowodc.model.SyncInfo eo) {
    com.wowodc.model.SyncInfo localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
