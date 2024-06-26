# Rogo Smart SDK -  Android

## Lưu ý trước khi thực hiện config thiết bị
- Kiểm tra có thiết bị IR available trước khi bắt đầu 

[//]: # (## Cấu hình remote)

[//]: # (- Raw: chọn remote từ các brand có sẵn)

[//]: # (- Protocol: Là 1 mã code gửi đến hub để hub gen ra mã raw)

[//]: # (- IR detect: detect raw sang protocol)

Loại remote hỗ trợ:
- TV: Raw, Protocol
- Fan: Raw
- AC: Raw, Protocol, IR detect

## 1. Raw
#### 1.1 Thêm từ điều khiển có sẵn
1. ```getSupportManufacturers()```: Lấy tất cả các hãng support ir remote
2. ```getIrRemotes()```: Lấy tất cả remote raw đang khả dụng
3. ```preloadTestIrRemote()```: Trước khi test cần preload -> để load remote lấy từ hàm tại bước 2
4. Đối với AC dùng hàm ```testAcIrRemote()``` còn TV và Fan sử dụng ```testIrRemote()```
5. Xác nhận điều khiển khả dụng ->
```void addIrRemote(String irDeviceId, IoTIrRemote ioTIrRemote, String deviceLabel, String groupId, SuccessCallback<IoTDevice> successStatusCallback);```
6. Gọi ```stopLearnIr()``` để clear toàn bộ dữ liệu

#### 1.2 Học lệnh raw TV và Quạt
1. Chọn mã nút cần học thông qua ```Quạt: IoTIrCodeFan```,```TV: IoTIrCodeTV```
2. Gọi ```learnIr(String irDeviceId, int code, LearnIrCallback learnIrCallback)```
3. Sau khi học hết tất cả các lệnh cần học ->
```addIrRemoteLearned(String irDevId, String deviceLabel, int manufacturer, int deviceType, String groupId, SuccessCallback<IoTDevice> successStatusCallback)```
4. Gọi ```stopLearnIr()``` để clear toàn bộ dữ liệu

## 2. Protocol
Mỗi hãng có thể có nhiều protocol, một protocol có thể điều khiển nhiều điều hoà.

#### 2.1 Từ protocol có sẵn
1. Lấy tất cả protocol xem tại ```IoTIrPrtc.getPrtcAcByManufacturer(IoTManufacturer)``` 
2. Test protocol xem có khớp với điều hoà hay không ```ConfigIrDeviceHandler.testAcIrPrtc()```
3. Xác nhận protocol khả dụng ->
```void addAcIrRemote(String gatewayUuid, String deviceLabel, String groupId, IoTIrProtocolInfo irProtocol, IoTIrRemote ioTIrRemote, SuccessCallback<IoTDevice> successStatusCallback)```
4. Gọi ```stopLearnIr()``` để clear toàn bộ dữ liệu

#### 2.2 Từ phát hiện điều khiển
1. Gọi lệnh ```learnIr()``` -> nếu detect được được Protocol từ hàm ```onIrProtocolDetected``` của callback có thể gọi 
```void addAcIrRemote(String gatewayUuid, String deviceLabel, String groupId, IoTIrProtocolInfo irProtocol, IoTIrRemote ioTIrRemote, SuccessCallback<IoTDevice> successStatusCallback)```

### 3. Handler
```kotlin
val handler = SmartSdk.learnIrDeviceHandler()
```

#### 3.1 Kiểm tra thiết bị IR available
```kotlin
handler.checkIrHubAvailable(checkIrHubInfoCallback)
```
#### 3.2 Dừng kiểm tra IR available
```kotlin
handler.stopCheckIrHubAvailable()
```

#### 3.3 Get các hãng thiết bị được hỗ trợ
```kotlin
handler.getSupportManufacturers(deviceType)
```

#### 3.4 Get remote theo brand 
```kotlin
handler.getIrRemotes(manufacture, deviceType, requestCallback)
```
##### Đối số
requestCallback: ```RequestCallback<Collection<IoTIrRemote>>```
```IoTIrRemote```:
tempRange: ```int[]``` Khoảng nhiệt độ
modes: ```int[]``` các chế độ của remote (lấy từ ```IoTCmdConst```)
fans: ```int[]``` các mức tốc độ gió (lấy từ ```IoTCmdConst```)
tempAllowIn: ```int[]``` danh sách mode cho phép điều chỉnh nhiệt độ
fanAllowIn: ```int[]``` danh sách mode cho phép điều chỉnh tốc độ gió


#### 3.5 preloadTestIrRemote
```kotlin
handler.preloadTestIrRemote(manufacture, deviceType, remoteId, onSuccess)
```

#### 3.6 Kiểm tra ir đã học đúng chức năng nút chưa (dành cho TV, quạt)
```kotlin
handler.testIrRemote(devId, rid, irc, onSuccess)
```
##### Đối số
devId: id của ir
rid: id remote
isOn: on/off điều hòa
mode: mode của điều hòa
temp: nhiệt độ điều hòa
fan: tốc độ quạt
onSuccess: ```successStatus```

#### 3.7 Kiểm tra ir đã học đúng chức năng nút chưa (dành cho điều hòa)
```kotlin
handler.testAcIrRemote(devId, rid, isOn, mode, temp, fan, onSuccess)
```
##### Đối số
devId: id của ir
rid: id remote
isOn: on/off điều hòa
mode: mode của điều hòa
temp: nhiệt độ điều hòa
fan: tốc độ quạt
onSuccess: ```successStatus```

#### 3.8 Thêm remote raw (remote có sẵn từ database)
```kotlin
handler.addIrRemote(irHubId, remote, deviceLabel, groupId, callback)
```
##### Đối số
irHubId: id của Ir
remote: ```IoTIrRemote.Raw```
deviceLabel: Nhãn remote
groupId: id nhóm phòng
callback: ```SuccessStatusCallback``` success callback

#### 3.9 Thêm remote protocol (học từ remote vật lý)
```kotlin
handler.addAcIrRemote(irHubId, deviceLabel, groupId, ioTIrProtocol, ioTIrRemote, callback)
```
##### Đối số
irHubId: id của Ir
deviceLabel: Nhãn remote
groupId: id nhóm phòng
ioTIrProtocol: ```IoTIrProtocolInfo```
ioTIrRemote: ```IoTIrRemote```
callback: ```SuccessStatusCallback``` success callback

#### 3.10 Học từng nút
```kotlin
handler.learnIr(irDeviceId, code, learnIrCallback)
```
- gọi hàm này để thiết bị vào trạng thái nhận tín hiệu protocol

##### Đối số
- irDeviceId: ```String``` uuid của thiết bị hub (IR)
- code: ```Int``` mã protocol của nút cần học (define trong ```IoTIrCodeTV``` cho TV hoặc ```IoTIrCodeFAN``` cho quạt)
- learnIrCallback: Khi thiết bị (IR) nhận được protocol sẽ trả về callback ```LearnIrCallback.onIrRawLearned```

##### ```LearnIrCallback```
```kotlin
learnIrCallback.onIrRawLearned(requestId, protocolInfo)
```
- requestId: ```Int``` = code ở ```handler.learnIr```
- protocolInfo: ```IoTIrProtocolInfo?``` 
- Lưu ý: check protocolInfo.irp của nút đã học có giống nhau hay không để chắc chắn đang học nút từ cùng 1 remote

#### 3.11 Tạo remote từ các nút đã học
```kotlin
handler.addIrRemoteLearned(irDevId, deviceLabel, manufacturer, deviceType, groupId, protocolInfo, successStatusCallback)
```
- tạo thiết bị remote với các nút đã học từ hàm learnIr

##### Đối số
irDevId:```String``` id của hub (IR)
deviceLabel:```String``` Nhãn thiết bị
manufacturer:```Int``` có thể lấy từ protocolInfo
deviceType:```Int``` device type
groupId:```String``` id nhóm phòng
protocolInfo: ```IoTIrProtocolInfo``` protocolInfo từ hàm ```learnIr```
callback: ```SuccessStatusCallback``` success callback

#### Stop learn ir
```kotlin
handler.stopLearnIr()
```
- Dừng quá trình học lệnh


#### Các class

###### ```IoTIrProtocolInfo```
* ```irp```: ```int``` đối số bắt buộc - đối với protocol có sẵn -> tạo object ```IoTIrProtocolInfo``` với irp = protocol được chọn


###### ```IoTIrRemote```
* ```tempRange```: ```int[]``` Nhiệt độ min - max
* ```modes```: ```int[]``` Mode hoạt động (Cool, Fan...)
* ```fans```: ```int[]``` Chế độ hoạt động của quạt
* ```swings```: ```int[]``` Các chế độ xoay
* ```tempAllowIn```: ```int[]``` Nhiệt độ có thể điều khiển trong những mode nào
* ```fanAllowIn```: ```int[]``` Quạt có thể điều khiển trong những mode nào
* ```keys```: ```int[]``` Đối với các thiết bị quạt và tv

###### ```LearnIrCallback```
* ```onRequestLearnIrStatus```: Gọi khi gửi lệnh xuống thiết bị gateway thành công
* ```onIrRawLearned(requestId: Int, protocolInfo: IoTIrProtocolInfo?)```: Hàm này được gọi khi học lệnh raw thành công
    * ```requestId```: ```Int``` mã code của lệnh muốn học tuỳ theo loại thiết bị ```Quạt: IoTIrCodeFan```,```TV: IoTIrCodeTV```
    * ```protocolInfo```: ```IoTIrProtocolInfo``` Protocol của remote (khi hàm này được gọi -> protocol thường là -1)
* ```onIrProtocolDetected(irProtocol: IoTIrProtocolInfo?)```: Gọi khi có 1 protocol được support được phát hiện (hiện tại chỉ support AC), -> có thể thêm remote ngay
* ```onFailure(requestId: Int, errorCode: Int, msg: String?)```: Gọi khi có lỗi xảy ra

