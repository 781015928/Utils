package com.czg.utils;


import android.text.TextUtils;

/**
 * 类 名 称  ： AdapterNotifyEvent.class
 * 作    者 ：  czg
 * 日    期 ：  2017/1/6.
 * 作    用 ： 在这里写一句话描述作用
 */
public class LogUtils {
    //private static boolean isDebug = true;// 在app代码Debug时置为true
    public static String TAG = LogUtils.class.getSimpleName();
    public static LogManager mLogManager = LogManager.LOGGER;
    public static final String DOUBLE_DIVIDER = "═════════════════════════════════════════════════════════════════";

    public static void init( String TAG) {
        LogUtils.TAG = TAG;
    }

    public static void d(String msg) {
        String[] infos = getAutoJumpLogInfos();
        mLogManager.d(infos[0] + infos[1], msg);
    }

    public static void d(Object msg) {
            String[] infos = getAutoJumpLogInfos();
            mLogManager.d(infos[0] + infos[1], msg != null ? msg.toString() : "Null");
    }

    public static void e(Throwable throwable) {

            String[] infos = getAutoJumpLogInfos();


            StackTraceElement[] trace = throwable.getStackTrace();
            // mLogManager.e(infos[0] + infos[1], throwable.toString());
            mLogManager.e(infos[0] + infos[1], throwable.toString(), throwable);
            for (StackTraceElement elements : trace) {
                //     mLogManager.e(infos[0] + infos[1], elements.toString());
            }
    }

    public static void e(String msg) {
            String[] infos = getAutoJumpLogInfos();
            mLogManager.e(infos[0] + infos[1], msg);
    }

    public static void e(Object msg) {
            String[] infos = getAutoJumpLogInfos();
            mLogManager.e(infos[0] + infos[1], msg != null ? msg.toString() : "Null");
    }

    private static String[] getAutoJumpLogInfos() {
        String[] infos = new String[]{"", "", ""};
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        if (elements.length < 5) {
            return infos;
        } else {

            infos[0] = "(" + elements[4].getFileName() + ":" + elements[4].getLineNumber() + ")";
            infos[1] = elements[4].getMethodName() + "()";

            return infos;
        }
    }

    public static void i(String msg) {
            String[] infos = getAutoJumpLogInfos();
            mLogManager.i(infos[0] + infos[1], msg);
    }

    public static void i(Object msg) {
            String[] infos = getAutoJumpLogInfos();
            mLogManager.i(infos[0] + infos[1], msg != null ? msg.toString() : "Null");
    }

    public static void v(Object msg) {
            String[] infos = getAutoJumpLogInfos();
            mLogManager.v(infos[0] + infos[1], msg != null ? msg.toString() : "Null");
    }

    public static void v(String msg) {
            String[] infos = getAutoJumpLogInfos();
            mLogManager.v(infos[0] + infos[1], msg);
    }

    // w级别log object:类名   msg:消息
    public static void w(String msg) {
            String[] infos = getAutoJumpLogInfos();
            mLogManager.w(infos[0] + infos[1], msg);
    }

    public static void w(Object msg) {
            String[] infos = getAutoJumpLogInfos();
            mLogManager.w(infos[0] + infos[1], msg != null ? msg.toString() : "Null");
    }

    public static void json(String json) {

        if (TextUtils.isEmpty(json)) {
            mLogManager.e(TAG, '╔' + DOUBLE_DIVIDER);
            mLogManager.e(TAG, "║");
            mLogManager.e(TAG, "║");
            mLogManager.e(TAG, "║");
            mLogManager.e(TAG, "║" + "          Empty/Null json content");
            mLogManager.e(TAG, "║");
            mLogManager.e(TAG, "║");
            mLogManager.e(TAG, "║");
            mLogManager.e(TAG, '╚' + DOUBLE_DIVIDER);
            return;
        } else {
            mLogManager.json(TAG, json);
        }
        ;

    }

    public static void http(String content) {

        mLogManager.e(TAG, '╔' + DOUBLE_DIVIDER);
        String[] split = content.split("\n");
        for (String msg : split) {
            mLogManager.e(TAG, "║" + msg);
        }
        mLogManager.e(TAG, '╚' + DOUBLE_DIVIDER);
    }


}

