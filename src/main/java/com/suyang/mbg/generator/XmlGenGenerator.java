package com.suyang.mbg.generator;

import com.suyang.mbg.utils.SerializeUtils;

public class XmlGenGenerator implements BaseGenProcessor {
    @Override
    public void process(String output, BaseGenConfig config) {
        SerializeUtils.xmlSerializeByJAXBToFile(output, XmlConverter.getInstance().convert((XmlGenConfig) config));
    }
}
