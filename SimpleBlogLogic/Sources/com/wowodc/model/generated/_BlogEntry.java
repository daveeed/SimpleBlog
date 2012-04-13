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
  public static final ERXKey<NSTimestamp> LAST_MODIFED = new ERXKey<NSTimestamp>("lastModifed");
  public static final ERXKey<NSTimestamp> TIMESTAMP_CREATION = new ERXKey<NSTimestamp>("timestampCreation");
  public static final ERXKey<String> TITLE = new ERXKey<String>("title");
  public static final ERXKey<String> UNIQUE_TITLE = new ERXKey<String>("uniqueTitle");
  // Relationship Keys
  public static final ERXKey<com.wowodc.model.BlogComment> BLOG_COMMENTS = new ERXKey<com.wowodc.model.BlogComment>("blogComments");
  public static final ERXKey<com.wowodc.model.BlogCategory> CATEGORIES = new ERXKey<com.wowodc.model.BlogCategory>("categories");
  public static final ERXKey<com.wowodc.model.Person> PERSON = new ERXKey<com.wowodc.model.Person>("person");

  // Attributes
  public static final String BODY_KEY = BODY.key();
  public static final String LAST_MODIFED_KEY = LAST_MODIFED.key();
  public static final String TIMESTAMP_CREATION_KEY = TIMESTAMP_CREATION.key();
  public static final String TITLE_KEY = TITLE.key();
  public static final String UNIQUE_TITLE_KEY = UNIQUE_TITLE.key();
  // Relationships
  public static final String BLOG_COMMENTS_KEY = BLOG_COMMENTS.key();
  public static final String CATEGORIES_KEY = CATEGORIES.key();
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

  public NSTimestamp lastModifed() {
    return (NSTimestamp) storedValueForKey(_BlogEntry.LAST_MODIFED_KEY);
  }

  public void setLastModifed(NSTimestamp value) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
    	_BlogEntry.LOG.debug( "updating lastModifed from " + lastModifed() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogEntry.LAST_MODIFED_KEY);
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

  public String uniqueTitle() {
    return (String) storedValueForKey(_BlogEntry.UNIQUE_TITLE_KEY);
  }

  public void setUniqueTitle(String value) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
    	_BlogEntry.LOG.debug( "updating uniqueTitle from " + uniqueTitle() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogEntry.UNIQUE_TITLE_KEY);
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
  
  public NSArray<com.wowodc.model.BlogComment> blogComments() {
    return (NSArray<com.wowodc.model.BlogComment>)storedValueForKey(_BlogEntry.BLOG_COMMENTS_KEY);
  }

  public NSArray<com.wowodc.model.BlogComment> blogComments(EOQualifier qualifier) {
    return blogComments(qualifier, null, false);
  }

  public NSArray<com.wowodc.model.BlogComment> blogComments(EOQualifier qualifier, boolean fetch) {
    return blogComments(qualifier, null, fetch);
  }

  public NSArray<com.wowodc.model.BlogComment> blogComments(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<com.wowodc.model.BlogComment> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.wowodc.model.BlogComment.BLOG_ENTRY_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray<EOQualifier> qualifiers = new NSMutableArray<EOQualifier>();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = com.wowodc.model.BlogComment.fetchBlogComments(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = blogComments();
      if (qualifier != null) {
        results = (NSArray<com.wowodc.model.BlogComment>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.wowodc.model.BlogComment>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToBlogComments(com.wowodc.model.BlogComment object) {
    includeObjectIntoPropertyWithKey(object, _BlogEntry.BLOG_COMMENTS_KEY);
  }

  public void removeFromBlogComments(com.wowodc.model.BlogComment object) {
    excludeObjectFromPropertyWithKey(object, _BlogEntry.BLOG_COMMENTS_KEY);
  }

  public void addToBlogCommentsRelationship(com.wowodc.model.BlogComment object) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
      _BlogEntry.LOG.debug("adding " + object + " to blogComments relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToBlogComments(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, _BlogEntry.BLOG_COMMENTS_KEY);
    }
  }

  public void removeFromBlogCommentsRelationship(com.wowodc.model.BlogComment object) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
      _BlogEntry.LOG.debug("removing " + object + " from blogComments relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	removeFromBlogComments(object);
    }
    else {
    	removeObjectFromBothSidesOfRelationshipWithKey(object, _BlogEntry.BLOG_COMMENTS_KEY);
    }
  }

  public com.wowodc.model.BlogComment createBlogCommentsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName( com.wowodc.model.BlogComment.ENTITY_NAME );
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, _BlogEntry.BLOG_COMMENTS_KEY);
    return (com.wowodc.model.BlogComment) eo;
  }

  public void deleteBlogCommentsRelationship(com.wowodc.model.BlogComment object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _BlogEntry.BLOG_COMMENTS_KEY);
    editingContext().deleteObject(object);
  }

  public void deleteAllBlogCommentsRelationships() {
    Enumeration<com.wowodc.model.BlogComment> objects = blogComments().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteBlogCommentsRelationship(objects.nextElement());
    }
  }

  public NSArray<com.wowodc.model.BlogCategory> categories() {
    return (NSArray<com.wowodc.model.BlogCategory>)storedValueForKey(_BlogEntry.CATEGORIES_KEY);
  }

  public NSArray<com.wowodc.model.BlogCategory> categories(EOQualifier qualifier) {
    return categories(qualifier, null);
  }

  public NSArray<com.wowodc.model.BlogCategory> categories(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<com.wowodc.model.BlogCategory> results;
      results = categories();
      if (qualifier != null) {
        results = (NSArray<com.wowodc.model.BlogCategory>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.wowodc.model.BlogCategory>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToCategories(com.wowodc.model.BlogCategory object) {
    includeObjectIntoPropertyWithKey(object, _BlogEntry.CATEGORIES_KEY);
  }

  public void removeFromCategories(com.wowodc.model.BlogCategory object) {
    excludeObjectFromPropertyWithKey(object, _BlogEntry.CATEGORIES_KEY);
  }

  public void addToCategoriesRelationship(com.wowodc.model.BlogCategory object) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
      _BlogEntry.LOG.debug("adding " + object + " to categories relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToCategories(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, _BlogEntry.CATEGORIES_KEY);
    }
  }

  public void removeFromCategoriesRelationship(com.wowodc.model.BlogCategory object) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
      _BlogEntry.LOG.debug("removing " + object + " from categories relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	removeFromCategories(object);
    }
    else {
    	removeObjectFromBothSidesOfRelationshipWithKey(object, _BlogEntry.CATEGORIES_KEY);
    }
  }

  public com.wowodc.model.BlogCategory createCategoriesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName( com.wowodc.model.BlogCategory.ENTITY_NAME );
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, _BlogEntry.CATEGORIES_KEY);
    return (com.wowodc.model.BlogCategory) eo;
  }

  public void deleteCategoriesRelationship(com.wowodc.model.BlogCategory object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _BlogEntry.CATEGORIES_KEY);
    editingContext().deleteObject(object);
  }

  public void deleteAllCategoriesRelationships() {
    Enumeration<com.wowodc.model.BlogCategory> objects = categories().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteCategoriesRelationship(objects.nextElement());
    }
  }


  public static com.wowodc.model.BlogEntry createBlogEntry(EOEditingContext editingContext, String body
, NSTimestamp lastModifed
, NSTimestamp timestampCreation
, String title
, String uniqueTitle
, com.wowodc.model.Person person) {
    com.wowodc.model.BlogEntry eo = (com.wowodc.model.BlogEntry) EOUtilities.createAndInsertInstance(editingContext, _BlogEntry.ENTITY_NAME);    
		eo.setBody(body);
		eo.setLastModifed(lastModifed);
		eo.setTimestampCreation(timestampCreation);
		eo.setTitle(title);
		eo.setUniqueTitle(uniqueTitle);
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
