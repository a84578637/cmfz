<%@page contentType="text/html;charset=utf-8" isELIgnored="false" %>
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">

    <%@page isELIgnored="false" %>
	<head>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
	<style>


.process {
  width: 800px;
  margin: 3em auto;
  cursor: default;
}

.process-items {
  display: table;
  margin: 0 0 10px;
  padding: 0;
  list-style-type: none;
  color: #222222;
  font-size: 18px;
  text-align: center;
}
.process-items li {
  display: table-cell;
  width: 25%;
  vertical-align: bottom;
  padding: 0 .5em;
  -webkit-transform: scale(0.65) translateY(40px);
          transform: scale(0.65) translateY(40px);
  -webkit-transform-origin: bottom center;
          transform-origin: bottom center;
  transition: -webkit-transform .5s;
  transition: transform .5s;
  transition: transform .5s, -webkit-transform .5s;
}
.process-items li.active {
  -webkit-transform: scale(1) translateY(0);
          transform: scale(1) translateY(0);
}
.process-items em {
  display: block;
  margin-top: .5em;
}
</style>
	
		<title>用户注册</title>
		<link href="../css/login.css" rel="stylesheet" type="text/css" />
		<link href="../css/page_bottom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>

		<script type="text/javascript">
			$(function () {
				$(".finsh").click(function () {
				    var v = $("#validatecode").val();

					$.post("${pageContext.request.contextPath}/user/checkVerify",{"verify":v,"userPhone":${sessionScope.userPhone}}
					,function (result) {
                            if(result=="success"){
                                $("#errorMsg").text("验证码正确！").css("color","green");
                                alert("注册成功");
                                window.location.href="${pageContext.request.contextPath}/user/sregist";
                            }else{
                                $("errorMsg").text("验证码错误").css("color","red");
                            }
                        })
                })
            })
		</script>
	</head>
	<body>


				<!-- ----------!!!!!!!!!! -->
		<div class="process">
    <ol class="process-items">
        <li class="active" >第一步 1
        <em>填&nbsp写&nbsp信&nbsp息&nbsp&nbsp</em></li>
        <li style="color:red">第二步 2
        <em>&nbsp&nbsp&nbsp验&nbsp证&nbsp邮&nbsp箱&nbsp&nbsp</em></li>
        <li>第三步 3
        <em>&nbsp&nbsp&nbsp注&nbsp册&nbsp成&nbsp功&nbsp&nbsp</em></li>
        <li>第四步 4
        <em>&nbsp&nbsp&nbsp继&nbsp续&nbsp登&nbsp录&nbsp&nbsp</em></li>
    </ol>
    <canvas id="canvas" width="800" height="55"></canvas>
</div>

<script>
var process = $('.process');
var canvas = document.getElementById('canvas');
var ctx = canvas.getContext('2d');

var SECTION_WIDTH = 200;

var sections = [];
var create = function(start) {
  var section = {
    start: start,
    width: SECTION_WIDTH,
    height: 45,
    hMax: 35,
    hMod: 0,
    progress: 0,
    dot: {
      x: 0,
      y: 0
    }
  };
  section.end = section.start + section.width;
  section.dot.x = section.start + section.width/2;
  section.dot.y = section.height;
  sections.push(section);
};

var draw = function(s) {
  var wMod = s.width * 0.3;
  ctx.beginPath();
  ctx.moveTo(s.start, s.height);
  ctx.bezierCurveTo(
    s.start+wMod, s.height,
    s.start+wMod, s.height - s.hMod,
    s.start + s.width/2, s.height - s.hMod
  );
  ctx.bezierCurveTo(
    s.end-wMod, s.height - s.hMod,
    s.end-wMod, s.height,
    s.end, s.height
  );
  ctx.lineWidth = 4;
  ctx.strokeStyle = 'balck';
  ctx.stroke();
  
  ctx.beginPath();
  ctx.fillStyle = 'gray';
  ctx.arc(s.dot.x, s.dot.y, 8, 0, Math.PI * 2);
  ctx.fill();
};

function quad(progress) {
  return Math.pow(progress, 2);
}
function makeEaseOut(delta) { 
  return function(progress) {
    return 1 - delta(1 - progress);
  }
}
var quadOut = makeEaseOut(quad);

var bend = function(s) {
  if(s.progress < s.hMax) {
    var delta = quadOut(s.progress/s.hMax);
    s.hMod = s.hMax*delta;
    s.dot.y = s.height - s.hMax*delta;
    s.progress++;
  }
};
var reset = function(s) {
  if(s.progress > 0) {
    var delta = quadOut(s.progress/s.hMax);
    s.hMod = s.hMax*delta;
    s.dot.y = s.height - s.hMax*delta;
    s.progress -= 2;
  } else {
    s.hMod = 0;
    s.dot.y = s.height;
    s.progress = 0;
  }
};

var currentSection = 0;
process.on('mousemove', function(event) {
  var section = Math.floor((event.clientX - process.offset().left) / SECTION_WIDTH);
  currentSection = section;
  process.find('.active').removeClass('active');
  process.find('li').eq(section).addClass('active');
});

create(0);
create(200);
create(400);
create(600);

var loop = function() {
  ctx.clearRect(0, 0, canvas.width, canvas.height);
  
  sections.forEach(function(s, index) {
    if(currentSection === index) {
      bend(s);
    } else {
      reset(s);
    }
    draw(s);
  });
  
  window.requestAnimationFrame(loop);
};
loop();
</script>
		
		
		
		<!-- @@@@@@@@@22 -->
		<form action="${pageContext.request.contextPath}/U/User_email" method="post">
			<div class="validate_email">
				<h2>
					感谢您的注册！现在请按以下步骤完成您的注册!
				</h2>
				<div class="look_email">
					<h4>
						第一步：查看短信
					</h4>
					<div class="mess reduce_h">
						我们给您发送了验证短信，手机号码为：${sessionScope.userPhone} ;
						<span class="red"><span id="lblEmail"></span>
						</span>
						 

					</div>
					<h4>
						第二步：输入验证码
					</h4>
					<div class="mess">
						<span class="write_in">输入您收到的短信验证码：</span>
						<input name="verify" type="text" id="validatecode" class="yzm_text" />
						<input class="finsh" type="button" value="完 成" id="Button1" />
						<span id="errorMsg" class="no_right"></span>
					</div>
				</div>
			</div>
		</form>

	</body>
</html>

