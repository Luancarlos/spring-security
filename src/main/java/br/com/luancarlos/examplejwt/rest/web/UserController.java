package br.com.luancarlos.examplejwt.rest.web;

import br.com.luancarlos.examplejwt.entity.User;
import br.com.luancarlos.examplejwt.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<>(this.userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody User user) throws Exception {
        if (user.getId() != null) {
            throw new Exception("Usuario já existe");
        }

        if (user.getPassword() == null) {
            throw new Exception("Senha obrigatoria");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return new ResponseEntity<>(this.userRepository.save(user), HttpStatus.CREATED);
    }
}
