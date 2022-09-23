package com.example.android.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeData: ViewModel() {
    val item = ShoeItem("","","","")


    private val _shoeList = MutableLiveData<MutableList<ShoeItem>>()
    val shoeList: LiveData<MutableList<ShoeItem>> = _shoeList

    fun addData(){
        _shoeList.value?.add(item)
        item.name =""
        item.company =""
        item.size =""
        item.description =""
    }

//    fun addData(newList:List<ShoeItem>){
//        _shoeList.value?.add(0,newList[0])
////        _shoeList.value?.toList()
//    }

//    fun saveList(newList:MutableList<ShoeItem>){
//        _shoeList.value = newList
//    }

}