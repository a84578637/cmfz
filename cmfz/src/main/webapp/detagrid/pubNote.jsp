<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">
    $(function () {



        var bannerTool2=[{
            text: "查看详情",
            iconCls: 'icon-edit',
            handler: function () {
                //获取选中行
                var row = $("#pubNoteDG").datagrid("getSelected");
                if (row != null) {
                    //编辑指定行

                    $("#showNote").dialog({
                        title:"查看公告 ",
                        width:500,
                        height:500,
                        closed:true,
                        cache:false,
                        href:"${pageContext.request.contextPath}/detagrid/showNote.jsp?id="+row.id,
                        modal:true
                    });

                    $("#showNote").dialog("open");
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
        }]


        $('#pubNoteDG').datagrid({
            url:'${pageContext.request.contextPath}/note/queryPubNote',
            columns:[[
                {field:'imgPath',title:'公告图',width:100,formatter: function(value,row,index){

                            return '<table><tr>' +
                                '<td rowspan=2 style="border:0"><img src="http://192.168.194.134/' + value + '" style="height:50px;"></td>' +
                                '</tr></table>';

                    }
                },
                {field:'title',title:'公告标题',width:100},
                {field:'content',title:'公告内容',width:100},
                {field:'releaseTime',title:'发布时间',width:100},
                {field:'userId',title:'发布者',width:100}


            ]],
            //填充单行
            fitColumns:true,
            //填充页面
            fit:true,
            pagination:true,
            pageList:[1,3,5,10],
            pageSize:3,
            toolbar:bannerTool2
        });
    });
</script>

<table id="pubNoteDG"></table>
<div id="showNote"></div>


