package com.example.alrem.Views;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private static PreferenceHelper minst;

    private final String User_ID = "user_id";
    private final String phone = "phone";
    private final String password = "password";
    private final String Lang = "lang";
    private final String DeviceToken = "firebase_token";

    private SharedPreferences app_prefs;
    private static Context mcontext;


    public static synchronized PreferenceHelper getInstans(Context context){
        if (minst==null){
            minst=new PreferenceHelper(context);
        }
        return minst;
    }

    public boolean logout(){
        app_prefs = mcontext.getSharedPreferences("shared",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=app_prefs.edit();
        editor.clear();
        editor.apply();
        return true;

    }

    public PreferenceHelper(Context context) {
        app_prefs = context.getSharedPreferences("shared",
                Context.MODE_PRIVATE);
        this.mcontext = context;
    }

    public void putIsLogin(boolean loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(User_ID, loginorout);
        edit.apply();
    }
    public boolean getIsLogin() {
        return app_prefs.getBoolean(User_ID, false);
    }

    public void putPhone(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(phone, loginorout);
        edit.commit();
    }
    public void putDeviceToken(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(DeviceToken, loginorout);
        edit.commit();
    }
    public void putLang(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(Lang, loginorout);
        edit.commit();
    }

    public void putPassword(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(password, loginorout);
        edit.commit();
    }
    public String getPhone() {
        return app_prefs.getString(phone, "");
    }
     public String getDeviceToken() {
        return app_prefs.getString(DeviceToken, "");
    }

    public String getLang() {
        return app_prefs.getString(Lang, "");
    }

    public String getPassword() {
        return app_prefs.getString(password, "");
    }
    public void putUser_ID(String loginorout) {
      SharedPreferences.Editor edit = app_prefs.edit();
       edit.putString(User_ID, loginorout);
       edit.commit();
   }
    public String getUser_id() {
        return app_prefs.getString(User_ID, "");
    }


}
