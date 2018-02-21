/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proyectonoticias.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.ejb.EJB;
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
public class AddNoticias extends HttpServlet {

    @EJB
    private NewsFacade newsFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String titulo = request.getParameter("title");
            String noticia = request.getParameter("news");

            News mynew = new News();
            mynew.setTitle(titulo);
            mynew.setDescription(noticia);
            mynew.setSlug(crearSlug(titulo));
            mynew.setCreator("Admin");
            newsFacade.create(mynew);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"error\":\"Error al guardar la noticia\"}");
        }

    }

    private String crearSlug(String t) {
        Pattern NONLATIN = Pattern.compile("[^\\w-]");
        Pattern WHITESPACE = Pattern.compile("[\\s]");
        String nowhitespace = WHITESPACE.matcher(t).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        String finalSlug = slug.toLowerCase(Locale.ENGLISH);
        List<News> news = newsFacade.findAll();
        for (int i = 0; i < news.size(); i++) {
            if (news.get(i).getSlug().equals(finalSlug)) {
                finalSlug += "v" + String.valueOf(news.size());
            }
        }
        return finalSlug;
    }

}
