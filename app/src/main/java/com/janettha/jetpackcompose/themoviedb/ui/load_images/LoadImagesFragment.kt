package com.janettha.jetpackcompose.themoviedb.ui.load_images

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.Timestamp
import com.janettha.jetpackcompose.themoviedb.R
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.FirebaseResponse
import com.janettha.jetpackcompose.themoviedb.databinding.FragmentLoadImagesBinding
import com.janettha.jetpackcompose.themoviedb.sys.config.Constants.Images.Companion.GALLERY_CODE
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import java.io.IOException

@AndroidEntryPoint
class LoadImagesFragment : Fragment() {
    private val mTag = "LoadImagesFragment"

    // region COMPONENTS
    private lateinit var binding: FragmentLoadImagesBinding

    private val viewModel by viewModels<LoadImagesViewModel>()
    // endregion

    // region VARIABLES
    private var imageUri: Uri? = null
    // endregion

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoadImagesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isLoading.observe(
            viewLifecycleOwner
        ) { response ->
            if (response) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }

        binding.imageViewToLoad.setOnClickListener {
            getImage()
        }

        binding.buttonLoadImages.setOnClickListener {
            if (imageUri != null) {
                viewModel.savePhoto(Timestamp.now().seconds.toString(), imageUri.toString())
            } else {
                handleSaveResponse(FirebaseResponse.Error(getString(R.string.load_foto_warning_data)))
            }
        }

        viewModel.result.observe(
            viewLifecycleOwner
        ) {
            handleSaveResponse(it)
            viewModel.resetResult()
            binding.imageViewToLoad.setBackgroundResource(R.drawable.img_not_available)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            if (data != null) {
                imageUri = data.data
                binding.imageViewToLoad.setImageURI(imageUri)
                try {
                    loadImageData(imageUri)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    // region PRIVATE
    private fun handleSaveResponse(result: FirebaseResponse) {
        when (result) {
            is FirebaseResponse.Success -> {
                Toast.makeText(context, R.string.success, Toast.LENGTH_LONG).show()
            }
            is FirebaseResponse.Error -> {
                Toast.makeText(context, R.string.load_foto_warning_data, Toast.LENGTH_LONG).show()
            }
            else -> Toast.makeText(context, R.string.error, Toast.LENGTH_LONG).show()
        }
    }

    private fun getImage() {
        startActivityForResult(
            Intent(Intent.ACTION_GET_CONTENT)
                .setType("image/*"), GALLERY_CODE
        )
    }

    private fun loadImageData(data: Uri?) {
        val bitmap = MediaStore.Images.Media.getBitmap(
            this.context?.contentResolver, Uri.parse(
                imageUri.toString()
            )
        )

        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val imageInByte: ByteArray = stream.toByteArray()
        val lengthBmp = imageInByte.size.toLong()

        Log.d(
            mTag, "loadImageData: " +
                    "size: $lengthBmp \n" +
                    "uri: ${data?.path}"
        )
    }
    // endregion

}