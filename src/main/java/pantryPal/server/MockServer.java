package pantryPal.server;

public class MockServer implements IServer{
    public static boolean status = false;

    public static void turnOff() {
        status = false;
    }

    public static void turnOn() {
        status = true;
    }

    public static boolean getStatus() {
        return status;
    }
}
