# Rogo Smart SDK -  Android

### Register callback

```kotlin
smartSdkEventCallback = object : SmartSdkEventCallback() {
            override fun onEntityDataSynced(type: Int) {

            }

            override fun onAllDataResyned() {

            }

            override fun onEvent(ioTEventNotify: IoTEventNotify?, uuid: String?) {

            }

            override fun onCloudConnectionReady(isReady: Boolean) {

            }
        }
        SmartSdk.registerEventCallback(smartSdkEventCallback)

```
> Mỗi khi gọi         SmartSdk.registerEventCallback(smartSdkEventCallback)
>call back cũ sẽ bị xoá
- onEntityDataSynced: Gọi khi dữ liệu đã được sync theo loại. type: ```IoTEntityType``` là loại dữ liệu được sync
- onAllDataResyned: Gọi khi tất cả dữ liệu được sync.
- onEvent: Khi có sự kiện thay đổi. Biến uuid ```String``` Phụ thuộc vào loại entity được thay đổi

```
Các event
 /**
     * @apiNote event new device
     * **/
    EDC, //event new device created
    /**
     * @apiNote event device updating
     * **/
    EDU, //event device updated
    /**
     * @apiNote event device deleted
     * **/
    EDD, //event new  device deleled

    EDSC, //event multi device created fill to ids

    /**
     * @apiNote event group created
     * **/
    EGC, //event new  group created

    /**
     * @apiNote event group updated
     * **/
    EGU, //event group updated

    /**
     * @apiNote event group deleted
     * **/
    EGD, //event group deleted

    /**
     * @apiNote event group ctl member created or updated
     * **/
    EMGU, //event member group ctl update

    /**
     * @apiNote event group ctl member deleted
     * **/
    EMGD, //event member group ctl deleted

    /**
     * @apiNote event location created
     * **/
    ELC, //event location creaated,
    /**
     * @apiNote event location updated
     * **/
    ELU, //event location updated,

    /**
     * @apiNote event location deleted
     * **/
    ELD, //event location deleted,



    /**
     * @apiNote event smart created
     * **/
    ESMC, //event smart created
    /**
     * @apiNote event smart updated
     * **/
    ESMU, //event smart updated
    /**
     * @apiNote event smart deleted
     * **/
    ESMD, //event smart delete

    /**
     * @apiNote event smartcmd created
     * **/
    ESMCC, //event smartcmd created

    /**
     * @apiNote event smartcmd updated
     * **/
    ESMCU, //event smartcmd updated

    /**
     * @apiNote event smartcmd deleted
     * **/
    ESMCD, //event smartcmd deleted

    /**
     * @apiNote event smart trigger created
     * **/
    ESTEC, //event smarttrigger created

    /**
     * @apiNote event smart trigger updated
     * **/
    ESTEU, //event smarttrigger updated

    /**
     * @apiNote event smart trigger deleted
     * **/
    ESTED, //event smarttrigger delete

    /**
     * @apiNote event smart schedule created
     * **/
    ESCC, //event new schedule created

    /**
     * @apiNote event smart schedule updated
     * **/
    ESCU, //event schedule updated

    /**
     * @apiNote event smart schedule deleted
     * **/
    ESCD, //event schedule delete

    /**
     * @apiNote event linking created
     * **/
    ELIEC, //event new linking created

    /**
     * @apiNote event linking deleted
     * **/
    ELIED, //event linking delete

    ESHC, //event new shairing created
    ESHU, //event shairing updated
    ESHD, //event shairing delete

    EUXU, //event userextra infor updated
    ECLDU, //Event Cloud data update
    EIRC, //event irdata created
    EIRU, //event irdata updated
    EIRD, //event irdata deleted

    ESC, //event new scenario created deprecated
    ESU, //event scenario updating deprecated
    ESD, //event scenario delete deprecated

    EAC, //event new automation created deprecated
    EAU, //event automation updated deprecated
    EAD, //event automation delete deprecated


```

- onCloudConnectionReady: Gọi khi kết nối tới cloud thành công
### Unregister callback
```kotlin
        SmartSdk.unregisterEventCallback(smartSdkEventCallback)
```
