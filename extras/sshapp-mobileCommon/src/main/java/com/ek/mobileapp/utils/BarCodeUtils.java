package com.ek.mobileapp.utils;

public class BarCodeUtils {
    public static boolean isPatientTM(String pattern, String tm) {
        String p = pattern.trim();

        if (Util.isNumericByP(p)) {
            if (tm.trim().length() == Integer.valueOf(p)) {
                return true;
            } else {
                return false;
            }
        } else {
            //TODO
            //指定字符开头或其他方法
            return false;
        }
    }

    public static boolean isOrderNo(String pattern, String tm) {
        String p = pattern.trim();

        if (Util.isNumericByP(p)) {
            if (tm.trim().length() == Integer.valueOf(p)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isLisNo(String pattern, String tm) {
        String p = pattern.trim();

        if (Util.isNumericByP(p)) {
            if (tm.trim().length() == Integer.valueOf(p)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
