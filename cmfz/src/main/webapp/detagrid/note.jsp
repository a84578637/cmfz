<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">
    $(function () {

        $("#addBanner").dialog({
            title:"添加轮播图 ",
            width:500,
            height:500,
            closed:true,
            cache:false,
            href:"${pageContext.request.contextPath}/detagrid/AddNote.jsp",
            modal:true
        });


        var bannerTool=[{
            iconCls: 'icon-add',
            text: "添加",
            handler: function () {
                $("#addBanner").dialog("open");
            }
        }, '-', {
            text: "修改",
            iconCls: 'icon-edit',
            handler: function () {
                //获取选中行
                var row = $("#bannerDg").edatagrid("getSelected");
                if (row != null) {
                    //编辑指定行
                    var index = $("#bannerDg").edatagrid("getRowIndex", row);
                    $("#bannerDg").edatagrid("editRow", index);
                } else {
                    $.messager.show({
                        title:"消息",
                        msg:"请先选中",
                        showType:'show',
                        style:{
                            right:'',
                            top:document.body.scrollTop+document.documentElement.scrollTop,
                            bottom:''
                        }

                    });
                }
            }
        }, '-', {
            text: "删除",
            iconCls: 'icon-remove',
            handler: function () {
               var row = $("#bannerDg").edatagrid("getSelected");

                if (row != null) {
                    //编辑指定行

                    $.post("${pageContext.request.contextPath}/note/remove",{id:row.id},function(){
                        $.messager.show({
                            title:"消息",
                            msg:"删除成功",
                            showType:'show',
                            style:{
                                right:'',
                                top:document.body.scrollTop+document.documentElement.scrollTop,
                                bottom:''
                            }

                        });
                        $("#bannerDg").edatagrid("reload");
                    });
                } else {
                    $.messager.show({
                        title:"消息",
                        msg:"请先选中行",
                        showType:'show',
                        style:{
                            right:'',
                            top:document.body.scrollTop+document.documentElement.scrollTop,
                            bottom:''
                        }

                    });
                }
            }
        }, '-', {
            text: "保存",
            iconCls: 'icon-save',
            handler: function () {
                $("#bannerDg").edatagrid("saveRow")

            }
        }];


        $("#bannerDg").edatagrid({
            url:'${pageContext.request.contextPath}/note/queryAll',
            updateUrl:"${pageContext.request.contextPath}/note/update",
            columns:[[
                {field:'title',title:'名称',width:100},
                {field:'content',title:'内容',width:100, editor: {
                        type: "text",
                        options: {required:true}
                    }},
                {field:'releaseTime',title:'上传时间',width:100}
            ]],
            //填充单行
            fitColumns:true,
            //填充页面
            fit:true,
            pagination:true,
            pageList:[1,3,5,10],
            pageSize:3,
            toolbar:bannerTool,
            view:detailview,
            detailFormatter:function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="http://192.168.194.134/' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>状态: ' + rowData.status + '</p>' +
                    '<p>日期: ' + rowData.releaseTime + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        })
    })


</script>

<table id="bannerDg"></table>
<div id="addBanner"></div>

