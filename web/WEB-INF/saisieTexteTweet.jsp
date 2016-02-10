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
            <p>Welcom to the ${title} of the web sit!</p> 
            
            <ul class="tabs" data-tabs id="example-tabs">
                <li class="tabs-title is-active"><a href="#panel1" aria-selected="true">Enter text</a></li>
                <li class="tabs-title"><a href="#panel2">Upload a file</a></li>
            </ul>
            
            <div class="tabs-content" data-tabs-content="example-tabs">
                <div class="tabs-panel is-active" id="panel1">
                    <form method="get" action="<c:url value="/affichageAPITweet"/>">
                        <div class="row">
                            <div class="large-12 columns">
                                <label>Write your tweet : 
                                    <textarea id="tweetAAnalyser" name="tweetAAnalyser" placeholder=""></textarea>
                                </label>
                            </div>
                        </div>
                        <input type="submit" value="Submit" class="button" />
                    </form>
                </div>
                <div class="tabs-panel" id="panel2">
                    <p>Ecrire un tweet par ligne. </p>
                    <label for="exampleFileUpload" class="button">Upload File</label>
                    <input type="file" id="exampleFileUpload" class="show-for-sr">
                </div>
            </div>
        </div>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>

        <%@include file="foot.jsp" %>
        <%@include file="foundation.jsp" %>
    </body>
</html>
