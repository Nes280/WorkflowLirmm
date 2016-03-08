<%-- 
    Document   : choixSentimentAnalysis
    Created on : 4 mars 2016, 14:07:42
    Author     : Elsa Martel
--%>

<%@include file="head.jsp" %>

    <body>
        <%@include file="topMenu.jsp" %>
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
                <td><i class="fi-social-twitter"></i> French Tweets polarity</td>
                <td>This is longer content Donec id elit non mi porta gravida at eget metus.</td>
                <td>Content Goes Here</td>
                <td><form method="post" action="<c:url value="/AffichageModeleExistant"/>">
                        <input value="saisieTexte" name="choix" hidden>
                        <input type="submit" value="Classify" class="button" />
                    </form>
                </td>
              </tr>
              <tr class="blue-hover">
                <td><i class="fi-social-twitter"></i> French  Tweets subjectivity</td>
                <td>This is longer content Donec id elit non mi porta gravida at eget metus.</td>
                <td>Content Goes Here</td>
                <td><form method="post" action="<c:url value="/AffichageModeleExistant"/>">
                        <input value="saisieTexte" name="choix" hidden>
                        <input type="submit" value="Classify" class="button" />
                    </form>
                </td>
              </tr>
              <tr class="blue-hover">
                <td><i class="fi-social-twitter"></i> French  Tweets emotion</td>
                <td>This is longer content Donec id elit non mi porta gravida at eget metus.</td>
                <td>Content Goes Here</td>
                <td><form method="post" action="<c:url value="/AffichageModeleExistant"/>">
                        <input value="saisieTexte" name="choix" hidden>
                        <input type="submit" value="Classify" class="button" />
                    </form>
                </td>
              </tr>
              <tr class="blue-hover">
                <td><i class="fi-video"></i> French  Movie reviews</td>
                <td>This is longer Content Goes Here Donec id elit non mi porta gravida at eget metus.</td>
                <td>Content Goes Here</td>
                <td><form method="post" action="<c:url value="/AffichageModeleExistant"/>">
                        <input value="saisieTexte" name="choix" hidden>
                        <input type="submit" value="Classify" class="button" />
                    </form>
                </td>
              </tr>
              <tr class="blue-hover">
                <td><i class="fi-book"></i> French Book reviews</td>
                <td>This is longer Content Goes Here Donec id elit non mi porta gravida at eget metus.</td>
                <td>Content Goes Here</td>
                <td><form method="post" action="<c:url value="/AffichageModeleExistant"/>">
                        <input value="saisieTexte" name="choix" hidden>
                        <input type="submit" value="Classify" class="button" />
                    </form>
                </td>
              </tr>
              <tr class="blue-hover">
                <td><i class="fi-torsos-all"></i> French  parlementary debates</td>
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
        </div>
        <%@include file="foot.jsp" %>
        <%@include file="foundation.jsp" %>
    </body>
</html>
