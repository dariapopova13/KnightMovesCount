<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>KnightMoveCount</title>
</head>
<body>

<div style="margin: 30px" id="get-form">
    <div>
        <h3>Rest</h3>
        <form method="get">
            <label for="width">Width</label>
            <input type="number" id="width" name="width" placeholder="Width" value="8">

            <label for="height">Height</label>
            <input type="number" id="height" name="height" placeholder="height" value="8">

            <label for="start">Start</label>
            <input type="text" id="start" name="start"
                   placeholder="start" pattern="[a-zA-Z]{1,}[0-9]{1,}">

            <label for="end">End</label>
            <input type="text" id="end" name="end"
                   placeholder="end" pattern="[a-zA-Z]{1,}[0-9]{1,}">

            <input type="submit" value="Submit rest" formaction="/hourse/rest/count">
            <input type="submit" value="Submit servlet" formaction="/hourse/servlet/count">
        </form>
    </div>
</div>

<div style="margin: 30px;">
    ${result}
    ${exception}
</div>
</body>
</html>
