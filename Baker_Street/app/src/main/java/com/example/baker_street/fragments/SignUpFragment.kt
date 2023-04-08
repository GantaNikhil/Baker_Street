package com.example.baker_street.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.baker_street.activities.MainActivity
import com.example.baker_street.activities.SignInActivity
import com.example.baker_street.databinding.LayoutSignupBinding
import com.example.baker_street.models.UserModel
import com.example.baker_street.viewmodels.AuthViewModel

class SignUpFragment : Fragment() {

    private lateinit var email: EditText
    private lateinit var name: EditText
    private lateinit var admno: EditText
    private lateinit var password: EditText
    private lateinit var cnfpassword: EditText
    private lateinit var signupviewmodel: AuthViewModel
    private val stuOrProf by lazy { arguments?.getString(STU_OR_PROF) }
    private lateinit var binding: LayoutSignupBinding
    private lateinit var userModel: UserModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutSignupBinding.inflate(layoutInflater)

        email = binding.email
        name = binding.name
        admno = binding.admno
        password = binding.password
        cnfpassword = binding.confirmPassword

        signupviewmodel = ViewModelProvider(this)[AuthViewModel::class.java]

        userModel = if (stuOrProf == "STUDENTS")
            UserModel(
                name = name.text.toString(),
                email = email.text.toString(),
                admno = admno.text.toString(),
                password = password.text.toString()
            )
        else
            UserModel(
                name = name.text.toString(),
                email = email.text.toString(),
                password = password.text.toString()
            )
        binding.btnSignUp.setOnClickListener {
            if (password.toString() != cnfpassword.toString()) {
                Toast.makeText(context, "Passwords are different", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            signupviewmodel.signUpStu(userModel)
        }
        initObservers()

        binding.goToLogin.setOnClickListener {
            val intent = Intent(context, SignInActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    private fun initObservers() {
        signupviewmodel.getMessageObserver()?.observe(viewLifecycleOwner) { it ->
            if (it == "OK2") {
                signupviewmodel.getSignUpStuObserver()?.observe(viewLifecycleOwner) {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }

            } else if (it == "Error2") {
                signupviewmodel.getSignUpStuObserver()?.observe(viewLifecycleOwner) {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        var STU_OR_PROF = "stu_or_prof"

        @JvmStatic
        fun newInstance(stu_or_prof: String) = SignUpFragment().apply {
            arguments = Bundle().apply {
                putSerializable(STU_OR_PROF, stu_or_prof)
            }
        }
    }
}