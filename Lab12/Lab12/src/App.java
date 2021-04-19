import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application

{
    private TextField tfAnnualInterestRate = new TextField();
    private TextField tfNumberOfYears = new TextField();
    private TextField tfLoanAmount = new TextField();
    private TextField tfMounthlyPayment = new TextField();
    private TextField tfTotalPayment = new TextField();
    private Button btCalculate = new Button("Calculate");
    private Button btClear = new Button("Clear");
    private Button btLoad = new Button("Load");
    private Button btSave = new Button("Save");

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create UI
        GridPane gridPane = new GridPane();
        HBox buttonLine = new HBox(btSave, btLoad, btClear);
        buttonLine.setSpacing(10);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("Annual Interest Rate:"), 0, 0);
        gridPane.add(tfAnnualInterestRate, 1, 0);
        gridPane.add(new Label("Number of Years:"), 0, 1);
        gridPane.add(tfNumberOfYears, 1, 1);
        gridPane.add(new Label("Loan Amount:"), 0, 2);
        gridPane.add(tfLoanAmount, 1, 2);
        gridPane.add(new Label("Monthly Payment:"), 0, 3);
        gridPane.add(tfMounthlyPayment, 1, 3);
        gridPane.add(new Label("Total Payment:"), 0, 4);
        gridPane.add(tfTotalPayment, 1, 4);
        gridPane.add(btCalculate, 1, 5);
        gridPane.add(buttonLine, 0, 5);

        // Set properties for UI
        gridPane.setAlignment(Pos.CENTER);
        tfAnnualInterestRate.setAlignment(Pos.BOTTOM_RIGHT);
        tfNumberOfYears.setAlignment(Pos.BOTTOM_RIGHT);
        tfLoanAmount.setAlignment(Pos.BOTTOM_RIGHT);
        tfMounthlyPayment.setAlignment(Pos.BOTTOM_RIGHT);
        tfTotalPayment.setAlignment(Pos.BOTTOM_RIGHT);
        tfMounthlyPayment.setEditable(false);
        tfTotalPayment.setEditable(false);
        GridPane.setHalignment(btCalculate, HPos.RIGHT);

        // Process events
        btSave.setOnAction(e -> btOnSave());
        btLoad.setOnAction(e -> btOnLoad());
        btClear.setOnAction(e -> btOnClear());
        btCalculate.setOnAction(e -> btOnCalculate());

        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 400, 250);
        primaryStage.setTitle("LoanCalculator");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void btOnSave() {
        File file = new File("Data.dat");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream output = new ObjectOutputStream(fos);
            Loan loan = new Loan();
            loan.setAnnualInterestRate(Double.parseDouble(tfAnnualInterestRate.getText()));
            loan.setNumberOfYears(Integer.parseInt(tfNumberOfYears.getText()));
            loan.setLoanAmount(Double.parseDouble(tfLoanAmount.getText()));
            output.writeObject(loan);
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void btOnLoad() {
        File file = new File("Data.dat");
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream input = new ObjectInputStream(fis);
            Loan loan = (Loan) input.readObject();
            input.close();
            tfAnnualInterestRate.setText(Double.toString(loan.getAnnualInterestRate()));
            tfNumberOfYears.setText(Integer.toString(loan.getNumberOfYears()));
            tfLoanAmount.setText(Double.toString(loan.getLoanAmount()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void btOnClear() {
        tfAnnualInterestRate.setText("");
        tfNumberOfYears.setText("");
        tfLoanAmount.setText("");
        tfMounthlyPayment.setText("");
        tfTotalPayment.setText("");
    }

    private void btOnCalculate() {
        double interest = 0;
        int year = 0;
        double loanAmount = 0;
        try {
            // Get values from text fields.
            interest = Double.parseDouble(tfAnnualInterestRate.getText());
            year = Integer.parseInt(tfNumberOfYears.getText());
            loanAmount = Double.parseDouble(tfLoanAmount.getText());

            // Create a loan object
            Loan loan = new Loan(interest, year, loanAmount);
            tfMounthlyPayment.setText(String.format("$%.2f", loan.getMonthlyPayment()));
            tfTotalPayment.setText(String.format("$%.2f", loan.getTotalPayment()));
        } catch (NumberFormatException ignore) {
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}