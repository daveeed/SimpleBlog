// DO NOT EDIT.  Make changes to com.wowodc.model.Person.java instead.
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
public abstract class _Person extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "Person";

  // Attribute Keys
  public static final ERXKey<String> EMAIL = new ERXKey<String>("email");
  public static final ERXKey<String> FIRST_NAME = new ERXKey<String>("firstName");
  public static final ERXKey<String> LAST_NAME = new ERXKey<String>("lastName");
  public static final ERXKey<String> LOGIN = new ERXKey<String>("login");
  public static final ERXKey<String> PASSWORD = new ERXKey<String>("password");
  // Relationship Keys
  public static final ERXKey<com.wowodc.model.BlogEntry> BLOG_ENTRIES = new ERXKey<com.wowodc.model.BlogEntry>("blogEntries");
  public static final ERXKey<com.wowodc.model.Role> ROLES = new ERXKey<com.wowodc.model.Role>("roles");

  // Attributes
  public static final String EMAIL_KEY = EMAIL.key();
  public static final String FIRST_NAME_KEY = FIRST_NAME.key();
  public static final String LAST_NAME_KEY = LAST_NAME.key();
  public static final String LOGIN_KEY = LOGIN.key();
  public static final String PASSWORD_KEY = PASSWORD.key();
  // Relationships
  public static final String BLOG_ENTRIES_KEY = BLOG_ENTRIES.key();
  public static final String ROLES_KEY = ROLES.key();

  private static Logger LOG = Logger.getLogger(_Person.class);

  public com.wowodc.model.Person localInstanceIn(EOEditingContext editingContext) {
    com.wowodc.model.Person localInstance = (com.wowodc.model.Person)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String email() {
    return (String) storedValueForKey(_Person.EMAIL_KEY);
  }

  public void setEmail(String value) {
    if (_Person.LOG.isDebugEnabled()) {
    	_Person.LOG.debug( "updating email from " + email() + " to " + value);
    }
    takeStoredValueForKey(value, _Person.EMAIL_KEY);
  }

  public String firstName() {
    return (String) storedValueForKey(_Person.FIRST_NAME_KEY);
  }

  public void setFirstName(String value) {
    if (_Person.LOG.isDebugEnabled()) {
    	_Person.LOG.debug( "updating firstName from " + firstName() + " to " + value);
    }
    takeStoredValueForKey(value, _Person.FIRST_NAME_KEY);
  }

  public String lastName() {
    return (String) storedValueForKey(_Person.LAST_NAME_KEY);
  }

  public void setLastName(String value) {
    if (_Person.LOG.isDebugEnabled()) {
    	_Person.LOG.debug( "updating lastName from " + lastName() + " to " + value);
    }
    takeStoredValueForKey(value, _Person.LAST_NAME_KEY);
  }

  public String login() {
    return (String) storedValueForKey(_Person.LOGIN_KEY);
  }

  public void setLogin(String value) {
    if (_Person.LOG.isDebugEnabled()) {
    	_Person.LOG.debug( "updating login from " + login() + " to " + value);
    }
    takeStoredValueForKey(value, _Person.LOGIN_KEY);
  }

  public String password() {
    return (String) storedValueForKey(_Person.PASSWORD_KEY);
  }

  public void setPassword(String value) {
    if (_Person.LOG.isDebugEnabled()) {
    	_Person.LOG.debug( "updating password from " + password() + " to " + value);
    }
    takeStoredValueForKey(value, _Person.PASSWORD_KEY);
  }

  public NSArray<com.wowodc.model.BlogEntry> blogEntries() {
    return (NSArray<com.wowodc.model.BlogEntry>)storedValueForKey(_Person.BLOG_ENTRIES_KEY);
  }

  public NSArray<com.wowodc.model.BlogEntry> blogEntries(EOQualifier qualifier) {
    return blogEntries(qualifier, null, false);
  }

  public NSArray<com.wowodc.model.BlogEntry> blogEntries(EOQualifier qualifier, boolean fetch) {
    return blogEntries(qualifier, null, fetch);
  }

  public NSArray<com.wowodc.model.BlogEntry> blogEntries(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<com.wowodc.model.BlogEntry> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.wowodc.model.BlogEntry.PERSON_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray<EOQualifier> qualifiers = new NSMutableArray<EOQualifier>();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = com.wowodc.model.BlogEntry.fetchBlogEntries(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = blogEntries();
      if (qualifier != null) {
        results = (NSArray<com.wowodc.model.BlogEntry>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.wowodc.model.BlogEntry>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToBlogEntries(com.wowodc.model.BlogEntry object) {
    includeObjectIntoPropertyWithKey(object, _Person.BLOG_ENTRIES_KEY);
  }

  public void removeFromBlogEntries(com.wowodc.model.BlogEntry object) {
    excludeObjectFromPropertyWithKey(object, _Person.BLOG_ENTRIES_KEY);
  }

  public void addToBlogEntriesRelationship(com.wowodc.model.BlogEntry object) {
    if (_Person.LOG.isDebugEnabled()) {
      _Person.LOG.debug("adding " + object + " to blogEntries relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToBlogEntries(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, _Person.BLOG_ENTRIES_KEY);
    }
  }

  public void removeFromBlogEntriesRelationship(com.wowodc.model.BlogEntry object) {
    if (_Person.LOG.isDebugEnabled()) {
      _Person.LOG.debug("removing " + object + " from blogEntries relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	removeFromBlogEntries(object);
    }
    else {
    	removeObjectFromBothSidesOfRelationshipWithKey(object, _Person.BLOG_ENTRIES_KEY);
    }
  }

  public com.wowodc.model.BlogEntry createBlogEntriesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName( com.wowodc.model.BlogEntry.ENTITY_NAME );
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, _Person.BLOG_ENTRIES_KEY);
    return (com.wowodc.model.BlogEntry) eo;
  }

  public void deleteBlogEntriesRelationship(com.wowodc.model.BlogEntry object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _Person.BLOG_ENTRIES_KEY);
    editingContext().deleteObject(object);
  }

  public void deleteAllBlogEntriesRelationships() {
    Enumeration<com.wowodc.model.BlogEntry> objects = blogEntries().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteBlogEntriesRelationship(objects.nextElement());
    }
  }

  public NSArray<com.wowodc.model.Role> roles() {
    return (NSArray<com.wowodc.model.Role>)storedValueForKey(_Person.ROLES_KEY);
  }

  public NSArray<com.wowodc.model.Role> roles(EOQualifier qualifier) {
    return roles(qualifier, null);
  }

  public NSArray<com.wowodc.model.Role> roles(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<com.wowodc.model.Role> results;
      results = roles();
      if (qualifier != null) {
        results = (NSArray<com.wowodc.model.Role>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.wowodc.model.Role>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToRoles(com.wowodc.model.Role object) {
    includeObjectIntoPropertyWithKey(object, _Person.ROLES_KEY);
  }

  public void removeFromRoles(com.wowodc.model.Role object) {
    excludeObjectFromPropertyWithKey(object, _Person.ROLES_KEY);
  }

  public void addToRolesRelationship(com.wowodc.model.Role object) {
    if (_Person.LOG.isDebugEnabled()) {
      _Person.LOG.debug("adding " + object + " to roles relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToRoles(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, _Person.ROLES_KEY);
    }
  }

  public void removeFromRolesRelationship(com.wowodc.model.Role object) {
    if (_Person.LOG.isDebugEnabled()) {
      _Person.LOG.debug("removing " + object + " from roles relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	removeFromRoles(object);
    }
    else {
    	removeObjectFromBothSidesOfRelationshipWithKey(object, _Person.ROLES_KEY);
    }
  }

  public com.wowodc.model.Role createRolesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName( com.wowodc.model.Role.ENTITY_NAME );
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, _Person.ROLES_KEY);
    return (com.wowodc.model.Role) eo;
  }

  public void deleteRolesRelationship(com.wowodc.model.Role object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _Person.ROLES_KEY);
    editingContext().deleteObject(object);
  }

  public void deleteAllRolesRelationships() {
    Enumeration<com.wowodc.model.Role> objects = roles().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteRolesRelationship(objects.nextElement());
    }
  }


  public static com.wowodc.model.Person createPerson(EOEditingContext editingContext) {
    com.wowodc.model.Person eo = (com.wowodc.model.Person) EOUtilities.createAndInsertInstance(editingContext, _Person.ENTITY_NAME);    
    return eo;
  }

  public static ERXFetchSpecification<com.wowodc.model.Person> fetchSpec() {
    return new ERXFetchSpecification<com.wowodc.model.Person>(_Person.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<com.wowodc.model.Person> fetchAllPersons(EOEditingContext editingContext) {
    return _Person.fetchAllPersons(editingContext, null);
  }

  public static NSArray<com.wowodc.model.Person> fetchAllPersons(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Person.fetchPersons(editingContext, null, sortOrderings);
  }

  public static NSArray<com.wowodc.model.Person> fetchPersons(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<com.wowodc.model.Person> fetchSpec = new ERXFetchSpecification<com.wowodc.model.Person>(_Person.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<com.wowodc.model.Person> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static com.wowodc.model.Person fetchPerson(EOEditingContext editingContext, String keyName, Object value) {
    return _Person.fetchPerson(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.wowodc.model.Person fetchPerson(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<com.wowodc.model.Person> eoObjects = _Person.fetchPersons(editingContext, qualifier, null);
    com.wowodc.model.Person eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Person that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.wowodc.model.Person fetchRequiredPerson(EOEditingContext editingContext, String keyName, Object value) {
    return _Person.fetchRequiredPerson(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.wowodc.model.Person fetchRequiredPerson(EOEditingContext editingContext, EOQualifier qualifier) {
    com.wowodc.model.Person eoObject = _Person.fetchPerson(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Person that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.wowodc.model.Person localInstanceIn(EOEditingContext editingContext, com.wowodc.model.Person eo) {
    com.wowodc.model.Person localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
