package io.mauth.rahulb.fakefood10.core;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.mime.MultipartEntityBuilder;
import cz.msebera.android.httpclient.entity.mime.content.FileBody;


public class MultiPartRequest extends Request<String> {

    private Response.Listener<String> mListener;
    private HttpEntity mHttpEntity;

    public MultiPartRequest(Response.ErrorListener errorListener, Response.Listener listener, Map<String,File> file,String path) {
        super(Method.POST,path, errorListener);
        mListener = listener;
        mHttpEntity = buildMultipartEntity(file);

    }

    private HttpEntity buildMultipartEntity(Map<String,File> file) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        for (Map.Entry<String, File> entry : file.entrySet()) {
            FileBody fileBody = new FileBody(entry.getValue());
            builder.addPart(entry.getKey(),fileBody);
        }
        return builder.build();
    }




    @Override
    public String getBodyContentType() {
        return mHttpEntity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            mHttpEntity.writeTo(bos);
            return bos.toByteArray();
        } catch (IOException e) {
            VolleyLog.e("" + e);
            return null;
        } catch (OutOfMemoryError e){
            VolleyLog.e("" + e);
            return null;
        }

    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            return Response.success(new String(response.data, "UTF-8"),
                    getCacheEntry());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.success(new String(response.data),
                    getCacheEntry());
        }
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

}
