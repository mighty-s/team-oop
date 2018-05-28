package client;

import account.Account;
import account.ClientAccount;

public class Client
{
    // 멤버 변수
    private String             id;      // 유저 아이디
    private int              rate;      // ?? 모르겟음
    private Phone           phone;      // 유저의 핸드폰 -> 컴포지트
    private ClientAccount account;      // 유저의 계좌   -> 컴포지트

    // 생성자      TODO 이부분 해야댐
    public Client()
    {
        phone = new Phone();
    }

    public Client(String id, Phone phone, ClientAccount account )
    {

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
     * 음식을 주문하는 함수
     */
    public void order()
    {
        account.pay();
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
}
