<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<html>
<head>
<img width="20%" src="<c:url value='/images/logo-carefirst.svg'/>" />



</head>
<body>
	<h2>Member Id Card Page</h2>
	<table>
		<tr>
			<td><img width="80%" height="80%" src="${pageContext.request.contextPath}/idcard/image/front"/>
			</td>
			<td><img width="80%" height="80%" src="${pageContext.request.contextPath}/idcard/image/back"/>
			</td>
		</tr>
	</table>




</body>
</html>