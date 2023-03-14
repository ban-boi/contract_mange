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

import java.io.IOException;
@MultipartConfig
@WebServlet(name = "FindRecordServlet", value = "/findRecordServlet")
public class FindRecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取auto_id
        String auto_id=request.getParameter("auto_id");
        //2.调用Service查询
        RecordService service =new RecordServiceImpl();
        Record record =service.findRecordByAuto_id(auto_id);
        //final Part document =service.findDocument(auto_id);
        //3.将record存入request
        request.setAttribute("record",record);
        //request.setAttribute("file",document);
        //4.转发到update.jsp
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
