# SSM(1)
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

   

2. web.xml

   DispatcherServlet配置

   ​	DispatcherServlet前

   ```java

   ```

   端控制器,攔截請求分給目標controller處理

   

3. spring-mvc.xml

   3.1<context:component-scan 自動掃描

   3.2<mvc:annotation-driven 自動註冊 RequestMappingHandlerAdapter、		RequestMappingHandlerMapping: 

   HandlerMapping的作用處理 @RequestMapping 註解，並將其註冊到請求對映表中。

   HandlerAdapter的作用則是處理請求的介面卡，確定呼叫哪個類的哪個方法，並且構造方法引數，返回					值。自動為我們將掃描到的 @Component，@Controller，@Service，@Repository等註解標記的元件註冊				到工廠中，來處理我們的請求。

   3.3視圖解析器 控制層回傳的字串+前後綴=jsp 

   ```java

   ```

   

4. Controller 控制器

   @Controller

   @RequestMapping("/HI")

   @GetMapping("/haha")

   ```java

   ```

   

5. 對專案

   run as maven build -> tomcat:run 、 source -> add

   run as configurations -> run

   網址列http://127.0.0.1:8186/HI/haha  即可跳轉到hi.jsp

   


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



Error creating bean with name 'mapperScannerConfigurer' defined in URL [file:/C:/JAVA_dikailin/Git/back%20end/SSMDemo/SSMDemo/target/classes/app-context.xml]: 

Error setting property values; 

nested exception is org.springframework.beans.NotWritablePropertyException

 Invalid property 'sqlSessionFactoryBesnName' of bean class [org.mybatis.spring.mapper.MapperScannerConfigurer]: Bean property 'sqlSessionFactoryBesnName' is not writable or has an invalid setter method. Did you mean 'sqlSessionFactoryBeanName'?



字要打對

引用 ref

值 value





<h1>SSM(2)</h1>





1. 概述





2 DB表





3. 持久化 類





4. Dao
5. Mapper
6. 



### @RestController與@Controlle

































# 一次搞懂POJO、PO、DTO、VO、BO

## 前言

在還是學生時，腦袋完全沒有所謂的Bean的觀念，直到進入職場開始使用Spring並且看到物件的命名(PO、DTO、VO、BO)才發現原來每個物件，對於他們的功能都有一個相對的命名，所以就抽空整理網路上看到的資料並且記錄下來，如果內文有錯誤的話煩請高手糾正指導。

## 主文

### POJO (Plain Old Java Object)

> Martin Fowler、Rebecca Parsons和Josh MacKenzie在2000年的一次一講提出的概念，當初提出的背景是EJB過於複雜，所以想要找一個簡單的方式取代他，而現在也證明他成功了，應為套用POJO概念的Spring已經是Java不可或缺的框架。

#### 定義

簡單並且純粹，POJO就是一個Java物件只包含自己的屬性(private)和提取或儲存這些屬性的method(get、set)，而其他的Object也是以POJO為基準開始延伸。

#### 題外話

對於一個進入正式開發階段的Java工程師在工作時常常看到Entity應該都不知道他們的正式名稱就是POJO。

下方參考[Webopedia](https://www.webopedia.com/TERM/P/POJO.html)介紹

> POJO, or Plain Old Java Object, is a normal Java object class (that is, not a JavaBean, EntityBean etc.)  and does not serve any other special role nor does it implement any special interfaces of any of the Java frameworks. This term was coined by Martin Fowler, Rebbecca Parsons and Josh MacKenzie who believed that by creating the acronym POJO, such objects would have a “fancy name”, thereby convincing people that they were worthy of use.

### PO (persistent object)

#### 定義

因為ORM框架的誕生所以才有PO的概念，可以簡單地將它視為資料庫table對應的java物件，通常PO的名詞都會與使用hibernate相關

### DTO (Data Transfer Object)

#### 定義

傳輸用的物件，假設今天我的程式像資料庫提取了PO資料物件，我必須將我的資料傳往其他系統或是服務時則可以用DTO進行再包裝，通常DTO的資訊都會比PO少，因為沒有必要將全部的資料傳輸出去。

### VO (value object)

#### 定義

用於呈現時的資料包裝，並且將實體的資料(PO)抽象適合當前程式運作的物件，他可以很單純如同PO一樣對應資料庫的屬性，但他也可以包含多個PO組裝成一個較為複雜的資料物件。

### DAO (data access object)

#### 定義

用於ORM(hibernate)將資料從資料庫提取的邏輯物件，其中邏輯主要包含如何提取資料庫的資料(SQL)並且將資料包裝成PO。

### 題外話

### BO (business object)

#### 定義

用於業務層開發的物件，和PO和VO差別在於BO包含複雜的業務邏輯，而不再是單純的資料存取或儲存物件。

### 圖解

借用網路上搜尋到的資料我覺得一張圖在看完上面的介紹後，可以馬上了解每個object的差異。

![img](https://i.imgur.com/1JuZ1qI.png)

我們可以看到這張架構圖，在DAO會再持續層對資料庫做資料提取並且和業務層做交換，而在業務層會將PO轉換成BO做程式邏輯的處理，而業務層和服務層則會再次將BO包裝成對應的VO顯示或者DTO傳遞出去。







