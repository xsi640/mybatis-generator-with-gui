package com.suyang.mbg.context;

public enum StageType {
    @StageDescribe(fxml = "views/main.fxml", title = "Mybatis代码生成器", width = 850, height = 550, minWidth = 850, minHeight = 550)
    Main,
    @StageDescribe(fxml = "views/datasource.fxml", title = "新增数据源", width = 500, height = 300, minWidth = 500, minHeight = 300, resizable = false)
    DataSource,
    @StageDescribe(fxml = "views/generator.fxml", title = "生成代码", width = 400, height = 300, minWidth = 400, minHeight = 300, resizable = false)
    Generator;

    public static StageDescribe valueOf(StageType stageType) throws NoSuchFieldException {
        return stageType.getClass().getField(stageType.name()).getAnnotation(StageDescribe.class);
    }
}
