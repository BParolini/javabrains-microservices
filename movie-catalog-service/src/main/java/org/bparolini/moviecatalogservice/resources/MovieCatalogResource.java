package org.bparolini.moviecatalogservice.resources;

import org.bparolini.moviecatalogservice.models.CatalogItem;
import org.bparolini.moviecatalogservice.models.Movie;
import org.bparolini.moviecatalogservice.models.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        RestTemplate restTemplate = new RestTemplate();

        // get all rated movie IDs
        List<Rating> ratings = Arrays.asList(
            new Rating("1234", 4),
            new Rating("5678", 3)
        );

        // for each movie ID, call movie info service and get details
        return ratings.stream().map(rating -> {
            final Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(), "", rating.getRating());
        }).collect(Collectors.toList());

        // put them all together
    }
}
