<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Matches</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="allMatchesBody">
<div class="header">
    <div class="HomeIcon">
        <a href="${pageContext.request.contextPath}/home"><img
                src="${pageContext.request.contextPath}/img/Home-Icon.png" alt="Home Icon"></a>
    </div>
    <div class="TextAboutPage">
        <p>Matches</p>
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
<div class="mainAllMatches">
    <form action="${pageContext.request.contextPath}/matches" method="GET" class="search-form">
        <div class="search">
            <input class="search-input" type="text" name="query" placeholder="Search..">
            <button type="submit" class="search-button">Search</button>
            <a href="${pageContext.request.contextPath}/matches" class="reset-button">Reset</a>
        </div>
    </form>

    <div class="List">
        <table class="content-table">
            <thead>
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.matches}" var="match">
                <tr>
                    <td>${match.player1.name}</td>
                    <td>${match.player2.name}</td>
                    <td>${match.winner.name}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="pagination">
        <c:forEach var="i" begin="1" end="${requestScope.pages}" step="1">
            <form action="${pageContext.request.contextPath}/matches" method="GET">
                <input type="hidden" name="page" value="${i}"/>
                <button type="submit">${i}</button>
            </form>
        </c:forEach>
    </div>

</div>

</body>
</html>