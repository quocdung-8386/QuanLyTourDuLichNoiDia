package com.example.quanlytourdulichnoidia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.quanlytourdulichnoidia.R;
import com.google.android.material.tabs.TabLayout;

public class CustomerCareActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private Button btnOpenSupportTicket;
    private Button btnSendWarning;
    // CardView cho Phân tích Cảm xúc KH (dựa trên cấu trúc, không có ID trong XML)
    // Cần phải gán ID cho CardView này trong XML để thực hiện click.
    // private CardView sentimentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Giả sử tên file layout là activity_customer_care
        setContentView(R.layout.activity_customer_care);

        initViews();
        setupListeners();
    }

    /**
     * Ánh xạ các thành phần giao diện (View) bằng ID từ XML.
     */
    private void initViews() {
        tabLayout = findViewById(R.id.tabLayout);
        btnOpenSupportTicket = findViewById(R.id.btnOpenSupportTicket);
        btnSendWarning = findViewById(R.id.btnSendWarning);

        // Ghi chú: Các CardView (Phân tích Cảm xúc và Danh sách phiếu) cần ID nếu muốn
        // xử lý sự kiện click riêng biệt cho từng Card.
    }

    /**
     * Thiết lập các sự kiện lắng nghe (Listeners).
     */
    private void setupListeners() {
        // Nút Mở Phiếu hỗ trợ
        btnOpenSupportTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomerCareActivity.this, "Chuyển đến màn hình Tạo Phiếu hỗ trợ mới.", Toast.LENGTH_SHORT).show();
                // TODO: Thêm Intent đến Activity tạo phiếu hỗ trợ (e.g., NewTicketActivity)
            }
        });

        // Nút Gửi Cảnh báo
        btnSendWarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomerCareActivity.this, "Mở chức năng Gửi Cảnh báo Khách hàng.", Toast.LENGTH_SHORT).show();
                // TODO: Thêm Intent đến Activity gửi cảnh báo (e.g., WarningActivity)
            }
        });

        // TabLayout Listener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String tabTitle = tab.getText().toString();
                Toast.makeText(CustomerCareActivity.this, "Xem danh sách theo tab: " + tabTitle, Toast.LENGTH_SHORT).show();
                // TODO: Cập nhật dữ liệu danh sách phiếu hỗ trợ/phản hồi dựa trên tab

                // Giả định: 0 = Theo dõi Hỗ trợ; 1 = Phản hồi & Khiếu nại
                int position = tab.getPosition();
                if (position == 0) {
                    // Load danh sách Theo dõi Hỗ trợ
                } else if (position == 1) {
                    // Load danh sách Phản hồi & Khiếu nại
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Không cần làm gì khi tab bị bỏ chọn
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Có thể làm mới dữ liệu khi chọn lại tab
            }
        });

        // Xử lý sự kiện cho các CardView (nếu có ID):
        // Nếu CardView Phân tích Cảm xúc có ID, bạn sẽ xử lý như sau:
        /*
        sentimentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomerCareActivity.this, "Mở Báo cáo Phân tích Cảm xúc.", Toast.LENGTH_SHORT).show();
                // TODO: Thêm Intent đến SentimentReportActivity
            }
        });
        */

        // Xử lý sự kiện cho các mục phiếu hỗ trợ (nếu có ID):
        /* ticketItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomerCareActivity.this, "Mở chi tiết phiếu #1205.", Toast.LENGTH_SHORT).show();
                // TODO: Thêm Intent đến TicketDetailActivity và truyền ID phiếu
            }
        });
        */
    }
}