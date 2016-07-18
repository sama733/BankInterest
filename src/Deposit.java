import java.math.BigDecimal;


public class Deposit implements Comparable<Deposit> {

    private int customerNumber;
    private BigDecimal depositBalance;
    private int durationInDays;
    private BigDecimal interest;
    private DepositType depositType;

    public BigDecimal getInterest() {
        return interest;

    }

    public void setInterest(BigDecimal interest) {

        this.interest = interest;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public BigDecimal getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(BigDecimal depositBalance) {

        this.depositBalance = depositBalance;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public DepositType getDepositType() {
        return depositType;
    }

    public void setDepositType(DepositType depositType) {

        this.depositType = depositType;
    }


    @Override
    public int compareTo(Deposit deposit) {
        return deposit.getInterest().compareTo(interest);
    }
}
