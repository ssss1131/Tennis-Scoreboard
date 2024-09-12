<%@ page contentType="text/html;charset=UTF-8"%>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="container">
<div class="wrapper">
    <div class="content">
        <div class="TnsScoBoard">
            <h1>Tennis Scoreboard</h1>
        </div>
        <div class="Buttons">
            <a href="${pageContext.request.contextPath}/new-match" class="Button NewMatchButton">New Match</a>
            <a href="${pageContext.request.contextPath}/matches" class="Button AllMatchButton">All Matches</a>
        </div>
    </div>
    <div class="tennis-img">
        <img src="${pageContext.request.contextPath}/img/HomeImage.jpg" alt="Tennis Image">
    </div>
</div>
</body>
</html>
