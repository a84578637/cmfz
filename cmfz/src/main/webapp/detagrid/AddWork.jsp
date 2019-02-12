<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">
    $(function () {


        $("#addEmpAddress").textbox({
            required:true,
            editable:false
        });
        $("#addEmpSalary").textbox({
            required:true
        });
        $("#addEmpName").textbox({
            required:true,
            editable:false
        });
        $("#addEmpPhone").textbox({
            required:true,
            editable:false
        });

        $("#addWorkForm").form("load",
            "${pageContext.request.contextPath}/emp/queryByPhone?phone="+${param.phone}
        );
        $("#selectWork").combobox({
            url:'${pageContext.request.contextPath}/comp/getAllComp',
            valueField:'id',
            textField:'contact',
            editable:false,
            onLoadSuccess:function (data) {
                $("#selectWork").combobox("setValue",data[0].id);

            }
        })

        $("#addWorkSubmit").linkbutton({
            text:"修改信息",
            onClick:function () {
                $("#addWorkForm").form('submit',{
                    url:"${pageContext.request.contextPath}/emp/addWork",
                    onSubmit: function(){
                        return $("#addWorkForm").form("validate");
                    },
                    success:function(data){

                        $.messager.show({
                            title:"就业",
                            msg:"修改就业信息成功",
                            showType:'show'
                        });

                        setTimeout(function () {


                            $("#addWork").dialog("close");

                        },500)
                    }

                })
            }
        })

    })

</script>

<h1>选择企业</h1>

<form id="addWorkForm">
    <input name="id" id="addEmpId" hidden="hidden">
    姓名：<input style="width:200px" name="name" id="addEmpName" /><br/>
    籍贯：<input style="width:200px" name="address" id="addEmpAddress" /><br/>
    手机号：<input style="width:200px" name="phone" id="addEmpPhone" /><br/>
    选择企业：<input id="selectWork" name="compid" ><br/>
    就业薪资：<input id="addEmpSalary" name="nsalary" >（请输入实际就业薪资）<br/><br/><br/><br/>

    <input id="addWorkSubmit" value="就业" >
</form>