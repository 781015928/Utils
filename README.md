# Utils
###DebugLog

在方法上加上@DebugLog 
![Image text](https://github.com/781015928/Utils/snapshot/SNAPSHOT_1.png)
   ``` 
  @DebugLog
    private static String  getStr2222(String str,String str2,String str3){
        return "33333"+str+str3;
    }

```
![Image text](https://github.com/781015928/Utils/snapshot/SNAPSHOT_2.png)
 ```
   @DebugLog(DebugLog.ERROR)
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
      }
  
 ```
 
 
##LifeCycleLog 自动生成生命周期i级别 log
![Image text](https://github.com/781015928/Utils/snapshot/SNAPSHOT_3.png)
 