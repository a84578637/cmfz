<%@page pageEncoding="UTF-8" %>


<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="emain2" style="width: 600px;height:400px;"></div>

<script type="text/javascript">
    var myChart2 = echarts.init(document.getElementById('emain2'));


  var   option2 = {
        title : {
            text: 'iphone销量',
            subtext: '纯属虚构',
            left: 'center'
        },
        tooltip : {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data:['用户数量']
        },
        visualMap: {
            min: 0,
            max: 2500,
            left: 'left',
            top: 'bottom',
            text:['高','低'],           // 文本，默认为数值文本
            calculable : true
        },
        toolbox: {
            show: true,
            orient : 'vertical',
            left: 'right',
            top: 'center',
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        }
    };

    myChart2.setOption(option2);
    $.get("${pageContext.request.contextPath}/lineJson3.json",function (data) {
        myChart2.setOption({
            series : [
                {
                    name: '用户数量',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data:data.data
                }

            ]})


    },"JSON")
</script>
