package com.example.learntestcomplete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.learntestcomplete.databinding.BottomSheetExampleBinding

class BottomSheetExample : BottomSheetDialogFragment() {

    private var _binding: BottomSheetExampleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetExampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLike.setOnClickListener {
            Toast.makeText(requireContext(), "Ти натиснув Лайк 👍", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        binding.btnShare.setOnClickListener {
            Toast.makeText(requireContext(), "Поділився 🙌", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}