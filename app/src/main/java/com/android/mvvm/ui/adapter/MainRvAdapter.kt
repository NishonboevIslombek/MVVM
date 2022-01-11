package com.android.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.android.mvvm.R
import com.android.mvvm.data.network.response.UserResponse
import com.android.mvvm.databinding.ItemMainBinding

class MainRvAdapter : RecyclerView.Adapter<MainRvAdapter.ViewHolder>() {

    private var mListItems: MutableList<UserResponse.User> = mutableListOf()

    fun updateItemList(itemList: List<UserResponse.User>) {
        mListItems.clear()
        mListItems.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initView(mListItems[position])
    }

    override fun getItemCount(): Int {
        return mListItems.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMainBinding.bind(itemView)

        fun initView(item: UserResponse.User) {

            binding.loginUser.text = item.login

            binding.avatarUser.load(item.avatarUrl) {
                transformations(RoundedCornersTransformation(6f))
            }


        }
    }

}