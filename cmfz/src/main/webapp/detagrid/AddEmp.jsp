<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">
$(function () {

$("#addEmpAge").textbox({
    required:true
});

    $("#addEmpName").textbox({
        required:true
    });
    $("#addEmpPhone").textbox({
        required:true
    });
    $("#addEmpStyle").combobox({
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
    $("#addEmpSex").combobox({
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
    $("#addEmpAddress").textbox({
        required:true
    });
    $("#addEmpNcity").textbox({
        required:true
    });
    $("#addEmpNsalary").textbox({
        required:true
    });
    $("#addEmpNwork").textbox({
        required:true
    });
    $("#addEmpCity").textbox({
        required:true
    });
    $("#addEmpWorkTime").textbox({
        required:true
    });
   $("#addEmpSubmit").linkbutton({
       text:"添加",
       onClick:function () {

           $("#addChapterForm").form('submit',{
               url:"${pageContext.request.contextPath}/emp/regist",
               onSubmit: function(){
                   return $("#addChapterForm").form("validate");
               },

               success:function(data){

                   $.messager.show({
                       title:"添加",
                       msg:"添加成功",
                       showType:'show'
                   });

                   setTimeout(function () {

                       $("#album").datagrid("reload");
                       $("#addEmp").dialog("close");

                   },500)
               }

           });
       }
   })
})
</script>
<div align="center">
<h3>添加员工</h3>
    <div class="formtitle"><span>基本信息</span></div>


<form id="addChapterForm" method="POST" >

    姓名：<input style="width:200px" name="name" id="addEmpName" /><br/>
    年龄：<input style="width:200px" name="age" id="addEmpAge" /><br/>
    工种：<input style="width:200px" name="style" id="addEmpStyle" /><br/>
    性别：<input style="width:200px" name="sex" id="addEmpSex" /><br/>
    籍贯：<input style="width:200px" name="address" id="addEmpAddress" /><br/>
    手机号：<input style="width:200px" name="phone" id="addEmpPhone" /><br/>
    期望薪资：<input style="width:200px" name="nsalary" id="addEmpNsalary" /><br/>
    期望城市：<input style="width:200px" name="ncity" id="addEmpCity" /><br/>
    期望工作：<input style="width:200px" name="nwork" id="addEmpNwork" /><br/>
    期望工时：<input style="width:200px" name="worktime" id="addEmpWorkTime" /><br/>

    <input id="addEmpSubmit" value="添加" >
</form>
</div>
