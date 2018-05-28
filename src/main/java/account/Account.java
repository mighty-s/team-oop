package account;

public abstract class Account
{
    // 멤버변수
    private String id;                        // 계좌번호
    private int money;                        // 잔고

    // 생성자
    protected Account()
    {
        this.id = (int)Math.random() * 9999 + "-" + (int)Math.random() * 9999;  // "9999-9999" 형식의 랜덤 ID
        System.out.println(this.id);
        this.money = 0;
    }

    protected Account(String id, int money)
    {
        this.id = id;
        this.money = money;
    }


    protected void showRemain()
    {
        System.out.println("현재 고객님의 잔고는      : " + money + "원입니다.\n");
    };

    protected abstract void pay();

}
