package store;

import client.Client;
import store.Store;

public class Rate
{
    // 멤버변수
    private Store  store;       // 가게 이름            //  TODO -> 이거 문자열로 변경
    private Client client;      // 평점을 매긴 고객이름  //  TODO -> 이거 문자열로 변경
    private int    rate;        // 평점

    // 생성자들
    @Deprecated
    public Rate()
    {

    }

    public Rate(Store store, Client client,int rate)
    {
        this.store  = store;
        this.client = client;
        this.rate   = rate;
    }

    /* --------------- getters & setters ------------------  */

    /**
     * 평점를 리턴하는 함수
     * @return 평점
     */
    public int getRate()
    {
        return this.rate;
    }

}
