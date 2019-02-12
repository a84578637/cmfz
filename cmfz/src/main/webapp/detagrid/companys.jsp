<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">


    $(function () {



        $('#companyD').edatagrid({
            method: "get",
            url: '${pageContext.request.contextPath}/comp/getComp',
            columns: [[
                {field: 'contact', title: '企业名', width: 1},
                {field: 'city', title: '工作地区', width: 1},
                {field: 'nsex', title: '需求性别', width: 1},
                {field: 'work', title: '工作种类', width: 1},
                {field: 'salary', title: '工资', width: 1},
                {field: 'worktime', title: '工时', width: 1},
                {field: 'style', title: '所需类型', width: 1},
                {field: 'person', title: '以招人数', width: 1},
                {field: 'nperson', title: '所需人数', width: 1}
            ]],
            fit: true,
            fitColumns: true,
            pagination: true,
            pageList: [1, 3, 5, 10],
            pageSize: 3,

            view:detailview,
            detailFormatter:function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td style="border:0">'+
                    '<p>联系方式: ' + rowData.phone + '</p>' +
                    '<p>奖 金: ' + rowData.reward + '</p>' +
                    '<p>公司地址: ' + rowData.address + '</p>' +
                    '<p>招聘简章: ' + rowData.motoley + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });
    })


</script>

<table id="companyD"></table>

