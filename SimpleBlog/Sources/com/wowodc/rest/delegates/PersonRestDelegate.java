package com.wowodc.rest.delegates;

import com.webobjects.eocontrol.EOClassDescription;
import com.webobjects.foundation.NSArray;
import com.wowodc.model.Person;

import er.extensions.eof.ERXEC;
import er.extensions.eof.ERXQ;
import er.rest.ERXAbstractRestDelegate;
import er.rest.ERXRestContext;

public class PersonRestDelegate extends ERXAbstractRestDelegate {

  public Object primaryKeyForObject(Object obj, ERXRestContext context) {
    return ((Person)obj).fullName();
  }

  public Object createObjectOfEntityWithID(EOClassDescription entity, Object id, ERXRestContext context) {
    return null;
  }

  public Object objectOfEntityWithID(EOClassDescription entity, Object id, ERXRestContext context) {
    NSArray<Person> persons = ERXQ.filtered(Person.fetchAllPersons(ERXEC.newEditingContext()), ERXQ.is("fullName", id));
    return persons.size() == 0 ? null : persons.objectAtIndex(0);  
  }

}
