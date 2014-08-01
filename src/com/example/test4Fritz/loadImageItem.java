package com.example.test4Fritz;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: brodjag
 * Date: 08.08.13
 * Time: 13:52
 * To change this template use File | Settings | File Templates.
 */
public class loadImageItem {

    Activity con;
    String urlS, fileName;
    String cashFolder = "cash";
    ImageView img;


    public loadImageItem(Activity c, String url_in, ImageView layout) {
        con = c;
        urlS = url_in;
        fileName = "img_" + System.currentTimeMillis() + Math.random();
        img = layout;

        new task().execute();
    }


    private class task extends AsyncTask<Void, Integer, Bitmap> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                //FileOutputStream ff;
                URL url = new URL(urlS);
                url.getAuthority();
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(15000);
                conn.setDefaultUseCaches(false);
                // conn.setRequestProperty("Accept-Encoding", "identity");

                conn.connect();
                Log.d("savef1", urlS + " code: " + conn.getResponseCode());
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {


                    File folder = new File(con.getCacheDir(), cashFolder);
                    if (!folder.exists()) {
                        folder.mkdir();
                    }
                    File f = new File(folder, fileName + ".tmp");


                    Log.d("satSavefile", urlS + " code: " + conn.getResponseCode());

                    OutputStream outStream = new FileOutputStream(f);

                    //InputStream
                    InputStream inputStream = conn.getInputStream();
                    int totalSize = conn.getContentLength();
                    // Log.d("totalSize","="+totalSize);
                    //  waitDialog.setMax(totalSize/1024);
                    int downloadedSize = 0;
                    byte[] buffer = new byte[1024];


                    int bufferLength; //used to store a temporary size of the buffer

                    while ((bufferLength = inputStream.read(buffer)) > 0) {
                        outStream.write(buffer, 0, bufferLength);
                        downloadedSize = downloadedSize + bufferLength;
                        Log.d("satSavefile", "size=" + downloadedSize);
                    }//end while
                    Log.d("satSavefile", "mark0");
                    outStream.close();

                    Log.d("satSavefile", "mark1");
                    File tof = new File(folder, fileName);
                    Log.d("satSavefile", "mark2");
                    if (!f.renameTo(tof)) {
                        Log.d("satSavefile", "error rename");
                        //    Toast.makeText(con, "ошибка периименвания", Toast.LENGTH_LONG).show();
                    }

                    // Bitmap b = BitmapFactory.decodeFile(con.getCacheDir() + "/" + cashFolder + "/" + fileName);
                    return BitmapFactory.decodeFile(con.getCacheDir() + "/" + cashFolder + "/" + fileName);


                } else {
                    Log.d("satSavefile", ("error http code" + conn.getResponseCode() + " for url"));
                }
                // conn.wait(1000);
                //for (int q = 0; q < 500000; q++) {   String v = "";         }


            } catch (Exception e) {
                Log.d("satSavefile", ("error save file ! " + e.getMessage()));
            }

            return null;
        }


        protected void onPostExecute(Bitmap b) {
            if (b != null) {
                 img.setImageBitmap(b);
                //Drawable d = new BitmapDrawable(b);
               // img.setBackgroundDrawable(d);

                b = BitmapFactory.decodeFile("");
                b = null;
                System.gc();

            }
            File file = new File(con.getCacheDir() + "/" + cashFolder + "/" + fileName);
            file.delete();

        }
    }
}




