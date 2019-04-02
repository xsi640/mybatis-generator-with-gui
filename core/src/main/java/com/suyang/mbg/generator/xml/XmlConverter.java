package com.suyang.mbg.generator.xml;

import com.suyang.mbg.domain.GeneratorConfig;
import com.suyang.mbg.domain.Property;
import com.suyang.mbg.generator.mapper.*;
import com.suyang.mbg.generator.sql.SqlGenerator;
import com.suyang.mbg.generator.sql.XmlSqlGenerator;
import com.suyang.commons.NameUtils;

import java.util.ArrayList;
import java.util.List;

public class XmlConverter {

    private static XmlConverter instance = new XmlConverter();

    public static XmlConverter getInstance() {
        return instance;
    }

    private static final String COLUMN_LIST = "<include refid=\"Base_Column_List\"/>";

    private static final String BASE_RESULT_MAP = "BaseResultMap";

    private SqlGenerator sqlGenerator = new XmlSqlGenerator();

    public Mapper convert(GeneratorConfig config) {
        Mapper result = new Mapper();

        result.setNamespace(config.getMapperPackage() + "." + config.getMapperName());
        result.setSql(new Sql(getSqlValue(config)));

        ResultMap resultMap = new ResultMap();
        resultMap.setType(config.getEntityPackage() + "." + config.getEntityName());
        List<Result> results = new ArrayList<>();
        results.add(new Id(NameUtils.toCamelName(config.getPrimaryKey().getName()),
                config.getPrimaryKey().getDbName(),
                config.getPrimaryKey().getType(),
                config.getPrimaryKey().getJdbcType().name()));

        for (Property property : config.getProperties()) {
            Result r = new Result(
                    NameUtils.toCamelName(property.getName()),
                    property.getDbName(),
                    property.getType(),
                    property.getJdbcType().name());
            results.add(r);
        }
        resultMap.setResults(results);
        result.setResultMap(resultMap);

        fillSelects(result, config);
        fillInserts(result, config);
        fillUpdate(result, config);
        fillDelete(result, config);

        return result;
    }

    private void fillSelects(Mapper root, GeneratorConfig config) {
        List<Select> selects = new ArrayList<>();

        Select findAll = new Select("findAll", null);
        findAll.setResultMap(BASE_RESULT_MAP);
        findAll.setValue(sqlGenerator.findAll(config));
        selects.add(findAll);

        Select findOneById = new Select("findById", config.getPrimaryKey().getType());
        findOneById.setResultMap(BASE_RESULT_MAP);
        findOneById.setValue(sqlGenerator.findById(config));
        selects.add(findOneById);

        Select count = new Select("count", null);
        count.setResultType("java.lang.Integer");
        count.setValue(sqlGenerator.count(config));
        selects.add(count);

        root.setSelects(selects);
    }

    private void fillInserts(Mapper root, GeneratorConfig config) {
        List<Insert> inserts = new ArrayList<>();

        Insert insert = new Insert("insert",
                NameUtils.toCamelName(config.getPrimaryKey().getName()),
                config.getEntityPackage() + "." + config.getEntityName(),
                config.getPrimaryKey().isAutoIncrement());
        insert.setValue(sqlGenerator.insert(config));
        inserts.add(insert);

        Insert insertCollection = new Insert("insertCollection",
                NameUtils.toCamelName(config.getPrimaryKey().getName()),
                "java.util.List",
                config.getPrimaryKey().isAutoIncrement());
        insertCollection.setValue(sqlGenerator.insertCollection(config));
        inserts.add(insertCollection);

        Insert insertOrUpdate = new Insert("insertOrUpdate",
                NameUtils.toCamelName(config.getPrimaryKey().getName()),
                config.getEntityPackage() + "." + config.getEntityName(),
                config.getPrimaryKey().isAutoIncrement());
        insertOrUpdate.setValue(sqlGenerator.insertOrUpdate(config));
        inserts.add(insertOrUpdate);

        root.setInserts(inserts);
    }

    private void fillUpdate(Mapper root, GeneratorConfig config) {
        List<Update> updates = new ArrayList<>();

        Update update = new Update("update",
                config.getEntityPackage() + "." + config.getEntityName());
        update.setValue(sqlGenerator.update(config));
        updates.add(update);

        root.setUpdates(updates);
    }

    private void fillDelete(Mapper root, GeneratorConfig config) {
        List<Delete> deletes = new ArrayList<>();

        Delete delete = new Delete("delete", config.getPrimaryKey().getType().toString());
        delete.setValue(sqlGenerator.delete(config));
        deletes.add(delete);

        Delete deleteAll = new Delete("deleteAll", null);
        deleteAll.setValue(sqlGenerator.deleteAll(config));
        deletes.add(deleteAll);

        Delete deleteIds = new Delete("deletes", "java.util.List");
        deleteIds.setValue(sqlGenerator.deletes(config));
        deletes.add(deleteIds);

        root.setDeletes(deletes);
    }

    private String getSqlValue(GeneratorConfig config) {
        StringBuilder sb = new StringBuilder();
        sb.append(config.getPrimaryKey().getDbName()).append(", ");
        List<Property> properties = config.getProperties();
        for (int i = 0; i < properties.size(); i++) {
            Property property = properties.get(i);
            if (i == properties.size() - 1) {
                sb.append(NameUtils.toCamelName(property.getDbName()));
            } else {
                sb.append(NameUtils.toCamelName(property.getDbName())).append(", ");
            }
        }
        return sb.toString();
    }
}
