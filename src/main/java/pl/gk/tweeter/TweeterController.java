package pl.gk.tweeter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gk.tweeter.model.Tweet;
import pl.gk.tweeter.operations.TweeterOperations;
import pl.gk.tweeter.repository.InMemoryOfMarcinRepository;

import java.time.LocalDateTime;

/**
 * Copied from internet by Skipper on 2017-03-21.
 */
@RestController
@RequestMapping(value = "/tweets")

public class TweeterController {

    private final InMemoryOfMarcinRepository inMemoryOfMarcinRepository;
    private TweeterOperations tweeterOperations;

    @Autowired
    public TweeterController(InMemoryOfMarcinRepository inMemoryOfMarcinRepository, TweeterOperations tweeterOperations) {
        this.inMemoryOfMarcinRepository = inMemoryOfMarcinRepository;
        this.tweeterOperations = tweeterOperations;
    }

    @PostMapping(value = "/user/{userId}")
    ResponseEntity addTweet(@PathVariable("userId") Long userId, @RequestBody(required = false) Tweet tweet) {
        if(tweet == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        if(tweet.getText().length() > 150)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        tweet.setDate(LocalDateTime.now());
        tweeterOperations.performUltimateUpgradeOnTweetAddingUser(userId, tweet);
        inMemoryOfMarcinRepository.put(userId, tweet);

        return new ResponseEntity(HttpStatus.OK);
    }
}
