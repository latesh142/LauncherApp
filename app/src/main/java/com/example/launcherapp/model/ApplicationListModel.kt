package com.example.launcherapp.model

import com.example.launcher.model.ApplicationData

class ApplicationListModel(val applications: ArrayList<ApplicationData>) {
    private val searchedResult = ArrayList<ApplicationData>()

    fun searchApplication(input: String): ArrayList<ApplicationData>{
        searchedResult.clear()

        for(application in applications){
            if(application.appName.contains(input, ignoreCase = true))
                searchedResult.add(application)
        }

        return searchedResult
    }
}