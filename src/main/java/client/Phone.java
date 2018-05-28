package client;

import java.util.Optional;

public class Phone
{
    // 멤버변수
    private String location;            // 현위치
    private String phoneNumber;         // 핸드폰 번호

    // 생성자
    public Phone()
    {
        String location[] = {"혜화동","명동","장충동","신당동","신사동","논현동"};
        this.location = location[(int)(Math.random()*location.length)];
        this.phoneNumber = "010-" + (int)(Math.random()*8999)+1000 +"-"+(int)(Math.random()*8999)+1000;
                            // 핸드폰번호는 " 010-xxxx-xxxx" 형식
    }

    /**
     * 현재의 위치를 가져오는 함수
     *
     * @return 현재의 위치
     */
    public String getLocation()
    {
        return this.location;
      /*return Optional.ofNullable(location)
                       .orElse("위치 없음"); */
    }

    /**
     * 핸드폰 번호를 가져오는 함수
     *
     * @return 핸드폰 번호
     */
    public String getPhoneNumber()
    {
        return this.phoneNumber;
        /*return Optional.ofNullable(location)
                         .orElse("번호 없음");*/
    }


}
