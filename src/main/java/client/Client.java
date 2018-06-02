package client;

import account.Account;
import account.ClientAccount;
import store.Order;
import store.Rate;
import store.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Client
{
    // 멤버 변수
    private String             id;      // 유저 아이디
    private List<Order> orderList;      // 유저가 주문한 매뉴 리스트       (배열)
    private Phone           phone;      // 유저의 핸드폰 -> 컴포지트
    private ClientAccount account;      // 유저의 계좌   -> 컴포지트

    // 생성자
    public Client()
    {
        id        = "";
        phone     = new Phone();
        account   = new ClientAccount();
        orderList = new ArrayList<>(20);        // 배열
    }

    public Client(String id, Phone phone, ClientAccount account )
    {
        // TODO 이부분 구현해야댐!!!/
    }

    // ---------------------------- public operations --------------------------------

    /**
     * 내가 시킨 메뉴를 보여주는 함수
     * 고객의 주문 리스트를 순회하면서 값을 뽑아내준다.
     *
     */
    public void showMyOrders()
    {
        for(int i = 0 ; i < orderList.size() ; i++)
        {
           orderList.get(i).showOrder();
        }
    }

    /**
     * 음식값을 결제하는 함수
     * @param store 결제하는 가게
     * @param menu  결재할 메뉴
     * @param money 결제할 금액
     *
     * @return bool 결제 성공시 true, 실패시 false
     */
    public boolean pay(Store store, String menu, int money ) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   // scanf를 사용하기 위한 객체
        int select = 0;  // 유저의 선택을 입력받기 위한 정수, 문자열을 입력받자마자 정수로 바꾸려고 int씀

        account.showRemain();
        System.out.println("SYSTEM) 결제방식을 선택해주세요.\n\t1.현금결제 \n\t2.코인결제");
        select = Integer.parseInt(br.readLine());   //scanf 후, 문자 입력받자마자 정수로 변환

        if(select == 1)                  // 현금 결제
        {
            if(account.pay(money))       // 거래 성공시 true, 실패시 false 반화)
            {
                add(new Order(store,menu,money,false)); // 고객 주문 리스트에 주문 추가( 마지막 false는 코인 결제 여부를 나타냄 --> 현금결제)
                return true;
            }
        }else if(select == 2){           // 코인 거래

            if(account.payInCoin(money)) // 거래 성공시 true, 실패시 false 반화
            {
                add(new Order(store,menu,money,true));     // 고객 주문 리스트에 주문 추가( 마지막 true는 코인 결제 여부를 나타냄 --> 코인결제)
                return true;
            }
        }
        return false;
    }

    /**
     * 현재
     */
    public void showRemain()
    {
        this.account.showRemain();
    }

    /**
     * 평점을 매기는 함수
     * @param store  평점을 매길 가게
     */
    public void rating(Store store) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   // scanf를 사용하기 위한 객체
        int rate = 0;       //평점을 입력받을 함수

        System.out.println("SYSTEM) 저희 시스템을 이용해주셔서 감사합니다.\n" +
                           "SYSTEM) 평점을 매겨주세요! 1 ~ 5 사이의 정수");

        rate = Integer.parseInt(br.readLine());         // 읽은 문자 바로 숫자로 변환

        store.add(new Rate(store,this,rate));     // 해당 가게에 평가 추가
    }

    /**
     *  나의 현재위치를 보여주는 함수
     */
    public String getLocation()
    {
        return phone.getLocation();
    }

    // ---------------------------- private operations --------------------------------
    /**
     * 주문을 고객의 주문내역에 저장한다.
     * @param order  orderList에 주문 내역
     */
    private void add(Order order)
    {
        orderList.add(order);
    }
}
