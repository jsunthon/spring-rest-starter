package com.tyco.repositories;

import com.tyco.models.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * Created by jamessunthonlap on 6/22/17.
 */
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Collection<Bookmark> findByAccountUsername(String username);
}
