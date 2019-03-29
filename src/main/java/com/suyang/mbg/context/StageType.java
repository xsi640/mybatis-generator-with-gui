package com.suyang.mbg.context;

public enum StageType {
    @StageDescribe(fxml = "views/main.fxml", css = "views/main.css", title = "Mybatis Code Generator", width = 850, height = 550, minWidth = 850, minHeight = 550)
    Main,
    @StageDescribe(fxml = "views/datasource.fxml", css = "views/main.css", title = "New Connection", width = 500, height = 300, minWidth = 500, minHeight = 300, resizable = false)
    DataSource;

    public static StageDescribe valueOf(StageType stageType) throws NoSuchFieldException {
        return stageType.getClass().getField(stageType.name()).getAnnotation(StageDescribe.class);
    }
}
