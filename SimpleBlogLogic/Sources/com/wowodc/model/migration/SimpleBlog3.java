package com.wowodc.model.migration;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationTable;
import er.extensions.migration.ERXModelVersion;

public class SimpleBlog3 extends ERXMigrationDatabase.Migration {
  
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
    ERXMigrationTable syncInfoTable = database.newTableNamed("SyncInfo");
    syncInfoTable.newStringColumn("delegatedPrimaryKeyValue", 255, true);
    syncInfoTable.newStringColumn("etag", 255, false);
    syncInfoTable.newIntegerColumn("id", false);
    syncInfoTable.newTimestampColumn("lastModified", false);
    syncInfoTable.newStringColumn("status", 50, false);
    syncInfoTable.newStringColumn("token", 255, false);
    syncInfoTable.create();
    syncInfoTable.setPrimaryKey("id");
    
    syncInfoTable.addUniqueIndex("token");
    syncInfoTable.addUniqueIndex("etag");
  }
}