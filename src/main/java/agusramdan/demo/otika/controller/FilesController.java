package agusramdan.demo.otika.controller;

import com.jlefebure.spring.boot.minio.MinioException;
import com.jlefebure.spring.boot.minio.MinioService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/otika/files")
public class FilesController {

    private MinioService minioService;


    // @GetMapping("/")
    // public List testMinio() throws MinioException {
    //     return minioService.list();
    // }

    @GetMapping("/{object}")
    public void getObject(@PathVariable("object") String object, HttpServletResponse response) throws MinioException, IOException {
        InputStream inputStream = minioService.get(Paths.get(object));
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        // Set the content type and attachment header.
        response.addHeader("Content-disposition", "attachment;filename=" + object);
        response.setContentType(URLConnection.guessContentTypeFromName(object));

        // Copy the stream to the response's output stream.
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }
}
