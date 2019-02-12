<%@page pageEncoding="UTF-8" %>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jsapi.js"></script>
<script type="text/javascript" src="js/format+zh_CN,default,corechart.I.js"></script>
<script type="text/javascript" src="js/jquery.gvChart-1.0.1.min.js"></script>
<script type="text/javascript" src="js/jquery.ba-resize.min.js"></script>
<script type="text/javascript">
    gvChartInit();
    jQuery(document).ready(function(){

        jQuery('#myTable5').gvChart({
            chartType: 'PieChart',
            gvSettings: {
                vAxis: {title: 'No of players'},
                hAxis: {title: 'Month'},
                width: 650,
                height: 250
            }
        });
    });
</script>




<div class="mainbox">

    <div class="mainleft">


        <div class="leftinfo">
            <div class="listtitle"><a href="#" class="more1">更多</a>数据统计</div>


            <div class="welinfo">
                <span><img src="images/sun.png" alt="天气" /></span>
                <b>早上好${sessionScope.username}，欢迎使用信息管理系统</b>

            </div>

            <div class="welinfo">
                <span><img src="images/time.png" alt="时间" /></span>
                <i>您上次登录的时间：${sessionScope.userlogintime} </i>
            </div>

        </div>
        <!--leftinfo end-->


        <div class="leftinfos">


            <div class="infoleft">

                <div class="listtitle"><a href="#" class="more1">更多</a>待办事项</div>
                <ul class="newlist">
                    <li><a href="#">上海自贸区今日正式挂牌成立</a><b>10-09</b></li>
                    <li><a href="#">习近平将访东南亚两国 首次出席APEC峰会</a><b>10-08</b></li>
                    <li><a href="#">最高法:谎称炸弹致航班备降者从重追刑责</a><b>10-09</b></li>
                    <li><a href="#">华北大部遭遇雾霾天 北京全城陷重污染</a><b>10-06</b></li>
                    <li><a href="#">"环保专家"董良杰涉嫌寻衅滋事被刑拘</a><b>10-05</b></li>
                    <li><a href="#">中央巡视组：重庆对一把手监督不到位</a><b>10-04</b></li>
                    <li><a href="#">囧!悍马没改好成华丽马车(图)</a><b>10-03</b></li>
                </ul>

            </div>


            <div class="inforight">
                <div class="listtitle"><a href="#" class="more1">添加</a>常用工具</div>

                <ul class="tooli">
                    <li><span><img src="images/d01.png" /></span><p><a href="#">信息资料</a></p></li>
                    <li><span><img src="images/d02.png" /></span><p><a href="#">编辑</a></p></li>
                    <li><span><img src="images/d03.png" /></span><p><a href="#">记事本</a></p></li>
                    <li><span><img src="images/d04.png" /></span><p><a href="#">任务日历</a></p></li>
                    <li><span><img src="images/d05.png" /></span><p><a href="#">图片管理</a></p></li>
                    <li><span><img src="images/d06.png" /></span><p><a href="#">交易</a></p></li>
                    <li><span><img src="images/d07.png" /></span><p><a href="#">档案袋</a></p></li>
                </ul>

            </div>


        </div>


    </div>
    <!--mainleft end-->


    <div class="mainright">


        <div class="dflist">
            <div class="listtitle"><a href="#" class="more1">更多</a>最新信息</div>
            <ul class="newlist">
                <li><a href="#">上海自贸区今日正式挂牌成立</a></li>
                <li><a href="#">习近平将访东南亚两国 首次出席APEC峰会</a></li>
                <li><a href="#">最高法:谎称炸弹致航班备降者从重追刑责</a></li>
                <li><a href="#">华北大部遭遇雾霾天 北京全城陷重污染</a></li>
                <li><a href="#">"环保专家"董良杰涉嫌寻衅滋事被刑拘</a></li>
                <li><a href="#">中央巡视组：重庆对一把手监督不到位</a></li>
                <li><a href="#">囧!悍马没改好成华丽马车(图)</a></li>
                <li><a href="#">澳门黄金周加派稽查人员监察出租车违规行为</a></li>
                <li><a href="#">香港环境局长吁民众支持政府扩建堆填区</a></li>
            </ul>
        </div>


        <div class="dflist1">
            <div class="listtitle"><a href="#" class="more1">更多</a>信息统计</div>
            <ul class="newlist">
                <li><i>会员数：</i>2535462</li>
                <li><i>文档数：</i>5546</li>
                <li><i>普通文章：</i>2315</li>
                <li><i>软件：</i>1585</li>
                <li><i>评论数：</i>5342</li>
            </ul>
        </div>





    </div>
    <!--mainright end-->


</div>




<script type="text/javascript">
    setWidth();
    $(window).resize(function(){
        setWidth();
    });
    function setWidth(){
        var width = ($('.leftinfos').width()-12)/2;
        $('.infoleft,.inforight').width(width);
    }
</script>
