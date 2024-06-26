# Rogo Smart SDK -  Android
## Lưu ý trước khi thực hiện config thiết bị
- Bắt buộc phải kiểm tra gateway - thiết bị trung tâm có đang available hay không trước khi bắt đầu quá trình cấu hình thiết bị

## Cấu hình thiết bị Zigbee
Quy trình thêm:
1. Kiểm tra network
2. Pairing
3. Thêm thiết bị

### Kiểm tra network
```kotlin
val handler = SmartSdk.configZigbeeHandler()
handler.checkNetworkAvailable(callback)
```
#### Đối số
- callback: Là một ```GetZigbeeAvailableCallback``` dùng để lắng nghe id thiết bị mỗi khi tìm thấy một thiết bị khả dụng
### Huỷ kiểm tra network
```kotlin
handler.cancelCheckNetworkAvailable()
```
### Pairing
```kotlin
val handler = SmartSdk.configZigbeeHandler()
handler.startPairingZigbee(gatewayId, second, deviceType, onDeviceFound)
```
#### Đối số
- gatewayId: id của thiết bị trung gian khả dụng được tìm thấy khi gọi ```checkNetworkAvailable```
- second: khoảng thời gian thiết bị trung gian quét thiết bị 
- deviceType: ```IoTDeviceType```.  Đối với những USB Zigbee không cần có kiểu thiết bị truyền 0
- onDeviceFound: ```DiscoveryIoTZigbeeCallback``` gọi khi tìm thấy thiết bị đang sẵn sàng kết nối

### Huỷ pairing
```kotlin
handler.stopPairingZigbee(gatewayId)
```
#### Đối số
- gatewayId: id của thiết bị trung gian

### Thêm thiết bị Zigbee
Sau khi đã tìm thấy thiết bị
```kotlin
 val handler = SmartSdk.configZigbeeHandler()
  handler.addZigbeeDevice(gatewayId, setupDeviceInfo, callback)
```
#### Đối số
- gatewayId: id của thiết bị trung gian khả dụng được tìm thấy khi gọi ```checkNetworkAvailable```
- setupDeviceInfo: ```SetupDeviceInfo``` Thông tin thiết bị
- callback: ``` SuccessStatusCallback``` có dạng:

```kotlin
  val callback = object : SuccessStatusCallback {
        override fun onFailure(code: Int, message: String?){

        }

        override fun onSuccess() {
            
        }

    }
```