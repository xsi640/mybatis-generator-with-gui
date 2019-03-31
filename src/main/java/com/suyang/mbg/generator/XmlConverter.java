package com.suyang.mbg.generator;

import com.suyang.mbg.generator.domain.Property;
import com.suyang.mbg.generator.mapper.*;

import java.util.ArrayList;
import java.util.List;

public class XmlConverter {

    private static XmlConverter instance = new XmlConverter();

    public static XmlConverter getInstance() {
        return instance;
    }

    public Root convert(XmlGenConfig xmlGenConfig) {
        Root result = new Root();

        Mapper mapper = new Mapper();
        mapper.setNamespace(xmlGenConfig.getBasePackage() + "." + xmlGenConfig.getMapperNamespace() + "." + xmlGenConfig.getEntityName() + xmlGenConfig.getSuffix());
        mapper.setSql(new Sql(getSqlValue(xmlGenConfig)));
        result.setMapper(mapper);

        ResultMap resultMap = new ResultMap();
        resultMap.setType(xmlGenConfig.getBasePackage() + "." + xmlGenConfig.getEntityPackage() + "." + xmlGenConfig.getEntityName());
        Constructor constructor = new Constructor();
        List<Arg> args = new ArrayList<>();
        IdArg idArg = new IdArg(xmlGenConfig.getPrimaryKey().getDbName(), xmlGenConfig.getPrimaryKey().getType().name(), xmlGenConfig.getPrimaryKey().getJdbcType().name());
        args.add(idArg);
        for (Property property : xmlGenConfig.getProperties()) {
            Arg arg = new Arg(property.getDbName(), property.getType().name(), property.getJdbcType().name());
            args.add(arg);
        }
        constructor.setArgs(args);
        resultMap.setConstructor(constructor);
        result.setResultMap(resultMap);


        return null;
    }

    private String getSqlValue(XmlGenConfig xmlGenConfig) {
        StringBuilder sb = new StringBuilder();
        List<Property> properties = xmlGenConfig.getProperties();
        for (int i = 0; i < properties.size(); i++) {
            if (i == properties.size() - 1) {
                sb.append(xmlGenConfig.getPrimaryKey().getDbName());
            } else {
                sb.append(xmlGenConfig.getPrimaryKey().getDbName()).append(", ");
            }
        }
        return sb.toString();
    }
}
