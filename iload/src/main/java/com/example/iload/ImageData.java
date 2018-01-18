package com.example.iload;

import android.content.res.AssetManager;
import android.graphics.ImageFormat;

import ar.com.hjg.pngj.ImageInfo;


public interface ImageData {
    ImageFormat getFormat();


    int getWidth();
    int getChannel();
    int getHeight();

    byte[] getData() throws Exception;

    byte[] mirror() throws Exception;

    byte[] flip() throws Exception;

    byte[] origin() throws Exception;
}


