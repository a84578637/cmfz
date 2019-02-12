<%@page pageEncoding="UTF-8" %>


<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="emain2" style="width: 600px;height:400px;"></div>

<script type="text/javascript">
    var myChart2 = echarts.init(document.getElementById('emain2'));


  var   option2 = {
        title : {
            text: '用户注册数量',
            subtext: '纯属虚构',
            left: 'center'
        },
        tooltip : {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data:['用户数量','男性','女性']
        },
        visualMap: {
            min: 0,
            max: 1000,
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
    $.get("${pageContext.request.contextPath}/emp/empProvinces",function (data) {
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
                    data:data.data,
                    itemStyle: {
                        normal:{label:{show:true}},
                        emphasis:{label:{show:true}}
                    }
                },{
                    name: '女性',
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
                    data:data.woman
                },{
                    name: '男性',
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
                    data:data.man
                }

            ]})


    },"JSON");


    var goEasy = new GoEasy({
        appkey: "BC-5d656a5ce51b45779a80fbe8903f8c4c"
    });
    goEasy.subscribe({
        channel: "userProvince",
        onMessage: function (message) {
            // alert("Channel:" + message.channel + " content:" + message.content);
            var m = eval("("+message.content+")");

            console.log(m);
            console.log(m.data);
            console.log(m.man);

            console.log(m.woman);


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
                        data:m.data,
                        itemStyle: {
                            normal:{label:{show:true}},
                            emphasis:{label:{show:true}}
                        }
                    },{
                        name: '女性',
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
                        data:m.woman
                    },{
                        name: '男性',
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
                        data:m.man
                    }

                ]})
        }
    });
</script>


