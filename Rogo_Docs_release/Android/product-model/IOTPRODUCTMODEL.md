# Rogo Smart SDK -  Android

## Product Model

### 1. Get Model info
```kotlin
val ioTDevice = SmartSdk.deviceHandle()?.get(devId);
val iotProductModel = SmartSdk.getProductModel(ioTDevice?.productId:"");
```

### Description

```kotlin

public class IoTProductModel {
    private String modelId; // model name
    private String name; // default name


    public String getModelId() {
        return modelId;
    }

    public String getName() {
        return name;
    }


    public int getDevType() {
        if (categoryInfo.length >= 1)
            return categoryInfo[0];
        return IoTDeviceType.ALL;
    }

    public int getDevSubType() {
        if (categoryInfo.length >= 2)
            return categoryInfo[1];
        return IoTDeviceSubType.GENERIC;
    }

// Power type của thiết bị pin hay có cắm nguồn
    public int getPowerType() {
        if (categoryInfo.length >= 3)
            return categoryInfo[2];
        return 0;
    }

    public int getConnectivity() {
        if (baseInfo.length >= 1)
            return baseInfo[0];
        return 0;
    }

// See IoTManuafacturer
    public int getManufacturer() {
        if (baseInfo.length >= 3)
            return baseInfo[2];
        return IoTManufacturer.UNKNOWN;
    }

// See IoTAttribute
    public int[] getAttrs() {
        return extraInfo.getAttrs();
    }

// Lấy những sự kiện mà model hỗ trợ(Battery, Lux...) - see IoTAttribute
    public int[] getEventAttrs() {
        return extraInfo.getEventAttrs();
    }

    // get setting của thiết bị
    public int[] getSettings(int elm) {
        return extraInfo.getSettings(elm);
    }

// Xác định xem model này có chứa setting (attribute) của thiết bị hay không
    public boolean containSetting(int elm, int att) {
        for (int setting :
                getSettings(elm))
            if (setting == att) return true;
        return false;
    }

    // Xác định layout của công tắc
    public int[][] getSwitchLayout() {
        return extraInfo.getSwitchLayout();
    }
}

```