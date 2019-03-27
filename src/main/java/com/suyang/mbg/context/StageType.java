package com.suyang.mbg.context;

public enum StageType {
    @StageDescribe(fxml = "main/main.fxml", css = "main/main.css", width = 500, height = 600)
    Main,
    ;

    public static StageDescribe valueOf(StageType stageType) throws NoSuchFieldException {
        return stageType.getClass().getField(stageType.name()).getAnnotation(StageDescribe.class);
    }
}
