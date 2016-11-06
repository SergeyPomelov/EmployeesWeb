<%@ page session="false" %>
<!--JSP Tags for spring MVC, remove if this jsp tuned for standalone working-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <title>ExtJS + SpringMVC</title>
    <!--- External and internal cascade styles. -->
    <link rel="stylesheet" type="text/css"
          href="http://examples.sencha.com/extjs/6.2.0/classic/theme-gray/resources/theme-gray-all.css">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>">
    <!--- External and internal JS files. -->
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.2.0/ext-all.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/employeesDataGrid.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/loadDataButtons.js"/>"></script>
</head>
<body>
<div id="buttons"><!--output for control buttons set, generated in loadDataButtons.js--></div>
<div id="grid"><!--output for data grid, generated in employeesDataGrid.js--></div>
</body>
</html>