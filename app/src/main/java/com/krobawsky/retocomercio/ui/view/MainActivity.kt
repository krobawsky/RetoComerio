package com.krobawsky.retocomercio.ui.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.krobawsky.retocomercio.R
import com.krobawsky.retocomercio.data.model.UserModel
import com.krobawsky.retocomercio.databinding.ActivityMainBinding
import com.krobawsky.retocomercio.ui.viewmodel.UserViewModel

private const val TAG = "MyActivity"
class MainActivity : AppCompatActivity(), UserAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel.onCreate()

        val userAdapter = UserAdapter(this)

        binding.apply {
            recyclerViewUsers.apply {
                adapter = userAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
            }
        }

        userViewModel.userListModel.observe(this, Observer {
            userAdapter.submitList(it)
        })
        userViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })

    }

    @SuppressLint("SetTextI18n")
    override fun onItemClick(user: UserModel) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.fragment_user_dialog)
        val txtName = dialog.findViewById(R.id.txt_name) as TextView
        val txtUsername = dialog.findViewById(R.id.txt_username) as TextView
        val txtEmail = dialog.findViewById(R.id.txt_email) as TextView
        val txtPhone = dialog.findViewById(R.id.txt_phone) as TextView
        val txtWebsite = dialog.findViewById(R.id.txt_website) as TextView
        // address
        val txtStreet = dialog.findViewById(R.id.txt_street) as TextView
        val txtSuite = dialog.findViewById(R.id.txt_suite) as TextView
        val txtCity = dialog.findViewById(R.id.txt_city) as TextView
        // company
        val txtCompanyName = dialog.findViewById(R.id.txt_company_name) as TextView
        val txtCatchPhrase = dialog.findViewById(R.id.txt_company_catchPhrase) as TextView
        val txtBs = dialog.findViewById(R.id.txt_company_bs) as TextView

        txtName.text = "name: ${user.name}"
        txtUsername.text = "user: ${user.username}"
        txtEmail.text = "email: ${user.email}"
        txtPhone.text = "phone: ${user.phone}"
        txtWebsite.text = "website: ${user.website}"

        txtStreet.text = "street: ${user.address.street}"
        txtSuite.text = "suite: ${user.address.suite}"
        txtCity.text = "city: ${user.address.city}"

        txtCompanyName.text = "name: ${user.company.name}"
        txtCatchPhrase.text = "catch phrase: ${user.company.catchPhrase}"
        txtBs.text = "bs: ${user.company.bs}"

        dialog.show()
    }

}