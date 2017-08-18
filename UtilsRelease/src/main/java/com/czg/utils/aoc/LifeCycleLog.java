package com.czg.utils.aoc;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by czg on 2017/8/17.
 */
@Aspect
public class LifeCycleLog {
    public static boolean ISDEBUG = true;
    public static String TAG = LifeCycleLog.class.getSimpleName();

   @Before("execution(* android.app.Activity.on**(..))")
    public void onActivityLife(JoinPoint joinPoint) {
        parseJoinPoint(joinPoint);

    }

    @Before("execution(* android.app.Fragment.on**(..))")
    public void onFragmentLife(JoinPoint joinPoint) {
        parseJoinPoint(joinPoint);
    }

    @Before("execution(* android.support.v4.app.Fragment.on**(..))")
    public void onV4FragmentLife(JoinPoint joinPoint) {
        parseJoinPoint(joinPoint);
    }

   @Before("execution(* android.app.Dialog.on**(..))")
    public void onDialogLife(JoinPoint joinPoint) {
        parseJoinPoint(joinPoint);
    }

    @Before("execution(* android.view.View.on**(..))")
    public void onViewLife(JoinPoint joinPoint) {
        parseJoinPoint(joinPoint);
    }

    private void parseJoinPoint(JoinPoint joinPoint) {
        if(!ISDEBUG) {
            return;
        }
        Signature signature = joinPoint.getSignature();

        StringBuilder msg = new StringBuilder(joinPoint.getThis().getClass().getSimpleName());
        msg.append(".");
        msg.append(signature.getName());
        msg.append("(");
        msg.append(joinPoint.getSourceLocation().getFileName());
        msg.append(":");
        msg.append(joinPoint.getSourceLocation().getLine());
        msg.append(")");
        log(msg.toString());
    }

    private void log(String msg) {
        Log.i(TAG, msg);
    }

}
