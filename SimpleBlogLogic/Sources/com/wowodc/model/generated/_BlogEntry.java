// DO NOT EDIT.  Make changes to com.wowodc.model.BlogEntry.java instead.
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
public abstract class _BlogEntry extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "BlogEntry";

  // Attribute Keys
  public static final ERXKey<String> BODY = new ERXKey<String>("body");
  public static final ERXKey<NSTimestamp> TIMESTAMP_CREATION = new ERXKey<NSTimestamp>("timestampCreation");
  public static final ERXKey<String> TITLE = new ERXKey<String>("title");
  // Relationship Keys
  public static final ERXKey<com.wowodc.model.Person> PERSON = new ERXKey<com.wowodc.model.Person>("person");

  // Attributes
  public static final String BODY_KEY = BODY.key();
  public static final String TIMESTAMP_CREATION_KEY = TIMESTAMP_CREATION.key();
  public static final String TITLE_KEY = TITLE.key();
  // Relationships
  public static final String PERSON_KEY = PERSON.key();

  private static Logger LOG = Logger.getLogger(_BlogEntry.class);

  public com.wowodc.model.BlogEntry localInstanceIn(EOEditingContext editingContext) {
    com.wowodc.model.BlogEntry localInstance = (com.wowodc.model.BlogEntry)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String body() {
    return (String) storedValueForKey(_BlogEntry.BODY_KEY);
  }

  public void setBody(String value) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
    	_BlogEntry.LOG.debug( "updating body from " + body() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogEntry.BODY_KEY);
  }

  public NSTimestamp timestampCreation() {
    return (NSTimestamp) storedValueForKey(_BlogEntry.TIMESTAMP_CREATION_KEY);
  }

  public void setTimestampCreation(NSTimestamp value) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
    	_BlogEntry.LOG.debug( "updating timestampCreation from " + timestampCreation() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogEntry.TIMESTAMP_CREATION_KEY);
  }

  public String title() {
    return (String) storedValueForKey(_BlogEntry.TITLE_KEY);
  }

  public void setTitle(String value) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
    	_BlogEntry.LOG.debug( "updating title from " + title() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogEntry.TITLE_KEY);
  }

  public com.wowodc.model.Person person() {
    return (com.wowodc.model.Person)storedValueForKey(_BlogEntry.PERSON_KEY);
  }
  
  public void setPerson(com.wowodc.model.Person value) {
    takeStoredValueForKey(value, _BlogEntry.PERSON_KEY);
  }

  public void setPersonRelationship(com.wowodc.model.Person value) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
      _BlogEntry.LOG.debug("updating person from " + person() + " to " + value);
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	setPerson(value);
    }
    else if (value == null) {
    	com.wowodc.model.Person oldValue = person();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _BlogEntry.PERSON_KEY);
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, _BlogEntry.PERSON_KEY);
    }
  }
  

  public static com.wowodc.model.BlogEntry createBlogEntry(EOEditingContext editingContext, String body
, NSTimestamp timestampCreation
, String title
, com.wowodc.model.Person person) {
    com.wowodc.model.BlogEntry eo = (com.wowodc.model.BlogEntry) EOUtilities.createAndInsertInstance(editingContext, _BlogEntry.ENTITY_NAME);    
		eo.setBody(body);
		eo.setTimestampCreation(timestampCreation);
		eo.setTitle(title);
    eo.setPersonRelationship(person);
    return eo;
  }

  public static ERXFetchSpecification<com.wowodc.model.BlogEntry> fetchSpec() {
    return new ERXFetchSpecification<com.wowodc.model.BlogEntry>(_BlogEntry.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<com.wowodc.model.BlogEntry> fetchAllBlogEntries(EOEditingContext editingContext) {
    return _BlogEntry.fetchAllBlogEntries(editingContext, null);
  }

  public static NSArray<com.wowodc.model.BlogEntry> fetchAllBlogEntries(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _BlogEntry.fetchBlogEntries(editingContext, null, sortOrderings);
  }

  public static NSArray<com.wowodc.model.BlogEntry> fetchBlogEntries(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<com.wowodc.model.BlogEntry> fetchSpec = new ERXFetchSpecification<com.wowodc.model.BlogEntry>(_BlogEntry.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<com.wowodc.model.BlogEntry> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static com.wowodc.model.BlogEntry fetchBlogEntry(EOEditingContext editingContext, String keyName, Object value) {
    return _BlogEntry.fetchBlogEntry(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.wowodc.model.BlogEntry fetchBlogEntry(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<com.wowodc.model.BlogEntry> eoObjects = _BlogEntry.fetchBlogEntries(editingContext, qualifier, null);
    com.wowodc.model.BlogEntry eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one BlogEntry that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.wowodc.model.BlogEntry fetchRequiredBlogEntry(EOEditingContext editingContext, String keyName, Object value) {
    return _BlogEntry.fetchRequiredBlogEntry(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.wowodc.model.BlogEntry fetchRequiredBlogEntry(EOEditingContext editingContext, EOQualifier qualifier) {
    com.wowodc.model.BlogEntry eoObject = _BlogEntry.fetchBlogEntry(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no BlogEntry that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.wowodc.model.BlogEntry localInstanceIn(EOEditingContext editingContext, com.wowodc.model.BlogEntry eo) {
    com.wowodc.model.BlogEntry localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
