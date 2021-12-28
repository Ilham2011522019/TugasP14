import java.util.Scanner;

public class Barang implements Penjualan  {
    public String Kasir;
    public static String NoFaktur;
    public static String NamaBarang;
    public static Integer HargaBarang;
    public static Integer Jumlah;
    public static Integer SubTotal;
    public static Integer Discount;
    public static Integer TotalHarga; 
    
    Scanner input = new Scanner(System.in);

    @Override
    public void Kasir()
    {
        Kasir = null;
    }

    @Override
    public void NoFaktur()
    {
        System.out.println("No Faktur  = ");
        NoFaktur = input.nextLine();
    }
    
    @Override
    public void NamaBarang()
    {
        System.out.println("Nama Barang  = ");
        NamaBarang = input.nextLine();
    }
    
    @Override
    public void HargaBarang()
    {  
        System.out.println("Harga Barang  = ");
        HargaBarang = input.nextInt();
    }

    @Override
    public void Jumlah()
    {
        System.out.println("Jumlah Barang  = ");
        Jumlah = input.nextInt();
    }

    public void SubTotal() 
    {
        SubTotal = null;
    }
 
    public void Discount()
    {
        Discount = null;
    }
 
    public void TotalHarga()
    {
        TotalHarga = null;
    }
}