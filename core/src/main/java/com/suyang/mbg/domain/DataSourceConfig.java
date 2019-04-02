package com.suyang.mbg.domain;

import java.io.Serializable;
import java.util.Objects;

public class DataSourceConfig implements Serializable {
    private static final long serialVersionUID = -7389129207461526171L;
    private String name;
    private String host;
    private int port;
    private String username;
    private String password;
    private String dbName;

    public DataSourceConfig() {
    }

    public DataSourceConfig(String name, String host, int port, String username, String password, String dbName) {
        this.name = name;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.dbName = dbName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataSourceConfig config = (DataSourceConfig) o;
        return Objects.equals(name, config.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
