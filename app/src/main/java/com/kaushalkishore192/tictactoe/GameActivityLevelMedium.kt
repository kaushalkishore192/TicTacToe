package com.kaushalkishore192.tictactoe

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_game.*

class GameActivityLevelMedium : AppCompatActivity() {
    var playerSelected = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_level_medium)
//        textView.text = str.toString()
        //Setting the initial view of grid
        gridView.setImageResource(R.drawable.grid)
        resetButton.visibility = View.GONE

        val bandle : Bundle? = intent.extras
        playerSelected = bandle?.getString("player").toString()

    }
    var winnerDeclared = false

    var winingPosition = listOf(
        listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
        listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
        listOf(0, 4, 8), listOf(2, 4, 6)
    )
    var playerTurn = "x"
    var boxContent = mutableListOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    //x -->1
    //o -->0
    fun boxClicked(view: View){
        var imageView = view as ImageView
        var tag = imageView.tag as String
        var tag_id = tag.toInt()
        playerTurn = if (playerTurn == "x") {
            imageView.setImageResource(R.drawable.image_x)
            playButtonSound()
            imageView.isEnabled = false
            statusView.setText(R.string.player_o_turn)
            changeBoxContent(1, tag_id)
            if (playerSelected != "Computer"){
                "o"

            }
            else{
                val handler = Handler()
                handler.postDelayed({ playComputer() }, 1000).toString()

            }

        } else{
            imageView.setImageResource(R.drawable.image_o)
            playButtonSound()

            imageView.isEnabled = false
            statusView.setText(R.string.player_x_turn)
            changeBoxContent(0, tag_id)
            "x"
        }
        checkStatus()

    }
    private fun playButtonSound(){
        var media_player=  MediaPlayer.create(this, R.raw.button_sound);
        media_player.start()
    }

    private fun  playComputer(): String {
        checkStatus()
        var empty_position = mutableListOf<Int>()
        for (i in 0 until  boxContent.size){
            if(boxContent[i] ==2){
                empty_position.add(i)
            }

        }
        if (empty_position.isNotEmpty() and (! winnerDeclared)){
//            val selectedCompPosition = empty_position.random()
            val selectedCompPosition = get_appropriate_position(empty_position)

            val view_id = resources.getIdentifier("imageView$selectedCompPosition", "id", packageName)

            var img  =  findViewById(view_id) as ImageView
            img.setImageResource(R.drawable.image_o)
            playButtonSound()
            changeBoxContent(content = 0,tag = selectedCompPosition)
            img.isEnabled = false

        }

        playerTurn = "x"
        statusView.setText(R.string.player_x_turn)
        checkStatus()
        return "x"


    }

    private fun get_appropriate_position(empty_position : List<Int>): Int {
        for (temp in winingPosition ){
            if(
                (boxContent[temp[0]] == boxContent[temp[1]] )
                and (boxContent[temp[1]]  == 1 )
                and  (boxContent[temp[2]]  == 2 )
            ){
                return temp[2]
            }
        }
        return empty_position.random()
    }

    private fun checkStatus(){
        for (temp in winingPosition ){
            if(
                (boxContent[temp[0]] == boxContent[temp[1]] )
                and (boxContent[temp[1]]  == boxContent[temp[2]] )
                and (boxContent[temp[0]] != 2)
            ){
                var winner_id = boxContent[temp[0]]
                if(winner_id == 0){
                    var winner = "O"
                    declareWinner(winner)

                }
                else{
                    var winner = "X"
                    declareWinner(winner)
                }
            }
        }
        if (!boxContent.contains(2) and !winnerDeclared){
            matchTie()
        }
    }
    private fun changeBoxContent(content: Int, tag: Int){
        boxContent[tag] = content

    }
    private fun declareWinner(player: String){
        winnerDeclared = true
        statusView.text = "Player - " + player + " Won!!!"
        disableAllView()
        tabToPlayAgain()

    }
    private fun matchTie(){
        statusView.setText(R.string.match_tie)
        tabToPlayAgain()
    }


    private fun tabToPlayAgain(){
        resetButton.visibility = View.VISIBLE
        resetButton.isEnabled = true
    }
    private fun disableAllView(){
        imageView0.isEnabled = false
        imageView1.isEnabled = false
        imageView2.isEnabled = false
        imageView3.isEnabled = false
        imageView4.isEnabled = false
        imageView5.isEnabled = false
        imageView6.isEnabled = false
        imageView7.isEnabled = false
        imageView8.isEnabled = false


    }
    private fun enableAllView(){
        imageView0.isEnabled = true
        imageView1.isEnabled = true
        imageView2.isEnabled = true
        imageView3.isEnabled = true
        imageView4.isEnabled = true
        imageView5.isEnabled = true
        imageView6.isEnabled = true
        imageView7.isEnabled = true
        imageView8.isEnabled = true


    }

    private fun resetBoxView(){
        imageView0.setImageResource(0)
        imageView1.setImageResource(0)
        imageView2.setImageResource(0)
        imageView3.setImageResource(0)
        imageView4.setImageResource(0)
        imageView5.setImageResource(0)
        imageView6.setImageResource(0)
        imageView7.setImageResource(0)
        imageView8.setImageResource(0)


    }


    fun resetButtonPressed(view: View) {
        boxContent = mutableListOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
        statusView.setText(R.string.player_x_turn)
        playerTurn = "x"
        winnerDeclared = false
        enableAllView()
        resetButton.visibility = View.GONE
        resetBoxView()
    }

}