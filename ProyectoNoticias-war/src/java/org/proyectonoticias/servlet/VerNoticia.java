/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proyectonoticias.servlet;

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
public class VerNoticia extends HttpServlet {

    @EJB
    private NewsFacade newsFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String slug = request.getParameter("s");
            List<News> news = newsFacade.findNewsBySlug(slug);
            News newAux;
            if (news.isEmpty()) {
                newAux = null;
            } else {
                newAux = news.get(0);
            }
            request.setAttribute("mynew", newAux);
            RequestDispatcher a = request.getRequestDispatcher("/readnew.jsp");
            a.forward(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"error\":\"Error al cargar la noticia\"}");
        }
    }

}
