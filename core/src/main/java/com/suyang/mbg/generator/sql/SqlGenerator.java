package com.suyang.mbg.generator.sql;

import com.suyang.mbg.domain.GeneratorConfig;

public interface SqlGenerator<T> {
    T insert(GeneratorConfig config);

    T insertCollection(GeneratorConfig config);

    T insertOrUpdate(GeneratorConfig config);

    T insertOrUpdateCollection(GeneratorConfig config);

    T update(GeneratorConfig config);

    T delete(GeneratorConfig config);

    T deleteAll(GeneratorConfig config);

    T deletes(GeneratorConfig config);

    T findAll(GeneratorConfig config);

    T findByWhere(GeneratorConfig config);

    T findByWhereOrder(GeneratorConfig config);

    T findByLimit(GeneratorConfig config);

    T findByWhereLimit(GeneratorConfig config);

    T findByWhereOrderLimit(GeneratorConfig config);

    T findById(GeneratorConfig config);

    T count(GeneratorConfig config);

    T countByWhere(GeneratorConfig config);
}
