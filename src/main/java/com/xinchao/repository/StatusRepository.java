package com.xinchao.repository;


import com.xinchao.models.Status;
import com.xinchao.models.StatusEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StatusRepository extends CrudRepository<Status, Integer> {

  Optional<Status> findByName(StatusEnum name);

}
