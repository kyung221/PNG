package com.example.myapplication;

import android.content.res.AssetManager;
import android.renderscript.ScriptGroup;

import com.example.iload.ILoader;
import com.example.iload.ImageData;
import com.example.iload.PNGLoader;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import static junit.framework.Assert.assertTrue;

/**
 * Created by alchera on 18. 1. 16.
 */

public class image extends ExampleInstrumentedTest {

    public void loadimage() throws Exception {
        ILoader myloader = new PNGLoader();

        ImageData img = myloader.load(context,"cake.png");
        Assert.assertNotNull(img);
        // Assert.assertEquals(4,img.getFormat());
        //Assert.assertEquals(10,img.getChannel());
        Assert.assertEquals(10, img.getData().length);

    }
    ImageData lhs, rhs;
    @Before
    public void Same() throws Exception
    {
        ILoader myloader = new PNGLoader();
        lhs = myloader.load(context,"cake.png");
        rhs = myloader.load(context,"cake_flop.png");

    }

    @Test
    public void CheckData() throws Exception{
        Random rng = new Random();

        int length = lhs.getWidth() * rhs.getHeight()*lhs.getChannel();
        int count = 1000;
        byte[] origin = lhs.getData();
        byte[] copy = rhs.mirror();
        while(count-- > 0){
            int idx = rng.nextInt(length - 1);
            Assert.assertEquals(origin[idx], copy[idx]);
           // if(origin[idx]!=copy[idx])

        }

    }

}

