package com.xinchao.repository;


import com.xinchao.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
    Optional<User> findByArc(String arc);
    Optional<User> findByVnId(String vnId);
    Optional<User> findByPassportNumber(String passport);
    Optional<User> findByPhoneNumber(String phone);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}

