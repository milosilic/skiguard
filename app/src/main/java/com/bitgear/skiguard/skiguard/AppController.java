package com.bitgear.skiguard.skiguard;

import android.app.Application;

import com.bitgear.skiguard.dao.DaoMaster;
import com.bitgear.skiguard.dao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by ila on 25.5.17..
 */

public class AppController extends Application {

    public static final boolean ENCRYPTED = false;
    private DaoSession daoSession;


    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"users-db"); //The users-db here is the name of our database.
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
