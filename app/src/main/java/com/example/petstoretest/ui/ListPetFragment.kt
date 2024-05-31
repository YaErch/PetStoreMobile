package com.example.petstoretest.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

import com.example.petstoretest.R
import com.example.petstoretest.api.RetrofitInstance
import com.example.petstoretest.model.Pet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListPetFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_pets, container, false)
        super.onViewCreated(view, savedInstanceState)
        Log.d("ListPetFragment", "ListPetFragment onViewCreated")
        val btnGetPets = view.findViewById<Button>(R.id.btnGetPets)
        val tvAllPetsInfo = view.findViewById<TextView>(R.id.tvAllPetsInfo)

        btnGetPets.setOnClickListener {
            val status = "available" // Укажите нужный статус (available, pending, sold)

            RetrofitInstance.api.getPetsByStatus(status).enqueue(object : Callback<List<Pet>> {
                override fun onResponse(call: Call<List<Pet>>, response: Response<List<Pet>>) {
                    if (response.isSuccessful) {
                        val pets = response.body()
                        val petsInfo = StringBuilder()
                        pets?.forEachIndexed { index, pet ->
                            petsInfo.append("Pet ${index + 1}: Name - ${pet.name}, Status - ${pet.status}\n")
                        }
                        tvAllPetsInfo.text = petsInfo.toString()
                    } else {
                        Log.e("ListPetsFragment", "Error: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<List<Pet>>, t: Throwable) {
                    Log.e("ListPetsFragment", "Failure: ${t.message}")
                }
            })
        }

        return view
    }
}
