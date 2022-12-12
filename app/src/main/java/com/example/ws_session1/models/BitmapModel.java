package com.example.ws_session1.models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class BitmapModel implements Serializable {
    private Bitmap bitmap;
    private String fileTime;

    public BitmapModel(Bitmap bitmap, String fileTime) {
        this.bitmap = bitmap;
        this.fileTime = fileTime;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getFileTime() {
        return fileTime;
    }
}
