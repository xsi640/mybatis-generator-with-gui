package ${entityPackage};

import java.util.Date;

public class ${entityName} {
    private ${primaryKey.type} ${primaryKey.name?uncap_first};
<#list properties as item>
    private ${item.type} ${item.name?uncap_first};
</#list>

    public ${entityName}() { }

    public ${entityName}(<#list properties as item><#if item_has_next>${item.type} ${item.name?uncap_first}, <#else>${item.type} ${item.name?uncap_first}</#if></#list>) {
        <#list properties as item>
            this.${item.name?uncap_first} = ${item.name?uncap_first};
        </#list>
    }

    public ${primaryKey.type} get${primaryKey.name?cap_first}(){
        return this.${primaryKey.name?uncap_first};
    }

    public void set${primaryKey.name?cap_first}(${primaryKey.type} ${primaryKey.name?uncap_first}){
        this.${primaryKey.name?uncap_first} = ${primaryKey.name?uncap_first};
    }

<#list properties as item>
    <#if item.type=="Boolean">
    public ${item.type} is${item.name?cap_first}() {
    <#else>
    public ${item.type} get${item.name?cap_first}() {
    </#if>
        return this.${item.name?uncap_first};
    }

    public void set${item.name?cap_first}(${item.type} ${item.name?uncap_first}) {
        this.${item.name?uncap_first} = ${item.name?uncap_first};
    }

</#list>
    @Override
    public String toString() {
        return "${entityName}{" +
<#list properties as item>
               "${item.name?uncap_first}=" + this.${item.name?uncap_first} +
</#list>
               '}';
    }
}