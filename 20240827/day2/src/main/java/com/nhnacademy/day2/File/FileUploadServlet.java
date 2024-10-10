package com.nhnacademy.day2.File;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

@MultipartConfig(
        fileSizeThreshold = 1024*1024*1,
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024*100,
        location = "D:\\소프트웨어 아카데미\\9주차\\20240827\\day2\\src\\main\\java\\com\\nhnacademy\\day2\\upload"
)
@Slf4j
public class FileUploadServlet extends HttpServlet {

    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = "D:\\소프트웨어 아카데미\\9주차\\20240827\\day2\\src\\main\\java\\com\\nhnacademy\\day2\\upload";
    private static final Logger log = Logger.getLogger(FileUploadServlet.class.getName());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for(Part part : req.getParts()) {
            String contentDisposition = part.getHeader(CONTENT_DISPOSITION);

            if(contentDisposition.contains("filename=")) {
                String fileName = extractFileName(contentDisposition);

                if(part.getSize() > 0){
                    part.write(UPLOAD_DIR + File.separator + fileName);
                    part.delete();
                }
            }else{
                String formValue = req.getParameter(part.getName());
                log.info(part.getName() + "=" + formValue);
            }
        }
        resp.sendRedirect("/");
    }

    private String extractFileName(String contentDisposition){
        log.info("contentDisposition=" + contentDisposition);
        for(String token : contentDisposition.split(";")){
            if(token.trim().startsWith("filename")){
                return token.substring(token.indexOf("=")+2, token.length()-1);
            }
        }
        return null;
    }


}
