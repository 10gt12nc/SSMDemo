# SSMDemo
# ---Spring--- 

1.建置Maven,pom相關配置

報錯!!

1-1. build path entries are missing 

​	main和test遺失

1-2. Eclipse中專案出現小紅叉的解決方法

​	web.xml中的版本和你的本地檔案的版本不一致

1-3. Spring配置错误：class path resource [.xml] cannot be opened because it does not exist

​	ClassPathXmlApplicationContext( ) 方法是在其所在的目录中寻找 .xml 配置文件
​	这里指的是编译后的 .class 文件所在的目录，不是 .java 文件

​	只能讀放在web-info/classes目錄下的配置文件

​	.java文件存于 src 中，.class 文件存于 target 中
​	因此，ClassPathXmlApplicationContext( ) 方法无法找到 applicationContext.xml



src不是classpath, WEB-INF下面的classes和lib才是classpath，WEB-INF/ 是资源目录, 客户端不能直接访问。
WEB-INF/classes目录存放src目录java文件编译之后的class文件，xml、properties等资源配置文件，这是一个定位资源的入口。引用classpath路径下的文件，只需在文件名前加classpath:
4、lib和classes同属classpath，两者的访问优先级为: lib>classes。
5、classpath 和 classpath* 区别：
classpath：只会到你的class路径中查找找文件;

classpath*：不仅包含class路径，还包括jar文件中(class路径)进行查找

1-4. pom.xml中出現web.xml is missing and  failOnMissingWebXml  is set to true

```java


```



2.web.xml 配置  Spring ApplicationContext文件的路径

```java

```



3.resource建立app-context.xml ,

<context:component-scan  base-package= 配置自動掃描包並自动注册bean 

<import resource="app-dynamic-datasource.xml" 动态数据源控制

```java

```







4. Java junitTest

```

```

Spring中載入xml配置檔案的常用的幾種方式

**// ClassPathXmlApplicationContext 是读取 src 目录下的配置文件**
ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

**//  FileSystemXmlApplicationContext 即系统文件路径，文件的目录。（注意：如果你的xml文件放在WEB-INF文件夹下，需要用这个，否则会找不到该文件）**
ApplicationContext context = new FileSystemXmlApplicationContext("WebRoot/WEB-INF/applicationContext.xml");



# ---Spring MVC--- 





1. pom

   添加相關依賴

   ```java

   ```

   ​

2. web.xml

   DispatcherServlet配置

   ​	DispatcherServlet前

   ```java

   ```

   端控制器,攔截請求分給目標controller處理

   ​

3. spring-mvc.xml

   3.1<context:component-scan 自動掃描

   3.2<mvc:annotation-driven 自動註冊 RequestMappingHandlerAdapter、		RequestMappingHandlerMapping: 

   HandlerMapping的作用處理 @RequestMapping 註解，並將其註冊到請求對映表中。

   HandlerAdapter的作用則是處理請求的介面卡，確定呼叫哪個類的哪個方法，並且構造方法引數，返回					值。自動為我們將掃描到的 @Component，@Controller，@Service，@Repository等註解標記的元件註冊				到工廠中，來處理我們的請求。

   3.3視圖解析器 控制層回傳的字串+前後綴=jsp 

   ```java

   ```

   ​

4. Controller 控制器

   @Controller

   @RequestMapping("/HI")

   @GetMapping("/haha")

   ```java

   ```

   ​

5. 對專案

   run as maven build -> tomcat:run 、 source -> add

   run as configurations -> run

   網址列http://127.0.0.1:8186/HI/haha  即可跳轉到hi.jsp

   ​


<h1>---MyBatis---</h1>

1.pom

mysql-connector-java 驅動

druid 連接池

mybatis-spring 無縫整合

2.jdbc.properties

3.app-context.xml

4.MySQL



5.domain (物件)



6.Dao



7.Controller



