package cn.itcast.web.servlet;

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

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URLEncoder;



@WebServlet(name = "FileDownloadServlet", value = "/fileDownloadServlet")
public class FileDownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String id =request.getParameter("id");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String id = request.getParameter("id") != null ? request.getParameter("id") : "NA";

        ServletOutputStream sos;
        Connection con = null;
        PreparedStatement pstmt = null;
        sos = response.getOutputStream();
        //使用response获得字节输出
        ResultSet rset = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contract?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8&useUnicode=yes&rewriteBatchedStatements=true","root","666666");
            } catch (Exception e) {
                System.out.println(e);
                System.exit(0);
            }
            pstmt = con.prepareStatement("Select document,doc_name from contract where id=? and if_recent_expire=0");
            pstmt.setString(1, id.trim());
            rset = pstmt.executeQuery();
            if (rset.next()) {
                response.setContentType("APPLICATION/OCTET-STREAM");

                response.setHeader("Content-disposition", "inline; filename*=UTF-8''" + URLEncoder.encode(rset.getString("doc_name"), "UTF-8"));

                InputStream inputStream = rset.getBinaryStream("document");

                int i;
                while ((i = inputStream.read()) != -1) {
                    sos.write(i);
                }

                System.out.println(rset.getBytes("document"));
                System.out.println(rset.getString("doc_name"));
            } else
                return;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (con != null) {
                // closes the database connection
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

        sos.flush();
        sos.close();
        response.sendRedirect(request.getContextPath()+"/recordListServlet");

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
