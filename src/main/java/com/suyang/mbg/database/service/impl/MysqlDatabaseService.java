package com.suyang.mbg.database.service.impl;

import com.suyang.mbg.database.domain.Column;
import com.suyang.mbg.database.domain.DataSourceConfig;
import com.suyang.mbg.database.domain.Table;
import com.suyang.mbg.database.enums.JdbcType;
import com.suyang.mbg.database.service.DatabaseService;
import com.suyang.mbg.utils.MySqlUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            statement.setString(1, tableName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Column column = new Column();
                column.setName(rs.getString("Field"));
                column.setType(JdbcType.valueOfDb(rs.getString("Type")));
                column.setNull(rs.getString("Null").equalsIgnoreCase("YES"));
                column.setKey(rs.getString("Key").equalsIgnoreCase("PRI"));
                column.setDefaultValue(rs.getString("Default"));
                column.setExtra(rs.getString("Extra"));
                result.add(column);
            }
        }
        return result;
    }
}
