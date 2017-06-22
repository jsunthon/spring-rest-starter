package com.tyco.repositories;

import com.tyco.models.Greeting;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jamessunthonlap on 6/21/17.
 */

public interface GreetingRepository extends CrudRepository<Greeting, Long> {

}
