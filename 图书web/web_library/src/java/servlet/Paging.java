/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tool.MyTools;
import tool.book;
import tool.dbOption;

/**
 *
 * @author 周志军
 */
public class Paging extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String page=(String) session.getAttribute("page");
            String action=request.getParameter("action");
            List<book> bookslist = new ArrayList<book>();  
            if(action==null)
                action="";
            if(action.equals("1")){
                //返回第一页数据
                bookslist= dbOption.queryPage(1,10);           
                session.setAttribute("bookslist",bookslist); 
                session.setAttribute("page","1");

                response.sendRedirect("libraryShow.jsp");
            }
            if(action.equals("2")){
                //返回第2页数据
                bookslist= dbOption.queryPage(2,10);           
                session.setAttribute("bookslist",bookslist); 
                session.setAttribute("page","2");
                response.sendRedirect("libraryShow.jsp");
               
            }
            if(action.equals("3")){
                //返回第3页数据
                bookslist= dbOption.queryPage(3,10);           
                session.setAttribute("bookslist",bookslist);
                session.setAttribute("page","3");
                response.sendRedirect("libraryShow.jsp");
            }
            if(action.equals("pre")){
                   
                 if(page==null||page.equals("1")) 
                 { //返回第一页数据       
                    bookslist= dbOption.queryPage(1,10);           
                    session.setAttribute("bookslist",bookslist); 
                    session.setAttribute("page","1");
                    response.sendRedirect("libraryShow.jsp");
                 }   
                 else
                 {
                     //返回上一页
                    int currPage=MyTools.strToint(page);
                    String p=Integer.toString(currPage-1);
                    bookslist= dbOption.queryPage(currPage-1,10);           
                    session.setAttribute("bookslist",bookslist); 
                    session.setAttribute("page",p);
                    response.sendRedirect("libraryShow.jsp");
                 }
                     
             }

             if(action.equals("next")){
                 if(page==null)
                 {
                     //返回第2页
                    bookslist= dbOption.queryPage(2,10);           
                    session.setAttribute("bookslist",bookslist); 
                    session.setAttribute("page","2");
                    response.sendRedirect("libraryShow.jsp");
                 }
                 if(page.equals("3"))
                    //返回第三页
                 {    
                    bookslist= dbOption.queryPage(3,10);           
                    session.setAttribute("bookslist",bookslist); 
                    response.sendRedirect("libraryShow.jsp");
                 }
                else
                 {
                  //返回下一页
                    int currPage=MyTools.strToint(page);
                    String p=Integer.toString(currPage+1);
                    bookslist= dbOption.queryPage(currPage+1,10);           
                    session.setAttribute("bookslist",bookslist); 
                    session.setAttribute("page",p);
                    response.sendRedirect("libraryShow.jsp");   
                  }
             }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Paging.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Paging.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
