package store.deliver;

import store.Store;

public class Deliver
{
    // 멤버변수
    protected boolean onDeliver;    // 배달중인지 아닌지

    public Deliver()
    {
        this.onDeliver = false;
    }

    public void deliverStart() throws InterruptedException
    {
        this.onDeliver = true;
        int i = 0;
        System.out.println("배달을 시작합니다 -- 배달 유형 : 일반 --");
        while( i < 7)
        {
            System.out.println("배달중... :" + i);
            Thread.sleep(1000);
            if(i == 3)
                System.out.println("알림 : 배달 도착까지 5분 남았습니다");
            i++;
        }
        System.out.println("배달이 완료되었습니다");
        this.onDeliver = false;
    }
}
