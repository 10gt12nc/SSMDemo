# SSMDemo
 SSMDemo

---Spring---

1.建置Maven,pom相關配置

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







4.

Spring中載入xml配置檔案的常用的幾種方式





**// ClassPathXmlApplicationContext 是读取 src 目录下的配置文件**
ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

**//  FileSystemXmlApplicationContext 即系统文件路径，文件的目录。（注意：如果你的xml文件放在WEB-INF文件夹下，需要用这个，否则会找不到该文件）**
ApplicationContext context = new FileSystemXmlApplicationContext("WebRoot/WEB-INF/applicationContext.xml");







run as maven build -> tomcat:run 

http://127.0.0.1:8186/hI   即可跳轉到頁面







