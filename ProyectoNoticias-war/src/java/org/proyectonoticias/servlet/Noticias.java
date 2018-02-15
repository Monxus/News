/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proyectonoticias.servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.proyectonoticias.entities.News;
import org.proyectonoticias.sessionbeans.NewsFacade;

/**
 *
 * @author Ramon
 */
public class Noticias extends HttpServlet {

    @EJB
    private NewsFacade newsFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            List<News> news = newsFacade.findFirstNewsByDate();
            request.setAttribute("list_news", news);
            RequestDispatcher a = request.getRequestDispatcher("/news.jsp");
            a.forward(request, response);
        
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
        int pag = Integer.parseInt(request.getParameter("page"));
        List<News> news = newsFacade.findMoreNewsByDate(pag*3);

        Gson g = new Gson();
        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();
        pw.println(g.toJson(news));
    }

}
