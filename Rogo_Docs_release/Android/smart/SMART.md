# Rogo Smart SDK -  Android

```kotlin
val handler = SmartSdk.smartFeatureHandler()
```

## Scenario

### 1. Tạo Kịch bản
```kotlin
handler.createSmartScene(label, sceneType, ownerId, requestCallback)
```
#### Params
- label:```String``` Tên kịch bản
- sceneType:```Int``` loại kịch bản
- ownerId:```String``` id 
- requestCallback: ```RequestCallback<IoTSmart>```

### 6. Kích hoạt kịch bản
```kotlin
handler.activeSmart(smartId)
```
#### Params
- smartId: uuid của ```IoTSmart```


## Schedule

### 1. Tạo lập lịch
```kotlin
handler.createSmartSchedule(label, ownerId, requestCallback)
```
#### Params
- label:```String``` Tên lập lịch
- ownerId:```String``` id
- requestCallback: ```RequestCallback<IoTSmart>```

### 2. get danh sách lập lịch theo smart id
```kotlin
handler.getSmartSchedule(smartId)
```
#### Return ```Collection<IoTSmartSchedule>```
#### Params
- smartId: uuid của ```IoTSmart```

### 3. get tất cả lập lịch 
```kotlin
handler.getSmartSchedules()
```
#### Return ```Collection<IoTSmartSchedule>```

### 4. Thêm hẹn giờ vào lập lịch
```kotlin
handler.bindSmartSchedule(smartId, timeSchedule, daySchedules, requestCallback)
```
#### Params
- smartId:```String``` uuid của ```IoTSmart```
- timeSchedule:```Int``` thời điểm kích hoạt trong ngày (tính = phút)
- daySchedules:```IntArray``` ngày trong tuần (cn đến t7 tương ứng 0 đến 6)
- requestCallback:```RequestCallback<IoTSmartSchedule>```

### 5. Rebound hẹn giờ vào lập lịch
```kotlin
handler.reboundSmartSchedule(smartId, smartScheduleId, timeSchedule, daySchedules, requestCallback)
```
#### Params
- smartId:```String``` uuid của ```IoTSmart```
- smartScheduleId:```String``` uuid của ```IoTSmartSchedule```
- timeSchedule:```Int``` thời điểm kích hoạt trong ngày (tính = phút)
- daySchedules:```IntArray``` ngày trong tuần (cn đến t7 tương ứng 0 đến 6)
- requestCallback:```RequestCallback<IoTSmartSchedule>```

### 6. Xóa hẹn giờ khỏi lập lịch
```kotlin
handler.unboundSmartSchedule(smartScheduleId, requestCallback)
```
#### Params
- smartScheduleId:```String``` uuid của ```IoTSmartSchedule```
- requestCallback:```RequestCallback<IoTSmartSchedule>```


## Automation

### 1. Tạo tự động hóa
```kotlin
handler.createSmartAutomation(label, automationType, timeJob, ownerId, requestCallback)
```
#### Params
- label:```String``` Tên tự động hóa
- automationType:```Int``` loại tự động hóa
- timeJob:```IntArray``` (chưa sử dụng) mặc đinh truyền mảng rỗng intArrayOf()
- ownerId:```String``` (chưa sử dụng) mặc đinh truyền null
- requestCallback: ```RequestCallback<IoTSmart>```

### 2. Thêm trigger tự động hóa
```kotlin
handler.bindSmartTrigger(smartTrigger, requestCallback)
```
#### Params
- smartTrigger: ```IoTSmartTrigger```
- requestCallback: ```RequestCallback<IoTSmartTrigger>```

### 3. Xóa trigger khỏi smart tự động hóa
```kotlin
handler.unboundSmartTrigger(triggerId, requestCallback)
```
#### Params
- triggerId: uuid của ```IoTSmartTrigger```
- requestCallback: ```RequestCallback<IoTSmartTrigger>```

### 4. Get danh sách trigger của smart
```kotlin
handler.getSmartTriggers(smartId)
```
#### Params
- smartId:```String``` uuid của ```IoTSmart```
#### Return ```Collection<IoTSmartTrigger>```

### 5. Get smart triggers theo thiết bị
```kotlin
handler.getSmartTriggerByDeviceId(deviceId)
```
#### Params
- deviceId:```String``` uuid của ```IoTDevice```
- #### Return ```Collection<IoTSmartTrigger>```

### 6. Get smart trigger theo thiết bị và smart
```kotlin
handler.getSmartTriggerByDeviceId(smartId, deviceId)
```
#### Params
- smartId:```String``` uuid của ```IoTSmart```
- deviceId:```String``` uuid của ```IoTDevice```
#### Return ```IoTSmartTrigger```

### 7. Get tất cả smart triggers
```kotlin
handler.getAllTriggers()
```
#### Return ```Collection<IoTSmartTrigger>```

### 8. Tạo smart công tắc cầu thang
```kotlin
handler.bindSmartFeatureStairSwitch(smartStairSwitch, requestCallback)
```
#### Params
- smartStairSwitch:```IoTSmartStairSwitch``` uuid của ```IoTSmart```
- requestCallback: ```RequestCallback<IoTSmartStairSwitch>```

### 9. Tạo smart push thông báo
```kotlin
handler.bindSmartFeatureNotification(ioTSmartNotification, requestCallback)
```
#### Params
- ioTSmartNotification:```IoTSmartNotification``` uuid của ```IoTSmart```
- requestCallback: ```RequestCallback<IoTSmartNotification>```

### 10. Tạo smart đảo ngược on/off
```kotlin
handler.bindSmartFeatureReverseOnOff(ioTSmartSelfReverse, requestCallback)
```
#### Params
- ioTSmartSelfReverse:```IoTReverseOnOff``` uuid của ```IoTSmart```
- requestCallback: ```RequestCallback<IoTReverseOnOff>```

### 11. Tạo smart không phát hiện chuyển động
```kotlin
handler.bindSmartFeatureMotionNotDetected(ioTSmartMotionNotDetected, requestCallback)
```
#### Params
- ioTSmartMotionNotDetected:```IoTSmartMotionNotDetected``` uuid của ```IoTSmart```
- requestCallback: ```RequestCallback<IoTSmartMotionNotDetected>```

### 12. Tạo smart điều hòa (developing...)
```kotlin
handler.bindSmartFeatureAirConditioner(ioTSmartAirConditioner, requestCallback)
```
#### Params
- ioTSmartAirConditioner:```IoTSmartAirConditioner``` uuid của ```IoTSmart```
- requestCallback: ```RequestCallback<IoTSmartAirConditioner>```

### 13. Tạo smart đèn WC (developing...)
```kotlin
handler.bindSmartFeatureWcLighting(ioTSmartWcLighting, requestCallback)
```
#### Params
- ioTSmartWcLighting:```IoTSmartWcLighting``` uuid của ```IoTSmart```
- requestCallback: ```RequestCallback<IoTSmartWcLighting>```

### 14. Tạo smart công tắc cảnh dimmer (developing...)
```kotlin
handler.bindSmartDimmerScene(IoTSmartDimmerScene, requestCallback)
```
#### Params
- ioTSmartWcLighting:```IoTSmartWcLighting``` uuid của ```IoTSmart```
- requestCallback: ```RequestCallback<IoTSmartDimmerScene>```

### 15. Get smart công tắc cầu thang
```kotlin
handler.getSmartFeatureStairSwitch(smartId)
```
#### Params
- smartId:```String``` uuid của ```IoTSmart```
#### Return ```IoTSmartStairSwitch```

### 16. Get smart push thông báo
```kotlin
handler.getSmartFeatureNotification(smartId)
```
#### Params
- smartId:```String``` uuid của ```IoTSmart```
#### Return ```IoTSmartNotification```

### 17. Get danh sách smart push thông báo của 1 thiết bị
```kotlin
handler.getSmartFeaturesNotifications(deviceId)
```
#### Params
- deviceId:```String``` uuid của ```IoTDevice```
#### Return ```Collection<IoTSmartNotification>```

### 18. Get smart đảo ngược on/off
```kotlin
handler.getSmartReverseOnOff(smartId)
```
#### Params
- smartId:```String``` uuid của ```IoTSmart```
#### Return ```IoTReverseOnOff```

### 19. Get smart không phát hiện chuyển động
```kotlin
handler.getSmartFeatureMotionNotDetected(smartId)
```
#### Params
- smartId:```String``` uuid của ```IoTSmart```
- #### Return ```IoTSmartMotionNotDetected```

### 20. Get smart công tắc cảnh dimmer (developing...)
```kotlin
handler.getSmartDimmerScene(smartId)
```
#### Params
- smartId:```String``` uuid của ```IoTSmart```
- #### Return ```IoTSmartDimmerScene```

### 21. Get smart điều hòa (developing...)
```kotlin
handler.getSmartFeatureAc(smartId)
```
#### Params
- smartId:```String``` uuid của ```IoTSmart```
- #### Return ```IoTSmartAirConditioner```

### 22. Get danh sách automation theo feature
```kotlin
handler.getAllAutomationFeature(clazz)
```
#### Params
- clazz:```Class<E>``` generic class ```IoTSmartNotification.class```, ```IoTSmartStairSwitch.class```, ```IoTSmartMotionNotDetected.class```
- #### Return ```Collection<E>```

## Smart

### 1. Thêm đối tượng điều khiển vào smart
```kotlin
handler.bindDeviceSmartCmd(smartId, devId, cmds, requestCallback)
```
#### Params
- smartId:```String``` uuid của ```IoTSmart```
- devId:```String``` uuid của thiết bị ```IoTDevice```
- cmds:```HashMap<Int, IoTTargetCmd>``` key = element của thiết bị, value = đối tượng IoTTargetCmd
- requestCallback:```RequestCallback<IoTSmartCmd?>```

### 2. Xóa đối tượng điều khiển khỏi smart
```kotlin
handler.unboundSmartCmd(smartCmdId, requestCallback)
```
#### Params
- smartId:```String``` uuid của ```IoTSmartCmd```
- requestCallback:```RequestCallback<IoTSmartCmd>```

### 3. Set nhãn smart
```kotlin
handler.setSmartLabel(smartId, label, requestCallback)
```
#### Params
- smartId:```String``` uuid của ```IoTSmart```
- label:```String``` nhãn smart
- requestCallback:```RequestCallback<IoTSmart>```

### 4. Get danh sách cmd của smart
```kotlin
handler.getSmartCmds(smartId)
```
#### Params
- smartId:```String``` uuid của ```IoTSmart```
#### Return ```Collection<IoTSmartCmd>```

### 5. Gửi request sync (refresh) smart đến gateway
```kotlin
handler.requestCheckSync(smartId)
```
#### Params
- smartId:```String``` uuid của ```IoTSmart```

### 6. get tất cả smart
```kotlin
handler.all
```
#### Return ```Collection<IoTSmart>```

### 7. get smart theo type
```kotlin
handler.getSmartByType(type)
```
#### Params
- type:```Int``` type smart  ```IoTSmartType```
#### Return ```Collection<IoTSmart>```

### 8. delete
- xóa smart
```kotlin
handler.delete(smartId, requestCallback)
```
#### Params
- smartId: ```String``` uuid của IoTSmart
- requestCallback:```RequestCallback<Boolean>```

### 9. set enable smart
handler.disableSmart(smartId, isEnable, successStatusCallback)
#### Params
- smartId:  ```String``` uuid của IoTSmart
- isEnable : ```Int``` -1 (enable), 0 (disable)

## NOTE
- trigger: thiết bị điều kiện để kích hoạt automation
- cmd : element của thiết bị là đối tượng điều khiển

### RequestCallback
```kotlin
object : RequestCallback<T> {
    override fun onSuccess(success: T) {
    }

    override fun onFailure(code: Int, message: String?) {
    }
}
```

### IoTTargetCmd (cập nhật mới có thể dùng CmdGenerator)
- delay: ```Int``` thời gian delay thực thi lệnh smart (giây)
- reversing: ```Int``` thời gian đảo ngược lệnh tính = giây (0 = không đảo ngược) (hiện tại chỉ hỗ trợ với lệnh on-off)
- cmd: ```IntArray``` lệnh smart
- chi tiết cmd:
```kotlin
//power on-off
cmd = intArrayOf(
    IoTAttribute.ONOFF,
    if (isOn) IoTCmdConst.POWER_ON else IoTCmdConst.POWER_OFF
)
//open-close
val state = IoTCmdConst.OPENCLOSE_MODE_CLOSE || IoTCmdConst.OPENCLOSE_MODE_OPEN || IoTCmdConst.OPENCLOSE_MODE_STOP || IoTCmdConst.OPENCLOSE_MODE_MOVING
cmd = intArrayOf(IoTAttribute.OPEN_CLOSE_CTL, state)

//nhiệt độ màu
cmd = intArrayOf(IoTAttribute.BRIGHTNESS_KELVIN, brightness, kelvin)

//đổi màu
val hsv = [h, s, v]     // màu HSV
cmd = intArrayOf(IoTAttribute.COLOR_HSV, hsv[0], hsv[1], hsv[2])

//điều hòa
cmd = intArrayOf(IoTAttribute.AC, if (isOn) IoTCmdConst.POWER_ON else IoTCmdConst.POWER_OFF, mode, temp, fan, swing, extra)
```

### IoTSmartTrigger
- ```String``` uuid:
- ```String``` smartId: uuid của ioTSmart
- ```int``` type: loại trigger (```IoTTriggerType.OWNER``` trigger chính, ```IoTTriggerType.EXT``` trigger bổ sung)
- ```String``` devId: uuid thiết bị làm trigger
- ```int``` elm: element kích hoạt trigger của thiết bị
- ```int``` condition: điều kiện kích hoạt trigger ```IoTCondition```
- ```int[]``` value: giá trị kích hoạt trigger (```['feature', 'value']```)
- ```int``` elmExt: element kích hoạt trigger bổ sung của thiết bị
- ```int``` conditionExt: điều kiện kích hoạt trigger ext
- ```int[]``` valueExt:giá trị kích hoạt trigger ext
- ```int[]``` timeCfg: cấu hình time cho trigger (```['ioTTriggerTimeCfg','timeValue']``` đơn vị giây)
  - ```IoTTriggerTimeCfg.REVERSE_TIME``` thời gian để trigger trở lại trạng thái trước đó
  - ```IoTTriggerTimeCfg.MIN_TIME``` thời gian tối thiểu giữa 2 lần trigger (ví dụ giãn cách giữa 2 lần trigger thông báo)
  - ```IoTTriggerTimeCfg.WAITING_TIME``` thời gian đợi đến lần trigger tiếp theo (ví dụ sau khi phát hiện chuyển động thì 1 thời gian sau mới trigger tiếp)
- ```int[]``` timeJob: khoảng thời gian hoạt động của automation (```['startTime', 'endTime']``` phút trong ngày)
- ```String``` userId: id user
- ```String``` locId: id location
- ```int``` zone:  // and - or for condition

### IoTSmartCmd
- ```String``` uuid: uuid 
- ```String``` smartId: uuid của iotSmart
- ```String``` targetId; //SoftKey - Id's scene|rule|schedule|device|group|location
- ```int``` target; //Type's scene|rule|schedule|device|group|location
- ```int``` filter;//Target DeviceType
- ```HashMap<Integer, IoTTargetCmd>``` cmds; //Element - AttrCmd
- ```int``` cfm; // confirm num for check sync between
- ```String``` linkId; //This use in rule or schedule for 3rd device
- ```int``` linkType; //This use in rule or schedule for 3rd device
- ```String``` ownerId;
- ```String``` userId;

### IoTSmartSchedule

### IoTSmartStairSwitch


### IoTSmartNotification
### IoTReverseOnOff
### IoTSmartMotionNotDetected
### IoTSmartAirConditioner
### IoTSmartWcLighting
### IoTSmartDimmerScene
### IoTTriggerTimeCfg





