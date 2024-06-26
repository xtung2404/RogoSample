# Rogo Smart SDK -  Android

## Group

### 1. Tạo mới group
```java
    GroupHandler handler = SmartSdk.getGroupHandler();
    handler.createGroup(label, desc, requestCallback);

```

##### Đối số :
- label: String
- desc: String - Mô tả
- requestCallback: Đối tượng callback có kiểu dữ liệu ```RequestCallback<IoTGroup>```

### 2. Cập nhật group
```java
    GroupHandler handler = SmartSdk.getGroupHandler();
    handler.updateGroup(id ,label, desc, requestCallback);

```

##### Đối số :
- id: String - Id của location cần update
- label: String
- desc: String - Mô tả
- requestCallback: Đối tượng callback có kiểu dữ liệu ```RequestCallback<IoTGroup>```

### 3. Xoá group
```java
    GroupHandler handler = SmartSdk.getGroupHandler();
    handler.delete(id, requestCallback);

```

##### Đối số :
- id: String - Id của location cần update
- requestCallback: Đối tượng callback có kiểu dữ liệu ```RequestCallback<Boolean>```

### 4. Lấy tất cả group
```java
    LocationHandler handler = SmartSdk.getLocationHandler();
    handler.getAll();

```