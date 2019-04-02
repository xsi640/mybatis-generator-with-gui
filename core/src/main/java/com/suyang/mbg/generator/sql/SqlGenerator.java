package com.suyang.mbg.generator.sql;

import com.suyang.mbg.domain.GeneratorConfig;

public interface SqlGenerator<T> {
    T insert(GeneratorConfig config);

    T insertCollection(GeneratorConfig config);

    T insertOrUpdate(GeneratorConfig config);

    T update(GeneratorConfig config);

    T delete(GeneratorConfig config);

    T deleteAll(GeneratorConfig config);

    T deletes(GeneratorConfig config);

    T findAll(GeneratorConfig config);

    T findById(GeneratorConfig config);

    T count(GeneratorConfig config);
}
