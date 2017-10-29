package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class MainController {

    final double learningRate = 0.01;
    double[] weights;
    double error;
    Random random;
    Scanner scanner;
    Scanner scannerTest;
    int input[][];
    int inputTest[][];
    double target[];
    double targetTest[];

    public int rowCount;
    public  int columnCount;
    double globalError;

    @FXML
    public TextField TrainData;
    @FXML
    public TextField TestData;
    @FXML
    public TextField TestResult;

    public MainController() throws FileNotFoundException {

        //scanner = new Scanner(new File(data.txt""));

        rowCount = 20;
        columnCount = 35;

        random = new Random();
        weights = new double[columnCount ];
        input = new int[rowCount][columnCount];
        target = new double[rowCount];

        for (int i = 0; i < 35; i++) {
            weights[i] = random.nextInt()%2;
            System.out.println(weights[i]);
        }


    }

    public void loadData() throws FileNotFoundException {
        scanner = new Scanner(new File("trainData.txt"));
        int row = 0;
//        int rows = scanner.nextInt();
        while (scanner.hasNextInt()) {
            target[row] = scanner.nextInt();
            for (int i = 0; i < columnCount; i++) {

                input[row][i] = scanner.nextInt();
            }

            row++;
        }


    }

    public double weightSum2(double[] tmp) {
        double weightSum = 0;
        for (int i = 0; i < columnCount ; i++) {
            weightSum += weights[i] * tmp[i];
        }

//        System.out.println("weightsum = " + weightSum);

        return sigmoid(weightSum);


    }

    public void learn2() throws FileNotFoundException {
        double checkTmp = 0;
        loadData();

        for (int i = 0; i < rowCount; i++) {
            double tmp[] = new double[columnCount ];
            for (int j = 0; j < columnCount; j++) {

                tmp[j] = input[i][j];
            }

            checkTmp = weightSum2(tmp);
            error = target[i] - checkTmp;

            if (error * error > 0.25)
                adjustWeights(tmp);
            System.out.println("error = " + error);
        }


    }

    private void adjustWeights(double[] tmp) {

        for (int i = 0; i < columnCount; i++) {
            weights[i] += error * learningRate * tmp[i];
        }
    }


    public double sigmoid(double weightSum) {
        return 1 / (1 + Math.exp(-weightSum));
    }


    public void test() throws FileNotFoundException {
        scannerTest = new Scanner(new File("testData.txt"));
        double rowCountTest = 20;
        double columnCountTest = 35;
        inputTest = new int[(int) rowCountTest][(int) columnCountTest];
        targetTest = new double[(int) rowCountTest];
        double weightsumTest;
        double tmp[] = new double[35];
        double success = 0;
        double errorTest;
        double prediction = 0;

        for (int i = 0; i < rowCountTest; i++) {
            targetTest[i] = scannerTest.nextInt();
            for (int j = 0; j < 35; j++) {
                inputTest[i][j] = scannerTest.nextInt();
            }

            for (int j = 0; j < 35; j++) {
                tmp[j] = (double)inputTest[i][j];
            }


            weightsumTest = weightSum2(tmp);
            System.out.println("weightsumTest ="+ weightsumTest);
            prediction = weightsumTest;
            errorTest = targetTest[i] - prediction;
            //System.out.println();
            System.out.println("errorTest = "+errorTest);
            if (errorTest * errorTest < 0.25) {
                success += 1;
            }
        }
        double average = (success / rowCountTest) * 100;
        TestResult.setText(String.valueOf(average));
        System.out.println("successess = "+ success);


    }


}
