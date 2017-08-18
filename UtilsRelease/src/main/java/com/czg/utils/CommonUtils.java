package com.czg.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommonUtils {

	public final static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";


	public static synchronized boolean isNetworkConnected(Context context) {
		boolean isConnected = false;
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null) {
		} else {
			NetworkInfo[] info = cm.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						isConnected = true;
					}
				}
			}
		}
		return isConnected;

	}
	public static synchronized boolean isWifiConnected(Context context) {
		ConnectivityManager connManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connManager != null) {
			NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
			if (networkInfo != null) {
				int networkInfoType = networkInfo.getType();
				if (networkInfoType == ConnectivityManager.TYPE_WIFI || networkInfoType == ConnectivityManager.TYPE_ETHERNET) {
					return networkInfo.isConnected();
				}
			}
		}
		return false;
	}

	public static boolean isMobileNetworkConnected(Context context) {
		ConnectivityManager connManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connManager != null) {
			NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
			if (networkInfo != null) {
				int networkInfoType = networkInfo.getType();
				if (networkInfoType == ConnectivityManager.TYPE_MOBILE) {
					return networkInfo.isConnected();
				}
			}
		}
		return false;
	}

	public static boolean isAllowOfflineDownload(int networkType) {
		switch (networkType) {
		case ConnectivityManager.TYPE_ETHERNET:
		case ConnectivityManager.TYPE_WIFI:
			return true;
		default:
			return false;
		}
	}
/**
 * 当前应用版本号
 * @return
 */
	public static int getCurrentVersionCode(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			String packageName = context.getPackageName();
			PackageInfo info = packageManager.getPackageInfo(packageName, 0);
			return info.versionCode;
		} catch (Exception e) {
		}
		return 1;
	}

	/**
	 * 获取当前应用版本名称
	 * 
	 * @param
	 * @return
	 */
	public static String getCurrentVersionName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			String packageName = context.getPackageName();
			PackageInfo info = packageManager.getPackageInfo(packageName, 0);
			return info.versionName;
		} catch (Exception e) {
		}
		return "4.0.0";

	}

	public static void hideKeyBoard(Activity activity) {
		if (activity.getCurrentFocus() != null) {
			((InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity
					.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
	public static void hideKeyBoard2(View view,Context context) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
	public static void showKeyBoard(final View view,Context context ){
		final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
			}
		}, 100);

	}

	public static void setFullscreen(Activity activity, boolean on) {
		if (activity != null) {
			Window win = activity.getWindow();
			WindowManager.LayoutParams winParams = win.getAttributes();
			final int bits = WindowManager.LayoutParams.FLAG_FULLSCREEN;
			if (on) {
				winParams.flags |= bits;
			} else {
				winParams.flags &= ~bits;
			}
			win.setAttributes(winParams);
		}
	}

	public static void setTranslucentStatus(Activity activity, boolean on) {
		Window win = activity.getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		// final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		// if (on) {
		// winParams.flags |= bits;
		// } else {
		// winParams.flags &= ~bits;
		// }
		win.setAttributes(winParams);
	}

	public static void setTranslucentNavigation(Activity activity, boolean on) {
		Window win = activity.getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		// final int bits =
		// WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
		// if (on) {
		// winParams.flags |= bits;
		// } else {
		// winParams.flags &= ~bits;
		// }
		win.setAttributes(winParams);
	}

	// 没有2g 3g判断，手机网络统一为2
	public static int getNetworkType(Context context) {
		int type = 0;
		if (isWifiConnected(context)) {
			type = 1;
		} else if (isMobileNetworkConnected(context)) {
			type = 2;
		}
		return type;
	}

	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
		}
		return "";
	}

	/**
	 * 判断时间是否超时
	 * 
	 * @param
	 * @return
	 */
//	public static boolean isTimeOut(long lastUpdate, String flag) {
//		if (lastUpdate == 0) {
//			return true;
//		}
//		long time = new Date().getTime();
//		long interval = time - lastUpdate;
//		if (ActionConstants.HALF_HOUR.equals(flag) && interval > ActionConstants.INTERVAL_HALF_HOUR) {// 超过半小时
//			return true;
//		} else if (ActionConstants.FIVE_MINUTE.equals(flag) && interval > ActionConstants.INTERVAL_FIVE_MINUTE) {// 超过5分钟
//			return true;
//		} else if (ActionConstants.ONE_HOUR.equals(flag) && interval > ActionConstants.INTERVAL_ONE_HOUR) {// 超过1小时
//			return true;
//		} else if (ActionConstants.SIX_HOUR.equals(flag) && interval > ActionConstants.INTERVAL_SIX_HOUR) {// 超过6小时
//			return true;
//		} else if (ActionConstants.TWELVE_HOUR.equals(flag) && interval > ActionConstants.INTERVAL_TWELVE_HOUR) {// 超过12小时
//			return true;
//		} else if (ActionConstants.TWO_HOUR.equals(flag) && interval > ActionConstants.INTERVAL_TWO_HOUR) {// 超过2小时
//			return true;
//		} else if (ActionConstants.TEN_MINUTE.equals(flag) && interval > ActionConstants.INTERVAL_TEN_MINUTE) {// 超过10分钟
//			return true;
//		}
//		return false;
//
//	}

	public static String getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		String date = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
		return date;
	}

	public static String getFormatDate(String date, String format, String parser) {
		try {
			Date d = new SimpleDateFormat(parser).parse(date);
			date = new SimpleDateFormat(format).format(d);
		} catch (Exception e) {
		}
		return date;
	}




	public static boolean isServiceWorked(Class<?> mClass,Context context) {
		ActivityManager myManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
		ArrayList<RunningServiceInfo> runningService = (ArrayList<RunningServiceInfo>) myManager.getRunningServices(30);
		for (int i = 0; i < runningService.size(); i++) {
			if (runningService.get(i).service.getClassName().toString().equals(mClass.getName())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isServiceWorked(String className,Context context) {
		ActivityManager myManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		ArrayList<RunningServiceInfo> runningService = (ArrayList<RunningServiceInfo>) myManager.getRunningServices(30);
		for (int i = 0; i < runningService.size(); i++) {
			if (runningService.get(i).service.getClassName().toString().equals(className)) {
				return true;
			}
		}
		return false;
	}


	/**
	 * 获取屏幕的高度
	 * @param manager
	 * @return
	 */
	public static int getScreenHeight(WindowManager manager){
		DisplayMetrics displayMetrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics.heightPixels;
	}

	/**
	 * 获取状态栏的高度
	 * @param context
	 * @return
	 */
	public static int getStatusBarHeight(Context context) {
		int result = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	public static String generateUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 生成三位随机数
	 *
	 * @return
	 */
	public static int generateThreeRandomNum() {
		Random random = new Random();
		int num = (int) (random.nextDouble() * (1000 - 100) + 100);
		return num;
	}

	/**
	 * 生成图片随机id
	 *
	 * @return
	 */
	public static String generateImgID() {
		return new StringBuilder().
				append(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())).
				append(generateThreeRandomNum()).toString();
	}

	/**
	 * 弹出底部菜单
	 *
	 * @param context
	 * @param view
	 * @return
	 */

	/**
	 * 获取屏幕的宽度
	 * @param manager
	 * @return
	 */
	public static int getScreenWidth(WindowManager manager){
		DisplayMetrics displayMetrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics.widthPixels;
	}

	public static String BigDecimal2Str(BigDecimal bigDecimal){
		String str = "";
//		if(bigDecimal.remainder(BigDecimal.ONE).floatValue()>0){
			DecimalFormat df = new DecimalFormat("0.0"); // 保留1位小数
			str = df.format(bigDecimal);
//		}else{
//			DecimalFormat df = new DecimalFormat("0"); // 取整
//			str = df.format(bigDecimal);
//		}
		return str;
	}


	/**
	 *
	 * @param time
	 * @return
	 */
	public static String formatTime(long time, String format){
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 验证用户名
	 * @param username 用户名
	 * @return boolean
	 */
	public static boolean checkUsername(String username){
		String regex = "([a-zA-Z0-9\\u4e00-\\u9fa5]{2,12})";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(username);
		return m.matches();
	}

	/**
	 * 验证职位名
	 * @param username 用户名
	 * @return boolean
	 */
	public static boolean checkJobname(String username){
		String regex = "([a-zA-Z0-9\\u4e00-\\u9fa5]{1,8})";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(username);
		return m.matches();
	}

	/**
	 * 自动安装apk
	 * @param file
	 */
	public static void openFile(Context context , File file) {
		if(file == null || !file.exists()){
			return ;
		}
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	/**
	 * md5加密
	 */
	public static String md5(String string) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10) hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}

	/**
	 * 读取assets中的文件
	 */
	public static String readFileFromAssets(Context context , String fileName){
		String txt = null;
		try{
			InputStream in = context.getAssets().open(fileName);  //获得AssetManger 对象, 调用其open 方法取得  对应的inputStream对象
			int size = in.available();//取得数据流的数据大小
			byte[] buffer= new byte[size];
			in.read(buffer);
			in.close();
			txt =new String(buffer);
		}catch(Exception e){
			e.printStackTrace();
		}
		return txt;
	}

	public static String formatPrice(double num){
		BigDecimal b = new BigDecimal(num);
		double f = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		String formatNum = String.format("%.2f", f);
		return formatNum;
	};

	public static double mul(double d1,double d2){
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.multiply(bd2).doubleValue();
	}
}
