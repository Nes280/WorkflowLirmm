<%-- 
    Document   : affichageAPITweet
    Created on : 9 févr. 2016, 13:31:02
    Author     : Elsa Martel
--%>

<%@include file="head.jsp" %>

    <body>
        <jsp:include page="topMenu.jsp">
            <jsp:param name="topMenuName" value="${topMenuName}"/>
        </jsp:include>
        
        <jsp:include page="Breadcrumbs.jsp">
            <jsp:param name="breadcrumbs" value="${breadcrumbs}"/>
        </jsp:include>
        
        <p>${tweet}</p>
        <p>${resultat}</p>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
         <%@include file="foot.jsp" %>
        <%@include file="foundation.jsp" %>
    </body>
</html>
