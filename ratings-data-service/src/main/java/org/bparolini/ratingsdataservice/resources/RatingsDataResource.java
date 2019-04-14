package org.bparolini.ratingsdataservice.resources;

import org.bparolini.ratingsdataservice.models.Rating;
import org.bparolini.ratingsdataservice.models.UserRating;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {

    @GetMapping("/{movieId}")
    public ResponseEntity<Rating> getRating(@PathVariable String movieId) {
        return ResponseEntity.ok(new Rating(movieId, 4));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserRating> getUserRatings(@PathVariable String userId) {
        List<Rating> ratings = Arrays.asList(
            new Rating("1234", 4),
            new Rating("5678", 3)
        );

        return ResponseEntity.ok(new UserRating(ratings));
    }
}
