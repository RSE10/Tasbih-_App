package com.example.tasbih;

import android.app.Application;

public class GetUserId extends Application {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
