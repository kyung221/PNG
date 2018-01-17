package com.example.iload;

import android.content.Context;

public interface ILoader {
    boolean available();
    ImageData load(Context context, String assetPath) throws Exception;

}

