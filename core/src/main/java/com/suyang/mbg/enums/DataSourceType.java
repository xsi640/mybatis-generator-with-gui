package com.suyang.mbg.enums;

import com.suyang.commons.Service;
import com.suyang.mbg.database.service.impl.MysqlDatabaseService;

public enum DataSourceType {
    @Service(clazz = MysqlDatabaseService.class)
    MySQL,
    ;
}
