package pl.gk.tweeter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Copied from internet by Skipper on 2017-03-21.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet {
    private String text;

    @JsonIgnore
    private User user;

    @JsonIgnore
    private LocalDateTime date;
}
