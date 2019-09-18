package id.govca.consumermovies.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.govca.consumermovies.Constants;
import id.govca.consumermovies.R;
import id.govca.consumermovies.entity.Favorite;

public class ListFavoriteAdapter extends RecyclerView.Adapter<ListFavoriteAdapter.ListViewHolder> {
    private ArrayList<Favorite> listFavorite = new ArrayList<>();
    private final String TAG = this.getClass().getSimpleName();
    private final Activity activity;

    private ListFavoriteAdapter.OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(ListFavoriteAdapter.OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListFavoriteAdapter(Activity activity){
        this.activity = activity;
    }

    public void setData(ArrayList<Favorite> items) {
        listFavorite.clear();
        listFavorite.addAll(items);
        notifyDataSetChanged();
    }

    public interface OnItemClickCallback {
        void onItemClicked(Favorite data);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new ListFavoriteAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        final Favorite favorite = listFavorite.get(position);

        Glide
                .with(holder.itemView.getContext())
                .load(Constants.IMAGE_ROOT_SMALL + favorite.getPoster_path())
                .into(holder.img_poster);

        holder.tv_movie_name.setText(favorite.getTitle());
        holder.tv_year.setText(favorite.getDate_available());
        holder.tv_rating.setText(String.valueOf(favorite.getVote_average()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listFavorite.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFavorite.size();
    }


    public class ListViewHolder extends RecyclerView.ViewHolder{

        ImageView img_poster;
        TextView tv_rating, tv_year, tv_movie_name;
        Button btnDelete;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            img_poster = itemView.findViewById(R.id.imgView_poster);
            tv_rating = itemView.findViewById(R.id.tv_show_rating);
            tv_year = itemView.findViewById(R.id.tv_show_year);
            tv_movie_name = itemView.findViewById(R.id.tv_movie_title);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
