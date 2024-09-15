<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Winner Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="header">
    <div class="HomeIcon">
        <a href="${pageContext.request.contextPath}/home"><img
                src="${pageContext.request.contextPath}/img/Home-Icon.png" alt="Home Icon"></a>
    </div>
    <div class="TextAboutPage">
        <p>Winner</p>
    </div>
    <div class="ListIcon">
        <a href="${pageContext.request.contextPath}/matches"><img
                src="${pageContext.request.contextPath}/img/listIcon.png" alt="list Icon"></a>
    </div>
    <div class="PlusIcon">
        <a href="${pageContext.request.contextPath}/new-match"><img
                src="${pageContext.request.contextPath}/img/plusIcon.jpg" alt="plus Icon"></a>
    </div>
</div>
<hr>
<div class="mainWinnerPage">
    <div class="winnerImage">
        <img src="${pageContext.request.contextPath}/img/winner.jpg" alt="winner Image">
    </div>
    <div class="winnerDetails">
        <h1>Congratulations!</h1>
        <p>${requestScope.winner}</p>
    </div>
</div>
</body>
</html>
