<%-- 
    Document   : TopMenu
    Created on : 2 févr. 2016, 20:23:29
    Author     : Niels
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${ sessionScope.isLog == 1}">
    <div class="title-bar" data-responsive-toggle="example-menu" data-hide-for="medium">
      <button class="menu-icon" type="button" data-toggle></button>
      <div class="title-bar-title">Menu</div>
    </div>

    <div class="top-bar" id="example-menu" >
        <div class="top-bar-left">
            <ul class="dropdown menu" data-dropdown-menu>
                <li class="menu-text"><a href="<c:url value="/index"/>"><i class="fi-home"> Home</i></a></li>
            </ul>
        </div>
        <div class="top-bar-right">
            <ul class="dropdown menu" data-dropdown-menu>
                
                <li><a href="#">Sentiment analysis</a>
                    <ul class="menu vertical">
                        <li><a href="saisieTexteTweet">Tweets</a></li>
                        <li><a href="#">Movie reviews</a></li>
                        <li><a href="#">Book reviews</a></li>
                        <li><a href="#">Parliamentary debates</a></li>
                    </ul>
                </li>
                <li><a href="choixSentimentAnalysis">New Sentiment analysis</a>
                <li><a href="#">Help</a></li>
                <li><button class="warning hollow button" data-open="offCanvas">${sessionScope.prenom} ${sessionScope.nom}</button></li>
            </ul>  
        </div>
    </div>
    
</c:if>
<c:if test="${ sessionScope.isLog != 1}">
    <div class="title-bar" data-responsive-toggle="example-menu" data-hide-for="medium">
        <button class="menu-icon" type="button" data-toggle></button>
        <div class="title-bar-title">Menu</div>
    </div>
    <div class="top-bar" id="example-menu">
        <div class="top-bar-left">
            <ul class="dropdown menu" data-dropdown-menu>
                <li class="menu-text">${topMenuName}</li>
                <li><a href="#">Help</a></li>
            </ul>
        </div>
        <div class="top-bar-right">
            <button type="button" class="success hollow button" onClick="window.location.href='<c:url value="/logOn"/>';">Signup</button>
            <button class="button" type="button" data-toggle="example-dropdown" data-open="logIn">log in</button>
            <div class="reveal" id="logIn" data-reveal>
                <div class="medium-7 medium-centered large-5 large-centered columns">
                    <form method="post" action="<c:url value="/logIn"/>" data-abide novalidate>
                        <div data-abide-error class="alert callout" style="display: none;">
                            <p><i class="fi-alert"></i> There are some errors in your form.</p>
                        </div>
                        <div class="row column log-in-form">
                            <h4 class="text-center">Log in with your email account</h4>
                            <label>Email
                                <input pattern="email" name="mail" id="mail" type="text" placeholder="somebody@example.com" required>
                            </label>
                            <label>Password
                                <input  name="pass" id="pass" type="password" placeholder="Password" required >
                            </label>
                                <input type="submit" value="Login!" class="button" />
                                <p class="text-center"><a href="#">Forgot your password?</a></p>   
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</c:if>                       
    