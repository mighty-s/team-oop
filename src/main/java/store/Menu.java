package store;

import java.util.HashMap;
import java.util.Map;

public class Menu       // 메뉴판 하나에 대한 클래스
{
    // 멤버 변수
    private Map<String,Integer> menuList;  // 메뉴의 목록을 가지고 있는 리스트
                                   // <key,value> 형태이며, 여기선 <"음식이름",가격> 형태로 되어있다.
    // 생성자
    public Menu(){}

    public Menu(String storeType)
    {
        menuList = new HashMap<>();
        switch(storeType)
        {
            case "중국집":
                menuList.put("짜장면",6000 );
                menuList.put("짬뽕"  ,6000 );
                menuList.put("간짬뽕",8000 );
                menuList.put("간짜장",8000 );
                menuList.put("탕수육",10000);
                break;
            case "치킨집":
                menuList.put("후라이드 "        ,12000 );
                menuList.put("양념치킨 "        ,12000 );
                menuList.put("양념반 후라이드반" ,13000 );
                break;
            case "파자집":
                menuList.put("포테이토 피자"  ,12000 );
                menuList.put("페퍼로니 피자 " ,12000 );
                menuList.put("치즈크러스트 피자" ,13000 );
                break;
            case "족발집":
                menuList.put("족발 - 소" ,9000 );
                menuList.put("족발 - 중 ",11000 );
                menuList.put("족발 - 대" ,18000 );
                break;
            default:
                break;
        }
    }

    /**
     * 메뉴의 정보를 출력해주는 함수
     */
    public void showMenu()
    {



        /*
        menuList.forEach( (k,v) ->
        {
            System.out.println(k + " : " + v + "원");
        });
        */
    }
}


