package com.ntt.tainguyen197.childapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NGUYENTRUNGTAI on 5/5/2018.
 */

public class Photos {
    String urlhinh;

    public Photos(String urlhinh) {
        this.urlhinh = urlhinh;
    }

    public String getUrlhinh() {
        return urlhinh;
    }

    public void setUrlhinh(String urlhinh) {
        this.urlhinh = urlhinh;
    }

    /*@Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int arg1) {
        // TODO Auto-generated method stub
        dest.writeString(urlhinh);
    }*/

    public Photos(Parcel in) {
        urlhinh = in.readString();
    }

    public static final Parcelable.Creator<Photos> CREATOR = new Parcelable.Creator<Photos>() {
        public Photos createFromParcel(Parcel in) {
            return new Photos(in);
        }

        public Photos[] newArray(int size) {
            return new Photos[size];
        }
    };
}
