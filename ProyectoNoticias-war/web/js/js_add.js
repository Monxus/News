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

        var fd = new FormData($("#myform")[0]);
        var emess = "Error desconocido";

        fd.append("noticia", tinymce.get('area_news').getContent());

        $.ajax({type: "POST",
            enctype: 'multipart/form-data',
            url: "../AddNoticias",
            data: fd,
            processData: false,
            contentType: false,
            cache: false,
            success: function (d) {
                window.location = d["mess"];
            },
            error: function (e) {
                if (e["responseJSON"] === undefined)
                    alert(emess);
                else
                    alert(e["responseJSON"]["error"]);
                $("#btn_enviar").show();
                $(".loader").hide();
            }
        });

        return false;
    }
}
