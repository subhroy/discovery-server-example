package roy.movie.rating.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roy.movie.rating.models.Rating;
import roy.movie.rating.models.UserMovieRating;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/v1/movie/rating/")
public class MovieRatingDataController {
    /*@RequestMapping("/{movieId}")
    public List<Rating> getMoviesInfo(@PathVariable("movieId") String movieId){
        return Collections.singletonList(
                new Rating(movieId,4)
        );*/
    @RequestMapping("/{movieId}")
    public Rating getMoviesInfo(@PathVariable("movieId") String movieId){
        return new Rating(movieId,4);
    }

    @RequestMapping("/users/{userId}")
    public UserMovieRating getUserMoviesRating(@PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("Titanic", 5),
                new Rating("Avatar", 5)
        );
        //return ratings;
        UserMovieRating  userMovieRating = new UserMovieRating();
        userMovieRating.setUserMovieRating(ratings);
        return userMovieRating;
    }
}
