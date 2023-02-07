package com.christianfleschhut.firstappbasicactivityapi22

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.christianfleschhut.firstappbasicactivityapi22.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.button.setOnClickListener {
            val email = binding.emailAddress.text.toString()
            val password = binding.password.text.toString()
            val message = getString(R.string.message_text, email, password)

            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            Log.i("FirstFragment", "onViewCreated: $message")
        }

        val viewModel by viewModels<MainViewModel>()

        binding.addQuantityButton.setOnClickListener { viewModel.increaseQuantity() }
        binding.removeQuantityButton.setOnClickListener { viewModel.decreaseQuantity() }
        binding.checkoutButton.setOnClickListener { viewModel.checkout() }

        viewModel.quantity.observe(this) { updateTotalCount(it) }
        viewModel.totalAmount.observe(this) { handleCheckout(it) }
    }

    private fun updateTotalCount(total: Int) {
        binding.total.text = total.toString()
    }
    
    private fun handleCheckout(totalAmount: Int) {
        Toast.makeText(context, "Total amount: $totalAmount", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}