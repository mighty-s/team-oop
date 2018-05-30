import client.Client;
import store.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
        clientList = new LinkedList<>();        // 배열
        storeList  = new LinkedList<>();        // 배열
        profit = 0;

        String store[][] = {
                {"중국집","홍콩반점"},{"중국집","몽고반점"},{"중국집","반점반점"},
                {"중국집","한강반점"},{"중국집","국일관" }, {"중국집","만리장성"},
                {"치킨집","또레오레"},{"치킨집","굽네치킨"}, {"치킨집","치킨공주"},
                {"치킨집","BBQ"    },{"치킨집","조은치킨"} , {"치킨집","위잉치킨"},
                {"피자집","피자나라"},{"피자집","피자스쿨"} , {"피자집","피자헛"},
                {"피자집","도미노피자"},{"피자집","굳피자"} , {"피자집","피자마루"},
                {"족발집","이런족발"},{"족발집","족발나라"} , {"족발집","족발장인"},
                {"족발집","족발만세"},{"족발집","굳족발"} , {"족발집","족발전문"},
        };

        for(int i = 0; i < store.length ; i++)
        {
            storeList.add(new Store(store[i][0],store[i][1]));  // 위의 배열 정보대로 storeList에 가게 생성 후 삽입
        }
    }

    private static class SingleTon
    {
        private static final App instance = new App();
    }

    public static App getInstance()
    {
        return SingleTon.instance;
    }

    // ----------------------------------- 멤버 함수 --------------------------------------------------

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
     * 사용자로부터 음식을 주문받는 함수
     * @param client
     */
    public void orderFood(Client client) throws IOException
    {
        String storeName = findNearStore(client,0);     // 평점에 상관없이 근처 음식점 데이터를 가져옴으로
        Store store = null;                                      //
        if(storeName == null)return; // 주변에 검색된 가게가 없으면 자동 종료

        // storeList를 순회하다 사용자가 선택한 가게 이름을 가진 store객체를 찾으면 store 변수에 대입하고 루프 탈출
        for(int i = 0 ; i < storeList.size() ; i++)
        {
            if(storeList.get(i).getStoreName().equals(storeName)) // storeList의 i번째 가게 객체의 이름과, 유저가 선택한 가게 이름이 같을 때,
            {
                store = storeList.get(i);
                break;
            }
        }

        store.makeOrder(client);
    }

    /**
     *  맛집 찾기, 사용자 근처에 있는 음식점중 맛집을 찾아준다
     * @param     client      사용자
     *
     */
    public void findGoodPlace(Client client) throws IOException
    {
        String store = findNearStore(client,3.0);     // 3.0 이상 평점의 주변 음식점 데이터를가져온다.

        if(store == null)return; // 주변에 검색된 가게가 없으면 자동 종료
    }


    /**
     * 유저 근처에 있는 가게 목록을 찾고, 유저가 선택한 가게 이름을 출력해주는 함수
     * @param client        사용자
     * @param rateLimit     검색시 평점 x점 이상이라는 제한을 두기 위해 만듬
     * */
    private String findNearStore(Client client,double rateLimit) throws IOException
    {
        Map<Integer,String> selectedStore = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("어떤 메뉴를 원하나요? \n1.중국집  | 2.치킨집 | 3.피자집 | 4.족발집");
        String choice = br.readLine();      // scanf
        switch(choice)
        {
            case "1":
                choice = "중국집";
                break;
            case "2":
                choice = "치킨집";
                break;
            case "3":
                choice = "피자집";
                break;
            case "4":
                choice = "족발집";
                break;
            default:
                break;
        }

        System.out.println("** 당신의 위치** : " + client.getLocation() + "\n인근 가게----------");

        int mapCount = 1;       // map에 1,2,3.. 순서대로 집어넣기 위한 변수
        for(int i = 0; i < storeList.size(); i++) // storeList 배열을 처음부터 끝까지 순회
        {
            if(storeList.get(i).getLocation().equals(client.getLocation())          // 조건 1. 가게의 위치와 사용자의 위치가 같고,
                    && storeList.get(i).getStoreType().equals(choice)               // 조건 2. 사용자가 선택한 가게 타입과, 가게의 업종이 같고,
                    && storeList.get(i).getAvgRate() >= rateLimit  )                // 조건 3. 해당 가게의 평균 평점이 제시한 평균 평점보다 높을때,
            {
                selectedStore.put(mapCount,storeList.get(i).getStoreName());        //찾은 가게 이름을 map에 <순서,가게이름> 형태로 저장한다
                                                                                    //ex) 1. 가게이름 몽고반점,  --> selectedStore 에는 (1, "몽고반점")이 들어가 있음
                System.out.println(mapCount + " : 가게 이름 : \t\t" + storeList.get(i).getStoreName()
                        + " || 평균 평점 \t\t:" + storeList.get(i).getAvgRate());
                mapCount++;
            }
        }// end for i

        if(mapCount == 1)   //검색된게 아무것도 없으면
        {
            System.out.println("\n주변에 해당 가게가 없습니다. 다시골라주세요.");
            return null;
        }

        System.out.println("\n원하는 가게 번호를 골라주세요");
        choice = br.readLine(); // scanf

        // choice는 문자열이므로 -> 정수로 변환해줘야한다. 자바에서는 Integer.parseInt(문자열) 사용시 문자가 정수로 변환됨
        // C++ 에서 string을 int로 변환하려면 stoi(문자열)함수를 사용해야한다.

        //br.close(); //버퍼 닫기
        return selectedStore.get(Integer.parseInt(choice)); // 맵에 해당 키에 대한 값을 꺼내서 리턴한다
        // ex) choice에서 1을 선택하면, (1, "몸고반점")에가서 "몸고반점"값을 리턴한다.
    }// end findNearStore


    /**
     * 고객을 App에 등록하는 함수
     * @prarm  client  고객
     *
     */
    public void add(Client client)
    {
        clientList.add(client);
    }

    /**
     * 가게를 App에 등록하는 함수
     * @param  store    상점
     *
     */
    public void add(Store store)
    {
        storeList.add(store);
    }

}
