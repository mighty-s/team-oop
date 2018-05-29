import client.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        App app = App.getInstance();            // 어플리케이션 객체 가져오기
        Client client = new Client();           // 고객 객체 생성
        app.add(client);                        // 고객 어플에 추가

        String choice;
        System.out.println("어떤 메뉴를 원하나요? \n1.중국집  | 2.치킨집 | 3.피자집 | 4.족발집");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // C의 scanf와 동일하게 쓰기위한 객체, 이건 문자열만 입력받음
        choice = br.readLine();  // 여기서 한줄을 읽어준다. 실제 scanf가 실행되는 부분
        switch (choice) {
            case "1":
                app.findNearStore("중국집", client);
                break;
            case "2":
                app.findNearStore("치킨집", client);
                break;
            case "3":
                app.findNearStore("피자집", client);
                break;

            case "4":
                app.findNearStore("족발집", client);
                break;
            default:
                break;
        }
    }
}
