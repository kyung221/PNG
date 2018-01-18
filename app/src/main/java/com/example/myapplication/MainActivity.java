package com.example.myapplication;

import android.content.Context;

import android.graphics.Bitmap;


import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.iload.ILoader;
import com.example.iload.ImageData;
import com.example.iload.PNGLoader;

import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;


public class MainActivity extends AppCompatActivity {

    ImageView pngImage;
    ImageData img;
    Bitmap bm = null;

    private Button mirror, flip, original;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        pngImage = (ImageView)findViewById(R.id.pngImage);
        mirror = (Button)findViewById(R.id.mirror);
        flip = (Button)findViewById(R.id.flip);
        original = (Button)findViewById(R.id.original);
        try {
            loadPNG();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        try {
                            bm = MirrorPng(img);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(0);
                    }
                }.start();
            }
        });

        flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        try {
                            bm = FlipPng(img);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(0);
                    }
                }.start();
            }
        });

        original.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        try {
                            bm = original(img);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(0);
                    }
                }.start();
            }
        });
    }


    Handler mHandler = new Handler(){
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            pngImage.setImageBitmap(bm);
        }
    };

    public void loadPNG() throws Exception {

        Context context = getApplicationContext();

        ILoader myloader = new PNGLoader();

        img = myloader.load(context,"" +
                "540-20180111-12-3725.png");
        byte[]data = img.getData();

        bm = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.ARGB_8888);
        bm.copyPixelsFromBuffer(ByteBuffer.wrap(data));

        pngImage.setImageBitmap(bm);
    }

    public Bitmap original(ImageData img) throws Exception{
        Bitmap org = Bitmap.createBitmap(img.getWidth(),img.getHeight(), Bitmap.Config.ARGB_8888);
        byte[]origin = img.origin();
        org.copyPixelsFromBuffer(ByteBuffer.wrap(origin));
        return org;
    }

    public Bitmap MirrorPng(ImageData img)throws Exception{
        Bitmap mr = Bitmap.createBitmap(img.getWidth(),img.getHeight(), Bitmap.Config.ARGB_8888);
        byte[]mirror = img.mirror();
        mr.copyPixelsFromBuffer(ByteBuffer.wrap(mirror));
        return mr;
    }

    public Bitmap FlipPng(ImageData img)throws Exception{
        Bitmap fp = Bitmap.createBitmap(img.getWidth(),img.getHeight(), Bitmap.Config.ARGB_8888);
        byte[]flip = img.flip();
        fp.copyPixelsFromBuffer(ByteBuffer.wrap(flip));
        return fp;
    }

}
