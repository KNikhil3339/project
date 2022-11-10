package com.calculator.rewards.model;

public class Rewards {

  private long customerId;
  private long oneMonthRewards;
  private long twoMonthsRewards;
  private long threeMonthsRewards;
  private long totalRewards;

  public long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

  public long getOneMonthRewards() {
    return oneMonthRewards;
  }

  public void setOneMonthRewards(long oneMonthRewards) {
    this.oneMonthRewards = oneMonthRewards;
  }

  public long getTwoMonthsRewards() {
    return twoMonthsRewards;
  }

  public void setTwoMonthsRewards(long twoMonthsRewards) {
    this.twoMonthsRewards = twoMonthsRewards;
  }

  public long getThreeMonthsRewards() {
    return threeMonthsRewards;
  }

  public void setThreeMonthsRewards(long threeMonthsRewards) {
    this.threeMonthsRewards = threeMonthsRewards;
  }

  public long getTotalRewards() {
    return totalRewards;
  }

  public void setTotalRewards(long totalRewards) {
    this.totalRewards = totalRewards;
  }
}
