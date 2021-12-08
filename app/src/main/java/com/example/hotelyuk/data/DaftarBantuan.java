package com.example.hotelyuk.data;

import com.example.hotelyuk.entity.HelpData;

import java.util.ArrayList;

public class DaftarBantuan {
    public ArrayList<HelpData> daftarBantuan;
    public DaftarBantuan(){
        daftarBantuan = new ArrayList();
        daftarBantuan.add(h1);
        daftarBantuan.add(h2);
        daftarBantuan.add(h3);
        daftarBantuan.add(h4);
        daftarBantuan.add(h5);
        daftarBantuan.add(h6);
        daftarBantuan.add(h7);
        daftarBantuan.add(h8);
        daftarBantuan.add(h9);
        daftarBantuan.add(h10);
    }

    public static final HelpData h1 = new HelpData("Q : Tipe kamar apa saja yang tersedia di hotel?",
            "Ada 2 Kategori Kamar : Single dan Double. Kamar Single seperti namanya berarti single bed" +
                    "dan Kamar Double berarti double bed.");
    public static final HelpData h2 = new HelpData("Q : Apakah harga hotel Anda per kamar atau per orang?",
            "Harga kamar adalah per kamar dan bukan per orang.");
    public static final HelpData h3 = new HelpData("Q : Berapa jumlah orang yang dapat menginap di satu kamar?",
            "Untuk Kamar Single 1 orang per kamar dan untuk Kamar Double maksimal 2 orang per kamar.");
    public static final HelpData h4 = new HelpData("Q : Saya memiliki pemesanan atas nama saya, " +
            "dapatkan teman saya yang melakukan check in untuk saya?",
            "Bisa, namun tamu harus menginformasikan nama teman tersebut sebelum check-in.");
    public static final HelpData h5 = new HelpData("Q : Dapatkan saya membayar kamar saya dalam mata uang berbeda?",
            "Tidak, semua transaksi di hotel dalam bentuk mata uang Rupiah.");
    public static final HelpData h6 = new HelpData("Q : Apakah harga yang tercantum di aplikasi, sebelum atau setelah diskon?",
            "Harga yang tertera di aplikasi adalah setelah diskon. Tidak ada tambahan biaya tersembunyi maupun" +
                    " biaya pemesanan jika Anda memesan melalui aplikasi resmi. ");
    public static final HelpData h7 = new HelpData("Q: Apakah hotel menawarkan kamar untuk menginap singkat (day use)?",
            "Tidak, kami tidak menyediakan layanan tersebut. Tamu dapat memilih check out lebih lambat, setelah menginap semalam.");
    public static final HelpData h8 = new HelpData("Q : Dapatkah saya melakukan check in lebih awal? Jika iya, " +
            "berapa biaya nya?",
            "Jika ketibaan sebelum jam 10.00, disarankan untuk memesan kamar sehari sebelumnya. Jika check in " +
                    "setelah jam 10.00 dan sebelum jam 14.00, check in lebih awal ada tergantung ketersediaan, " +
                    "berdasarkan permintaan, tanpa biaya tambahan.");
    public static final HelpData h9 = new HelpData("Q : Dapatkah saya melakukan check out lebih lambat? Jika iya, " +
            "berapa biaya nya?",
            "Untuk check out hingga jam 6.00 sore, dikenakan biaya setengah hari, untuk check out melebihi jam 6 sore," +
                    " dikenakan harga sehari penuh.");
    public static final HelpData h10 = new HelpData("Q : Apakah hotel memiliki meja layanan tur dimana tamu dapat memesan tur saat menginap?",
            "Iya, ada konter tur di lobi hotel.");
    public static final HelpData h11 = new HelpData("Q : Apakah hotel menawarkan wifi gratis?",
            "Iya.");
    public static final HelpData h12 = new HelpData("Q : Apakah tersedia kamar dengan bak mandi?",
            "Iya, tersedia berdasarkan permintaan dan ketersediaan.");
    public static final HelpData h13 = new HelpData("Q : Apakah ada kolam renang di area hotel? Jika iya bagaimana" +
            "dengan jam beroperasinya?",
            "Iya, kolam renang buka dari jam 06.00 pagi hingga 22.00.");
    public static final HelpData h14 = new HelpData("Q : Apakah ada pusat kebugaran di hotel? Jika ada, peralatan apa saja yang " +
            "tersedia disana? Bagaimana dengan jam operasinya?",
            "Iya, hotel kami dilengkapi dengan treadmills, dumbbells, mesin angkat berat, mesin eliptikal. " +
                    "Jam buka dari jam 06.00 hingga 22.00.");
    public static final HelpData h15 = new HelpData("Q : Apakah tersedia layanan dokter di hotel?",
            "Ada. Kami dapat menghubungi dokter dalam satu jam.");

}
