package com.suyang.mbg.database.service.impl;

import com.suyang.mbg.database.domain.Column;
import com.suyang.mbg.database.domain.DataSourceConfig;
import com.suyang.mbg.database.domain.Table;
import com.suyang.mbg.database.enums.JdbcType;
import com.suyang.mbg.database.service.DatabaseService;
import com.suyang.commons.CollectionUtils;
import com.suyang.mbg.utils.MySqlUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlDatabaseService implements DatabaseService {

    private static final String SHOW_TABLES = "SHOW TABLES";
    private static final String DESC_TABLE = "DESC `%s`";

    @Override
    public boolean check(DataSourceConfig config) {
        try (Connection conn = MySqlUtils.getConnection(config)) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Table> getTables(DataSourceConfig config) throws SQLException, ClassNotFoundException {
        List<Table> result = new ArrayList<>();
        try (Connection conn = MySqlUtils.getConnection(config)) {
            List<String> tableNames = getTableNames(conn);

            if (tableNames.isEmpty()) {
                throw new RuntimeException("not found tables");
            }

            for (String table : tableNames) {
                Table tb = new Table();
                tb.setName(table);
                tb.setColumns(getColumns(conn, table));
                result.add(tb);
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    private List<String> getTableNames(Connection conn) throws SQLException {
        List<String> result = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(SHOW_TABLES)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result.add(rs.getString(1));
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    private List<Column> getColumns(Connection conn, String tableName) throws SQLException {
        List<Column> result = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(String.format(DESC_TABLE, tableName))) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Column column = new Column();
                column.setName(rs.getString("Field"));
                column.setNullable(rs.getString("Null").equalsIgnoreCase("YES"));
                column.setPrimary(rs.getString("Key").equalsIgnoreCase("PRI"));
                column.setDefaultValue(rs.getString("Default"));
                column.setAutoIncrement(rs.getString("Extra").contains("auto_increment"));
                column.setVirtual(rs.getString("Extra").contains("VIRTUAL"));
                result.add(column);
            }
        }
        fillJdbcType(conn, tableName, result);
        return result;
    }

    private void fillJdbcType(Connection conn, String tableName, List<Column> columns) throws SQLException {
        ResultSet rs = conn.getMetaData().getColumns(conn.getCatalog(), conn.getSchema(), tableName, null);
        while (rs.next()) {
            String name = rs.getString("COLUMN_NAME");
            int jdbcType = rs.getInt("DATA_TYPE");
            boolean nullable = rs.getInt("NULLABLE") == 1;

            Column column = CollectionUtils.findOne(columns, item -> item.getName().equals(name));
            if (column != null) {
                column.setType(JdbcType.valueOfDb(jdbcType));
                column.setNullable(nullable);
            }
        }
    }
}
