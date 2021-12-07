package algonquin.cst2335.finalprojectassignment.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class PreferenceManager {

    private static PreferenceManager instance;
    String KEY_LIST = "KEY_LIST";

    public static PreferenceManager getInstance() {
        if (instance == null) {
            instance = new PreferenceManager();
        }
        return instance;
    }

    private SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("alka0064.sp", Context.MODE_PRIVATE);
    }


    public List<String> getPreviousList(Context context) {
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString(KEY_LIST, "");

        if (TextUtils.isEmpty(json)){
            SharedPreferences.Editor editor = getSharedPreferences(context).edit();
            editor.putString(KEY_LIST, new Gson().toJson(new ArrayList<>()));
            editor.apply();
        }

        Type type = new TypeToken<List<String>>() {
        }.getType();

        return  gson.fromJson(json, type);
    }


    public void savePreviousText(Context context, String text) {
        List<String> stringList = getPreviousList(context);
        stringList.add(text);
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_LIST, new Gson().toJson(stringList));
        editor.apply();
    }
}
