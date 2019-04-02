package ${mapperPackage};

import ${entityPackage}.${entityName};

import java.util.List;

public interface ${mapperName} {
    int insert(${entityName} ${entityName?uncap_first});
    int insertCollection(List<${entityName}> ${entityName?uncap_first}s);
    int insertOrUpdate(${entityName} ${entityName?uncap_first});
    int update(${entityName} ${entityName?uncap_first});
    int delete(${primaryKey.type} id);
    int deleteAll();
    int deletes(List<${primaryKey.type}> ids);
    List<${entityName}> findAll();
    ${entityName} findById(${primaryKey.type} id);
    int count();
}