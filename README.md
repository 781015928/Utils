# Utils
### DebugLog

##### classpath
```
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.3'
        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:1.0.8'
       
    }
}
```
##### apply plugin
```
apply plugin: 'android-aspectjx'
```


### debug
debugCompile 'com.czg.utils:UtilsDebug:1.0.0' 

### release
 release版本部关闭所有功能

releaseCompile  'com.czg.utils:UtilsRelease:1.0.0' 


在方法上加上@DebugLog 
![Image text](https://github.com/781015928/Utils/blob/master/snapshot/SNAPSHOT_1.png)
   ``` 
  @DebugLog
    private static String  getStr2222(String str,String str2,String str3){
        return "33333"+str+str3;
    }

```
![Image text](https://github.com/781015928/Utils/blob/master/snapshot/SNAPSHOT_2.png)
 ```
   @DebugLog(DebugLog.ERROR)
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
      }
  
 ```
 
 
## LifeCycleLog 自动生成生命周期i级别 log
![Image text](https://github.com/781015928/Utils/blob/master/snapshot/SNAPSHOT_3.png)
 