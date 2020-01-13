# TestPie 
* :round_pushpin: **加载测试数据(JSON、YAML或其它格式)，by** `@PieData`
* :clapper: **隔离Mock code和TestUnit code，by** `@MockPrimay` or `@MockProvider`

## 依赖
### Requirements
* Jdk 1.8+
* Junit 5
* Mockito(可选，如果需要使用mock功能需要加入此依赖)

### Gradle
```groovy
testImplementation "com.deepoove:testpie:0.0.1"
```

### Maven
```xml
<dependency>
  <groupId>com.deepoove</groupId>
  <artifactId>testpie</artifactId>
  <version>0.0.1</version>
  <scope>test</scope>
</dependency>
```


## `@PieData`：加载数据
### 方式一、`Pie.initAnnotations(this)`
**1). 准备测试数据user.json**

```json
{
  "phone": 12306,
  "name": "Sayi",
  "age": 10
}
```
**2). 加载数据**

```java
@PieData(value = "/pie/user.json")
private User user;

public PieTest() {
    Pie.initAnnotations(this);
}

@Test
public void testPieData() {
    assertNotNull(user);
}
```

### 方式二、`PieExtension`代替`Pie.initAnnotations`：

```java
@ExtendWith(PieExtension.class)
public class PieExtensionTest {

  @PieData(value = "/pie/user.json")
  private User user;

  @Test
  public void testPieData() {
    assertNotNull(user);
    assertEquals(user.getPhone(), 12306);
  }
}
```

### 扩展：使用`YamlConverter`加载yml数据
```java
@ExtendWith(PieExtension.class)
public class YamlConverterTest {

  @PieData(value = "/yaml/user.yml", converter = YamlConverter.class)
  private User user;

  @Test
  public void testPieDataByYaml() {
    assertNotNull(user);
    assertEquals(user.getPhone(), 12306);
  }
}
```

### 扩展：自定义数据格式转化器
```java
public class GsonJsonConverter implements Converter {
  @Override
  public Object convert(String source, TypeContext context) {
    Gson gson = new Gson();
    return gson.fromJson(source, context.getType());
  }
}
```

## `@MockPrimay`：为某个对象提供mock
一个对象的Mock功能可以被**快速构造**和**复用**，通过一个单独的类提供对象的所有Mock功能，这个类称为：该对象的Mock类。就像这样:

```java
Supplier<UserService> mock = new UserServiceMock();
UserService userService = mock.get();
```
与这段代码等价的功能是TestPie提供了与Mockito集成的注解`@MockPrimay`来快速构造一个具有Mock功能的对象。

### 方式一、使用`@MockPrimay`
1). 创建一个单独的**对象Mock类**定义具体Mock功能

```java
public class UserServiceMock implements Consumer<UserService> {

  @PieData("/pie/list_user_.json")
  private List<User> userList;

  public UserServiceMock() {
    // 加载数据
    Pie.initAnnotations(this);
  }

  @Override
  public void accept(UserService userService) {
    // 处理Mock功能
    given(userService.findAll()).willReturn(userList);
    given(userService.delete(1590000)).willReturn(false);
    given(userService.delete(1581111)).willReturn(true);
  }
}
```

2). 使用`@MockPrimay`初始化Mock功能

```java
@Mock(lenient = true)
@MockPrimary(UserServiceMock.class)
private UserService userService;

public PieMockitoTest() {
    // first mockito
    MockitoAnnotations.initMocks(this);
    // the testpie
    Pie.initAnnotations(this);
}

@Test
public void testPieData() {
    assertFalse(userService.delete(1590000));
    assertTrue(userService.delete(1581111));
}
```
需要注意初始化的顺序，`@Mock`的顺序优先于`@MockPrimary`，即先使用Mockito初始化Mock对象，再使用TestPie指定该对象的Mock类。

### 方式二、使用Junit5的扩展类`PieMockitoExtension`
`PieMockitoExtension`会在每个测试方法执行前初始化拥有注解`@Mock`和`@MockPrimay`对象。

```java
@ExtendWith({ MockitoExtension.class, PieMockitoExtension.class })
public class PieMockitoExtensionTest {

  @Mock(lenient = true)
  @MockPrimary(UserServiceMock.class)
  private UserService userService;

  @Test
  public void testPrimary() {
    assertFalse(userService.delete(1590000));
    assertTrue(userService.delete(1581111));
  }
}
```

## `@MockProvider`：为某个测试方法提供mock
`@MockPrimary`提供了快速Mock一个对象的功能，`@MockProvider`则针对每个测试方法准备Mock功能的方法。`@MockPrimary`是一个对象的Mock，`@MockProvider`则每个测试方法需要所有Mock对象的Mock，它是测试方法级别的。

1). 创建测试类的TestClassMock类：

```java
public class MockProviderTestClassMock {

  @PieData("/pie/list_user_.json")
  private List<User> userList;

  private UserService userService;

  public MockProviderTestClassMock() {
    Pie.initAnnotations(this);
  }

  public void mock_testProvider() {
    given(userService.find(anyLong())).willReturn(userList.get(1));
  }

}
```

2). 使用`@MockProvider`指定TestClassMock：

```java
@ExtendWith({ MockitoExtension.class, PieMockitoExtension.class })
@MockProvider(MockProviderTestClassMock.class)
public class MockProviderTest {

  @Mock(lenient = true)
  @MockPrimary(UserServiceMock.class)
  private UserService userService;

  @Test
  public void testPrimary() {
    // use primary mock
    User findUser = userService.find(12306);
    Assertions.assertEquals(findUser.getName(), "Sayi");
  }

  @Test
  public void testProvider() {
    // user provider mock
    User findUser = userService.find(12306);
    Assertions.assertEquals(findUser.getName(), "Van");
  }

}

```
每个测试方法会自动寻找`@MockProvider`指定的类的前缀为mock_的同名方法初始化Mock功能，比如testProvider方法执行前会执行MockProviderTestClassMock的mock_testProvider方法。

`@MockProvider`的Mock功能会覆盖`@MockPrimary`的功能，即优先级更高。


