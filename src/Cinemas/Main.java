package Cinemas;

import java.util.Locale;
import java.text.NumberFormat;
import java.util.Scanner;


public class Main {
//    Public variable
    public static String DEFAULT_USERNAME = "alvin_ferdian";
    public static String DEFAULT_PASSWORD = "123456";
    public static int DELUXE_PRICE = 40000;
    public static int IMAX_PRICE = 70000;
    public static int PREMIERE_PRICE = 100000;

    public static String[][] movies = {
            {"Black Panther", "100"},
            {"Gundala","10"},
            {"Sri Asih", "100"},
            {"Kimi no Nawa", "100"}
    };
    public static Scanner scanner = new Scanner(System.in);

    public static NumberFormat numberFormat= NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
//    Main method
    public static void main(String[] args) {
        mainPage();
    }

    static void mainPage(){

        System.out.println("------------ Selamat datang ------------");
        System.out.println("1. Login \n2. Keluar");
        System.out.println("Silahkan pilih salah satu angka diatas!");
        int pilihan = scanner.nextInt();

        switch(pilihan){
            case 1 :
                loginPage();
            break;
            case 2 :
                exit();
            break;
            default :{
                inputErrorMsg();
                mainPage();
            }
        }

    }


    static void mainMenu(){
        System.out.println("------------ Halo," + DEFAULT_USERNAME + "------------");
        System.out.println("1. Pilih Film  \n2. Keluar");
        System.out.println("Silahkan pilih salah satu angka diatas!");
        int pilihan = scanner.nextInt();

        switch (pilihan){
            case 1:
                movieList();
            break;
            case 2:
                exit();
            break;
            default: {
                inputErrorMsg();
                mainMenu();
            }
        }
    }

    static void loginPage(){
        System.out.println("Masukkan Username");
        String username = scanner.next().trim();
        System.out.println("Masukkan Password");
        String password = scanner.next().trim();
        auth(username, password);
    }

    static void auth(String username, String password){
//        check if the params is null
        if (username != null && password!= null){
//            validate the username and password with the default value
            if (username.equals(DEFAULT_USERNAME) && password.equals(DEFAULT_PASSWORD)){
                System.out.println("Berhasil login!");
                mainMenu();
            }else {
                System.out.println("Login gagal!");
            }
        } else {
            System.out.println("Mohon di isi!");
        }
    }

    public static void movieList(){
        System.out.println("----------------------------------------------");
        System.out.printf("%5s %15s %20s\n", "No", "Film", "Stok tiket");
        System.out.println("----------------------------------------------");
        for (int i = 0; i < movies.length; i++) {
            System.out.format("%5d %20s %15s \n", (i+1),  movies[i][0]  ,movies[i][1]);
            System.out.println();
        }
        System.out.println("----------------------------------------------");
        chooseMovie();
    }

    public static void chooseMovie(){
        System.out.println("Pilih salah satu film yang ingin anda tonton diatas!");
        int pilihan = scanner.nextInt();
        chosenMovie(pilihan-1);
        System.out.println("Berapa kusrsi yang anda pesan?");
        int kursi = scanner.nextInt();
        checkoutTicket(pilihan -1, kursi );
        System.out.println("Pilih studio!");
        System.out.format("\n1. Deluxe: %s \n2. Imax: %s \n3. Premiere: %s", numberFormat.format(DELUXE_PRICE), numberFormat.format(IMAX_PRICE), numberFormat.format(PREMIERE_PRICE));
        System.out.println();
        int studio = scanner.nextInt();
        approveTransaction(priceSum(studio, kursi));

    }

    public static void approveTransaction(int nominalTotal){
        System.out.println("Apakah anda setuju dengan transaksi dan ingin melanjutkan?");
        System.out.println("1. Ya \n2. Tidak");
        int pilihan = scanner.nextInt();

        switch (pilihan){
            case 1:
                System.out.println("Total transaksi: "+ numberFormat.format(nominalTotal));
            case 2:
                mainMenu();
            default: {
                inputErrorMsg();
                mainMenu();
            }
        }
    }

    public static int priceSum(int studioPilihan, int kursi){
        switch (studioPilihan){
            case 1:
                return 40000*kursi;
            case 2:
                return 70000*kursi;
            case 3:
                return 100000*kursi;
            default:
                return 0;
        }

    }

    public static void chosenMovie(int index){
        System.out.println(movies[index][0]);
    }

    public static void exit(){
        System.out.println("-------- Keluar --------");
        System.exit(0);
    }

    public static void inputErrorMsg(){
        System.out.println("Mohon masukkan pilihan angka diatas");
        System.out.println();
    }

    public static void checkoutTicket(int index, int quantity){
        int ticketQuantity = Integer.parseInt(movies[index][1]);
        int result = ticketQuantity - quantity;
        pushNewTicketQuantity(index, result);
    }

    public static void pushNewTicketQuantity(int index, int quantity){
        String qty = String.valueOf(quantity);
        movies[index][1] = qty;

        System.out.println(movies[index][1]);
    }


}
