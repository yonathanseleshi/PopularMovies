package popularmovies.com.popularmovies;

import android.content.ClipData;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public int popular = R.id.sortby_popularity;
    public int rating = R.id.sortby_rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String rated = "top_rated";
        String popularity = "popular";


        int itemSelected = item.getItemId();

        if (itemSelected == rating) {

            URL getUrl = NetworkUtils.BuildUrl(rated);


            new GetMovieData().execute(getUrl);


        } else if (itemSelected == popular) {

            URL getUrl = NetworkUtils.BuildUrl(popularity);

            new GetMovieData().execute(getUrl);


        }

        return super.onOptionsItemSelected(item);
    }


    public class GetMovieData extends AsyncTask<URL, Void, String> {


        @Override
        protected String doInBackground(URL... urls) {

            String moviedbResults = null;
            URL queryURL = urls[0];

            try {


                moviedbResults = NetworkUtils.getResponseFromHttpUrl(queryURL);

            } catch (IOException e) {

                e.printStackTrace();
            }

            return moviedbResults;

        }


        @Override
        protected void onPostExecute(String s) {

            try {


                JSONObject moviedb_json = new JSONObject(s);
                JSONObject results = moviedb_json.getJSONObject("results");
                 String poster = results.getString("poster_path");




            } catch (JSONException e) {

                e.printStackTrace();
            }


        }
    }




}