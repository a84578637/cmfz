<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">
    $(function () {
        var bannerTool=[{
            iconCls: 'icon-add',
            text: "添加",
            handler: function () {
                alert('编辑按钮')
            }
        }, '-', {
            text: "修改",
            iconCls: 'icon-edit',
            handler: function () {
                //获取选中行
                var row = $("#dg").edatagrid("getSelected");
                if (row != null) {
                    //编辑指定行
                    var index = $("#dg").edatagrid("getRowIndex", row);
                    $("#dg").edatagrid("editRow", index);
                } else {
                    alert("请先选中行")
                }


            }
        }, '-', {
            text: "删除",
            iconCls: 'icon-remove',
            handler: function () {
                alert('帮助按钮')
            }
        }, '-', {
            text: "保存",
            iconCls: 'icon-save',
            handler: function () {
                $("#dg").edatagrid("saveRow")

            }
        }];


        $("#bannerDg").edatagrid({
            url:'${pageContext.request.contextPath}/#',
            updateUrl:"${pageContext.request.contextPath}/#",
            columns:[[
                {field:'title',title:'名称',width:100},
                {field:'status',title:'状态',width:100},
                {field:'uploadDate',title:'上传时间',width:100}
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
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/img/' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>描述: ' + rowData.desc + '</p>' +
                    '<p>日期: ' + rowData.uploadDate + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        })
    })


</script>

<table id="bannerDg"></table>



