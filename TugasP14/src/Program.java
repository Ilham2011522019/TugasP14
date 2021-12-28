import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;



public class Program {

    //static Scanner scanner;
	static Connection conn;
    public static void main(String[] args) throws Exception {
        
        Transaksi transaksi = new Transaksi();

        Scanner terimaInput = new Scanner (System.in);
		String pilihanUser;
		boolean isLanjutkan = true;
						
		String url = "jdbc:mysql://localhost:3306/transaksi_minimarket";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","");
			System.out.println("Class Driver ditemukan");
			
			while (isLanjutkan) {
				System.out.println("\n----------------------------");
				System.out.println("Database Transaksi Minimarket");
				System.out.println("-----------------------------");
				System.out.println("1. Lihat Data Transaksi");
				System.out.println("2. Tambahkan Data Transaksi");
				System.out.println("3. Hapus Data Transaksi");
				System.out.println("4. Rubah Data Transaksi");
				System.out.println("5. Cari Data Transaksi");
				
				System.out.print("\nPilihan anda (1/2/3/4/5): ");
				pilihanUser = terimaInput.next();
				
				switch (pilihanUser) {
				case "1":
                    transaksi.ViewDAta();
					break;
				case "2":
					transaksi.AddData();
					break;
				case "3":
					transaksi.DeleteData();
					break;
				case "4":
					transaksi.ChangeData();
					break;
				case "5":
					transaksi.FindData();
					break;
				default:
					System.err.println("\nInput anda tidak ditemukan\nSilakan pilih [1-5]");
				}
				
				System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
				pilihanUser = terimaInput.next();
				isLanjutkan = pilihanUser.equalsIgnoreCase("y");
			}
			System.out.println("\nBye.... Selamat Berjumpa Kembali!!!");
			
		}
		catch(ClassNotFoundException ex) {
			System.err.println("Driver Error");
			System.exit(0);
		}
		catch(SQLException e){
			System.err.println("Tidak berhasil koneksi");
		}
	}
}
