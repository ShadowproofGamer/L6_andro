package com.example.l5_andro

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.l5_andro.databinding.Fragment1Binding

class Fragment1 : Fragment() {
    private lateinit var _binding: Fragment1Binding
    lateinit var invitation: EditText
    lateinit var authorName: EditText
    lateinit var authorSurname: EditText
    lateinit var saveButton: Button
    lateinit var data: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = Fragment1Binding.inflate(inflater, container, false)
        invitation = _binding.invitationEdit
        authorSurname = _binding.authorSurnameEdit
        authorName = _binding.authorNameEdit
        saveButton = _binding.applyButton1


        return _binding.root
    }
    fun setData(){
        val var1 = invitation.text.toString()
        val var2 = authorName.text.toString()
        val var3 = authorSurname.text.toString()
        val editor = data.edit()
        editor.putString("invitation", var1)
        editor.putString("authorName", var2)
        editor.putString("authorSurname", var3)
        editor.apply()
        requireActivity().onBackPressed()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = requireActivity().getSharedPreferences("L5_preferences", Context.MODE_PRIVATE)
        invitation.setText(data.getString("invitation", "Fragment to start on"))
        authorName.setText(data.getString("authorName", "Jakub"))
        authorSurname.setText(data.getString("authorSurname", "Cebula"))



        saveButton.setOnClickListener { _ ->
            setData()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment1().apply {
                arguments = Bundle().apply {

                }
            }
    }

}