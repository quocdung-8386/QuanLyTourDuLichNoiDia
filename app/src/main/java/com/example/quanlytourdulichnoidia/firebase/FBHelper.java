package com.example.quanlytourdulichnoidia.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Lớp tiện ích để truy cập các instance của Firebase Authentication và Firestore,
 * và xử lý các thao tác Auth cơ bản.
 */
public class FBHelper {
    private static FirebaseAuth mAuth;
    private static FirebaseFirestore db;
    private static final String USERS_COLLECTION = "users";

    /**
     * Interface Callback chung để xử lý kết quả của các thao tác Firebase bất đồng bộ.
     */
    public interface FirebaseCallback<T> {
        void onSuccess(T result);
        void onFailure(Exception e);
    }

    // --- UTILITY METHODS ---

    public static FirebaseAuth getFirebaseAuth() {
        if (mAuth == null) {
            mAuth = FirebaseAuth.getInstance();
        }
        return mAuth;
    }

    public static FirebaseFirestore getFirestoreInstance() {
        if (db == null) {
            db = FirebaseFirestore.getInstance();
            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(true)
                    .build();
            db.setFirestoreSettings(settings);
        }
        return db;
    }

    public static FirebaseUser getCurrentUser() {
        return getFirebaseAuth().getCurrentUser();
    }

    public static boolean isUserLoggedIn() {
        return getCurrentUser() != null;
    }

    public static String getCurrentUserId() {
        FirebaseUser user = getCurrentUser();
        return user != null ? user.getUid() : null;
    }

    public static String getCurrentUserEmail() {
        FirebaseUser user = getCurrentUser();
        return user != null ? user.getEmail() : "N/A";
    }

    public static String getCurrentUserDisplayName() {
        FirebaseUser user = getCurrentUser();
        return (user != null && user.getDisplayName() != null) ? user.getDisplayName() : "Khách hàng";
    }

    // --- AUTHENTICATION ACTIONS ---

    /**
     * Đăng ký người dùng mới bằng email và mật khẩu.
     * Sau khi đăng ký thành công, lưu thông tin cơ bản vào Firestore.
     * @param email Email của người dùng.
     * @param password Mật khẩu.
     * @param name Tên hiển thị.
     * @param callback Callback để trả về FirebaseUser hoặc lỗi.
     */
    public static void registerUser(String email, String password, final String name, final FirebaseCallback<FirebaseUser> callback) {
        getFirebaseAuth().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = task.getResult().getUser();
                        if (user != null) {
                            // Lưu profile cơ bản vào Firestore
                            saveUserProfile(user.getUid(), email, name, callback);
                        } else {
                            callback.onFailure(new Exception("Đăng ký thành công nhưng không tìm thấy người dùng."));
                        }
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }

    /**
     * Đăng nhập người dùng bằng email và mật khẩu.
     * @param email Email của người dùng.
     * @param password Mật khẩu.
     * @param callback Callback để trả về FirebaseUser hoặc lỗi.
     */
    public static void loginUser(String email, String password, final FirebaseCallback<FirebaseUser> callback) {
        getFirebaseAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(task.getResult().getUser());
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }

    /**
     * Đăng xuất người dùng hiện tại.
     */
    public static void logoutUser() {
        getFirebaseAuth().signOut();
    }

    // --- FIRESTORE PROFILE HANDLING (Internal) ---

    /**
     * Lưu thông tin người dùng cơ bản vào Firestore sau khi đăng ký.
     * @param uid User ID.
     * @param email User Email.
     * @param name User Display Name.
     * @param callback Callback để hoàn thành quá trình đăng ký.
     */
    private static void saveUserProfile(String uid, String email, String name, final FirebaseCallback<FirebaseUser> callback) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("uid", uid);
        userMap.put("email", email);
        userMap.put("name", name);
        userMap.put("createdAt", System.currentTimeMillis()); // Thời gian tạo tài khoản

        getFirestoreInstance().collection(USERS_COLLECTION).document(uid)
                .set(userMap, SetOptions.merge()) // SetOptions.merge() để tránh ghi đè toàn bộ document
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(getCurrentUser());
                    } else {
                        callback.onFailure(new Exception("Lưu thông tin người dùng thất bại: " + task.getException().getMessage()));
                    }
                });
    }
}