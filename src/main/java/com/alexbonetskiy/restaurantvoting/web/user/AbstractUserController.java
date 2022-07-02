package com.alexbonetskiy.restaurantvoting.web.user;


import com.alexbonetskiy.restaurantvoting.model.User;
import com.alexbonetskiy.restaurantvoting.repository.UserRepository;
import com.alexbonetskiy.restaurantvoting.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static com.alexbonetskiy.restaurantvoting.util.ValidationUtil.checkModification;


@Slf4j
public abstract class AbstractUserController {

    @Autowired
    protected UserRepository repository;


    public ResponseEntity<User> get(int id) {
        log.info("get {}", id);
        return ResponseEntity.of(repository.findById(id));
    }

    public void delete(int id) {
        log.info("delete {}", id);
        checkModification(repository.delete(id), id);
    }

    protected User prepareAndSave(User user) {
        return repository.save(UserUtil.prepareToSave(user));
    }
}