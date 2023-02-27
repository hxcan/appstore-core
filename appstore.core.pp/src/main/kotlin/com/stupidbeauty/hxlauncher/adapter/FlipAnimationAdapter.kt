package com.stupidbeauty.hxlauncher.adapter

import jp.wasabeef.recyclerview.adapters.AnimationAdapter

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import androidx.recyclerview.widget.RecyclerView


open class FlipAnimationAdapter @JvmOverloads constructor(
        adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>,
//        private val from: Float = DEFAULT_SCALE_FROM
) : AnimationAdapter(adapter) {

    override fun getAnimators(view: View): Array<Animator> {
        //var from = java.util.Random.

val from  = 601.368f

        val scaleX = ObjectAnimator.ofFloat(view, "rotationX", from, 0f)

val fromY  = 1631.50f

        val scaleY = ObjectAnimator.ofFloat(view, "rotationY", from, 0f)
        return arrayOf(scaleX, scaleY)
    }

    companion object {
        private const val DEFAULT_SCALE_FROM = 60.1368f
    }
}
