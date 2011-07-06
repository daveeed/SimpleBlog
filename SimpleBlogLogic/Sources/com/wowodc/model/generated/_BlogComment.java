// DO NOT EDIT.  Make changes to com.wowodc.model.BlogComment.java instead.
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
public abstract class _BlogComment extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "BlogComment";

  // Attribute Keys
  public static final ERXKey<String> COMMENT_TEXT = new ERXKey<String>("commentText");
  public static final ERXKey<NSTimestamp> LAST_MODIFED = new ERXKey<NSTimestamp>("lastModifed");
  public static final ERXKey<Integer> RATING = new ERXKey<Integer>("rating");
  public static final ERXKey<NSTimestamp> TIMESTAMP_CREATION = new ERXKey<NSTimestamp>("timestampCreation");
  // Relationship Keys
  public static final ERXKey<com.wowodc.model.BlogEntry> BLOG_ENTRY = new ERXKey<com.wowodc.model.BlogEntry>("blogEntry");
  public static final ERXKey<com.wowodc.model.Person> PERSON = new ERXKey<com.wowodc.model.Person>("person");

  // Attributes
  public static final String COMMENT_TEXT_KEY = COMMENT_TEXT.key();
  public static final String LAST_MODIFED_KEY = LAST_MODIFED.key();
  public static final String RATING_KEY = RATING.key();
  public static final String TIMESTAMP_CREATION_KEY = TIMESTAMP_CREATION.key();
  // Relationships
  public static final String BLOG_ENTRY_KEY = BLOG_ENTRY.key();
  public static final String PERSON_KEY = PERSON.key();

  private static Logger LOG = Logger.getLogger(_BlogComment.class);

  public com.wowodc.model.BlogComment localInstanceIn(EOEditingContext editingContext) {
    com.wowodc.model.BlogComment localInstance = (com.wowodc.model.BlogComment)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String commentText() {
    return (String) storedValueForKey(_BlogComment.COMMENT_TEXT_KEY);
  }

  public void setCommentText(String value) {
    if (_BlogComment.LOG.isDebugEnabled()) {
    	_BlogComment.LOG.debug( "updating commentText from " + commentText() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogComment.COMMENT_TEXT_KEY);
  }

  public NSTimestamp lastModifed() {
    return (NSTimestamp) storedValueForKey(_BlogComment.LAST_MODIFED_KEY);
  }

  public void setLastModifed(NSTimestamp value) {
    if (_BlogComment.LOG.isDebugEnabled()) {
    	_BlogComment.LOG.debug( "updating lastModifed from " + lastModifed() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogComment.LAST_MODIFED_KEY);
  }

  public Integer rating() {
    return (Integer) storedValueForKey(_BlogComment.RATING_KEY);
  }

  public void setRating(Integer value) {
    if (_BlogComment.LOG.isDebugEnabled()) {
    	_BlogComment.LOG.debug( "updating rating from " + rating() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogComment.RATING_KEY);
  }

  public NSTimestamp timestampCreation() {
    return (NSTimestamp) storedValueForKey(_BlogComment.TIMESTAMP_CREATION_KEY);
  }

  public void setTimestampCreation(NSTimestamp value) {
    if (_BlogComment.LOG.isDebugEnabled()) {
    	_BlogComment.LOG.debug( "updating timestampCreation from " + timestampCreation() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogComment.TIMESTAMP_CREATION_KEY);
  }

  public com.wowodc.model.BlogEntry blogEntry() {
    return (com.wowodc.model.BlogEntry)storedValueForKey(_BlogComment.BLOG_ENTRY_KEY);
  }
  
  public void setBlogEntry(com.wowodc.model.BlogEntry value) {
    takeStoredValueForKey(value, _BlogComment.BLOG_ENTRY_KEY);
  }

  public void setBlogEntryRelationship(com.wowodc.model.BlogEntry value) {
    if (_BlogComment.LOG.isDebugEnabled()) {
      _BlogComment.LOG.debug("updating blogEntry from " + blogEntry() + " to " + value);
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	setBlogEntry(value);
    }
    else if (value == null) {
    	com.wowodc.model.BlogEntry oldValue = blogEntry();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _BlogComment.BLOG_ENTRY_KEY);
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, _BlogComment.BLOG_ENTRY_KEY);
    }
  }
  
  public com.wowodc.model.Person person() {
    return (com.wowodc.model.Person)storedValueForKey(_BlogComment.PERSON_KEY);
  }
  
  public void setPerson(com.wowodc.model.Person value) {
    takeStoredValueForKey(value, _BlogComment.PERSON_KEY);
  }

  public void setPersonRelationship(com.wowodc.model.Person value) {
    if (_BlogComment.LOG.isDebugEnabled()) {
      _BlogComment.LOG.debug("updating person from " + person() + " to " + value);
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	setPerson(value);
    }
    else if (value == null) {
    	com.wowodc.model.Person oldValue = person();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _BlogComment.PERSON_KEY);
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, _BlogComment.PERSON_KEY);
    }
  }
  

  public static com.wowodc.model.BlogComment createBlogComment(EOEditingContext editingContext, com.wowodc.model.BlogEntry blogEntry, com.wowodc.model.Person person) {
    com.wowodc.model.BlogComment eo = (com.wowodc.model.BlogComment) EOUtilities.createAndInsertInstance(editingContext, _BlogComment.ENTITY_NAME);    
    eo.setBlogEntryRelationship(blogEntry);
    eo.setPersonRelationship(person);
    return eo;
  }

  public static ERXFetchSpecification<com.wowodc.model.BlogComment> fetchSpec() {
    return new ERXFetchSpecification<com.wowodc.model.BlogComment>(_BlogComment.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<com.wowodc.model.BlogComment> fetchAllBlogComments(EOEditingContext editingContext) {
    return _BlogComment.fetchAllBlogComments(editingContext, null);
  }

  public static NSArray<com.wowodc.model.BlogComment> fetchAllBlogComments(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _BlogComment.fetchBlogComments(editingContext, null, sortOrderings);
  }

  public static NSArray<com.wowodc.model.BlogComment> fetchBlogComments(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<com.wowodc.model.BlogComment> fetchSpec = new ERXFetchSpecification<com.wowodc.model.BlogComment>(_BlogComment.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<com.wowodc.model.BlogComment> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static com.wowodc.model.BlogComment fetchBlogComment(EOEditingContext editingContext, String keyName, Object value) {
    return _BlogComment.fetchBlogComment(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.wowodc.model.BlogComment fetchBlogComment(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<com.wowodc.model.BlogComment> eoObjects = _BlogComment.fetchBlogComments(editingContext, qualifier, null);
    com.wowodc.model.BlogComment eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one BlogComment that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.wowodc.model.BlogComment fetchRequiredBlogComment(EOEditingContext editingContext, String keyName, Object value) {
    return _BlogComment.fetchRequiredBlogComment(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.wowodc.model.BlogComment fetchRequiredBlogComment(EOEditingContext editingContext, EOQualifier qualifier) {
    com.wowodc.model.BlogComment eoObject = _BlogComment.fetchBlogComment(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no BlogComment that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.wowodc.model.BlogComment localInstanceIn(EOEditingContext editingContext, com.wowodc.model.BlogComment eo) {
    com.wowodc.model.BlogComment localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
