<%@page pageEncoding="UTF-8" %>


<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="emain" style="width: 600px;height:400px;"></div>

<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('emain'));

   var option = {
       title:{
         text:'近十周用户注册数量'
       },
       tooltip:{},
        xAxis: {
            type: 'category',
            data: ['第一周', '第二周', '第三周', '第四周', '第五周', '第六周', '第七周', '第八周', '第九周', '第十周']
        },
        yAxis: {
            type: 'value'
        }
    };

    myChart.setOption(option);
    $.get("${pageContext.request.contextPath}/lineJson2.json",function (data) {
    myChart.setOption({
        series: [{
            data: data.data,
            type: 'bar'
        }]
    })
    },"JSON")

</script>
