package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity(), MainAdapter.ListenerProduct {

    lateinit var adapter: MainAdapter
    lateinit var cbAll: AppCompatCheckBox
    lateinit var rcList: RecyclerView
    private val listImage = mutableListOf<Int>(
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3,
        R.drawable.img4,
        R.drawable.img4,
        R.drawable.img4,
        R.drawable.img4,
        R.drawable.img4,
        R.drawable.img4,
        R.drawable.img4,
        R.drawable.img4,
        R.drawable.img1,
        R.drawable.img1,
        R.drawable.img1,
        R.drawable.img4,
        R.drawable.img3,
        R.drawable.img2,
        R.drawable.img3,
        R.drawable.img4,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,

        )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
//        rcList.itemAnimator?.changeDuration = 0
        adapter = MainAdapter(this)
        rcList.layoutManager = GridLayoutManager(this, 2)
        rcList.setHasFixedSize(true)
        rcList.adapter = adapter
        adapter.setOnChangeListener(this)
        adapter.submitList(fakeData())

    }

    private fun fakeData(): List<Product> {
        val list: MutableList<Product> = ArrayList()
        for (i in 1..10) {
            list.add(
                Product(
                    i,
                    "name $i ${Random.nextInt(312128434)}",
                    "description $i ${Random.nextInt(1000000000)}",
                    listImage[i],
                    false
                )
            )
        }
        return list
    }

    private fun initView() {
        cbAll = findViewById(R.id.cbAll)
        rcList = findViewById(R.id.recyclerView)
    }

    fun checkALL(view: View) {
        for (i in 0 until adapter.currentList.size) {
            adapter.currentList[i].isSelect = cbAll.isChecked
//            adapter.notifyItemChanged(i)
        }
        adapter.notifyDataSetChanged()
        Log.d("testing", adapter.currentList.size.toString())
    }

    override fun onProductChecked() {
        var isCheckAll = true
        adapter.currentList.forEach { product ->
            if (!product.isSelect) {
                isCheckAll = false
            }
        }
        cbAll.isChecked = isCheckAll
    }


}