package view;

import Service.Service;
import Service.Status;
import java.io.IOException;
import java.util.InputMismatchException;
import utils.Validation;
import java.util.Scanner;
public class Menu { //Call to service to do all the function and switch case to choose the function
    Service service = new Service();
    Validation validation = new Validation();
    Scanner sc = new Scanner(System.in);
    public void execute(){
        System.out.println("Welcome to the Store Management System");
        System.out.println("Represent to you by Phan Nguyen Hoang Long SE185083 ");
        System.out.println("======================================");
        System.out.println("Please choose the function you want to use");
        System.out.println("1. Add product");
        System.out.println("2. Update product");
        System.out.println("3. Remove product");
        System.out.println("4. Display all product");
        System.out.println("5. Create import receipt");
        System.out.println("6. Create export receipt");
        System.out.println("7. Display all receipt");
        System.out.println("8. Display expired product");
        System.out.println("9. Display selling product");
        System.out.println("10. Display product running out");
        // System.out.println("11. Display receipt product");
        System.out.println("11. Exit");
        int option = validation.checkInt("Please choose the function you want to use: ", 1, 11, Status.NONE);
//        int option = 0 ;
//        try {
//            option = sc.nextInt();
//        } catch (InputMismatchException e) {
//            // Handle the case where the input is not a valid integer
//            System.err.println("Invalid input. Please enter a valid integer.");
//            sc.next(); // Consume the invalid input to avoid an infinite loop
//        }
        

        while(true){
        switch(option){
            case 1:
                service.addProduct();
                break;
            case 2:
                service.updateProduct();
                break;
            case 3:
                service.removeProduct();
                break;
            case 4:
                service.displayAll();
                break;
            case 5:
                service.createImportReceipt();
                break;
            case 6:
                service.createExportReceipt();
                break;
            case 7:
                service.displayReceiptProduct();
                break;
            case 8:
                service.displayProductExpired();
                break;
            case 9:
                service.displayProductSelling();
                break;
            case 10: 
                service.displayProductRunningOut();
                break;
            case 11:
                System.out.println("Thank you for using our service!");
                System.exit(0);
                break;
            }
        }
    }
}
