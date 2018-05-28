package account;


public class ClientAccount extends Account
{
    // 멤버변수
    private int tradeFee = 10;                  // 수수료 ( 10%)
    private CoinAccount coinAccount;            // 코인 지갑

    // 생성자
    public ClientAccount()
    {
        super();                                // C++ 에서는 Account()
        this.coinAccount = new CoinAccount();
    }

    /**
     * 부가가치세를 계산해 준다
     * @param   price   상품 가격
     * @return  상품의 부가가치세
     */
    private int getVAT(int price)
    {
        return (int)(price/100)*(100-tradeFee);
    }

    /**
     * 남은 잔고와 코인의 개수를 보여준다.
     */
    @Override
    protected void showRemain()
    {
        super.showRemain();                     // C++ 에서는 Account.showRemain()
        System.out.println("현재 고객님의 보유 코인은 : " + coinAccount.getCoin() + "개 입니다");
    }
    /**
     *
     */
    @Override
    protected void pay()
    {
        coinAccount.exchange()
    }
}
