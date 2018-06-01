
import account.CoinAccount;
import client.Client;
import org.junit.jupiter.api.Test;
import store.Store;
import store.deliver.Deliver;
import store.deliver.DroneDeliver;
import store.deliver.QuickDeliver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestProject {

    @Test
    void runTest() throws InterruptedException, IOException
    {
        CoinAccount coin = new CoinAccount();

        coin.showQuote();

        assertTrue(coin.exchange(10000));


    }
}
