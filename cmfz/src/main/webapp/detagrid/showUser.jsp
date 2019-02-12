<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">
    $(function () {



        var bannerToolUser=[{
            text: "删除用户",
            iconCls: 'icon-edit',
            handler: function () {
                //获取选中行
                var row = $("#pubUserDG").datagrid("getSelected");
                if (row != null) {
                    //编辑指定行


                $.get("${pageContext.request.contextPath}/user/del",{"id":row.userId},function (result) {
                    if(result=="success"){
                        $.messager.show({
                            title:'删除信息',
                            msg:'删除成功',
                            timeout:5000,
                            showType:'slide'
                        });
                        $('#pubUserDG').datagrid("reload");

                    }else{
                        alert("删除异常!!");
                    }
                })


                } else {
                    $.messager.show({
                        title:"消息",
                        msg:"请先选中",
                        showType:'show',
                        style:{
                            right:'',
                            top:document.body.scrollTop+document.documentElement.scrollTop,
                            bottom:''
                        }

                    });
                }
            }
        }]


        $('#pubUserDG').datagrid({
            url:'${pageContext.request.contextPath}/user/getUserInfo',
            columns:[[

                {field:'userName',title:'用户名',width:100},
                {field:'createTime',title:'创建时间',width:100},
                {field:'loginTime',title:'上次登录时间',width:100},
                {field:'userPhone',title:'手机号',width:100}


            ]],
            //填充单行
            fitColumns:true,
            //填充页面
            fit:true,
            pagination:true,
            pageList:[1,3,5,10],
            pageSize:3,
            toolbar:bannerToolUser
        });
    });
</script>

<table id="pubUserDG"></table>
<div id="delUser"></div>


