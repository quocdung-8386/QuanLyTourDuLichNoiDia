package com.example.quanlytourdulichnoidia;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlytourdulichnoidia.R;

/**
 * Activity xử lý màn hình Thống kê (Statistics Dashboard).
 * Màn hình này hiển thị các chỉ số kinh doanh qua các biểu đồ tổng quan.
 */
public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvFilter;
    private ImageView btnExport; // Nút tải xuống (Export)
    private LinearLayout tabContainer; // Container chứa các tab

    // TextViews cho các Tab Bar (Cần thêm ID vào XML để ánh xạ chính xác)
    private TextView tabOverview; // Giả định ID: tvTabOverview
    private TextView tabTourBooking; // Giả định ID: tvTabTourBooking
    private TextView tabEmployeePerformance; // Giả định ID: tvTabEmployeePerformance
    private TextView tabCustomer; // Giả định ID: tvTabCustomer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Giả định tên file layout là activity_statistics
        setContentView(R.layout.activity_statistics);

        initViews();
        setupListeners();
    }

    /**
     * Ánh xạ các thành phần giao diện (View) bằng ID từ XML.
     * Lưu ý: Nút Export (ImageView stat_sys_download) không có ID trong XML.
     * Tôi sẽ giả định ID cho nó và các Tab Bar.
     */
    private void initViews() {
        tvFilter = findViewById(R.id.tvFilter);

        // Nút Export (Giả định ID: ivExport)
        // Trong XML: ImageView thứ 2 trong Header. Tôi sẽ giả định nó có ID là btnExport.
        // Giả định: <ImageView android:id="@+id/btnExport" ... />
        btnExport = findViewById(R.id.btnExport);

        // Tab Bar (Giả định ID cho các TextView trong LinearLayout)
        // Trong XML hiện tại không có ID cho các TextView trong Tab Bar,
        // nên việc ánh xạ sẽ không chính xác trừ khi bạn thêm ID.
        // Tôi sẽ tạm bỏ qua việc ánh xạ các tab để tránh lỗi NullPointer.

        // Nếu có ID:
        /*
        tabOverview = findViewById(R.id.tvTabOverview);
        tabTourBooking = findViewById(R.id.tvTabTourBooking);
        tabEmployeePerformance = findViewById(R.id.tvTabEmployeePerformance);
        tabCustomer = findViewById(R.id.tvTabCustomer);
        */
    }

    /**
     * Thiết lập các sự kiện lắng nghe (Listeners).
     */
    private void setupListeners() {
        if (tvFilter != null) {
            tvFilter.setOnClickListener(this);
        }

        // Cần thêm ID cho ImageView Export trong XML để code này chạy
        if (btnExport != null) {
            btnExport.setOnClickListener(this);
        }

        // Cần thêm ID cho các Tab Bar trong XML để code này chạy
        /*
        if (tabOverview != null) tabOverview.setOnClickListener(this);
        if (tabTourBooking != null) tabTourBooking.setOnClickListener(this);
        if (tabEmployeePerformance != null) tabEmployeePerformance.setOnClickListener(this);
        if (tabCustomer != null) tabCustomer.setOnClickListener(this);
        */
    }

    /**
     * Xử lý tất cả sự kiện click.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.tvFilter) {
            showFilterDialog();
        } else if (id == R.id.btnExport) { // Giả định ID cho nút Export
            exportData();
        }
        /*
        // Xử lý các sự kiện click Tab Bar
        else if (id == R.id.tvTabOverview) {
            selectTab(tabOverview);
        } else if (id == R.id.tvTabTourBooking) {
            selectTab(tabTourBooking);
        } else if (id == R.id.tvTabEmployeePerformance) {
            selectTab(tabEmployeePerformance);
        } else if (id == R.id.tvTabCustomer) {
            selectTab(tabCustomer);
        }
        */
    }

    /**
     * Mở dialog/dropdown để chọn khoảng thời gian lọc (Tháng này, Quý này, Năm này...).
     */
    private void showFilterDialog() {
        Toast.makeText(this, "Mở cửa sổ lọc thời gian cho báo cáo.", Toast.LENGTH_SHORT).show();
        // TODO: Implement custom DialogFragment hoặc PopupMenu để chọn khoảng thời gian
    }

    /**
     * Thực hiện logic xuất dữ liệu báo cáo ra file (e.g., CSV, Excel).
     */
    private void exportData() {
        Toast.makeText(this, "Đang chuẩn bị dữ liệu và xuất file báo cáo.", Toast.LENGTH_SHORT).show();
        // TODO: Implement logic xuất file
    }

    /**
     * Hàm giả lập chuyển đổi Tab và cập nhật UI.
     * @param selectedTab TextView của tab được chọn.
     */
    private void selectTab(TextView selectedTab) {
        // Reset màu và kiểu chữ cho tất cả các tab (Cần ánh xạ tất cả các tab trước)
        /*
        tabOverview.setTextColor(getResources().getColor(R.color.text_gray));
        tabOverview.setTypeface(null, Typeface.NORMAL);
        // ... reset các tab khác

        // Thiết lập màu và kiểu chữ cho tab được chọn
        selectedTab.setTextColor(getResources().getColor(R.color.primary_blue));
        selectedTab.setTypeface(null, Typeface.BOLD);

        Toast.makeText(this, "Đang chuyển sang tab: " + selectedTab.getText(), Toast.LENGTH_SHORT).show();
        // TODO: Thay đổi nội dung fragment hoặc ẩn/hiện các CardView tương ứng
        */
    }
}