
import org.junit.jupiter.api.Test;
import store.Store;
import store.deliver.Deliver;
import store.deliver.DroneDeliver;
import store.deliver.QuickDeliver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProject {

    @Test
    void runTest() throws InterruptedException
    {
        Deliver deliver = new Deliver();

        Store store = new Store("중국집","홍콩반점");
        Store store2 = new Store("중국집","홍콩반점");
        Store store3 = new Store("중국집","홍콩반점");


        store.sendDeliver(1);
    }
}
