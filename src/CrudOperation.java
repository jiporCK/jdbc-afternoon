import java.sql.*;
import java.util.Scanner;

public class CrudOperation {
    // Define url, username, password
    // Create connection instance, register with driver manager
    // Prepare sql statement
    // ResultSet to hold the result
    // close connection
    private final static String URL = "jdbc:postgresql://localhost:5432/library_db";
    private final static String USERNAME = "postgres";
    private final static String PASSWORD = "12345";

    private final static Scanner SCANNER = new Scanner(System.in);

    public void createBook() throws SQLException {
        System.out.print("Enter book title: ");
        String title = SCANNER.nextLine();
        System.out.print("Enter book author: ");
        String author = SCANNER.nextLine();

        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        String sql = """
                insert into books (title, author)
                values (?, ?)
                """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, author);

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Inserted successfully");
        } else {
            System.out.println("Failed to insert");
        }
    }

    public void findByTitle() throws SQLException {
        System.out.print("Enter book title: ");
        String title = SCANNER.nextLine();

        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        String sql = """
                select * from books where title ilike ?
                """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + title + "%");

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Book book = new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author")
            );
            System.out.println(book);
        }
    }

}
