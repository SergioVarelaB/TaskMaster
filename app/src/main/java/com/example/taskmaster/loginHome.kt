package com.example.taskmaster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.taskmaster.databinding.FragmentLoginHomeBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class loginHome : Fragment() {

    private var _binding: FragmentLoginHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // This callback will only be called when MyFragment is at least Started.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            // Handle the back button event
            activity?.finish()
        }

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginHome_to_login)
        }

        binding.btnSignUp.setOnClickListener{
            findNavController().navigate(R.id.action_loginHome_to_signUp)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}