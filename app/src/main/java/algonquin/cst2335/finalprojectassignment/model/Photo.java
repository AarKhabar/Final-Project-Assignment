
package algonquin.cst2335.finalprojectassignment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Photo implements Parcelable {

    @SerializedName("avg_color")
    private String mAvgColor;
    @SerializedName("height")
    private Long mHeight;
    @SerializedName("id")
    private Long mId;
    @SerializedName("liked")
    private Boolean mLiked;
    @SerializedName("photographer")
    private String mPhotographer;
    @SerializedName("photographer_id")
    private Long mPhotographerId;
    @SerializedName("photographer_url")
    private String mPhotographerUrl;
    @SerializedName("src")
    private Src mSrc;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("width")
    private Long mWidth;

    protected Photo(Parcel in) {
        mAvgColor = in.readString();
        if (in.readByte() == 0) {
            mHeight = null;
        } else {
            mHeight = in.readLong();
        }
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readLong();
        }
        byte tmpMLiked = in.readByte();
        mLiked = tmpMLiked == 0 ? null : tmpMLiked == 1;
        mPhotographer = in.readString();
        if (in.readByte() == 0) {
            mPhotographerId = null;
        } else {
            mPhotographerId = in.readLong();
        }
        mPhotographerUrl = in.readString();
        mUrl = in.readString();
        if (in.readByte() == 0) {
            mWidth = null;
        } else {
            mWidth = in.readLong();
        }
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public String getAvgColor() {
        return mAvgColor;
    }

    public void setAvgColor(String avgColor) {
        mAvgColor = avgColor;
    }

    public Long getHeight() {
        return mHeight;
    }

    public void setHeight(Long height) {
        mHeight = height;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Boolean getLiked() {
        return mLiked;
    }

    public void setLiked(Boolean liked) {
        mLiked = liked;
    }

    public String getPhotographer() {
        return mPhotographer;
    }

    public void setPhotographer(String photographer) {
        mPhotographer = photographer;
    }

    public Long getPhotographerId() {
        return mPhotographerId;
    }

    public void setPhotographerId(Long photographerId) {
        mPhotographerId = photographerId;
    }

    public String getPhotographerUrl() {
        return mPhotographerUrl;
    }

    public void setPhotographerUrl(String photographerUrl) {
        mPhotographerUrl = photographerUrl;
    }

    public Src getSrc() {
        return mSrc;
    }

    public void setSrc(Src src) {
        mSrc = src;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public Long getWidth() {
        return mWidth;
    }

    public void setWidth(Long width) {
        mWidth = width;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mAvgColor);
        if (mHeight == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(mHeight);
        }
        if (mId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(mId);
        }
        parcel.writeByte((byte) (mLiked == null ? 0 : mLiked ? 1 : 2));
        parcel.writeString(mPhotographer);
        if (mPhotographerId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(mPhotographerId);
        }
        parcel.writeString(mPhotographerUrl);
        parcel.writeString(mUrl);
        if (mWidth == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(mWidth);
        }
    }
}
