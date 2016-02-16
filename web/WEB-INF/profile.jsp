<%-- 
    Document   : profile
    Created on : 14 févr. 2016, 17:17:06
    Author     : Niels
--%>

<%@include file="head.jsp" %>
    <body>
        <jsp:include page="offCanvasBegin.jsp" />
        <jsp:include page="topMenu.jsp">
            <jsp:param name="topMenuName" value="${topMenuName}"/>
        </jsp:include>
        
        <jsp:include page="Breadcrumbs.jsp">
            <jsp:param name="breadcrumbs" value="${breadcrumbs}"/>
        </jsp:include>
        <c:choose>
            <c:when test="${edit == 1}">
                <div class="row medium-8 large-7 columns">
                    <div class="blog-post">
                        <div class="large-12 large-centered large-5 large-centered columns">
                            <div large large-centered large-5 large-centered columns >
                                <div class="warning callout primary">
                                    <h5>Change your settings</h5>
                                    <p>It has an easy to override visual style, and is appropriately subdued.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:when test="${edit == 2}">
                <div class="row medium-8 large-7 columns">
                    <div class="blog-post">
                        <div class="large-12 large-centered large-5 large-centered columns">
                            <div large large-centered large-5 large-centered columns >
                                <div class="warning callout primary">
                                    <h5>Change your password</h5>
                                    <p>It has an easy to override visual style, and is appropriately subdued.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="row medium-8 large-7 columns">
                    <div class="blog-post">
                        <div class="large-12 large-centered large-5 large-centered columns">
                            <div large large-centered large-5 large-centered columns >
                                <div class="warning callout primary">
                                    <h5>This is a secondary panel</h5>
                                    <p>It has an easy to override visual style, and is appropriately subdued.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        <div class="row medium-8 large-7 columns">
            <div class="blog-post">
                <div class="medium-7 medium-centered large-5 large-centered columns">
                    
                    <h1>Profile</h1>
                    
                    <c:choose>
                        <c:when test="${edit == 1}">
                            <form  method="post" action="<c:url value="/profile"/>">
                                <input name="change" value="settings" hidden>
                                <div class="row collapse">
                                    <div class="small-2  columns">
                                        <span class="prefix"><i class="fi-torso"></i></span>
                                    </div>
                                    <div class="small-10  columns">
                                        <input type="text" name="Lname" value="${sessionScope.nom}" >
                                    </div>
                                </div>

                                <div class="row collapse">
                                    <div class="small-2  columns">
                                        <span class="prefix"><i class="fi-torso"></i></span>
                                    </div>
                                    <div class="small-10  columns" >
                                        <input type="text" name="Fname" value="${sessionScope.prenom}" >
                                    </div>
                                </div>

                                <div class="row collapse">
                                    <div class="small-2 columns">
                                        <span class="prefix"><i class="fi-mail"></i></span>
                                    </div>
                                    <div class="small-10  columns">
                                        <input type="text" name="Mail" value="${sessionScope.mail}">
                                    </div>
                                </div>
                                <button type="submit" class="alert hollow button">Send</button>
                            </form>
                        </c:when>
                        
                        <c:when test="${edit == 2}">
                            <form  method="post" action="<c:url value="/profile"/>">
                                <input name="change" value="password" hidden>
                                <div class="row collapse">
                                    <div class="small-2 columns ">
                                        <span class="prefix"><i class="fi-lock"></i></span>
                                    </div>
                                    <div class="small-10 columns ">
                                        <input  name="pass" type="password" value="" >
                                    </div>
                                </div>
                                <div class="row collapse">
                                    <div class="small-2 columns ">
                                        <span class="prefix"><i class="fi-lock"></i></span>
                                    </div>
                                    <div class="small-10 columns ">
                                        <input name="rePass" type="password" value="" >
                                    </div>
                                </div>
                                <button class="hollow button">Send</button>
                            </form>
                        </c:when>
                        
                        <c:otherwise>
                            <div class="row collapse">
                                <div class="small-2  columns">
                                    <span class="prefix"><i class="fi-torso"></i></span>
                                </div>
                                <div class="small-10  columns">
                                    <input type="text" value="${sessionScope.nom}" disabled="disabled">
                                </div>
                            </div>

                            <div class="row collapse">
                                <div class="small-2  columns">
                                    <span class="prefix"><i class="fi-torso"></i></span>
                                </div>
                                <div class="small-10  columns" >
                                    <input type="text" value="${sessionScope.prenom}" disabled="disabled">
                                </div>
                            </div>

                            <div class="row collapse">
                                <div class="small-2 columns">
                                    <span class="prefix"><i class="fi-mail"></i></span>
                                </div>
                                <div class="small-10  columns">
                                    <input type="text" value="${sessionScope.mail}"disabled="disabled">
                                </div>
                            </div>

                            <div class="row collapse">
                                <div class="small-2 columns ">
                                    <span class="prefix"><i class="fi-lock"></i></span>
                                </div>
                                <div class="small-10 columns ">
                                    <input type="password" value="Realy?" disabled="disabled">
                                </div>
                            </div>
                                <form  method="get" action="<c:url value="/profile"/>">
                                    <input value="settings" name="edit" hidden>
                                    <button type="submit" class="hollow button">Change setting</button>
                                </form>
                                <form  method="get" action="<c:url value="/profile"/>">
                                    <input value="password" name="edit" hidden>
                                    <button type="submit" class="warning hollow button">Change password</button>
                                </form>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    <%@include file="foot.jsp" %>
        <jsp:include page="offCanvasEnd.jsp" />
        <%@include file="foundation.jsp" %>
    </body>
</html>
