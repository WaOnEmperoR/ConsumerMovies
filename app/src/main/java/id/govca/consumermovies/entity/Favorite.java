package id.govca.consumermovies.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Favorite implements Parcelable {
    private int favId;
    private int thingsId;
    private String title;
    private int type;
    private String date_available;
    private double vote_average;
    private String poster_path;
    private String homepage;
    private String synopsis;

    public int getFavId() {
        return favId;
    }

    public void setFavId(int favId) {
        this.favId = favId;
    }

    public int getThingsId() {
        return thingsId;
    }

    public void setThingsId(int thingsId) {
        this.thingsId = thingsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate_available() {
        return date_available;
    }

    public void setDate_available(String date_available) {
        this.date_available = date_available;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.favId);
        dest.writeInt(this.thingsId);
        dest.writeString(this.title);
        dest.writeInt(this.type);
        dest.writeString(this.date_available);
        dest.writeDouble(this.vote_average);
        dest.writeString(this.poster_path);
        dest.writeString(this.homepage);
        dest.writeString(this.synopsis);
    }

    public Favorite() {
    }

    protected Favorite(Parcel in) {
        this.favId = in.readInt();
        this.thingsId = in.readInt();
        this.title = in.readString();
        this.type = in.readInt();
        this.date_available = in.readString();
        this.vote_average = in.readDouble();
        this.poster_path = in.readString();
        this.homepage = in.readString();
        this.synopsis = in.readString();
    }

    public static final Parcelable.Creator<Favorite> CREATOR = new Parcelable.Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel source) {
            return new Favorite(source);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };
}
