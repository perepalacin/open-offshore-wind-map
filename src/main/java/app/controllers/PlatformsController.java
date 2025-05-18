package app.controllers;

import app.domain.PlatformDetails;
import app.services.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("${api.prefix}/platforms")
@RequiredArgsConstructor
public class PlatformsController {

    private final PlatformService platformService;

    @GetMapping("")
    public ResponseEntity<Object> listAll() {
        List<PlatformDetails> platforms = platformService.getAll();
        if (platforms == null || platforms.isEmpty()) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Something went wrong, please try again later.");
        }
        return ResponseEntity.status(OK).body(platforms);
    }

    @GetMapping("/{platformId}")
    public ResponseEntity<Object> findById(@PathVariable long platformId) {
        PlatformDetails platform = platformService.getById(platformId);
        if (platform == null) {
            return ResponseEntity.status(NOT_FOUND).body("We couldn't find the platform with id: " + platformId );
        }
        return ResponseEntity.status(OK).body(platform);
    }


}
