package com.alexbonetskiy.restaurantvoting.web.user;


import com.alexbonetskiy.restaurantvoting.model.User;
import com.alexbonetskiy.restaurantvoting.repository.UserRepository;
import com.alexbonetskiy.restaurantvoting.util.UserUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;


@Slf4j
@AllArgsConstructor
public abstract class AbstractUserController {


    protected UserRepository repository;


    public ResponseEntity<User> get(int id) {
        log.info("get {}", id);
        return ResponseEntity.of(repository.findById(id));
    }

    public void delete(int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }

    protected User prepareAndSave(User user) {
        return repository.save(UserUtil.prepareToSave(user));
    }
}