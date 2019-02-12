<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">
    $(function () {

        $("#stitle").textbox({
            readonly:true
        });
        $("#scontent").textbox({
            readonly:true,
            multiline:true
        })

        $("#showNoteFrom").form("load",
            "${pageContext.request.contextPath}/note/queryById?id=${param.id}"
        );
        setTimeout(function () {
            var coverimgsrc = "http://192.168.194.134/"+$("#simgPath").val();
            $("#imgP").attr("src",coverimgsrc);
        },300);

    })

</script>

<form id="showNoteFrom" method="post" >
    <table align="center">
        <tr align="center">
            <td valign="middle" align="left">
                <input id="stitle" name="title" readonly="readonly">
            </td>
        </tr>
        <tr>
            <td valign="middle" align="left">
                <img src="#"  id="imgP" width="300" HEIGHT="200">
                <input id="simgPath" name="imgPath" hidden="hidden" >

            </td>
        </tr>
        <tr>
            <td valign="middle" align="left">
                <input id="scontent" name="content" readonly="readonly"style="width: 300px ;height: 200px">
            </td>
        </tr>
    </table>
</form>