package com.example.launcherapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.launcherapp.R

import android.widget.TextView
import com.example.launcher.model.ApplicationData

class AppsAdapter (private val mContext: Context, list : ArrayList<ApplicationData>, private val listener: Listener) :
    RecyclerView.Adapter<AppsAdapter.ListHolder>() {
    var inflater: LayoutInflater = LayoutInflater.from(mContext)
    var applicationsList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val view = inflater.inflate(R.layout.item_app, parent, false)
        return ListHolder(view)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val data = applicationsList[position]
        holder.appName?.text = mContext.getString(R.string.app_name_label) + data.appName
        holder.packageName?.text = mContext.getString(R.string.package_name_label) + data.packageName
        holder.mainActivity?.text = mContext.getString(R.string.main_activity_label) + data.mainActivity
        holder.versionCode?.text = mContext.getString(R.string.version_code_label) + data.versionCode
        holder.versionName?.text = mContext.getString(R.string.version_name_label) + data.versionName
        holder.appIcon?.setImageDrawable(data.icon)
        holder.container?.setOnClickListener {
            listener.itemClick(data)
        }
    }

    fun update(list: ArrayList<ApplicationData>){
        applicationsList.clear()
        applicationsList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return applicationsList.size
    }

    inner class ListHolder(view: View) : RecyclerView.ViewHolder(view){
        var appName: TextView? = itemView.findViewById(R.id.name)
        var packageName: TextView? = itemView.findViewById(R.id.package_name)
        var mainActivity: TextView? = itemView.findViewById(R.id.main_activity)
        var versionCode: TextView? = itemView.findViewById(R.id.version_code)
        var versionName: TextView? = itemView.findViewById(R.id.version_name)
        var appIcon: ImageView? = itemView.findViewById(R.id.app_icon)
        var container: LinearLayout? = itemView.findViewById(R.id.container)
    }

    interface Listener {
        fun itemClick(applicationData: ApplicationData)
    }
}
