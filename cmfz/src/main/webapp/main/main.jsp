<%@ page isELIgnored="false" language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">
    <link rel="stylesheet" type="text/css" href="../themes/icon.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        <!--菜单处理-->

        $(function () {
            $.get("${pageContext.request.contextPath}/menu/queryAll",
            function (result) {
                $.each(result,function (i, item) {
                    var id=item.id;
                  var title1=item.title;
                  //加载一级菜单
                        $('#aa').accordion('add', {
                            title: item.title,
                            iconCls: item.iconcls,
                            selected: false,
                            content: '<div style="padding:10px 0px"><ul id="tree' + id + '"></ul></div>',
                        });

                    $.post(
                        "${pageContext.request.contextPath}/menu/queryAllchildrenByPid?pid=" + id,
                        function (data) {
                            $("#tree" + id).tree({
                               data:data,
                               animate:true,
                                iconCls:data.iconcls,
                               formatter:function(data){

                                   return data.title;
                               },
                                lines:true,
                                onClick:function (node) {
                                    console.log(node);
                                    if(node){
                                        addTab(node);
                                    }
                                }
                            });
                        }
                    )
            }
            )
            });
        });

        //添加标题栏
        function addTab(node){
            var tabExitOrNot=$('#tt').tabs('exists',node.title);
            console.log(tabExitOrNot);
            if(tabExitOrNot==true){
                $('#tt').tabs('select', node.title);
                return;
            }
            //添加选项卡
            $('#tt').tabs('add', {
                title: node.title,
                content: '<iframe scrolling="auto" frameborder="0" src="${pageContext.request.contextPath}' + node.url + '" style="width:100%;height:600px;"></iframe>',
                iconCls: node.iconcls,
                closable: true
            });
        }

    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">
        欢迎您:${sessionScope.Admin.username} &nbsp;<a href="#" class="easyui-linkbutton"
                                                    data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="#"
                                                                                                              class="easyui-linkbutton"
                                                                                                              data-options="iconCls:'icon-01'">退出系统</a>
    </div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 htf@zparkhr.com.cn</div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">

    </div>
</div>
<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-neighbourhood',"
             style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
    </div>
</div>
</body>
</html>