package com.example.quanlytourdulichnoidia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlytourdulichnoidia.R;
import com.example.quanlytourdulichnoidia.firebase.FBHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;

/**
 * Activity xử lý giao diện Đăng nhập và tương tác với Firebase Authentication.
 */
public class LoginActivity extends AppCompatActivity {

    // Khai báo các View cần tương tác
    private TextInputEditText edtEmail;
    private TextInputEditText edtPassword;
    private CheckBox chkRemember;
    private TextView tvForgot;
    private Button btnLogin;
    private TextView tvRegister;

    // Giả định bạn có MainActivity để chuyển hướng sau khi đăng nhập thành công
    // private Class<?> mainActivityClass = MainActivity.class;
    // Giả định bạn có SignUpActivity để chuyển hướng
    private Class<?> signUpActivityClass = SignupActivity.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Giả sử tên file layout là activity_login
        setContentView(R.layout.activity_login);

        // Kiểm tra nếu người dùng đã đăng nhập, chuyển hướng ngay lập tức
        if (FBHelper.isUserLoggedIn()) {
            // startActivity(new Intent(LoginActivity.this, mainActivityClass));
            // finish();
            // Tạm thời chỉ thông báo
            Toast.makeText(this, "Bạn đã đăng nhập rồi.", Toast.LENGTH_SHORT).show();
        }

        initViews();
        setupListeners();
    }

    /**
     * Ánh xạ các thành phần giao diện (View) bằng ID từ XML.
     */
    private void initViews() {
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        chkRemember = findViewById(R.id.chkRemember);
        tvForgot = findViewById(R.id.tvForgot);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
    }

    /**
     * Thiết lập các sự kiện lắng nghe (Listeners).
     */
    private void setupListeners() {
        // Nút Đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        // Liên kết Quên mật khẩu
        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Chức năng Quên mật khẩu đang được phát triển.", Toast.LENGTH_SHORT).show();
                // TODO: Thêm Intent đến ForgotPasswordActivity
            }
        });

        // Liên kết Đăng ký
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, signUpActivityClass);
                startActivity(intent);
                // Không gọi finish() ở đây nếu bạn muốn người dùng có thể quay lại màn hình đăng nhập
            }
        });
    }

    /**
     * Lấy dữ liệu người dùng, kiểm tra hợp lệ và gọi FBHelper để đăng nhập.
     */
    private void performLogin() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString();

        // 1. Kiểm tra hợp lệ (Validation)
        if (email.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập Email hoặc SĐT.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra định dạng email cơ bản (giả định đây là email, không phải SĐT)
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // Nếu bạn muốn hỗ trợ đăng nhập bằng SĐT, bạn cần thay đổi logic validation này
            Toast.makeText(this, "Định dạng Email không hợp lệ.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập mật khẩu.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tắt nút đăng nhập trong khi xử lý
        btnLogin.setEnabled(false);
        btnLogin.setText("Đang đăng nhập...");

        // 2. Gọi FBHelper để đăng nhập
        FBHelper.loginUser(email, password, new FBHelper.FirebaseCallback<FirebaseUser>() {
            @Override
            public void onSuccess(FirebaseUser user) {
                // Hành động thành công: Hiển thị thông báo và chuyển hướng
                Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_LONG).show();

                // 3. Chuyển hướng sau khi đăng nhập thành công
                // Intent intent = new Intent(LoginActivity.this, mainActivityClass);
                // startActivity(intent);
                // finish();

                // Tạm thời thông báo thành công và giữ ở màn hình hiện tại
                btnLogin.setEnabled(true);
                btnLogin.setText(R.string.login_button); // Trả về text gốc
            }

            @Override
            public void onFailure(Exception e) {
                // Hành động thất bại: Hiển thị thông báo lỗi
                String errorMessage = "Đăng nhập thất bại. Vui lòng kiểm tra lại Email và Mật khẩu.";
                if (e != null && e.getLocalizedMessage() != null) {
                    errorMessage = "Lỗi: " + e.getLocalizedMessage();
                }

                Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_LONG).show();

                // Bật lại nút đăng nhập
                btnLogin.setEnabled(true);
                btnLogin.setText(R.string.login_button); // Trả về text gốc
            }
        });
    }
}