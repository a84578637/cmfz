<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">
    $(function () {

        $("#addCompContact").textbox({
            required: true
        });

        $("#addCompPhone").textbox({
            required: true
        });
        $("#addCompAddress").textbox({
            required: true
        });
        $("#addCompStyle").combobox({
            editable: false,
            valueField: "label",
            textField: "value",
            data: [{
                label: '长工',
                value: '长工'

            }, {
                label: '短工',
                value: '短工'

            }, {
                label: '假期工',
                value: '假期工'

            }]

        });
        $("#addCompNsex").combobox({
            editable: false,
            valueField: "label",
            textField: "value",
            data: [{
                label: '男',
                value: '男'

            }, {
                label: '女',
                value: '女'

            },{
                label:'无要求',
                value: '无要求'
            }]

        });
        $("#addCompMotoley").textbox({
            required: true
        });
        $("#addCompWork").textbox({
            required: true
        });
        $("#addCompSalary").textbox({
            required: true
        });
        $("#addCompWorktime").textbox({
            required: true
        });
        $("#addCompCity").textbox({
            required: true
        });
        $("#addCompReward").textbox({
            required: true
        });
        $("#addCompNperson").textbox({
            required: true
        });
        $("#addCompSubmit").linkbutton({
            text: "添加",
            onClick: function () {

                $("#addCompForm").form('submit', {
                    url: "${pageContext.request.contextPath}/comp/regist",
                    onSubmit: function () {
                        return $("#addCompForm").form("validate");
                    },

                    success: function (data) {
                if (data=="success"){
                    $.messager.show({
                        title: "添加",
                        msg: "添加成功",
                        showType: 'show'
                    });
                    $("#addCompForm").form("clear");
                }else {
                    $.messager.show({
                        title: "添加",
                        msg: "添加失败",
                        showType: 'show'
                    });
                }

                    }

                });
            }
        })
    })
</script>
<div align="center">
    <h3>添加企业</h3>
    <div class="formtitle"><span>基本信息</span></div>


    <form id="addCompForm" method="POST">

        企业名：<input style="width:200px" name="contact" id="addCompContact"/><br/>
        企业电话：<input style="width:200px" name="phone" id="addCompPhone"/><br/>
        企业地址：<input style="width:200px" name="address" id="addCompAddress"/><br/>
        所需性别：<input style="width:200px" name="nsex" id="addCompNsex"/><br/>
        招聘简章：<input style="width:200px" name="motoley" id="addCompMotoley"/><br/>
        所需工作：<input style="width:200px" name="work" id="addCompWork"/><br/>
        招聘薪资：<input style="width:200px" name="salary" id="addCompSalary"/><br/>
        工作时间：<input style="width:200px" name="worktime" id="addCompWorktime"/><br/>
        工作城市：<input style="width:200px" name="city" id="addCompCity"/><br/>
        所需类型：<input style="width:200px" name="style" id="addCompStyle"/><br/>
        所需人数：<input style="width:200px" name="nperson" id="addCompNperson"/><br/>
        每人奖金：<input style="width:200px" name="reward" id="addCompReward"/><br/>

        <input id="addCompSubmit" value="添加">
    </form>
</div>
