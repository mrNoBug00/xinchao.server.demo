package com.xinchao.services;

import com.xinchao.models.Status;
import com.xinchao.models.StatusEnum;
import com.xinchao.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public Status addStatus(Status status) {
        return statusRepository.save(status);
    }

    public boolean deleteStatus(Integer id) {
        Optional<Status> status = statusRepository.findById(id);
        if (status.isPresent()) {
            statusRepository.deleteById(id);
            return true;
        } else {
            return false; // Status not found
        }
    }

    public Optional<Status> getStatusByName(StatusEnum name) {
        return statusRepository.findByName(name);
    }
    public List<Status> getAllStatuses() {
        return (List<Status>) statusRepository.findAll();
    }
}
