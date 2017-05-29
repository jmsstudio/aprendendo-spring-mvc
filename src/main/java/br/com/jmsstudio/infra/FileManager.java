package br.com.jmsstudio.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Component
public class FileManager {

    @Autowired
    private HttpServletRequest request;

    public String save(String basePath, MultipartFile multipartFile) throws IOException{
        final String fileName = multipartFile.getOriginalFilename();

        String realPath = request.getServletContext().getRealPath(File.separator + basePath);
        String path = realPath + File.separator + fileName;

        File directory = new File(realPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        multipartFile.transferTo(new File(path));

        return basePath + File.separator + fileName;
    }

}
