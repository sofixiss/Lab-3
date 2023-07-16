import java.util.concurrent.CompletableFuture;
import java.util.Scanner;

public class AsyncFibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Fibonacci number to calculate: ");
        int n = scanner.nextInt();

        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> fib(n));

        System.out.println("Calculating Fibonacci number asynchronously...");

        future.thenAccept(result -> {
            System.out.println("Calculation completed.");
            System.out.println("Fibonacci number " + n + " is " + result);
        });

        while (!future.isDone()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static long fib(int n) {
        if (n <= 1) return n;
        long a = 0, b = 1;

        for (int i = 2; i <= n; i++) {
            long c = a + b;
            a = b;
            b = c;
        }

        return b;
    }
}

