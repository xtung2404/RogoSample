# Rogo Smart SDK -  Android

## Config Media box - FPT Playbox

### 1 Thêm thiết bị

```kotlin
val handler = SmartSdk.configGatewayHandler()
handler.addPlayDevice(callback)
```
#### Đối số
- callback: Là một ```RequestAddPlayDeviceCallback``` dùng để lắng nghe sự kiện khi tạo mã code

```kotlin
 val callback = object : RequestAddPlayDeviceCallback {
            override fun onVerifyCodeGenerated(code: String?) {
            }

            override fun onTimeCountDown(time: Int) {
                // Thời gian còn lại
            }

            override fun onSuccess(device: IoTDevice?) {

            }

            override fun onFailure(code: Int, message: String?) {
            }

            override fun onCancelled() {

            }
        }
```

### Cancel
```kotlin
val handler = SmartSdk.configGatewayHandler()
handler.cancel()
```

