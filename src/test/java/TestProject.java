
import account.CoinAccount;
import client.Client;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import store.Store;
import store.deliver.Deliver;
import store.deliver.DroneDeliver;
import store.deliver.QuickDeliver;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestProject {

    @Test
    void runTest() throws InterruptedException, IOException
    {
        BufferedReader br;

        URL url = new URL("https://api.gopax.co.kr/trading-pairs/BTC-KRW/stats");
                        // 실제 고팍스 코인 시세 정보 주소, 위의 파라미터는 고팍스 API 주소
                        // https://www.gopax.co.kr/
        HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestMethod("GET");

        br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

        String coinInfo = br.readLine();

        System.out.println(coinInfo);


        JSONParser jsonParser = new JSONParser();

        //JSON데이터를 넣어 JSON Object 로 만들어 준다.
        try {
            JSONObject json = (JSONObject) jsonParser.parse(coinInfo);

            System.out.println(json.get("close"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
