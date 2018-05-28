package account;

public class StoreAccount extends Account
{
    // 맴버변수
    private static int appFee = 3;              //어플 수수료 (비율) -> 공통이므로 static ( 3% )

    // 생성자
    public StoreAccount()
    {
        super();
    }


    /**
     * 수수료를 지불하는 함수
     */
    @Override
    protected void pay()
    {

    }

    /**
     * 계좌에 남은 잔고를 보여주는 함수
     */
    @Override
    protected void showRemain()
    {
        super.showRemain();
    }
}
