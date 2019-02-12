<%@page pageEncoding="UTF-8" %>


<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="emain" style="width: 600px;height:400px;"></div>

<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('emain'));

   var option = {
       title:{
         text:'近十周用户就业数量'
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
    var goEasy = new GoEasy({
        appkey: "BC-5d656a5ce51b45779a80fbe8903f8c4c"
    });
    goEasy.subscribe({
        channel: "user140",
        onMessage: function (message) {
           // alert("Channel:" + message.channel + " content:" + message.content);
            var m = eval(message);
            console.log(m.content);
            myChart.setOption({
                series: [{
                    data: m.content,
                    type: 'bar'
                }]
            })
        }
    });
</script>
