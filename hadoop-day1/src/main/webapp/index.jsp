<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta content="always" name="referrer">
<title>file</title>

	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery.min.js"></script>
<script type="text/javascript">
    $(function(){
        //  form 表单提交
        $("#fileForm").bind("submit",function(){
            var f = true;
            if ($("#fileup").val() == "") {
                // $("#divErrorMssage").text("用户名或密码不可为空!");
                return false;
            }else{
                return f;
            }
        });
    });
</script>
<body>
<form id="fileForm" class="fm" method="post" enctype="multipart/form-data"
	  action="${pageContext.request.contextPath}/file/fileUp">
	<input type="file" id="fileup" name="hfile">
	<input type="submit" value="上传" id="upbtn" >
</form>
</body>
</html>