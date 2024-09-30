package com.example.cityguidy.Database;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences userSession;
    SharedPreferences.Editor editor;
    Context context;

    private static final String IS_LOGIN = "isLoggedIn";
    public static final String KEY_USERNAME = "userNameId";
    public static final String KEY_EMAIL = "emailId";
    public static final String KEY_PHONE = "phoneId";

    public SessionManager(Context mContext) {
        context = mContext;
        userSession = context.getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        editor = userSession.edit();
        editor.commit();

    }

    public void createLoginSession(String userNameId, String emailId, String phoneId) {

        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USERNAME, userNameId);
        editor.putString(KEY_EMAIL, emailId);
        editor.putString(KEY_PHONE, phoneId);

        editor.commit();
    }

    public HashMap<String, String> getUserDetailFromSession(){
        HashMap<String, String> userData = new HashMap<String, String>();
        userData.put(KEY_USERNAME, userSession.getString(KEY_USERNAME, null));
        userData.put(KEY_EMAIL, userSession.getString(KEY_EMAIL, null));
        userData.put(KEY_PHONE, userSession.getString(KEY_PHONE, null));
        return userData;
    }

    public Boolean chickLogin(){
if(userSession.getBoolean(IS_LOGIN,false)){
    return true;
}else
return false;
    }
public void  logoutUserFromSession(){
editor.clear();
editor.commit();
    }
}
