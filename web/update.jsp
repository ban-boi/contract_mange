<%--
  Created by IntelliJ IDEA.
  User: 24970
  Date: 2023/1/12
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--网页使用的语言-->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改合同信息</h3>
    <form action="${pageContext.request.contextPath}/updateRecordServlet" enctype="multipart/form-data" method="post">

        <!--隐藏域 提交id-->
        <input type="hidden" name="auto_id" value="${record.auto_id}">

        <div class="form-group">
            <label for="id">合同编号：</label>
            <input type="text" class="form-control" id="id" name="id" value="${record.id}" placeholder="请输入合同编号" />
        </div>

        <div class="form-group">
            <label for="part_a">甲方单位名称：</label>
            <input type="text" class="form-control" id="part_a" name="part_a" value="${record.part_a}" placeholder="请输入甲方单位名称" />
        </div>

        <div class="form-group">
            <label for="part_b">乙方单位名称：</label>
            <input type="text" class="form-control" id="part_b" name="part_b" value="${record.part_b}" placeholder="请输入乙方单位名称" />
        </div>

        <div class="form-group">
            <label for="start_date">合同起始日期：</label>
            <input type="date" class="form-control" id="start_date" name="start_date" value="${record.start_date}">
        </div>

        <div class="form-group">
            <label for="expiry_date">合同结束日期：</label>
            <input type="date" class="form-control" id="expiry_date" name="expiry_date" value="${record.expiry_date}">
        </div>

        <div class="form-group">
            <label for="expiry_date">合同电子版：(已上传：${record.doc_name})</label>
            <input type="file" class="form-control" name="file" id="file" value="${record.doc_name}"/>
            <!--<input type="submit" value="Upload" name="upload" id="upload" />-->
        </div>

        <div class="form-group">
            <label for="expiry_date">备注：</label>
            <input type="text" class="form-control" id="remarks" name="remarks" value="${record.remarks}">
        </div>


        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>
