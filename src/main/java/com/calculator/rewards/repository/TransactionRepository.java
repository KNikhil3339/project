package com.calculator.rewards.repository;

import com.calculator.rewards.entity.TransactionDTO;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TransactionRepository extends CrudRepository<TransactionDTO, Long> {

  List<TransactionDTO> findAllByCustomerId(Long customerId);

  List<TransactionDTO> findAllByCustomerIdAndTransactionDateBetween(Long customerId,
      Timestamp from, Timestamp to);
}
