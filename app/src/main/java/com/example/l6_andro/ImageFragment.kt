package com.example.l6_andro

import android.content.Context
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
import com.example.l6_andro.databinding.FragmentImageBinding
import com.example.l6_andro.lab6.ImageItem
import java.io.FileNotFoundException
import java.io.InputStream

private const val ARG_PARAM1 = "imageUri"
class ImageFragment : Fragment() {
    private lateinit var _binding: FragmentImageBinding
    private var imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageUri = it.getParcelable(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentImageBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            imageUri?.let {
                _binding.imageImg.setImageBitmap(requireContext().contentResolver.loadThumbnail(it,
                    Size(280,280), null))
            }
        }
        else
            _binding.imageImg.setImageBitmap(getBitmapFromUri(requireContext(), imageUri))
    }
    fun getBitmapFromUri(mContext: Context, uri: Uri?): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val image_stream: InputStream
            try {
                image_stream = uri?.let {
                    mContext.getContentResolver().openInputStream(it)
                }!!
                bitmap = BitmapFactory.decodeStream(image_stream)
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
        fun newInstance(image: ImageItem): ImageFragment {
            val fragment = ImageFragment()
            val args = Bundle()
            args.putParcelable("imageUri", image.curi)
            fragment.arguments = args
            return fragment
        }
    }
}