package com.example.uliyana.Realm;

import io.realm.RealmConfiguration;

public class RealmUtility {
    private static final int SCHEMA_V_PREV = 1;// previous schema version
    private static final int SCHEMA_V_NOW = 2;// change schema version if any change happened in schema


    public static int getSchemaVNow() {
        return SCHEMA_V_NOW;
    }


    public static RealmConfiguration getDefaultConfig() {
        return new RealmConfiguration.Builder()
                .schemaVersion(SCHEMA_V_NOW)
                .allowWritesOnUiThread(true)
                .deleteRealmIfMigrationNeeded()// if migration needed then this methoud will remove the existing database and will create new database
                .build();
    }
}
