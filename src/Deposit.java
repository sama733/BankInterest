import java.math.BigDecimal;

public class Deposit {

    private int customerNumber;
    private BigDecimal depositBalance;
    private int durationInDays;
    private DepositType depositType;


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

    public void setDurationInDays(int durationDays) {
        this.durationInDays = durationDays;
    }

    public void setDepositType(DepositType depositType) {
        this.depositType = depositType;
    }

    public DepositType getDepositType() {
        return depositType;
    }


    public BigDecimal payedInterest() {
        return null;
    }



}
