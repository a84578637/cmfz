<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">
$(function () {
    var paramid =${param.id} ;
    console.log(paramid);
    $("#album1").textbox({
        readonly:true
    });
    $("#album2").textbox({
        readonly:true
    });
    $("#album3").textbox({
        readonly:true
    });
    $("#album4").textbox({
        readonly:true
    });
    $("#album5").textbox({
        readonly:true
    });
    $("#album6").textbox({
        readonly:true
    });
    $("#album7").textbox({
        readonly:true
    });




    $('#albumForm').form("load","${pageContext.request.contextPath}/album/OneAlbum?id="+paramid);

    setTimeout(function () {
        var coverimgsrc = "${pageContext.request.contextPath}/img/"+$("#album8").val();
        $("#albumPic").attr("src",coverimgsrc);
    },100);


})
</script>
<div align="center">
<h3>专辑详情</h3>
    <form id="albumForm" method="post">
    专辑名：<input style="width:200px" name="title" id="album1"/></br>
    作者名：<input style="width:200px" name="author" id="album2"/></br>
    播音师：<input style="width:200px" name="broadcast" id="album3"/></br>
    总分数：<input style="width:200px" name="score" id="album4"/></br>
    总集数：<input style="width:200px" name="count" id="album5"/></br>
    简  介：<input style="width:200px" name="brief" id="album6"/></br>
    上传日：<input style="width:200px" name="pubDate" id="album7"/></br>
        <input style="width:200px" name="coverImg" id="album8" hidden="hidden"/></br>
        <img id="albumPic" src="#" width="300" HEIGHT="200"/>
    </form>

</div>