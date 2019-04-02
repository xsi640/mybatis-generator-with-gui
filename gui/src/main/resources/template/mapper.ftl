package ${mapperPackage};

import ${entityPackage}.${entityName};

import java.util.List;
<#if annoation??>import org.apache.ibatis.annotations.*;</#if>

public interface ${mapperName} {
<#if annoation??>${annoation["insert"]}</#if>
    int insert(${entityName} ${entityName?uncap_first});
<#if annoation??>${annoation["insertCollection"]}</#if>
    int insertCollection(<#if annoation??>@Param("list")</#if>List<${entityName}> ${entityName?uncap_first}s);
<#if annoation??>${annoation["insertOrUpdate"]}</#if>
    int insertOrUpdate(${entityName} ${entityName?uncap_first});
<#if annoation??>${annoation["update"]}</#if>
    int update(${entityName} ${entityName?uncap_first});
<#if annoation??>${annoation["delete"]}</#if>
    int delete(${primaryKey.type} id);
<#if annoation??>${annoation["deleteAll"]}</#if>
    int deleteAll();
<#if annoation??>${annoation["deletes"]}</#if>
    int deletes(<#if annoation??>@Param("list")</#if>List<${primaryKey.type}> ids);
<#if annoation??>${annoation["findAll"]}</#if>
    List<${entityName}> findAll();
<#if annoation??>${annoation["findById"]}</#if>
    ${entityName} findById(${primaryKey.type} id);
<#if annoation??>${annoation["count"]}</#if>
    int count();
}