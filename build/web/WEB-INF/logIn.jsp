<%-- 
    Document   : logIn
    Created on : 9 févr. 2016, 15:06:25
    Author     : niels
--%>

<%@include file="head.jsp" %>
    <body>
        <jsp:include page="topMenu.jsp">
            <jsp:param name="topMenuName" value="${topMenuName}"/>
        </jsp:include>
        
        <jsp:include page="Breadcrumbs.jsp">
            <jsp:param name="breadcrumbs" value="${breadcrumbs}"/>
        </jsp:include>
        <div class="row medium-8 large-7 columns">
            <div class="blog-post">
                <div class="medium-7 medium-centered large-5 large-centered columns">
                        <form method="post" action="<c:url value="/logIn"/>">
                            <div class="row column log-in-form">
                                <h4 class="text-center">Log in with your email account</h4>
                                <label>Email
                                    <input name="mail" id="mail" type="text" placeholder="somebody@example.com">
                                </label>
                                <label>Password
                                    <input name="pass" id="pass" type="password" placeholder="Password">
                                </label>
                                <input type="submit" value="Login!" class="button" />
                                <p class="text-center"><a href="#">Not yet registered?</a></p>
                                <p class="text-center"><a href="#">Forgot your password?</a></p>   
                            </div>
                        </form>
                    </div>
            </div>
        </div>
        <%@include file="foot.jsp" %>
        <%@include file="foundation.jsp" %>
    </body>
</html>