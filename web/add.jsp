<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- HTML5文档-->
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加用户</title>
    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <center><h3>添加合同信息页面</h3></center>
    <form action="${pageContext.request.contextPath}/addRecordServlet" enctype="multipart/form-data" method="post">
        <div class="form-group">
            <label for="id">合同编号：(请确保输入的每条合同编号是独一无二的)</label>
            <input type="text" class="form-control" id="id" name="id" placeholder="请输入合同编号">
        </div>

        <div class="form-group">
            <label for="part_a">甲方：</label>
            <input type="text" class="form-control" id="part_a" name="part_a" placeholder="请输入甲方公司名称">
        </div>

        <div class="form-group">
            <label for="part_b">乙方：</label>
            <input type="text" class="form-control" id="part_b" name="part_b" placeholder="请输入乙方单位名称">
        </div>

        <div class="form-group">
            <label for="start_date">合同起始日期：</label>
            <input type="date" class="form-control" id="start_date" name="start_date">
        </div>

        <div class="form-group">
            <label for="expiry_date">合同起始日期：</label>
            <input type="date" class="form-control" id="expiry_date" name="expiry_date">
        </div>

        <div class="form-group">
            <label for="expiry_date">合同电子版：</label>
            <input type="file" class="form-control" name="file" id="file" />
            <!--<input type="submit" value="Upload" name="upload" id="upload" />-->
        </div>

        <div class="form-group">
            <label for="expiry_date">备注：</label>
            <input type="text" class="form-control" id="remarks" name="remarks">
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" />
        </div>


    </form>
</div>
</body>
</html>