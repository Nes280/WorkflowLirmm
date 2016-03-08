<%-- 
    Document   : affichageModeleExistant
    Created on : 4 mars 2016, 14:15:39
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
            <p>French Tweet Polarity</p> 
            
            <ul class="tabs" data-tabs id="example-tabs">
                <li class="tabs-title is-active"><a href="#panel1" aria-selected="true">Description</a></li>
                <li class="tabs-title"><a href="#panel2">Use Model</a></li>
            </ul>
            
            <div class="tabs-content" data-tabs-content="example-tabs">
                <div class="tabs-panel is-active" id="panel1">
                    <p>
                        <h3>Description </h3>
                        ${description}
                    </p>
                    <br/>
                    <p>
                        <h3>Global</h3>
                        <ul class="menu vertical nested">
                            <p>Micro f-measure</p>
                            <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                <span class="progress-meter" style="width: ${root.getMicrofmeasure()}">
                                    <p class="progress-meter-text">${root.getMicrofmeasure()}</p>
                                </span>
                            </div>
                            <p>Macro f-measure</p>
                            <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                <span class="progress-meter" style="width: ${root.getMacrofmeasure()}">
                                    <p class="progress-meter-text">${root.getMacrofmeasure()}</p>
                                </span>
                            </div>
                            <p>Micro precision</p>
                            <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                <span class="progress-meter" style="width: ${root.getMicroprecision()}">
                                    <p class="progress-meter-text">${root.getMicroprecision()}</p>
                                </span>
                            </div>
                            <p>Macro precision</p>
                            <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                <span class="progress-meter" style="width: ${root.getMacroprecision()}">
                                    <p class="progress-meter-text">${root.getMacroprecision()}</p>
                                </span>
                            </div>
                            <p>Micro recall</p>
                            <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                <span class="progress-meter" style="width: ${root.getMicrorecall()}">
                                    <p class="progress-meter-text">${root.getMicrorecall()}</p>
                                </span>
                            </div>
                            <p>Macro recall</p>
                            <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                <span class="progress-meter" style="width: ${root.getMacrorecall()}">
                                    <p class="progress-meter-text">${root.getMacrorecall()}</p>
                                </span>
                            </div>
                        </ul>
                    </p>
                    <br/>
                   <ul class="vertical menu" data-accordion-menu>
                        <li>
                            <a href="#">Positive</a>
                            <ul class="menu vertical nested">
                                <p>F-measure</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: ${positive.getFmeasure()}">
                                        <p class="progress-meter-text">${positive.getFmeasure()}</p>
                                    </span>
                                </div>
                                <p>Precision</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: ${positive.getPrecision()}">
                                        <p class="progress-meter-text">${positive.getPrecision()}</p>
                                    </span>
                                </div>
                                <p>Recall</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: ${positive.getRecall()}">
                                        <p class="progress-meter-text">${positive.getRecall()}</p>
                                    </span>
                                </div>
                            </ul>
                        </li>
                        <li>
                            <a href="#">Neutre</a>
                            <ul class="menu vertical nested">
                                <p>F-measure</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: ${neutre.getFmeasure()}">
                                        <p class="progress-meter-text">${neutre.getFmeasure()}</p>
                                    </span>
                                </div>
                                <p>Precision</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: ${neutre.getPrecision()}">
                                        <p class="progress-meter-text">${neutre.getPrecision()}</p>
                                    </span>
                                </div>
                                <p>Recall</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: ${neutre.getRecall()}">
                                        <p class="progress-meter-text">${neutre.getRecall()}</p>
                                    </span>
                                </div>
                            </ul>
                        </li>
                        <li>
                            <a href="#">Negative</a>
                            <ul class="menu vertical nested">
                                <p>F-measure</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: ${negative.getFmeasure()}">
                                        <p class="progress-meter-text">${negative.getFmeasure()}</p>
                                    </span>
                                </div>
                                <p>Precision</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: ${negative.getPrecision()}">
                                        <p class="progress-meter-text">${negative.getPrecision()}</p>
                                    </span>
                                </div>
                                <p>Recall</p>
                                <div class="progress" role="progressbar" tabindex="0" aria-valuenow="20" aria-valuemin="0" aria-valuetext="25 percent" aria-valuemax="100">
                                    <span class="progress-meter" style="width: ${negative.getRecall()}">
                                        <p class="progress-meter-text">${negative.getRecall()}</p>
                                    </span>
                                </div>
                            </ul>
                        </li>
                      </ul>
                </div>
                <div class="tabs-panel" id="panel2">
                    <div>
                        <form method="post" action="<c:url value="/affichageModeleExistant"/>">
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Write your tweet : 
                                        <textarea id="tweetAAnalyser" name="tweetAAnalyser" placeholder=""></textarea>
                                    </label>
                                </div>
                            </div>
                            <input value="saisieTexte" name="choix" hidden>
                            <input type="submit" value="Classify" class="button" />
                        </form>
                    </div>
                    <br/>
                    <div>
                        <form method="post" action="<c:url value="/affichageModeleExistant"/>" enctype="multipart/form-data">
                            <p>Write a tweet for line and skip a line. The format of the text is .txt. </p>
                            <label for="fileUpload" class="button">Upload File</label>
                            <input type="file" id="fileUpload" name="fileUpload" class="show-for-sr">
                            <input value="uploadFile" name="choix" hidden>
                            <input type="submit" value="Classify" class="button" />
                        </form>             
                     </div>
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
