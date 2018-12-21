<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">
    $(function () {
        $("#addAlbum").dialog({
            title:"添加轮播图 ",
            width:500,
            height:500,
            closed:true,
            cache:false,
            href:"${pageContext.request.contextPath}/datagrid/AddAlbum.jsp",
            modal:true
        });

        var toolbar = [{
            iconCls: 'icon-add',
            text: "专辑详情",
            handler: function () {
                var row = $("#album").treegrid("getSelected");


                if (row != null) {
                    //获取当前行ID
                    var albumid = row.id;
                    if(row.author!=null){
                        $("#AlbumMsg").dialog({
                            title:"专辑详情 ",
                            width:500,
                            height:500,
                            closed:true,
                            cache:false,
                            queryParams:{id:albumid},
                            href:"${pageContext.request.contextPath}/datagrid/AlbumMsg.jsp",
                            modal:true
                        });
                        $("#AlbumMsg").dialog("open");
                    }else{
                        alert("请选择专辑");
                    }




                } else {
                    alert("请先选中行")
                }



            }
        }, '-', {
            text: "添加专辑",
            iconCls: 'icon-edit',
            handler: function () {
                //获取选中行
                $("#addAlbum").dialog("open");



            }
        }, '-', {
            text: "添加音频",
            iconCls: 'icon-remove',
            handler: function () {
                alert('帮助按钮')
            }
        }, '-', {
            text: "音频下载",
            iconCls: 'icon-save',
            handler: function () {
                $("#dg").edatagrid("saveRow")

            }
        }]





        $('#album').treegrid({
            method:"get",
            url:'${pageContext.request.contextPath}/album/queryAll',
            idField:'id',
            treeField:'title',
            columns:[[
                {field:'title',title:'名字',width:60},
                {field:'duration',title:'时长',width:80},
                {field:'size',title:'大小',width:80},
                {field:'uploadDate',title:'更新时间',width:80}

            ]],
            fit:true,
            fitColumns:true,
            pagination:true,
            pageList:[1,3,5,10],
            pageSize:3,
            toolbar:toolbar,
        });
    })

</script>

<table id="album"></table>
<div id="AlbumMsg"></div>
<div id="addAlbum"></div>