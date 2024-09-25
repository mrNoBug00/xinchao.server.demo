package com.xinchao.seeder;

import com.xinchao.models.Role;
import com.xinchao.models.RoleEnum;
import com.xinchao.models.Status;
import com.xinchao.models.StatusEnum;
import com.xinchao.repository.RoleRepository;
import com.xinchao.repository.StatusRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;


@Component
public class StatusSeeder implements ApplicationListener<ContextRefreshedEvent> {
  private final StatusRepository statusRepository;


  public StatusSeeder(StatusRepository statusRepository) {
    this.statusRepository = statusRepository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    this.loadStatus();
  }

  private void loadStatus() {


    StatusEnum[] statusNames = new StatusEnum[] {
        StatusEnum.FOR_RENT,
            StatusEnum.RENTED,
            StatusEnum.CONFIRMED,
            StatusEnum.PENDING,
            StatusEnum.REFUSE,
            StatusEnum.CANCEL,
            StatusEnum.SIGNED
    };
    Map<StatusEnum, String> StatusDescriptionMap = Map.of(
        StatusEnum.FOR_RENT, "For rent",
        StatusEnum.RENTED, "Rented",
            StatusEnum.CONFIRMED, "Confirmed",
            StatusEnum.PENDING, "Pending",
            StatusEnum.REFUSE, "Refuse",
            StatusEnum.CANCEL, "Cancel",
            StatusEnum.SIGNED, "Signed"
    );

    Arrays.stream(statusNames).forEach((statusName) -> {
      Optional<Status> optionalStatus = statusRepository.findByName(statusName);

      optionalStatus.ifPresentOrElse(System.out::println, () -> {
        Status statusToCreate = new Status();

        statusToCreate.setName(statusName);
        statusToCreate.setDescription(StatusDescriptionMap.get(statusName));

        statusRepository.save(statusToCreate);
      });
    });
  }
}