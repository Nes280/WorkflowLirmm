<%-- 
    Document   : manage
    Created on : 11 mars 2016, 13:15:22
    Author     : Niels
--%>

<%@include file="head.jsp" %>
    <body>
        <script>

            function attention()
{
	resultat=window.confirm('Caution, you are about to delete your model and all files that would be attached to it. Do you want to continue?');
	if (resultat==1)
	{
	}
	else
	{
            return false;
	}
}
     </script>
      <%@include file="topMenu.jsp" %>
      <c:if test="${ sessionScope.isLog != 1}">
            <%@include file="forbidden.jsp" %>
        </c:if>
        <c:if test="${ sessionScope.isLog == 1}">
            
            <div class="zone">
                <c:choose>  
                    <c:when  test="${tableau != ''}">   
                        <table class="">
                            <thead>
                                <tr>
                                    <th>Model name</th>
                                    <th>Information</th>
                                    <th>Last update</th>
                                    <th>Configure</th>
                                    <th>Train</th>
                                    <th>Delete</th>    
                                </tr>
                            </thead>
                            <tbody>
                                ${tableau}
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div class="callout primary">
                            <h5>You don't have yet any models.</h5>
                            <p>After your first model creation, your models appear here.</p>
                        </div>
                    </c:otherwise>
                </c:choose> 
                
            </div>
            </div>
            <div class="zone">          
                <div class="blog-post">
                    <form method="post" action="<c:url value="/manage"/>" data-abide novalidate>
                        <div data-abide-error class="alert callout" style="display: none;">
                            <p><i class="fi-alert"></i> There are some errors in your form.</p>
                        </div>
                        <div class="row column log-on-form">
                            <h4 class="text-center">Create a new model</h4>
                            <label>File name
                                <input pattern="text" value="" name="file_name" id="file_name" type="text" placeholder="File name" required>
                            </label>
                            <label>Additional information
                                <textarea id="info" value="" name="info" placeholder="Additional information"></textarea>
                            </label>
                            <input type="submit" value="Create new file" class="button" />  
                        </div>   
                    </form> 
                </div>
            </div>   
        </c:if>
        <%@include file="foot.jsp" %>    
        <%@include file="foundation.jsp" %>
    </body>
</html>
