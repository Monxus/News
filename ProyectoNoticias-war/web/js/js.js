var pagNew = 1;
var scrollable = true;
var puntoCarga = 40;
var maxPag = 3;
var moreNews = true;

$(document).ready(function () {

    $("#cargar-menu").click(function () {
        if (scrollable) {
            $("#cargar-menu").html("Cargar noticias al pulsar un botón");
            $("#cargarBoton").hide();
            $(window).scroll(function () {
                if ($(window).scrollTop() + $(window).height() > $(document).height() - puntoCarga && moreNews) {
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
});
function cargarNoticias() {
    var url = "Noticias";
    var emess = "Error desconocido";
    $.ajax({
        method: "POST",
        url: url,
        data: {page: pagNew},
        success: function (jsn) {
            crearNoticias(jsn);
            pagNew++;
            if (jsn.length < 3) {
                $("#cargarBoton").hide();
                moreNews = false;
            }
        },
        error: function (e) {
            if (e["responseJSON"] === undefined)
                alert(emess);
            else
                alert(e["responseJSON"]["error"]);
        }
    });
}

function crearNoticias(json) {
    $.each(json, function (i, noticia) {
        console.log(noticia);
        console.log(noticia.img);
        var imgAux;
        if (typeof noticia.img === 'undefined') {
            imgAux = "img/newDefault.jpg";
        } else {
            imgAux = noticia.img;
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
