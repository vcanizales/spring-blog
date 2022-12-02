$(document).ready(function(){
    $(".editButton").on('click',function (){
        window.location.replace(`/posts/${$(this).attr("data-id")}/edit`)
    });
});