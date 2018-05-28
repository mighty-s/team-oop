package account;

public class CoinAccount
{
    // 멤버변수
    private static int exchangeRate = 10000;   // 코인 하나당 가격 (초깃값 만원, 모든 객체 공통이므로 static)
    private int coin;                          // 코인의 개수 --> TODO double로 만들자

    // 생성자
    public CoinAccount()
    {
        this.coin = (int)Math.random()*10 +1;   // 모든 지갑에는 1~9 사이의 난수 개수만큼 코인이 있다.
    }

    /**
     * 코인을 현금으로 바꿔서 리턴해준다.
     *
     * @param coinCount 환전할 코인의 개수
     * @return 코인이 coinCount 만큼 있을경우 -> 환전한 코인의 현금 가격 리턴
     *         코인이 부족할 경우             -> 코인은 그대로, 알림뜨고 -1 리턴
     */
    public int exchange(int coinCount)
    {
        if(coin - coinCount < 0 )        // 환전을 원하는 코인이 가지고 있는 코인보다 클 때
        {
            System.out.println("코인이 부족합니다 \n현재 보유코인 : " + coin );
            return -1;
        }else{
            coin =- coinCount;
            return coinCount*exchangeRate;
        }

    }

    /**
     * 현재 시세를 출력해서 사용자에게 보여준다
     */
    public void showQuote()
    {
        System.out.println("현재 코인의 시세는 " + getQuote() + " per 1코인 입니다."); // 여기서만 코인의 시세를 바꿔준다
    }

    /**
     * 현재 코인 하나의 가격(시세)을 가져오는 함수
     * @return 현재 코인 하나의 가격
     */
    private static int getQuote()
    {
        int quote = exchangeRate - ((int)Math.random()*10 - 3) * 100;      // 현재 시세 +- 0 ~ 3000원 (1000원 단위로 변동)

        if(quote <= 0)           // 시세가 0보다 작게될 경우 다시 만원대로
        {
            return 10000;
        }else{                  // 아닐경우 시세 그대로 리턴
            return quote;
        }
    }

//-------- getters & setters ------------
    public int getCoin() { return coin; }

    public void setCoin(int coin) { this.coin = coin; }

    public int getExchangeRate() { return exchangeRate; }

    public void setExchangeRate(int exchangeRate) { this.exchangeRate = exchangeRate; }
}
