package ${mapperPackage};

import ${entityPackage}.${entityName};

import java.util.List;

public interface ${mapperName} {
    int insert(${entityName} ${entityName?uncap_first});
    int insertCollection(List<${entityName}> ${entityName?uncap_first}s);
    int insertOrUpdate(${entityName} ${entityName?uncap_first});
    int insertOrUpdateCollection(List<${entityName}> ${entityName?uncap_first}s);
    int update(${entityName} ${entityName?uncap_first});
    int delete(${primaryKeyType} id);
    int deleteAll();
    int deleteIds(List<${primaryKeyType}> ids);
    List<${entityName}> findAll();
    int count();
}