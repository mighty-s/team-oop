
import org.junit.jupiter.api.Test;
import store.deliver.Deliver;
import store.deliver.DroneDeliver;
import store.deliver.QuickDeliver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProject {

    @Test
    void runTest() throws InterruptedException
    {
        Deliver deliver = new Deliver();

        //deliver.deliverStart();

        DroneDeliver d = new DroneDeliver();
        QuickDeliver q = new QuickDeliver();

        d.deliverStart();
        q.deliverStart();
    }
}
