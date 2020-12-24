package com.kaushalkishore192.tictactoe

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var gameLevel = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialiseView()
    }

    @SuppressLint("ResourceAsColor")
    private fun initialiseView(){
        game_icon_iv.setImageResource(R.drawable.tic_tac_toe_icon)
        player_computer_iv.setImageResource(R.drawable.computer_player6)
        player_friend_iv.setImageResource(R.drawable.friend_player)
        tv_level1.setBackgroundColor(R.color.purple_700)
        tv_level2.setBackgroundColor(R.color.background_color)
        tv_level3.setBackgroundColor(R.color.background_color)


    }

    fun playWithComputer(view: View) {
        startGame("Computer")

    }
    fun playWithFriend(view: View) {
        startGame("Friend")

    }

    private fun startGame(player: String){
        var intent = Intent(this,GameActivity::class.java)
        if (gameLevel == 2)
            intent = Intent(this,GameActivityLevelMedium::class.java)

        else if (gameLevel == 3)
            intent = Intent(this,GameActivityLevelHard::class.java)
        intent.putExtra("player",player)
        startActivity(intent)

    }

    @SuppressLint("ResourceAsColor")
    fun level2Clicked(view: View) {
        gameLevel = 2
        tv_level2.setBackgroundColor(R.color.purple_700)
        tv_level3.setBackgroundColor(R.color.background_color)

    }
    @SuppressLint("ResourceAsColor")
    fun level3Clicked(view: View) {
        gameLevel = 3
        tv_level2.setBackgroundColor(R.color.purple_700)
        tv_level3.setBackgroundColor(R.color.purple_700)

    }

    @SuppressLint("ResourceAsColor")
    fun level1Clicked(view: View) {
        tv_level2.setBackgroundColor(R.color.background_color)
        tv_level3.setBackgroundColor(R.color.background_color)

    }


}