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


import java.util.Date;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "RecordListServlet", value = "/recordListServlet")
public class RecordListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //获取条件查询参数
        //Map<String,String[]> condition = request.getParameterMap();
        String keyword = request.getParameter("part_a");



        //1.调用RecordService完成查询
        RecordService service=new RecordServiceImpl();
        //List<Record> records=service.selectRecord(condition);
        List<Record> records=service.selectRecord1(keyword);
        //2.将list存入request域
        request.setAttribute("records",records);
        /**
         * for (int i = 0; i < records.size(); i++) {
         *             System.out.println(records.get(i).getStart_date());
         *         }
         */
        //3.转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);
        //String contextPath = request.getSession().getServletContext().getRealPath("/");
        //System.out.println(contextPath);
    }
}
