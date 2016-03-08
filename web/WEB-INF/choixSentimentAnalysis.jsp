<%-- 
    Document   : choixSentimentAnalysis
    Created on : 4 mars 2016, 14:07:42
    Author     : Elsa Martel
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
        <div class="center">
        <table >
            <!--thead>
              <tr>
                <th width="200">Type of analysis</th>
                <th>Description</th>
                <th width="150">Number of models</th>
                <th width="150">Table Header</th>
              </tr!-->
            </thead>
            <tbody>
              <tr class="blue-hover">
                <td><i class="fi-social-twitter"></i></td>
                <td>This is longer content Donec id elit non mi porta gravida at eget metus.</td>
                <td>Content Goes Here</td>
                <td><form method="post" action="<c:url value="/AffichageModeleExistant"/>">
                        <input value="saisieTexte" name="choix" hidden>
                        <input type="submit" value="Classify" class="button" />
                    </form>
                </td>
              </tr>
              <tr class="blue-hover">
                <td><i class="fi-social-twitter"></i></td>
                <td>This is longer content Donec id elit non mi porta gravida at eget metus.</td>
                <td>Content Goes Here</td>
                <td><form method="post" action="<c:url value="/AffichageModeleExistant"/>">
                        <input value="saisieTexte" name="choix" hidden>
                        <input type="submit" value="Classify" class="button" />
                    </form>
                </td>
              </tr>
              <tr class="blue-hover">
                <td><i class="fi-social-twitter"></i></td>
                <td>This is longer content Donec id elit non mi porta gravida at eget metus.</td>
                <td>Content Goes Here</td>
                <td><form method="post" action="<c:url value="/AffichageModeleExistant"/>">
                        <input value="saisieTexte" name="choix" hidden>
                        <input type="submit" value="Classify" class="button" />
                    </form>
                </td>
              </tr>
              <tr class="blue-hover">
                <td><i class="fi-video"></i></td>
                <td>This is longer Content Goes Here Donec id elit non mi porta gravida at eget metus.</td>
                <td>Content Goes Here</td>
                <td><form method="post" action="<c:url value="/AffichageModeleExistant"/>">
                        <input value="saisieTexte" name="choix" hidden>
                        <input type="submit" value="Classify" class="button" />
                    </form>
                </td>
              </tr>
              <tr class="blue-hover">
                <td><i class="fi-book"></i></td>
                <td>This is longer Content Goes Here Donec id elit non mi porta gravida at eget metus.</td>
                <td>Content Goes Here</td>
                <td><form method="post" action="<c:url value="/AffichageModeleExistant"/>">
                        <input value="saisieTexte" name="choix" hidden>
                        <input type="submit" value="Classify" class="button" />
                    </form>
                </td>
              </tr>
              <tr class="blue-hover">
                <td><i class="fi-torsos-all"></i></td>
                <td>This is longer Content Goes Here Donec id elit non mi porta gravida at eget metus.</td>
                <td>Content Goes Here</td>
                <td><form method="post" action="<c:url value="/AffichageModeleExistant"/>">
                        <input value="saisieTexte" name="choix" hidden>
                        <input type="submit" value="Classify" class="button" />
                    </form>
                </td>
              </tr>
            </tbody>
        </table>
>>>>>>> Stashed changes
        </div>
                        <%@include file="foot.jsp" %>
        <jsp:include page="offCanvasEnd.jsp" />
        <%@include file="foundation.jsp" %>
    </body>
</html>
