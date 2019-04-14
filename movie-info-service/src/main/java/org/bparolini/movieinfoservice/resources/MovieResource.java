package org.bparolini.movieinfoservice.resources;

import org.bparolini.movieinfoservice.models.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieInfo(@PathVariable String movieId) {
        return ResponseEntity.ok(new Movie(movieId, "Test name"));
    }
}
