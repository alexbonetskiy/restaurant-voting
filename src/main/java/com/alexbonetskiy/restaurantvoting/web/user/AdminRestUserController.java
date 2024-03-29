package com.alexbonetskiy.restaurantvoting.web.user;


import com.alexbonetskiy.restaurantvoting.model.User;
import com.alexbonetskiy.restaurantvoting.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.alexbonetskiy.restaurantvoting.util.ValidationUtil.assureIdConsistent;
import static com.alexbonetskiy.restaurantvoting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminRestUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminRestUserController extends AbstractUserController{

     public static final String REST_URL = "/api/admin/users";


     @Override
     @GetMapping("/{id}")
     public ResponseEntity<User> get(@PathVariable int id) {
          return super.get(id);
     }

     @Override
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     @CacheEvict(value = "users", allEntries = true)
     public void delete(@PathVariable int id) {
          super.delete(id);
     }

     @GetMapping
     public List<User> getAll() {
          log.info("getAll");
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name", "email"));
     }


     @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<User> createWithLocation(@Valid @RequestBody User user) {
          log.info("create {}", user);
          checkNew(user);
          User created = prepareAndSave(user);
          URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                  .path(REST_URL + "/{id}")
                  .buildAndExpand(created.getId()).toUri();
          return ResponseEntity.created(uriOfNewResource).body(created);
     }

     @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
     @ResponseStatus(HttpStatus.NO_CONTENT)
     @CachePut(value = "users")
     public User update(@Valid @RequestBody User user, @PathVariable int id) {
          log.info("update {} with id={}", user, id);
          assureIdConsistent(user, id);
          return prepareAndSave(user);
     }

     @GetMapping("/by-email")
     public ResponseEntity<User> getByEmail(@RequestParam String email) {
          log.info("getByEmail {}", email);
          return ResponseEntity.of(repository.findByEmailIgnoreCase(email));
     }

}


