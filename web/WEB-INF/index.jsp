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
        <%@include file="Breadcrumbs.jsp" %>
        <div class="row medium-8 large-7 columns">
            <div class="blog-post">
                <h1>Hello World!</h1>
                <p>Welcom to the ${title} of the web sit!</p>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
            </div>
        </div>
        <%@include file="foot.jsp" %>
        <%@include file="foundation.jsp" %>
    </body>
</html>
