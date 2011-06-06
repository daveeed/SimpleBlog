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

    ERXJDBCUtilities.executeUpdate(database.adaptorChannel(), "insert into Person ( \"firstName\", \"lastName\", \"password\", \"login\", \"id\" ,\"email\") values ( 'Test', 'User', 'test', 'test',1,'dummy@test.com')", true);

  }

}