package com.example.iload;

import android.content.Context;
import android.content.res.AssetManager;


public class PNGLoader implements ILoader{
    AssetManager am;

    public PNGLoader() {

    }

    @Override
    public boolean available() {
        return am != null;
    }

    @Override
    public ImageData load(Context context, String assetPath) throws Exception {

        am = context.getAssets();
        ImageData png = new png();
        png.load(am,assetPath);

        return png;
    }


}
