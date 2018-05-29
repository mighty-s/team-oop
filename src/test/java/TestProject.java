
import client.Client;
import org.junit.jupiter.api.Test;
import store.Store;
import store.deliver.Deliver;
import store.deliver.DroneDeliver;
import store.deliver.QuickDeliver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProject {

    @Test
    void runTest() throws InterruptedException, IOException
    {
        App app = App.getInstance();            // 어플리케이션 객체 가져오기
        Client client = new Client();           // 고객 객체 생성
        String choice;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // C의 scanf와 동일하게 쓰기위한 객체, 이건 문자열만 입력받음

        app.add(client);                        // 고객 어플에 추가
        System.out.println("어떤 메뉴를 원하나요? \n1.중국집  | 2.치킨집 | 3.피자집 | 4.족발집");


        choice = "2";//br.readLine();  // 여기서 한줄을 읽어준다. 실제 scanf가 실행되는 부분
        switch(choice)
        {
            case "1":
                app.findNearStore("중국집",client);
                break;
            case "2":
                app.findNearStore("치킨집",client);
                break;
            case "3":
                app.findNearStore("피자집",client);
                break;

            case "4":
                app.findNearStore("족발집",client);
                break;
            default:
                break;
        }






        br.close();
    }
}
