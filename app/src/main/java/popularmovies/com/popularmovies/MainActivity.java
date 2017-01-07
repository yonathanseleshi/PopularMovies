package popularmovies.com.popularmovies;

import android.content.ClipData;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public int popular = R.id.sortby_popularity;
    public int rating = R.id.sortby_rating;

    private static final int NUM_LIST_ITEMS = 20;
    private MovieAdapter mAdapter;
    private RecyclerView mMoviePosters;
    String popularity = "popular";

    ArrayList<String> posterPathList;
    ArrayList<String> imageURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMoviePosters = (RecyclerView) findViewById(R.id.movie_posters);
        Context mContext = getApplicationContext();

        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);
        mMoviePosters.setLayoutManager(layoutManager);
        mMoviePosters.setHasFixedSize(true);
        mAdapter = new MovieAdapter(NUM_LIST_ITEMS, this.);
        mMoviePosters.setAdapter(mAdapter);

        URL getUrl = NetworkUtils.BuildUrl(popularity);

        new GetMovieData().execute(getUrl);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String rated = "top_rated";



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



            try {

               if (moviedbResults != null) {

                   JSONObject moviedb_json = new JSONObject(moviedbResults);
                   JSONArray results = moviedb_json.getJSONArray("results");

                   for (int i = 0; i < results.length(); i++) {
                       JSONObject p = results.getJSONObject(i);

                       String poster = p.getString("poster_path");
                       posterPathList.add(poster);
                   }

               }


            } catch (JSONException e) {

                e.printStackTrace();
            }

                      for (int i; i < posterPathList.size(); ++i ) {

                          URL imagePath;
                          imagePath = NetworkUtils.BuildImageUrl(posterPathList.get(i));
                          imageURL.add(imagePath.toString());

                      }



        }


        @Override
        protected void onPostExecute(String s) {




        }
    }




}