package com.ntt.tainguyen197.childapplication;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Intent mServiceIntent;
    private UploadAdapter mUploadAdapter;
    private UploadPhoto mUploadPhotos;
    private UploadContact muploadContact;
    ArrayList<Contact> ct;
    Context ctx;
    String url = "http://192.168.1.8:8000/androidwebservice/getdata.php";
    public Context getCtx() {
        return ctx;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        ct = new ArrayList<Contact>();
        setContentView(R.layout.activity_main);

        /*if (GETREADEXTERNALSTORAGE()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    UploadPhotos();
                }
            }).start();
        }*/

        /*if(GetReadContact()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    UploadContact();
                }
            }).start();
        }*/

        //ReadJSON(url);
        UploadContact();
    }
    void UploadPhotos(){
        mUploadPhotos = new UploadPhoto(ctx);
        mUploadPhotos.OnCreat();
    }

    void UploadContact(){
        muploadContact = new UploadContact(ctx);
        muploadContact.OnCreat();
    }

    private void ReadJSON(String url){


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String ten = null,sdt,anh = null;
                int id;
                for(int i = 0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        //Toast.makeText(ctx, object.toString(), Toast.LENGTH_SHORT).show();
                        id = object.getInt("ID");
                        //Toast.makeText(ctx, Integer.toString(id), Toast.LENGTH_SHORT).show();
                        ten = object.getString("Ten");
                        //Toast.makeText(ctx, ten, Toast.LENGTH_SHORT).show();
                        sdt = object.getString("SDT");
                        //Toast.makeText(ctx, sdt, Toast.LENGTH_SHORT).show();
                        anh = object.getString("HinhAnh");
                        //Toast.makeText(ctx, anh, Toast.LENGTH_SHORT).show();
                        ct.add(new Contact(id,ten,sdt,anh));

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(ctx, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                }

                Toast.makeText(ctx, Integer.toString(ct.size()), Toast.LENGTH_SHORT).show();

                for(int j= 0;j<ct.size();j++) {
                    Log.i("Image", ten);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(arrayRequest);
    }

    public boolean GETREADCONTACT() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS}, 3);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callbac\k method gets the
                // result of the request.
            }
            return false;
        }
        else
            return true;
    }

    public boolean GetReadContact(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS}, 3);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callbac\k method gets the
                // result of the request.
            }
            return false;
        }
        else return true;
    }

    public boolean GETREADEXTERNALSTORAGE(){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},4);
            }
            return false;
        }
        else
            return true;
    }

}
