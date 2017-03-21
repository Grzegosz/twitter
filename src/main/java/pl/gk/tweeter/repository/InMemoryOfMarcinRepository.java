package pl.gk.tweeter.repository;

import lombok.Getter;
import org.springframework.stereotype.Component;
import pl.gk.tweeter.model.Tweet;
import pl.gk.tweeter.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copied from internet by Skipper on 2017-03-21.
 */

@Component
@Getter
public class InMemoryOfMarcinRepository {
    private final Map<Long, List<Tweet>> tweets;
    private final Map<Long, User> users;

    public InMemoryOfMarcinRepository() {
        tweets = new ConcurrentHashMap<>();
        users = new ConcurrentHashMap<>();
    }

    public void put(Long userId, Tweet tweet) {
        List<Tweet> userTweets = tweets.computeIfAbsent(userId, k -> Collections.synchronizedList(new ArrayList<>()));
        userTweets.add(tweet);
    }
}
