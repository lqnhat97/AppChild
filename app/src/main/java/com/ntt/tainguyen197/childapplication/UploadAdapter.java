package com.ntt.tainguyen197.childapplication;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class UploadAdapter extends Thread{
    private int counter=0;
    private static Context context;
    private UploadPhoto mUploadPhotos;

    public UploadAdapter(Context ct){
        context = ct;
    }

    public void Init(){
        mUploadPhotos = new UploadPhoto(context);
        mUploadPhotos.OnCreat();
    }

}
