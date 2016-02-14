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
        <div class="row medium-8 large-7 columns">
            <div class="blog-post">
                <div class="large-12 large-centered large-5 large-centered columns">
                    <div large large-centered large-5 large-centered columns >
                        <div class="callout primary">
                            <h5>This is a secondary panel</h5>
                            <p>It has an easy to override visual style, and is appropriately subdued.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row medium-8 large-7 columns">
            <div class="blog-post">
                <div class="medium-7 medium-centered large-5 large-centered columns">
                    
                    <h1>Profile</h1>
                        
                    <div class="row collapse">
                        <div class="small-2  columns">
                            <span class="prefix"><i class="fi-torso"></i></span>
                        </div>
                        <div class="small-10  columns">
                            <input type="text" placeholder="${sessionScope.prenom}" disabled="disabled">
                        </div>
                    </div>
                        
                    <div class="row collapse">
                        <div class="small-2  columns">
                            <span class="prefix"><i class="fi-torso"></i></span>
                        </div>
                        <div class="small-10  columns" >
                            <input type="text" placeholder="${sessionScope.nom}" disabled="disabled">
                        </div>
                    </div>
                        
                    <div class="row collapse">
                        <div class="small-2 columns">
                            <span class="prefix"><i class="fi-mail"></i></span>
                        </div>
                        <div class="small-10  columns">
                            <input type="text" placeholder="${sessionScope.mail}"disabled="disabled">
                        </div>
                    </div>
                        
                    <div class="row collapse">
                        <div class="small-2 columns ">
                            <span class="prefix"><i class="fi-lock"></i></span>
                        </div>
                        <div class="small-10 columns ">
                            <input type="text" placeholder="Realy?" disabled="disabled">
                        </div>
                    </div>                    
                </div>
            </div>
        </div>
    <%@include file="foot.jsp" %>
        <jsp:include page="offCanvasEnd.jsp" />
        <%@include file="foundation.jsp" %>
    </body>
</html>
