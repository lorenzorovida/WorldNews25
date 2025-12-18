package com.unimib.worldnews25.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class ArticleSource implements Parcelable {
    private String id;
    private String name;

    public ArticleSource(String id, String name) {
        setId(id);
        setName(name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ArticleSource that = (ArticleSource) o;
        boolean e = Objects.equals(id, that.id) && Objects.equals(name, that.name);
        return e;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public void readFromParcel(Parcel source) {
        this.name = source.readString();
    }

    protected ArticleSource(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<ArticleSource> CREATOR = new Parcelable.Creator<ArticleSource>() {
        @Override
        public ArticleSource createFromParcel(Parcel source) {
            return new ArticleSource(source);
        }

        @Override
        public ArticleSource[] newArray(int size) {
            return new ArticleSource[size];
        }
    };
}
