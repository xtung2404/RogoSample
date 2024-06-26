# Rogo Smart SDK -  Android


### Connect Service

```kotlin
 val smartSdkConnectCallback = object : SmartSdkConnectCallback {
            override fun onConnected(isAuthenticated: Boolean) {

            }

            override fun onDisconnected(){

            }
        }
        SmartSdk.connectService(context, smartSdkConnectCallback)
```

> Bắt buộc phải connect service trước khi gọi bất kì lệnh nào tới SDK

> Mỗi khi gọi SmartSdk.connectService(context, smartSdkConnectCallback) thì callback trước đó sẽ bị xoá
- onConnected: gọi khi đã kết nối service thành công
- onDisconnected: Khi hàm này được gọi hãy đăng xuất khỏi ứng dụng


### Close
```kotlin
        SmartSdk.closeConnectionService()
```