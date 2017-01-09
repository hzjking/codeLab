<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="michaelhe">
    <%@include file="/WEB-INF/views/common/head.jsp" %>
    <!-- iCheck -->
    <title>文件上传</title>
</head>
<body style="height: 500px">
<%--<form id="uploadForm" action="doUpload.action" method="post" enctype="multipart/form-data">
    <div align="center">
        <fieldset style="width:80%">
            <legend>上传文件</legend><br/>
            <div align="left">上传文件1</div>
            <div align="left">
                <input type="file" name="file1"/>
            </div>
            <div align="left">上传文件2</div>
            <div align="left">
                <input type="file" name="file2"/>
            </div>
            <div>
                <div align='left'>上传文件说明1</div>
                <div align='left'><input type="text" name="description1"/></div>
            </div>
            <div>
                <div align='left'>上传文件说明2</div>
                <div align='left'><input type="text" name="description2"/></div>
            </div>
            <div>
                <div align='left'>
                    <input type='submit' value="上传文件"/>
                </div>
            </div>
        </fieldset>
    </div>
</form>--%>
<%--<form action="<%=request.getContextPath()%>/upload/uploadFile" method="post" enctype="multipart/form-data">
    <input type="hidden" name="hidden" value="1" id="hidden">
    用户：
    <input type="text" name="user">

    <input type="button" value="上传文件" onclick="addElement()">

    <input type="button" value="删除" onclick="deleteElement()">
    <div id="upload_div" style="width: 300px;">
    </div>
    <input type="submit" value="提交">
</form>--%>

<form action="<%=request.getContextPath()%>/upload/uploadFile" method="post" enctype="multipart/form-data">
    <%-- <input id="hidden" name="hidden" value="2" type="hidden">
     用户：
     <input name="user" type="text">--%>
    <%--<input value="上传文件" onclick="addElement()" type="button">
    <input value="删除" onclick="deleteElement()" type="button">--%>
    <div id="upload_div" style="width: 300px;">
        选择文件：
        <input name="file1" type="file">
        <br>
    </div>
    <input value="提交" type="submit">
</form>

</div>
<script type="text/javascript" language="javascript">

    $(document).ready(function(){

    });

    function addElement() {
        //得到隐藏input里面的value值
        var index = document.getElementById("hidden").getAttribute("value");
        if(index>4){
            alert("最大同时上传4个文件");
            return false;
        }

        //创建一个input类型的节点
        var inputElement = document.createElement("input");
        //设置新创建的节点的属性，类型为file，name值递增
        inputElement.setAttribute("type", "file");
        inputElement.setAttribute("name", "file" + index);

        //创建文本节点
        var fontElement = document.createTextNode("选择文件：");
        //将创建的文本节点添加到div中
        document.getElementById("upload_div").appendChild(fontElement);

        //将创建的input类型的节点添加到div中
        document.getElementById("upload_div").appendChild(inputElement);

        //创建一个换行
        var brElement = document.createElement("br");
        //添加到div中
        document.getElementById("upload_div").appendChild(brElement);
        //将隐藏input里面的value值加1
        document.getElementById("hidden").setAttribute("value",
                parseInt(index) + 1);
    }

    function deleteElement() {
        //因为add方法添加了3个节点循环三次
        for ( var i = 0; i < 3; i++) {
            //删除最后一个节点
            document.getElementById("upload_div").removeChild(
                    document.getElementById("upload_div").lastChild);
        }
        //将隐藏input里面的value值设为1
        document.getElementById("hidden").setAttribute("value",1);
    }


    function iFrameHeight() {
//        alert("come into iFrameHeight !!");
        var subWeb = document.frames ? document.frames["iframe"].document : ifm.contentDocument;
        if(ifm != null && subWeb != null) {
            ifm.height = subWeb.body.scrollHeight;
            ifm.width = subWeb.body.scrollWidth;
        }
    }
</script>
</body>
</html>