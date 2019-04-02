package ${mapperPackage};

import ${entityPackage}.${entityName};

import java.util.List;
import org.apache.ibatis.annotations.*;

public interface ${mapperName} {
<#if annoation??>${annoation["insert"]}</#if>
    int insert(${entityName} ${entityName?uncap_first});
<#if annoation??>${annoation["insertCollection"]}</#if>
    int insertCollection(<#if annoation??>@Param("list")</#if>List<${entityName}> ${entityName?uncap_first}s);
<#if annoation??>${annoation["insertOrUpdate"]}</#if>
    int insertOrUpdate(${entityName} ${entityName?uncap_first});
<#if annoation??>${annoation["insertOrUpdateCollection"]}</#if>
    int insertOrUpdateCollection(<#if annoation??>@Param("list")</#if>List<${entityName}> ${entityName?uncap_first}s);
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
<#if annoation??>${annoation["findByWhere"]}</#if>
    List<${entityName}> findByWhere(@Param("where") String where);
<#if annoation??>${annoation["findByWhereOrder"]}</#if>
    List<${entityName}> findByWhereOrder(@Param("where") String where, @Param("order") String order);
<#if annoation??>${annoation["findByLimit"]}</#if>
    List<${entityName}> findByLimit(@Param("offset") String offset, @Param("limit") String limit);
<#if annoation??>${annoation["findByWhereLimit"]}</#if>
    List<${entityName}> findByWhereLimit(@Param("where") String where, @Param("offset") String offset, @Param("limit") String limit);
<#if annoation??>${annoation["findByWhereOrderLimit"]}</#if>
    List<${entityName}> findByWhereOrderLimit(@Param("where") String where, @Param("order") String order, @Param("offset") String offset, @Param("limit") String limit);
<#if annoation??>${annoation["findById"]}</#if>
    ${entityName} findById(${primaryKey.type} id);
<#if annoation??>${annoation["count"]}</#if>
    int count();
<#if annoation??>${annoation["count"]}</#if>
    int count(@Param("where") String where);
}