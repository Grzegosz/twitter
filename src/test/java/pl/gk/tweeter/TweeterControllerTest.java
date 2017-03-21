package pl.gk.tweeter;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.gk.tweeter.model.Tweet;
import pl.gk.tweeter.operations.TweeterOperations;
import pl.gk.tweeter.repository.InMemoryOfMarcinRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Copied from internet by Skipper on 2017-03-21.
 */
public class TweeterControllerTest {

    private TweeterController unit;
    private InMemoryOfMarcinRepository inMemoryOfMarcinRepository = mock(InMemoryOfMarcinRepository.class);
    private TweeterOperations tweeterOperations = mock(TweeterOperations.class);

    @Before
    public void setUp() throws Exception{
        unit = new TweeterController(inMemoryOfMarcinRepository, tweeterOperations);
    }

    @Test
    public void itAddsATweetWith259CharsWhichIsWaaaaayTooLong() throws Exception {
        Tweet tweet = new Tweet();

        String biscuit = "Wielodzietna rodzina, jest już 6 pociech i nagle niespodzianka... Siódme dziecko w drodze... " +
                "Usg i badania, no i już wiadomo, że będzie synek.\n" +
                "- Skarbie, jak go nazwiemy?\n" +
                "- Tomasz!\n" +
                "- Ale skąd takie zdecydowanie? Dlaczego Tomasz?\n" +
                "- Nie chciałaś w dupę to masz!";

        // tweet with 259 chars - to prove that please remove all spaces (inline this text) then select whole String and look
        // at the bottom of intellij, I checked it like that, that's why I'm sure this is over 150 chars.
        tweet.setText(biscuit);

        assertEquals(biscuit.length(), 259); // And this is proving that I'm right #dzfko
        ResponseEntity response = unit.addTweet(1L, tweet);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}