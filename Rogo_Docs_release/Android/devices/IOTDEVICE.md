# Rogo Smart SDK -  Android
Thông tin một thiết bị trong sdk

```java

  private String mac;
    private String uuid;
    private String rootUuid;
    private boolean fav;
    private int cdev;//0 ONLINE , 1 OFFLINE, 11 LEARN IRDEVICE, 12 IRDEVICE
    private String label;
    private String desc;
    private String productId;
    private String groupId; // IoTGroup uuid
    private String groupVid;
    private int firmCode;
    private String firmVer;
    private int cfm;
    private String userId;
    private String locationId;


    @TypeConverters(ConverterIntArrays.class)
    private int[] elementIds; // Mỗi thiết bị có thể có nhiều element

    @TypeConverters(ConverterIoTElements.class)
    @DaoFieldConverter(MapIoTElementInfoConverter.class)
    private HashMap<Integer, IoTElementInfo> elementInfos; // Thông tin cho từng element


    @TypeConverters(ConverterIntArrays.class)
    private int[] features; // Thiết bị có nhiều feature tuỳ thuộc theo loại thiệt bị

    @TypeConverters(ConverterIntArrays.class)
    private int[] productInfos;

    public int getDevType() // kiểu thiết bị
```

