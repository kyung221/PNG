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
    AssetManager am;
    String assetPath;

    public png(){

    }

    public png(AssetManager am, String assetPath) throws IOException {
        this.assetPath = assetPath;
        this.am = am;
        pngr = new PngReaderByte(am.open(assetPath));
        data = new byte[pngr.imgInfo.cols*pngr.imgInfo.rows*pngr.imgInfo.channels];

    }

    @Override
    public void load(AssetManager am, String assetPath)throws Exception {
        pngr = new PngReaderByte(am.open(assetPath));
        data = new byte[pngr.imgInfo.cols*pngr.imgInfo.rows*pngr.imgInfo.channels];

    }

    @Override
    public ImageFormat getFormat() {
        // int form = ImageFormat.UNKNOWN;
        final ImageFormat format = getFormat();
        return format;
    }

    @Override
    public ImageInfo getInfo(){
        return pngr.imgInfo;
    }
    @Override
    public int getWidth() {
        width = pngr.imgInfo.cols;
        return width;
    }

    @Override
    public int getChannel() {
        channels = pngr.imgInfo.channels;
        return channels;
    }

    @Override
    public int getHeight() {
        height = pngr.imgInfo.rows;
        return height;
    }

    @Override
    public byte[] getData() throws Exception {
        int count =0;
        while(pngr.hasMoreRows()) {
            ImageLineByte line = pngr.readRowByte();
            byte[] line1 = line.getScanlineByte();
            System.arraycopy(line1,0,data,(line1.length)*count,line1.length);
            count++;
        }
        return data;

    }

    @Override
    public byte[] mirror() throws Exception {

        byte[] line = new byte[pngr.imgInfo.cols*pngr.imgInfo.channels];
        int channels = pngr.imgInfo.channels;
        byte aux;

        for (int row = 0; row < pngr.imgInfo.rows; row++) {
            System.arraycopy(data,(line.length)*row,line,0,line.length);

            for (int c1 = 0, c2 = pngr.imgInfo.cols - 1; c1 < c2; c1++, c2--) {

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
        byte[] temp = new byte[pngr.imgInfo.cols*pngr.imgInfo.rows*pngr.imgInfo.channels];
        byte[] line1 = new byte[pngr.imgInfo.cols*pngr.imgInfo.channels];
        System.arraycopy(data,0,temp,0,data.length);
        int k = pngr.imgInfo.rows - 1;
        for(int row =0;row<pngr.imgInfo.rows;row++) {
            System.arraycopy(temp,(line1.length)*row,line1,0,line1.length);

            System.arraycopy(line1, 0, data,
                    line1.length * (k--), line1.length);
        }

        return data;
    }


}
