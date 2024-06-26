# Rogo Smart SDK -  Android
Lắng nghe thiết bị thay đổi trạng thái

```kotlin
 val smartSdkDeviceStateCallback = object : SmartSdkDeviceStateCallback() {
            // Gọi khi thiết bị có thay đổi log
            // devId (IoTDevice uuid) - element(Element id) - feature(IoTFeature)
            override fun onDeviceLogSensorUpdated(devId: String?, element: Int, feature: Int) {
                
            }
            // Gọi khi toàn bộ trạng thái thiết bị thay đổi
            override fun onDeviceStateUpdated(devId: String) {
               

            }
            // Gọi khi trạng thái thiết bị thay đổi
            // devId (IoTDevice uuid) - element(Element id) - values(Giá trị thay đổi, giá trị đan xen [IoTFeature,value1,value2v...,IoTFeature,value,...)
            override fun onEventfeatureStateChange(devId: String?, element: Int, values: IntArray?) {
        
            }
        }
        // register callback
         SmartSdk.registerDeviceStateCallback(smartSdkDeviceStateCallback)
         //unregister callback
         SmartSdk.unregisterDeviceStateCallback(smartSdkDeviceStateCallback)

```
