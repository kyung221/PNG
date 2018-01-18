package com.example.iload;


import android.content.res.AssetManager;
import android.graphics.ImageFormat;

import java.io.IOException;

import ar.com.hjg.pngj.ImageInfo;
import ar.com.hjg.pngj.ImageLineByte;

import ar.com.hjg.pngj.PngReaderByte;



public class png implements ImageData {

    PngReaderByte pngr;

    int width;
    int height;
    int channels;

    byte[] data;
    byte[] temp;
    AssetManager am;
    String assetPath;

    public png(){

    }

    public png(AssetManager am, String assetPath) throws IOException {

        this.assetPath = assetPath;
        this.am = am;
        pngr = new PngReaderByte(am.open(assetPath));
        width = pngr.imgInfo.cols;
        height = pngr.imgInfo.rows;
        channels = pngr.imgInfo.channels;
        data = new byte[width*height*channels];
        temp = new byte[width*height*channels];
    }


    @Override
    public ImageFormat getFormat() {
        // int form = ImageFormat.UNKNOWN;
        final ImageFormat format = getFormat();
        return format;
    }


    @Override
    public int getWidth() {

        return width;
    }

    @Override
    public int getChannel() {

        return channels;
    }

    @Override
    public int getHeight() {

        return height;
    }

    @Override
    public byte[] getData() throws Exception {
        int count =0;
        while(pngr.hasMoreRows()) {
            ImageLineByte line = pngr.readRowByte();
            byte[] line1 = line.getScanlineByte();
            System.arraycopy(line1,0,data,(line1.length)*count,line1.length);
            System.arraycopy(line1,0,temp,(line1.length)*count,line1.length);
            count++;
        }
        return data;

    }

    @Override
    public byte[] mirror() throws Exception {

        byte[] line = new byte[width*channels];
        byte aux;

        for (int row = 0; row < height; row++) {
            System.arraycopy(data,(line.length)*row,line,0,line.length);

            for (int c1 = 0, c2 = width - 1; c1 < c2; c1++, c2--) {
                for (int i = 0; i < channels; i++) {
                    aux = line[c1 * channels + i];
                    line[c1 * channels + i] = line[c2 * channels + i];
                    line[c2 * channels + i] = aux;
                }
                System.arraycopy(line,0,data,
                        (line.length)*row,line.length);
            }
        }
        return data;
    }

    @Override
    public byte[] flip() throws Exception{
        byte[] temp = new byte[width*height*channels];
        byte[] line1 = new byte[width*channels];
        System.arraycopy(data,0,temp,0,data.length);
        int k = height - 1;
        for(int row = 0; row < height ;row++) {
            System.arraycopy(temp,(line1.length)*row,line1,0,line1.length);

            System.arraycopy(line1, 0, data,
                    line1.length * (k--), line1.length);
        }

        return data;
    }
    @Override
    public byte[] origin() throws Exception{
        System.arraycopy(temp,0,data,0,data.length);

        return data;
    }

}
