package com.github.nauhalf.segmentedcontrol

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.alanvan.segmented_control.SegmentedControlGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewpager: ViewPager2
    lateinit var progressBar: ProgressBar

    val callback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            val percent = position / viewpager.childCount
            progressBar.progress = percent
        }


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()
    }

    private fun setUp(){
        tabLayout = findViewById(R.id.tablayout)
        progressBar = findViewById(R.id.progress)
//       segmentedControlGroup = findViewById(R.id.segmented_control_group)
//
//        segmentedControlGroup
        viewpager = findViewById(R.id.viewpager)
//        segmentedControlGroup.apply {
//            setOnSelectedOptionChangeCallback {
//                viewpager.unregisterOnPageChangeCallback(callback)
//                viewpager.currentItem = it
//                viewpager.registerOnPageChangeCallback(callback)
//            }
//        }
        viewpager.adapter = ViewPagerAdapter(this)
//        viewpager.registerOnPageChangeCallback(callback)
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            tab.text = "Tab $position"
            viewpager.setCurrentItem(tab.position, true)
        }.attach()

        viewpager.registerOnPageChangeCallback(callback)

//        for (i in 0 until tabLayout.tabCount) {
//            val tab = tabLayout.getTabAt(i)
//            val constraint = LayoutInflater.from(this)
//                .inflate(R.layout.tab_indicator, tabLayout, false)
//            val tabTextView = constraint.findViewById<View>(R.id.tab_title) as TextView
//            tabTextView.text = tab?.text
//            tab?.customView = constraint
//            tab?.select()
//        }

    }

    override fun onDestroy() {
        super.onDestroy()
        viewpager.unregisterOnPageChangeCallback(callback)
    }
}