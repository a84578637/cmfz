<%@page pageEncoding="UTF-8" %>


<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="emain6" style="width: 600px;height:400px;"></div>

<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('emain6'));

   var option = {
       title: {
           text: '最近两周入职分析',
           subtext: '入职分析'
       },
       tooltip: {
           trigger: 'axis'
       },
       legend: {
           data:['前两周人数','前一周人数']
       },
       toolbox: {
           show: true,
           feature: {
               dataZoom: {
                   yAxisIndex: 'none'
               },
               dataView: {readOnly: false},
               magicType: {type: ['line', 'bar']},
               restore: {},
               saveAsImage: {}
           }
       },
       xAxis:  {
           type: 'category',
           boundaryGap: false,
           data: ['周一','周二','周三','周四','周五','周六','周日']
       },
       yAxis: {
           type: 'value',
           axisLabel: {
               formatter: '{value} 人'
           }
       },
       series: [
           {
               name:'前两周人数',
               type:'line',
               data:[11, 11, 15, 13, 12, 13, 10],
               markPoint: {
                   data: [
                       {type: 'max', name: '最大值'},
                       {type: 'min', name: '最小值'}
                   ]
               },
               markLine: {
                   data: [
                       {type: 'average', name: '平均值'}
                   ]
               }
           },
           {
               name:'前一周人数',
               type:'line',
               data:[11, 13, 12, 16, 13, 9, 7],
               markPoint: {
                   data: [
                       {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
                   ]
               },
               markLine: {
                   data: [
                       {type: 'average', name: '平均值'},
                       [{
                           symbol: 'none',
                           x: '90%',
                           yAxis: 'max'
                       }, {
                           symbol: 'circle',
                           label: {
                               normal: {
                                   position: 'start',
                                   formatter: '最大值'
                               }
                           },
                           type: 'max',
                           name: '最高点'
                       }]
                   ]
               }
           }
       ]
   };


    myChart.setOption(option);

</script>
