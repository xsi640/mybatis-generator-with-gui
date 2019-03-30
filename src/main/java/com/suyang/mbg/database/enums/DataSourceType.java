package com.suyang.mbg.database.enums;

import com.suyang.mbg.utils.Service;
import com.suyang.mbg.database.service.impl.MysqlDatabaseService;

public enum DataSourceType {
    @Service(clazz = MysqlDatabaseService.class)
    MySQL,
    ;
}
