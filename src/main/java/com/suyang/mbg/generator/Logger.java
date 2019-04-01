package com.suyang.mbg.generator;

import com.suyang.mbg.enums.Level;

public interface Logger {
    void append(String message, Level level);
}
