# Rogo Smart SDK -  Android
Lấy thông tin thiết bị

### Lấy tất cả các thiết bị

```kotlin
val deviceHandler: DeviceHandler = SmartSdk.deviceHandler()
val list:Collection<IoTDevice> = deviceHandler.all
```

### Lấy danh sách thiết bị theo groupId (String)
```kotlin
val list = deviceHandler.getByGroup(groupId)
```
### Lấy thông tin thiết bị
```kotlin
    val device: IoTDevice = deviceHandler.get(uuid)
```
- uuid: ```String``` id của thiết bị cần lấy
### Cập nhật nhãn thiết bị

```kotlin
deviceHandler.updateDeviceLabel(id, label, elementLabels, callback)
```
#### Đối số
- id: ```String``` id của thiết bị
- label: ```String``` label mới
- elementLabels: ```HashMap<Int, String>``` label của các element có trong thiết bị. Key : element id, Value: label mới của element
- callback: ```RequestCallback``` có dạng

```kotlin
val callback = object : RequestCallback<IoTDevice> {
        override fun onSuccess(device: IoTDevice){

        }
        override fun onFailure(code: Int, message: String?){

        }
    }

```

### Xoá thiết bị
```kotlin
        deviceHandler.delete(uuid, callback)
```
#### Đối số
- uuid: ```String``` id của thiết bị muốn xoá
- callback: ```RequestCallback``` có dạng
```kotlin
val callback = object : RequestCallback<Boolean> {
        override fun onSuccess(success: Boolean){

        }
        override fun onFailure(code: Int, message: String?){

        }
    }

```