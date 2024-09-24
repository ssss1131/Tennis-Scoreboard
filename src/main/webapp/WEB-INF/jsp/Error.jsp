<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="errorPageBody">
<div class="errorContainer">
    <h1>Oops! Something went wrong.</h1>
    <p>${requestScope.error}</p>
    <a href="${pageContext.request.contextPath}/home" class="backToHome">Return to Home</a>
</div>
</body>
</html>

