<%--
  Created by IntelliJ IDEA.
  User: en
  Date: 2016/10/23
  Time: 2:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form  action="jsonAjax.action" method="post">
      <input type="text" id="te" name="te"/> <button id="dialog-name-value"  value="提交"  name="心想事成">洗dd液</button>
  </form>
  </body>
  <script  type="text/javascript" src="static/js/jquery.js"></script>
<script type="text/javascript">
    function doA(){
        alert("aaaaaaaaa");

        $.ajax({
            type: "POST",
            url: "jsonAjax.action",
            data: {},
            dataType:"json", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
            success: function(json){
                var obj = $.parseJSON(json);  //使用这个方法解析json
                var state_value = obj.result;  //result是和action中定义的result变量的get方法对应的
                alert(state_value);
            },
            error: function(json){
                alert("json=" + json);
                return false;
            }
        });
//        $.ajax({
//            type : "post",
//            url : "jsonAjax.action",
//            data : {        },
//
//            dataType:"json",
//            success : function(msg) {
//                alert("ccccccccccccccccccc");
//               //alert(msg);
////                var d = eval("("+msg+")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
////                alert(d);
////            alert(d.bookId);
////            alert(d.bookName);
////            alert(d.bookPrice);
//            },
//            error:function(){ alert("bbbbbbbbbbbbbbbbbbbbbb");}
//
//        });
//        alert("dddddddddddddddddddddddddddd");
    }
</script>
</html>
