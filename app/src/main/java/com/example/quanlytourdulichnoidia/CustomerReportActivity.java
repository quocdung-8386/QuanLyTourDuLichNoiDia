package com.example.quanlytourdulichnoidia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.quanlytourdulichnoidia.R;

/**
 * Activity xử lý màn hình Báo cáo Khách hàng.
 * Màn hình này hiển thị các chỉ số thống kê, cơ cấu khách hàng và danh sách khách.
 */
public class CustomerReportActivity extends AppCompatActivity {

    // Nút Filters
    private Button btnFilterWeek;
    private Button btnFilterCustomerType;
    private Button btnFilterSource;

    // Thẻ Stats (chưa có ID trong XML, giả định xử lý click trên LinearLayout chứa CardView)
    // Các CardView trong layout không có ID, nên tôi sẽ tập trung vào các nút Button có ID rõ ràng.

    // Nút Xuất file
    private Button btnExportFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Giả sử tên file layout là activity_customer_report
        setContentView(R.layout.activity_customer_report);

        initViews();
        setupListeners();
    }

    /**
     * Ánh xạ các thành phần giao diện (View) bằng ID từ XML.
     */
    private void initViews() {
        // Ánh xạ các nút Filters (Các nút này không có ID trong XML, tôi giả định ID nếu cần xử lý click)
        // Để xử lý click chính xác, các button này cần có ID.
        // Trong trường hợp này, tôi sẽ tạm thời bỏ qua việc ánh xạ 3 nút filter

        // Tuy nhiên, nút Xuất file có ID rõ ràng trong RelativeLayout
        btnExportFile = findViewById(R.id.btnExportFile); // Giả định bạn thêm ID vào nút Xuất file
    }

    /**
     * Thiết lập các sự kiện lắng nghe (Listeners).
     */
    private void setupListeners() {
        // Xử lý sự kiện cho Nút Xuất file
        if (btnExportFile != null) {
            btnExportFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CustomerReportActivity.this, "Đang chuẩn bị dữ liệu và xuất file báo cáo.", Toast.LENGTH_SHORT).show();
                    // TODO: Thực hiện logic xuất file (e.g., CSV, Excel)
                }
            });
        }

        // Ghi chú: Nếu muốn xử lý click cho các nút lọc (Tuần này, Loại khách, Nguồn khách),
        // bạn cần đặt ID cho chúng trong file XML.
        /*
        // Ví dụ xử lý click cho nút "Tuần này" (giả sử có ID tvFilterWeek)
        if (btnFilterWeek != null) {
            btnFilterWeek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CustomerReportActivity.this, "Mở dialog chọn khoảng thời gian.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        */

        // Ghi chú: Nếu muốn xử lý click cho các CardView thống kê hoặc danh sách khách hàng,
        // cần đặt ID cho chúng trong file XML.
    }
}
