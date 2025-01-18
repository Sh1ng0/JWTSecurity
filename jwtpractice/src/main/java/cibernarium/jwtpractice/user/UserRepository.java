package cibernarium.jwtpractice.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    // Query methods personalizados

    Optional<User> findByUsername(String username);




}


