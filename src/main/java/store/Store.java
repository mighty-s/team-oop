package store;

import store.deliver.Deliver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Store
{
    // 멤버변수
    private Queue<? extends Deliver> deliver_ready;         // 대기중인 배달부 목록  (큐)
    private Queue<? extends Deliver> deliver_work;          // 배달중인 배달부 목록  (큐)
    private List<Rate> rateList;                            // 평점 리스트       (배열)
    private List<Menu> orderList;                           // 평점 리스트       (배열)
    private String storeType;                               // 가게 종류
    private String storeName;                               // 가게 이름
    private String location;                                // 가게 위치

    // 생성자
    public Store(String storeType, String storeName)
    {
        this.storeType = storeType;
        this.storeName = storeName;
        rateList  = new ArrayList<>(20);
        orderList = new ArrayList<>(20);
        deliver_ready = new LinkedList<>();
        deliver_work  = new LinkedList<>();
    }

    /**
     *  가게의 메뉴를 보여주는 함수
     */
    public void showMenu()
    {

    }

    /**
     * 배달을 시작하는 함수
     */
    public void sendDeliver()
    {
        Deliver deliver = deliver_ready.poll();       // 대기중인 드라이버를 큐에서 하나 꺼낸다.
        try{
            deliver.deliverStart();
        }catch(InterruptedException e){
            e.printStackTrace();
            System.out.println("배달 실패");
        }

    }

    /**
     * 배달이 종료될때의 함수
     */
    public void deliverEnd()
    {

    }

}
