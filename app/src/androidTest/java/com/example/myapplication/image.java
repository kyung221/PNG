package com.example.myapplication;

import com.example.iload.ILoader;
import com.example.iload.ImageData;
import com.example.iload.PNGLoader;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

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
        lhs = myloader.load(context, "540-20180111-12-3725.png");
        rhs = myloader.load(context, "540-20180111-12-3729.png");
        mhs = myloader.load(context, "540-20180111-12-3735.png");
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

