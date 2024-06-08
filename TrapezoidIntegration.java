/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trapezoidintegration;

/**
 *
 * @author BILL_0058
 */
import java.util.Arrays;

public class TrapezoidIntegration {
    // Fungsi untuk dihitung integralnya
    public static double f(double x) {
        return 4.0 / (1.0 + x * x);
    }

    // Metode integrasi trapezoid
    public static double trapezoidIntegral(int N) {
        double h = 1.0 / N;
        double sum = 0.5 * (f(0) + f(1));

        for (int i = 1; i < N; i++) {
            sum += f(i * h);
        }

        return sum * h;
    }

    // Menghitung galat RMS
    public static double calculateRMS(double[] errors) {
        double sum = 0;
        for (double error : errors) {
            sum += error * error;
        }
        return Math.sqrt(sum / errors.length);
    }

    public static void main(String[] args) {
        int[] NValues = {10, 100, 1000, 10000};
        double referencePi = 3.14159265358979323846;

        for (int N : NValues) {
            long startTime = System.nanoTime();
            double estimatedPi = trapezoidIntegral(N);
            long endTime = System.nanoTime();

            double error = Math.abs(estimatedPi - referencePi);
            double[] errors = new double[N];
            Arrays.fill(errors, error);
            double rmsError = calculateRMS(errors);

            System.out.printf("N = %d\n", N);
            System.out.printf("Estimated Pi: %.15f\n", estimatedPi);
            System.out.printf("RMS Error: %.15f\n", rmsError);
            System.out.printf("Execution Time: %d ns\n\n", (endTime - startTime));
        }
    }
}

