/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proyectonoticias.servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
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
        try {
            List<News> news = newsFacade.findFirstNewsByDate();
            for (int i = 0; i < news.size(); i++) {
                tratarNews(news.get(i));
            }

            request.setAttribute("list_news", news);
            RequestDispatcher a = request.getRequestDispatcher("/news.jsp");
            a.forward(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"error\":\"Error al cargar noticias\"}");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int pag = Integer.parseInt(request.getParameter("page"));
            List<News> news = newsFacade.findMoreNewsByDate(pag * 3);
            if (news.size() == 0) {
                response.setContentType("application/json");
                PrintWriter pw = response.getWriter();
                pw.println("{\"mess\":\"No hay más noticias para cargar\"}");
            } else {
                for (int i = 0; i < news.size(); i++) {
                    tratarNews(news.get(i));
                }
                Gson g = new Gson();
                response.setContentType("application/json");
                PrintWriter pw = response.getWriter();
                pw.println(g.toJson(news));
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"error\":\"Error al cargar noticias\"}");
        }
    }

    private void tratarNews(News n) {
        String desc = Jsoup.parse(n.getDescription()).text();
        int limit = (desc.length() < 400) ? desc.length() : 400;
        String substring = desc.substring(0, limit);
        if (limit == 400) {
            substring += "...";
        }
        n.setDescription(substring);

    }

}
