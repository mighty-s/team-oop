package account;


public class ClientAccount extends Account
{
    // 멤버변수
    private static int tradeFee = 10;           // 수수료 ( 10%)
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
    public void showRemain()
    {
        super.showRemain();                     // C++ 에서는 Account.showRemain()
        System.out.println("현재 고객님의 보유 코인 : " + coinAccount.getCoin() + "개");
        System.out.println("코인 시세              : " + coinAccount.getExchangeRate());
    }

    /**
     *  현금으로 음식을 주문하는 함수
     *
     *  @param  money  메뉴의 가격
     *  @return bool   결제 성공시 true, 실패시 false
     */
    @Override
    public boolean pay(int money)
    {
        if(this.money > money)
        {
            this.money -= money;
            System.out.println("SYSTEM) 결제가 성공하였습니다");
            return true;
        }else{
            System.out.println("SYSTEM) 결제가 실패하였습니다");
            return false;
        }
    }

    /**
     *  코인으로 음식을 주문하는 함수
     *  @param  money  메뉴의 가격
     *  @return bool   결제 성공시 true, 실패시 false
     */
    public boolean payInCoin(int money)
    {
        return false;
    }

}
