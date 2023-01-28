
import java.util.List;

public class Kullanici {
    String kullaniciAdi;
    int kullaniciParola;
    List<Hesap> kullaniciHesaplari;



    public Kullanici(String kullaniciAdi, int kullaniciParola, List<Hesap> hesaplar){
        this.kullaniciAdi = kullaniciAdi;
        this.kullaniciParola = kullaniciParola;
        this.kullaniciHesaplari = hesaplar;

    }

}
