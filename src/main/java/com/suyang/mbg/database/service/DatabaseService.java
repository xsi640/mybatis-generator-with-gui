package com.suyang.mbg.database.service;

import com.suyang.mbg.database.domain.DataSourceConfig;
import com.suyang.mbg.database.domain.Table;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseService {
    boolean check(DataSourceConfig config);

    List<Table> getTables(DataSourceConfig config) throws SQLException, ClassNotFoundException;
}
