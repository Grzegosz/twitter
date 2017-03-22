package pl.gk.tweeter.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.gk.tweeter.ImpossibleTrainingException;
import pl.gk.tweeter.model.Tweet;
import pl.gk.tweeter.model.User;
import pl.gk.tweeter.repository.InMemoryOfMarcinRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Copied from internet by Skipper on 2017-03-21.
 */

@Component
public class TweeterOperations {

    private InMemoryOfMarcinRepository inMemoryOfMarcinRepository;

    @Autowired
    public TweeterOperations(InMemoryOfMarcinRepository inMemoryOfMarcinRepository) {
        this.inMemoryOfMarcinRepository = inMemoryOfMarcinRepository;
    }

    public Tweet performUltimateUpgradeOnTweetAddingUser(Long userId, Tweet tweet) {
        User user = inMemoryOfMarcinRepository.getUsers().get(userId);
        tweet.setUser(user);

        // Not using loggers, because of operations security reasons
        System.out.println(prepareUltimateLogMessage());
        return tweet;
    }

    String prepareUltimateLogMessage() {
        String message = "Upgrade tweet complete";
        return message;
    }

    // NOT FINISHED DO NOT READ
    public void takeSteroids(User user) {
        if (checkTrainingConditions(user)){
            user.drinkWhey();
        }
    }

    private boolean checkTrainingConditions(User user) {
        List<Integer> trainingDays = IntStream.rangeClosed(1, 7).boxed().collect(Collectors.toList());

        long legTrainings = trainingDays.stream().filter(this::legDay).count();
        if (legTrainings > 0)
            throw new ImpossibleTrainingException();

        return isAnabolicWindowOpenedForUser(user);
    }

    private boolean legDay(int day) {
        return false;
    }

    private boolean isAnabolicWindowOpenedForUser(User user){
        int secondsAfterTraining = new Random().nextInt(240);
        return secondsAfterTraining < 120;
    }

}
