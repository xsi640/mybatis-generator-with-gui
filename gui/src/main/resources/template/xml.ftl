&lt;?xml version="1.0" encoding="UTF-8"?&gt;
&lt;!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd"&gt;
&lt;mapper namespace="${basePackage}.${mapperPackage}.${entityName}${suffix}"&gt;
    &lt;sql id="Base_Column_List"&gt;
        ${primaryKey.name}
<#list properties as item>
    <#if item_has_next>
        ${item.name},
    <#else>
        ${item.name}
    </#if>
</#list>
    &lt;/sql&gt;

    &lt;resultMap id="BaseResultMap" type="${basePackage}.${entityPackage}.${entityName}"&gt;
        &lt;constructor&gt;
        &lt;idArg column="${primaryKey.name}" javaType="${primaryKey.type}" jdbcType="${primaryKey.jdbcType}"/&gt;
<#list properties as item>
        &lt;arg column="${item.name}" javaType="${item.type}" jdbcType="${item.jdbcType}"/&gt;
</#list>
        &lt;/constructor&gt;
    &lt;/resultMap&gt;

    &lt;insert id="insert" keyProperty="id" parameterType="${basePackage}.${entityPackage}.${entityName}" useGeneratedKeys="true"&gt;
        insert into ${tableName} ( ${primaryKey.dbName},<#list properties as item>
            <#list properties as item>
                <#if item_has_next>
                    ${item.dbName},
                <#else>
                    ${item.dbName}
                </#if>
            </#list>
        </#list>

        values()
    &lt;select id="findOneById" parameterType="java.lang.Integer" resultMap="BaseResultMap"&gt;
        select
        &lt;include refid="Base_Column_List"/&gt;
        from tb_student
        where id = #{id,jdbcType=INTEGER}
    &lt;/select&gt;
    &lt;delete id="deleteById" parameterType="java.lang.Integer"&gt;
        delete from tb_student
        where id = #{id,jdbcType=INTEGER}
    &lt;/delete&gt;

    &lt;insert id="insert" keyProperty="id" parameterType="com.suyang.domain.Student" useGeneratedKeys="true"&gt;
        insert into tb_student (id, name, age,
        birthday)
        values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER},
        #{birthday,jdbcType=TIMESTAMP})
    &lt;/insert&gt;

    &lt;update id="update" parameterType="com.suyang.domain.Student"&gt;
        update tb_student
        set name = #{name,jdbcType=VARCHAR},
        age = #{age,jdbcType=INTEGER},
        birthday = #{birthday,jdbcType=TIMESTAMP}
        where id = #{id,jdbcType=INTEGER}
    &lt;/update&gt;
    &lt;select id="findAll" resultMap="BaseResultMap"&gt;
        select
        &lt;include refid="Base_Column_List"/&gt;
        from tb_student
    &lt;/select&gt;
    &lt;delete id="deleteAll"&gt;
        delete from tb_student
    &lt;/delete&gt;
&lt;/mapper&gt;