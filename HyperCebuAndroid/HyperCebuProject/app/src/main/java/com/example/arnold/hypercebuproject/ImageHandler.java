package com.example.arnold.hypercebuproject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Arnold on 5/17/2017.
 */

public class ImageHandler {

    private static ImageHandler mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private ImageHandler(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized ImageHandler getmInstance(Context context){
        if(mInstance == null){
            mInstance = new ImageHandler(context);
        }
        return mInstance;
    }

    public <T> void addToRequestQue(Request<T> request){
        requestQueue.add(request);
    }
}
