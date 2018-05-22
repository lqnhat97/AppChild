package com.ntt.tainguyen197.childapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;

/**
 * Created by NGUYENTRUNGTAI on 5/5/2018.
 */

public class UploadToServerTask  extends AsyncTask<Void, Void, String> {
    //URL để tải hình lên server
    private String URL = "http://192.168.1.8:8000/androidwebservice/index2.php";
    private Context context=null;
    private ProgressDialog progressDialog=null;
    private String ba1;
    public UploadToServerTask(Context context, String ba1)
    {
        this.context=context;
        this.ba1=ba1;
        //this.progressDialog=new ProgressDialog(this.context);
    }
    protected void onPreExecute() {
        super.onPreExecute();
        /*this.progressDialog.setMessage("Vui lòng chờ hệ thống đang upload hình!");
        this.progressDialog.show();*/
    }

    @Override
    protected String doInBackground(Void... params) {
        //Coding gửi hình lên Server
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("base64", ba1));
        nameValuePairs.add(new BasicNameValuePair("ImageName", System.currentTimeMillis() + ".jpg"));
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(URL);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            String st = EntityUtils.toString(response.getEntity());

        } catch (Exception e) {
        }
        return "Thành công";
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        /*this.progressDialog.hide();
        this.progressDialog.dismiss();*/
    }
}
