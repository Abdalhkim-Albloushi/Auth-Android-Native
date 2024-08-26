package com.example.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.login.databinding.FragmentRegisterBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class RegisterFragment : Fragment() {

   private lateinit var binding:FragmentRegisterBinding
    private val REQUEST_IMAGE_CAPTURE = 1
    private val PICK_IMAGE_REQUEST = 2

    private lateinit var pickerDateEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =FragmentRegisterBinding.inflate(layoutInflater)
        binding.buttonPickImage.setOnClickListener {
            showBottomSheet(binding.root.context)
        }

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.editRegisterDate.setOnClickListener {


            // Get today's date in UTC milliseconds
            val today = MaterialDatePicker.todayInUtcMilliseconds()
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))

            // Set the calendar to January of the current year
            calendar.timeInMillis = today
            calendar[Calendar.MONTH] = Calendar.JANUARY
            val janThisYear = calendar.timeInMillis

            // Set the calendar to December of the current year
            calendar.timeInMillis = today
            calendar[Calendar.MONTH] = Calendar.DECEMBER
            val decThisYear = calendar.timeInMillis

            // Build constraints for the date picker
            val constraintsBuilder = CalendarConstraints.Builder()
                .setStart(1900)
                .setEnd(decThisYear)

            // Build the date picker
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(today)
                .setCalendarConstraints(constraintsBuilder.build())
                .build()

            // Show the date picker
            datePicker.show(parentFragmentManager, "DATE_PICKER")

            // Listen for the positive button click
            datePicker.addOnPositiveButtonClickListener { selection ->
                // Handle the selected date
                val selectedDate = calendar.apply {
                    timeInMillis = selection

                }.time

                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate)
                binding.editRegisterDate.setText("$formattedDate")
            }
        }
        return binding.root


    }




    private fun showBottomSheet(context: Context) {
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_menu, null)
        val bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetDialog.setContentView(bottomSheetView)

        val buttonTakePhoto = bottomSheetView.findViewById<TextView>(R.id.buttonTakePhoto)
        val buttonChooseFromGallery = bottomSheetView.findViewById<TextView>(R.id.buttonChooseFromGallery)
        val buttonCancel = bottomSheetView.findViewById<TextView>(R.id.buttonCancel)


            buttonTakePhoto.setOnClickListener {
            // Handle taking a photo
            takePhoto()
            bottomSheetDialog.dismiss()
        }

                buttonChooseFromGallery.setOnClickListener {
            // Handle choosing from gallery
            chooseFromGallery()
            bottomSheetDialog.dismiss()
        }

        buttonCancel.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }



    @SuppressLint("QueryPermissionsNeeded")
    private fun takePhoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)


    }

    private fun chooseFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)




    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)



        Log.i("========>","requestCode==>$requestCode =====  resultCode===>$resultCode === data===>$data")
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as? Bitmap
                    val imageView = view?.findViewById<ImageView>(R.id.imageView)
                    imageView?.setImageBitmap(imageBitmap)
                }

                PICK_IMAGE_REQUEST -> {
                    val imageUri = data?.data
                    val imageView = view?.findViewById<ImageView>(R.id.imageView)
                    imageView?.setImageURI(imageUri)

                    // Handle the image selected from gallery
                    // For example: set it to an ImageView
                }
            }
        }
    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
//            val imageUri = data.data
//
//            // Display the selected image in an ImageView
//            val imageView = view?.findViewById<ImageView>(R.id.imageView)
//            imageView?.setImageURI(imageUri)
//        }
//    }




}