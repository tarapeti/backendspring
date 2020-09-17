package hu.backend.controller;

import hu.backend.dto.UserCredentials;
import hu.backend.entity.User;
import hu.backend.exception.InvalidUserCredentialsException;
import hu.backend.security.JwtTokenServices;
import hu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenServices jwtTokenServices;

    public UserController(AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
    }

    @PostMapping(path = "/authentication")
    public ResponseEntity login(@RequestBody UserCredentials data) {
        try {
            String name = data.getUsername();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(name, roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("name", name);
            model.put("roles", roles);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new InvalidUserCredentialsException("Invalid username/password supplied");
        }
    }
    @GetMapping(path = "/user")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping(path = "/user/{id}")
    public @ResponseBody
    User getById(@RequestBody int id){
        return userService.getById(id);
    }

    @PostMapping("/user")
    public void register(@RequestBody User user) {
        userService.save(user);
    }


    @DeleteMapping(path = "/user/{id}")
    @Transactional
    public @ResponseBody
    void deleteUser(@RequestParam int userId) {
        userService.deleteById(userId);
    }

}

