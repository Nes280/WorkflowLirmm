<%-- 
    Document   : offCanvas
    Created on : 14 févr. 2016, 14:41:39
    Author     : Niels
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="off-canvas-wrapper">
    <div class="off-canvas-wrapper-inner" data-off-canvas-wrapper>
        <div class="off-canvas position-right" id="offCanvas" data-off-canvas data-position="right">
            <ul class="no-bullet">
                <form method="get" action="<c:url value="/profile"/>">
                    <li><button  >Profile</button></li>
                </form>
                <li><button  >Manage my models</button></li>
                <li><button  >About</button></li>
                <li><button  >About</button></li>
                <form method="get" action="<c:url value="/logOut"/>">
                    <li> <button class="alert hollow button" href="#">Log out</button></li>
                </form>
            </ul>
        </div>
        <div class="off-canvas-content" data-off-canvas-content>
