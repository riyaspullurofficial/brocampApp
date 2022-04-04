package com.example.brocamp

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.*
import com.example.brocamp.databinding.ActivityMainBinding
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.squareup.okhttp.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class MainActivity : YouTubeBaseActivity() {
    val api_key =  "AIzaSyBwdPJerwkBCkk4jSxPfF6CQS8gpiG2yLI"
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.contactusBtn.setOnClickListener {
            startActivity(Intent(this,Contact::class.java))
        }
        binding.applyNowBtn.setOnClickListener {
           // startActivity(Intent(this,UserRegistration::class.java))
            startActivity(Intent(this,UserOTPVerification::class.java))
        }
        binding.adminBtn.setOnClickListener {
            startActivity(Intent(this,AdminLogin::class.java))
        }
        binding.spswhat.setOnClickListener {

            var spswhatLink:String="I9QGnNvrmoY"
            var intent:Intent= Intent(this,PlayerActivity::class.java)
            intent.putExtra("linkAddress",spswhatLink)
            startActivity(intent)
        }
        binding.howspswork.setOnClickListener {

            var spswhatLink:String="ckub1RSlhic"
            var intent:Intent= Intent(this,PlayerActivity::class.java)
            intent.putExtra("linkAddress",spswhatLink)
            startActivity(intent)
        }
        binding.whocanjoin.setOnClickListener {

            var spswhatLink:String="xkmM6h32lnM"
            var intent:Intent= Intent(this,PlayerActivity::class.java)
            intent.putExtra("linkAddress",spswhatLink)
            startActivity(intent)
        }
        binding.howCost.setOnClickListener {

            var spswhatLink:String="vhG3wKGp2mc"
            var intent:Intent= Intent(this,PlayerActivity::class.java)
            intent.putExtra("linkAddress",spswhatLink)
            startActivity(intent)
        }
        binding.convocationCeremoney.setOnClickListener {

            var spswhatLink:String="m_hx0Ebg5Yo"
            var intent:Intent= Intent(this,PlayerActivity::class.java)
            intent.putExtra("linkAddress",spswhatLink)
            startActivity(intent)
        }




  /*      binding.spswhat.initialize(api_key, object : YouTubePlayer.OnInitializedListener{
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
//                player?.loadVideo("I9QGnNvrmoY")
//                player?.play()
//                player?.pause()
                player?.cueVideo("I9QGnNvrmoY",0)
                player?.play()
            }

            // Inside onInitializationFailure
            // implement the failure functionality
            // Here we will show toast
            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(this@MainActivity , "Video player Failed" , Toast.LENGTH_SHORT).show()
            }
        })*/

     /*   binding.howspswork.initialize(api_key, object : YouTubePlayer.OnInitializedListener{
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
//                player?.loadVideo("ckub1RSlhic")
//                player?.play()

                player?.cueVideo("ckub1RSlhic",0)
                player?.play()
            }

            // Inside onInitializationFailure
            // implement the failure functionality
            // Here we will show toast
            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(this@MainActivity , "Video player Failed" , Toast.LENGTH_SHORT).show()
            }
        })*/

      /*  binding.whocanjoin.initialize(api_key, object : YouTubePlayer.OnInitializedListener{
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
//                player?.loadVideo("xkmM6h32lnM")
//                player?.play()
                player?.cueVideo("xkmM6h32lnM",0)
                player?.play()
            }

            // Inside onInitializationFailure
            // implement the failure functionality
            // Here we will show toast
            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(this@MainActivity , "Video player Failed" , Toast.LENGTH_SHORT).show()
            }
        })*/
        /*
     binding.howCost.initialize(api_key, object : YouTubePlayer.OnInitializedListener{
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
                player?.loadVideo("vhG3wKGp2mc")
                player?.play()


            }

            // Inside onInitializationFailure
            // implement the failure functionality
            // Here we will show toast
            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(this@MainActivity , "Video player Failed" , Toast.LENGTH_SHORT).show()
            }
        })*/
/*
                binding.convocationCeremoney.initialize(api_key, object : YouTubePlayer.OnInitializedListener{
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
                        player?.loadVideo("m_hx0Ebg5Yo")
                        //player?.play()
                        player?.pause()
                    }

                    // Inside onInitializationFailure
                    // implement the failure functionality
                    // Here we will show toast
                    override fun onInitializationFailure(
                        p0: YouTubePlayer.Provider?,
                        p1: YouTubeInitializationResult?
                    ) {
                        Toast.makeText(this@MainActivity , "Video player Failed" , Toast.LENGTH_SHORT).show()
                    }
                })
 */
    }

}
