package com.example.interactiveregisterform

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.interactiveregisterform.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận dữ liệu từ Intent gửi sang
        val fullName = intent.getStringExtra("EXTRA_FULL_NAME") ?: "Chưa nhập"
        val email = intent.getStringExtra("EXTRA_EMAIL") ?: "Chưa nhập"
        val phone = intent.getStringExtra("EXTRA_PHONE") ?: "Chưa nhập"
        val studentId = intent.getStringExtra("EXTRA_STUDENT_ID") ?: "Chưa nhập"
        val className = intent.getStringExtra("EXTRA_CLASS") ?: "Chưa nhập"

        // Đổ dữ liệu lên giao diện
        binding.tvFullName.text = "Họ tên: $fullName"
        binding.tvEmail.text = "Email: $email"
        binding.tvPhone.text = "SĐT: $phone"
        binding.tvStudentId.text = "MSV: $studentId"
        binding.tvClass.text = "Lớp: $className"

        // Nút quay lại màn hình đăng ký
        binding.btnBackToRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }
}