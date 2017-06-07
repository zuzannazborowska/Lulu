package zuza.lulu;

import com.android.volley.Request;
 import com.android.volley.Response;
 import com.android.volley.VolleyError;
 import com.android.volley.toolbox.StringRequest;

/**
 * Created by Chris on 07.06.2017.
 */

public class MyStringRequest {

    StringRequest stringRequest;

    public StringRequest getStringRequest() {
        return stringRequest;
    }

    public StringRequest zuza(String url) {
        stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });
        return stringRequest;

    }
}