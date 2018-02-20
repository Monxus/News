tinymce.init({selector: 'textarea'});
//
////$(document).ready(function(){
//    $("#btn_enviar").click(function () {
//        sendNew();
//    });
//});

function sendNew(){
    var url = "../AddNoticias";
    var emess = "Error desconocido";
    var titulo = $("#titulo_news").val();
    var noticia = tinymce.get('area_news').getContent();
    $.ajax({
        method: "POST",
        url: url,
        data: {title: titulo,news:noticia},
        success: function (jsn) {        
            alert("Noticia guardada correctamente");
        },
        error: function (e) {
            if (e["responseJSON"] === undefined)
                alert(emess);
            else
                alert(e["responseJSON"]["error"]);
        }
    });
    return false;
};

