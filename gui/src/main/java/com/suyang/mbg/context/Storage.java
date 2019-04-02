package com.suyang.mbg.context;

import com.suyang.mbg.domain.DataSourceConfig;
import com.suyang.mbg.domain.GenSettings;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Storage implements Serializable {

    private static final long serialVersionUID = -4752389676169627338L;

    private List<DataSourceConfig> configs = null;
    private Map<String, GenSettings> settings = null;

    public Storage(List<DataSourceConfig> configs, Map<String, GenSettings> settings) {
        this.configs = configs;
        this.settings = settings;
    }

    public List<DataSourceConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<DataSourceConfig> configs) {
        this.configs = configs;
    }

    public Map<String, GenSettings> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, GenSettings> settings) {
        this.settings = settings;
    }
}
