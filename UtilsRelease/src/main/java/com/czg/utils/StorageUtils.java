package com.czg.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.File;

/**
 * 储存工具类
 */
public class StorageUtils {
    /**
     * 根据路径获取内存状态
     *
     * @param path
     * @return
     */
    public static String getMemoryInfo(Context context, File path) {
        // 获得一个磁盘状态对象
        StatFs stat = new StatFs(path.getPath());

        long blockSize = stat.getBlockSize();   // 获得一个扇区的大小

        long totalBlocks = stat.getBlockCount();    // 获得扇区的总数

        long availableBlocks = stat.getAvailableBlocks();   // 获得可用的扇区数量

        // 总空间
        String totalMemory = Formatter.formatFileSize(context, totalBlocks * blockSize);
        // 可用空间
        String availableMemory = Formatter.formatFileSize(context, availableBlocks * blockSize);

        return "总空间: " + totalMemory + "\n可用空间: " + availableMemory;
    }


    /**
     * 获取手机内部储存可用空间
     */
    public static String getDataAvailSize(Context context) {
        File path2 = Environment.getDataDirectory();
        StatFs stat2 = new StatFs(path2.getPath());
        long blockSize2 = stat2.getBlockSize();
        long totalBlocks2 = stat2.getBlockCount();
        long availableBlocks2 = stat2.getAvailableBlocks();

        long totalSize2 = totalBlocks2 * blockSize2;
        long availSize2 = availableBlocks2 * blockSize2;

        String totalStr2 = Formatter.formatFileSize(context, totalSize2); //总大小
        String availStr2 = Formatter.formatFileSize(context, availSize2); //可用空间
        return availStr2;
    }

    /**
     * 获取手机内部储存已用的百分比
     */
    public static float getUsedPercentage(Context context) {
        File path2 = Environment.getDataDirectory();
        StatFs stat2 = new StatFs(path2.getPath());
        long blockSize2 = stat2.getBlockSize();
        long totalBlocks2 = stat2.getBlockCount();
        long availableBlocks2 = stat2.getAvailableBlocks();

        float totalSize2 = totalBlocks2 * blockSize2;
        float availSize2 = availableBlocks2 * blockSize2;
        float usedSize = totalSize2 - availSize2;
        return usedSize/totalSize2;
    }

    /**
     * 获取手机内部储占用空间
     */
    public static String getDataBlockSize(Context context) {
        File path2 = Environment.getDataDirectory();
        StatFs stat2 = new StatFs(path2.getPath());
        long blockSize2 = stat2.getBlockSize();
        long totalBlocks2 = stat2.getBlockCount();
        long availableBlocks2 = stat2.getAvailableBlocks();

        long totalSize2 = totalBlocks2 * blockSize2;
        long availSize2 = availableBlocks2 * blockSize2;

        String totalStr2 = Formatter.formatFileSize(context, totalSize2); //总大小
        String availStr2 = Formatter.formatFileSize(context, availSize2); //可用空间
        String blockStr2 = Formatter.formatFileSize(context, blockSize2); //占用容量
        return blockStr2;
    }


    /**
     * 获得 内部储存 总容量
     */
    public static String getDataTotalSize(Context context) {
        File path2 = Environment.getDataDirectory();
        StatFs stat2 = new StatFs(path2.getPath());
        long blockSize2 = stat2.getBlockSize();
        long totalBlocks2 = stat2.getBlockCount();
        long availableBlocks2 = stat2.getAvailableBlocks();

        long totalSize2 = totalBlocks2 * blockSize2;
        long availSize2 = availableBlocks2 * blockSize2;

        String totalStr2 = Formatter.formatFileSize(context, totalSize2); //总大小
        String availStr2 = Formatter.formatFileSize(context, availSize2); //可用空间
        return totalStr2;
    }

    /**
     * 获得 SD 卡 总容量
     */
    public String getSdTotalSize(Context context) {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        long availableBlocks = stat.getAvailableBlocks();

        long totalSize = totalBlocks * blockSize;
        long availSize = availableBlocks * blockSize;

        String totalStr = Formatter.formatFileSize(context, totalSize); //总大小
        String availStr = Formatter.formatFileSize(context, availSize); //可用容量
        return totalStr;
    }

    /**
     * 获得 SD 卡 可用空间
     */

    public static String getSdAvailableSize(Context context) {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        long availableBlocks = stat.getAvailableBlocks();

        long totalSize = totalBlocks * blockSize;
        long availSize = availableBlocks * blockSize;


        String totalStr = Formatter.formatFileSize(context, totalSize); //总大小
        String availStr = Formatter.formatFileSize(context, availSize); //可用容量
        return availStr;
    }

    /**
     * 获得 SD 卡 占用空间
     */

    public static String getSdBlockSize(Context context) {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        long availableBlocks = stat.getAvailableBlocks();

        long totalSize = totalBlocks * blockSize;
        long availSize = availableBlocks * blockSize;


        String totalStr = Formatter.formatFileSize(context, totalSize); //总大小
        String availStr = Formatter.formatFileSize(context, availSize); //可用容量
        String blockStr = Formatter.formatFileSize(context, blockSize); //占用容量
        return blockStr;
    }

    public static String getFolderSize(Context context, String path) {
        File file = new File(path);
        return Formatter.formatFileSize(context, getFolderSize(file));
    }

    public static long getFolderSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);

                } else {
                    size = size + fileList[i].length();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return size/1048576;
        return size;
    }

}
