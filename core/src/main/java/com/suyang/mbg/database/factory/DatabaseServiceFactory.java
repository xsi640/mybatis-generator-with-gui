package com.suyang.mbg.database.factory;

import com.suyang.mbg.enums.DataSourceType;
import com.suyang.mbg.database.service.DatabaseService;
import com.suyang.commons.EnumsUtils;
import com.suyang.commons.Service;

public class DatabaseServiceFactory {
    private static DatabaseServiceFactory instance = new DatabaseServiceFactory();

    public static DatabaseServiceFactory getInstance() {
        return instance;
    }

    public DatabaseService getDatabaseService(DataSourceType type) {
        try {
            Class clazz = EnumsUtils.getAnnotation(type, Service.class).clazz();
            return (DatabaseService) clazz.newInstance();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
