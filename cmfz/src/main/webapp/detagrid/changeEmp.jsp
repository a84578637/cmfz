<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">
$(function () {
$("#searchFormat").linkbutton({
    iconCls: 'icon-search'
})

    $('#searchFormat').bind('click', function(){
        var phone = $("#searchPhone").val();
        $.get("${pageContext.request.contextPath}/emp/searchEmpByPhone",{"phone":phone},function (result) {
            if (result=="success"){
                $("#showEmp").dialog({
                    title:"员工信息 ",
                    width:500,
                    height:500,
                    closed:true,
                    cache:false,
                    href:"${pageContext.request.contextPath}/detagrid/showEmp.jsp?phone="+phone,
                    modal:true
                });
                $("#showEmp").dialog("open");
            } else {
                alert("号码输入错误，无此员工");
            }
        })



    });


})
</script>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>

    <ul class="forminfo">
        <li><label>员工手机号</label><input id="searchPhone" type="text" class="dfinput" /><input id="searchFormat" value="查询"><i>手机号不能超过30个字符</i></li>

    </ul>
    <ul >

    </ul>

</div>

<div id="showEmp"></div>