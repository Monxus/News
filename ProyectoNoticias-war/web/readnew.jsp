<%-- 
    Document   : readnew
    Created on : 20-feb-2018, 16:12:09
    Author     : Ramon
--%>

<%@page import="org.proyectonoticias.entities.News"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% News mynew = (News) request.getAttribute("mynew");
%>
<% if(mynew==null){
    response.sendRedirect("./Noticias");
}else{%>
<!DOCTYPE html>
<html>
    <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
    <title><%= mynew.getTitle()%></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel='stylesheet' type="text/css" href='css/custom.css'>
    <link href="https://fonts.googleapis.com/css?family=Allerta" rel="stylesheet">   
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/js.js"></script>
    <meta name="description" content="El almacén de semillas del Ártico para prevenir un desastre global necesita reparaciones tras inundarse parcialmente">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta property="og:title" content=<%= mynew.getTitle()%>/>
   <!-- <meta property="og:url" content="https://rawgit.com/Monxus/PaginaNoticias/master/news/arca-noe-plantas-peligro.html"/>
    <meta property="og:image" content="https://rawgit.com/Monxus/PaginaNoticias/master/img/new1b.jpg"/>-->
    <meta property="og:site_name" content="Natura News"/>
    <meta property="og:description" content="El almacén de semillas del Ártico para prevenir un desastre global necesita reparaciones tras inundarse parcialmente"/>
    
</head>
<body>
    <!--Barra navegación superior fija-->
    <nav class="navbar navbar-default container navbar-fixed-top" >
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>                        
                </button>
                <a class="navbar-brand" href="./Noticias" id="logo-nav">Natura News <span class="glyphicon glyphicon-leaf"></span></a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="./Noticias">Inicio</a></li>
            </ul>
            </div>
        </div>
    </nav>

    <div class="container not-pag">
        <div id="anuncio1">
            <img src="img/anu1.jpg" alt="anuncio">
        </div>
        <div id="anuncio2">
            <picture>
                <source srcset="img/anu2.jpg" media="(max-width: 399px)">
                <source srcset="img/anu2aux.jpg">
                <img src="img/anu2aux.jpg" alt="anuncio">
            </picture>
        </div>

        <div class="noticia-pag">
            <h1><%= mynew.getTitle()%></h1>
            <p class="text-muted">Publicada <%= mynew.getTime()%> por <%= mynew.getCreator()%></p>
            <% String imgAux;
                        if (mynew.getImg() == null) {
                            imgAux = "img/imgAux.jpg";
                        } else {
                            imgAux = "img/uploadImg/"+mynew.getImg()+".png";
                        }%>
            <img class="img-thumbnail img-responsive" src=<%= imgAux%> alt="noticia">
            <div id="redes">
                <img class="redss" id="bottwit" src="img/twit.png" alt="twitter" >
                <span><img class="redss" id="botface" src="img/face.png" alt="facebook" ></span>
            </div>
            <%= mynew.getDescription()%>
        </div>
    </div>


    <!--Pie de la web-->
    <div class="container panel panel-default" id="pie">
        <div class="panel-body"><span class="glyphicon glyphicon-copyright-mark"></span> Ramón Moreno - (2017-2018)</div>
    </div>
</body>
</html>
<% } %>

