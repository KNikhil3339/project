package com.calculator.rewards.service;

import com.calculator.rewards.constants.Constants;
import com.calculator.rewards.entity.TransactionDTO;
import com.calculator.rewards.model.Rewards;
import com.calculator.rewards.repository.TransactionRepository;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardsServiceImpl implements RewardsService {


  @Autowired
  TransactionRepository transactionRepository;

  @Autowired
  public RewardsServiceImpl(
      TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public static Long getRewardsPerMonth(List<TransactionDTO> transactions) {
    return transactions.stream().map(transaction -> calculateRewards(transaction))
        .collect(Collectors.summingLong(r -> r.longValue()));
  }

  public static Long calculateRewards(TransactionDTO t) {
    if (t.getTransactionAmount() > Constants.firstRewardLimit
        && t.getTransactionAmount() <= Constants.secondRewardLimit) {
      return Math.round(t.getTransactionAmount() - Constants.firstRewardLimit);
    } else if (t.getTransactionAmount() > Constants.secondRewardLimit) {
			return Math.round(t.getTransactionAmount() - Constants.secondRewardLimit) * 2
					+ (Constants.secondRewardLimit - Constants.firstRewardLimit);
		} else {
			return 0l;
		}

  }

  public static Timestamp getDateBasedOnOffSetDays(int days) {
    return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
  }

  public Rewards getRewardsByCustomerId(Long customerId) {

    Timestamp oneMonthTimeStamp = getDateBasedOnOffSetDays(Constants.daysInMonths);
    Timestamp twoMonthsTimeStamp = getDateBasedOnOffSetDays(2 * Constants.daysInMonths);
    Timestamp threeMonthsTimeStamp = getDateBasedOnOffSetDays(3 * Constants.daysInMonths);

    List<TransactionDTO> oneMonthTransactions = transactionRepository
        .findAllByCustomerIdAndTransactionDateBetween(
            customerId, oneMonthTimeStamp, Timestamp.from(Instant.now()));
    List<TransactionDTO> twoMonthsTransactions = transactionRepository
        .findAllByCustomerIdAndTransactionDateBetween(customerId, twoMonthsTimeStamp,
            oneMonthTimeStamp);
    List<TransactionDTO> threeMonthsTransactions = transactionRepository
        .findAllByCustomerIdAndTransactionDateBetween(customerId, threeMonthsTimeStamp,
            twoMonthsTimeStamp);

    Long oneMonthRewards = getRewardsPerMonth(oneMonthTransactions);
    Long twoMonthsRewards = getRewardsPerMonth(twoMonthsTransactions);
    Long threeMonthsRewards = getRewardsPerMonth(threeMonthsTransactions);

    Rewards customerRewards = new Rewards();
    customerRewards.setCustomerId(customerId);
    customerRewards.setOneMonthRewards(oneMonthRewards);
    customerRewards.setTwoMonthsRewards(twoMonthsRewards);
    customerRewards.setThreeMonthsRewards(threeMonthsRewards);
    customerRewards.setTotalRewards(
        oneMonthRewards + twoMonthsRewards + threeMonthsRewards);

    return customerRewards;

  }

}
