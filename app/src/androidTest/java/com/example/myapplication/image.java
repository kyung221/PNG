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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
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
    ImageData lhs, rhs, mhs;
    @Before
    public void Same() throws Exception
    {
        ILoader myloader = new PNGLoader();

    }

    @Test
    public void CheckWidth() throws Exception{
        assertEquals(10,lhs.getWidth());
    }
    @Test
    public void CheckHeight() throws Exception{
        assertEquals(10,lhs.getHeight());
    }
    @Test
    public void CheckChannels() throws Exception{
        assertEquals(10,lhs.getChannel());
    }
}

