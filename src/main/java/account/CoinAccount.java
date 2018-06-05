package account;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class CoinAccount
{
    // 멤버변수
    private static int exchangeRate = getQuote();   // 코인 하나당 가격 (초깃값 만원, 모든 객체 공통이므로 static)
    private double coin;                   // 코인의 개수

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
            System.out.println("결제 전 코인 : " + coin);
            coin -= result;
            System.out.println("결제 후 코인 : " + coin);
            return true;
        }
    }

    // ---------------------------- private operations --------------------------------
    /**
     * 현재 코인 하나의 가격(시세)을 가져오는 함수
     *
     * @return 현재 코인 하나의 가격    ( 실제 비트코인의 시세 : 약 800만원대 ) / 1000
     *         API 가져오기 실패시 -1
     */
    private static int getQuote()
    {
        try {
        BufferedReader br;

        URL url = new URL("https://api.gopax.co.kr/trading-pairs/BTC-KRW/stats");

        HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestMethod("GET");

        br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

        String coinInfo = br.readLine();
        JSONParser jsonParser = new JSONParser();

        JSONObject json = (JSONObject) jsonParser.parse(coinInfo);

        exchangeRate = Integer.parseInt(String.valueOf(json.get("close")))/1000;    // 현재 비트코인 시세 /1000
        br.close();
        return exchangeRate;

        } catch (ParseException | IOException e) {
            e.printStackTrace();
            return 0;
        }
    }


    //---------------------------- getters & setters ------------------------------------
    public double getCoin() { return coin; }
}
