package com.ntt.tainguyen197.childapplication;

import android.graphics.Bitmap;

public class Contact {
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private String SDT;
    private String Ten;

    public String getProfilestring() {
        return profilestring;
    }

    public void setProfilestring(String profilestring) {
        this.profilestring = profilestring;
    }

    private String profilestring;

    public Bitmap getProfile() {
        return profile;
    }

    public void setProfile(Bitmap profile) {
        this.profile = profile;
    }

    private Bitmap profile;

    public Contact(int ID, String ten,String SDT,String profile) {
        this.ID = ID;
        this.SDT = SDT;
        this.Ten = ten;
        this.profilestring = profile;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }
}
