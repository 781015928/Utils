package com.czg.utils.log;

import android.app.Activity;
import android.util.Log;

import com.czg.utils.permission.AopUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by czg on 2017/8/17.
 */
@Aspect
public class LifeCycleLog {
    public static String TAG = LifeCycleLog.class.getSimpleName();


    @Before("execution(* android.app.Activity.onCreate(..))")
    public void onActivityCreate(JoinPoint joinPoint) {
        parseJoinPoint(joinPoint);

    }

    @Before("execution(* android.app.Activity.onStart(..))")
    public void onActivityStart(JoinPoint joinPoint) {
        parseJoinPoint(joinPoint);
    }

    @Before("execution(* android.app.Activity.onRestart(..))")
    public void onActivityRestart(JoinPoint joinPoint) {
        parseJoinPoint(joinPoint);
    }

    @Before("execution(* android.app.Activity.onResume(..))")
    public void onActivityResume(JoinPoint joinPoint) {
        parseJoinPoint(joinPoint);
        AopUtils.updateActiveActivity((Activity) joinPoint.getThis());
    }

    @Before("execution(* android.app.Activity.onPause(..))")
    public void onActivityPause(JoinPoint joinPoint) {
        parseJoinPoint(joinPoint);
    }

    @Before("execution(* android.app.Activity.onStop(..))")
    public void onActivityStop(JoinPoint joinPoint) {
        parseJoinPoint(joinPoint);
    }

    @Before("execution(* android.app.Activity.onDestroy(..))")
    public void onActivityDestroy(JoinPoint joinPoint) {
        parseJoinPoint(joinPoint);
    }
    private void parseJoinPoint(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        StringBuilder msg = new StringBuilder(joinPoint.getSourceLocation().getFileName().replace(".java",""));
        msg.append(".");
        msg.append(signature.getName());
        msg.append("(");
        msg.append(")");
        log(msg.toString());
    }

    private void log(String msg) {
        Log.i(TAG, msg);
    }

}
