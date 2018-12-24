<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">


    $(function () {


        function albumform(value, row, index) {
            if (row.author == null) {
                return "<audio controls='controls' src='${pageContext.request.contextPath}" + row.url + "'    >音频</audio>";
            }

        }


        $("#addAlbum").dialog({
            title: "添加轮播图 ",
            width: 500,
            height: 500,
            closed: true,
            cache: false,
            href: "${pageContext.request.contextPath}/datagrid/AddAlbum.jsp",
            modal: true
        });


        var toolbar = [{
            iconCls: 'icon-add',
            text: "专辑详情",
            handler: function () {
                var row = $("#album").treegrid("getSelected");
                if (row != null) {
                    //获取当前行ID
                    var albumid = row.id;
                    if (row.author != null) {
                        $("#AlbumMsg").dialog({
                            title: "专辑详情 ",
                            width: 500,
                            height: 500,
                            closed: true,
                            cache: false,
                            queryParams: {id: albumid},
                            href: "${pageContext.request.contextPath}/datagrid/AlbumMsg.jsp",
                            modal: true

                        });
                        $("#AlbumMsg").dialog("open");
                    } else {
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

                var row = $("#album").treegrid("getSelected");
                if (row != null) {
                    //获取当前行ID
                    var albumid = row.id;
                    if (row.author != null) {
                        $("#addChapter").dialog({
                            title: "添加文章 ",
                            width: 500,
                            height: 500,
                            closed: true,
                            cache: false,
                            queryParams: {id: albumid},
                            href: "${pageContext.request.contextPath}/datagrid/AddChapter.jsp",
                            modal: true
                        });

                        $("#addChapter").dialog("open");
                    } else {
                        alert("请选择专辑");
                    }
                } else {
                    alert("请先选中行")
                }
            }
        }, '-', {
            text: "音频下载",
            iconCls: 'icon-save',
            handler: function () {
                var row = $("#album").treegrid("getSelected");
                if (row != null) {
                    //获取当前行ID
                    var albumid = row.id;
                    if (row.author == null) {


                        window.location.href="${pageContext.request.contextPath}/chapter/download?id="+albumid;


                    } else {
                        alert("请选择章节0");
                    }
                } else {
                    alert("请先选中行")
                }
            }
        }, '-', {
            text: "导出",
            iconCls: 'icon-save',
            handler: function () {
                window.location.href="${pageContext.request.contextPath}/album/poi";
            }
        }]


        $('#album').treegrid({
            method: "get",
            url: '${pageContext.request.contextPath}/album/queryAll',
            idField: 'id',
            treeField: 'title',
            columns: [[
                {field: 'title', title: '名字', width: 1},
                {field: 'duration', title: '时长', width: 1},
                {field: 'size', title: '大小', width: 1},
                {field: 'url', title: '在线播放', width: 3, formatter: albumform},
                {field: 'uploadDate', title: '更新时间', width: 1}

            ]],
            fit: true,
            fitColumns: true,
            pagination: true,
            pageList: [1, 3, 5, 10],
            pageSize: 3,
            toolbar: toolbar,
            onLoadSuccess: function () {
                $("#album").treegrid("collapseAll");
            },
            onDblClickRow: function (row) {
                $("#album").treegrid("toggle", row.id);

            }
        });
    })


</script>

<table id="album"></table>
<div id="AlbumMsg"></div>
<div id="addAlbum"></div>
<div id="addChapter"></div>