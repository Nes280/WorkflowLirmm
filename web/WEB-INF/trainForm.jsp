<%-- 
    Document   : trainForm
    Created on : 5 avr. 2016, 19:18:27
    Author     : Niels
--%>

<%@include file="head.jsp" %>
    <body>
        <%@include file="topMenu.jsp" %>
        <div class="zone">
            <div class="row medium-8 large-7 columns">
                <h4>Train your data</h4>
                <form method="post" action="<c:url value="/train-data"/>" data-abide novalidate enctype="multipart/form-data">
                    <div data-abide-error class="alert callout" style="display: none;">
                        <p><i class="fi-alert"></i> There are some errors in your form.</p>
                    </div>
                    <h5>
                        Train Data:  <label for="fileUpload" class="button">Upload file</label>
                                    <input type="file" id="fileUpload" name="fileUpload" class="show-for-sr">
                    </h5>
                        <input type="radio" name="classification" value="Cross" id="Cross" required checked="checked"><label for="Cross">Cross Validation</label>
                            <label>Folds
                                <select id="select" class="small-2">
                                <option value="10">10</option>
                                <option value="5">5</option>
                                <option value="3">3</option>
                              </select>
                            </label>
                          
                        <input type="radio" name="classification" value="Test" id="Test"><label for="Test">Test data</label>
                        <label for="fileUpload2" class="button">Upload file</label>
                           <input type="file" id="fileUpload2" name="fileUpload2" class="show-for-sr">
                           
                        <h4>Mode</h4>
                        <input type="radio" name="mode" value="text" id="text" required checked="checked"><label for="text">Default free text 
                            <span data-tooltip aria-haspopup="true" class="has-tip" data-disable-hover="false" tabindex="1" title="Fancy word for a beetle."><i class=" fi-info "></i></span>
                        </label>
                        <br />
                        <input type="radio" name="mode" value="tweet" id="tweet"><label for="tweet">Default tweet 
                            <span data-tooltip aria-haspopup="true" class="has-tip" data-disable-hover="false" tabindex="1" title="Fancy word for a beetle."><i class=" fi-info "></i></span>
                        </label>
                        <br />
                        <input type="radio" name="mode" value="advenced" id="advenced"><label for="advenced">Advenced</label>
                        <br />  
                        <input value="true" name="hidden" hidden>
                        <input type="submit" value="Next" class="hollow button">
                    
                </form>
            </div>
        </div>
                
        </div>
        <h1>Hello World!</h1>
        <p>File id ${fileId}</p>
    <%@include file="foot.jsp" %>
    <%@include file="foundation.jsp" %>
    </body>
</html>
