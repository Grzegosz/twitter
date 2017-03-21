package pl.gk.tweeter.operations;

import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;
import pl.gk.tweeter.repository.InMemoryOfMarcinRepository;
import pl.gk.tweeter.model.Tweet;
import pl.gk.tweeter.model.User;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Copied from internet by Skipper on 2017-03-21.
 */
public class TweeterOperationsTest {

    private final String ultimateLogMessage = "Upgrade tweet complete";
    private final Long userId = 18L; // ----------------> https://www.youtube.com/watch?v=5eFdt6Y_34E

    private TweeterOperations unit;
    private InMemoryOfMarcinRepository inMemoryRepo;

    @Before
    public void setUp() throws Exception{
        inMemoryRepo = mock(InMemoryOfMarcinRepository.class);
        unit = new TweeterOperations(inMemoryRepo);
    }

    @Test
    public void itReturnsProperUltimateLogMessage() throws Exception {
        assertEquals(unit.prepareUltimateLogMessage(), ultimateLogMessage);
    }

    @Test
    public void itChecksVeryImportantOperationWhereUserIsSet() throws Exception{
        // given
        Tweet tweetWithoutUser = new Tweet();
        User user = new User("Skipper");
        Map<Long, User> users = ImmutableMap.of(userId, user);

        // when
        when(inMemoryRepo.getUsers()).thenReturn(users);
        Tweet ultimateTweet = unit.performUltimateUpgradeOnTweetAddingUser(userId, tweetWithoutUser);

        // then
        assertEquals(ultimateTweet.getUser().getLogin(), user.getLogin());
    }
}