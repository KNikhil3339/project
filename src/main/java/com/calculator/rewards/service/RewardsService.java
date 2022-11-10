package com.calculator.rewards.service;

import com.calculator.rewards.model.Rewards;


public interface RewardsService {

  Rewards getRewardsByCustomerId(Long customerId);
}
