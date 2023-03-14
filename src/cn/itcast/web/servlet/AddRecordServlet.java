package cn.itcast.web.servlet;

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

import javax.servlet.http.Part;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Date;

@WebServlet(name = "AddRecordServlet", value = "/addRecordServlet")
@MultipartConfig
public class AddRecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取参数
        Map<String,String[]> map=request.getParameterMap();
        final Part document_part = (Part) request.getPart("file");

        //3.封装对象
        Record record= new Record();
        try {
            BeanUtils.populate(record,map);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        record.toString();
        
        //4.调用service保存
        RecordService service=new RecordServiceImpl();
        service.addRecord(record, (javax.servlet.http.Part) document_part);
        //5.跳转到recordListServlet
        response.sendRedirect(request.getContextPath()+"/recordListServlet");

    }
}
