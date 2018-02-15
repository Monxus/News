<%-- 
    Document   : news
    Created on : 09-feb-2018, 20:53:47
    Author     : Ramon
--%>

<%@page import="org.proyectonoticias.entities.News"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<News> news = (List<News>) request.getAttribute("list_news");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Natura News</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel='stylesheet' type="text/css" href='css/custom.css'>  
        <link href="https://fonts.googleapis.com/css?family=Allerta" rel="stylesheet">
        <link rel="alternate" href="rss/rss.xml" type="application/rss+xml" title="RSS de Natura News"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="js/js.js"></script>
        <meta name="description" content="Natura News - Tu principal portal de noticias sobre Naturaleza y Medio Ambiente">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta property="og:title" content="Natura News"/>
        <meta property="og:url" content="https://rawgit.com/Monxus/PaginaNoticias/master/news.html"/>
        <meta property="og:image" content="https://rawgit.com/Monxus/PaginaNoticias/master/img/fondo.jpg"/>
        <meta property="og:site_name" content="Natura News"/>
        <meta property="og:description" content="Natura News - Tu principal portal de noticias sobre Naturaleza y Medio Ambiente"/>
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
                    <a class="navbar-brand" href="news.html" id="logo-nav">Natura News <span class="glyphicon glyphicon-leaf"></span></a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="news.html">Inicio</a></li>
                        <li><a id="cargar-menu">Cargar noticias con scroll</a></li>
                        <li><a href="admin/add_news.html">Añadir noticia</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!--Cabecera con el título de la página-->
        <div class="jumbotron container">
            <div class="container">
                <h1>Natura News <span class="glyphicon glyphicon-leaf"></span></h1>      
                <p>Tu principal portal de noticias sobre Naturaleza y Medio Ambiente</p>
            </div>
        </div>

        <!--Cuerpo con las noticias y el anuncio-->
        <div class="container" id="cuerpo">

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

            <div id="noticias">
                <% for (int i = 0; i < news.size(); i++) {%>
                <div class="well" id="<%= news.get(i).getId()%>">
                    <% String imgAux;
                        if (news.get(i).getImg() == null) {
                            imgAux = "img/newDefault.jpg";
                        } else {
                            imgAux = news.get(i).getImg();
                        }%>
                    <a href="#"><h2><%= news.get(i).getTitle()%></h2></a>
                    <p class="text-muted">Publicada <%= news.get(i).getTime()%> por <%= news.get(i).getCreator()%></p>
                    <div class="row">
                        <div class="col-sm-3"><img class="img-rounded img-responsive" src=" <%= imgAux%>" alt="noticia"></div>
                        <div class="col-sm-9"><p class="text-justify"><%= news.get(i).getDescription()%></p></div>
                    </div>
                </div> 
                <%  }%>

            </div>
        </div>

        <!--Botón cargar más noticias-->
        <div class="container" id="cargarBoton">
            <button type="button" class="btn btn-success">Cargar más...</button>
        </div>

        <div id="redes">
            <img class="redss" id="bottwit" src="img/twit.png" alt="twitter" >
            <span><img class="redss" id="botface" src="img/face.png" alt="facebook" ></span>
        </div>

        <!--Pie de la web-->
        <div class="container panel panel-default" id="pie">
            <div class="panel-body"><span class="glyphicon glyphicon-copyright-mark"></span> Ramón Moreno - (2017-2018)
                <a href="rss/rss.xml">
                    <img src = "https://www.w3schools.com/xml/pic_rss.gif" width ="36" height ="14">
                </a>
            </div>
        </div>
    </body>
</html>
