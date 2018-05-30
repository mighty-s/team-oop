package client;

import account.Account;
import account.ClientAccount;
import store.Order;
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
    private int              rate;      // ?? 모르겟음
    private Phone           phone;      // 유저의 핸드폰 -> 컴포지트
    private ClientAccount account;      // 유저의 계좌   -> 컴포지트
    private List<Order> orderList;      // 유저가 주문한 매뉴 리스트       (배열)


    // 생성자
    public Client()
    {
        phone = new Phone();
        account = new ClientAccount();
        orderList = new ArrayList<>(20);        // 배열
    }

    public Client(String id, Phone phone, ClientAccount account )
    {
        // TODO 이부분 구현해야댐!!!
    }

    /**
     * 내가 시킨 메뉴를 보여주는 함수
     */
    public void showMenu()
    {

    }

    /**
     * 내가 평점을매긴것들을 보여주는 함수
     */
    public void showRate()
    {

    }

    /**
     * 음식값을 결제하는 함수
     * @param store 결제하는 가게
     * @param menu  결재할 메뉴
     * @param money 결제할 금액
     *
     * @return bool 결제 성공시 true, 실패시 false
     */
    public boolean pay(Store store, String menu,int money ) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   // scanf를 사용하기 위한 객체
        int select = 0;  // 유저의 선택을 입력받기 위한 정수, 문자열을 입력받자마자 정수로 바꾸려고 int씀
        account.showRemain();
        System.out.println("SYSTEM) 결제방식을 선택해주세요.\n1.현금결제 \n2.코인결제");
        select = Integer.parseInt(br.readLine());   //scanf 후, 문자 입력받자마자 정수로 변환

        if(select == 1)
        {
            return account.pay(money);          // 거래 성공시 true, 실패시 false 반화
        }else if(select == 2){
            return account.payInCoin(money);    // 거래 성공시 true, 실패시 false 반화
        }
        return false;
    }

    /**
     * 평점을 매기는 함수
     */
    public void rating()
    {

    }

    /**
     * 뭐하는건지 모르겟음
     */
    public void returnID()
    {

    }

    /**
     *  나의 현재위치를 보여주는 함수
     */
    public String getLocation()
    {
        return phone.getLocation();
    }
}
