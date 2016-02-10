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
        <div class="row medium-8 large-7 columns">
            <ul class="tabs" data-tabs id="example-tabs">
                <li class="tabs-title is-active"><a href="#panel1" aria-selected="true">API</a></li>
                <li class="tabs-title"><a href="#panel2">Description</a></li>
                <li class="tabs-title"><a href="#panel3">Statistics</a></li>
            </ul>

            <div class="tabs-content" data-tabs-content="example-tabs">
                    <div class="tabs-panel is-active" id="panel1">
                        <p> API affichage des resultats </p>
                        <p>${message}</p>
                        <c:if test="${ !erreur}">
                            <p>${tweet}</p>
                            <p>${resultat}</p>
                        </c:if>
                    </div>
                    <div class="tabs-panel" id="panel2">
                        <p> Description du modele des tweets </p>
                    </div>
                    <div class="tabs-panel" id="panel3">
                        <p>Statistics afficher les résultats de root, positive, neutre, négative</p>
                        <ul class="vertical menu" data-accordion-menu>
                            <li>
                                <a href="#">Root</a>
                                <ul class="menu vertical nested">
                                    <p>micro f-measure</p>
                                    <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                        <span class="progress-meter" style="width: 25%">
                                            <p class="progress-meter-text">25%</p>
                                        </span>
                                    </div>
                                    <p>macro f-measure</p>
                                    <p>micro precision</p>
                                    <p>macro precision</p>
                                    <p>micro recall</p>
                                    <p>macro recall</p>
                                </ul>
                            </li>
                            <li>
                                <a href="#">Positive</a>
                                <ul class="menu vertical nested">
                                    <p>f-measure</p>
                                    <p>precision</p>
                                    <p>recall</p>
                                </ul>
                            </li>
                            <li>
                                <a href="#">Neutre</a>
                                <ul class="menu vertical nested">
                                    <p>f-measure</p>
                                    <p>precision</p>
                                    <p>recall</p>
                                </ul>
                            </li>
                            <li>
                                <a href="#">Negative</a>
                                <ul class="menu vertical nested">
                                    <p>f-measure</p>
                                    <p>precision</p>
                                    <p>recall</p>
                                </ul>
                            </li>
                          </ul>
                    </div>

            </div>
        </div>
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
