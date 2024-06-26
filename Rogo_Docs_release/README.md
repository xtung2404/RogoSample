# Rogo-Sdk-Document

### Android Release note

### 1.1.0-alpha release

#### Deprecated
```
- IoTProduct
- IoTProductId
- IoTProductExtra
```
=> Thay thế bằng IoTProductModel

#### Thay đổi
- Hỗ trợ xác thực sử dụng appKey và app secret
- Đổi tên IoTFeature -> IoTAttribute
- Hỗ trợ tính năng OTA cho các thiết bị WILE
- Hỗ trợ điều chỉnh setting của thiết bị (nếu có)
- Thêm Kotlin Coroutine
- Các thiết bị mới
    * Công tắc rèm hera
    * Công tắc 1 nút hera
    * Usb 2 chip
    * Cảm biến chuyển động hỗ trợ điều chỉnh motion sensitive (BLE MESH)
    * Hỗ trợ ổ cắm WILE
    * Hỗ trợ FPT camera

