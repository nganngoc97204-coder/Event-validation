package com.example.interactiveregisterform

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.interactiveregisterform.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Gọi hàm kiểm tra lỗi theo thời gian thực (Event-validation)
        setupRealtimeValidation()

        // Khi bấm nút Đăng ký
        binding.btnRegister.setOnClickListener {
            if (validateAllFields()) {
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("EXTRA_FULL_NAME", binding.edtFullName.text.toString().trim())
                    putExtra("EXTRA_EMAIL", binding.edtEmail.text.toString().trim())
                    putExtra("EXTRA_PHONE", binding.edtPhone.text.toString().trim())
                    putExtra("EXTRA_STUDENT_ID", binding.edtStudentId.text.toString().trim())
                    putExtra("EXTRA_CLASS", binding.edtClass.text.toString().trim())
                }
                startActivity(intent)
            }
        }

        // Nút phụ chuyển thẳng sang màn hình chi tiết
        binding.btnGoToDetail.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRealtimeValidation() {
        binding.edtFullName.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrBlank()) binding.tilFullName.error = "Họ tên không được trống"
            else binding.tilFullName.error = null
        }

        binding.edtEmail.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrBlank()) {
                binding.tilEmail.error = "Email không được trống"
            } else if (!Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                binding.tilEmail.error = "Email không đúng định dạng"
            } else {
                binding.tilEmail.error = null
            }
        }

        binding.edtPhone.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrBlank()) {
                binding.tilPhone.error = "SĐT không được trống"
            } else if (!text.matches(Regex("^[0-9]{10}$"))) {
                binding.tilPhone.error = "SĐT phải gồm đúng 10 chữ số"
            } else {
                binding.tilPhone.error = null
            }
        }

        binding.edtStudentId.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrBlank()) binding.tilStudentId.error = "MSV không được trống"
            else binding.tilStudentId.error = null
        }

        binding.edtClass.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrBlank()) binding.tilClass.error = "Lớp không được trống"
            else binding.tilClass.error = null
        }
    }

    private fun validateAllFields(): Boolean {
        var isValid = true

        val fullName = binding.edtFullName.text.toString().trim()
        val email = binding.edtEmail.text.toString().trim()
        val phone = binding.edtPhone.text.toString().trim()
        val studentId = binding.edtStudentId.text.toString().trim()
        val className = binding.edtClass.text.toString().trim()

        if (fullName.isEmpty()) {
            binding.tilFullName.error = "Họ tên không được trống"
            isValid = false
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Email không hợp lệ"
            isValid = false
        }
        if (phone.isEmpty() || !phone.matches(Regex("^[0-9]{10}$"))) {
            binding.tilPhone.error = "SĐT không hợp lệ (cần 10 số)"
            isValid = false
        }
        if (studentId.isEmpty()) {
            binding.tilStudentId.error = "MSV không được trống"
            isValid = false
        }
        if (className.isEmpty()) {
            binding.tilClass.error = "Lớp không được trống"
            isValid = false
        }

        return isValid
    }
}