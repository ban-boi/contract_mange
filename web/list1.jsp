<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>>

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
    <title>合同信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>

    <script>
        function deleterecord(auto_id){
            //用户安全提示
            if(confirm("确定删除？")){
                //访问路径
                location.href="${pageContext.request.contextPath}/delRecordServlet?auto_id="+auto_id;
            }
        }

        document.write("请注意，以下编号的合同将在一个月之内到期："+"<br>")
        <c:forEach items="${records}" var="record" varStatus="s">
        if(${record.if_recent_expire})
        {
            document.write(${record.id}+"<br>");
        }
        </c:forEach>

        window.onload=function(){
            //给删除选中按钮添加单机事件
            document.getElementById("delSelect").onclick=function() {
                if (confirm("您确定要删除选中条目吗？")){
                    //判断是否有选中条目
                    var flag = false;
                    var cbs = document.getElementsByName("rauto_id");
                    for (var i = 0; i < cbs.length; i++) {
                        if (cbs[i].checked) {
                            //至少选了一个
                            flag = true;
                            break;
                        }
                    }

                    if (flag) {//有条目被选中
                        //表单提交
                        document.getElementById("selected").submit();
                    }
                }
            }
        }


    </script>

</head>
<body>

<div class="container">

    <h1 style="text-align: center">合同列表</h1>

    <div style="float: right;margin:5px">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加</a>
        <a type="bottom" class="btn btn-primary" href="javascript:void(0);" id="delSelect">删除选中</a>
    </div>
    <br>
    <br>
    <br>
    <div>

        <form class="container" action="${pageContext.request.contextPath}/recordListServlet">
            <div class="row">
                <div class="form-group col-lg-3 col-xs-6">
                    <label for="exampleInputName1">甲方公司名</label>
                    <input type="text"
                           name="part_a"
                           class="form-control"
                           id="exampleInputName1"
                           placeholder="输入完整或部分名称">
                </div>
                <div class="form-group col-lg-3 col-xs-6">
                    <label for="exampleInputName2">生效日期</label>
                    <input type="text"
                           name="start_date"
                           class="form-control"
                           id="exampleInputName2"
                           placeholder="XXXX-XX-XX">
                </div>
                <div class="form-group col-lg-3 col-xs-6">
                    <label for="exampleInputName3">截止日期</label>
                    <input type="text"
                           name="expiry_date"
                           class="form-control"
                           id="exampleInputName3"
                           placeholder="XXXX-XX-XX">
                </div>
                <div class="form-group col-lg-3 col-xs-6">
                    <label for="exampleInputEmail4">合同编号</label>
                    <input type="text"
                           name="id"
                           class="form-control"
                           id="exampleInputEmail4"
                           placeholder="输入全部或部分编号">
                </div>
            </div>

            <button type="submit"
                    class="btn btn-default"
                    style="float: right;margin:5px">查询</button>
            <div style="float: right;margin:5px">
                <a class="btn btn-default" href="${pageContext.request.contextPath}/recordListServlet">全部</a>
            </div>
        </form>
    </div>

    <div class="table-responsive">

        <table border="1" class="table table-bordered table-hover">
            <form id="selected" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">

                <tr class="success">
                    <th><input type="checkbox" id="firstCB"></th>
                    <th>合同编号</th>
                    <th>甲方单位名称</th>
                    <th>乙方单位名称</th>
                    <th>合同生效日期</th>
                    <th>合同到期日期</th>
                    <th>合同电子版</th>
                    <th>数据操作</th>
                </tr>

                <!--用form表单标签包含table，即可使其包含的input标签提交checkbox信息-->
                <c:forEach items="${records}" var="record" varStatus="s">
                    <tr>
                        <th><input type="checkbox" name="rauto_id" value="${record.auto_id}"></th>
                        <td>${record.id}</td>
                        <td>${record.part_a}</td>
                        <td>${record.part_b}</td>
                        <td>${record.start_date}</td>
                        <td>${record.expiry_date}</td>
                        <td><a href="${pageContext.request.contextPath}/fileDownloadServlet?id=${record.id}">${record.doc_name}</a></td>
                        <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findRecordServlet?auto_id=${record.auto_id}">修改</a>
                            <a class="btn btn-default btn-sm" href="javascript:deleterecord(${record.auto_id});">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </form>
        </table>

    </div>






</div>
<script>
    //1.获取第一个cb
    document.getElementById("firstCB").onclick=function(){
        //2.获取以下列表中的所有cb
        var cbs =document.getElementsByName("rauto_id");
        //3.遍历
        for(var i=0;i<cbs.length;i++){
            //4.设置这些cbs[i]的checked状态=fristCB.checked
            cbs[i].checked=this.checked;
        }
    }
</script>
</body>
</html>
