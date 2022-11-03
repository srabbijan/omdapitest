package com.android.omdapitest.Network;

import com.android.volley.VolleyError;

public interface ApiServices {
    public void setJsonDataResponse(String response);
    public void setVolleyError(VolleyError volleyError);
}
