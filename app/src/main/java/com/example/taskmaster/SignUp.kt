package com.example.taskmaster

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmaster.databinding.FragmentSignUpBinding


class SignUp : Fragment() {

    private var _binding: FragmentSignUpBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var mail = false
    private var pass = false
    private var name = false


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAccept.isEnabled = false
        listeners()

        binding.btnAccept.setOnClickListener {
            Toast(context).showCustomToast ("Hello! This is a custom Toast!", requireActivity())
            findNavController().navigate(R.id.action_signUp_to_fragmentHome)
        }

        binding.btnGoBack.setOnClickListener{
            findNavController().navigate(R.id.action_signUp_to_login)
        }
    }

    private fun listeners(){
        val emailPattern = "[a-zA-Z\\d._-]+@[a-z]+\\.+[a-z]+"
        val passPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+._=])(?=\\S+$).{4,}$"

        val tvName = binding.userName
        val tvEmail = binding.userMail
        val tvPass = binding.pass
        val tvPass2 = binding.passConfirmation
        tvEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if(!s.matches(emailPattern.toRegex()) && s.isNotEmpty()){
                    tvEmail.setError("Debe de ser un correo electronico valido!")
                    mail = false
                }else{
                    mail = true
                }
                next()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // other stuffs
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // other stuffs
            }
        })

        tvPass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if(!s.matches(passPattern.toRegex()) && s.isNotEmpty()){
                    tvPass.error = "La contraseña debe contener al menos 8 caracteres, un numero, una letra y un caracter especial"
                    pass = false
                }
                next()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // other stuffs
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // other stuffs
            }
        })

        tvPass2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if(s.toString() == tvPass.text.toString() && s.toString().isNotEmpty()){
                    pass = true
                }else{
                    pass = false
                    tvPass2.error = "La contraseña debe ser igual"
                }
                next()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // other stuffs
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // other stuffs
            }
        })

        tvName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if(s.isNotEmpty() && binding.userName.text.length < 8){
                    name = true
                }else{
                    name = false
                    tvName.error = "El nombre debe de ser menor a 8 caracteres"
                }
                next()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // other stuffs
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // other stuffs
            }
        })

    }

    fun next(){
        binding.btnAccept.isEnabled = name && pass && mail
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}