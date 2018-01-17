package com.example.myapplication;

import android.content.Context;

import android.graphics.Bitmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import com.example.iload.ILoader;
import com.example.iload.ImageData;
import com.example.iload.PNGLoader;

import java.nio.ByteBuffer;


public class MainActivity extends AppCompatActivity {

    ImageView pngImage;
    ImageData img;
    Bitmap bm;
    public void ButtonClick(View v) throws Exception {
        switch(v.getId())
        {
            case R.id.mirror:
            {
                MirrorPng(img);
                break;
            }
            case R.id.flip:
            {
                FlipPng(img);
                break;
            }
            case R.id.original:
            {
                original();
                break;
            }

        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        pngImage = (ImageView)findViewById(R.id.pngImage);

        try {
            loadPNG();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPNG() throws Exception {

        Context context = getApplicationContext();

        ILoader myloader = new PNGLoader();

        img = myloader.load(context,"540-20180111-12-3725.png");
        byte[]data = img.getData();

        bm = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.ARGB_8888);
        bm.copyPixelsFromBuffer(ByteBuffer.wrap(data));

        pngImage.setImageBitmap(bm);

    }
    public void original() throws Exception{
        pngImage.setImageBitmap(bm);
    }
    public void MirrorPng(ImageData img)throws Exception{
        byte[]mirror = img.mirror();
        Bitmap mr = Bitmap.createBitmap(img.getWidth(),img.getHeight(), Bitmap.Config.ARGB_8888);
        mr.copyPixelsFromBuffer(ByteBuffer.wrap(mirror));
        pngImage.setImageBitmap(mr);
    }

    public void FlipPng(ImageData img)throws Exception{
        Bitmap fp = Bitmap.createBitmap(img.getWidth(),img.getHeight(), Bitmap.Config.ARGB_8888);
        byte[]flip = img.flip();
        fp.copyPixelsFromBuffer(ByteBuffer.wrap(flip));
        pngImage.setImageBitmap(fp);
    }

}
