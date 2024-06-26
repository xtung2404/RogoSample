# Rogo Smart SDK -  Android
Điều khiển thiết bị

### Điều khiển Power
```kotlin
val handler = SmartSdk.controlHandler()
 handler.controlDevicePower(uuidDev, element, isOn)
```

- uuidDev: ```String```  id của thiết bị
- element: ```Int``` id của element
- isOn: ```Boolean``` bật hoặc tắt

```kotlin
handler.controlDevicePower(uuidDev, elements, isOn)
```
- uuidDev: ```String```  id của thiết bị
- elements: ```IntArray``` id của các elements muốn điều khiển
- isOn: ```Boolean``` bật hoặc tắt

```kotlin
handler.controlDevicePower(ioTDevice, isOn)
```
- ioTDevice: ```IoTDevice``` Thiết bị muốn điều khiển
- isOn: ```Boolean``` bật hoặc tắt

### Điều khiển group power
```kotlin 
 handler.controlGroupPower(id, isOn, deviceType)
```

- id: ```String```  id của group
- isOn: ```Boolean``` bật hoặc tắt
- deviceType ```Int``` Loại thiết bị muốn điều khiển

### Điều khiển bộ điều khiển động cơ
```kotlin
    handler.controlMotorCurtain(id, isGroupTarget, mode)
```

id: ```String``` id của thiết bị hoặc id của group
isGroupTarget: ```Boolean``` có phải đang muốn điều khiển nhóm hay không
mode: ```Int``` chế độ điều khiển - xem tại class ```ValOpenCloseStopMoving```

### Điều khiển các thiết bị đèn

#### Điều khiển độ sáng và nhiệt dộ màu (Brightness - Kelvin)
```kotlin
 handler.controlLightBrightness(id, isGroupTarget, b, k)
```
id: ```String``` id của thiết bị hoặc id của group
isGroupTarget: ```Boolean``` có phải đang muốn điều khiển nhóm hay không
b: ```Float``` hoặc ```Int``` Độ sáng. Nếu ```Float``` 0.1f -> 1.0f. Nếu ```Int```  0 -> 1000
k: ```Float``` hoặc ```Int``` Nhiệt độ màu. Nếu ```Float``` 0.1f -> 1.0f. Nếu ```Int```  2200 -> 6500

#### Điều khiển màu HSV 
```kotlin
   handler.controlLightHsv(id, isGroupTarget, hsv, null)
```

id: ```String``` id của thiết bị hoặc id của group
isGroupTarget: ```Boolean``` có phải đang muốn điều khiển nhóm hay không
hsv: ```FloatArray``` thông tin HSV - Tham khảo http://color.lukas-stratmann.com/color-systems/hsv.html

### Kích hoạt kịch bản

```kotlin
    handler.activeScenario(uuid)
```

uuid: ```String``` id của kịch bản cần kích hoạt