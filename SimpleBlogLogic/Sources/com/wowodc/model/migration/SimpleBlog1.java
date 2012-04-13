package com.wowodc.model.migration;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.jdbc.ERXJDBCUtilities;
import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXModelVersion;

public class SimpleBlog1 extends ERXMigrationDatabase.Migration {
  @Override
  public NSArray<ERXModelVersion> modelDependencies() {
    return null;
  }

  @Override
  public void downgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
    // DO NOTHING
  }

  @Override
  public void upgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {

    ERXJDBCUtilities.executeUpdate(database.adaptorChannel(), "INSERT INTO Role (roleDescription,id) VALUES ('Admin',1)", true);
    ERXJDBCUtilities.executeUpdate(database.adaptorChannel(), "INSERT INTO Role (roleDescription,id) VALUES ('Normal',2)", true);

    ERXJDBCUtilities.executeUpdate(database.adaptorChannel(), "insert into Person (firstName, lastName, password, login, id , email) values ( 'Admin', 'User', 'test', 'test',1,'dummy-1@test.com')", true);
    ERXJDBCUtilities.executeUpdate(database.adaptorChannel(), "insert into Person (firstName, lastName, password, login, id , email) values ( 'David', 'Holt', 'david', 'david',2,'dummy-2@test.com')", true);
    ERXJDBCUtilities.executeUpdate(database.adaptorChannel(), "insert into Person (firstName, lastName, password, login, id , email) values ( 'Pascal', 'Robert', 'pascal', 'pascal',3,'dummy-3@test.com')", true);
    ERXJDBCUtilities.executeUpdate(database.adaptorChannel(), "insert into Person (firstName, lastName, password, login, id , email) values ( 'Public', 'User', 'safds987007gdsfg', 'system',4,'dummy-4@test.com')", true);
    
    ERXJDBCUtilities.executeUpdate(database.adaptorChannel(), "insert into BlogCategory (id, categoryDescription, urlFriendlyDescription) values ( 1, 'WOWODC', 'wowodc')", true);
    ERXJDBCUtilities.executeUpdate(database.adaptorChannel(), "insert into BlogCategory (id, categoryDescription, urlFriendlyDescription) values ( 2, 'WebObjects', 'webobjects')", true);
    ERXJDBCUtilities.executeUpdate(database.adaptorChannel(), "insert into BlogCategory (id, categoryDescription, urlFriendlyDescription) values ( 3, 'DirectToWeb', 'directtoweb')", true);
    
    ERXJDBCUtilities.executeUpdate(database.adaptorChannel(), "insert into XPersonRole (personId, roleId) values ( 1, 1)", true);
    ERXJDBCUtilities.executeUpdate(database.adaptorChannel(), "insert into XPersonRole (personId, roleId) values ( 2, 2)", true);
    ERXJDBCUtilities.executeUpdate(database.adaptorChannel(), "insert into XPersonRole (personId, roleId) values ( 3, 2)", true);

  }

}