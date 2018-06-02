package store;

import account.StoreAccount;
import client.Client;
import store.deliver.Deliver;
import store.deliver.DroneDeliver;
import store.deliver.QuickDeliver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Driver;
import java.util.*;

public class Store
{
    // 멤버변수
    private Queue<Deliver>  deliver_ready;   // 대기중인 배달부 목록  (큐)
    private List<Rate>      rateList;        // 평점 리스트       (배열)
    private Menu            menu;            // 가게의 메뉴
    private String          storeType;       // 가게 종류
    private String          storeName;       // 가게 이름
    private String          location;        // 가게 위치
    private StoreAccount    account;         // 가게의 소유금

    // 생성자
    public Store(String storeType, String storeName)
    {
        String location[] = {"혜화동","명동","신사동"};
        rateList       = new ArrayList<>(20);        // 배열
        deliver_ready  = new LinkedList<>();                     // 큐
        menu           = new Menu(storeType);
        account        = new StoreAccount();
        this.storeType = storeType;
        this.storeName = storeName;
        this.location  = location[(int)(Math.random()*location.length)];       //location 배열 중의 랜덤한 값중 하나

        for(int i = 0 ; i < 4 ; i++)        // 기본적으로 모든 가게는 배달부가 종류별로 3개씩 있다.
        {                                   // .offer : 큐에 데이터를 집어넣기
            deliver_ready.offer(new Deliver());
            deliver_ready.offer(new DroneDeliver());
            deliver_ready.offer(new QuickDeliver());
        }

        for(int i = 0; i < 5 ; i++)         // 기본적으로 모든 가게는 평점을 5개씩 가지고 있다.
        {
            int rate =(int)(Math.random()*3)+2;   // 2 ~ 5이하의 난수
            rateList.add(new Rate(this,new Client(),rate));     //rateList에 평점을 넣는다. 인자 : (자기 자신의 가게 객체, 새로운 고객, 위의 난수)
        }
    }



    // ----------------------------------------------- public operation --------------------------------------------------------------------

    /**
     * 사용자로부터 주문을 받는 함수
     * @param client 주문한 고객
     */
    public void makeOrder(Client client) throws IOException, InterruptedException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   // scanf를 사용하기 위한 객체
        Map<Integer,String> menuSelect = menu.showMenu();                           // map에 < 1, "첫번째 메뉴"> 이런 식으로 저장되어있음 key는 콘솔에 띄워지는 메뉴 번호
        Map<String,Integer> menuMap = menu.getMenuList();                           // map에 <"메뉴", 가격> 형식으로 저장되어있는 데이터를 가져온다.
        int select = 0;                                                       // 콘솔에서 유저가 선택한 메뉴 번호
        int price = 0;                                                              // 메뉴의 가격
        int deliverType = 0;                                                        // 어떤 배달원을 고를지 정하는 변수

        System.out.println("SYSTEM) 원하는 메뉴번호를 선택해주세요");
        select = Integer.parseInt(br.readLine());                           // sacnf (문자열 입력받음) --> 받자마자 정수로 변환
        price = menuMap.get(menuSelect.get(select));                        // 금액은 map객체에서 입력받은 숫자(키)로부터 꺼내온다.

        deliverType =  pickDeliver();                                               // 여기서 배달 유형을 입력 받는다
        price += getDriverFee(deliverType);                                         // 배달 종류에 따라 추가요금 계산

        if(client.pay(this,menuSelect.get(select),price))                     // 고객이 메뉴 결제를 결제한다. 인자 : (해당 가게 객체, 메뉴, 금액), 성공시 true, 실패시 false 리턴
        {
            account.add(price);                                                     // 결제 성공시, 음식 가격을 가게 소지금에 추가
            sendDeliver(deliverType);                                               //      배달 시작
            client.rating(this);
        }
        // 실패시 아무 행동도 하지 않고 함수 종료
    }

    /**
     * 가게의 위치를 가져오는 함수
     * @return 가게 위치
     */
    public String getLocation() { return this.location; }

    /**
     * 가게의 이름을 가져오는 함수
     * @return 가게의 이름
     */
    public String getStoreName() { return this.storeName; }

    /**
     * 가게의 타입을 가져오는 함수
     * @return 가게의 업종 타입
     */
    public String getStoreType() { return this.storeType; }

    /**
     * 가게의 평균 평점을 구하는 함수
     * @return  가게의 평균 평점( 소수점 둘째자리에서 반올림 ex)3.5 )
     */
    public double getAvgRate()
    {
        double avgRate = 0;
        for(int i = 0; i < rateList.size() ; i++)
        {
            avgRate += rateList.get(i).getRate();   //평균을 구하기 위해 평점값을 계속 더해준다.
        }

        avgRate = avgRate/rateList.size();  // 평균 계산식 : 총 평점값 / 평점수
        return Math.round(avgRate*10)/10.0; //소수점 2째자리에서 반올림
    }

    /**
     * 고객이 평점을 매긴걸 rateList에 저장하는 함수 -> client클래스에서 호출된다.
     * @param   rate    고객이 평가한 평점
     */
    public void add(Rate rate)
    {
        rateList.add(rate);
    }

    // ------------------------------ private operations -----------------------------------

    /**
     * 사용자가 어떤 배달부를 사용할 것인지 입력을 받아 정하는 클래스
     * @return   int    1 -> 일반 배달부 . 2 -> 퀵 배달부, 3 -> 드론 배달부
     */
    private int pickDeliver() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   // scanf를 사용하기 위한 객체
        System.out.println("SYSTEM) 어떤 배달부를 고르시겟어요? \n" +
                           "\t1. 일반 배달부  -> 가격 : 무료 \n" +
                           "\t2. 퀵 배달부    -> 가격 : " + QuickDeliver.getAdditionalFee() + "\n" +
                           "\t3. 드론 배달부  -> 가격 : " + DroneDeliver.getAdditionalFee());
        String choice = br.readLine();  // scanf

        return Integer.parseInt(choice);    // 선택한 번호를 정수로 바꾸고 반환
    }

    /**
     * 배달부 타입에 따라 추가 요금을 붙여주는 함수
     * @param deliverType  드라이버의 종류
     * @return
     */
    private int getDriverFee(int deliverType)
    {
        switch(deliverType)
        {
            case 1:             // 일반일때 배달요금 없음
                return 0;
            case 2:             // 퀵배달 추가요금 계산
                return QuickDeliver.getAdditionalFee();
            case 3:             // 드론 배달 추가요금 계산
                return DroneDeliver.getAdditionalFee();
            default:
                return 0;
        }
    }

    /**
     * 사용자가 원하는 타입의 배달부를 대기중인 배달부 큐에서 꺼내는 함수
     *
     * @param   deliverType 어떤 배달을 시킬지에 대한 값   ( 1. 일반 배달부, 2. 퀵배달, 3.드론배달 )
     * @return  해당 유형의 배달부
     */
    private void sendDeliver(int deliverType) throws InterruptedException
    {
        switch(deliverType)                                 // .peek : 큐의 제일 앞을 확인, .offer : 큐에 데이터를 집어넣기 ,
        {                                                   // .poll : 큐의 제일 앞의 값을 삭제하고 그 값을 반환
            case 1: // 일반 배달부를 고를 때
                while(!(deliver_ready.peek() instanceof DroneDeliver
                        || !(deliver_ready.peek() instanceof QuickDeliver))) // 큐 제일 앞이 QuickDeliver나 DroneDeliver이 아닐때 까지
                {                                                            // 계속 뽑고 다시 넣기를 반복
                    Deliver deliver = deliver_ready.poll();
                    deliver_ready.offer(deliver);
                }
                deliver_ready.poll()
                             .deliverStart();
                break;
            case 2: //  퀵 배달을 고를 때
                while(!(deliver_ready.peek() instanceof QuickDeliver))  // 큐 제일 앞이 QuickDeliver가 아닐때 까지 계속 뽑고 다시 넣기를 반복
                {
                    Deliver deliver = deliver_ready.poll();
                    deliver_ready.offer(deliver);
                }
                deliver_ready.poll()
                             .deliverStart();
                break;
            case 3: // 드론배달을 고를 때
                while(!(deliver_ready.peek() instanceof DroneDeliver)) // 큐 제일 앞이 DroneDeliver가 아닐때 까지 계속 뽑고 다시 넣기를 반복
                {
                    Deliver deliver = deliver_ready.poll();
                    deliver_ready.offer(deliver);
                }
                deliver_ready.poll()
                             .deliverStart();
                break;
        }
    }
}
