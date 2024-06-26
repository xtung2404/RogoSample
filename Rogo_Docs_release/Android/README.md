# Rogo Smart SDK -  Android

## Cách setup SDK
 -  Tạo mới một class kế thừa từ Application và gọi init SDK tại onCreate

 ```java
public class MyCustomApplication extends Application {
        
	@Override
	public void onCreate() {
	    super.onCreate();
            // Required initialization
        SmartSdk.isForceStagingSvr = true;

		// call init sdk at here
		// example:
		// SmartSdk().init(this, this, customAuthHandler, false)
		
		// enable sdk log
        ILogR.setEnablePrint(true)
	}
}
 ```

### Một số phương thức khởi tạo kèm đối số
```java
//Init
void init(Application application, Context context, boolean runAsForegroundSrv);
void init(Application application, Context context, String authKey, String rogoKey, boolean runAsForegroundSrv);
void init(Application application, Context context, AuthHandler auth, boolean runAsForegroundSrv); // Custome your auth, example Firebase
void init(Application application, Context context, AuthHandler auth, NotificationHandler notificationHandler, boolean runAsForegroundSrv); // Custome your auth, example Firebase
```

