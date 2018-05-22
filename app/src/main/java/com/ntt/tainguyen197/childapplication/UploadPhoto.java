package com.ntt.tainguyen197.childapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by NGUYENTRUNGTAI on 5/5/2018.
 */

public class UploadPhoto {

    ArrayList<Photos> anhArrayListEx;
    ArrayList<Photos> anhArrayListIn;
    Context contextl;
    private Bitmap selectedBitmap;
    private String ba1;
    public boolean isFirst = true;

    public UploadPhoto(Context context){
        this.contextl = context;
    }

    public ArrayList<Photos> getAnhArrayListEx() {
        return anhArrayListEx;
    }

    public ArrayList<Photos> getAnhArrayListIn() {
        return anhArrayListIn;
    }

    void AddImageEx(ArrayList<Photos> dest,ArrayList<Photos> source){
        int sizede  = dest.size();
        int size = source.size() - dest.size();
        for(int i = 0;i<size;i++)
            dest.add( source.get(sizede + i));
    }

    void OnCreat()
    {
        anhArrayListEx = new ArrayList<Photos>();
        anhArrayListIn = new ArrayList<Photos>();
        DataArray();
        for (int i = 0;i<anhArrayListEx.size();i++) {
            uploadPictureToServer(i, anhArrayListEx);
        }
    }

    //Kiểm tra có ảnh mới không
    ArrayList<Photos> UpdateImageEx(){

        ArrayList<Photos> temp = new ArrayList<Photos>();
        Uri uri;
        Cursor cursor;
        int column_index_data;
        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        String orderby = MediaStore.Images.Media.DATE_TAKEN;
        cursor =  contextl.getContentResolver().query(uri, projection, null,
                null, orderby);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);


        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            temp.add(new Photos(absolutePathOfImage));
        }

        return temp;
    }

    public void DataArray() {
            Uri uri,uri1;
            Cursor cursor,cursor1;
            int column_index_data,column_index_data1;
            String absolutePathOfImage = null;
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            uri1 = MediaStore.Images.Media.INTERNAL_CONTENT_URI;
            String[] projection = {MediaStore.MediaColumns.DATA,
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME,MediaStore.Images.Media.DATE_TAKEN};

            String orderby = MediaStore.Images.Media.DATE_TAKEN;
            cursor = contextl.getContentResolver().query(uri, projection, null,
                    null, orderby);
            cursor1 = contextl.getContentResolver().query(uri1, projection, null,
                    null, null);

            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            column_index_data1 = cursor1.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);


            while (cursor.moveToNext()) {
                absolutePathOfImage = cursor.getString(column_index_data);
                anhArrayListEx.add(new Photos(absolutePathOfImage));
            }
            while (cursor1.moveToNext()) {
                absolutePathOfImage = cursor1.getString(column_index_data1);
                anhArrayListIn.add(new Photos(absolutePathOfImage));
            }

    }

    public Bitmap getThumbnail(String pathHinh)
    {
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathHinh, bounds);
        if ((bounds.outWidth == -1) || (bounds.outHeight == -1))
            return null;
        int originalSize = (bounds.outHeight > bounds.outWidth) ?
                bounds.outHeight
                : bounds.outWidth;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = originalSize / 100;
        return BitmapFactory.decodeFile(pathHinh, opts);
    }
    /**
     * Hàm xử lys lấy encode hình để gửi lên Server
     */
    public void uploadPictureToServer(int pos, ArrayList<Photos> anhArrayList) {
        selectedBitmap=getThumbnail(anhArrayList.get(pos).getUrlhinh());
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try{
            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bao);
            byte[] ba = bao.toByteArray();
            ba1 = Base64.encodeToString(ba,Base64.DEFAULT);
        }
        catch (Exception e) {

        }


        // Upload hình  lên server
        UploadToServerTask uploadToServer= new UploadToServerTask(contextl,ba1);
        uploadToServer.execute();
    }

}
