// DO NOT EDIT.  Make changes to com.wowodc.model.Role.java instead.
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
public abstract class _Role extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "Role";

  // Attribute Keys
  public static final ERXKey<String> ROLE_DESCRIPTION = new ERXKey<String>("roleDescription");
  // Relationship Keys
  public static final ERXKey<com.wowodc.model.Person> PERSONS = new ERXKey<com.wowodc.model.Person>("persons");

  // Attributes
  public static final String ROLE_DESCRIPTION_KEY = ROLE_DESCRIPTION.key();
  // Relationships
  public static final String PERSONS_KEY = PERSONS.key();

  private static Logger LOG = Logger.getLogger(_Role.class);

  public com.wowodc.model.Role localInstanceIn(EOEditingContext editingContext) {
    com.wowodc.model.Role localInstance = (com.wowodc.model.Role)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String roleDescription() {
    return (String) storedValueForKey(_Role.ROLE_DESCRIPTION_KEY);
  }

  public void setRoleDescription(String value) {
    if (_Role.LOG.isDebugEnabled()) {
    	_Role.LOG.debug( "updating roleDescription from " + roleDescription() + " to " + value);
    }
    takeStoredValueForKey(value, _Role.ROLE_DESCRIPTION_KEY);
  }

  public NSArray<com.wowodc.model.Person> persons() {
    return (NSArray<com.wowodc.model.Person>)storedValueForKey(_Role.PERSONS_KEY);
  }

  public NSArray<com.wowodc.model.Person> persons(EOQualifier qualifier) {
    return persons(qualifier, null);
  }

  public NSArray<com.wowodc.model.Person> persons(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<com.wowodc.model.Person> results;
      results = persons();
      if (qualifier != null) {
        results = (NSArray<com.wowodc.model.Person>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.wowodc.model.Person>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToPersons(com.wowodc.model.Person object) {
    includeObjectIntoPropertyWithKey(object, _Role.PERSONS_KEY);
  }

  public void removeFromPersons(com.wowodc.model.Person object) {
    excludeObjectFromPropertyWithKey(object, _Role.PERSONS_KEY);
  }

  public void addToPersonsRelationship(com.wowodc.model.Person object) {
    if (_Role.LOG.isDebugEnabled()) {
      _Role.LOG.debug("adding " + object + " to persons relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToPersons(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, _Role.PERSONS_KEY);
    }
  }

  public void removeFromPersonsRelationship(com.wowodc.model.Person object) {
    if (_Role.LOG.isDebugEnabled()) {
      _Role.LOG.debug("removing " + object + " from persons relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	removeFromPersons(object);
    }
    else {
    	removeObjectFromBothSidesOfRelationshipWithKey(object, _Role.PERSONS_KEY);
    }
  }

  public com.wowodc.model.Person createPersonsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName( com.wowodc.model.Person.ENTITY_NAME );
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, _Role.PERSONS_KEY);
    return (com.wowodc.model.Person) eo;
  }

  public void deletePersonsRelationship(com.wowodc.model.Person object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _Role.PERSONS_KEY);
    editingContext().deleteObject(object);
  }

  public void deleteAllPersonsRelationships() {
    Enumeration<com.wowodc.model.Person> objects = persons().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deletePersonsRelationship(objects.nextElement());
    }
  }


  public static com.wowodc.model.Role createRole(EOEditingContext editingContext, String roleDescription
) {
    com.wowodc.model.Role eo = (com.wowodc.model.Role) EOUtilities.createAndInsertInstance(editingContext, _Role.ENTITY_NAME);    
		eo.setRoleDescription(roleDescription);
    return eo;
  }

  public static ERXFetchSpecification<com.wowodc.model.Role> fetchSpec() {
    return new ERXFetchSpecification<com.wowodc.model.Role>(_Role.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<com.wowodc.model.Role> fetchAllRoles(EOEditingContext editingContext) {
    return _Role.fetchAllRoles(editingContext, null);
  }

  public static NSArray<com.wowodc.model.Role> fetchAllRoles(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Role.fetchRoles(editingContext, null, sortOrderings);
  }

  public static NSArray<com.wowodc.model.Role> fetchRoles(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<com.wowodc.model.Role> fetchSpec = new ERXFetchSpecification<com.wowodc.model.Role>(_Role.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<com.wowodc.model.Role> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static com.wowodc.model.Role fetchRole(EOEditingContext editingContext, String keyName, Object value) {
    return _Role.fetchRole(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.wowodc.model.Role fetchRole(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<com.wowodc.model.Role> eoObjects = _Role.fetchRoles(editingContext, qualifier, null);
    com.wowodc.model.Role eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Role that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.wowodc.model.Role fetchRequiredRole(EOEditingContext editingContext, String keyName, Object value) {
    return _Role.fetchRequiredRole(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.wowodc.model.Role fetchRequiredRole(EOEditingContext editingContext, EOQualifier qualifier) {
    com.wowodc.model.Role eoObject = _Role.fetchRole(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Role that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.wowodc.model.Role localInstanceIn(EOEditingContext editingContext, com.wowodc.model.Role eo) {
    com.wowodc.model.Role localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
