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
import kotlin.random.Random

class AddPetFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_pet, container, false)
        super.onViewCreated(view, savedInstanceState)
        Log.d("AddPetFragment", "AddPetFragment onViewCreated")
        val etName = view.findViewById<EditText>(R.id.etName)
        val etStatus = view.findViewById<EditText>(R.id.etStatus)
        val btnAddPet = view.findViewById<Button>(R.id.btnAddPet)
        val tvPetInfo = view.findViewById<TextView>(R.id.tvPetInfo)
        val tvPetId = view.findViewById<TextView>(R.id.tvPetId)

        btnAddPet.setOnClickListener {
            val name = etName.text.toString()
            val status = etStatus.text.toString()

            val randomId = Random.nextLong(1, 1001) // Генерация случайного целого числа от 1 до 1000

            val newPet = Pet(id = randomId, name = name, status = status)

            RetrofitInstance.api.addPet(newPet).enqueue(object : Callback<Pet> {
                override fun onResponse(call: Call<Pet>, response: Response<Pet>) {
                    if (response.isSuccessful) {
                        val addedPet = response.body()
                        tvPetInfo.text = "New Pet Added: ${addedPet?.name}, Status: ${addedPet?.status}"
                        tvPetId.text = "Pet ID: ${addedPet?.id}"
                        Log.d("AddPetFragment", "Request successful. Response: $addedPet")
                    } else {
                        Log.e("AddPetFragment", "Error: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<Pet>, t: Throwable) {
                    Log.e("AddPetFragment", "Failure: ${t.message}")
                }
            })
        }


        return view
    }
}


