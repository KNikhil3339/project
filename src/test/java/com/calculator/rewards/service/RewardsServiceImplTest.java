package com.calculator.rewards.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.calculator.rewards.entity.TransactionDTO;
import com.calculator.rewards.model.Rewards;
import com.calculator.rewards.repository.TransactionRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit test for {@link RewardsServiceImpl}
 */
@RunWith(MockitoJUnitRunner.class)
public class RewardsServiceImplTest {

  @InjectMocks
  RewardsServiceImpl rewardsService;

  @Mock
  private TransactionRepository transactionRepository;

  @Test
  public void getRewardsByCustomerIdTest() {
    List<TransactionDTO> transactions = new ArrayList<>();
    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setCustomerId(1L);
    transactionDTO.setTransactionId(2L);
    transactionDTO.setTransactionAmount(1000.00);
    transactionDTO.setTransactionDate(Timestamp.valueOf(LocalDateTime.now().minusMonths(1)));
    transactions.add(transactionDTO);
    when(transactionRepository.findAllByCustomerIdAndTransactionDateBetween(any(), any(), any()))
        .thenReturn(transactions);
    Rewards reward = rewardsService.getRewardsByCustomerId(1L);
    assertNotNull(reward);
    Assert.assertEquals(reward.getCustomerId(), 1L);
  }
}
