package account;

public abstract class Account
{
    // 멤버변수
    protected String id;                        // 계좌번호
    protected int money;                        // 잔고

    // 생성자
    protected Account()
    {
        this.id = (int)(Math.random() * 9999) + "-" + (int)(Math.random() * 9999);  // "9999-9999" 형식의 랜덤 ID
        this.money = 30000; // 기본 소지금 3만원
    }

    protected Account(String id, int money)
    {
        this.id = id;
        this.money = money;
    }

    public void showRemain()
    {
        System.out.println("현재 고객님의 잔고    : " + money + "원입니다.");
    };

    public abstract boolean pay(int money);

}
