<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>




	<div>
		<c:forEach items="${moods}" var="mood">

<table>

</table>
			用戶:<span>${mood.username}</span>
			<br>
			內容:<span>${mood.content}</span>
			<br>
			發表時間:<span>${mood.publishtime}</span>
			<br>
			爛的數量:<span>${mood.praisenum}</span>
			<br>
			<br>
			<br>
			<div style="margin-left: 350px">
				<h1><a id="praise" href="/mood/${mood.id}/praise?userid=1">爛</a></h1>
			</div>
			<!-- "要包對  ${mood.userid}-->
			<!-- userid=1 先假設一個會員在使用-->
			<div>--------------------</div>
			<!-- +redis -->
			<h1><a id="praiseForRedis" href="/mood/${mood.id}/praiseForRedis?userid=${mood.userid}"></a></h1>
			
			
			
			
		</c:forEach>


	</div>

</body>
</html>