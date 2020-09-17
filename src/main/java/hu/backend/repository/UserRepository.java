package hu.backend.repository;

import hu.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    List<User> findAll();

    User findById(int id);

    User findByUsername(String name);
}
