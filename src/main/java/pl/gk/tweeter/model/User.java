package pl.gk.tweeter.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Copied from internet by Skipper on 2017-03-21.
 */
@Data
@AllArgsConstructor
public class User {
    private String login;

    public void drinkWhey(){
        System.out.println("User is drinking whey in open anabolic window. He is right now not an ulana kurtyzana.");
    }
}
