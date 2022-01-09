<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page import="org.solent.com504.oodd.cart.model.dto.CardDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
// request set in controller
//    request.setAttribute("selectedPage","contact");
%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <H1 style="text-align: center">Checkout Items</H1>
    <div style="color:red;">${errorMessage}</div>
    <div style="color:green;">${message}</div>


    <H4 style="text-align: center">Enter your credit card details to purchase items.....</H4>
    <form style="text-align: center" action="./checkout" method="POST">
        <input type="hidden" name="action" value="continue">
        <p>Credit Card Number <input type="text" name="cardNum" value="" required></input></p><BR>
        <p>Expiry Date  <input type="text" name="endDate" value="" required></input></p>
        <p>Issue Number  <input type="text" name="issueNum" value="" required></input></p>
        <p>CVV Number  <input type="password" name="cvv" required></input></p>
        <p><button type="submit">continue</button></p>
    </form> 
</main>


<jsp:include page="footer.jsp" />
