<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">


    $(function () {

        $("#addEmp").dialog({
            title:"添加员工 ",
            width:500,
            height:500,
            closed:true,
            cache:false,
            href:"${pageContext.request.contextPath}/detagrid/AddEmp.jsp",
            modal:true
        });

        var toolbar = [{
            text: "添加员工",
            iconCls: 'icon-edit',
            handler: function () {
                //获取选中行
                $("#addEmp").dialog("open");


            }
        }, '-', {
            text: "移除员工",
            iconCls: 'icon-remove',
            handler: function () {

                var row = $("#album").treegrid("getSelected");
                if (row != null) {
                    //获取当前行ID
                    var albumid = row.id;
                    $.post("${pageContext.request.contextPath}/emp/del",{"id":albumid},function (result) {
                        if(result=="success"){
                            $.messager.show({
                                title:'删除信息',
                                msg:'删除成功',
                                timeout:5000,
                                showType:'slide'
                            });
                            $("#album").edatagrid("reload");
                        }else{
                            $.messager.show({
                                title:'删除信息',
                                msg:'删除异常',
                                timeout:5000,
                                showType:'slide'
                            });

                        }
                    })
                } else {
                    alert("请选择员工")
                }
            }
        }, '-', {
            text: "导出",
            iconCls: 'icon-save',
            handler: function () {
                window.location.href="${pageContext.request.contextPath}/emp/poi";
            }
        }]


        $('#album').edatagrid({
            method: "get",
            url: '${pageContext.request.contextPath}/emp/getUnused',
            columns: [[
                {field: 'name', title: '姓名', width: 1},
                {field: 'age', title: '年龄', width: 1},
                {field: 'sex', title: '性别', width: 1},
                {field: 'style', title: '工作性质', width: 1},
                {field: 'ncity', title: '工作城市', width: 1},
                {field: 'nwork', title: '需求工作', width: 1}

            ]],
            fit: true,
            fitColumns: true,
            pagination: true,
            pageList: [1, 3, 5, 10],
            pageSize: 3,
            toolbar: toolbar,
            view:detailview,
            detailFormatter:function (rowIndex, rowData) {
                return '<table><tr>' +

                    '<td style="border:0">'+
                    '<p>手机: ' + rowData.phone + '</p>' +
                    '<p>工作时间: ' + rowData.worktime + '</p>' +
                    '<p>期待工资: ' + rowData.nsalary + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });
    })


</script>

<table id="album"></table>

<div id="addEmp"></div>
