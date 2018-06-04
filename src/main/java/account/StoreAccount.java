package account;

public class StoreAccount extends Account
{
    // 생성자
    public StoreAccount()
    {
        super();
        this.money = 10000;
    }


    /**
     * 수수료를 지불하는 함수
     * @param  commisionRate   입력받을 수수료
     * @return bool            수수료 지불 성공시 true, 실패시 false
     */
    @Override
    public boolean pay(int commisionRate) {
        return false;
    }

    /**
     * 계좌에 남은 잔고를 보여주는 함수
     */
    @Override
    public void showRemain()
    {
        super.showRemain();
    }


    public void add(int money)
    {
        this.money += money;
    }

    public int getMoney()
    {
        return this.money;
    }
}
