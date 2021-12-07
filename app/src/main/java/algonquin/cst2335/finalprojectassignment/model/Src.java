
package algonquin.cst2335.finalprojectassignment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Src implements Parcelable {

    @SerializedName("landscape")
    private String mLandscape;
    @SerializedName("large")
    private String mLarge;
    @SerializedName("large2x")
    private String mLarge2x;
    @SerializedName("medium")
    private String mMedium;
    @SerializedName("original")
    private String mOriginal;
    @SerializedName("portrait")
    private String mPortrait;
    @SerializedName("small")
    private String mSmall;
    @SerializedName("tiny")
    private String mTiny;

    protected Src(Parcel in) {
        mLandscape = in.readString();
        mLarge = in.readString();
        mLarge2x = in.readString();
        mMedium = in.readString();
        mOriginal = in.readString();
        mPortrait = in.readString();
        mSmall = in.readString();
        mTiny = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mLandscape);
        dest.writeString(mLarge);
        dest.writeString(mLarge2x);
        dest.writeString(mMedium);
        dest.writeString(mOriginal);
        dest.writeString(mPortrait);
        dest.writeString(mSmall);
        dest.writeString(mTiny);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Src> CREATOR = new Creator<Src>() {
        @Override
        public Src createFromParcel(Parcel in) {
            return new Src(in);
        }

        @Override
        public Src[] newArray(int size) {
            return new Src[size];
        }
    };

    public String getLandscape() {
        return mLandscape;
    }

    public void setLandscape(String landscape) {
        mLandscape = landscape;
    }

    public String getLarge() {
        return mLarge;
    }

    public void setLarge(String large) {
        mLarge = large;
    }

    public String getLarge2x() {
        return mLarge2x;
    }

    public void setLarge2x(String large2x) {
        mLarge2x = large2x;
    }

    public String getMedium() {
        return mMedium;
    }

    public void setMedium(String medium) {
        mMedium = medium;
    }

    public String getOriginal() {
        return mOriginal;
    }

    public void setOriginal(String original) {
        mOriginal = original;
    }

    public String getPortrait() {
        return mPortrait;
    }

    public void setPortrait(String portrait) {
        mPortrait = portrait;
    }

    public String getSmall() {
        return mSmall;
    }

    public void setSmall(String small) {
        mSmall = small;
    }

    public String getTiny() {
        return mTiny;
    }

    public void setTiny(String tiny) {
        mTiny = tiny;
    }

}
