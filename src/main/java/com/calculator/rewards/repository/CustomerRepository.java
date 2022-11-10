package com.calculator.rewards.repository;

import com.calculator.rewards.entity.CustomerDTO;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerDTO, Long> {

  CustomerDTO findByCustomerId(Long customerId);
}
