package popularmovies.com.popularmovies;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public int popular = (MenuItem) R.menu.menu.id.sortby_popularity;
    public int rating = (MenuItem) R.menu.menu.id.sortby_rating;

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

            try {

                moviedbResults = NetworkUtils.getResponseFromHttpUrl(getUrl);
            } catch (IOException e) {

                e.printStackTrace();
            }


            return true;
        } else if (itemSelected == popular) {

            try {

                URL getUrl = NetworkUtils.BuildUrl(popularity);
                moviedbResults = NetworkUtils.getResponseFromHttpUrl(getUrl);
            } catch (IOException e) {

                e.printStackTrace();
            }
            return super.onOptionsItemSelected(item);

        }

        return true;
    }

}