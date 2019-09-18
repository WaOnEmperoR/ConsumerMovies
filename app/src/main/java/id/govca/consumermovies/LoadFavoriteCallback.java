package id.govca.consumermovies;

import android.database.Cursor;

public interface LoadFavoriteCallback {
    void postExecute(Cursor favorites);
}
