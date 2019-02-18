package tech.fraction.webapp.util;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import tech.fraction.webapp.R;
import tech.fraction.webapp.activity.LoginActivity;
import tech.fraction.webapp.model.Childrens;
import tech.fraction.webapp.model.Menu;
import tech.fraction.webapp.model.PersonInformation;
import tech.fraction.webapp.model.UserRights;
import tech.fraction.webapp.preference.AppPersistence;
import tech.fraction.webapp.preference.AppPreference;

public class Utils {
    private Context _context;

    public Utils(Context context) {
        this._context = context;
    }

    public static boolean isValidEmail(String strEmail) {
        boolean b;
        if (strEmail == null) {
            b = false;
        } else {
            b = Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(strEmail).matches();
        }
        return b;
    }

    public static synchronized boolean isNetworkAvailable(Context activity) {
        boolean flag;

        if (checkNetworkAvailable(activity)) {
            flag = true;
        } else {
            flag = false;
            Log.d("", "No network available!");
        }
        return flag;
    }

    public static String getTodayDate() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(c);
    }

    private static boolean checkNetworkAvailable(Context activity) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public static void setDate(final TextView textView, Activity context) {
        Utils.hideKeyboard(context);
        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "dd-MM-yyyy"; // In which you need put
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                textView.setText(sdf.format(myCalendar.getTime()));
            }
        };

        DatePickerDialog d = new DatePickerDialog(context,
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        d.setCancelable(false);
        d.show();
    }

    public static int convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        String pxx;
        if (String.valueOf(px).contains(".")) {
            pxx = String.valueOf(px).substring(0, String.valueOf(px).indexOf("."));
        } else {
            pxx = String.valueOf(px);
        }
        return Integer.parseInt(pxx);
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showKeyboard(Activity noticeBoard) {
        View view = noticeBoard.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) noticeBoard.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void ShowSnakBar(String s, View view, Activity login) {
        Snackbar snackbar = Snackbar.make(view, s, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView tv = sbView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        sbView.setBackgroundColor(ContextCompat.getColor(login, R.color.colorPrimary));
        snackbar.show();
    }

    public static String ifIsStringNull(Object s) {
        if (s == null) {
            s = "";
        }
        return s.toString();
    }

    public static int ifIsIntNull(Object s) {
        if (s == null) {
            s = 0;
        }
        return Integer.parseInt(s.toString());
    }

    public static boolean ifIsObjectNull(Object s) {
        boolean isNull = false;
        if (s == null) {
            isNull = true;
        }
        return isNull;
    }

    public static void showToast(Context context, String msg) {
        if (!msg.isEmpty()) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "There was some server or network problem try again", Toast.LENGTH_SHORT).show();
        }
    }

    public static void ShowSnakBar1(String s, View linearLayout, final Activity login) {
        Snackbar snackbar = Snackbar
                .make(linearLayout, s, Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

        snackbar.setActionTextColor(Color.RED);

        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        sbView.setBackgroundColor(ContextCompat.getColor(login, R.color.colorPrimary));
        snackbar.show();
    }


    public static UserRights getUserRights(Context context) {
        String connectionsJSONString = AppPersistence.start(context).get(AppPersistence.keys.Role_Rights).toString();
        Type type = new TypeToken<UserRights>() {
        }.getType();
        return new Gson().fromJson(connectionsJSONString, type);
    }

    public static void setUserRights(Context context, UserRights userRights) {
        String toJson = new Gson().toJson(userRights);
        AppPersistence.start(context).saveObject(AppPersistence.keys.Role_Rights, toJson);
    }

    public static List<Menu> getMenus(Context context) {
        String connectionsJSONString = AppPersistence.start(context).get(AppPersistence.keys.Menus).toString();
        JSONObject jsonObjectList = null;
        List<Menu> menus = new ArrayList<>();
        try {
            String s = connectionsJSONString.replaceAll("\\\\", "");
            s = s.substring(1, s.length() - 1);
            jsonObjectList = new JSONObject(s);

            JSONArray jsonArray = jsonObjectList.getJSONArray("menu");
            {
                if (jsonArray != null && jsonArray.length() != 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Menu menu = new Menu();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        List<Childrens> childrensList = new ArrayList<>();
                        JSONArray jsonArray1 = jsonObject.getJSONArray("childrens");
                        {
                            if (jsonArray1 != null && jsonArray1.length() != 0) {
                                for (int j = 0; j < jsonArray1.length(); j++) {
                                    Childrens childrens = new Childrens();

                                    JSONObject jsonObject1 = jsonArray1.getJSONObject(j);

                                    List<Childrens> childrensList1 = new ArrayList<>();
                                    JSONArray jsonArray2 = jsonObject1.getJSONArray("childrens");
                                    {
                                        if (jsonArray2 != null && jsonArray2.length() != 0) {
                                            for (int k = 0; k < jsonArray2.length(); k++) {
                                                Childrens childrens1 = new Childrens();
                                                JSONObject jsonObject2 = jsonArray2.getJSONObject(k);
                                                childrens1.setChildrens(null);
                                                childrens1.setDisplayName(jsonObject2.getString("displayName"));
                                                childrens1.setDisplayOrder(jsonObject2.getInt("displayOrder"));
                                                childrens1.setMenuId(jsonObject2.getInt("menuId"));
                                                childrens1.setParentId(jsonObject2.getInt("parentId"));
                                                childrens1.setTotalChilds(jsonObject2.getInt("totalChilds"));
                                                childrensList1.add(childrens1);
                                            }
                                        }
                                    }

                                    childrens.setChildrens(childrensList1);
                                    childrens.setDisplayName(jsonObject1.getString("displayName"));
                                    childrens.setDisplayOrder(jsonObject1.getInt("displayOrder"));
                                    childrens.setMenuId(jsonObject1.getInt("menuId"));
                                    childrens.setParentId(jsonObject1.getInt("parentId"));
                                    childrens.setTotalChilds(jsonObject1.getInt("totalChilds"));
                                    childrensList.add(childrens);
                                }
                            }
                        }

                        menu.setChildrens(childrensList);
                        menu.setDisplayName(jsonObject.getString("displayName"));
                        menu.setDisplayOrder(jsonObject.getInt("displayOrder"));
                        menu.setMenuId(jsonObject.getInt("menuId"));
                        menu.setParentId(jsonObject.getInt("parentId"));
                        menu.setTotalChilds(jsonObject.getInt("totalChilds"));

                        menus.add(menu);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return menus;
    }

    public static void setMenu(Context context, List<Menu> menus) {

        JSONObject jResult = new JSONObject();// main object
        JSONArray jArray = new JSONArray();// /ItemDetail jsonArray

        for (int i = 0; i < menus.size(); i++) {
            JSONObject jGroup = new JSONObject();// /sub Object

            JSONArray jArraySub = new JSONArray();// /ItemDetail jsonArray

            if (menus.get(i).getTotalChilds() != 0) {
                for (int j = 0; j < menus.get(i).getChildrens().size(); j++) {
                    JSONObject jGroupSub = new JSONObject();// /sub Object

                    JSONArray jArraySubSub = new JSONArray();// /ItemDetail jsonArray

                    if (menus.get(i).getChildrens().get(j).getTotalChilds() != 0) {
                        for (int k = 0; k < menus.get(i).getChildrens().get(j).getChildrens().size(); k++) {
                            JSONObject jGroupSubSub = new JSONObject();// /sub Object

                            try {
                                jGroupSubSub.put("childrens", null);
                                jGroupSubSub.put("displayName", menus.get(i).getChildrens().get(j).getChildrens().get(k).getDisplayName());
                                jGroupSubSub.put("totalChilds", menus.get(i).getChildrens().get(j).getChildrens().get(k).getTotalChilds());
                                jGroupSubSub.put("displayOrder", menus.get(i).getChildrens().get(j).getChildrens().get(k).getDisplayOrder());
                                jGroupSubSub.put("menuId", menus.get(i).getChildrens().get(j).getChildrens().get(k).getMenuId());
                                jGroupSubSub.put("parentId", menus.get(i).getChildrens().get(j).getChildrens().get(k).getParentId());

                                jArraySubSub.put(jGroupSubSub);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    try {
                        jGroupSub.put("childrens", jArraySubSub);
                        jGroupSub.put("displayName", menus.get(i).getChildrens().get(j).getDisplayName());
                        jGroupSub.put("totalChilds", menus.get(i).getChildrens().get(j).getTotalChilds());
                        jGroupSub.put("displayOrder", menus.get(i).getChildrens().get(j).getDisplayOrder());
                        jGroupSub.put("menuId", menus.get(i).getChildrens().get(j).getMenuId());
                        jGroupSub.put("parentId", menus.get(i).getChildrens().get(j).getParentId());

                        jArraySub.put(jGroupSub);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                jGroup.put("childrens", jArraySub);
                jGroup.put("displayName", menus.get(i).getDisplayName());
                jGroup.put("totalChilds", menus.get(i).getTotalChilds());
                jGroup.put("displayOrder", menus.get(i).getDisplayOrder());
                jGroup.put("menuId", menus.get(i).getMenuId());
                jGroup.put("parentId", menus.get(i).getParentId());

                jArray.put(jGroup);

                // /itemDetail Name is JsonArray Name
                jResult.put("menu", jArray);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        String toJson = jResult.toString();
        AppPersistence.start(context).saveObject(AppPersistence.keys.Menus, toJson);
    }

    public static void setPersonalInfo(Context context, PersonInformation personInformation) {
        AppPersistence.start(context).save(AppPersistence.keys.personId, personInformation.getPersonId());
        AppPersistence.start(context).save(AppPersistence.keys.accountId, personInformation.getAccountId());
        AppPersistence.start(context).save(AppPersistence.keys.firstName, personInformation.getFirstName());
        AppPersistence.start(context).save(AppPersistence.keys.lastName, personInformation.getLastName());
        AppPersistence.start(context).save(AppPersistence.keys.emailAddress, personInformation.getEmailAddress());
        AppPersistence.start(context).save(AppPersistence.keys.personType, personInformation.getPersonType());
    }

    public static PersonInformation getPersonalInfo(Context context) {
        PersonInformation personInformation = null;
        if (AppPersistence.start(context).get(AppPersistence.keys.firstName) == null) {
            return personInformation;
        } else {
            personInformation = new PersonInformation();
        }
        personInformation.setPersonId((Integer) AppPersistence.start(context).get(AppPersistence.keys.personId));
        personInformation.setAccountId((Integer) AppPersistence.start(context).get(AppPersistence.keys.accountId));
        personInformation.setFirstName((String) AppPersistence.start(context).get(AppPersistence.keys.firstName));
        personInformation.setLastName((String) AppPersistence.start(context).get(AppPersistence.keys.lastName));
        personInformation.setEmailAddress((String) AppPersistence.start(context).get(AppPersistence.keys.emailAddress));
        personInformation.setPersonType((String) AppPersistence.start(context).get(AppPersistence.keys.personType));
        return personInformation;
    }


    public static void callIntent(Activity context, String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        context.startActivity(intent);
    }


    public static void Logout(Context context) {
        AppPersistence.start(context).removeAll();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public static String getIndianRupee(String value) {
        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        return format.format(new BigDecimal(value));
    }

    public static String FormatDate(String s) {
        String date;
        if (s.contains("T")) {
            date = s.substring(0, s.indexOf("T"));
        } else {
            date = s;
        }
        return date;
    }
}
