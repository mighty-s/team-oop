package account;

public class CoinAccount
{
    // 멤버변수
    private static int exchangeRate = 10000;   // 코인 하나당 가격 (초깃값 만원, 모든 객체 공통이므로 static)
    private double coin;                          // 코인의 개수 --> TODO double로 만들자

    // 생성자
    public CoinAccount()
    {
        this.coin = (int)(Math.random()*10) +1;   // 모든 지갑에는 1~9 사이의 난수 개수만큼 코인이 있다.
    }


    /**
     * 현재 시세를 출력해서 사용자에게 보여준다
     */
    public void showQuote()
    {
        System.out.println("INFO) 현재 코인의 시세는 " +
                            getQuote() + "원 / 1코인 입니다."); // 여기서만 코인의 시세를 바꿔준다
    }

    /**
     * 코인을 현금으로 바꿔서 리턴해준다.
     *
     * @param money 환전할 금액
     * @return 코인이 coinCount 만큼 있을경우 -> 환전한 코인의 현금 가격 리턴
     *         코인이 부족할 경우             -> 코인은 그대로, 알림뜨고 -1 리턴
     */
    public boolean exchange(int money)
    {
        double result = Math.round(((double)money/(double)exchangeRate)*100)/100.0;
        if(coin < result )        // 가지고 있는 코인 수 < 환전하려는 코인의 금액
        {
            System.out.println("코인이 부족합니다 \n현재 보유코인 : " + coin );
            return false;
        }else{
            System.out.println("잔여코인 : " + coin);
            coin -= result;     //
            System.out.println("잔여코인 : " + coin);
            return true;
        }
    }

    // ---------------------------- private operations --------------------------------
    /**
     * 현재 코인 하나의 가격(시세)을 가져오는 함수
     *
     * @return 현재 코인 하나의 가격
     */
    private static int getQuote()
    {
        exchangeRate -= ((int)(Math.random()*3) - 3) * 1000;      // 현재 시세 +- 0 ~ 3000원 (1000원 단위로 변동)

        if(exchangeRate <= 0)           // 시세가 0보다 작게될 경우 다시 만원대로
        {
            return 10000;
        }else{                  // 아닐경우 시세 그대로 리턴
            return exchangeRate;
        }
    }



    //---------------------------- getters & setters ------------------------------------
    public double getCoin() { return coin; }

    public void setCoin(int coin) { this.coin = coin; }

    private int getExchangeRate() { return exchangeRate; }

    public void setExchangeRate(int exchangeRate) { CoinAccount.exchangeRate = exchangeRate; }
}
