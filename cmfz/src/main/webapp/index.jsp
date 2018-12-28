<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>

</head>
<body>
<h2>Hello World!adsasdasdasdasdasdsadasdasddas陈好好</h2>
<script type="text/javascript">
    var goEasy = new GoEasy({
        appkey: "BC-5d656a5ce51b45779a80fbe8903f8c4c"
    });
    goEasy.subscribe({
        channel: "140",
        onMessage: function (message) {
            alert("Channel:" + message.channel + " content:" + message.content);
        }
    });
</script>



</body>
</html>
