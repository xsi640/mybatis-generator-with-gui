package com.suyang.mbg.generator.sql;

import com.suyang.mbg.generator.XmlGenConfig;

public interface SqlGenerator {
    String insert(XmlGenConfig config);

    String insertCollection(XmlGenConfig config);

    String update(XmlGenConfig config);

    String delete(XmlGenConfig config);

    String deleteAll(XmlGenConfig config);

    String deletes(XmlGenConfig config);

    String findAll(XmlGenConfig config);

    String findById(XmlGenConfig config);

    String count(XmlGenConfig config);
}
