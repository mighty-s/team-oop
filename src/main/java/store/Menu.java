package store;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
            case "피자집":
                menuList.put("포테이토 피자"  ,12000 );
                menuList.put("페퍼로니 피자 " ,12000 );
                menuList.put("치즈크러스트 피자" ,13000 );
                break;
            case "족발집":
                menuList.put("족발 - 소" , 9000 );
                menuList.put("족발 - 중 ", 11000 );
                menuList.put("족발 - 대" , 18000 );
                break;
            default:
                break;
        }
    }

    /**
     * 메뉴의 정보를 출력해주는 함수
     * @return 해당 매뉴 정보가 들어있는 map 객체
     */
    public Map<Integer,String> showMenu()
    {
        Map<Integer,String> menuNameMap = new HashMap<>();      //  < 1부터 시작하는 숫자, "메뉴"> 형식으로 이루어진 map

        Iterator<String> it = menuList.keySet().iterator();     // menuList의 키값("메뉴 이름")을 set으로 받아온다.

        int menuNum = 1;
        while(it.hasNext())
        {
            String key = it.next(); //map 의 key (메뉴 이름)
            System.out.println(menuNum + ". " + key + " :  " + menuList.get(key));
        }

        it = menuList.keySet().iterator();                      // iterator은 일회용, 재사용해야한다. 다시 키 set을 받아온다.
        menuNum = 1;
        while(it.hasNext())
        {
            String key = it.next();
            menuNameMap.put(menuNum,key);
            menuNum++;
        }

        return menuNameMap;
    }

    public Map<String,Integer> getMenuList()
    {
        return this.menuList;
    }

}


