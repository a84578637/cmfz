<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">


    $(function () {



        var toolbarWork = [{
            text: "就业",
            iconCls: 'icon-edit',
            handler: function () {
                //获取选中行
                var row = $("#myalbum").treegrid("getSelected");
                if (row != null) {
                    //获取当前行ID
                    var rowphone = row.phone;
                    $("#addWork").dialog({
                        title:"就业",
                        width:500,
                        height:500,
                        closed:true,
                        cache:false,
                        href:"${pageContext.request.contextPath}/detagrid/AddWork.jsp?phone="+rowphone,
                        modal:true
                    });


                    $("#addWork").dialog("open");
                } else {
                    alert("请选择员工")
                }


            }
        }]


        $('#myalbum').edatagrid({
            method: "get",
            url: '${pageContext.request.contextPath}/emp/getMyEmp',
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
            toolbar: toolbarWork,
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

<table id="myalbum"></table>
<div id="addWork"></div>

