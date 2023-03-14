<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>合同信息管理系统</title>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Roboto:400,700"
    />
    <!-- https://fonts.google.com/specimen/Roboto -->
    <link rel="stylesheet" href="css/fontawesome.min.css" />
    <link rel="stylesheet" href="css/font-awesome.css" />
    <!-- https://fontawesome.com/ -->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <!-- https://getbootstrap.com/ -->
    <link rel="stylesheet" href="css/templatemo-style.css"/>

    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css"/>

    <script>
      function deleterecord(auto_id){
        //用户安全提示
        if(confirm("确定删除？")){
          //访问路径
          location.href="${pageContext.request.contextPath}/delRecordServlet?auto_id="+auto_id;
        }
      }

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

  <body id="reportsPage">
    <nav class="navbar navbar-expand">
      <div class="container h-100">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
          <h1 class="tm-site-title mb-0">
            <i class="fas fa-building fa-1x" /></i>
            上海经贸国际货运有限公司
            <span class="sr-only">(current)</span>
          </h1>
        </a>

        <a class="nav-link"  href="${pageContext.request.contextPath}/index.jsp">
          合同管理页面
        </a>
      </div>
    </nav>


    <div class="container-fluid p-md-5 mt-1">

      <div class="row tm-content-row">

        <div class="col-sm-12 col-md-12 col-lg-2 col-xl-2 ">
          <div class="tm-block-col">
            <div class="tm-bg-primary-dark tm-block tm-block-product-categories111">

              <h2 class="tm-block-title111">一个月之内到期：</h2>
              <div class="tm-product-table-container111">


                <table class="table tm-table-small tm-product-table">
                  <tbody>
                  <c:forEach items="${records}" var="record" varStatus="s">
                    <c:if test="${record.if_recent_expire}">
                      <tr>
                        <td>${record.id}</td>
                      </tr>
                    </c:if>
                  </c:forEach>
                  </tbody>
                </table>

              </div>

            </div>

          </div>

          <div class="tm-block-col">

            <div class="tm-bg-primary-dark tm-block tm-block-product-categories222">

              <form action="${pageContext.request.contextPath}/recordListServlet" method="post" class="tm-login-form">


                <div class="form-group ">
                  <label for="exampleInputName1">查询</label>
                  <input type="text"
                         name="part_a"
                         class="form-control validate"
                         id="exampleInputName1"
                         placeholder="请输入任一信息项的任意字段"/>
                </div>


<%--                <div class="form-group ">--%>
<%--                  <label for="exampleInputName2">生效日期</label>--%>
<%--                  <input type="text"--%>
<%--                         name="start_date"--%>
<%--                         class="form-control"--%>
<%--                         id="exampleInputName2"--%>
<%--                         placeholder="XXXX-XX-XX"/>--%>
<%--                </div>--%>

<%--                <div class="form-group mt-3">--%>
<%--                  <label for="exampleInputName3">截止日期</label>--%>
<%--                  <input type="text"--%>
<%--                         name="expiry_date"--%>
<%--                         class="form-control validate"--%>
<%--                         id="exampleInputName3"--%>
<%--                         placeholder="XXXX-XX-XX">--%>
<%--                </div>--%>

<%--                <div class="form-group mt-3">--%>
<%--                  <label for="exampleInputEmail4">合同编号</label>--%>
<%--                  <input type="text"--%>
<%--                         name="id"--%>
<%--                         class="form-control"--%>
<%--                         id="exampleInputEmail4"--%>
<%--                         placeholder="输入全部或部分编号">--%>
<%--                </div>--%>

                <div class="form-group mt-4">
                  <button
                          type="submit"
                          class="btn btn-primary btn-block text-uppercase"
                  >
                    查询
                  </button>
                </div>
                <button class="btn btn-primary btn-block text-uppercase"
                        href="${pageContext.request.contextPath}/recordListServlet">
                  显示全部
                </button>
              </form>

            </div>

          </div>
        </div>

        <div class="col-sm-12 col-md-12 col-lg-10 col-xl-10 tm-block-col">
          <div class="tm-bg-primary-dark tm-block tm-block-products111">
            <div class="tm-product-table-container222">
              <table  class="table table-hover tm-table-small tm-product-table">
                <form id="selected" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">

                  <tr>
                    <th scope="col"><input type="checkbox" id="firstCB"></th>
                    <th scope="col">合同编号</th>
                    <th scope="col">甲方单位名称</th>
                    <th scope="col">乙方单位名称</th>
                    <th scope="col">合同生效日期</th>
                    <th scope="col">合同到期日期</th>
                    <th scope="col">合同电子版</th>
                    <th scope="col">备注</th>
                    <th scope="col">数据操作</th>
                  </tr>

                  <!--用form表单标签包含table，即可使其包含的input标签提交checkbox信息-->
                  <c:forEach items="${records}" var="record" varStatus="s">
                    <tr>
                      <th scope="row"><input type="checkbox" name="rauto_id" value="${record.auto_id}"></th>
                      <td>${record.id}</td>
                      <td>${record.part_a}</td>
                      <td>${record.part_b}</td>
                      <td>${record.start_date}</td>
                      <td>${record.expiry_date}</td>
                      <td><a href="${pageContext.request.contextPath}/fileDownloadServlet?id=${record.id}">${record.doc_name}</a></td>
                      <td>${record.remarks}</td>

                      <td><a class="tm-product-delete-link" href="${pageContext.request.contextPath}/findRecordServlet?auto_id=${record.auto_id}">
                        <i class="far fa-edit tm-product-delete-icon" /></i>
                      </a>
                        <a class="tm-product-delete-link" href="javascript:deleterecord(${record.auto_id});">
                          <i class="far fa-trash-alt tm-product-delete-icon"></i>
                        </a>
                      </td>
                    </tr>
                  </c:forEach>
                </form>
              </table>            </div>
            <!-- table container -->
            <a
                    href="${pageContext.request.contextPath}/add.jsp"
                    class="btn btn-primary btn-block text-uppercase mb-3">添加新记录</a>
            <button class="btn btn-primary btn-block text-uppercase" href="javascript:void(0);" id="delSelect">
              删除选中记录
            </button>
          </div>
        </div>

      </div>
    </div>
    <footer class="tm-footer row tm-mt-small">
      <div class="col-12 font-weight-light">
        <p class="text-center text-white mb-0 px-4 small">
          Copyright &copy; 2018.Company name All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/">网页模板</a>
        </p>
      </div>
    </footer>

    <script src="js/jquery-3.3.1.min.js"></script>
    <!-- https://jquery.com/download/ -->
    <script src="js/bootstrap.min.js"></script>
    <!-- https://getbootstrap.com/ -->
    <script>
      $(function() {
        $(".tm-product-name").on("click", function() {
          window.location.href = "edit-product.html";
        });
      });
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