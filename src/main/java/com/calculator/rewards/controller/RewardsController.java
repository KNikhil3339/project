package com.calculator.rewards.controller;

import com.calculator.rewards.entity.CustomerDTO;
import com.calculator.rewards.model.Rewards;
import com.calculator.rewards.repository.CustomerRepository;
import com.calculator.rewards.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class RewardsController {

  @Autowired
  RewardsService rewardsService;

  @Autowired
  CustomerRepository customerRepository;

  @GetMapping(value = "/{customerId}/rewards", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Rewards> getRewardsByCustomerId(
      @PathVariable("customerId") Long customerId) {
    CustomerDTO customer = customerRepository.findByCustomerId(customerId);
    if (customer == null) {
      throw new RuntimeException("Invalid / Missing customer Id ");
    }
    Rewards customerRewards = rewardsService.getRewardsByCustomerId(customerId);
    return new ResponseEntity<>(customerRewards, HttpStatus.OK);
  }

}
