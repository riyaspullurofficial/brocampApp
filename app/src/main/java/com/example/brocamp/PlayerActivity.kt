package com.example.brocamp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.brocamp.databinding.ActivityMainBinding
import com.example.brocamp.databinding.ActivityPlayerBinding
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class PlayerActivity : YouTubeBaseActivity() {
    var spswhatLink:String="I9QGnNvrmoY"
    val api_key =  "AIzaSyBwdPJerwkBCkk4jSxPfF6CQS8gpiG2yLI"
    private  lateinit var binding: ActivityPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var linkAddresPlayer= intent.getStringExtra("linkAddress")

            binding.playerView.initialize(api_key, object : YouTubePlayer.OnInitializedListener{
                // Implement two methods by clicking on red error bulb
                // inside onInitializationSuccess method
                // add the video link or the
                // playlist link that you want to play
                // In here we also handle the play and pause functionality
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider?,
                    player: YouTubePlayer?,
                    p2: Boolean
                ) {
                   player?.loadVideo(linkAddresPlayer)
                    player?.play()
//                player?.pause()
               //     player?.cueVideo("I9QGnNvrmoY",0)
                //    player?.play()
                }

                // Inside onInitializationFailure
                // implement the failure functionality
                // Here we will show toast
                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    Toast.makeText(this@PlayerActivity , "Video player Failed" , Toast.LENGTH_SHORT).show()
                }
            })

    }
    private fun playing(link:String){

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}