package com.example.petstoretest.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.petstoretest.R
import com.example.petstoretest.api.RetrofitInstance
import com.example.petstoretest.model.Pet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPetByIdFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_get_pet_by_id, container, false)
        super.onViewCreated(view, savedInstanceState)
        Log.d("GetPetByIdFragment", "GetPetByIdFragment onViewCreated")

        val etPetId = view.findViewById<EditText>(R.id.etPetId)
        val btnGetPetById = view.findViewById<Button>(R.id.btnGetPetById)
        val tvPetInfo = view.findViewById<TextView>(R.id.tvPetInfo)

        btnGetPetById.setOnClickListener {
            val petId = etPetId.text.toString().toInt()

            RetrofitInstance.api.getPetById(petId).enqueue(object : Callback<Pet> {
                override fun onResponse(call: Call<Pet>, response: Response<Pet>) {
                    if (response.isSuccessful) {
                        val pet = response.body()
                        tvPetInfo.text = "Pet ID: ${pet?.id}, Name: ${pet?.name}, Status: ${pet?.status}"
                    } else {
                        Log.e("GetPetByIdFragment", "Error: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<Pet>, t: Throwable) {
                    Log.e("GetPetByIdFragment", "Failure: ${t.message}")
                }
            })
        }

        return view
    }
}
