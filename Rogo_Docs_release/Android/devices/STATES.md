# Rogo Smart SDK -  Android
Lấy thông tin thiết bị


## Ping device
ping trạng thái thiết bị trước khi get
```kotlin
SmartSdk.stateHandler().pingDeviceState(deviceId)
```

## Get object state
Để lấy trạng thái thiết bị
```kotlin
val objState:IoTObjState = SmartSdk.stateHandler().getObjState(devId)
```

### Các phương thức của ```IoTObjState```

- Power
```kotlin
// Thiết bị đang bật - tắt
objState.isOn();

// Element của thiết bị đang bật - tắt
objState.isOn(elementId);
```

- Các trạng thái của đèn
```kotlin

// Độ sáng
objState.getDim();

// Nhiệt độ màu
objState.getKelvin();

// Màu sắc hiện tại hsv
objState.getHsv();
```

- Nhiệt độ - độ ẩm
```kotlin

// Nhiệt độ
objState.getTemp();

// Độ ẩm
objState.getHumid();

```

- Đóng mở, gắn tường
```kotlin

// Đóng mở
objState.isDoorOpen(elementId);

// Độ ẩm
objState.isWallMounted(elementId);

```
- Lux
```kotlin

objState.getLux();

```

- Battery
```kotlin

objState.getBattery();

```

- Khoá
```kotlin

objState.isLocked(elementId);

```

- Trạng thái điều hoà
```kotlin
// Chế độ
objState.getMode();

// Nhiệt độ
objState.getTempSet();

// Tốc độ quạt
objState.getFanSpeed();


```
### Ping Log sensor device
Ping đến thiết bị cần lấy log (cần để SmartSdkDeviceStateCallback() hoạt động)
```kotlin
SmartSdk.stateHandler().pingLogSensorDevice(devId, elm, attr)
```
```kotlin
SmartSdk.stateHandler().pingLogSensorDevice(devId, elm, attr, year, day)
```
#### Params
- devId: ```String``` uuid của thiết bị 
- elm: ```Int``` element của thiết bị
- attr: ```Int``` attribute: loại cảm biến
- year, day: ```Int```

### Get sensor log
Để lấy log thiết bị
```kotlin
val ioTSensorLog = SmartSdk.stateHandler().getSensorLog(devId, elm, attr)
```
```kotlin
val ioTSensorLog = SmartSdk.stateHandler().getSensorLog(devId, elm, attr,  cp)
```

#### Params
- devId: ```String``` uuid của thiết bị
- elm: ```Int``` element của thiết bị
- attr: ```Int``` attribute: loại cảm biến
- cp: ```Int``` current part: vị trí đoạn log hiện tại đang lấy

#### return ```IoTLogSensor```
- ioTSensorLog.cp```Int``` current part: vị trí data log
- ioTSensorLog.pp```Int``` previous part: vị trí data log trước
- ioTSensorLog.data```IntAray``` dữ liệu log

#### Đọc data
Nhiệt độ - độ ẩm
```kotlin
//nhiệt độ, độ ẩm
IoTAttribute.TEMP_HUMID_EVT
val step = 3 //chia mảng data thành các đoạn 3 giá trị tương ứng:
data[pos + 0] = minute Of Day
data[pos + 1] = nhiệt độ
data[pos + 2] = độ ẩm

//Cảm biến cửa  
IoTAttribute.OPEN_CLOSE_EVT
step = 2
data[pos + 0] = minute Of Day
data[pos + 1] = giá trị đóng-mở (mở = 1)

//motion
IoTAttribute.MOTION_EVT
step = 2
data[pos + 0] = minute Of Day
data[pos + 1] = giá trị (phát hiện chuyển động = 1)

//occupancy
IoTAttribute.LOCK_UNLOCK
step = 2
data[pos + 0] = minute Of Day
data[pos + 1] = giá trị (locked = 1)

//occupancy
IoTAttribute.PRESENSCE_EVT
step = 2
data[pos + 0] = minute Of Day
data[pos + 1] = giá trị (phát hiện = 1)
```

### Cập nhật thông tin thời gian thực
```kotlin
val callback = object : SmartSdkDeviceStateCallback(){}

/**
 * register [smartSdkDeviceStateCallback]
 * note: call this after your view init successful, your app will be crash if in any event has function call to UI (but UI was not created)
 */
SmartSdk.registerDeviceStateCallback(callback)

/**
 * unregister [smartSdkDeviceStateCallback]
 * note: call this after your view destroy, your app will be crash if in any event has function call to UI (but UI destroyed)
 */
SmartSdk.unregisterDeviceStateCallback(callback)
```
#### Note callback
```kotlin
override fun onDeviceLogSensorUpdated(devId: String?, element: Int, attrs: Int)
override fun onDeviceStateUpdated(devId: String)
override fun onEventAttrStateChange(devId: String?, attrs: Int, values: IntArray?)
```
#### Params
- devId: uuid thiết bị
- element: element của thiết bị
- attrs: thuộc tính cảm biến
- values: dữ liệu cảm biến
