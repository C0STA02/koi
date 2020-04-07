package com.example.koi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Photo {
    private boolean photoDownloaded = false;
    Bitmap photo;
    InputStream photoStream;
    public void setPhoto() {
        synchronized (this) {
            while (photoDownloaded==false) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }
    public boolean getBool(){
        return photoDownloaded;
    }
    public  Bitmap getPhoto(){
        return photo;
    }
    public void downloaderPhoto() throws MalformedURLException {
        synchronized (this) {
            URL url = new URL("https://avatars.mds.yandex.net/get-pdb/1645430/4e0ed674-f22d-4e0a-b3e6-323ab87adb38/s375");
            try {
                photoStream = (InputStream) url.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }
            photo = BitmapFactory.decodeStream(photoStream);
            notifyAll();
        }
    }
}
