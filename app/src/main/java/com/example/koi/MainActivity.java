package com.example.koi;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.koi.R;

import java.net.MalformedURLException;

class Test {

    Photo photo;

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            photo.setPhoto();
        }
    }
}

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    Bitmap photoBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView2);
        textView = (TextView) findViewById(R.id.textView2);
    }

    public void main(String[] args) throws MalformedURLException {
    Test test = new Test();
    test.photo = new Photo();
    Test.MyThread thread = test.new MyThread();
    thread.start();
    test.photo.downloaderPhoto();
    while(test.photo.getBool()!=true){
        textView.setText("NO!");
    }
    photoBitmap = test.photo.getPhoto();
    imageView.setImageBitmap(photoBitmap);
    textView.setText("YES!!!");
    }
}
