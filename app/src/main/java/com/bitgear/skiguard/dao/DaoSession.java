package com.bitgear.skiguard.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.bitgear.skiguard.dao.User;
import com.bitgear.skiguard.dao.History;
import com.bitgear.skiguard.dao.Device;
import com.bitgear.skiguard.dao.Piste;
import com.bitgear.skiguard.dao.Lift;

import com.bitgear.skiguard.dao.UserDao;
import com.bitgear.skiguard.dao.HistoryDao;
import com.bitgear.skiguard.dao.DeviceDao;
import com.bitgear.skiguard.dao.PisteDao;
import com.bitgear.skiguard.dao.LiftDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig historyDaoConfig;
    private final DaoConfig deviceDaoConfig;
    private final DaoConfig pisteDaoConfig;
    private final DaoConfig liftDaoConfig;

    private final UserDao userDao;
    private final HistoryDao historyDao;
    private final DeviceDao deviceDao;
    private final PisteDao pisteDao;
    private final LiftDao liftDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        historyDaoConfig = daoConfigMap.get(HistoryDao.class).clone();
        historyDaoConfig.initIdentityScope(type);

        deviceDaoConfig = daoConfigMap.get(DeviceDao.class).clone();
        deviceDaoConfig.initIdentityScope(type);

        pisteDaoConfig = daoConfigMap.get(PisteDao.class).clone();
        pisteDaoConfig.initIdentityScope(type);

        liftDaoConfig = daoConfigMap.get(LiftDao.class).clone();
        liftDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        historyDao = new HistoryDao(historyDaoConfig, this);
        deviceDao = new DeviceDao(deviceDaoConfig, this);
        pisteDao = new PisteDao(pisteDaoConfig, this);
        liftDao = new LiftDao(liftDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(History.class, historyDao);
        registerDao(Device.class, deviceDao);
        registerDao(Piste.class, pisteDao);
        registerDao(Lift.class, liftDao);
    }
    
    public void clear() {
        userDaoConfig.clearIdentityScope();
        historyDaoConfig.clearIdentityScope();
        deviceDaoConfig.clearIdentityScope();
        pisteDaoConfig.clearIdentityScope();
        liftDaoConfig.clearIdentityScope();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public HistoryDao getHistoryDao() {
        return historyDao;
    }

    public DeviceDao getDeviceDao() {
        return deviceDao;
    }

    public PisteDao getPisteDao() {
        return pisteDao;
    }

    public LiftDao getLiftDao() {
        return liftDao;
    }

}
