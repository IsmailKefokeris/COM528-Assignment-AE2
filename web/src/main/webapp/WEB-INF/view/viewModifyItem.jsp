 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="org.solent.com504.oodd.cart.model.dto.User"%>
<%@page import="org.solent.com504.oodd.cart.model.dto.UserRole"%>
<c:set var = "selectedPage" value = "users" scope="request"/>
<jsp:include page="header.jsp" />

<!-- Begin page content -->
<main role="main" class="container">

    <div>
        <H1>Item Details ${modifyItem.name} </H1>
        <!-- print error message if there is one -->
        <div style="color:red;">${errorMessage}</div>
        <div style="color:green;">${message}</div>

        <form action="./viewModifyItem" method="POST">
            <table class="table">
                <thead>
                </thead>

                <tbody>
                    <tr>
                        <td>Item ID</td>
                        <td>${modifyItem.id}</td>
                    </tr>
                    <tr>
                        <td>Item UUID</td>
                        <td>${modifyItem.uuid}</td>
                    </tr>
                    <tr>
                        <td>Item Name</td>
                        <td><input type="text" name="itemName" value="${modifyItem.name}" /></td>
                    </tr>
                    <tr>
                        <td>Item Price</td>
                        <td><input type="text" name="itemPrice" value="${modifyItem.price}" /></td>
                    </tr>
                </tbody>

            </table>

            <input type="hidden" name="itemID" value="${modifyItem.id}"/>
            <button class="btn" type="submit" >Update User ${modifyItem.name}</button>
        </form>
    </div>

</main>

<jsp:include page="footer.jsp" />
