package com.example.l6_andro

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Size
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.l6_andro.databinding.FragmentMainBinding
import java.io.FileNotFoundException
import java.io.InputStream

class MainFragment : Fragment() {
    lateinit var invitation: TextView
    lateinit var authorName: TextView
    lateinit var authorSurname: TextView
    lateinit var imageMain: ImageView
    lateinit var setting: TextView

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
        val view = FragmentMainBinding.inflate(inflater, container, false)
        invitation = view.invitationText
        authorName = view.authorNameTxt
        authorSurname = view.authorSurnameTxt
        imageMain = view.invitationImage
        setting = view.additionalSetting


        return view.root
    }

    fun applyData() {
        val data: SharedPreferences =
            requireActivity().getSharedPreferences("L5_preferences", Context.MODE_PRIVATE)
        invitation.text = data.getString("invitation", "Fragment to start on")
        authorName.text = data.getString("authorName", "Jakub")
        authorSurname.text = data.getString("authorSurname", "Cebula")
        imageMain.setImageResource(data.getInt("image", R.drawable.emotion_neutral))
        if (data.contains("imageUri")) {
            val img = Uri.parse(data.getString("imageUri", ""))
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                img.let {
                    imageMain.setImageBitmap(
                        requireContext().contentResolver.loadThumbnail(
                            it,
                            Size(400, 400), null
                        )
                    )
                }
            } else {
                val bitmap = getBitmapFromUri(requireContext(), img)
                if (bitmap != null) {
                    imageMain.setImageBitmap(bitmap).apply { Size(400, 400) }
                }
            }
        }

        val data2: SharedPreferences =
            requireActivity().getSharedPreferences("additional", Context.MODE_PRIVATE)
        setting.text = data2.getString("str2", "Setting Def")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyData()

    }

    fun getBitmapFromUri(mContext: Context, uri: Uri?): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val imageStream: InputStream
            try {
                imageStream = uri?.let {
                    mContext.contentResolver.openInputStream(it)
                }!!
                bitmap = BitmapFactory.decodeStream(imageStream)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onResume() {
        super.onResume()
        applyData()
    }
}