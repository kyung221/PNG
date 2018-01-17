package com.example.iload;

import android.content.res.AssetManager;
import android.graphics.ImageFormat;


public interface ImageData {
    ImageFormat getFormat();
    int getWidth();
    int getChannel();
    int getHeight();
    byte[] getData() throws Exception;


    void load(AssetManager am, String assetPath) throws Exception;

    byte[] mirror() throws Exception;

    byte[] flip() throws Exception;
}

