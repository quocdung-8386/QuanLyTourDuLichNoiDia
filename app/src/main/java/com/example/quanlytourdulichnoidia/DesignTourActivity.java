package com.example.quanlytourdulichnoidia;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlytourdulichnoidia.R;

/**
 * Activity xử lý màn hình Thiết kế Tour.
 * Cho phép người dùng nhập thông tin cơ bản và cấu hình chi tiết cho một tour du lịch mới.
 */
public class DesignTourActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnBack;
    private TextView btnSave;
    private EditText edtTenTour;
    private EditText edtMaTour;

    // Các mục cấu hình chi tiết
    private LinearLayout itemLichTrinh;
    private LinearLayout itemBaoGom;
    private LinearLayout itemKhongBaoGom;
    private LinearLayout itemChinhSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Giả định tên file layout là activity_design_tour.xml
        setContentView(R.layout.activity_design_tour);

        initViews();
        setupListeners();
    }

    /**
     * Ánh xạ các thành phần giao diện (View) bằng ID từ XML.
     */
    private void initViews() {
        // Header
        btnBack = findViewById(R.id.btnBack);
        btnSave = findViewById(R.id.btnSave);

        // Input Fields
        edtTenTour = findViewById(R.id.edtTenTour);
        edtMaTour = findViewById(R.id.edtMaTour);

        // Detail Items (Giả định ID cho các LinearLayout chứa các mục này để dễ dàng xử lý click)
        // Trong XML, các item này không có ID. Tôi sẽ gán ID giả định và cần bạn kiểm tra lại XML.
        // Giả định: itemLichTrinh, itemBaoGom, itemKhongBaoGom, itemChinhSach
        // Vì các item này là LinearLayout, ta sẽ tìm kiếm chúng trong layout.

        // Do XML không có ID cho các LinearLayout item, tôi sẽ phải tìm chúng qua thứ tự (dễ bị lỗi)
        // hoặc giả định chúng có ID, nhưng để đảm bảo tính an toàn, tôi sẽ chỉ
        // xử lý click trên các thành phần có ID rõ ràng.
        // Tuy nhiên, để làm cho code hoạt động, tôi sẽ thêm các ID giả định:
        // Cần thêm ID vào XML cho các item sau:
        // itemLichTrinh -> id/itemLichTrinhContainer
        // itemBaoGom -> id/itemBaoGomContainer
        // itemKhongBaoGom -> id/itemKhongBaoGomContainer
        // itemChinhSach -> id/itemChinhSachContainer

        // Tạm thời bỏ qua ánh xạ các item chi tiết do thiếu ID trong XML.
        // Cần thêm ID cho các LinearLayout tương ứng.
    }

    /**
     * Thiết lập các sự kiện lắng nghe (Listeners).
     */
    private void setupListeners() {
        // Header actions
        btnBack.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        // Ánh xạ và thiết lập Listener cho các mục chi tiết nếu có ID
        // Ví dụ: itemLichTrinh.setOnClickListener(this);
    }

    /**
     * Xử lý tất cả sự kiện click.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnBack) {
            onBackPressed(); // Quay lại màn hình trước
        } else if (id == R.id.btnSave) {
            saveTour(); // Xử lý logic lưu tour
        }
        /* else if (id == R.id.itemLichTrinhContainer) {
            Toast.makeText(this, "Mở màn hình cấu hình Lịch trình.", Toast.LENGTH_SHORT).show();
            // TODO: Intent đến Activity Lịch trình
        } else if (id == R.id.itemBaoGomContainer) {
            Toast.makeText(this, "Mở màn hình cấu hình Dịch vụ bao gồm.", Toast.LENGTH_SHORT).show();
            // TODO: Intent đến Activity Dịch vụ bao gồm
        }
        // ... xử lý cho các item khác
        */
    }

    /**
     * Hàm xử lý logic lưu thông tin tour.
     */
    private void saveTour() {
        String tenTour = edtTenTour.getText().toString().trim();
        String maTour = edtMaTour.getText().toString().trim();

        if (tenTour.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập Tên tour.", Toast.LENGTH_SHORT).show();
            edtTenTour.requestFocus();
            return;
        }

        if (maTour.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập Mã tour.", Toast.LENGTH_SHORT).show();
            edtMaTour.requestFocus();
            return;
        }

        // TODO: Thực hiện lưu dữ liệu vào cơ sở dữ liệu (Database Save Logic)
        Toast.makeText(this, "Đang lưu Tour: " + tenTour + " (" + maTour + ")", Toast.LENGTH_LONG).show();

        // Kết thúc Activity sau khi lưu thành công (tùy thuộc vào luồng ứng dụng)
        // finish();
    }
}
