<%-- 
    Document   : affichageAPITweet
    Created on : 9 févr. 2016, 13:31:02
    Author     : Elsa Martel
--%>

<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
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
                        <p>
                            <ul>
                                <c:forEach var="t" items="${tweet}">
                                    <li>
                                        <c:out value="${t.key}"/> 
                                        <c:if test="${t.value.equals('+')}" >
                                            <c:out value="${t.value}"/> 
                                            <span class="success label"><i class="fi-plus"></i> Positif</span>
                                        </c:if>
                                        <c:if test="${t.value.equals('-')}" >
                                            <c:out value="${t.value}"/> 
                                            <span class="alert label"><i class="fi-minus"></i> Negatif</span>
                                        </c:if>
                                        <c:if test="${t.value.equals('=')}" >
                                            <c:out value="${t.value}"/> 
                                            <span class="info label"><i class="fi-list"></i> Neutre</span>
                                        </c:if>
                                    </li>
                                </c:forEach>
                            </ul>
                        </p>
                    </c:if>
                </div>
                <div class="tabs-panel" id="panel2">
                    <p> Description du modele des tweets </p>
                </div>
                <div class="tabs-panel" id="panel3">
                    <p>Statistics afficher les résultats de root, positive, neutre, négative</p>
                    <c:import url="tweet.xml" var="documentXML" />
                    <x:parse var="doc" doc="${documentXML}" />
                    <ul class="vertical menu" data-accordion-menu>
                        <li>
                            <a href="#">Root</a>
                            <ul class="menu vertical nested">
                                <p>micro f-measure</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: <x:out select="$doc/tweet/root/microfmeasure"/>">
                                        <p class="progress-meter-text"><x:out select="$doc/tweet/root/microfmeasure"/></p>
                                    </span>
                                </div>
                                <p>macro f-measure</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: <x:out select="$doc/tweet/root/macrofmeasure"/>">
                                        <p class="progress-meter-text"><x:out select="$doc/tweet/root/macrofmeasure"/></p>
                                    </span>
                                </div>
                                <p>micro precision</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: <x:out select="$doc/tweet/root/microprecision"/>">
                                        <p class="progress-meter-text"><x:out select="$doc/tweet/root/microprecision"/></p>
                                    </span>
                                </div>
                                <p>macro precision</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: <x:out select="$doc/tweet/root/macroprecision"/>">
                                        <p class="progress-meter-text"><x:out select="$doc/tweet/root/macroprecision"/></p>
                                    </span>
                                </div>
                                <p>micro recall</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: <x:out select="$doc/tweet/root/microrecall"/>">
                                        <p class="progress-meter-text"><x:out select="$doc/tweet/root/microrecall"/></p>
                                    </span>
                                </div>
                                <p>macro recall</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: <x:out select="$doc/tweet/root/macrorecall"/>">
                                        <p class="progress-meter-text"><x:out select="$doc/tweet/root/macrorecall"/></p>
                                    </span>
                                </div>
                            </ul>
                        </li>
                        <li>
                            <a href="#">Positive</a>
                            <ul class="menu vertical nested">
                                <p>f-measure</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: <x:out select="$doc/tweet/positive/fmeasure"/>">
                                        <p class="progress-meter-text"><x:out select="$doc/tweet/positive/fmeasure"/></p>
                                    </span>
                                </div>
                                <p>precision</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: <x:out select="$doc/tweet/positive/precision"/>">
                                        <p class="progress-meter-text"><x:out select="$doc/tweet/positive/precision"/></p>
                                    </span>
                                </div>
                                <p>recall</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: <x:out select="$doc/tweet/positive/recall"/>">
                                        <p class="progress-meter-text"><x:out select="$doc/tweet/positive/recall"/></p>
                                    </span>
                                </div>
                            </ul>
                        </li>
                        <li>
                            <a href="#">Neutre</a>
                            <ul class="menu vertical nested">
                                <p>f-measure</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: <x:out select="$doc/tweet/neutre/fmeasure"/>">
                                        <p class="progress-meter-text"><x:out select="$doc/tweet/neutre/fmeasure"/></p>
                                    </span>
                                </div>
                                <p>precision</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: <x:out select="$doc/tweet/neutre/precision"/>">
                                        <p class="progress-meter-text"><x:out select="$doc/tweet/neutre/precision"/></p>
                                    </span>
                                </div>
                                <p>recall</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: <x:out select="$doc/tweet/neutre/recall"/>">
                                        <p class="progress-meter-text"><x:out select="$doc/tweet/neutre/recall"/></p>
                                    </span>
                                </div>
                            </ul>
                        </li>
                        <li>
                            <a href="#">Negative</a>
                            <ul class="menu vertical nested">
                                <p>f-measure</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: <x:out select="$doc/tweet/negative/fmeasure"/>">
                                        <p class="progress-meter-text"><x:out select="$doc/tweet/negative/fmeasure"/></p>
                                    </span>
                                </div>
                                <p>precision</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: <x:out select="$doc/tweet/negative/precision"/>">
                                        <p class="progress-meter-text"><x:out select="$doc/tweet/negative/precision"/></p>
                                    </span>
                                </div>
                                <p>recall</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: <x:out select="$doc/tweet/negative/recall"/>">
                                        <p class="progress-meter-text"><x:out select="$doc/tweet/negative/recall"/></p>
                                    </span>
                                </div>
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
