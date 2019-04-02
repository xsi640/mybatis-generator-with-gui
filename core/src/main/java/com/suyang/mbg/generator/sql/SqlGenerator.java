package com.suyang.mbg.generator.sql;

import com.suyang.mbg.domain.GeneratorConfig;

public interface SqlGenerator {
    String insert(GeneratorConfig config);

    String insertCollection(GeneratorConfig config);

    String insertOrUpdate(GeneratorConfig config);

    String update(GeneratorConfig config);

    String delete(GeneratorConfig config);

    String deleteAll(GeneratorConfig config);

    String deletes(GeneratorConfig config);

    String findAll(GeneratorConfig config);

    String findById(GeneratorConfig config);

    String count(GeneratorConfig config);
}
