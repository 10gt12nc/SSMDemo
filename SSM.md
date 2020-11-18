<h1>簡述</h1>

-SSM 框架

使用Spring+Spring MVC+MyBatis+...

-Spring

 降低開發的複雜性。使用分層架構可以選擇使用

1.Data Access/Integration 資料存取/整合

jdbc 樣板、orm  hibernate jpa  mybatis  、oxm  object <-> xml 映射 、 jms 異步通信 、 transaction  管理事務

2.Web

websocket、 servlet 、web、 portlet

3.AOP 、Aspects

4.核心容器

spring-beans、spring-core、 spring-context、spring-spel

5.Test

-Spring MVC

建構Web應用程式全功能MVC模組

-MyBatis

持久層框架

-安裝、配置 (略) 



<h1>簡易SSM</h1>

--Maven

軟體專案管理工具，使用Pom檔管理專案建構,、關聯性，自動下載和專案相關的Libraries(函式庫)。

----------------------------Library dependency----------------------------

建構時檢查pom 

repository (檔案庫)

本地.m2、中央<dependency>、遠程<respository>，settings.xm文件中定義存放路徑。

----------------------------Maven profile----------------------------

軟體會面對不同的執行環境，比如開發環境(DEV)、測試環境(UAT)、生產環境，讓我們不用修改配置就能釋出到不同的環境中，比如資料來源配置、日誌檔案配置。resources目錄下spring-applicationContext.xml。jdbc.properties資料來源、logback.xm日誌等等。

----------------------------Multi-module 多模組----------------------------

將原本一個大專案切成多個模組專案更容易維護、更好管理、更好重用。

parent下的business、總控、後台、ｍｅｍｂｅｒ。

（重用：略同程式碼，依賴war來達到共用程式碼）

新增一個新的後台專案。因為總控、後台、ｍｅｍｂｅｒ都為獨立的專案，

可各自打包成jar，新的專案可直接加到依賴中，不用再依賴一整個war。

專案的依賴可由父專案的pom來統一管理，不用每個專案各自維護自己的依賴。

而各模組仍可依需求在自己的pom設定依賴。不用每次都build整個專案，你只需要build你所在的專案即可，節省build的時間。

(父parent<modules>Maven Install，子<parent>Run As)

----------------------------Maven plugin 插件----------------------------

編譯插件
 打包插件

部署插件

tomcat插件；自動部屬

多模組

Parant pom，tomcat插件；定義版本

Member、Control、Platform，tomcat插件:定義路徑、Port號

--Tomcat

輕量級應用服務器  自動部屬

 

<h1>CH3.Spring核心IOC與AOP</h1>



概念相同 ,IOC控制反轉 通過DI依賴注入實現

原先需要實現創建對象及對象間的依賴,反轉給容器做

聲明

@Component  聲明bean

@Service  服務層(業務邏輯)

@Repository 數據訪物層(Dao)

@Controller 控制層

注入

@Autiwired Spring提供 默認按類型 結合@Qualifier 使用名稱

@Resource J2EE提供   默認按名稱裝配



(( 單例模式 (( 工廠模式      我有!!!

只有一個實例供全局使用

傳統是new  



--Spring AOP概述 

面向切面 提高重用 開發效率

日誌紀錄 性能統計 安全控制 事務處理 異常處理

3.2.2 Spring AOP核心概念 57

就像一把刀在程式執行過程隨意插入拔出

p58

 核心概念表3-1   advice 通知類型3-2

--JDK動態代理實現日誌框架

P63步驟

--Spring AOP實現日誌框架  這可舉例 其他略提

是使用動態代理

--靜態代理與動態代理模式

1個物件1個proxy

1個proxy可供N個物件使用 隨意插入拔出



<h1>MyBatis映射器與動態SQL </h1>


--4.1 MyBatis映射器 69
4.1.1 映射器的主要元素 69
4.1.2 select元素 70
4.1.3 insert元素 71
4.1.4 selectKey元素 72
4.1.5 update元素 73
4.1.6 delete元素 73
4.1.7 sql元素 74
4.1.8 #與$區別 75
4.1.9 resultMap結果映射集 75
--4.2 動態SQL 77
4.2.1 動態SQL概述 77
4.2.2 if元素 77
4.2.3 choose、when、otherwise元素 78
4.2.4 trim、where、set元素 79
4.2.5 foreach元素 82
4.2.6 bind元素 82
--4.3 MyBatis註解配置 83
4.3.1 MyBatis常用註解 83
4.3.2 @Select註解 84
4.3.3 @Insert、@Update、@Delete註解 84
4.3.4 @Param註解 85
--4.4 MyBatis關聯映射 86
4.4.1 關聯映射概述 86
4.4.2 一對一 86
4.4.3 一對多 89
4.4.4 多對多 92

第5章 Spring MVC常用註解 98

<h1>Spring MVC常用註解 </h1>



--5.1 請求映射註解 98

5.1.1 @Controller註解 98
5.1.2 @RequestMapping註解 99
5.1.3 @GetMapping和@PostMapping註解 104
5.1.4 Model和ModelMap 104
5.1.5 ModelAndView 105
5.1.6 請求方法可出現參數和可返回類型 106
--5.2 參數綁定註解 108
5.2.1 @RequstParam註解 108
6.2.2 @PathVariable註解 109
5.2.3 @RequestHeader註解 110
5.2.4 @CookieValue註解 110
5.2.5 @ModelAttribute註解 111
5.2.6 @SessionAttribute和@SessionAttributes註解 115
5.2.7 @ResponseBody和@RequestBody註解 117
--5.3 信息轉換詳解 119
5.3.1 HttpMessageConverter<T> 119
5.3.2 RequestMappingHandlerAdapter 121
5.3.3 自定義HttpMessageConverter 122



 <h1>分頁開發、數據校驗與事務管理</h1>


--6.1 RowBounds類 124
6.1.1 分頁概述 124
6.1.2 RowBounds類 125
6.1.3 RowBounds分頁應用 126
6.1.4 RowBounds分頁原理 127
6.1.5 分頁插件PageHelper 128
--6.2 Spring數據校驗 130
6.2.1 數據校驗概述 131
6.2.2 Spring的Validation校驗框架 131
6.2.3 JSR 303校驗 135
--6.3 Spring和MyBatis事務管理 139
6.3.1 Spring事務管理 139
6.3.2 MyBatis事務管理 141

<h1>MyBatis緩存機制</h1>

--7.1 MyBatis的緩存模式 147
--7.2 一級查詢緩存 148
7.2.1 一級緩存概述 148
7.2.2 一級緩存示例 148
7.2.3 一級緩存生命週期 151
--7.3 二級查詢緩存 151
7.3.1 二級緩存概述 151
7.3.2 二級緩存示例 153
7.3.3 Cache-ref共享緩存 155
--7.4 MyBatis緩存原理 156
7.4.1 MyBatis緩存的工作機制 156
7.4.2 裝飾器模式 157
7.4.3 Cache接口及其實現 159

<h1> Spring MVC原理</h1>


--8.1 Spring MVC的執行流程與前端控制器 162
--8.2 前端控制器DispatcherServlet 164
--8.3 處理映射器和適配器 167
8.3.1 處理映射器 167
8.3.2 處理適配器 168
--8.4 視圖解析器 179
8.4.1 視圖解析流程 179
8.4.2 常用視圖解析器 179
8.4.3 ViewResolver鏈 185

<h1>MyBatis原理/h1>



--9.1 MyBatis的整體框架介紹 187
9.1.1 接口層 187
9.1.2 核心處理層 190
9.1.3 基礎支撐層 191
--9.2 MyBatis初始化流程 192
--9.3 MyBatis的執行流程 194



<h1>點贊實戰 </h1>

--11.1 高並發點贊項目代碼實現 218
11.1.1 項目概述 218
11.1.2 數據庫表和持久化類 218
11.1.3 DAO層和Mapper映射文件 222
11.1.4 Service層和DTO類 225
11.1.5 Controller層和前端頁面 229
11.1.6 測試 230
--11.2 傳統點贊功能實現 231
11.2.1 概述 231
11.2.2 代碼實現 232
11.2.3 測試 235
--11.3 集成Redis緩存 236
11.3.1 概述 236
11.3.2 Redis的安裝和使用 237
11.3.3 集成Redis緩存 243
11.3.4 設計Redis數據結構 246
11.3.5 代碼實現 247
11.3.6 集成Quartz定時器 250

Quartz 

用戶 >  service redis > qrartz定時器 > SQL11.3.7 測試 254
--11.4 集成ActiveMQ 254
11.4.1 概述 254
11.4.2 ActiveMQ的安裝 255
11.4.3 集成ActiveMQ 257
11.4.4 ActiveMQ異步消費 259
11.4.5 測試 262

JPA Java持久化API , ORM規範  

--All思考與練習QA                              
													