package com.example.learntestcomplete

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class LanguageSelectionDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireActivity())

        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_dialog, null)

        builder.setView(dialogView)

        dialogView.findViewById<Button>(R.id.btnJava).setOnClickListener {
            Toast.makeText(requireContext(), "Вибрано: Java", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        dialogView.findViewById<Button>(R.id.btnPython).setOnClickListener {
            Toast.makeText(requireContext(), "Вибрано: Python", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        dialogView.findViewById<Button>(R.id.btnKotlin).setOnClickListener {
            Toast.makeText(requireContext(), "Вибрано: Kotlin", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        return builder.create()
    }
}