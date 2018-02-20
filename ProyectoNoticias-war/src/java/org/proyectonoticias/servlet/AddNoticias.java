/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proyectonoticias.servlet;

import java.io.IOException;
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
        String titulo = request.getParameter("title");
        String noticia = request.getParameter("news");
        
        System.out.println("----------------------------");
        System.out.println(titulo);
        System.out.println(noticia);
        
        News mynew = new News();
        mynew.setTitle(titulo);
        mynew.setDescription(noticia);
        //mynew.setImg(titulo);
        //mynew.setSlug(titulo);
        //mynew.setCreator("Admin");
        //MODIFICAR ENTITIES!!!!!!!!!!!!!!!!!!!!!
        System.out.println("ENTRANDOOOO");
        newsFacade.create(mynew);
        System.out.println("CREADOOOO");
    }

    

}
