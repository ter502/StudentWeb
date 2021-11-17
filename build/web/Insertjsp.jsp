<%-- 
    Document   : DBJSP
    Created on : Nov 17, 2021, 10:23:44 PM
    Author     : eiwte
--%>
<%@page import ="EntityDB.DBController" %>
<%@page import ="EntityDB.Student" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Page</title>
    </head>
    <body>
        <%
            String name = request.getParameter("Name");
            double gpa = Double.parseDouble(request.getParameter("GPA"));
            Student st = new Student();
            DBController DB = new DBController();
            st.setName(name);
            st.setGpa(gpa);
            DB.InputToBase(st);
            out.print("<h1> Insert Successful </h1>");
        %>
    </body>
</html>
