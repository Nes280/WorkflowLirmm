<%-- 
    Document   : manage
    Created on : 11 mars 2016, 13:15:22
    Author     : Niels
--%>

<%@include file="head.jsp" %>
    <body>
      <%@include file="topMenu.jsp" %>
      <c:if test="${ sessionScope.isLog != 1}">
            <%@include file="forbidden.jsp" %>
        </c:if>
        <c:if test="${ sessionScope.isLog == 1}">
            <div class="zone">
              <div class="row medium-8 large-7 columns">
                  <div class="blog-post">
                      <h1>Manage</h1>
                      <p>Welcom to the ${title} of the web sit!</p>
                      <p> --${test}--</p>
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
                      <br/>  
                  </div>
              </div>
            </div>
        </c:if>
        <%@include file="foot.jsp" %>    
        <%@include file="foundation.jsp" %>
    </body>
</html>
