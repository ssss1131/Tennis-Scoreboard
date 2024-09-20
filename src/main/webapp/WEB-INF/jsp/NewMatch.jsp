<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>New Match</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="newMatchBody">
<div class="header">
    <div class="HomeIcon">
        <a href="${pageContext.request.contextPath}/home"><img src="${pageContext.request.contextPath}/img/Home-Icon.png" alt="Home Icon"></a>
    </div>
    <div class="TextAboutPage">
        <p>New Match</p>
    </div>
    <div class="ListIcon">
        <a href="${pageContext.request.contextPath}/matches"><img src="${pageContext.request.contextPath}/img/listIcon.png" alt="list Icon"></a>
    </div>
    <div class="PlusIcon">
        <a href=${pageContext.request.contextPath}/"new-match"><img src="${pageContext.request.contextPath}/img/plusIcon.jpg" alt="plus Icon"></a>
    </div>
</div>
<hr>
<c:if test="${not empty requestScope.error}">
    <p class="error">${requestScope.error}</p>
</c:if>

<form action="${pageContext.request.contextPath}/new-match" method="POST">
    <div class="mainNewMatch">
        <div class="Players">
            <div class="FirstPlayer">
                <label for="FirstPlayer">
                    <input type="text" pattern="[a-zA-Z]+" name="firstPlayer" id="FirstPlayer" placeholder="Alex"
                           required>
                </label>
            </div>
        </div>
        <div class="Start">
            <button type="submit">START</button>
        </div>
        <div class="Players">
            <div class="SecondPlayer">
                <label for="SecondPlayer">
                    <input type="text" pattern="[a-zA-Z]+" name="secondPlayer" id="SecondPlayer" placeholder="John"
                           required>
                </label>
            </div>
        </div>
    </div>
</form>

</body>
</html>