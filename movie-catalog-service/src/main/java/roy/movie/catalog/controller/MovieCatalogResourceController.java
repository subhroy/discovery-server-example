package roy.movie.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import roy.movie.catalog.models.CatalogItem;
import roy.movie.catalog.models.Movie;
import roy.movie.catalog.models.Rating;
import roy.movie.catalog.models.UserMovieRating;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog")
public class MovieCatalogResourceController {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  WebClient.Builder webClientBuilder;

  @RequestMapping("/{userId}")
  public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

      //Another way of making service call using WebClient
      //WebClient is the reactive way - its the way of asynchronus type call

      //WebClient.Builder build = WebClient.builder(); - shifted to bean & autowired



    //REST Template way - Older way
    //RestTemplate restTemplate = new RestTemplate();

    // get all rated movie ids
    //List<Rating> ratings = Arrays.asList(new Rating("Titanic", 4), new Rating("Avatar", 5));
     //UserMovieRating ratings = restTemplate.getForObject("http://localhost:8083/api/v1/movie/rating/users/"+userId, UserMovieRating.class);

    //Ratings service call using Discovery Server
    // movie-ratings-service -> Name Registered in Discovery Server
    // movie-info-service -> Name Registered in Discovery Server
    UserMovieRating ratings = restTemplate.getForObject("http://movie-ratings-service/api/v1/movie/rating/users/"+userId, UserMovieRating.class);

    return ratings.getUserMovieRating().stream()
        .map(
            rating -> {
                // for each movie id call movie info service and get details
                Movie movieObj =
                  restTemplate.getForObject(
                      //"http://localhost:8082/api/v1/info/movies/" + rating.getMovieId(),
                    "http://movie-info-service/api/v1/info/movies/" + rating.getMovieId(),
                      Movie.class);
                // put them all together
                return new CatalogItem(movieObj.getMovieName(), "Dummy Description", rating.getRating());
            })
        .collect(Collectors.toList());





      // Call using webclient builder
              /*  Movie movieObj =webClientBuilder.build()
                      .get()
                      .uri("http://localhost:8082/api/v1/info/movies/" + rating.getMovieId())
                      .retrieve()
                      .bodyToMono(Movie.class)
                      .block();*/

    /*return Collections.singletonList(
            new CatalogItem("Titanic","Movie on Titanic",4)
    );*/


  }
}
