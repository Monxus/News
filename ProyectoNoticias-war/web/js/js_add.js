tinymce.init({selector: 'textarea'});
//
////$(document).ready(function(){
//    $("#btn_enviar").click(function () {
//        sendNew();
//    });
//});

function sendNew(){
    var titulo = $("#titulo_news").val();
    var news = tinymce.get('area_news').getContent();
    alert(titulo);
    alert(news);
    return false;
};

