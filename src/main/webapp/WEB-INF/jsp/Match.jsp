<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Match</title>
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
        <p>Playing</p>
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
<div class="mainPlayingMatch">
    <div class="wrapperOfTable">
        <table class="tableOfMatch">
            <thead>
            <tr>
                <th>Player</th>
                <th>Sets</th>
                <th>Games</th>
                <th>Points</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${requestScope.match.firstPlayer.name}</td>
                <td>${requestScope.match.firstPlayer.score.sets}</td>
                <td>${requestScope.match.firstPlayer.score.games}</td>
                <td>
                    <c:choose>
                        <c:when test="${requestScope.match.firstPlayer.score.isDeuce}">
                            <c:choose>
                                <c:when test="${requestScope.match.firstPlayer.score.deucePointsPlayer == 'EQUAL'}">=</c:when>
                                <c:when test="${requestScope.match.firstPlayer.score.deucePointsPlayer == 'PLAYER_LEADING'}">&gt;</c:when>
                                <c:when test="${requestScope.match.firstPlayer.score.deucePointsPlayer == 'OPPONENT_LEADING'}">&lt;</c:when>
                            </c:choose>
                        </c:when>
                        <c:when test="${requestScope.match.firstPlayer.score.isTieBreak}">${requestScope.match.firstPlayer.score.tiebreakPointsPlayer}</c:when>
                        <c:otherwise>
                            ${requestScope.match.firstPlayer.score.points}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="ButtonsForPlayers">
                    <form method="POST"
                          action="${pageContext.request.contextPath}/match-score?uuid=${requestScope.match.uuid}">
                        <input type="hidden" name="playerId" value="${requestScope.match.firstPlayer.id}"/>
                        <button type="submit" class="buttonForPoint">Point</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td>${requestScope.match.secondPlayer.name}</td>
                <td>${requestScope.match.secondPlayer.score.sets}</td>
                <td>${requestScope.match.secondPlayer.score.games}</td>
                <td>
                    <c:choose>
                        <c:when test="${requestScope.match.secondPlayer.score.isDeuce}">
                            <c:choose>
                                <c:when test="${requestScope.match.secondPlayer.score.deucePointsPlayer == 'EQUAL'}">=</c:when>
                                <c:when test="${requestScope.match.secondPlayer.score.deucePointsPlayer == 'PLAYER_LEADING'}">&gt;</c:when>
                                <c:when test="${requestScope.match.secondPlayer.score.deucePointsPlayer == 'OPPONENT_LEADING'}">&lt;</c:when>
                            </c:choose>
                        </c:when>
                        <c:when test="${requestScope.match.secondPlayer.score.isTieBreak}">
                            ${requestScope.match.secondPlayer.score.tiebreakPointsPlayer}
                        </c:when>
                        <c:otherwise>
                            ${requestScope.match.secondPlayer.score.points}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="ButtonsForPlayers">
                    <form method="POST"
                          action="${pageContext.request.contextPath}/match-score?uuid=${requestScope.match.uuid}">
                        <input type="hidden" name="playerId" value="${requestScope.match.secondPlayer.id}"/>
                        <button type="submit" class="buttonForPoint">Point</button>
                    </form>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>