<%@page contentType="text/html;charset=utf-8"%>

    <%@page isELIgnored="false" %>
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>

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
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
		
		
		$(function(){
			$("#imgVcode").live("click",function(){	
			 $("#imgVcode").attr("src","${pageContext.request.contextPath}/user/pic?flag="+Math.random());
			});
			$("#t").live("click",function(){	
			 $("#imgVcode").attr("src","${pageContext.request.contextPath}/user/pic?flag="+Math.random());
			});	
			
			$("#txtEmail").blur(function(){checkEmail();
			

			});

			$("#txtPassword").blur(function(){checkPassword();});
			$("#txtRepeatPass").blur(function(){checkRepeatPass();});
			$("#txtVerifyCode").blur(function(){checkVcode();});
			});
			
		function isEmail(val){
	          	var myreg = /^1\d{10}$/;
				if(!myreg.test(val))
				return '不是邮箱';
				return '是邮箱';
			};
			
		function checkName(){
				var value=$("#txtNickName").val();
				
				
				if(value.length==0){
					$(".d3").text("姓名不能为空！").css("color","red");
					return "error";
				}else if(value.length>20){

					$(".d3").text("姓名过长！").css("color","red");
					return "error";
				}else{
					$(".d3").css("color","green").text("输入正确！");
					return "success";
				}
			};
			function checkPassword(){
				var value=$("#txtPassword").val();
				if(value.length==0){
					$(".d4").text("密码不能为空！").css("color","red");
					return "error";
				}else if(value.length>0&&value.length<6){
					
					$(".d4").text("密码长度不符合要求！").css("color","red");
					return "error";
				}else if(value.length>20){
					$(".d4").text("密码长度不符合要求！").css("color","red");
					return "error";
				}else{
					$(".d4").css("color","green").text("该密码可用！");
					return "success";
				}
			};
			function checkRepeatPass(){
				var value1=$("#txtRepeatPass").val();
				var value2=$("#txtPassword").val();
				if(value1.length==0){
					$(".d5").text("密码不能为空！").css("color","red");
					return "error";
				}else if(value1!=value2){
					$(".d5").text("两次输入密码不相同！").css("color","red");
					return "error";
				}else{
					$(".d5").css("color","green").text("密码正确！");
					return "success";
				}
			};
			
			function checkEmail(){
				var value=$("#txtEmail").val();
				if(value.length==0){
					$(".d1").text("手机号码不能为空！").css("color","red");
					return "error";
				}else if(isEmail(value)=='不是邮箱'){
					$(".d1").text("手机号码格式错误！").css("color","red");
					return "error";
				}else {
				$(".d1").text("正在检查！").css("color","red");
                    canUse();
					return "success";
				}
			};
			function checkVcode(){

			    var code = $("#txtVerifyCode").val();

			$.post("${pageContext.request.contextPath}/user/checkVcode",
				{"vcode":code},function (result) {
					if(result=="success"){

                        $(".d6").text("验证码正确！").css("color","green");
                    }else{

                        $(".d6").text("请输入正确的验证码！").css("color","red");
                    }
                });



		
			


		};
		
		
		
		
			function canUse(){	
			var val=$("#txtEmail").val();
				var xhr;
				$.get("${pageContext.request.contextPath}/user/checkEmail",{"email":val},function (result) {
					if(result=="false"){
                        $(".d1").text("该号码已被注册！").css("color","red");
                    }else{

                        $(".d1").css("color","green").text("该号码可用！");
                    }

                })




			}


		function checkForm(){
			checkEmail();
			checkPassword();
			checkName();
			checkRepeatPass();
			canUse();
			
               if(checkName()=='success'&&checkPassword()=='success'&&checkRepeatPass()=='success'&&checkEmail()=='success'){
               	if(	$(".d6").text()=='验证码正确！'){
               	
               	 return true;
               	}else{

               	 return false;
               	}
                 
                 
               }else{
               	alert("信息错误！请检查!")
                  return false;
               }
		}
		</script>
		
		
		
		
		
		
		
		
	</head>
	<body>

	
		
		<!-- ----------!!!!!!!!!! -->
		<div class="process">
    <ol class="process-items">
        <li class="active" style="color:red">第一步 1
        <em>填&nbsp写&nbsp信&nbsp息&nbsp&nbsp</em></li>
        <li>第二步 2
        <em>&nbsp&nbsp&nbsp短&nbsp信&nbsp验&nbsp证&nbsp&nbsp</em></li>
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
		
		
		<div class="fill_message">
			<form name="ctl00" method="post" action="${pageContext.request.contextPath}/user/regist" id="f" onsubmit="return checkForm()">
				<table class="tab_login" >
					<tr>
						<td valign="top" class="w1">
							请输入您的手机号：
						</td>
						<td>
							<input name="phone" type="text" id="txtEmail" maxlength="30" class="text_input"/>
							<div class="text_left" id="emailValidMsg">
								<p>
									该手机号将会短信认证请认真填写

								、将会成为您登录的用户名
								</p>
									<span id="email.info" class = "d1"style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							请输入您的姓名：
						</td>
						<td>
							<input name="username" type="text" id="txtNickName" maxlength="11" class="text_input" />
							<div class="text_left" id="nickNameValidMsg">
								<p>

								</p>
								<span id="name.info" class="d3" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							设置密码：
						</td>
						<td>
							<input name="password" type="password" maxlength="20"  id="txtPassword"
								class="text_input" />
							<div class="text_left" id="passwordValidMsg">
								<p>
									您的密码可以由大小写英文字母、数字组成，长度6－20位。
								</p>
								<span id="password.info" class="d4" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							再次输入您设置的密码：
						</td>
						<td>
							<input name="password1" type="password" id="txtRepeatPass"
								class="text_input"/>
							<div class="text_left" id="repeatPassValidMsg">
							<span id="password1.info" class="d5"  style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							验证码：
						</td>
						<td>
							<img class="yzm_img" id='imgVcode' src="${pageContext.request.contextPath}/user/pic" />
							<input name="vcode" type="text" maxlength="4" id="txtVerifyCode"
								class="yzm_input"/>

								<span id="number" class="d6" style="color:red"></span>

							<div class="text_left t1">
								<p class="t1">
									<span id="vcodeValidMsg">请输入图片中的四个字母。</span>

									<span id="number.info" style="color:red"></span>
									<a href="#" id ="t">看不清楚？换个图片</a>
								</p>

							</div>
						</td>
					</tr>
				</table>

				<div class="login_in">

					<input id="btnClientRegister" class="button_1" name="submit"  type="submit" value="注 册"/>
				</div>
			</form>
		</div>

	</body>
</html>

