import client.Client;
import store.Store;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author sight
 * @since 2015-05-22
 *
 * 이 클래스는 싱글톤 패턴을 따릅니다.
 * 객체를 만드는 행위는 getInstance 로만 합니다.
 */
public class App
{
    // Member Variables
    private List<Client> clientList;       // 고객의 정보
    private List<Store>  storeList;        // 가게의 정보
    private int profit;                    // 잔고

    // constructor & singleton
    private App() {
        clientList = new LinkedList<>();
        storeList  = new LinkedList<>();
        profit = 0;
    }

    private static class SingleTon
    {
        private static final App instance = new App();
    }

    public static App getInstance()
    {
        return SingleTon.instance;
    }

    /**
     * 가게들로부터 수수료를 받는 함수
     */
    public void getCommision()
    {

    }

    /**
     *  메뉴를 선택을 하면 아무 가게에서나 배달을 시켜주는 함수
     */
    public void randomCall()
    {

    }

    /**
     *  맛집 추천
     */
    public void matzipchuchon()
    {

    }

    /**
     * @prarm  client 고객
     */
    public void add(Client client)
    {
        clientList.add(client);
    }

    /**
     * @param  store 상점
     */
    public void add(Store store)
    {
        storeList.add(store);
    }

}
