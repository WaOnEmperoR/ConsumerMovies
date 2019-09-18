package id.govca.consumermovies;

import android.database.Cursor;

import java.util.ArrayList;

import id.govca.consumermovies.entity.Favorite;

public class MappingHelper {

    public static ArrayList<Favorite> mapCursorToArrayList(Cursor favoritesCursor)
    {
        ArrayList<Favorite> favoriteArrayList = new ArrayList<>();

        while (favoritesCursor.moveToNext()){
            Favorite favorite = new Favorite();

            favorite.setFavId(favoritesCursor.getInt(favoritesCursor.getColumnIndexOrThrow("favId")));
            favorite.setThingsId(favoritesCursor.getInt(favoritesCursor.getColumnIndexOrThrow("thingsId")));
            favorite.setDate_available(favoritesCursor.getString(favoritesCursor.getColumnIndexOrThrow("date_available")));
            favorite.setTitle(favoritesCursor.getString(favoritesCursor.getColumnIndexOrThrow("title")));
            favorite.setType(favoritesCursor.getInt(favoritesCursor.getColumnIndexOrThrow("type")));
            favorite.setHomepage(favoritesCursor.getString(favoritesCursor.getColumnIndexOrThrow("homepage")));
            favorite.setPoster_path(favoritesCursor.getString(favoritesCursor.getColumnIndexOrThrow("poster_path")));
            favorite.setVote_average(favoritesCursor.getDouble(favoritesCursor.getColumnIndexOrThrow("vote_average")));

            favoriteArrayList.add(favorite);
        }
        return favoriteArrayList;
    }
}
