package agusramdan.demo.otika.controller;

import com.jlefebure.spring.boot.minio.MinioException;
import com.jlefebure.spring.boot.minio.MinioService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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
//    @PostMapping(value = "/", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    @ResponseStatus(HttpStatus.CREATED)
//    Mono<Void> upload(@RequestPart("file") Mono<FilePart> file: ): {
//        return minioService..save(file)
//    }

    // @GetMapping("/")
    // public List testMinio() throws MinioException {
    //     return minioService.list();
    // }

    @GetMapping("/{object}")
    public ResponseEntity<Mono<InputStreamResource>> getObject(@PathVariable("object") String object) throws MinioException, IOException {
        InputStream inputStream = minioService.get(Paths.get(object));
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+ object)
                .contentType(MediaType.parseMediaType(URLConnection.guessContentTypeFromName(object)))
                .body(Mono.just(inputStreamResource));
    }
}
