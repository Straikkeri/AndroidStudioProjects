package com.example.aleksi.h04t01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private ProgressBar progressBar; //https://media2.riemurasia.net/albumit/mmedia/mq/92t/ner/128537/normal_1932181028.jpg";
    private final String path = "https://media2.riemurasia.net/albumit/mmedia/mq/92t/ner/128537/";
    private String[] images = {"normal_1932181028.jpg", "normal_1932041046.jpg", "normal_1932160955.jpg"};
    private int visibleImg;
    private DownloadImageTask task;
    private float x1, x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //yhdistetään activity_main xml tiedostossa esitetyt elementit koodielementteihin
        imageView = (ImageView)findViewById(R.id.imageView);
        textView = (TextView)findViewById(R.id.textView);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        visibleImg = 0;
        showImage();

    }

    public void showImage(){
        //uusi async taski
        task = new DownloadImageTask();
        //taskin aloitus
        task.execute(path + images[visibleImg]);
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                if (x1 < x2) { //swipe vasemmalta oikealle eli edellinen
                    visibleImg--;
                    if (visibleImg < 0) visibleImg = images.length-1;
                } else { // swipe oikealta vasemmalle, eli seuraava
                    visibleImg++;
                    if (visibleImg > (images.length-1)) visibleImg = 0;
                }
                showImage();
                break;
        }
        return false;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
        }

        //kutsumiset? lataa kuva ja välitä se onPostExecutelle?
        @Override
        protected Bitmap doInBackground(String... urls){
            URL imageUrl;
            Bitmap bitmap = null;
            try {
                imageUrl = new URL(urls[0]);
                InputStream in = imageUrl.openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch(Exception e) {
                Log.e("<<LOADIMAGE>>", e.getMessage());
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
            textView.setText("Image " + (visibleImg + 1) + "/" + images.length);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
