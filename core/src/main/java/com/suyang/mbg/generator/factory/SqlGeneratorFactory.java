package com.suyang.mbg.generator.factory;

import com.suyang.mbg.generator.sql.SqlGenerator;
import com.suyang.mbg.generator.sql.XmlSqlGenerator;

public class SqlGeneratorFactory {
    private static SqlGeneratorFactory sqlGenerator = new SqlGeneratorFactory();

    public static SqlGeneratorFactory getInstance() {
        return sqlGenerator;
    }

    public SqlGenerator getXmlSqlGenerator() {
        return new XmlSqlGenerator();
    }
}
