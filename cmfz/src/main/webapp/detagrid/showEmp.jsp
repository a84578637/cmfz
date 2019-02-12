<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">
    $(function () {
        $("#showEmpForm").form("load",
            "${pageContext.request.contextPath}/emp/queryByPhone?phone=${param.phone}"
        );

        $("#showEmpAge").textbox({
            required:true
        });

        $("#showEmpName").textbox({
            required:true
        });
        $("#showEmpPhone").textbox({
            required:true
        });
        $("#showEmpStyle").combobox({
            editable:false,
            valueField:"label",
            textField:"value",
            data:[{
                label: '长工',
                value: '长工'

            },{
                label: '短工',
                value: '短工'

            },{
                label: '假期工',
                value: '假期工'

            }]

        });
        $("#showEmpSex").combobox({
            editable:false,
            valueField:"label",
            textField:"value",
            data:[{
                label: '男',
                value: '男'

            },{
                label: '女',
                value: '女'

            }]

        });
        $("#showEmpAddress").textbox({
            required:true
        });
        $("#showEmpNcity").textbox({
            required:true
        });
        $("#showEmpNsalary").textbox({
            required:true
        });
        $("#showEmpNwork").textbox({
            required:true
        });
        $("#showEmpCity").textbox({
            required:true
        });
        $("#showEmpWorkTime").textbox({
            required:true
        });




        $("#updateEmpSubmit").linkbutton({
            text:"修改信息",
            onClick:function () {

                $("#showEmpForm").form('submit',{
                    url:"${pageContext.request.contextPath}/emp/showUpdateEmp",
                    onSubmit: function(){
                        return $("#showEmpForm").form("validate");
                    },

                    success:function(data){

                        $.messager.show({
                            title:"修改",
                            msg:"修改成功",
                            showType:'show'
                        });

                        setTimeout(function () {


                            $("#showEmp").dialog("close");

                        },500)
                    }

                });
            }
        })

    })

</script>

<form id="showEmpForm" method="POST" >
            <input name="id" id="showEmpId" hidden="hidden">
    姓名：<input style="width:200px" name="name" id="showEmpName" /><br/>
    年龄：<input style="width:200px" name="age" id="showEmpAge" /><br/>
    工种：<input style="width:200px" name="style" id="showEmpStyle" /><br/>
    性别：<input style="width:200px" name="sex" id="showEmpSex" /><br/>
    籍贯：<input style="width:200px" name="address" id="showEmpAddress" /><br/>
    手机号：<input style="width:200px" name="phone" id="showEmpPhone" /><br/>
    期望薪资：<input style="width:200px" name="nsalary" id="showEmpNsalary" /><br/>
    期望城市：<input style="width:200px" name="ncity" id="showEmpCity" /><br/>
    期望工作：<input style="width:200px" name="nwork" id="showEmpNwork" /><br/>
    期望工时：<input style="width:200px" name="worktime" id="showEmpWorkTime" /><br/>

    <input id="updateEmpSubmit" value="修改" >
</form>