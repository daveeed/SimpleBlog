// DO NOT EDIT.  Make changes to com.wowodc.model.DelegatePKHistory.java instead.
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
public abstract class _DelegatePKHistory extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "DelegatePKHistory";

  // Attribute Keys
  public static final ERXKey<String> DELEGATED_PRIMARY_KEY_VALUE = new ERXKey<String>("delegatedPrimaryKeyValue");
  // Relationship Keys
  public static final ERXKey<com.wowodc.model.SyncInfo> SYNC_INFO = new ERXKey<com.wowodc.model.SyncInfo>("syncInfo");

  // Attributes
  public static final String DELEGATED_PRIMARY_KEY_VALUE_KEY = DELEGATED_PRIMARY_KEY_VALUE.key();
  // Relationships
  public static final String SYNC_INFO_KEY = SYNC_INFO.key();

  private static Logger LOG = Logger.getLogger(_DelegatePKHistory.class);

  public com.wowodc.model.DelegatePKHistory localInstanceIn(EOEditingContext editingContext) {
    com.wowodc.model.DelegatePKHistory localInstance = (com.wowodc.model.DelegatePKHistory)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String delegatedPrimaryKeyValue() {
    return (String) storedValueForKey(_DelegatePKHistory.DELEGATED_PRIMARY_KEY_VALUE_KEY);
  }

  public void setDelegatedPrimaryKeyValue(String value) {
    if (_DelegatePKHistory.LOG.isDebugEnabled()) {
    	_DelegatePKHistory.LOG.debug( "updating delegatedPrimaryKeyValue from " + delegatedPrimaryKeyValue() + " to " + value);
    }
    takeStoredValueForKey(value, _DelegatePKHistory.DELEGATED_PRIMARY_KEY_VALUE_KEY);
  }

  public com.wowodc.model.SyncInfo syncInfo() {
    return (com.wowodc.model.SyncInfo)storedValueForKey(_DelegatePKHistory.SYNC_INFO_KEY);
  }
  
  public void setSyncInfo(com.wowodc.model.SyncInfo value) {
    takeStoredValueForKey(value, _DelegatePKHistory.SYNC_INFO_KEY);
  }

  public void setSyncInfoRelationship(com.wowodc.model.SyncInfo value) {
    if (_DelegatePKHistory.LOG.isDebugEnabled()) {
      _DelegatePKHistory.LOG.debug("updating syncInfo from " + syncInfo() + " to " + value);
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	setSyncInfo(value);
    }
    else if (value == null) {
    	com.wowodc.model.SyncInfo oldValue = syncInfo();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _DelegatePKHistory.SYNC_INFO_KEY);
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, _DelegatePKHistory.SYNC_INFO_KEY);
    }
  }
  

  public static com.wowodc.model.DelegatePKHistory createDelegatePKHistory(EOEditingContext editingContext, String delegatedPrimaryKeyValue
, com.wowodc.model.SyncInfo syncInfo) {
    com.wowodc.model.DelegatePKHistory eo = (com.wowodc.model.DelegatePKHistory) EOUtilities.createAndInsertInstance(editingContext, _DelegatePKHistory.ENTITY_NAME);    
		eo.setDelegatedPrimaryKeyValue(delegatedPrimaryKeyValue);
    eo.setSyncInfoRelationship(syncInfo);
    return eo;
  }

  public static ERXFetchSpecification<com.wowodc.model.DelegatePKHistory> fetchSpec() {
    return new ERXFetchSpecification<com.wowodc.model.DelegatePKHistory>(_DelegatePKHistory.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<com.wowodc.model.DelegatePKHistory> fetchAllDelegatePKHistories(EOEditingContext editingContext) {
    return _DelegatePKHistory.fetchAllDelegatePKHistories(editingContext, null);
  }

  public static NSArray<com.wowodc.model.DelegatePKHistory> fetchAllDelegatePKHistories(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _DelegatePKHistory.fetchDelegatePKHistories(editingContext, null, sortOrderings);
  }

  public static NSArray<com.wowodc.model.DelegatePKHistory> fetchDelegatePKHistories(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<com.wowodc.model.DelegatePKHistory> fetchSpec = new ERXFetchSpecification<com.wowodc.model.DelegatePKHistory>(_DelegatePKHistory.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<com.wowodc.model.DelegatePKHistory> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static com.wowodc.model.DelegatePKHistory fetchDelegatePKHistory(EOEditingContext editingContext, String keyName, Object value) {
    return _DelegatePKHistory.fetchDelegatePKHistory(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.wowodc.model.DelegatePKHistory fetchDelegatePKHistory(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<com.wowodc.model.DelegatePKHistory> eoObjects = _DelegatePKHistory.fetchDelegatePKHistories(editingContext, qualifier, null);
    com.wowodc.model.DelegatePKHistory eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one DelegatePKHistory that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.wowodc.model.DelegatePKHistory fetchRequiredDelegatePKHistory(EOEditingContext editingContext, String keyName, Object value) {
    return _DelegatePKHistory.fetchRequiredDelegatePKHistory(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.wowodc.model.DelegatePKHistory fetchRequiredDelegatePKHistory(EOEditingContext editingContext, EOQualifier qualifier) {
    com.wowodc.model.DelegatePKHistory eoObject = _DelegatePKHistory.fetchDelegatePKHistory(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no DelegatePKHistory that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.wowodc.model.DelegatePKHistory localInstanceIn(EOEditingContext editingContext, com.wowodc.model.DelegatePKHistory eo) {
    com.wowodc.model.DelegatePKHistory localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
