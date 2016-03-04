<%-- 
    Document   : choixSentimentAnalysis
    Created on : 4 mars 2016, 14:07:42
    Author     : Elsa Martel
--%>

<%@include file="head.jsp" %>

    <body>
        <h1>Hello World!</h1>
        <a href="affichageModeleExistant">French Tweets polarity</a>
        <form method="post" action="<c:url value="/affichageModeleExistant"/>">
            <div class="row">
                <div class="large-12 columns">
                    <label>Write your tweet : 
                        <textarea id="tweetAAnalyser" name="tweetAAnalyser" placeholder=""></textarea>
                    </label>
                </div>
            </div>
            <input value="saisieTexte" name="choix" hidden>
            <input type="classify" value="Classify" class="button" />
        </form>

    </body>
</html>
