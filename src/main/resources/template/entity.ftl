package ${basePackage}.${entityNamespace};

import java.util.Date;

public class ${className} {
    private ${primaryKey.type} ${primaryKey.name?uncap_first};
<#list properties as item>
    private ${item.type} ${item.name?uncap_first};
</#list>

    public ${className}() { }

    public ${className}(<#list properties as item><#if item_has_next>${item.type} ${item.name?uncap_first},<#else>${item.type} ${item.name?uncap_first}</#if></#list>) {
        <#list properties as item>
            this.${item.name?uncap_first} = ${item.name?uncap_first};
        </#list>
    }

<#list properties as item>
    public ${item.type} get${item.name?cap_first}() {
        return this.${item.name?uncap_first};
    }

    public ${item.type} set${item.name?cap_first}(${item.type} ${item.name?uncap_first}) {
        this.${item.name?uncap_first} = ${item.name?uncap_first};
    }
</#list>

    @Override
    public String toString() {
        return "${className}{" +
<#list properties as item>
               "${item.name?uncap_first}=" + this.${item.name?uncap_first} +
</#list>
               '}';
    }
}