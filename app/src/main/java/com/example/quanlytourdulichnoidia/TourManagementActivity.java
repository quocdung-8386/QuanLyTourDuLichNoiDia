package com.example.quanlytourdulichnoidia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.quanlytourdulichnoidia.R;

/**
 * Activity xử lý màn hình Quản lý Tour (Tour Management Main Screen).
 * Màn hình này cung cấp các tùy chọn để tạo mới, chỉnh sửa, hoặc gán hướng dẫn viên cho Tour.
 */
public class TourManagementActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStartCreating;
    private CardView cardEditTour; // Giả định CardView cho Chỉnh sửa Tour
    private CardView cardAssignGuide; // Giả định CardView cho Gán hướng dẫn viên

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Giả định tên file layout là activity_tour_management
        setContentView(R.layout.activity_tour_management);

        initViews();
        setupListeners();
    }

    /**
     * Ánh xạ các thành phần giao diện (View) bằng ID từ XML.
     * Lưu ý: Các CardView hành động chưa có ID trong XML, tôi sẽ phải tìm chúng qua thứ tự hoặc
     * giả định ID cho chúng. Tôi sẽ giả định ID cho các CardView.
     */
    private void initViews() {
        // Nút Bắt đầu tạo Tour
        btnStartCreating = findViewById(R.id.btnStartCreating);

        // Các CardView hành động (Cần thêm ID vào XML cho các CardView này)
        // Dựa trên cấu trúc layout, tôi sẽ giả định các CardView có ID tương ứng.

        // Cần thêm ID:
        // CardView 1 (Chỉnh sửa Tour) -> id/cardEditTour
        // CardView 2 (Gán hướng dẫn viên) -> id/cardAssignGuide

        // Do thiếu ID, tôi sẽ tạm thời chỉ ánh xạ btnStartCreating

        // Nếu có ID:
        // cardEditTour = findViewById(R.id.cardEditTour);
        // cardAssignGuide = findViewById(R.id.cardAssignGuide);
    }

    /**
     * Thiết lập các sự kiện lắng nghe (Listeners).
     */
    private void setupListeners() {
        if (btnStartCreating != null) {
            btnStartCreating.setOnClickListener(this);
        }

        // Nếu có ID cho CardView:
        /*
        if (cardEditTour != null) {
            cardEditTour.setOnClickListener(this);
        }
        if (cardAssignGuide != null) {
            cardAssignGuide.setOnClickListener(this);
        }
        */
    }

    /**
     * Xử lý tất cả sự kiện click.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnStartCreating) {
            // Chuyển sang màn hình Thiết kế Tour (DesignTourActivity)
            Intent intent = new Intent(TourManagementActivity.this, DesignTourActivity.class);
            startActivity(intent);
        }
        /* else if (id == R.id.cardEditTour) {
            Toast.makeText(this, "Mở màn hình chọn Tour để chỉnh sửa.", Toast.LENGTH_SHORT).show();
            // TODO: Intent đến màn hình danh sách Tour
        } else if (id == R.id.cardAssignGuide) {
            Toast.makeText(this, "Mở màn hình Gán hướng dẫn viên.", Toast.LENGTH_SHORT).show();
            // TODO: Intent đến màn hình gán HDV
        }
        */
    }
}
