package popularmovies.com.popularmovies;

import android.content.ClipData;
import android.content.Context;
import android.net.sip.SipAudioCall;
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
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public int popular = R.id.sortby_popularity;
    public int rating = R.id.sortby_rating;

    private static final int NUM_LIST_ITEMS = 20;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView mMoviePosters;
    String popularity = "popular";
    private RecyclerView.LayoutManager layoutManager;
    public static Context mContext;


    ArrayList<String> posterPathList;
    ArrayList<String> imageURL;
    ArrayList<HashMap<String, String>> movieDetails;

    ArrayList<String> moviedb_imageURLs;
    ArrayList<HashMap<String, String>> moviedb_movieDetails;
    String moviedbQueryResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMoviePosters = (RecyclerView) findViewById(R.id.movie_posters);
        mContext = getApplicationContext();





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




    private ArrayList<HashMap<String, String>> getMovieDetails(String moviedbData) {


            try

    {

        if (moviedbData != null) {

            JSONObject moviedb_json = new JSONObject(moviedbData);
            JSONArray results = moviedb_json.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject p = results.getJSONObject(i);

                String poster = p.getString("poster_path");
                posterPathList.add(poster);
            }

            for (int i = 0; i < results.length(); i++) {
                JSONObject p = results.getJSONObject(i);

                String poster = p.getString("poster_path");
                String title = p.getString("original_title");
                String rating = p.getString("vote_average");
                String release = p.getString("release_date");
                String overview = p.getString("overview");
                HashMap<String, String> movieDetail = new HashMap<>();
                movieDetail.put("poster", poster);
                movieDetail.put("title", title);
                movieDetail.put("rating", rating);
                movieDetail.put("release", release);
                movieDetail.put("overview", overview);
                movieDetails.add(movieDetail);
            }


        }


         } catch(
           JSONException e)

        {

        e.printStackTrace();
        }
             return movieDetails;
       }




       public ArrayList<String> preparePosterURLS(String moviedbData) {



           try

           {

               if (moviedbData != null) {

                   JSONObject moviedb_json = new JSONObject(moviedbData);
           JSONArray results = moviedb_json.getJSONArray("results");

           for (int i = 0; i < results.length(); i++) {
               JSONObject p = results.getJSONObject(i);

               String poster = p.getString("poster_path");
               posterPathList.add(poster);
           }

              for (int i = 0; i < posterPathList.size(); ++i) {

            URL imagePath;
            imagePath = NetworkUtils.BuildImageUrl(posterPathList.get(i));
            imageURL.add(imagePath.toString());
              }


               }


           } catch(
                   JSONException e)

           {

               e.printStackTrace();
           }

        return imageURL;
    }




    public class GetMovieData extends AsyncTask<URL, Void, String>{


        @Override
        protected String doInBackground(URL... urls) {

            String moviedbResults = null;
            URL queryURL = urls[0];

            try {


                moviedbResults = NetworkUtils.getResponseFromHttpUrl(queryURL);

            } catch (IOException e) {

                e.printStackTrace();
            }

            String moviedbQueryResults = moviedbResults;

                  return moviedbQueryResults;
        }


        @Override
        protected void onPostExecute(String moviedbQueryData) {


          moviedbQueryResults = moviedbQueryData;

            moviedb_movieDetails = getMovieDetails(moviedbQueryResults);
            moviedb_imageURLs = preparePosterURLS(moviedbQueryResults);
            layoutManager = new GridLayoutManager(mContext, 3);
            mMoviePosters.setLayoutManager(layoutManager);
            mMoviePosters.setHasFixedSize(true);

            mAdapter = new MovieAdapter(NUM_LIST_ITEMS, mContext, moviedb_imageURLs);
            mMoviePosters.setAdapter(mAdapter);

        }
    }




}