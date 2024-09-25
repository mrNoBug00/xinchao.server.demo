package com.xinchao.repository;

import com.xinchao.models.Role;
import com.xinchao.models.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Optional<Role> findByName(RoleEnum name);

}
