package tech.fraction.webapp.util;

import android.support.v4.app.NotificationCompatSideChannelService;

import java.util.Date;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static final boolean isValidEmail(String strEmail) {
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

    public static final boolean isValidPassword(String strPwd) {
        boolean b;
        if (strPwd.isEmpty()) {
            b = false;
        } else b = strPwd.length() >= 4;

        return b;
    }

    public static final boolean isValidPhoneNumber(String mobileNo) {
        if (mobileNo.length() < 6 || mobileNo.length() > 13) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(mobileNo).matches();
        }

    }

    public static final boolean isValidRemark(String remark) {
        return remark.length() >= 10;

    }

    public static final boolean isValidName(String name) {
        return Pattern.matches("^[\\p{L} .'-]+$", name);
    }

    public static final boolean isValidVehicleNumber(String vehicleNo) {
        return Pattern.matches("^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}$", vehicleNo);
    }

    public static String compareDates(Date date1, Date date2)
    {

        String message="";
        if(date1.after(date2)){
          message= "Date1 is after Date2";
        }

        if(date1.before(date2)){

          message="Date1 is before Date2";
        }

        if(date1.equals(date2)){
            message="Date1 is equal Date2";
        }

        return  message;

    }

}
