<%@ page contentType="text/html;charset=UTF-8"%>

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
        <a href="${pageContext.request.contextPath}/home"><img src="${pageContext.request.contextPath}/img/Home-Icon.png" alt="Home Icon"></a>
    </div>
    <div class="TextAboutPage">
        <p>Matches</p>
    </div><div class="ListIcon">
        <a href="${pageContext.request.contextPath}/matches"><img src="${pageContext.request.contextPath}/img/listIcon.png" alt="list Icon"></a>
    </div><div class="PlusIcon">
        <a href="${pageContext.request.contextPath}/new-match"><img src="${pageContext.request.contextPath}/img/plusIcon.jpg" alt="plus Icon"></a>
    </div>
</div>
<hr>
<div class="mainAllMatches">
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
            <tr>
                <td>Alex</td>
                <td>John</td>
                <td>Alex</td>
            </tr>
            <tr>
                <td>Alex</td>
                <td>John</td>
                <td>Alex</td>
            </tr>
            <tr>
                <td>Alex</td>
                <td>John</td>
                <td>Alex</td>
            </tr>
            <tr>
                <td>Alex</td>
                <td>John</td>
                <td>Alex</td>
            </tr>
            <tr>
                <td>Alex</td>
                <td>John</td>
                <td>Alex</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="pagination">
        <a href="#">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
    </div>

</div>

</body>
</html>