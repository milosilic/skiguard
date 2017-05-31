package com.bitgear.skiguard.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HISTORY".
*/
public class HistoryDao extends AbstractDao<History, Long> {

    public static final String TABLENAME = "HISTORY";

    /**
     * Properties of entity History.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Id_device = new Property(1, int.class, "id_device", false, "ID_DEVICE");
        public final static Property Lat = new Property(2, float.class, "lat", false, "LAT");
        public final static Property Lng = new Property(3, float.class, "lng", false, "LNG");
        public final static Property Battery = new Property(4, int.class, "battery", false, "BATTERY");
        public final static Property Id_track = new Property(5, int.class, "id_track", false, "ID_TRACK");
        public final static Property Id_track_change = new Property(6, int.class, "id_track_change", false, "ID_TRACK_CHANGE");
    }


    public HistoryDao(DaoConfig config) {
        super(config);
    }
    
    public HistoryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HISTORY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ID_DEVICE\" INTEGER NOT NULL ," + // 1: id_device
                "\"LAT\" REAL NOT NULL ," + // 2: lat
                "\"LNG\" REAL NOT NULL ," + // 3: lng
                "\"BATTERY\" INTEGER NOT NULL ," + // 4: battery
                "\"ID_TRACK\" INTEGER NOT NULL ," + // 5: id_track
                "\"ID_TRACK_CHANGE\" INTEGER NOT NULL UNIQUE );"); // 6: id_track_change
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HISTORY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, History entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getId_device());
        stmt.bindDouble(3, entity.getLat());
        stmt.bindDouble(4, entity.getLng());
        stmt.bindLong(5, entity.getBattery());
        stmt.bindLong(6, entity.getId_track());
        stmt.bindLong(7, entity.getId_track_change());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, History entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getId_device());
        stmt.bindDouble(3, entity.getLat());
        stmt.bindDouble(4, entity.getLng());
        stmt.bindLong(5, entity.getBattery());
        stmt.bindLong(6, entity.getId_track());
        stmt.bindLong(7, entity.getId_track_change());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public History readEntity(Cursor cursor, int offset) {
        History entity = new History( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // id_device
            cursor.getFloat(offset + 2), // lat
            cursor.getFloat(offset + 3), // lng
            cursor.getInt(offset + 4), // battery
            cursor.getInt(offset + 5), // id_track
            cursor.getInt(offset + 6) // id_track_change
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, History entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setId_device(cursor.getInt(offset + 1));
        entity.setLat(cursor.getFloat(offset + 2));
        entity.setLng(cursor.getFloat(offset + 3));
        entity.setBattery(cursor.getInt(offset + 4));
        entity.setId_track(cursor.getInt(offset + 5));
        entity.setId_track_change(cursor.getInt(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(History entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(History entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(History entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}