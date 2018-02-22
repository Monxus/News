var pagNew = 1;
var scrollable = true;
var puntoCarga = 60;
var moreNews = true;
var cargandoNews=false;

$(document).ready(function () {

    $("#cargar-menu").click(function () {
        if (scrollable) {
            $("#cargar-menu").html("Cargar noticias al pulsar un botón");
            $("#cargarBoton").hide();
            $(window).scroll(function () {
                if (($(window).scrollTop() + $(window).height() > $(document).height() - puntoCarga) && moreNews && !cargandoNews) {
                    cargarNoticias();
                }
            });
            scrollable = false;
        } else {
            $("#cargar-menu").html("Cargar noticias con scroll");
            if (moreNews) {
                $("#cargarBoton").show();
            }
            scrollable = true;
        }
    });
    $("#cargarBoton").click(function () {
        cargarNoticias();
    });
    $("#bottwit").click(function () {
        compartirTwit();
    });
    $("#botface").click(function () {
        compartirFace();
    });

    $(".loader").hide();
    $(".myAlert-bottom").hide();
});

function cargarNoticias() {
    cargandoNews = true;
    var url = "Noticias";
    var emess = "Error desconocido";
    $(".loader").show();
    $("#cargarBoton").hide();
    $.ajax({
        method: "POST",
        url: url,
        data: {page: pagNew},
        success: function (jsn) {
            if (jsn["mess"] === "No hay más noticias para cargar") {
                myAlertBottom("No hay más noticias para cargar");
                moreNews = false;
            } else {
                crearNoticias(jsn);
                pagNew++;
                myAlertBottom("Noticias cargadas");
                if (jsn.length < 3) {
                    moreNews = false;
                    myAlertBottom("Están son las últimas noticias almacenadas. No hay más noticias.");
                }
            }
            $(".loader").hide();
            if (moreNews) {
                $("#cargarBoton").show();
            }
            cargandoNews=false;
        },
        error: function (e) {
            if (e["responseJSON"] === undefined)
                alert(emess);
            else
                alert(e["responseJSON"]["error"]);
            $(".loader").hide();
            if (moreNews) {
                $("#cargarBoton").show();
            }
            cargandoNews=false;
        }
    });



}

function crearNoticias(json) {
    $.each(json, function (i, noticia) {
        var imgAux;
        if (typeof noticia.img === 'undefined') {
            imgAux = "img/newDefault.jpg";
        } else {
            imgAux = "img/uploadImg/" +noticia.img+".png";
        }
        $("#noticias").append("<div class='well' id='" + noticia.id + "'><a href='#'><h2>" + noticia.title + "</h2></a>" +
                "<p class='text-muted'>Publicada " + noticia.time + " por " + noticia.creator + "</p><div class='row'>" +
                "<div class='col-sm-3'><img class='img-rounded img-responsive' src='" + imgAux + "' alt='noticia'></div>" +
                "<div class='col-sm-9'><p class='text-justify'>" + noticia.description + "</p></div></div></div>"
                );
    });

}
function compartirFace() {
    var linkMal = window.location.href;
    var link = encodeURIComponent(linkMal);
    window.open("https://www.facebook.com/sharer/sharer.php?u=" + link);
}

function compartirTwit() {
    var linkMal = window.location.href;
    var link = encodeURIComponent(linkMal);
    window.open("https://twitter.com/home?status=" + $('title').text() + " - " + link);
}

function myAlertBottom(txt) {
    $(".myAlert-bottom").show();
    $("#myAlertText").text(txt);
    setTimeout(function () {
        $(".myAlert-bottom").fadeOut();
    }, 1000);
}