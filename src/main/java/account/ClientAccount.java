package account;


public class ClientAccount extends Account
{
    // 멤버변수
    private CoinAccount coinAccount;            // 코인 지갑

    // 생성자
    public ClientAccount()
    {
        super();                                // C++ 에서는 Account()
        this.money = 30000;
        this.coinAccount = new CoinAccount();
    }

    // ---------------------------- public operations --------------------------------
    /**
     * 남은 잔고와 코인의 개수를 보여준다.
     */
    @Override
    public void showRemain()
    {
        super.showRemain();                     // C++ 에서는 Account.showRemain()
        System.out.println("SYSTEM) 현재 고객님의 보유 코인   : " + coinAccount.getCoin() + "개");
        coinAccount.showQuote();                // 이후 현재 코인의 시세를 보여준다
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
        boolean result = coinAccount.exchange(money);
                // 해당 금액만큼 돈을 환전하고 결과 받아옴 ( 성공시 true, 실패시 false )

        if(result == true)      // 잘되면 true , 실패하면 false 반환
        {
            return true;
        }else{
            return false;
        }
    }
}
