package cn.itcast.web.servlet;

import cn.itcast.domain.Document;
import cn.itcast.domain.Record;
import cn.itcast.service.RecordService;
import cn.itcast.service.impl.RecordServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import javax.servlet.http.Part;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.sql.*;
import java.util.Map;


@MultipartConfig
@WebServlet(name = "UpdateRecordServlet", value = "/updateRecordServlet")
public class UpdateRecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取map及文件
        Map<String,String[]> map=request.getParameterMap();
        String auto_id=request.getParameter("auto_id");
        Part document_part = request.getPart("file");

        //3.封装对象
        Record record =new Record();
        try {
            BeanUtils.populate(record,map);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        //4.调用Service修改（如果用户没有上传文件，就把原来的文件和文件名传过去；如果上传了文件，就更新成新的文件）
        RecordService service=new RecordServiceImpl();
        if(document_part.getSize()==0){
            Document document =service.findDocument(auto_id);
            //调用findDocument方法获得本条记录原来的文件及文件名 打包存放在存在Document类中 命名为document

            InputStream input = new ByteArrayInputStream(document.getDocument());
            //将获取到的document中的原来的文件（以字节数组存在）转为inputstream类型

            record.setDoc_name(document.getDoc_name());
            //将获取到的document中的原来的文件名赋给record对象

            service.updateRecord(record,document_part);
            //先把record对象中的数据传过去

            service.updateDocument(auto_id,input);
            //再把文件（inputstream类型）单独传过去
        }
        else {
            //System.out.println(document_part.getSubmittedFileName());
            service.updateRecord(record,document_part);
        }

        //5.调转到查询所有Service
        response.sendRedirect(request.getContextPath()+"/recordListServlet");
    }
}
