import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Transaksi extends Barang {
    static Connection conn;

    @Override
    public void Kasir()
    {
        System.out.println("Nama Kasir = ");
        Kasir = input.nextLine();
        Kasir = Kasir.toUpperCase();
    }

    @Override
    public void SubTotal()
    {
        SubTotal = HargaBarang * Jumlah;
    }
 
    @Override
    public void Discount() 
    {
        if (SubTotal >= 50000 & SubTotal < 100000)
        {
            Discount = SubTotal * 5/100;
        }

        else if (SubTotal >= 100000 && SubTotal < 150000)
        {
            Discount = SubTotal * 10/100; 
        }

        else if (SubTotal >= 150000 && SubTotal < 200000)
        {
            Discount = SubTotal * 15/100;
        }

        else if (SubTotal >= 200000 && SubTotal < 300000)
        {
            Discount = SubTotal * 20/100;
        }

        else if (SubTotal >= 300000)
        {
            Discount = SubTotal * 25/100;
        }

        else
        {
            Discount = 0;
        }
    }
 
    @Override
    public void TotalHarga()
    {
        TotalHarga = SubTotal - Discount;
    }

    public void tanggal()
    {
        Date Date = new Date();
        SimpleDateFormat tgl = new SimpleDateFormat("EEEE, dd MMM yyyy");
        System.out.println("Tanggal Transaksi   = " + tgl.format(Date));
    }

    private void waktu()
    {
        Date Time = new Date();    
        SimpleDateFormat tm = new SimpleDateFormat("HH:mm:ss");  
        System.out.println("Waktu Transaksi     = " + tm.format(Time));
    }

    static void ViewDAta() throws SQLException {
		String text1 = "\nHasil Transaksi Minimarket";
		System.out.println(text1.toUpperCase());

        String url = "jdbc:mysql://localhost:3306/transaksi_minimarket";
		conn = DriverManager.getConnection(url,"root","");
						
		String sql ="SELECT * FROM penjualan";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		
		while(result.next()){
			System.out.print("\nNomor Faktur     : ");
            System.out.print(result.getString("Nomor_Faktur"));
            System.out.print("\nNama Barang      : ");
            System.out.print(result.getString("Nama_Barang"));
            System.out.print("\nHarga Barang     : ");
            System.out.print(result.getString("Harga_Barang"));
            System.out.print("\nJumlah Barang    : ");
            System.out.print(result.getString("Jumlah"));
            System.out.print("\nSub Total        : ");
            System.out.print(result.getString("Sub_Total"));
            System.out.print("\nDiskon           : ");
            System.out.print(result.getString("Diskon"));
            System.out.print("\n>>Total Harga    : ");
            System.out.print(result.getString("Total_Biaya"));
            System.out.print("\n");
		}
	}

    static void AddData() throws SQLException{
		String text2 = "\n===Tambah Data Penjualan===";
		System.out.println(text2.toUpperCase());

        String url = "jdbc:mysql://localhost:3306/transaksi_minimarket";
		conn = DriverManager.getConnection(url,"root","");
		
		Scanner terimaInput = new Scanner (System.in);
			
        Transaksi transaksi = new Transaksi();
		try {
		
            transaksi.NoFaktur();
            transaksi.NamaBarang();
            transaksi.HargaBarang();
            transaksi.Jumlah();
            transaksi.SubTotal();
            transaksi.Discount();
            transaksi.TotalHarga();

		
		String sql = "INSERT INTO penjualan (Nomor_Faktur, Nama_Barang, Harga_Barang, Jumlah, Sub_Total, Diskon, Total_Biaya) VALUES ('"+NoFaktur+"','"+NamaBarang+"','"+HargaBarang+"','"+Jumlah+"','"+SubTotal+"','"+Discount+"','"+TotalHarga+"')";
					
        Statement statement = conn.createStatement();
        statement.execute(sql);
        System.out.println("Berhasil input data");
	
	    } catch (SQLException e) {
	        System.err.println("Terjadi kesalahan input data");
	    } catch (InputMismatchException e) {
	    	System.err.println("Inputlah dengan angka saja");
	   	}
	}

    static void ChangeData() throws SQLException{
		String text3 = "\n===Ubah Data Transaksi Minimarket===";
		System.out.println(text3.toUpperCase());

        String url = "jdbc:mysql://localhost:3306/transaksi_minimarket";
		conn = DriverManager.getConnection(url,"root","");
		
		Scanner terimaInput = new Scanner (System.in);
		Transaksi transaksi = new Transaksi();

		try {
            ViewDAta();
            System.out.print("Masukkan Nomor Faktur yang akan dirubah : ");
            transaksi.NoFaktur = (terimaInput.nextLine());
            
            String sql = "SELECT * FROM penjualan WHERE Nomor_Faktur = " +transaksi.NoFaktur;
            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            if(result.next()){
                
                System.out.print("Nama Barang ["+result.getString("Nama_Barang")+"]\t: ");
                transaksi.NamaBarang = terimaInput.nextLine();
                
                System.out.print("Harga Barang ["+result.getString("Harga_Barang")+"]\t: ");
                transaksi.HargaBarang = terimaInput.nextInt();

                System.out.print("Jumlah Barang ["+result.getString("Jumlah")+"]\t: ");
                transaksi.Jumlah = terimaInput.nextInt();

                transaksi.SubTotal();
                transaksi.Discount();
                transaksi.TotalHarga();    
                   
                sql = "UPDATE penjualan SET Nama_Barang='"+transaksi.NamaBarang+"',Harga_Barang= '"+transaksi.HargaBarang+"',Jumlah= '"+transaksi.Jumlah+"',Sub_Total= '"+transaksi.SubTotal+"',Diskon= '"+transaksi.Discount+"',Total_Biaya= '"+transaksi.TotalHarga+"' WHERE Nomor_Faktur='"+transaksi.NoFaktur+"'";
                //System.out.println(sql);

                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil memperbaharui data Penjualan (Nomor_Faktur "+transaksi.NoFaktur+")");
                }
            }
            statement.close();        
        } catch (SQLException e) {
            System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
        }
		}

        static void DeleteData() throws SQLException {
            String text4 = "\n===Hapus Data Penjualan===";
            System.out.println(text4.toUpperCase());

            String url = "jdbc:mysql://localhost:3306/transaksi_minimarket";
		    conn = DriverManager.getConnection(url,"root","");
            
            Scanner terimaInput = new Scanner (System.in);
            
            try{
               ViewDAta();
                System.out.print("\nKetik Nomor Faktur yang akan Anda Hapus : ");
                Integer NoFaktur= Integer.parseInt(terimaInput.nextLine());
                
                String sql = "DELETE FROM penjualan WHERE Nomor_Faktur = "+ NoFaktur;
                Statement statement = conn.createStatement();
                //ResultSet result = statement.executeQuery(sql);
                
                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil menghapus data Transaksi (Nomor Faktur	 "+NoFaktur+")");
                }
           }catch(SQLException e){
                System.out.println("Terjadi kesalahan dalam menghapus data barang");
                }
            }
	
            static void FindData () throws SQLException {
                String text5 = "\n===Cari Data Penjualan===";
                System.out.println(text5.toUpperCase());

                String url = "jdbc:mysql://localhost:3306/transaksi_minimarket";
	        	conn = DriverManager.getConnection(url,"root","");
                
                Scanner terimaInput = new Scanner (System.in);
                        
                System.out.print("Masukkan Nomor Faktur: ");
                
                String keyword = terimaInput.nextLine();
                Statement statement = conn.createStatement();
                String sql = "SELECT * FROM penjualan WHERE Nama_Barang LIKE '%"+keyword+"%'";
                ResultSet result = statement.executeQuery(sql); 
                        
                while(result.next()){
                    System.out.print("\nNomor Faktur     : ");
                    System.out.print(result.getInt("Nomor_Faktur"));
                    System.out.print("\nNama Barang      : ");
                    System.out.print(result.getString("Nama_Barang"));
                    System.out.print("\nHarga Barang     : ");
                    System.out.print(result.getString("Harga_Barang"));
                    System.out.print("\nJumlah Pembelian : ");
                    System.out.print(result.getString("Jumlah Barang"));
                    System.out.print("\nSub Total        : ");
                    System.out.print(result.getString("Sub_Total"));
                    System.out.print("\nDiskon           : ");
                    System.out.print(result.getString("Diskon"));
                    System.out.print("\n>>Total Harga    : ");
                    System.out.print(result.getString("Total_Biaya"));
                    System.out.print("\n");
                }
            }

    public void View()
    {
        System.out.println("_____________________________________________");
        System.out.println("              PEMBELIAN BARANG");
        System.out.println("---------------------------------------------");
        tanggal();
        waktu();
        System.out.println("Nama Kasir          = " + Kasir);
        System.out.println("No Faktur           = " + NoFaktur);
        System.out.println("---------------------------------------------");
        System.out.println("Nama Barang         = " + NamaBarang);
        System.out.println("Harga Barang        = " + HargaBarang);
        System.out.println("Jumlah Barang       = " + Jumlah);
        System.out.println("Total Pembelian     = " + SubTotal);
        System.out.println("Discount            = " + Discount);
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Total Pembayaran     = " + TotalHarga);
    }
}
