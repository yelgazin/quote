package com.kameleoon.trialtask.quote.controller;

import com.kameleoon.trialtask.quote.entity.Quote;
import com.kameleoon.trialtask.quote.entity.User;
import com.kameleoon.trialtask.quote.service.QuoteService;
import com.kameleoon.trialtask.quote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuoteService quoteService;

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users/{userName}/quotes")
    public ResponseEntity<?>  createQuote(@PathVariable String userName, @RequestBody Quote quote) {
        try {
            quoteService.createQuote(userName, quote);
            return new ResponseEntity<>(quote, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{userName}/quotes")
    public ResponseEntity<?> updateQuote(@PathVariable String userName, @RequestBody Quote quote) {
        try {
            Quote updatedQuote = quoteService.updateQuote(userName, quote);
            return new ResponseEntity<>(updatedQuote, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{userName}/quotes/{quoteId}")
    public ResponseEntity<?> deleteQuote(@PathVariable String userName, @PathVariable int quoteId) {
        try {
            quoteService.deleteQuote(userName, quoteId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users/{userName}/quotes/{quoteId}/like")
    public ResponseEntity<?> addLikeToQuote(@PathVariable String userName, @PathVariable int quoteId) {
        try {
            quoteService.addLikeToQuote(userName, quoteId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{userName}/quotes/{quoteId}/like")
    public ResponseEntity<?> deleteLikeFromQuote(@PathVariable String userName, @PathVariable int quoteId) {
        try {
            quoteService.removeLikeFromQuote(userName, quoteId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users/{userName}/quotes/{quoteId}/dislike")
    public ResponseEntity<?> addDislikeToQuote(@PathVariable String userName, @PathVariable int quoteId) {
        try {
            quoteService.addDislikeToQuote(userName, quoteId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{userName}/quotes/{quoteId}/dislike")
    public ResponseEntity<?> removeDislikeFromQuote(@PathVariable String userName, @PathVariable int quoteId) {
        try {
            quoteService.removeDislikeFromQuote(userName, quoteId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
