import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CrudOperation operation = new CrudOperation();

        while (true) {
            System.out.println("""
                    1. Add book
                    2. Find by title
                    3. Update book by id
                    4. Delete book by id
                    5. List all books
                    0. Exit""");
            System.out.print("Enter an option: ");
            int op = Integer.parseInt(scanner.nextLine());

            if (op == 0) break;

            try {
                switch (op) {
                    case 1 -> operation.createBook();
                    case 2 -> operation.findByTitle();
                    default -> System.out.println("Invalid");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

    }

}
