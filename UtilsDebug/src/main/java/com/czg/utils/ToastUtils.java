package com.czg.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by czg on 2017/8/18.
 */

public class ToastUtils {

    private static int mDimension;
    private static WeakReference<Toast> sToast;
    private static int sGravity= Gravity.CENTER, sXOffset=0, sYOffset=100;

    public static void setLayout(int gravity, int xOffset, int yOffset) {
        sGravity = gravity;
        sXOffset = xOffset;
        sYOffset = yOffset;
    }


    public static void showShort(View view, Context context) {
        if (sToast != null && sToast.get() != null) {
            sToast.get().cancel();
        }

        sToast = new WeakReference(new Toast(context.getApplicationContext()));
        sToast.get().setView(view);
        sToast.get().setDuration(Toast.LENGTH_SHORT);
        sToast.get().setGravity(sGravity, sXOffset, sYOffset);
        sToast.get().show();
    }

    public static void showLong(View view, Context context) {
        if (sToast != null && sToast.get() != null) {
            sToast.get().cancel();
        }
        sToast = new WeakReference(new Toast(context.getApplicationContext()));
        sToast.get().setView(view);
        sToast.get().setDuration(Toast.LENGTH_LONG);
        sToast.get().setGravity(sGravity, sXOffset, sYOffset);
        sToast.get().show();
    }

    public static void showLong(CharSequence sequence, Context context) {
        if (sToast != null && sToast.get() != null) {
            sToast.get().cancel();
        }
        sToast = new WeakReference(Toast.makeText(context.getApplicationContext(), sequence, Toast.LENGTH_LONG));

        sToast.get().show();
    }

    public static void showShort(CharSequence sequence, Context context) {
        if (sToast != null && sToast.get() != null) {
            sToast.get().cancel();
        }
        sToast = new WeakReference(Toast.makeText(context.getApplicationContext(), sequence, Toast.LENGTH_SHORT));

        sToast.get().show();
    }
}
