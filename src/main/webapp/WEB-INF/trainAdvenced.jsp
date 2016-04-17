<%-- 
    Document   : trainAdvenced
    Created on : 12 avr. 2016, 10:58:13
    Author     : niels
--%>

<%@include file="head.jsp" %>
    <body>
        <%@include file="topMenu.jsp" %>
        <form method="post" action="<c:url value="/train-data-advenced"/>" data-abide novalidate >
            
            <div class="zone column-row"> 
                <div class="row medium-centered" >
                    <h3>Data</h3>
                    <c:if test="${classification == 'Cross'}">
                        <label>Data.nbFolds: 
                            <input class="small-3" type="number" name="Data.nbFolds" max="10" min="1" value="${folds}">
                        </label>
                    </c:if> 
                </div>
                <div class="row medium-centered" >
                    <h3>Ngrams</h3>
                    <label>Ngrams.min: 
                        <input class="small-3" type="number" name="Ngrams.min" max="3" min="1" value="1">
                    </label>
                    <label>Ngrams.max: 
                        <input class="small-3" type="number" name="Ngrams.max" max="3" min="1" value="1">
                    </label>
                </div>
            </div>
            
            <div class="zone">
                <div class="row medium-centered">
                <h3>Preprocessings</h3>
            
                <p>Preprocessings.lowercase</p>
                <div class="switch large">
                    <input class="switch-input" id="yes-no1" type="checkbox" checked name="lowercase">
                    <label class="switch-paddle" for="yes-no1">
                        <span class="show-for-sr">Preprocessings.lowercase</span>
                        <span class="switch-active" aria-hidden="true">Yes</span>
                        <span class="switch-inactive" aria-hidden="true">No</span>
                    </label>
                </div>
            
                <p>Preprocessings.lemmatize</p>
                <div class="switch large">
                    <input class="switch-input" id="yes-no2" type="checkbox" checked name="lemmatize">
                    <label class="switch-paddle" for="yes-no2">
                        <span class="show-for-sr">Preprocessings.lemmatize</span>
                        <span class="switch-active" aria-hidden="true">Yes</span>
                        <span class="switch-inactive" aria-hidden="true">No</span>
                    </label>
                </div>
            
                <p>Preprocessings.removeStopWords</p>
                <div class="switch large">
                    <input class="switch-input" id="yes-no3" type="checkbox" checked name="removeStopWords">
                    <label class="switch-paddle" for="yes-no3">
                        <span class="show-for-sr">Preprocessings.removeStopWords</span>
                        <span class="switch-active" aria-hidden="true">Yes</span>
                        <span class="switch-inactive" aria-hidden="true">No</span>
                    </label>
                </div>
            
                <p>Preprocessings.normalizeSlang</p>
                <div class="switch large">
                    <input class="switch-input" id="yes-no4" type="checkbox" name="normalizeSlang">
                    <label class="switch-paddle" for="yes-no4">
                        <span class="show-for-sr">Preprocessings.normalizeSlang</span>
                        <span class="switch-active" aria-hidden="true">Yes</span>
                        <span class="switch-inactive" aria-hidden="true">No</span>
                    </label>
                </div>
            
                <p>Preprocessings.normalizeHyperlinks</p>
                <div class="switch large">
                    <input class="switch-input" id="yes-no5" type="checkbox" checked name="normalizeHyperlinks">
                    <label class="switch-paddle" for="yes-no5">
                        <span class="show-for-sr">Preprocessings.normalizeHyperlinks</span>
                        <span class="switch-active" aria-hidden="true">Yes</span>
                        <span class="switch-inactive" aria-hidden="true">No</span>
                    </label>
                </div>
            
                <p>Preprocessings.normalizeEmails</p>
                <div class="switch large">
                    <input class="switch-input" id="yes-no6" type="checkbox" name="normalizeEmails">
                    <label class="switch-paddle" for="yes-no6">
                        <span class="show-for-sr">Preprocessings.normalizeEmails</span>
                        <span class="switch-active" aria-hidden="true">Yes</span>
                        <span class="switch-inactive" aria-hidden="true">No</span>
                    </label>
                </div>
            
                <p>Preprocessings.replacePseudonyms</p>
                <div class="switch large">
                    <input class="switch-input" id="yes-no7" type="checkbox" checked name="replacePseudonyms">
                    <label class="switch-paddle" for="yes-no7">
                        <span class="show-for-sr">Preprocessings.replacePseudonyms</span>
                        <span class="switch-active" aria-hidden="true">Yes</span>
                        <span class="switch-inactive" aria-hidden="true">No</span>
                    </label>
                </div>
            </div>
            
            <div class="row medium-centered" >
                <h3>Lexicons</h3>
                <div class="row align-spaced">
                    <p>Lexicons.feelPol</p>
                    <div class="switch large">
                        <input class="switch-input" id="yes-no8" type="checkbox"  name="feelPol">
                        <label class="switch-paddle" for="yes-no8">
                            <span class="show-for-sr">Lexicons.feelPol</span>
                            <span class="switch-active" aria-hidden="true">Yes</span>
                            <span class="switch-inactive" aria-hidden="true">No</span>
                        </label>
                    </div>
                    <p>Lexicons.polarimotsPol</p>
                    <div class="switch large">
                        <input class="switch-input" id="yes-no9" type="checkbox"  name="polarimotsPol">
                        <label class="switch-paddle" for="yes-no9">
                            <span class="show-for-sr">Lexicons.fpolarimotsPol</span>
                            <span class="switch-active" aria-hidden="true">Yes</span>
                            <span class="switch-inactive" aria-hidden="true">No</span>
                      </label>
                    </div>
                    <p>Lexicons.affectsPol</p>
                    <div class="switch large">
                        <input class="switch-input" id="yes-no10" type="checkbox"  name="affectsPol">
                        <label class="switch-paddle" for="yes-no10">
                            <span class="show-for-sr">Lexicons.affectsPol</span>
                            <span class="switch-active" aria-hidden="true">Yes</span>
                            <span class="switch-inactive" aria-hidden="true">No</span>
                        </label>
                    </div>
                    <p>Lexicons.dikoPol</p>
                    <div class="switch large">
                        <input class="switch-input" id="yes-no11" type="checkbox"  name="dikoPol">
                        <label class="switch-paddle" for="yes-no11">
                            <span class="show-for-sr">Lexicons.dikoPol</span>
                            <span class="switch-active" aria-hidden="true">Yes</span>
                            <span class="switch-inactive" aria-hidden="true">No</span>
                        </label>
                    </div>
                    <p>Lexicons.feelEmo</p>
                    <div class="switch large">
                        <input class="switch-input" id="yes-no12" type="checkbox"  name="feelEmo">
                        <label class="switch-paddle" for="yes-no12">
                            <span class="show-for-sr">Lexicons.feelEmo</span>
                            <span class="switch-active" aria-hidden="true">Yes</span>
                            <span class="switch-inactive" aria-hidden="true">No</span>
                        </label>
                    </div>
                    <p>Lexicons.affectsEmo</p>
                    <div class="switch large">
                        <input class="switch-input" id="yes-no13" type="checkbox"  name="affectsEmo">
                        <label class="switch-paddle" for="yes-no13">
                            <span class="show-for-sr">Lexicons.affectsEmo</span>
                            <span class="switch-active" aria-hidden="true">Yes</span>
                            <span class="switch-inactive" aria-hidden="true">No</span>
                        </label>
                    </div>
                    <p>Lexicons.dikoEmo</p>
                    <div class="switch large">
                        <input class="switch-input" id="yes-no14" type="checkbox"  name="dikoEmo">
                        <label class="switch-paddle" for="yes-no14">
                            <span class="show-for-sr">Lexicons.dikoEmo</span>
                            <span class="switch-active" aria-hidden="true">Yes</span>
                            <span class="switch-inactive" aria-hidden="true">No</span>
                        </label>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="zone">
            <div class="row medium-centered " >
                <h3>Syntactic Features</h3>
                <div class="row align-spaced">
                    <p>SyntacticFeatures.countCapitalizations</p>
                    <div class="switch large">
                        <input class="switch-input" id="yes-no15" type="checkbox"  name="countCapitalizations">
                        <label class="switch-paddle" for="yes-no15">
                            <span class="show-for-sr">SyntacticFeatures.countCapitalizations</span>
                            <span class="switch-active" aria-hidden="true">Yes</span>
                            <span class="switch-inactive" aria-hidden="true">No</span>
                        </label>
                    </div>
                    <p>SyntacticFeatures.countElongatedWords</p>
                    <div class="switch large">
                        <input class="switch-input" id="yes-no16" type="checkbox"  name="countElongatedWords">
                        <label class="switch-paddle" for="yes-no16">
                            <span class="show-for-sr">SyntacticFeatures.countElongatedWords</span>
                            <span class="switch-active" aria-hidden="true">Yes</span>
                            <span class="switch-inactive" aria-hidden="true">No</span>
                      </label>
                    </div>
                    <p>SyntacticFeatures.countHashtags</p>
                    <div class="switch large">
                        <input class="switch-input" id="yes-no17" type="checkbox"  name="countHashtags">
                        <label class="switch-paddle" for="yes-no17">
                            <span class="show-for-sr">SyntacticFeatures.countHashtags</span>
                            <span class="switch-active" aria-hidden="true">Yes</span>
                            <span class="switch-inactive" aria-hidden="true">No</span>
                        </label>
                    </div>
                    <p>SyntacticFeatures.countNegators</p>
                    <div class="switch large">
                        <input class="switch-input" id="yes-no18" type="checkbox"  name="countNegators">
                        <label class="switch-paddle" for="yes-no18">
                            <span class="show-for-sr">SyntacticFeatures.countNegators</span>
                            <span class="switch-active" aria-hidden="true">Yes</span>
                            <span class="switch-inactive" aria-hidden="true">No</span>
                        </label>
                    </div>
                    <p>SyntacticFeatures.presenceSmileys</p>
                    <div class="switch large">
                        <input class="switch-input" id="yes-no19" type="checkbox"  name="presenceSmileys">
                        <label class="switch-paddle" for="yes-no19">
                            <span class="show-for-sr">SyntacticFeatures.presenceSmileys</span>
                            <span class="switch-active" aria-hidden="true">Yes</span>
                            <span class="switch-inactive" aria-hidden="true">No</span>
                        </label>
                    </div>
                    <p>SyntacticFeatures.presencePunctuation</p>
                    <div class="switch large">
                        <input class="switch-input" id="yes-no20" type="checkbox"  name="presencePunctuation">
                        <label class="switch-paddle" for="yes-no20">
                            <span class="show-for-sr">SyntacticFeatures.presencePunctuation</span>
                            <span class="switch-active" aria-hidden="true">Yes</span>
                            <span class="switch-inactive" aria-hidden="true">No</span>
                        </label>
                    </div>
                    <p>SyntacticFeatures.presencePartOfSpeechTags</p>
                    <div class="switch large">
                        <input class="switch-input" id="yes-no21" type="checkbox"  name="presencePartOfSpeechTags">
                        <label class="switch-paddle" for="yes-no21">
                            <span class="show-for-sr">SyntacticFeatures.presencePartOfSpeechTags</span>
                            <span class="switch-active" aria-hidden="true">Yes</span>
                            <span class="switch-inactive" aria-hidden="true">No</span>
                        </label>
                    </div>
                </div>
            </div>
            <div class="row  align-stretch" >
                <h3>Feature Selection</h3>
                <label>FeatureSelection.percentageAttributes: 
                    <input class="small-3" type="number" name="percentageAttributes" max="10" min="1" value="10">
                </label>
            </div>
        </div>
        <div class="zone">
            <div class="row medium-centered " >
                <h3>Generate the configuration file</h3>
                <p>If in doubt , you can use the "reset" button to return to the default configuration.</p>
                <input  type="submit" value="Send" class="hollow button">
                <input value="true" name="config" hidden>
                <input value="${fileId}" name="fileId" hidden>
                <input value="${fileName}" name="fileName" hidden>
                <input type="reset" name="reset" value="Reset" class="hollow warning button">
            </div>
        </div>
    </form>
        
    <%@include file="foot.jsp" %>
    <%@include file="foundation.jsp" %>
    </body>
</html>
