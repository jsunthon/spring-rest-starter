package com.tyco.controllers;

import com.tyco.exceptions.UserNotFoundException;
import com.tyco.models.Bookmark;
import com.tyco.repositories.AccountRepository;
import com.tyco.repositories.BookmarkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("users/{userId}/bookmarks")
public class BookmarkRestController {
    private static final Logger logger = LoggerFactory.getLogger(BookmarkRestController.class);

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private AccountRepository accountRepository;

    // Gets all the bookmarks for a given user id
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Bookmark> readBooksmarks(@PathVariable String userId) {
        this.validateUser(userId);
        return bookmarkRepository.findByAccountUsername(userId);
    }

    // Get the bookmark with the given id, assuming the user accessing it exists
    @RequestMapping(method = RequestMethod.GET, value = "/{bookmarkId}")
    public Optional<Bookmark> readBookmark(@PathVariable String userId, @PathVariable Long bookmarkId) {
        this.validateUser(userId);
        return bookmarkRepository.findById(bookmarkId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@PathVariable String userId, @RequestBody Bookmark input) {
        this.validateUser(userId);
        return this.accountRepository
                .findByUsername(userId)
                .map(account -> {
                    Bookmark result = bookmarkRepository.save(new Bookmark(account,
                            input.getUri(), input.getDescription()));
                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(result.getId()).toUri();
                    logger.info("Constructing uri loc: " + location.toString());
                    return ResponseEntity.created(location).build();
                })
                .orElse(ResponseEntity.noContent().build());
    }

    // Makes sure the user id exists
    private void validateUser(String userId) {
        this.accountRepository.findByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
}
