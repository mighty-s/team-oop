import client.Client;

public class Main
{
    public static void main(String[]args)
    {
        App app = App.getInstance();
        Client client = new Client();

        app.add(client);
    }

    void init()
    {


    }
}
