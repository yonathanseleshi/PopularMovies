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


    final private MovieItemClickListener mOnClickListener;
    private ArrayList<String> mURLS;
    private Context mContext;


    public interface MovieItemClickListener {
        void onListItemClick(int clickedItemIndex);





    }



    private int mNumberItems;



    public MovieAdapter(int numberOfItems, Context context, MovieItemClickListener listener,  ArrayList<String> images){

        mNumberItems = numberOfItems;
        mOnClickListener = listener;
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

        ImageView movieItemView;
        movieItemView = holder.movieItemView;



            Picasso.with(mContext).load(mURLS.get(position)).into(movieItemView);


    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }



    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView movieItemView;

        public MovieViewHolder(View itemView) {
            super(itemView);

             movieItemView = (ImageView) itemView.findViewById(R.id.movie_item);

            itemView.setOnClickListener(this);



        }

        @Override
        public void onClick(View view) {

            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);


        }

        void bind(int view){



        }





    }


}
