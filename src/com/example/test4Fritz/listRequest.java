package com.example.test4Fritz;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: brodjag
 * Date: 01.08.14
 * Time: 17:07
 * To change this template use File | Settings | File Templates.
 */
public class listRequest {
    private MyActivity con;

    public listRequest(MyActivity c, String url){
        con=c;

        con.findViewById(R.id.progressBarr).setVisibility(View.VISIBLE);
        ((TextView) con.findViewById(R.id.progressBarrString)).setText(url);

        new HttpAsyncTask().execute(url);



    }



    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            con.findViewById(R.id.progressBarr).setVisibility(View.INVISIBLE);
            if(result==null){Toast.makeText(con, "ERROR! text ansver is null", Toast.LENGTH_LONG).show(); return;}
            // Toast.makeText(getBaseContext(),result,Toast.LENGTH_SHORT).show();
            setMyScroll(result);

        }


    }


    public static String GET(String url){
        InputStream inputStream = null;
        String result = null;
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = null;

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null) result += line;
        inputStream.close();
        return result;

    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) con.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }




    public void setMyScroll(String resultStr) {
        JSONObject json;
        LinearLayout scrollContent=(LinearLayout) con.findViewById(R.id.scrollContent);
        scrollContent.removeAllViews();

        try {
            json  = new JSONObject(resultStr);
            JSONArray jsArray=  (((JSONObject) json.get("metadata")).getJSONArray("results"));
            for(int i=0; i<jsArray.length();i++){
                View item= con.getLayoutInflater().inflate(R.layout.list_item,null);

                String name =((JSONObject) jsArray.get(i)).getJSONObject("data").getString("name");
                ((TextView)item.findViewById(R.id.name)).setText(name);

                String brand =((JSONObject) jsArray.get(i)).getJSONObject("data").getString("brand");
                ((TextView)item.findViewById(R.id.brand)).setText(brand);

                String price =((JSONObject) jsArray.get(i)).getJSONObject("data").getString("price");
                ((TextView)item.findViewById(R.id.price)).setText(price+" eu.");

                String path =((JSONObject) ((JSONObject) jsArray.get(i)).getJSONArray("images").get(0)).getString("path");
                Log.d("path",path);
                new loadImageItem(con,path, (ImageView) item.findViewById(R.id.image_item));
                scrollContent.addView(item);
            }
            con.TS.showList();





        } catch (JSONException e) {
            Toast.makeText(con,"json parsing error",Toast.LENGTH_LONG).show();  e.printStackTrace();
        }
    }

}
