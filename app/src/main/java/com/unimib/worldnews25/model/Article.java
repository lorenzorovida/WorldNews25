package com.unimib.worldnews25.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Article implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public long uid;

    @Embedded(prefix = "source_")
    private ArticleSource source;

    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    private boolean like;



    public Article(ArticleSource source, String author, String title, String description,
                   String url, String urlToImage, String publishedAt, String content, boolean like) {
        setSource(source);
        setAuthor(author);
        setTitle(title);
        setDescription(description);
        setUrl(url);
        setUrlToImage(urlToImage);
        setPublishedAt(publishedAt);
        setContent(content);
        setLike(like);
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getUid() {
        return uid;
    }

    public boolean getLike() { return like; }

    public ArticleSource getSource() {
        return source;
    }

    public void setSource(ArticleSource source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        boolean e = Objects.equals(source, article.source) && Objects.equals(author, article.author) && Objects.equals(title, article.title) && Objects.equals(description, article.description) && Objects.equals(url, article.url) && Objects.equals(urlToImage, article.urlToImage) && Objects.equals(publishedAt, article.publishedAt) && Objects.equals(content, article.content);
        return e;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, author, title, description, url, urlToImage, publishedAt, content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeLong(this.uid);
        parcel.writeString(this.author);
        parcel.writeString(this.title);
        parcel.writeParcelable(this.source, i);
        parcel.writeString(this.description);
        parcel.writeString(this.url);
        parcel.writeString(this.urlToImage);
        parcel.writeString(this.publishedAt);
        parcel.writeString(this.content);
        parcel.writeByte(this.like ? (byte) 1 : (byte) 0);
    }


    public void readFromParcel(Parcel source) {
        this.uid = source.readLong();
        this.author = source.readString();
        this.title = source.readString();
        this.source = source.readParcelable(ArticleSource.class.getClassLoader());
        this.description = source.readString();
        this.url = source.readString();
        this.urlToImage = source.readString();
        this.publishedAt = source.readString();
        this.content = source.readString();
        this.like = source.readByte() != 0;
    }

    protected Article(Parcel in) {
        this.uid = in.readLong();
        this.author = in.readString();
        this.title = in.readString();
        this.source = in.readParcelable(ArticleSource.class.getClassLoader());
        this.description = in.readString();
        this.url = in.readString();
        this.urlToImage = in.readString();
        this.publishedAt = in.readString();
        this.content = in.readString();
        this.like = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
