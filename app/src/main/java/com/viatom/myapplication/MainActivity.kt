package com.viatom.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.net.URISyntaxException

class MainActivity : AppCompatActivity() {
    lateinit var mSocket:Socket
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
val   fuck:TouchView=findViewById(R.id.yes)

        //------------------------------------------------------------------------------------------socketSetting
        try {
            mSocket = IO.socket("http://192.168.5.100:3000/")
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }

        mSocket.on("info",object:Emitter.Listener{
            override fun call(vararg args: Any?) {
                val x=args[0] as ByteArray
                val xx=x[0].toUByte().toInt()+x[1].toUByte().toInt()*256
                val yy=x[2].toUByte().toInt()+x[3].toUByte().toInt()*256
                fuck.dg(xx,yy)

            }

        })

        mSocket.connect()



    }
}