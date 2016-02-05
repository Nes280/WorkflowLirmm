<%-- 
    Document   : SaisieTexteTweet
    Created on : 5 févr. 2016, 15:17:31
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
        <div class="row medium-8 large-7 columns">
            <div class="blog-post">
                <p>Welcom to the ${title} of the web sit!</p>
                <form>
                    <div class="row">
                        <div class="large-12 columns">
                          <label>Write your tweet : 
                            <textarea placeholder=""></textarea>
                          </label>
                        </div>
                    </div>
                    <label for="submit" class="button">Submit</label>
                    <input id="submit" class="show-for-sr">
                </form>
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
