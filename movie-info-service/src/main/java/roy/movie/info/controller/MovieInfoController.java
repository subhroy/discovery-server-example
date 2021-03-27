package roy.movie.info.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roy.movie.info.models.Movie;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/info/movies")
public class MovieInfoController {
    @RequestMapping("/{movieId}")
    public Movie getMoviesInfo(@PathVariable("movieId") String movieId){
        return new Movie(movieId,movieId+" is a popular Movie");
    }
}
