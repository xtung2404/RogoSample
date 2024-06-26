# Rogo Smart SDK -  Android

## Authentication

### 1. Đăng nhập

```java
    SmartSdk.signIn(method, authRequestCallback);
```

##### Đối số :
- method: Phương thức xác thực là một đối tượng từ class implement inteface ```IAuthSignInMethod```
- authRequestCallback: Đối tượng callback có kiểu dữ liệu ```AuthRequestCallback<Boolean>```


### 2. Đăng kí

```java
    SmartSdk.signUp(method, authRequestCallback);
```

##### Đối số :
- method: Phương thức xác thực là một đối tượng từ class implement inteface ```IAuthSignUpMethod```
- authRequestCallback: Đối tượng callback có kiểu dữ liệu ```AuthRequestCallback<Boolean>```


### 3. Đăng xuất

```java
    SmartSdk.signOut(authRequestCallback);
```

##### Đối số :
- authRequestCallback: Đối tượng callback có kiểu dữ liệu ```AuthRequestCallback<Boolean>```

### 3. Quên mật khẩu

```java
        SmartSdk.forgot(method, authRequestCallback);
```

##### Đối số :
- method: Phương thức xác thực là một đối tượng từ class implement inteface ```IAuthForgotMethod```
- authRequestCallback: Đối tượng callback có kiểu dữ liệu ```AuthRequestCallback<Boolean>```

