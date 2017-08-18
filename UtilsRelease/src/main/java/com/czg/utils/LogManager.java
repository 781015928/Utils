package com.czg.utils;

import android.text.TextUtils;
import android.util.Log;

import static com.czg.utils.LogUtils.DOUBLE_DIVIDER;

/**
 * Created by 78101 on 2017/5/9.
 */

public enum LogManager {
    LOGGER;

    private LogManager() {
    }

    public void w(String tag, String info) {
        Log.println(Log.WARN, tag, info);
    }

    public void d(String tag, String info) {
        if(info!=null&&info.length()>3000) {
            Log.println(Log.DEBUG, tag, info.substring(0, 3000));
            d(tag,info.substring(3000,info.length()));
        }else {
            Log.println(Log.DEBUG, tag, info);
        }

    }

    public void e(String tag, String info) {
        Log.println(Log.ERROR, tag, info);
    }

    public void i(String tag, String info) {
        Log.println(Log.INFO, tag, info);
    }

    public void v(String tag, String info) {
        Log.println(Log.VERBOSE, tag, info);
    }

    public void e(String tag, String info, Throwable throwable) {
        Log.e(tag, info, throwable);
    }

    public void printJson(String tag, String json){


        if(json!=null&&json.length()>3000) {

            Log.println(Log.DEBUG, tag, json.substring(0, 3000));
            printJson(tag,json.substring(3000,json.length()));
        }else {
            Log.println(Log.DEBUG, tag, json);
        }
    }


    public void json(String tag, String json) {
        if (!TextUtils.isEmpty(json)) {
            String msg = "\n" + format(convertUnicode(json));

            printJson(tag,msg);
        } else {
            e(tag, '╔' + DOUBLE_DIVIDER);
            e(tag, "║");
            e(tag, "║");
            e(tag, "║");
            e(tag, "║" + "          Empty/Null json content");
            e(tag, "║");
            e(tag, "║");
            e(tag, "║");
            e(tag, '╚' + DOUBLE_DIVIDER);
        }
    }


    public static String format(String jsonStr) {
        int level = 0;
        StringBuffer jsonForMatStr = new StringBuffer();
        for (int i = 0; i < jsonStr.length(); i++) {
            char c = jsonStr.charAt(i);
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c + "\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c + "\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }

        return jsonForMatStr.toString();

    }

    public static String convertUnicode(String ori) {
        char aChar;
        int len = ori.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = ori.charAt(x++);
            if (aChar == '\\') {
                aChar = ori.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = ori.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);

        }
        return outBuffer.toString();
    }

    private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }
}
