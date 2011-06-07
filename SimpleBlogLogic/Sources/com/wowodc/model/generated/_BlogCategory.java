// DO NOT EDIT.  Make changes to com.wowodc.model.BlogCategory.java instead.
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
public abstract class _BlogCategory extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "BlogCategory";

  // Attribute Keys
  public static final ERXKey<String> NAME = new ERXKey<String>("name");
  public static final ERXKey<String> SHORT_NAME = new ERXKey<String>("shortName");
  // Relationship Keys
  public static final ERXKey<com.wowodc.model.BlogEntry> ENTRIES = new ERXKey<com.wowodc.model.BlogEntry>("entries");

  // Attributes
  public static final String NAME_KEY = NAME.key();
  public static final String SHORT_NAME_KEY = SHORT_NAME.key();
  // Relationships
  public static final String ENTRIES_KEY = ENTRIES.key();

  private static Logger LOG = Logger.getLogger(_BlogCategory.class);

  public com.wowodc.model.BlogCategory localInstanceIn(EOEditingContext editingContext) {
    com.wowodc.model.BlogCategory localInstance = (com.wowodc.model.BlogCategory)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String name() {
    return (String) storedValueForKey(_BlogCategory.NAME_KEY);
  }

  public void setName(String value) {
    if (_BlogCategory.LOG.isDebugEnabled()) {
    	_BlogCategory.LOG.debug( "updating name from " + name() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogCategory.NAME_KEY);
  }

  public String shortName() {
    return (String) storedValueForKey(_BlogCategory.SHORT_NAME_KEY);
  }

  public void setShortName(String value) {
    if (_BlogCategory.LOG.isDebugEnabled()) {
    	_BlogCategory.LOG.debug( "updating shortName from " + shortName() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogCategory.SHORT_NAME_KEY);
  }

  public NSArray<com.wowodc.model.BlogEntry> entries() {
    return (NSArray<com.wowodc.model.BlogEntry>)storedValueForKey(_BlogCategory.ENTRIES_KEY);
  }

  public NSArray<com.wowodc.model.BlogEntry> entries(EOQualifier qualifier) {
    return entries(qualifier, null);
  }

  public NSArray<com.wowodc.model.BlogEntry> entries(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<com.wowodc.model.BlogEntry> results;
      results = entries();
      if (qualifier != null) {
        results = (NSArray<com.wowodc.model.BlogEntry>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.wowodc.model.BlogEntry>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToEntries(com.wowodc.model.BlogEntry object) {
    includeObjectIntoPropertyWithKey(object, _BlogCategory.ENTRIES_KEY);
  }

  public void removeFromEntries(com.wowodc.model.BlogEntry object) {
    excludeObjectFromPropertyWithKey(object, _BlogCategory.ENTRIES_KEY);
  }

  public void addToEntriesRelationship(com.wowodc.model.BlogEntry object) {
    if (_BlogCategory.LOG.isDebugEnabled()) {
      _BlogCategory.LOG.debug("adding " + object + " to entries relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToEntries(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, _BlogCategory.ENTRIES_KEY);
    }
  }

  public void removeFromEntriesRelationship(com.wowodc.model.BlogEntry object) {
    if (_BlogCategory.LOG.isDebugEnabled()) {
      _BlogCategory.LOG.debug("removing " + object + " from entries relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	removeFromEntries(object);
    }
    else {
    	removeObjectFromBothSidesOfRelationshipWithKey(object, _BlogCategory.ENTRIES_KEY);
    }
  }

  public com.wowodc.model.BlogEntry createEntriesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName( com.wowodc.model.BlogEntry.ENTITY_NAME );
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, _BlogCategory.ENTRIES_KEY);
    return (com.wowodc.model.BlogEntry) eo;
  }

  public void deleteEntriesRelationship(com.wowodc.model.BlogEntry object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _BlogCategory.ENTRIES_KEY);
    editingContext().deleteObject(object);
  }

  public void deleteAllEntriesRelationships() {
    Enumeration<com.wowodc.model.BlogEntry> objects = entries().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteEntriesRelationship(objects.nextElement());
    }
  }


  public static com.wowodc.model.BlogCategory createBlogCategory(EOEditingContext editingContext, String name
, String shortName
) {
    com.wowodc.model.BlogCategory eo = (com.wowodc.model.BlogCategory) EOUtilities.createAndInsertInstance(editingContext, _BlogCategory.ENTITY_NAME);    
		eo.setName(name);
		eo.setShortName(shortName);
    return eo;
  }

  public static ERXFetchSpecification<com.wowodc.model.BlogCategory> fetchSpec() {
    return new ERXFetchSpecification<com.wowodc.model.BlogCategory>(_BlogCategory.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<com.wowodc.model.BlogCategory> fetchAllBlogCategories(EOEditingContext editingContext) {
    return _BlogCategory.fetchAllBlogCategories(editingContext, null);
  }

  public static NSArray<com.wowodc.model.BlogCategory> fetchAllBlogCategories(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _BlogCategory.fetchBlogCategories(editingContext, null, sortOrderings);
  }

  public static NSArray<com.wowodc.model.BlogCategory> fetchBlogCategories(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<com.wowodc.model.BlogCategory> fetchSpec = new ERXFetchSpecification<com.wowodc.model.BlogCategory>(_BlogCategory.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<com.wowodc.model.BlogCategory> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static com.wowodc.model.BlogCategory fetchBlogCategory(EOEditingContext editingContext, String keyName, Object value) {
    return _BlogCategory.fetchBlogCategory(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.wowodc.model.BlogCategory fetchBlogCategory(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<com.wowodc.model.BlogCategory> eoObjects = _BlogCategory.fetchBlogCategories(editingContext, qualifier, null);
    com.wowodc.model.BlogCategory eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one BlogCategory that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.wowodc.model.BlogCategory fetchRequiredBlogCategory(EOEditingContext editingContext, String keyName, Object value) {
    return _BlogCategory.fetchRequiredBlogCategory(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.wowodc.model.BlogCategory fetchRequiredBlogCategory(EOEditingContext editingContext, EOQualifier qualifier) {
    com.wowodc.model.BlogCategory eoObject = _BlogCategory.fetchBlogCategory(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no BlogCategory that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.wowodc.model.BlogCategory localInstanceIn(EOEditingContext editingContext, com.wowodc.model.BlogCategory eo) {
    com.wowodc.model.BlogCategory localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
