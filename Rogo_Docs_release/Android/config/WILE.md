# Rogo Smart SDK -  Android

_## Trước khi thực hiện config thiết bị wile_
- Kiểm tra đã cấp quyền BLE, network cho app chưa?

## Cấu hình thiết bị Wile
- Quy trình:
1. discovery
2. setup info
3. setup network
4. finish

### Handler
```kotlin
val handler = SmartSdk.configWileHandler()
```
### Discovery
- bắt đầu scan thiết bị wile ở gần
```kotlin
handler.discoveryWileDevice(onDeviceFound)
```
##### Đối số
onDeviceFound: ```DiscoveryIoTWileCallback``` callback được gọi khi phát hiện một thiết bị wile khả dụng

#### Stop discovery
```kotlin
handler.stopDiscovery()
```

#### Nhận diện thiết bị
- Nhận diện thiết bị đang pair (nháy đèn trên thiết bị)
```kotlin
handler.identifyDevice(ioTWileScanned, successStatus)
```
##### Đối số
ioTWileScanned: ```IoTWileScanned``` đối tượng wile scan được ở trên
successStatus: ```SuccessStatus``` callback gọi khi nhận diện thành công

### Thêm thiết bị
#### Bắt đầu setup thiết bị
```kotlin
handler.startSetupWileDevice(setupDeviceInfo, setupDeviceWileCallback)
```
##### Đối số
setupDeviceInfo: ```SetupDeviceInfo``` đối tượng thiết lập thông tin thiết bị wile
setupDeviceWileCallback: ```SetupDeviceWileCallback```
```setupDeviceWileCallback.onProgress(uuidMesh, progress, mess);```

#### Setup wifi network
- Scan được wifi thì ```onWifiScanned``` sẽ được gọi trả về ssid và rssi của wifi
- Quá trình scan kết thúc thì ```onWifiStopScanned``` được gọi

##### Config password Wifi
```kotlin
handler.setWifiPwd(ssid, pwd, isConfirm)
```
##### Đối số
ssid: ssid của wifi
pwd: password
isConfirm: Boolean : lựa chọn có xác thực wifi hay không

### Hủy bỏ thêm thiết bị wile
```kotlin
handler.cancelSetupWileDevice()
```
