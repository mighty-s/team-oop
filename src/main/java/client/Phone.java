package client;

import java.util.Optional;

public class Phone
{
    // 멤버변수
    private String location;            // 현위치
    private String phoneNumber;         // 핸드폰 번호

    // 생성자
    public Phone(){}

    public Phone(String location,String phoneNumber)
    {
        this.location = location;
        this.phoneNumber = phoneNumber;
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
