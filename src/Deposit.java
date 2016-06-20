


import java.math.BigDecimal;


public class Deposit  implements Comparable<Deposit> {

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
        try { if(depositBalance.compareTo(BigDecimal.ZERO)<0 )

                throw new Exception("not valid input");
            } catch (Exception e) {
                System.out.println(" مقدار موجودی وارد شده صحیح نمی باشد");
            }

        this.depositBalance = depositBalance;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        try { if(durationInDays <= 0)
                throw new Exception("not valid input");
            } catch (Exception e) {
                System.out.println(" تعداد روزهای وارد شده صحیح نمی باشد");
            }
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
        return -1* this.getInterest().compareTo(deposit.getInterest());

        /*public int compareTo(Deposit deposit) {
        * if(this.getInterest()> deposit.getInterested())
        * return 1;
        * else if (this.getInterest() < deposit.getInterest())
        *return -1;
        * else
        * return 0;
        * */
    }
}
