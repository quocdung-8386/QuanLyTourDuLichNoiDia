package com.example.quanlytourdulichnoidia;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlytourdulichnoidia.R;
import com.google.android.material.tabs.TabLayout;
public class AssignGuideVehicleScrollableActivity extends AppCompatActivity {

    private ImageButton backButton;
    private ImageButton notificationButton;
    private TabLayout tabLayout;

    // Các nút Gán Ngay cứng cho 3 tour mẫu
    private Button assignButton1;
    private Button assignButton2;
    private Button assignButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Giả sử tên file layout là activity_tour_assignment
        setContentView(R.layout.activity_assign_guide_vehicle_scrollable);

        initViews();
        setupListeners();
    }

    /**
     * Ánh xạ các thành phần giao diện (View) bằng ID từ XML.
     */
    private void initViews() {
        backButton = findViewById(R.id.backButton);
        notificationButton = findViewById(R.id.notificationButton);
        tabLayout = findViewById(R.id.tabLayout);

        // Ánh xạ các nút Gán Ngay theo ID cứng trong XML
        assignButton1 = findViewById(R.id.assignButton1);
        assignButton2 = findViewById(R.id.assignButton2);
        assignButton3 = findViewById(R.id.assignButton3);
    }

    /**
     * Thiết lập các sự kiện lắng nghe (Listeners).
     */
    private void setupListeners() {
        // Nút Quay lại
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Đóng Activity hiện tại
            }
        });

        // Nút Thông báo
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AssignGuideVehicleScrollableActivity.this, "Mở trang Thông báo.", Toast.LENGTH_SHORT).show();
                // TODO: Thêm Intent đến NotificationActivity
            }
        });

        // TabLayout Listener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Xử lý khi tab được chọn (Chờ Gán hoặc Đã Gán)
                String tabName = tab.getText().toString();
                Toast.makeText(AssignGuideVehicleScrollableActivity.this, "Chuyển sang tab: " + tabName, Toast.LENGTH_SHORT).show();
                // TODO: Cập nhật dữ liệu (RecyclerView) dựa trên tab được chọn
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Xử lý khi tab bị bỏ chọn (nếu cần)
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Xử lý khi tab được chọn lại
            }
        });

        // Nút Gán Ngay cho Tour 1
        assignButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Giả định tour code được lấy từ tourCodeTextView1
                handleAssignClick("DNIHA-2409");
            }
        });

        // Nút Gán Ngay cho Tour 2
        assignButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAssignClick("HCM-VT-011");
            }
        });

        // Nút Gán Ngay cho Tour 3
        assignButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAssignClick("HG-LOOP-05");
            }
        });
    }

    /**
     * Xử lý hành động nhấn nút "Gán Ngay"
     * @param tourCode Mã tour cần gán.
     */
    private void handleAssignClick(String tourCode) {
        Toast.makeText(this, "Mở màn hình gán tài nguyên cho Tour: " + tourCode, Toast.LENGTH_LONG).show();
        // TODO: Thêm Intent đến AssignmentDetailActivity và truyền tourCode đi
    }
}