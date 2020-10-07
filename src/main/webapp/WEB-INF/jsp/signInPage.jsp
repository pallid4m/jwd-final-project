<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <title>Sign in</title>
</head>
<body>
    <jsp:include page="header.jsp"/>

    <form action="main?command=sign-in" method="post">
        <div class="form-row col-md-8">
            <div class="form-group col-md-4">
                <label for="inputEmail">Email</label>
                <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus=""/>
            </div>
            <div class="form-group col-md-4">
                <label for="inputPassword">Password</label>
                <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required=""/>
            </div>
        </div>
        <div class="form-group col-md-2">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="gridCheck">
                <label class="form-check-label" for="gridCheck">Remember me</label>
            </div>
        </div>
        <div class="form-group col-md-2">
            <button type="submit" class="btn btn-primary">Sign in</button>
        </div>
    </form>

    <jsp:include page="footer.jsp"/>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>