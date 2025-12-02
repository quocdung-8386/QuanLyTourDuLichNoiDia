package com.example.quanlytourdulichnoidia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlytourdulichnoidia.firebase.FBHelper;
import com.example.quanlytourdulichnoidia.R; // Đảm bảo import R
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    // Khai báo các View cần tương tác
    private ImageButton btnBack;
    private EditText etName, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister, btnGoogleRegister;
    private TextView tvLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Giả sử tên file layout là activity_signup
        setContentView(R.layout.activity_signup);

        initViews();
        setupListeners();
    }

    /**
     * Ánh xạ các thành phần giao diện (View) bằng ID từ XML.
     */
    private void initViews() {
        btnBack = findViewById(R.id.btn_back);
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnRegister = findViewById(R.id.btn_register);
        btnGoogleRegister = findViewById(R.id.btn_google_register);
        tvLoginLink = findViewById(R.id.tv_login_link);

    }

    private void setupListeners() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();
            }
        });
        tvLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Giả định bạn có LoginActivity để chuyển sang màn hình đăng nhập
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnGoogleRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignupActivity.this, "Đang khởi tạo Google Sign-In...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Lấy dữ liệu người dùng, kiểm tra hợp lệ và gọi FirebaseHelper để đăng ký.
     */
    private void performRegistration() {
        final String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        // 1. Kiểm tra hợp lệ (Validation)
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin.", Toast.LENGTH_LONG).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Mật khẩu phải có ít nhất 6 ký tự.", Toast.LENGTH_LONG).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu xác nhận không khớp.", Toast.LENGTH_LONG).show();
            return;
        }

        // Tắt nút đăng ký trong khi xử lý
        btnRegister.setEnabled(false);
        btnRegister.setText("Đang đăng ký...");

        // 2. Gọi FirebaseHelper để đăng ký
        FBHelper.registerUser(email, password, name, new FBHelper.FirebaseCallback<FirebaseUser>() {
            @Override
            public void onSuccess(FirebaseUser user) {
                // Hành động thành công: Hiển thị thông báo
                Toast.makeText(SignupActivity.this, "Đăng ký thành công! Chào mừng, " + name, Toast.LENGTH_LONG).show();

                // 3. Chuyển hướng sau khi đăng ký thành công
                // Giả định chuyển đến màn hình chính (MainActivity)
                // Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                // startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Exception e) {
                // Hành động thất bại: Hiển thị thông báo lỗi
                Toast.makeText(SignupActivity.this, "Đăng ký thất bại: " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                // Bật lại nút đăng ký
                btnRegister.setEnabled(true);
                btnRegister.setText("Đăng ký");
            }
        });
    }
}
// Note: Bạn cần tạo một Activity tên là LoginActivity và MainActivity trong project để Intent hoạt động.