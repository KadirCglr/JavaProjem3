

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Aksiyon aksiyon1 = new Aksiyon("Para_Yatir");
        Aksiyon aksiyon2 = new Aksiyon("Para_Cek");
        Aksiyon aksiyon3 = new Aksiyon("Transfer");
        Aksiyon aksiyon4 = new Aksiyon("Cikis");
        List<Aksiyon> aksiyonlar = new ArrayList<>(Arrays.asList(aksiyon1, aksiyon2, aksiyon3, aksiyon4));


        Hesap hesap1 = new Hesap(189622, 500);
        Hesap hesap2 = new Hesap(152833, 350);
        Hesap hesap3 = new Hesap(222852, 750);
        Hesap hesap4 = new Hesap(285333, 870);
        Hesap hesap5 = new Hesap(335642, 900);
        Hesap hesap6 = new Hesap(385643, 150);


        Kullanici musteri1 = new Kullanici("alperes13", 12345, Arrays.asList(hesap1, hesap2));
        Kullanici musteri2 = new Kullanici("ozgurtechno", 48795, Arrays.asList(hesap3, hesap4));
        Kullanici musteri3 = new Kullanici("cglrkizilbulut", 42735, Arrays.asList(hesap5, hesap6));
        List<Kullanici> musteriler = new ArrayList<>(Arrays.asList(musteri1, musteri2, musteri3));

        Kullanici aktifKullanici;
        Hesap secilenHesap;

        while (true) {
            System.out.print("Lütfen kullanıcı adınızı giriniz : ");
            String username = scan.nextLine();
            System.out.print("Lütfen parolanızı giriniz :  ");
            int password = scan.nextInt();

            // -> Bu methodu asagida hangi kullanicinin giris yaptigini belirlemek maksadiyla kullanacaksiniz, olusturun...
            aktifKullanici = kullaniciAdiniVeParolayiDogrula(musteriler, username, password);

            if (aktifKullanici != null) {
                System.out.println("Basarili bir sekilde giris yaptiniz");
                break;
            }
            System.out.println("Sistemde kayitli boyle bir kullanici bulunamadi.. Tekrar deneyin");
        }


        while (true) {

            System.out.println("------------------------");
            for (int i = 0; i < aksiyonlar.size(); i++) {
                System.out.println(aksiyonlar.get(i).aciklama + " - " + (i + 1));
            }
            System.out.println("------------------------");
            System.out.print("Yapmak istediginiz islemi seciniz...");
            int userChoice = scan.nextInt();

            switch (userChoice) {

                case 1: {

                    for (Hesap hesap : aktifKullanici.kullaniciHesaplari) {
                        System.out.println(hesap.hesapNumarasi);
                    }

                    System.out.print("Lütfen bir hesap numarasi giriniz : ");
                    int hesapNumarasi = scan.nextInt();


                    secilenHesap = hesapNumarasiniDogrula(aktifKullanici, hesapNumarasi);

                    if (secilenHesap != null) {

                        System.out.print("Lütfen bakiye giriniz : ");
                        int bakiye = scan.nextInt();


                        secilenHesap.hesapBakiyesi += bakiye;
                        System.out.println("Güncel bakiyeniz = " + secilenHesap.hesapBakiyesi);
                        break;

                    }
                    System.out.println("Böyle bir hesap numarasi bulunmamaktır.");
                    break;

                }


                case 2: {

                    while (true) {

                        for (Hesap hesap : aktifKullanici.kullaniciHesaplari) {
                            System.out.println(hesap.hesapNumarasi);
                        }

                        System.out.print("Lütfen bir hesap numarasi giriniz : ");
                        int hesapNumarasi = scan.nextInt();


                        // confirmAccountNumber() methodunu kullanicinin dogru hesap bilgileri girdigini teyit etmek amacli kullanacaksiniz..
                        secilenHesap = hesapNumarasiniDogrula(aktifKullanici, hesapNumarasi);

                        if (secilenHesap == null) {
                            System.out.println("Hatali hesap numarasi girdiniz...");
                            continue;
                        }


                        System.out.print("Lutfen cekmek istediginiz para miktarini giriniz : ");
                        int cekilecekPara = scan.nextInt();

                        if (hesaptanParaCek(secilenHesap, cekilecekPara)) {
                            break;
                        }
                    }
                    break;
                }

                case 3: {
                    System.out.println("Bu islemi su an gerceklestiremiyoruz...");
                    break;
                }

                case 4: {
                    System.exit(1);
                }
                default: {
                    System.out.println("Basarili bir secim yapmadiniz...");
                }
            }
        }

    }


    public static Kullanici kullaniciAdiniVeParolayiDogrula(List<Kullanici> kullanicilar, String kullaniciAdi, int parola) {

        for (Kullanici kullanici : kullanicilar) {

            if (kullanici.kullaniciAdi.equals(kullaniciAdi) && kullanici.kullaniciParola == parola) {

                return kullanici;

            }

        }

        return null;

    }


    public static Hesap hesapNumarasiniDogrula(Kullanici kullanici, int hesapNumarasi) {

        for (Hesap hesap : kullanici.kullaniciHesaplari) {

            if (hesap.hesapNumarasi == hesapNumarasi) {

                return hesap;

            }

        }

        return null;

    }

    public static Boolean hesaptanParaCek(Hesap hesap, int bakiye) {

        if (hesap.hesapBakiyesi < bakiye) {
            System.out.println("Hesabınzdaki para miktarı yetersiz, bakiyeniz : " + hesap.hesapBakiyesi);
            return false;
        }
        hesap.hesapBakiyesi -= bakiye;
        System.out.println("Hesabınızın güncel bakiyesi : " + hesap.hesapBakiyesi);
        return true;

    }
}
