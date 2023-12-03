package com.example.mailisa_beauty.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DbName = "QLDT";

    public DbHelper(@Nullable Context context) {
        super(context, DbName, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //BẢNG TÀI KHOẢN
        String CreateTableTaiKhoan = "CREATE TABLE TaiKhoan(" +
                "maTK INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sdt TEXT NOT NULL," +
                "hoTen TEXT NOT NULL," +
                "matKhau TEXT NOT NULL," +
                "chucVu TEXT NOT NULL)";
        db.execSQL(CreateTableTaiKhoan);
        String data_TK = "INSERT INTO TaiKhoan VALUES" +
                "(1,'01234567890','Vũ Đức Anh','123','KH')," +
                "(2,'01122334455','Phạm Thị Hà','123','NV')," +
                "(3,'01122334456','Hồng Quân','1234','NV')," +
                "(4,'01122334457','Văn Đức','1234','NV')," +
                "(5,'09988776655','Đỗ Thị Kim Anh','1235','QL')," +
                "(6,'01234567899','Vũ Đức Anh','123','KH')," +
                "(11,'0941612689','Nguyễn Hoàng Long','1','QL')," +
                "(7,'0364209536','Giáp Văn Mạnh','1','QL')," +
                "(8,'0058387297','Nguyễn Thế Kỳ Anh','1','QL')," +
                "(9,'01234567891','Vũ Đức An','123','KH')," +
                "(10,'01234567892','Vũ Đức A','123','KH')";
        db.execSQL(data_TK);

        //BẢNG LỊCH LÀM VIỆC
        String CreateTableLichLamViec = "CREATE TABLE LichLamViec(" +
                "maLLV INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maTK INTEGER REFERENCES TaiKhoan(maTK)," +
                "ngayBatDau DATE NOT NULL," +
                "Ca INTEGER NOT NULL," +
                "ghiChu TEXT NOT NULL)";
        db.execSQL(CreateTableLichLamViec);
        String data_LLV = "INSERT INTO LichLamViec VALUES" +
                "(1,1,'2023/09/23','Sáng','colich')," +
                "(2,2,'2023/08/22','Chiều','1234')";
        db.execSQL(data_LLV);

        //BẢNG DỊCH VỤ
        String CreateTableDichVu = "CREATE TABLE DichVu(" +
                "maDV INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hinhAnh TEXT NOT NULL," +
                "tenDV TEXT NOT NULL," +
                "loaiDV TEXT NOT NULL," +
                "trangThai TEXT NOT NULL," +
                "giaDV INTEGER NOT NULL," +
                "giaSALE INTEGER NOT NULL," +
                "ghiChu TEXT)";
        db.execSQL(CreateTableDichVu);
        //android.resource://com.example.mailisa_beauty/2131165475
        //android.resource://com.example.mailisa_beauty/2131165476
        //android.resource://com.example.mailisa_beauty/2131165477
        //android.resource://com.example.mailisa_beauty/2131165478
        //android.resource://com.example.mailisa_beauty/2131165479
        //android.resource://com.example.mailisa_beauty/2131165480
        //android.resource://com.example.mailisa_beauty/2131165481
        String data_DV = "INSERT INTO DichVu VALUES" +
                "(1,'android.resource://com.example.mailisa_beauty/2131165338','LÀM ĐẦY RÃNH CƯỜI BABIES LINE','KHAC','SALE',12000000,10000000,'Rãnh cười chính là một đoạn nếp nhăn ở hai bên miệng, kéo dài từ cánh mũi chạy theo đường cong xuống tới cằm và nhìn rõ nhất khi cười.\n" +
                "\n" +
                "Rãnh cười xuất hiện ngay cả khi không cười thì đó chính là dấu hiệu cảnh báo về sự lão hóa khiến cho khuôn mặt bạn có vẻ già hơn.\n" +
                "\n" +
                "Bởi vậy rất nhiều chị em lo lắng và tìm kiếm phương pháp giải quyết, loại bỏ rãnh cười sâu ở trên khuôn mặt.')," +
                "(2,'android.resource://com.example.mailisa_beauty/2131165339','CĂNG DA MẶT BÁN PHẦN FACE LIFT','PT','NEW',12000000,12000000,'Trước khi Căng da mặt mặt bán phần Face Lift tại Mailisa\n" +
                "Quý khách hàng sẽ được đội ngũ nhân viên, y tá, bác sĩ kiểm tra tình trạng vùng da chùng nhão, lão hóa, nếp nhăn chân chim vùng từ đuôi mắt đuôi chân mày ra thái dương.\n" +
                "\n" +
                "Sau khi kiểm tra, y tá bác sĩ sẽ tư vấn đưa ra phương pháp căng da mặt cho từng khách hàng.\n" +
                "\n" +
                "► Làm căng da mặt mặt bán phần Face Lift tại Mailisa đem lại TỔNG THỂ GƯƠNG MẶT TRẺ TRUNG, TƯƠI TẮN, LÀN DA CĂNG SÁNG HƠN.\n" +
                "\n" +
                "\n" +
                "\n" +
                "► Đặc biệt dịch vụ căng da mặt mặt bán phần Face Lift có thêm một số ưu điểm như:\n" +
                "\n" +
                "- Trẻ hóa da từ bên trong.\n" +
                "\n" +
                "- Căng da mang lại khuôn mặt trẻ trung hơn 10 tuổi.\n" +
                "\n" +
                "- Ít tổn thương, xâm lấn.\n" +
                "\n" +
                "- An toàn, không sẹo và không gây biến chứng.\n" +
                "\n" +
                "- Duy trì kết quả lâu dài.\n" +
                "\n" +
                "- Thời gian thực hiện nhanh chóng')," +
                "(3,'android.resource://com.example.mailisa_beauty/2131165340','LÀM ĐẸP DA NÁM BẰNG CÔNG NGHỆ CAO','PS','SALE',16000000,13000000,'Mỗi quý khách hàng khi đến với Mailisa sẽ được đội ngũ chuyên viên kiểm tra để phân biệt nhận dạng, khách hàng đang bị nám gì để đưa ra phương pháp công nghệ cao phù hợp với từng làn da của từng khách hàng. Ví dụ khách hàng bị nám đinh chân sâu lâu năm hay là khách hàng bị tàn nhang hay là nám mảng cánh bướm lâu năm chân sâu hoặc là đồi mồi, bớt sắc tố'),"+
                "(4,'android.resource://com.example.mailisa_beauty/2131165341','LÀM ĐẸP DA SẸO RỖ BẰNG CÔNG NGHỆ CAO','PS','KHONG',800000,800000,'Khi bác sĩ, chuyên viên chiếu ánh sáng trực tiếp vào vùng da bị sẹo lõm, thông qua một thấu kính tạo ra hàng ngàn vi điểm trực tiếp xuyên thấu vào da mục đích cắt đứt những bao xơ cứng là lớp đáy của mô sẹo rỗ\n" +
                "\n" +
                "Và công nghệ này sẽ làm cho làn da tổn thương trong chế độ an toàn theo cơ chế tự phục hồi của làn da, nó sẽ kích thích elastin sản sinh ra collagen. Ngoài ra công nghệ này giúp loại bỏ hết tất cả các da sần sùi gồ ghề vỏ cam, làm cho làn da căng trắng mịn màng. Không còn tình trạng lồi lõm sần sùi, hỗ trợ điều tiết lượng nhờn, se khít lỗ chân lông, giúp hiệu quả ngay sau lần đầu sử dụng dịch vụ. Mailisa hy vọng sau khi quý khách hàng sử dụng dịch vụ lần đầu tiên làn da sẹo rỗ không chỉ được cải thiện đáng kể mà còn căng trắng mịn. chất da dai, lượng nhờn và lỗ chân lông được se khít.'),"+
                "(5,'android.resource://com.example.mailisa_beauty/2131165342','LÀM ĐẸP DA MỤN THÂM BẰNG CÔNG NGHỆ CAO','PS','SALE',2000000,1000000,'Nguyên lý loại bỏ mụn bằng công nghệ Pixel CO2 tại Mailisa:\n" +
                "Khi bác sĩ, chuyên viên chiếu ánh sáng trực tiếp vào từng cồi mụn mục đích nghiền nát, phá vỡ, phân hủy tận gốc của từng cồi mụn. Ngoài ra công nghệ pixel CO2 này có thêm một chức năng thông qua một thấu kính tạo ra hàng ngàn vi điểm trực tiếp bắn vào những vùng da không bị mụn,mục đích làm cho làn da tổn thương trong chế độ an toàn theo cơ chế tự phục hồi của làn da, nó sẽ kích thích elastin sản sinh ra collagen.'),"+
                "(6,'android.resource://com.example.mailisa_beauty/2131165343','PHẪU THUẬT KHÂU TẠO HÌNH MẮT 2 MÍ','PS','NEW',1600000,1600000,'“Khâu tạo hình mắt 2 mí” là một phương pháp tiểu phẫu nhưng cũng cần sự tinh tế và đòi hỏi tay nghề bác sĩ giỏi, có cặp mắt tinh tế và gu thẩm mỹ cao để xác định chính xác đường mí cần nhấn sao cho phù hợp với khuôn mặt và hốc mắt, giúp khách hàng trẻ trung, xinh đẹp. Thời gian thực hiện khoảng 20 - 30 phút'),"+
                "(7,'android.resource://com.example.mailisa_beauty/2131165344','PHUN MÀY CHẠM HẠT SƯƠNG BAY','KHAC','KHONG',22000000,22000000,'Với phương châm “Trao Bạn Nét Đẹp Mà Tự Nhiên”, khi mỗi quý khách hàng đến với Mailisa sẽ được đội ngũ nhân viên chuyên môn cao, tay nghề giỏi tư vấn chọn màu, thiết kế vẽ mẫu phù hợp với từng khuôn mặt.\n" +
                "\n" +
                "Khi khách hàng thực sự hài lòng thì chuyên viên mới bắt đầu phun theo dáng mẫu vừa vẽ.\n" +
                "\n" +
                "Dụng cụ mới vô trùng riêng biệt cho từng khách hàng, kết hợp cùng màu mực Doctor Magic nhập khẩu chính hãng, giúp cho màu sắc được bền, đẹp, tự nhiên, tuyệt đối không bị trổ xanh trổ đỏ; tông màu chính xác, phối màu nào ra màu đó, an toàn cho sức khỏe, đảm bảo gu thẩm mỹ. '),"+
                "(8,'android.resource://com.example.mailisa_beauty/2131165344','PHUN MÍ MỞ TRÒNG MẮT','PS','SALE',10500000,9500000,'Với phương châm “Trao Bạn Nét Đẹp Mà Tự Nhiên”, khi mỗi quý khách hàng đến với Mailisa sẽ được đội ngũ nhân viên chuyên môn cao, tay nghề giỏi tư vấn kiểu đường mí phù hợp, hài hòa với đôi mắt và khuôn mặt. Khi khách hàng thực sự hài lòng thì chuyên viên mới bắt đầu thực hiện phun mí. Đây là phương pháp sử dụng một đầu bút mảnh để tạo đường phun mí ôm nhuyễn theo đường cong trong lông mi, giúp cho “cửa sổ tâm hồn” có chiều sâu theo phong cách đẹp tự nhiên, thu hút mọi ánh nhìn.')"
                ;
        db.execSQL(data_DV);

        //Bảng DỊCH VỤ TRONG GIỎ DỊCH VỤ
        String CreateTableDichVuTrongGio = "CREATE TABLE DichVuTrongGio(" +
                "maDVTG INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maTK INTEGER REFERENCES TaiKhoan(maTK)," +
                "maDV INTEGER REFERENCES DichVu(maDV)," +
                "soLuong INTEGER NOT NULL," +
                "isCheck INTEGER)";
        db.execSQL(CreateTableDichVuTrongGio);
        String data_DVTG = "INSERT INTO DichVuTrongGio VALUES" +
                "(1,1,1,1,0)," +
                "(2,1,6,2,0)," +
                "(3,1,2,3,0)," +
                "(4,6,2,3,0)," +
                "(5,6,6,3,0)";
        db.execSQL(data_DVTG);

        //Bảng LỊCH ĐẶT CỦA KHÁCH HÀNG
        String CreateTableLichKhachHang = "CREATE TABLE LichKhachHang(" +
                "maLKH INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maTK INTEGER REFERENCES TaiKhoan(maTK)," +
                "maDV INTEGER REFERENCES DichVu(maDV)," +
                "ngayDat DATE NOT NULL," +
                "gioDat TEXT NOT NULL," +
                "PTTT TEXT NOT NULL," +
                "trangThat TEXT NOT NULL," +
                "feedBack TEXT ," +
                "ghiChu TEXT)";
        db.execSQL(CreateTableLichKhachHang);

        String data_LKH = "INSERT INTO LichKhachHang VALUES" +
                "(1,1,1,'2023/09/23','12:30','Chuyển khoản','Đang chờ','feedBack','ghiChu')," +
                "(2,6,3,'2023/08/22','9:10','Tiền mặt','Xác nhận','feedBack','ghiChu')," +
                "(3,1,8,'2023/07/21','15:20','Tiền mặt','Xác nhận','','')";
        db.execSQL(data_LKH);



        //Bảng feedback KHÁCH HÀNG
        String CreateTableFeedBack = "CREATE TABLE FeedBack(" +
                "maFB INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maLKH INTEGER REFERENCES LichKhachHang(maLKH)," +
                "soSao REAL DEFAULT 0," +
                "ghiChuKH TEXT,"  +
                "ghiChuQL TEXT)";
        db.execSQL(CreateTableFeedBack);

        String data_FB = "INSERT INTO FeedBack VALUES" +
                "(1,1,1.0,'Dịch vụ tệ','Xin lỗi vì trải ngiệm không tốt....')," +
                "(2,3,4.0,'ổn','Cám ơn vì đánh giá của bạn!')";
        db.execSQL(data_FB);

        //Bảng HÓA ĐƠN
        String CreateTableHoaDon = "CREATE TABLE HoaDon(" +
                "maHD INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maLKH INTEGER REFERENCES LichKhachHang(maLKH),"+
                "ngayTT DATE NOT NULL,"+
                "ghiChu TEXT)";
        db.execSQL(CreateTableHoaDon);

        String data_HD = "INSERT INTO HoaDon VALUES" +
                "(1,1,'2023/08/22','khong')," +
                "(2,3,'2023/07/21','')";
        db.execSQL(data_HD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion != oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS TaiKhoan");
            db.execSQL("DROP TABLE IF EXISTS DichVu");
            db.execSQL("DROP TABLE IF EXISTS LichLamViec");
            db.execSQL("DROP TABLE IF EXISTS DichVuTrongGio");
            db.execSQL("DROP TABLE IF EXISTS LichKhachHang");
            db.execSQL("DROP TABLE IF EXISTS FeedBack");
            db.execSQL("DROP TABLE IF EXISTS HoaDon");
            onCreate(db);
        }
    }
}
