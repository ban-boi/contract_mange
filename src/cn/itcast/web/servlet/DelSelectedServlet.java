package cn.itcast.web.servlet;

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

import javax.servlet.http.Part;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DelSelectedServlet", value = "/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取所有auto_id
        String[] auto_ids = request.getParameterValues("rauto_id");
        //2.调用service删除
        RecordService service = new RecordServiceImpl();
        service.delSelectRecord(auto_ids);

        //3.跳转查询所有servlet
        response.sendRedirect(request.getContextPath()+"/recordListServlet");
    }
}
