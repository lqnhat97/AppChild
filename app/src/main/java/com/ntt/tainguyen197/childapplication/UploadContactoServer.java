package com.ntt.tainguyen197.childapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UploadContactoServer {
    //URL để tải hình lên server
    private String URL = "http://192.168.1.8:8000/androidwebservice/index2.php";
    private Context context=null;
    private ProgressDialog progressDialog=null;
    private String ba1;
    public UploadContactoServer(Context context, String ba1)
    {
        this.context=context;
        this.ba1=ba1;
        //this.progressDialog=new ProgressDialog(this.context);
    }
    /*private void ThemSinhVien(String url){
        RequestQueue rsthemsv = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("hotenSV",edtName.getText().toString());
                params.put("namsinhSV",edtYear.getText().toString());
                params.put("diachiSV",edtAddess.getText().toString());

                return params;
            }
        };

        rsthemsv.add(stringRequest);
    }*/
}
