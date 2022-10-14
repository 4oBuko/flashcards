package com.chornobuk.flashcardsapi.repositories;

import com.chornobuk.flashcardsapi.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
