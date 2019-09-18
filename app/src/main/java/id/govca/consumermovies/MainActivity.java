package id.govca.consumermovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import id.govca.consumermovies.adapter.ListFavoriteAdapter;
import id.govca.consumermovies.entity.Favorite;

import static id.govca.consumermovies.MappingHelper.mapCursorToArrayList;

public class MainActivity extends AppCompatActivity implements LoadFavoriteCallback {

    private ListFavoriteAdapter favoriteAdapter;
    private DataObserver myObserver;

    // Base content yang digunakan untuk akses content provider
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority("id.govca.recyclerviewapi")
            .appendPath("favorite")
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Consumer Movies");

        RecyclerView rvFavorites = findViewById(R.id.recyclerView_favorite_movie);
        favoriteAdapter = new ListFavoriteAdapter(this);
        rvFavorites.setLayoutManager(new LinearLayoutManager(this));
        rvFavorites.setHasFixedSize(true);
        rvFavorites.setAdapter(favoriteAdapter);

        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        myObserver = new DataObserver(handler, this);
        getContentResolver().registerContentObserver(CONTENT_URI, true, myObserver);
        new getData(this, this).execute();
    }

    @Override
    public void postExecute(Cursor favorites) {
        ArrayList<Favorite> favoriteArrayList = mapCursorToArrayList(favorites);
        if (favoriteArrayList.size()>0)
        {
            favoriteAdapter.setData(favoriteArrayList);
        }
        else
        {
            Toast.makeText(this, "Tidak Ada data saat ini", Toast.LENGTH_SHORT).show();
            favoriteAdapter.setData(new ArrayList<Favorite>());
        }
    }

    private static class getData extends AsyncTask<Void, Void, Cursor>{
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadFavoriteCallback> weakCallback;

        private getData(Context context, LoadFavoriteCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return weakContext.get().getContentResolver().query(CONTENT_URI, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            weakCallback.get().postExecute(cursor);
        }
    }

    static class DataObserver extends ContentObserver {

        final Context context;

        DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new getData(context, (MainActivity) context).execute();
        }
    }
}
