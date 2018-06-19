// We need to import the java.sql package to use JDBC

import java.io.IOException;
import java.sql.*;
import java.util.Random;

// for reading from the command line

public class Manager extends controller {

    public int managerID;
    private int branch;

    public boolean validateID(int input) {
        int id;
        ResultSet rs;
        PreparedStatement ps;
        try {
            id = input;
            String connectURL = "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug";
            con = DriverManager.getConnection(connectURL, "ora_a1q1b", "a24581167");
            ps = con.prepareStatement("SELECT * FROM Clerk WHERE clerkID = ? AND type = 'Manager'");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            boolean success = true;
            if (rs.next()) {
                managerID = id;
                branch = rs.getInt("branchNumber");
                ps.close();
            } else {
                ps.close();
                return false;
            }
            return success;
        } catch (SQLException ex) {
            NotificationUI error = new NotificationUI(ex.getMessage());
            error.setVisible(true);
            try {
                con.rollback();
            } catch (SQLException ex2) {
                NotificationUI error2 = new NotificationUI(ex2.getMessage());
                error2.setVisible(true);
            }
            return false;
        }
    }

    public void showMenu() throws SQLException, IOException {
        int choice;
        boolean quit = false;

        while (!quit) {
            System.out.print("\n");
            System.out.print("1.  Show all employees\n");
            System.out.print("2.  Manage employee\n");
            System.out.print("3.  Manage item\n");
            System.out.print("4.  Manage membership\n");
            System.out.print("5.  Manage deal\n");
            System.out.print("6.  Generate report for certain period\n");
            System.out.print("7.  Get transaction since...\n");
            System.out.print("8.  Get Minimum wage for each branch\n");
            System.out.print("9.  Quit\n>> ");

            choice = Integer.parseInt(in.readLine());
            System.out.println(" ");
            switch (choice) {
                case 1:
                    showAllEmployees();
                    break;
                case 2:
//                    manageEmployeeWage();
                    break;
                case 3:
                    manageItem();
                    break;
                case 4:
                    manageMembership();
                    break;
                case 5:
                    manageDeal();
                    break;
                case 6:
                    getSalesRecord();
                    break;
                case 7:
                    getTotalTransactionAmount();
                    break;
                case 8:
                    getMinWageFromAllBranches();
                    break;
                case 9:
                    quit = true;
            }
        }

    }

    public void manageEmployeeWage(int employeeID, int wage) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("UPDATE Clerk SET wage = ? WHERE clerkID = ? AND branchNumber = ?");
        ps.setInt(2, employeeID);
        ps.setInt(3, branch);
        try {
            if (wage < 0) {
                throw new FormattingException("Wage Cannot be Negative");
            }
            ps.setInt(1, wage);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                throw new FormattingException("invalid id");
            }
            con.commit();
        } catch (FormattingException f) {
            NotificationUI error = new NotificationUI(f.getMessage());
            error.setVisible(true);
            ps.close();
        }
        ps.close();
    }

    private void showEmployee(int id) throws SQLException {
        int clerkID = id;
        String name;
        String type;
        int wage;
        int branchNumber;
        PreparedStatement ps;
        ResultSet rs;

        ps = con.prepareStatement("SELECT * FROM Clerk WHERE clerkID = ?");
        ps.setInt(1, clerkID);

        rs = ps.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();

        // get number of columns
        int numCols = rsmd.getColumnCount();

        System.out.println(" ");

        // display column names;
        for (int i = 0; i < numCols; i++) {
            // get column name and print it
            System.out.printf("%-15s", rsmd.getColumnName(i + 1));
        }

        System.out.println(" ");

        while (rs.next()) {
            // simplified output formatting; truncation may occur
            clerkID = rs.getInt("clerkID");
            System.out.printf("%-5s", clerkID);

            name = rs.getString("name");
            System.out.printf("%-5s", name);

            wage = rs.getInt("wage");
            System.out.printf("%-5s\n", wage);

            branchNumber = rs.getInt("branchNumber");
            System.out.printf("%-5s", branchNumber);

            type = rs.getString("type");
            System.out.printf("%-5s", type);
        }
    }

    public void showAllEmployees() throws SQLException {
        int clerkID;
        String name;
        String type;
        int wage;
        int branchNumber;
        Statement stmt;
        ResultSet rs;

        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM Clerk");

        // get info on ResultSet
        ResultSetMetaData rsmd = rs.getMetaData();

        // get number of columns
        int numCols = rsmd.getColumnCount();

        System.out.println(" ");

        // display column names;
        for (int i = 0; i < numCols; i++) {
            // get column name and print it
            System.out.printf("%-15s", rsmd.getColumnName(i + 1));
        }

        System.out.println(" ");

        while (rs.next()) {
            // simplified output formatting; truncation may occur
            clerkID = rs.getInt("clerkID");
            System.out.printf("%-5s", clerkID);

            name = rs.getString("name");
            System.out.printf("%-5s", name);

            wage = rs.getInt("wage");
            System.out.printf("%-5s\n", wage);

            branchNumber = rs.getInt("branchNumber");
            System.out.printf("%-5s", branchNumber);

            type = rs.getString("type");
            System.out.printf("%-5s", type);
        }
        // close the statement;
        // the ResultSet will also be closed
        stmt.close();
    }

    private void manageItem() throws IOException, SQLException {

        int choice;
        boolean quit = false;

        while (!quit) {
            System.out.print("1.  Manage item storage\n");
            System.out.print("2.  Display item storage\n");
            System.out.print("3.  Manage item price\n");
            System.out.print("4.  Go back\n>> ");

            choice = Integer.parseInt(in.readLine());

            System.out.println(" ");

            switch (choice) {
                case 1:
                    //manageItemStorage();
                    break;
                case 2:
//                    manageItemPrice();
                    break;
                case 3:
                    showMenu();
            }
        }
    }

    public boolean manageItemStorage(int id, int amount) throws SQLException{
        PreparedStatement ps;
        ps = con.prepareStatement("UPDATE Storage SET amount = ? WHERE itemID = ? AND branchNumber = ?");
        ps.setInt(2, id);
        ps.setInt(3, branch);
        ps.setInt(1, amount);

        int rowCount = ps.executeUpdate();
        if (rowCount == 0) {
            ps.close();
            return false;
        }
        ps.close();
        con.commit();
        return true;
    }

    public void manageItemPrice(int id, double price) throws FormattingException, SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("UPDATE Item A SET A.price = ? WHERE A.itemID = (SELECT S.itemID FROM Storage S WHERE A.itemID = S.itemID AND S.itemID = ? AND S.branchNumber = ?)");
        ps.setInt(2, id);
        ps.setInt(3, branch);
        if (price < 0) throw new FormattingException("Invalid price");
        ps.setDouble(1, price);
        int rowCount = ps.executeUpdate();
        if (rowCount == 0) {
            ps.close();
            throw new FormattingException("Branch does not have item");
        }
        con.commit();
        ps.close();
    }

    private void displayItemInfo(int id) throws SQLException {
        int itemID = id;
        String name;
        String type;
        int price;
        PreparedStatement ps;
        ResultSet rs;

        ps = con.prepareStatement("SELECT * FROM Item WHERE itemID = ?");
        ps.setInt(1, itemID);

        rs = ps.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();

        // get number of columns
        int numCols = rsmd.getColumnCount();

        System.out.println(" ");

        // display column names;
        for (int i = 0; i < numCols; i++) {
            // get column name and print it
            System.out.printf("%-15s", rsmd.getColumnName(i + 1));
        }

        System.out.println(" ");

        while (rs.next()) {
            // simplified output formatting; truncation may occur
            itemID = rs.getInt("itemID");
            System.out.printf("%-5s", itemID);

            name = rs.getString("name");
            System.out.printf("%-5s", name);

            price = rs.getInt("price");
            System.out.printf("%-5s\n", price);

            type = rs.getString("type");
            System.out.printf("%-5s", type);
        }
    }

    public void displayAllItems() throws SQLException {
        String     itemID;
        String     itemName;
        String     itemPrice;
        String     itemType;
        Statement  stmt;
        ResultSet  rs;

        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM Item");
        ResultSetMetaData rsmd = rs.getMetaData();
        int numCols = rsmd.getColumnCount();
        System.out.println(" ");
        for (int i = 0; i < numCols; i++) {
            System.out.printf("%-15s", rsmd.getColumnName(i+1));
        }
        System.out.println(" ");
        while(rs.next()) {
            itemID = rs.getString("itemID");
            System.out.printf("%-10.10s", itemID);

            itemName = rs.getString("name");
            System.out.printf("%-20.20s", itemName);

            itemPrice = rs.getString("price");
            System.out.printf("%-20.20s", itemPrice);

            itemType = rs.getString("type");
            System.out.printf("%-15.15s\n", itemType);

        }
        stmt.close();
    }

    private void manageMembership() {
        try {
            String name;
            String phone;
            PreparedStatement ps;
            ResultSet rs;
            int id = 0;
            int point;
            boolean current = false;
            Random r = new Random();

            while (!current) {
                id = r.nextInt(1000);
                ps = con.prepareStatement("SELECT * FROM MemberShip WHERE memberID = ?");
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (!rs.next())
                    current = true;
                ps.close();
            }

            ps = con.prepareStatement("INSERT INTO MemberShip VALUES (?,?,?,0)");
            ps.setInt(1, id);
            System.out.print("\nplease Enter Member name : \n");
            name = in.readLine();
            ps.setString(2, name);
            System.out.print("\nplease Enter Member phone number : \n");
            phone = in.readLine();
            ps.setString(3, phone);
            ps.executeUpdate();

            con.commit();
            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException se) {
            System.out.println("SQLException!");
        }

    }

    private void manageDeal() {
        int choice;
        boolean quit = false;

        try {
            while (!quit) {
                System.out.print("1.  Add new deal\n");
                System.out.print("2.  Display all deals\n");
                System.out.print("3.  Modify deal\n");
                System.out.print("4.  Go back\n>> ");

                choice = Integer.parseInt(in.readLine());

                System.out.println(" ");

                switch (choice) {
                    case 1:
//                        addNewDeal();
                        break;
                    case 2:
                        showAllDeals();
                        break;
                    case 3:
                        modifyDeal();
                }
            }
        } catch (IOException e) {
            System.out.println("IOException!");
        }
    }

    private void modifyDeal() {
        int choice;
        boolean quit = false;

        try {
            while (!quit) {
                System.out.print("1.  Add item to deal\n");
                System.out.print("2.  delete deal\n");
                System.out.print("3.  delete item from deal\n");
                System.out.print("4.  modify deal name\n>> ");
                System.out.println("5. modify deal Duration");
                System.out.println("6. modify deal percentage");


                choice = Integer.parseInt(in.readLine());

                System.out.println(" ");

                switch (choice) {
                    case 1:
//                        addItemToDeal();
                        break;
                    case 2:
//                        deleteDeal();
                        break;
                    case 3:
//                        deleteItemFromDeal();
                        break;
                    case 4:
                        //modifyDealName();
                        break;
                    case 5:
//                        modifyDealDuration();
                    case 6:
//                        modifyDealPercent();
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("IOException!");
        }
    }

    public void deleteItemFromDeal(int itemId, String dealName) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM ItemsInDeal WHERE dealName = \'" + dealName + "\' AND itemID = ?");
            ps.setInt(1, itemId);
            ps.executeUpdate();
            con.commit();
        } catch (SQLException se) {
            NotificationUI error = new NotificationUI(se.getMessage());
            error.setVisible(true);
        }

    }

    public void addItemToDeal(int itemId, String dealName, double percent) throws FormattingException{
        try {
            if(!searchItem(itemId)) throw new FormattingException("Item not Found");
            if(searchDeal(dealName)) throw new FormattingException("Deal Name not Found");
            PreparedStatement ps = con.prepareStatement("INSERT INTO ItemsInDeal VALUES (?,?,?)");
            ps.setInt(1, itemId);
            ps.setString(2, dealName);
            ps.setDouble(3, percent);
            ps.executeUpdate();
            con.commit();
            ps.close();
        } catch (SQLException s) {
            NotificationUI error = new NotificationUI(s.getMessage());
            error.setVisible(true);
        }
    }

    private boolean searchItem(int itemId){
        try {
            Statement s = con.createStatement();
            ResultSet res = s.executeQuery("SELECT * FROM Item WHERE itemID = " + itemId);
            if (!res.next())
                return false;
        } catch (SQLException s) {
            NotificationUI error = new NotificationUI(s.getMessage());
            error.setVisible(true);
        }
        return true;
    }

    private boolean searchDeal(String dealName) {
        try {
            PreparedStatement s = con.prepareStatement("SELECT * FROM Deal WHERE dealName = \'" + dealName + "\'");
            ResultSet res = s.executeQuery();
            if (!res.next())
                return false;
        } catch (SQLException s) {
            NotificationUI error = new NotificationUI(s.getMessage());
            error.setVisible(true);
        }
        return true;

    }

    public void showAllDeals() {
        String dealName;
        String duration;
        int itemID;
        double percentage;

        Statement stmt;
        ResultSet rs;
        try {
            stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT d.dealName AS dealName, d.STARTDATE as startdate, d.ENDDATE as enddate, id.itemId as itemId, id.percentage as persentage FROM Deal d, ItemsInDeal id WHERE d.dealName = id.dealName");
            // get info on ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();
            // get number of columns
            int numCols = rsmd.getColumnCount();
            System.out.println(" ");
            // display column names;
            for (int i = 0; i < numCols; i++) {
                // get column name and print it
                System.out.printf("%-15s", rsmd.getColumnName(i + 1));
            }
            System.out.println(" ");
            while (rs.next()) {
                // for display purposes get everything from Oracle
                // as a string
                // simplified output formatting; truncation may occur
                itemID = rs.getInt("itemId");
                System.out.printf("%-10.10s", itemID);
                dealName = rs.getString("dealName");
                System.out.printf("%-20.20s", dealName);
                Timestamp startdate = rs.getTimestamp("startdate");
                System.out.printf("%-20.20s", startdate);
                Timestamp enddate = rs.getTimestamp("enddate");
                System.out.printf("%-20.20s", enddate);
                percentage = rs.getDouble("persentage");
                System.out.printf("%-15.15s\n", percentage);
            }
            // close the statement;
            // the ResultSet will also be closed
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());
        }

    }

    public void addNewDeal(String dealName, String start, String end) {
        Timestamp startDate;
        Timestamp endDate;
        boolean canExit = false;
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("INSERT INTO Deal VALUES (?,?,?)");
            ps.setString(1, dealName);
            int[] timeStart = parseTimestamp(start);
            int[] timeEnd = parseTimestamp(end);
            startDate = new java.sql.Timestamp(timeStart[0], timeStart[1], timeStart[2], 0, 0, 0, 0);
            endDate = new java.sql.Timestamp(timeEnd[0], timeEnd[1], timeEnd[2], 0, 0, 0, 0);
            ps.setTimestamp(2, startDate);
            ps.setTimestamp(3, endDate);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            NotificationUI error = new NotificationUI(ex.getMessage());
            error.setVisible(true);
            try {
                // undo the insert
                con.rollback();
                System.exit(-1);
            } catch (SQLException ex2) {
                NotificationUI error2 = new NotificationUI(ex2.getMessage());
                error2.setVisible(true);
                System.exit(-1);
            }
        }
    }

    private void getSalesRecord() throws IOException, SQLException {
        int receiptNumber;
        java.sql.Timestamp purchaseTime;
        java.sql.Timestamp purchaseDate;

        int inputYear;
        int inputMonth;
        int inputDay;
        double totalPrice;
        int clerkID;
        int branchNumber;

        PreparedStatement ps;
        ResultSet rs;

        System.out.print("\nEnter starting year: ");
        inputYear = Integer.parseInt(in.readLine());

        System.out.print("\nEnter starting Month: ");
        inputMonth = Integer.parseInt(in.readLine());

        System.out.print("\nEnter starting Day: ");
        inputDay = Integer.parseInt(in.readLine());

        java.sql.Timestamp startDate = new java.sql.Timestamp(inputYear, inputMonth, inputDay, 0, 0, 0, 0);

        System.out.print("\nEnter ending year: ");
        inputYear = Integer.parseInt(in.readLine());

        System.out.print("\nEnter ending Month: ");
        inputYear = Integer.parseInt(in.readLine());

        System.out.print("\nEnter ending Day: ");
        inputYear = Integer.parseInt(in.readLine());

        java.sql.Timestamp endDate = new java.sql.Timestamp(inputYear, inputMonth, inputDay, 0, 0, 0, 0);

        ps = con.prepareStatement("SELECT * FROM Purchase WHERE purchaseTime >= ? AND purchaseTime <= ? AND branchNumber = ?");
        ps.setTimestamp(1, endDate);
        ps.setTimestamp(2, startDate);

        ps.setInt(3, branch);

        rs = ps.executeQuery();
        // get info on ResultSet
        ResultSetMetaData rsmd = rs.getMetaData();
        // get number of columns
        int numCols = rsmd.getColumnCount();

        System.out.println(" ");

        // display column names;
        for (int i = 0; i < numCols; i++) {
            // get column name and print it
            System.out.printf("%-15s", rsmd.getColumnName(i + 1));
        }

        System.out.println(" ");
        while (rs.next()) {
            receiptNumber = rs.getInt("receiptNumber");
            System.out.printf("%-10.10s", receiptNumber);

            purchaseTime = rs.getTimestamp("purchaseTime");
            System.out.printf("%-20.20s", purchaseTime);

            totalPrice = rs.getDouble("totalPrice");
            System.out.printf("%-15.15s", totalPrice);

            clerkID = rs.getInt("clerkID");
            System.out.printf("%-15.15s", clerkID);

            branchNumber = rs.getInt("branchNumber");
            System.out.printf("%-15.15s\n", branchNumber);

            // close the statement;
            // the ResultSet will also be closed
        }
        ps.close();
    }

    public void deleteDeal(String name) {
        try {
            Statement ps = con.createStatement();
            ps.executeUpdate("DELETE FROM (SELECT * FROM Deal d WHERE d.dealName = \'" + name + "\')");
            con.commit();
        } catch (SQLException s) {
            NotificationUI error = new NotificationUI(s.getMessage());
            error.setVisible(true);
        }
    }

    public void modifyDealPercent(String name, int itemId, double percentage) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE Deal SET percentage = ? WHERE itemID = ? AND dealName = \'" + name + "\'");
            ps.setDouble(1, percentage);
            ps.setInt(2, itemId);
            ps.executeUpdate();
            con.commit();
        } catch (SQLException se) {
            System.out.println("update failed!");
        }

    }

    public void modifyDealDuration(String name, String start, String end) {
        try {
            int[] timeStart = parseTimestamp(start);
            int[] timeEnd = parseTimestamp(end);
            java.sql.Timestamp startDate = new java.sql.Timestamp(timeStart[0], timeStart[1], timeStart[2], 0, 0, 0, 0);
            java.sql.Timestamp endDate = new java.sql.Timestamp(timeEnd[0], timeEnd[1], timeEnd[2], 0, 0, 0, 0);
            PreparedStatement ps = con.prepareStatement("UPDATE Deal d SET d.startDate = ? AND d.endDate = ? WHERE d.dealName = \'" + name + "\'");
            ps.setTimestamp(1, startDate);
            ps.setTimestamp(2, endDate);
            ps.executeUpdate();
            con.commit();
        } catch (SQLException s) {
            NotificationUI error = new NotificationUI(s.getMessage());
            error.setVisible(true);
        }
    }

    public static int[] parseTimestamp(String time) {
        // xx-xx-xx
        int[] array = new int[3];
        array[0] = Integer.parseInt(time.substring(0,2))+2000-1900;
        System.out.print(array[0]);
        array[1] = Integer.parseInt(time.substring(3, 5));
        System.out.print(array[1]);
        array[2] = Integer.parseInt(time.substring(6, 8));
        System.out.print(array[2]);
        return array;
    }

    private void getTotalTransactionAmount() throws SQLException, IOException {
        Double totalPrice;
        int count;
        int inputYear;
        int inputMonth;
        int inputDay;
        PreparedStatement ps;
        ResultSet rs;
        System.out.print("\nEnter starting year: ");
        inputYear = Integer.parseInt(in.readLine());
        System.out.print("\nEnter starting month: ");
        inputMonth = Integer.parseInt(in.readLine());
        System.out.print("\nEnter starting day:");
        inputDay = Integer.parseInt(in.readLine());
        java.sql.Timestamp startDate = new java.sql.Timestamp(inputYear, inputMonth, inputDay, 0, 0, 0, 0);
        ps = con.prepareStatement("SELECT SUM(totalPrice) AS SUM, COUNT(receiptNumber) AS CON FROM Purchase WHERE purchaseTime <= ? AND branchNumber = ?");
        //ps = con.prepareStatement("SELECT SUM(totalPrice) AS SUM, COUNT(receiptNumber) AS CON FROM Purchase WHERE purchaseTime >= '2015-01-01 0:0:0' AND branchNumber = 05");
        ps.setTimestamp(1, startDate);
        ps.setInt(2, branch);
        System.out.println("Gay1");
        rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int numCols = rsmd.getColumnCount();
        for (int i = 0; i < numCols; i++) {
            // get column name and print it
            System.out.printf("%-15s", rsmd.getColumnName(i + 1));
            System.out.println("Gay2");
        }
        System.out.println(" ");
        while (rs.next()) {
            System.out.println("Gay3");
            totalPrice = rs.getDouble("SUM");
            System.out.printf("%-10s", totalPrice);
            count = rs.getInt("CON");
            System.out.printf("%-5s", count);
        }
        ps.close();
    }

    private void getMinWageFromAllBranches() throws SQLException {
        int wage;
        int branch;
        int clerkID;
        Statement stmt;
        ResultSet rs;
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT MIN(wage), clerkID, branchNumber FROM Clerk GROUP BY branchNumber, clerkID");
        ResultSetMetaData rsmd = rs.getMetaData();
        // get number of columns
        int numCols = rsmd.getColumnCount();
        System.out.println(" ");
        // display column names;
        for (int i = 0; i < numCols; i++) {
            // get column name and print it
            System.out.printf("%-15s", rsmd.getColumnName(i + 1));
        }
        System.out.println(" ");
        while (rs.next()) {
            // simplified output formatting; truncation may occur
            wage = rs.getInt("MIN(wage)");
            System.out.printf("%-5s", wage);
            clerkID = rs.getInt("clerkID");
            System.out.printf("%-5s", clerkID);
            branch = rs.getInt("branchNumber");
            System.out.printf("%-5s\n", branch);
        }
        // close the statement;
        // the ResultSet will also be closed
        stmt.close();
    }
}
