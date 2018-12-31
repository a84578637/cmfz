<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">
$(function () {
    var paramid =${param.id} ;
    $("#addChapterAlbumId").attr("value",paramid );


    $("#addChapterTitle").textbox({
        required:true
    });
   $("#addChapterSubmit").linkbutton({
       text:"添加",
       onClick:function () {

           $("#addChapterForm").form('submit',{
               url:"${pageContext.request.contextPath}/chapter/regist",
               onSubmit: function(){
                   return $("#addChapterForm").form("validate");
               },

               success:function(data){

                   $.messager.show({
                       title:"添加",
                       msg:"添加成功",
                       showType:'show',
                       style:{
                           right:'',
                           top:document.body.scrollTop+document.documentElement.scrollTop,
                           bottom:''
                       }
                   });

                   setTimeout(function () {

                       $("#album").treegrid("reload");
                       $("#addChapter").dialog("close");

                   },500)
               }

           });
       }
   })
})
</script>
<div align="center">
<h3>添加音频</h3>


<form id="addChapterForm" method="POST"  enctype="multipart/form-data">
    <input type="file" name="uploadFile" id="uploadFile3"/><br />
    章节名：<input style="width:200px" name="title" id="addChapterTitle" />
    <input name="albumId" id="addChapterAlbumId" readonly="true" hidden="hidden"><br/>
    <input id="addChapterSubmit" value="添加" >
</form>
</div>
