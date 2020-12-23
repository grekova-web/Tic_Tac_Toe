import java.util.Random;
import java.util.Scanner;

public class TicTacGame {
    final char CROSS = 'x';
    final char ZERO = 'o';
    final char NULL = '.';
    char[][] table;
    Random random;
    Scanner scanner;

    public static void main(String[] args) {
        new TicTacGame().game();
    }

    TicTacGame() {
        random = new Random();
        scanner = new Scanner(System.in);
        table = new char[3][3];
    }

    void game() {
        makeField();
        while (true) {
            movePlayer();
            if (isWinner(CROSS)) {
                System.out.println("Поздравляем с победой!");
                break;
            }
            if (isTableFull()) {
                System.out.println("Ой.. Победила дружба!");
                break;
            }
            printTable();
            moveAI();
            System.out.println("Ход компьютера:");
            printTable();
            if (isWinner(ZERO)) {
                System.out.println("Вас победил искусственный интеллект! Восстание машин началось...");
                break;
            }
            if (isTableFull()) {
                System.out.println("Ой.. Победила дружба!");
                break;
            }
        }
        System.out.println("Спасибо за игру! Пока!");
        printTable();
    }

    void makeField() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                table[row][col] = NULL;
    }

    void printTable() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++)
                System.out.print(table[row][col] + " ");
            System.out.println();
        }
    }

    void movePlayer() {
        int x, y;
        do {
            System.out.println("Введите ваш ход. Координаты X и Y:");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        table[y][x] = CROSS;
    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 3|| y >= 3)
            return false;
        return table[y][x] == NULL;
    }

    void moveAI() {
        int x, y;
        x = random.nextInt(3);
        y = random.nextInt(3);
        while (!isCellValid(x, y)) {
            x = random.nextInt(3);
            y = random.nextInt(3);
        }
        table[y][x] = ZERO;
    }

    boolean isWinner(char dot) {
        for (int i = 0; i < 3; i++)
            if ((table[i][0] == dot && table[i][1] == dot &&
                    table[i][2] == dot) ||
                    (table[0][i] == dot && table[1][i] == dot &&
                            table[2][i] == dot))
                return true;
        return (table[0][0] == dot && table[1][1] == dot &&
                table[2][2] == dot) ||
                (table[2][0] == dot && table[1][1] == dot &&
                        table[0][2] == dot);
    }

    boolean isTableFull() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (table[row][col] == NULL)
                    return false;
        return true;
    }
}
