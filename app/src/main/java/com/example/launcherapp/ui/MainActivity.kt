package com.example.launcherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.launcher.LauncherInstance
import com.example.launcher.model.ApplicationData
import com.example.launcherapp.R
import com.example.launcherapp.adapter.AppsAdapter
import android.content.Intent
import android.content.pm.ResolveInfo
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import com.example.launcherapp.model.ApplicationListModel


class MainActivity : AppCompatActivity(),  AppsAdapter.Listener{
    lateinit var  appListView: RecyclerView
    lateinit var searchInput: EditText
    lateinit var search: ImageView
    lateinit var model: ApplicationListModel
    lateinit var adapter: AppsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val applications = LauncherInstance.getApplicationList(this)
        appListView = findViewById(R.id.app_list)
        searchInput = findViewById(R.id.search_input)
        search = findViewById(R.id.search)
        setUpAdapter(ArrayList(applications))
        model = ApplicationListModel(ArrayList(applications))

        search.setOnClickListener {
            searchAndUpdate()
        }
    }

    private fun searchAndUpdate(){
        val input = searchInput.text.toString()
        val searchedList = model.searchApplication(input)
        adapter.update(searchedList)
    }

    private fun setUpAdapter(applications: ArrayList<ApplicationData>){
        appListView.layoutManager = LinearLayoutManager(this)
        adapter = AppsAdapter(this, ArrayList(applications),this)
        appListView.adapter = adapter
    }

    override fun itemClick(applicationData: ApplicationData) {
        applicationData.packageName?.let {
            val launchIntent = packageManager.getLaunchIntentForPackage(it)
            startActivity(launchIntent)
        }
    }
}