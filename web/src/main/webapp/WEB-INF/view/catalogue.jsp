<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
// request set in controller
//request.setAttribute("selectedPage", "home");
%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    
    <div style="color:red;">${errorMessage}</div>
    <div style="color:green;">${message}</div>
    
    <H1>Catalogue</H1>
    <table class="table">

        <tr>
            <th>Item ID</th>
            <th>Item Name</th>
            <th>Price</th>
            <th></th>
        </tr>

        <c:forEach var="item" items="${availableItems}">

            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td></td>
                <td>
                    <form action="./modifyItem" method="GET">
                        <input type="hidden" name="itemID" value="${item.id}">
                        <button class="btn" type="submit" >Modify Item</button>
                    </form>
                    <form action="./removeItem" method="GET">
                        <input type="hidden" name="itemID" value="${item.id}">
                        <button class="btn" type="submit" >Remove Item</button>
                    </form>
                </td>
            </tr>

        </c:forEach>
    </table>
</main>
<jsp:include page="footer.jsp" />
