
<html>
<head>

</head>
<body>
<%@ include file="menu.jsp" %>
<form action="<c:url value="/Upload" />" method="post" enctype="multipart/form-data">
             <div class="col-sm-9">
    <div class="panel panel-default">
             <div class="panel-body form-horizontal payment-form">
           
            <div class="form-group">
                        <label for="description" class="col-sm-3 control-label">Description du fichier <span class="requis">*</span></label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="description" name="description" value="<c:out value="${fichier.description}"/>">
                             <span class="erreur">${form.erreurs['description']}</span>
                        </div>
               </div>
                
                 <div class="form-group">
                        <label for="fichier" class="col-sm-3 control-label">Emplacement du fichier <span class="requis">*</span></label>
                        <div class="col-sm-5">
                            <input type="file" class="form-control" id="fichier" name="fichier" value="<c:out value="${fichier.nom}"/>">
                            <span class="erreur">${form.erreurs['fichier']}</span>
                        </div>
               </div>
    <div class="form-group">
                        <div class="col-sm-12 text-right">
                            <button type="submit" class="btn btn-default preview-add-button">
                                Envoyer
                            </button>
                             
                        </div>
                    </div>
                          
            
    </div>   
    </div>
    </div>     
            
        </form>
</body>
</html>