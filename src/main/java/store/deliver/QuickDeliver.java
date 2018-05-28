package store.deliver;

public class QuickDeliver extends Deliver
{
    // 멤버변수
    private int additionalFee;      // 추가요금

    // 생성자
    public QuickDeliver()
    {
        super();
        this.additionalFee = 2000;
    }

    @Override
    public void deliverStart() throws InterruptedException {
        int i = 0;
        System.out.println("배달을 시작합니다 -- 배달 유형 : 퀵배달 --");
        while( i < 5)
        {
            System.out.println("배달중...:" + i);
            Thread.sleep(1000);
            if(i == 2)
                System.out.println("알림 : 배달 도착까지 5분 남았습니다");
            i++;
        }
        System.out.println("배달이 완료되었습니다");
    }
}
