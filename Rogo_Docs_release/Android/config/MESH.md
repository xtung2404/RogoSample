# Rogo Smart SDK -  Android

## Lưu ý trước khi thực hiện config thiết bị
- Bắt buộc phải kiểm tra gateway - thiết bị trung tâm có đang available hay không trước khi bắt đầu quá trình cấu hình thiết bị

## Cấu hình thiết bị Mesh
Quy trình thêm:
1. Kiểm tra mesh gateway
2. discovery
3. Tiền xử lý
4. Thêm thiết bị

### Kiểm tra mesh gateway
```kotlin
 val handler = SmartSdk.configMeshHandler()
 handler.checkMeshGatewayAvailable(onDeviceAvailable)
```
#### Đối số
onDeviceAvailable: ```GetDeviceAvailableCallback``` callback gọi mỗi khi lấy được id của một gateway khả dụng

#### Huỷ
```kotlin
 handler.cancelCheckMeshGatewayAvailable()
 ```
### Discovery
```kotlin
 val handler = SmartSdk.configMeshHandler()
handler.discoveryMeshDevice(onMeshDeviceFound)
```
#### Đối số
onMeshDeviceFound: ```DiscoveryIoTBleCallback``` gọi mỗi khi tìm thấy một thiết bị khả dụng
#### Stop discovery
```kotlin
handler.stopDiscovery()
```

### Tiền xử lý
```kotlin
 val handler = SmartSdk.configMeshHandler()
handler.preSetupDevice(gatewayId, uuidMesh, callback)
```
#### Đối số
- gatewayId: id của thiết bị trung gian khả dụng được tìm thấy khi gọi ```checkMeshGatewayAvailable```
- uuidMesh: id của mesh được chứa trong đối tượng ```IoTBleScanned```
- callback: ```SetupDeviceCallback``` có dạng:

```kotlin

val callback: SetupDeviceCallback = object : SetupDeviceCallback {
        override fun onProgress(mac: String?, progress: Int, message: String) {
            //Gọi mỗi khi tiến hành setup
            // progress = 20 là khi thiết bị đang trong trạng thái Identify và sẵn sàng để cài đặt

        }

        override fun onSuccess() {
        }

        override fun onSetupFailure(code: Int, mess: String?) {
            
        }

    }
```
### Bắt đầu thêm thiết bị

> Chỉ gọi hàm này khi thiết bị trong trạng thái Identify (quá trình tiền xử lý đạt 20% )

```kotlin
 val handler = SmartSdk.configMeshHandler()
handler.startSetupDevice(setupDeviceInfo)
```
#### Đối số
setupDeviceInfo: ```SetupDeviceInfo``` chứa thông tin của thiết bị muốn thêm

#### Huỷ thiết lập
```kotlin
handler.cancelSetupMeshDevice()
```