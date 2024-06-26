# Rogo Smart SDK -  Android

## Location

### 1. Tạo mới location
```java
    LocationHandler handler = SmartSdk.getLocationHandler();
    handler.createLocation(label, desc, requestCallback);

```

##### Đối số :
- label: String
- desc: String - Mô tả
- requestCallback: Đối tượng callback có kiểu dữ liệu ```RequestCallback<IoTLocation>```

### 2. Cập nhật location
```java
    LocationHandler handler = SmartSdk.getLocationHandler();
    handler.updateLocation(id ,label, desc, requestCallback);

```

##### Đối số :
- id: String - Id của location cần update
- label: String
- desc: String - Mô tả
- requestCallback: Đối tượng callback có kiểu dữ liệu ```RequestCallback<IoTLocation>```

### 3. Xoá location
```java
    LocationHandler handler = SmartSdk.getLocationHandler();
    handler.delete(id, requestCallback);

```

##### Đối số :
- id: String - Id của location cần update
- requestCallback: Đối tượng callback có kiểu dữ liệu ```RequestCallback<Boolean>```

### 4. Lấy tất cả location
```java
    LocationHandler handler = SmartSdk.getLocationHandler();
    handler.getAll();

```