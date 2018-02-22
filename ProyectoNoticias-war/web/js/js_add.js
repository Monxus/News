tinymce.init({selector: 'textarea'});

var send = false;

$(document).ready(function () {
    $(".loader").hide();
});

function sendNew() {
    if (!send) {
        send = true;
        $(".loader").show();
        $("#btn_enviar").hide();

        var fd = new FormData($(this)[0]); 
        var emess = "Error desconocido";

        fd.append("noticia", tinymce.get('area_news').getContent());
        fd.append("titulo",$("#titulo_news").val());
        
        $.ajax({url: "../AddNoticias", type: 'POST', data: fd,
            success: function (d) {
                window.location=d["mess"];
            },
            error: function (e) {
                if (e["responseJSON"] === undefined)
                    alert(emess);
                else
                    alert(e["responseJSON"]["error"]);
                $("#btn_enviar").show();
            }, cache: false, contentType: false, processData: false
        });

//        var url = "../AddNoticias";
//    var emess = "Error desconocido";
//    $.ajax({
//        method: "POST",
//        url: url,
//        data: {form: fd},
//        success: function (jsn) {
////            alert("Noticia guardada correctamente");
////            $("#btn_enviar").prop("disabled",false);
//alert("GG");
//        },
//        error: function (e) {
////            if (e["responseJSON"] === undefined)
////                alert(emess);
////            else
////                alert(e["responseJSON"]["error"]);
////            $("#btn_enviar").prop("disabled",false);
//alert("FAIL");
//        }
//    });
    }
//    $("#btn_enviar").prop("disabled", true);
//    var url = "../AddNoticias";
//    var emess = "Error desconocido";
//    var titulo = $("#titulo_news").val();
//    var noticia = tinymce.get('area_news').getContent();
//    var fd = new FormData($(this)[0]);
//    fd.append("noticia", noticia);
//    console.log(fd);
//    $.ajax({
//        method: "POST",
//        url: url,
//        data: {form: fd},
//        success: function (jsn) {
////            alert("Noticia guardada correctamente");
////            $("#btn_enviar").prop("disabled",false);
//        },
//        error: function (e) {
////            if (e["responseJSON"] === undefined)
////                alert(emess);
////            else
////                alert(e["responseJSON"]["error"]);
////            $("#btn_enviar").prop("disabled",false);
//        }
//    });
    return false;
}
;

//$("#myform").submit(function () {
//
//console.log("HI");
//    var send = true;
//
//    if (send) {
//        lding(); //si hay que enviar...
//
//        $("#btn_enviar").prop("disabled", true);
//
//        var fd = new FormData($(this)[0]); //cargar el formulario como parámetros
//
//        fd.append("noticia", tinymce.get('area_news').getContent());
//        
//        console.log(fd);
//
////        $.ajax({url: "../AddNoticias", type: 'POST', data: fd,
////            success: function (d) {
////                hldng();
////                window.location = d["redir"];
////            },
////            error: function (e) {
////                hldng();
////                if (e["responseJSON"] === undefined)
////                    modal(emess, null);
////                else
////                    modal(e["responseJSON"]["error"], null);
////            }, cache: false, contentType: false, processData: false
////        });
//    }
//    return false;
//});
