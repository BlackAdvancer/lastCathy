import java.io.IOException;
import java.sql.*;

// for reading from the command line
// for the login window


public class Member extends controller {
    int memID;
    String memName;

    public Member() {}

    public boolean validateMemberID(int inputID) {
        Statement statement;
        ResultSet result;
        int point = 0;
        try {
            memName = null;
            statement = con.createStatement();
            result = statement.executeQuery("SELECT * FROM Membership WHERE memberID = " + inputID);
            result.next();
            result.getInt("memberID");
            memName = result.getString("name");
            point = result.getInt("points");

        } catch (IOException e) {
            NotificationUI error = new NotificationUI(e.getMessage());
            error.setVisible(true);
            try {
                con.close();
                System.exit(-1);
            } catch (SQLException ex) {
                NotificationUI error = new NotificationUI(ex.getMessage());
                error.setVisible(true);
            }
        } catch (SQLException ex) {
            NotificationUI error = new NotificationUI(ex.getMessage());
            error.setVisible(true);
        }
    }
    public int checkPoint(int inputID) {
        Statement statement;
        ResultSet result;
        int point = 0;
        try {
            memName = null;
            statement = con.createStatement();
            result = statement.executeQuery("SELECT * FROM Membership WHERE memberID = " + inputID);
            result.next();
            result.getInt("memberID");
            memName = result.getString("name");
            point = result.getInt("points");

        } catch (IOException e) {
            NotificationUI error = new NotificationUI(e.getMessage());
            error.setVisible(true);
            try {
                con.close();
                System.exit(-1);
            } catch (SQLException ex) {
                NotificationUI error = new NotificationUI(ex.getMessage());
                error.setVisible(true);
            }
        } catch (SQLException ex) {
            NotificationUI error = new NotificationUI(ex.getMessage());
            error.setVisible(true);
        }
        return point;
    }

    public String getName() {
        return memName;
    }
}

