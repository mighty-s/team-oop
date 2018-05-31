package store;

import client.Client;

public class Order
{
    // 멤버변수
    private Store   store;         // 주문한 가게
    private String  menuName;      // 주문한 메뉴
    private int     price;         // 주문한 가격
    private boolean isCoinPayed;   // 코인으로 결제됬는지 아닌지 나타내는 변수

    // 생성자
    public Order(){}

    public Order(Store store,  String menuName, int price, boolean isCoinPayed)
    {
        this.store       = store;
        this.menuName    = menuName;
        this.price       = price;
        this.isCoinPayed = isCoinPayed;
    }

    /**
     * 주문 내역을 보여주는 함수
     */
    public void showOrder()
    {
        String pay ;        // 지불 방식이 어떤건지 고르는 함수
        if(isCoinPayed == true)
            pay = "코인 결제";
        else
            pay = "현금 결제";

        System.out.println("--------------------------------------------\n" +
                           " * 주문한 가게  : "+ store.getStoreName() + "\n" +
                           " * 주문한 메뉴  : "+ menuName +             "\n" +
                           " * 결제한 가격  : "+ price    +             "\n" +
                           " * 결제한 방식  : "+ pay      +             "\n" +
                           "--------------------------------------------\n" );
    }

}
