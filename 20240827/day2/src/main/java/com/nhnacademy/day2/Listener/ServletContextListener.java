package com.nhnacademy.day2.Listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class ServletContextListener implements javax.servlet.ServletContextListener{
    private static final Logger log = Logger.getLogger(ServletContextListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String counterFileName = servletContext.getInitParameter("counterFIleName");
        String counterFilePath = "/WEB-INF/classes/" + counterFileName;
        String realFilePath = servletContext.getRealPath(counterFilePath);
        log.info("path:" + realFilePath);

        File target = new File(realFilePath);

        if(target.exists()){
            try(FileInputStream fileInputStream = new FileInputStream(target);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(inputStreamReader);)
            {
                long c = Long.parseLong(br.readLine());
                servletContext.setAttribute("counter", c);
            } catch(FileNotFoundException fileNotFoundException){
                fileNotFoundException.printStackTrace();
            }catch(IOException ioException){
                ioException.printStackTrace();
            }
        }
        log.info("init counter : " + servletContext.getAttribute("counter"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce){
        ServletContext servletContext = sce.getServletContext();
        String counterFileName = servletContext.getInitParameter("counterFileName");
        String counterFilePath = "/WEB-INF/classes/" + counterFileName;
        String realFilePath = servletContext.getRealPath(counterFilePath);

        try(FileOutputStream fileOutputStream = new FileOutputStream(realFilePath);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
            BufferedWriter fw = new BufferedWriter(outputStreamWriter);
        ){
            fw.write(String.valueOf(servletContext.getAttribute("counter")));
        }catch(IOException ioException){
            ioException.printStackTrace();
        }

        System.out.println("distory-counter:" + servletContext.getAttribute("counter"));
    }
}
