package org.bparolini.moviecatalogservice.resources;

import org.bparolini.moviecatalogservice.models.Catalog;
import org.bparolini.moviecatalogservice.models.CatalogItem;
import org.bparolini.moviecatalogservice.models.Movie;
import org.bparolini.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private final RestTemplate restTemplate;

    @Autowired
    public MovieCatalogResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Catalog> getCatalog(@PathVariable String userId) {

        // get all rated movie IDs
        UserRating ratings = restTemplate.getForEntity("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class).getBody();

        if (ratings == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        List<CatalogItem> catalogItems = ratings.getRatings().stream().map(rating -> {
            // for each movie ID, call movie info service and get details
            final Movie movie = restTemplate.getForEntity("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class).getBody();

            // put them all together
            return movie != null ? new CatalogItem(movie.getName(), "", rating.getRating()) : null;
        }).filter(Objects::nonNull).collect(Collectors.toList());

        return ResponseEntity.ok(new Catalog(catalogItems));
    }
}
