package com.example.vacationproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.URL;
import java.util.HashMap;


public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {


    private String urlStr;
    private ImageView imageView;
    private static HashMap<String, Bitmap> bitmapHash = new HashMap<String, Bitmap>();

    public ImageLoadTask(String urlStr, ImageView imageView) {
        this.urlStr = urlStr;
        this.imageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap bitmap = null;
        try {
            if (bitmapHash.containsKey(urlStr)) {
                Bitmap oldbitmap = bitmapHash.remove(urlStr);
                if(oldbitmap != null) {

                    // 리사이클 조지면 스크롤 내렸다 올릴때 앱종료되서 리사이클 안시킴 -> 해결방안 찾아야함.
//                    oldbitmap.recycle();
//                    oldbitmap = null;
                }
            }
            URL url = new URL(urlStr);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            bitmapHash.put(urlStr,bitmap);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        imageView.setImageBitmap(bitmap);
        imageView.invalidate();
    }
}
