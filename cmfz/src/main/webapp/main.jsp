<%@ page isELIgnored="false" language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <script type="text/javascript" src="js/datagrid-detailview.js"></script>
    <link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="themes/IconExtension.css">
    <link rel="stylesheet" type="text/css" href="themes/icon.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>

    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>

    <script type="text/javascript" src="js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="js/jquery.edatagrid.js"></script>

    <script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
    <script src="js/echarts.min.js"></script>
    <script src="js/china.js"></script>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>

    <script type="text/javascript">
        <!--菜单处理-->

        $(function () {
            var albumid = "";
            $.get("${pageContext.request.contextPath}/menu/queryAll",
                function (result) {
                    $.each(result, function (i, item) {
                            var a = "";
                            var id = item.id;

                            $.post(
                                "${pageContext.request.contextPath}/menu/queryAllchildrenByPid?pid=" + id,
                                function (data1) {
                                    //console.log(data1);
                                    $.each(data1, function (index1, second) {
                                        console.log(second.title);
                                        a += "<p style='text-align: center'><a id=\"btn\" href=\"#\" class=\"easyui-linkbutton\" onclick=\"addTabs('" + second.title + "','" + second.iconcls + "','" + second.url + "')\" data-options=\"iconCls:'" + second.iconcls + "'\">" + second.title + "</a></p>";
                                    })



                                }
                            )
                            setTimeout(function () {
                                $('#aa').accordion('add', {
                                    title: item.title,
                                    iconCls: item.iconcls,
                                    selected: false,
                                    content: a
                                });
                            }, 200)

                            //加载一级菜单


                        }
                    )
                });
        });

        //添加标题栏
        function addTabs(title, iconCls, url) {
            var a = $("#tt").tabs("exists", title)
            if (a) {
                $("#tt").tabs("select", title)
            } else {
                $('#tt').tabs('add', {
                    title: title,
                    iconCls: iconCls,
                    href: "${pageContext.request.contextPath}" + url,
                    //href: "https://www.baidu.com",
                    selected: true,
                    closable: true

                });
            }

        }

    </script>

</head>
<body class="easyui-layout">



<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">

    </div>
</div>
<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-neighbourhood',"
           href="center.jsp"></div>
    </div>
</div>
</body>
</html>