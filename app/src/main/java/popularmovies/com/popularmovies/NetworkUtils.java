package popularmovies.com.popularmovies;

import android.net.Uri;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.Scanner;

/**
 * Created by yonathanseleshi on 1/3/17.
 */

public class NetworkUtils {

    final static String Moviedb_URL = "https://api.themoviedb.org/3/movie/";
    final static String QUERY = "?";
    final static String API_KEY = Keys.API_KEY;
    final static String Language = "&language=en";
    final static String Page = "&page=1";

    final static String BaseImageURL = "http://image.tmdb.org/t/p/";
    final static String ImageSize185 = "w185";



    public static URL BuildUrl(String sort_by) {
        Uri builtUri = Uri.parse(Moviedb_URL).buildUpon()
                .appendPath(sort_by)
                .appendPath(QUERY)
                .appendPath(API_KEY)
                .appendPath(Language)
                .appendPath(Page)
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ;

        return url;
    }

    public static URL BuildImageUrl(String imageURL){


     Uri builtImageUri = Uri.parse(BaseImageURL).buildUpon()
             .appendPath(ImageSize185)
             .appendPath(imageURL)
             .build();


    }



    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;

            }


        } finally{
            urlConnection.disconnect();


        }

    }
}
