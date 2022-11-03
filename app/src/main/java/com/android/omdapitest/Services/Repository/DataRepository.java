package com.android.omdapitest.Services.Repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.android.omdapitest.CommonFolder.LodingProgress;
import com.android.omdapitest.Network.ApiServices;
import com.android.omdapitest.Network.DataManger;
import com.android.omdapitest.Services.Model.Data_Model;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {
    private DataManger dataManger;
    private static DataRepository mLifeDataRepository;
    private static Context mContext;

    public static DataRepository getmLifeDataRepository(Context context) {
        if (mLifeDataRepository == null) {
            mLifeDataRepository = new DataRepository();
            mContext = context;
        }
        return mLifeDataRepository;
    }
    public MutableLiveData<List<Data_Model>> GetMovieList(String url) {
        Log.d("TAGGG", url);
        List<Data_Model> local = new ArrayList<>();
        MutableLiveData<List<Data_Model>> global = new MutableLiveData<>();
        dataManger = new DataManger(mContext);
        dataManger.GETRequest(mContext, new ApiServices() {
            @Override
            public void setJsonDataResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    String ResponseSucess = object.optString("Response");
                    if (ResponseSucess.equals("False")){
                        Log.d("GETAP", response);
                        String mes = object.optString("Error");
                        Toast.makeText(mContext, ""+mes, Toast.LENGTH_SHORT).show();
                        LodingProgress.hideLoadingDialog();
                        return;
                    }
                    JSONArray results = object.getJSONArray("Search");
                    for (int i=0;i<results.length();i++){
                        JSONObject data = results.getJSONObject(i);
                        String Title = data.optString("Title");
                        String Year = data.optString("Year");
                        String imdbID = data.optString("imdbID");
                        String Poster = data.optString("Poster");

                        Data_Model info = new Data_Model();
                        info.setImdbID(imdbID);
                        info.setTitle(Title);
                        info.setYear(Year);
                        info.setPoster(Poster);
                        local.add(info);

                    }
                    global.setValue(local);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("GETAP", e.getMessage().toString());
                }
            }

            @Override
            public void setVolleyError(VolleyError error) {
                final int statusCode = error.networkResponse.statusCode;
                Log.d("GETAPI", statusCode + "");
                final String  body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                Log.d("GETAPI", body + "");
                if (statusCode==401){
                    try {
                        JSONObject object = new JSONObject(body);
                        String mes = object.optString("Error");
                        Toast.makeText(mContext, ""+mes, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, url);
        return global;
    }
}
