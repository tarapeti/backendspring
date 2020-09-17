package hu.backend.service;



import hu.backend.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(int id);

    void save(User user);

    void deleteById(int userId);

    User getByUsername(String name);
}
