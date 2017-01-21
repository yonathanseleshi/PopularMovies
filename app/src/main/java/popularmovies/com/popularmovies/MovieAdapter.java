package popularmovies.com.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by yonathanseleshi on 1/5/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {



    private ArrayList<String> mURLS;
    private Context mContext;





    private int mNumberItems;



    public MovieAdapter(int numberOfItems, Context context, ArrayList<String> images){

        mNumberItems = numberOfItems;

        mURLS = images;
        mContext = context;

    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_poster_itemview;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttatchToParentImmediately = false;


        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttatchToParentImmediately);
        MovieViewHolder viewHolder = new MovieViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(position);





            Picasso.with(mContext).load(mURLS.get(position)).into(holder.movieItemView);


    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }



    class MovieViewHolder extends RecyclerView.ViewHolder  {
        ImageView movieItemView;

        public MovieViewHolder(View itemView) {
            super(itemView);

             movieItemView = (ImageView) itemView.findViewById(R.id.movie_item);





        }



        void bind(int view){



        }





    }


}
