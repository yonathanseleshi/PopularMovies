package popularmovies.com.popularmovies;

import android.net.Uri;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.util.Log;
import java.net.ProtocolException;

import java.util.Scanner;

/**
 * Created by yonathanseleshi on 1/3/17.
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    final static String Moviedb_URL = "api.themoviedb.org/3/movie/";
    final static String QUERY = "?";
    final static String API_KEY = Keys.API_KEY;
    final static String Language = "&language=en-US";
    final static String Page = "&page=1";

    final static String BaseImageURL = "http://image.tmdb.org/t/p/";
    final static String ImageSize185 = "w185";



    public static URL BuildUrl(String sort_by) {
        /* Uri builtUri = Uri.parse(Moviedb_URL).buildUpon()
                .appendPath(sort_by)
                .appendPath(QUERY)
                .appendPath(API_KEY)
                .appendPath(Language)
                .appendPath(Page)
                .build(); */

        Uri.Builder builtUri = new Uri.Builder();
        builtUri.scheme("https")
                .authority(Moviedb_URL)
                .appendPath(sort_by)
                .appendPath(QUERY)
                .appendPath(API_KEY)
                .appendPath(Language)
                .appendPath(Page);



        URL url = null;

        try {
           url = new URL(builtUri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL BuildImageUrl(String imageURL){


     /* Uri builtImageUri = Uri.parse(BaseImageURL).buildUpon()
             .appendPath(ImageSize185)
             .appendPath(imageURL)
             .build(); */

        Uri.Builder builtImageUri = new Uri.Builder();
        builtImageUri.scheme("https")
                .authority(BaseImageURL)
                .appendPath(ImageSize185)
                .appendPath(imageURL);


        URL url = null;

        try {
            url = new URL(builtImageUri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ;

        return url;

    }



    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String response = null;

        try {



            urlConnection.setRequestMethod("GET");
            InputStream in =  new BufferedInputStream(urlConnection.getInputStream());
            response = convertStreamToString(in);

            //Scanner scanner = new Scanner(in);
            //scanner.useDelimiter("\\A");

            /*
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;

            }*/
        /*
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
         */
        return response;

        } finally{
            urlConnection.disconnect();


        }



    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }



}
