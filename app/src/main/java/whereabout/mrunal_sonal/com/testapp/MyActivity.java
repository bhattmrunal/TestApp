package whereabout.mrunal_sonal.com.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by mrunal on 1/19/16.
 */
public class MyActivity extends Activity{
    String URL="http://api.nytimes.com/svc/topstories/v1/home.json?api-key=15629235341919a7b4b8b6e9344f9bca:6:72095783";
    ArrayList<String> items, images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items=new ArrayList<String>();
        images=new ArrayList<String>();

        setContentView(R.layout.intro);
        new ConnectTask().execute(URL);

    }

    class ConnectTask extends AsyncTask<String, Void, ArrayList> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {

            try {
                URL url = new URL
                        ("http://api.nytimes.com/svc/topstories/v1/home.json?api-key=15629235341919a7b4b8b6e9344f9bca:6:72095783");
                HttpURLConnection urlConnection =
                        (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                int response = urlConnection.getResponseCode();
                if (response == HttpStatus.SC_OK) {
                    // gets the server json data
                    BufferedReader bufferedReader =
                            new BufferedReader(new InputStreamReader(
                                    urlConnection.getInputStream()));

                    String JsonString=bufferedReader.readLine();
                    Log.i("jsonstring",JsonString);
                    JSONObject jsonResponse = new JSONObject(JsonString);
                    JSONArray cast = jsonResponse.getJSONArray("results");
                    for (int i=0; i<cast.length(); i++) {
                        JSONObject actor = cast.getJSONObject(i);
                        String title = actor.getString("title");
                        JSONArray imgUrlObj=actor.getJSONArray("multimedia");
                        String imgurl= imgUrlObj.getJSONObject(0).getString("url");
                        items.add(title);
                        images.add(imgurl);
                    }
                }
                else
                {
                    items.add("Data not found");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return items;
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {
            super.onPostExecute(arrayList);

            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("title",items);
            intent.putExtra("imgurl",images);
            startActivity(intent);
        }
    }

}
