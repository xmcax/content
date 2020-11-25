package pl.adamnowicki.demo.content;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class FileController {

    @GetMapping(path = "/file", produces = "application/zip" )
    public ResponseEntity<Resource> getFile() {
        final InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("test.zip");
        final String name = UUID.randomUUID().toString() + ".zip";
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-Disposition", String.format("attachment; filename=\"%s\"", name))
                .body(new InputStreamResource(resourceAsStream));
    }
}
