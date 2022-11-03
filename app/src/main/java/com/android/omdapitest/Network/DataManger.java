package com.android.omdapitest.Network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.nio.charset.StandardCharsets;

public class DataManger {
    private final Context context;
    private RequestQueue mQueue;
    public DataManger(Context context) {
        this.context = context;
    }

    public void GETRequest(Context context, final ApiServices dataValues, String url) {
        mQueue = VolleySingleton.getInstance(context).getRequestQueue();

            StringRequest request = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//                            LodingProgress.hideLoadingDialog();
                            Log.d("GETAPI", response);
                            dataValues.setJsonDataResponse(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
//                            LodingProgress.hideLoadingDialog();
                            dataValues.setVolleyError(error);
                            if (error == null || error.networkResponse == null) {
                                return;
                            }

                            //get status code here
                            final String statusCode = String.valueOf(error.networkResponse.statusCode);
                            //get response body and parse with appropriate encoding
                            Log.d("GETAPI", statusCode + "");

                            final String  body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                            Log.d("GETAPI", body + "");
                            Toast.makeText(context, ""+statusCode+body, Toast.LENGTH_SHORT).show();

                        }
                    });

            mQueue.add(request);
        }

}
