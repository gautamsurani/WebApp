package tech.fraction.webapp.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Map;

import tech.fraction.webapp.R;

public class AppPersistence {
    public enum keys {
        personId, accountId, firstName, lastName, emailAddress, personType, Role_Rights, Menus
    }

    private static AppPersistence mAppPersistance;
    private SharedPreferences sharedPreferences;

    public static AppPersistence start(Context context) {
        if (mAppPersistance == null) {
            mAppPersistance = new AppPersistence(context);
        }
        return mAppPersistance;
    }

    private AppPersistence(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.prefrence_file_name),
                Context.MODE_PRIVATE);
    }

    public Object get(Enum key) {
        Map<String, ?> all = sharedPreferences.getAll();
        return all.get(key.toString());
    }

    public void save(Enum key, Object val) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (val instanceof Integer) {
            editor.putInt(key.toString(), (Integer) val);
        } else if (val instanceof String) {
            editor.putString(key.toString(), String.valueOf(val));
        } else if (val instanceof Float) {
            editor.putFloat(key.toString(), (Float) val);
        } else if (val instanceof Long) {
            editor.putLong(key.toString(), (Long) val);
        } else if (val instanceof Boolean) {
            editor.putBoolean(key.toString(), (Boolean) val);
        }
        editor.apply();
    }

    public void saveObject(Enum key, Object val) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String connectionsJSONString = new Gson().toJson(val);
        editor.putString(key.toString(), connectionsJSONString);
        editor.apply();
    }

    void remove(Enum key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key.toString());
        editor.apply();
    }

    public void removeAll() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
