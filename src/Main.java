import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        SQLConnector sqlConnector = new SQLConnector();
        Connection con = sqlConnector.Connectington();
        tui tui = new tui(con);
        tui.startTui();

    }



}
