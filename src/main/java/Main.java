import client.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        App app = App.getInstance();            // 어플리케이션 객체 가져오기
        Client client = new Client();           // 고객 객체 생성
        app.add(client);                        // 고객 어플에 추가

        String command = "-1";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   // C의 scanf와 동일하게 쓰기위한 객체, 이건 문자열만 입력받음


        while(!command.equals("0"))  // 위에서 읽어들인 문자열이 0일때 까지
        {
            System.out.println("+-------------------------------------------------------+\n"+
                               "|        배달음식 전문 앱 저기요에 오신걸 환영합니다         |\n"+
                               "|                         메뉴                           |\n"+
                               "|     1. 음식 주문하기            2. 주변 맛집 찾아보기     |\n"+
                               "|     3.                         4.                     | \n"+
                               "|     0. 종료하기                                        | \n"+
                               "+-------------------------------------------------------+\n");
            command = br.readLine();    // 여기서 한줄을 읽어준다. 실제 scanf가 실행되는 부분
            switch(command)
            {
                case "1":
                    app.orderFood(client);      // 기본 음식 주문
                    break;
                case "2":
                    app.findGoodPlace(client);  // 맛집을 찾은 뒤 주문
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "0":
                    System.out.println("저희 앱을 찾아주셔서 갑사합니다");
                    break;
                default:
                    System.out.println("다시 입력해 주십시오");
                    break;
            }
        }

        br.close();
    }

    void printMenu()
    {

    }



}
