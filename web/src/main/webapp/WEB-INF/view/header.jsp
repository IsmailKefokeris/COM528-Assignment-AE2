<%-- 
    Document   : header
    Created on : Jan 4, 2020, 11:19:01 AM
    Author     : cgallen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">
        <!--<link rel="canonical" href="https://getbootstrap.com/docs/3.3/examples/navbar/">-->

        <title>Navbar Template for Bootstrap</title>

        <!-- Bootstrap core CSS -->
        <link href="./resources/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="./resources/css/navbar.css" rel="stylesheet">

    </head>

    <body>

        <div class="container">

            <!-- Static navbar -->
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">Project name</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li <% if ("home".equals(request.getAttribute("selectedPage"))) {%> class="active"  <% } %> ><a href="./home">Home</a></li> 
                            <li <% if ("about".equals(request.getAttribute("selectedPage"))) {%>  class="active"  <% } %> ><a href="./about">About</a></li> 
                            <li <% if ("contact".equals(request.getAttribute("selectedPage"))) {%>  class="active"  <% }%> ><a href="./contact">Contact</a></li>
                            <li <% if ("cart".equals(request.getAttribute("selectedPage"))) {%>  class="active"  <% }%> >
                                <div class="cartObject">
                                    <a href="./cart"> <svg href="./cart" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
                                    <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                    </svg><p>Cart</p></a>
                                </div>
                            </li>
                            <c:if test="${sessionUser.userRole =='ADMINISTRATOR'}">
                                <li class="dropdown" >
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> Admin <span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="./users">Manage Users</a></li>
                                        <li><a href="./catalogue">Manage Catalogue</a></li>
                                    </ul>
                                </li>
                            </c:if>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <!-- user role:  ${sessionUser.userRole}-->
                            <c:if test="${sessionUser.userRole =='ANONYMOUS'}">
                                <li><a href="./login">Login or create a new Account</a></li>
                                </c:if>
                                <c:if test="${sessionUser.userRole !='ANONYMOUS'}">
                                <form id="logoutForm" method="POST" action="./logout">
                                </form>
                                <form id="profile" method="GET" action="./viewModifyUser">
                                    <input type="hidden" name="username" value="${sessionUser.username}"/>
                                </form>
                                <p class="text-muted"> Welcome 
                                    <c:if test="${sessionUser.userRole =='ADMINISTRATOR'}"> Admin</c:if>                                   
                                    ${sessionUser.username}&nbsp;&nbsp;
                                    <a onclick="document.forms['logoutForm'].submit()">Logout</a><BR>
                                    <a onclick="document.forms['profile'].submit()">User Profile</a></p>
                                </c:if>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </nav>