package com.example.myapplication;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.iload.ILoader;
import com.example.iload.ImageData;
import com.example.iload.PNGLoader;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    ImageView pngImage,pngCopy,pngFlip;
    TextView mText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        pngImage = (ImageView)findViewById(R.id.pngImage);
        pngCopy = (ImageView)findViewById(R.id.pngCopy);
        pngFlip = (ImageView)findViewById(R.id.pngFlip);
        mText = (TextView)findViewById(R.id.mText);
        try {
            loadPNG();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPNG() throws Exception {


        Context context = getApplicationContext();

        ILoader myloader = new PNGLoader();

        ImageData img = myloader.load(context,"cake.png");
        byte[]data = img.getData();
        byte[]mirror = img.flip();

        Bitmap bm = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.ARGB_8888);
        bm.copyPixelsFromBuffer(ByteBuffer.wrap(data));

        Bitmap mr = Bitmap.createBitmap(img.getWidth(),img.getHeight(), Bitmap.Config.ARGB_8888);
        mr.copyPixelsFromBuffer(ByteBuffer.wrap(mirror));

//        Bitmap fp = Bitmap.createBitmap(img.getWidth(),img.getHeight(), Bitmap.Config.ARGB_8888);
//        fp.copyPixelsFromBuffer(ByteBuffer.wrap(flip));


        pngImage.setImageBitmap(bm);
        pngCopy.setImageBitmap(mr);
     //   pngFlip.setImageBitmap(fp);
    }


}
