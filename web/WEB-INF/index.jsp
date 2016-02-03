<%-- 
    Document   : index
    Created on : 2 févr. 2016, 20:13:36
    Author     : Niels
--%>
<%@include file="head.jsp" %>
    <body>
        <jsp:include page="topMenu.jsp">
            <jsp:param name="topMenuName" value="${topMenuName}"/>
        </jsp:include>
        <h1>Hello World!</h1>
        <p>Welcom to the ${title} of the web sit!</p>
        <%@include file="foot.jsp" %>
        <%@include file="foundation.jsp" %>
    </body>
</html>
