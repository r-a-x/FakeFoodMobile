package io.mauth.rahulb.fakefood10.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.opengl.GLException;
import android.provider.Settings;
import android.util.Base64;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;

import io.mauth.rahulb.fakefood10.model.Product;

import static io.mauth.rahulb.fakefood10.util.UnixEpochDateTypeAdapter.getUnixEpochDateTypeAdapter;

/**
 * Created by rahulb on 7/11/17.
 */

public final class Util {

    public static Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public static Date getDate(String epoch){
        Long seconds = Long.parseLong(epoch);
        return new Date(seconds*1000L);
    }

    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static String toBase64(byte[]  bytes){
        return  new String (Base64.encode(bytes,0) );
    }

    public static String getJson(Object object,Class cl){

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        return gson.toJson(object,cl);

    }

    public static Bitmap base64ToBitmap(String base64){

        if ( base64 == null)
            return null;
        byte[] byteArray = Base64.decode(base64,0);
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bmp;

    }

    public static <T> List<T> getListFromJSON(T t,String json ){


        TypeToken<List<T>> token = new TypeToken<List<T>>() {};
        List<T> lists = gson.fromJson(json.toString(), token.getType());

        return lists;

    }


    public static String androidId(Activity activity){
        return Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static File convertBitmapToFile(Bitmap bitmap, Context context)  {
        File outputDir = context.getCacheDir(); // context being the Activity pointer
        File outputFile = null;
        try {

            outputFile = File.createTempFile(getRandomName(), "tmp", outputDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(outputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        try {
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile;
    }

    public static String getRandomName(){
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr+".jpg";
    }
}

