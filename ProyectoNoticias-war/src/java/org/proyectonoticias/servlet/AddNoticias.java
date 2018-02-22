/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proyectonoticias.servlet;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.proyectonoticias.entities.News;
import org.proyectonoticias.sessionbeans.NewsFacade;

/**
 *
 * @author Ramon
 */
@MultipartConfig
public class AddNoticias extends HttpServlet {

    @EJB
    private NewsFacade newsFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            System.out.println("SERVLET");
            System.out.println("-------------------------");
            String username = request.getRemoteUser();
            String titulo = request.getParameter("title_new");
            String noticia = request.getParameter("noticia");
            System.out.println(titulo);
            System.out.println(noticia);
            
            

//            File fImg = new File(System.getProperty("uploads.img"));
//            if (!fImg.exists()) {
//                fImg.mkdirs();
//            }

//            File fMid = new File(System.getProperty("uploads.imgMid"));
//            if (!fMid.exists()) {
//                fMid.mkdirs();
//            }
            
//            Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
//            System.out.println("COJO FILE");
//            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
//            System.out.println(fileName);
//            InputStream fileContent = filePart.getInputStream();
//            
//            BufferedImage image = ImageIO.read(fileContent);
//            if (image !=null) {
//                File myfileimg = new File(fImg, crearSlug(titulo) + ".jpg");
//                System.out.println(myfileimg);
//            //ImageIO.write(resizeTrick(image, 800, 600), ".jpg", myfileimg);
//            }else{
////                response.setContentType("application/json");
////            PrintWriter pw = response.getWriter();
////            pw.println("{\"mess\":\"Error con la imagen\"}");
//System.out.println("ERROR IMG");
//            }
            
            News mynew = new News();
            mynew.setTitle(titulo);
            mynew.setDescription(noticia);
            String mySlug = crearSlug(titulo);
            System.out.println("XXX");
            Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
            if (filePart==null) {
                System.out.println("S NULO");
            }
            InputStream fileContent = filePart.getInputStream();
            BufferedImage image = ImageIO.read(fileContent);
            System.out.println("PATHS");
            System.out.println(request.getServletContext().getRealPath(""));
            System.out.println(request.getServletPath());
            String path = request.getServletContext().getRealPath("")
                    + File.separator + "img" + File.separator + "uploadImg" + File.separator + mySlug + ".png";
            System.out.println(path);
            String auxpath = request.getServletContext().getRealPath("")
                    + File.separator + ".." + File.separator + ".." + File.separator + ".." + File.separator + ".." + File.separator + "ProyectoNoticias-war" + File.separator + "web" + File.separator + "img" + File.separator + "uploadImg" + File.separator + mySlug + ".png";
            System.out.println(auxpath);
            File outputFile = new File(path);
            ImageIO.write(resizeBufferedImage(image, 800, 450), "png", outputFile);
            File outputFile2 = new File(auxpath);
            ImageIO.write(resizeBufferedImage(image, 800, 450), "png", outputFile2);
            System.out.println("ESCRITA");
            
            mynew.setSlug(mySlug);
            mynew.setCreator(username);
            mynew.setImg(mySlug);
            
            
            newsFacade.create(mynew);
            
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"mess\":\"../VerNoticia?s="+mySlug+"\"}");
            
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

    private BufferedImage resizeBufferedImage(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

}
