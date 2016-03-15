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
                      <form method="post" action="<c:url value="/manage"/>" data-abide novalidate>
                            <div data-abide-error class="alert callout" style="display: none;">
                                <p><i class="fi-alert"></i> There are some errors in your form.</p>
                            </div>
                            <div class="row column log-on-form">
                                <h4 class="text-center">Create a new model</h4>
                                <label>File name
                                    <input pattern="text" name="file_name" id="file_name" type="text" placeholder="File name" required>
                                </label>
                                <label>Additional information
                                    <textarea id="info" name="info" placeholder="Additional information"></textarea>
                                </label>
                                <input type="submit" value="Create new file" class="button" />  
                            </div>   
                        </form> 
                  </div>
              </div>
            </div>
        </c:if>
        <%@include file="foot.jsp" %>    
        <%@include file="foundation.jsp" %>
    </body>
</html>
