package com.tyco;

import com.tyco.models.Account;
import com.tyco.models.Bookmark;
import com.tyco.repositories.AccountRepository;
import com.tyco.repositories.BookmarkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by jamessunthonlap on 6/21/17.
 */
@SpringBootApplication
public class Application {
    public static final String DESCRIPTION_DEFAULT = "A description";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner init(AccountRepository accountRepository,
                                  BookmarkRepository bookmarkRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                String[] names = "jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(",");
                for (String name : names) {
                    Account account = accountRepository.save(new Account(name, "Password"));
                    // two bookmarks for the new account created
                    bookmarkRepository.save(new Bookmark(account, "http://bookmark.com/1/" + name, DESCRIPTION_DEFAULT));
                    bookmarkRepository.save(new Bookmark(account, "http://bookmark.com/2/" + name, DESCRIPTION_DEFAULT));
                }
            }
        };
    }
}
