<%-- 
    Document   : logon
    Created on : 13 févr. 2016, 10:32:15
    Author     : Niels
--%>
<%@include file="head.jsp" %>
    <body>
        <jsp:include page="topMenu.jsp">
            <jsp:param name="topMenuName" value="${topMenuName}"/>
        </jsp:include>
        
        <jsp:include page="Breadcrumbs.jsp">
            <jsp:param name="breadcrumbs" value="${breadcrumbs}"/>
        </jsp:include>
        <h1>Log on</h1>
        <p>En construction!</p>
        <%@include file="foot.jsp" %>
        <%@include file="foundation.jsp" %>
    </body>
</html>