package be.tftic.web2023.demo_08_notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var btnNotif : Button
    private val CHANNEL_ID_DEMO = "Nico42"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotifChannel()

        btnNotif = findViewById(R.id.btn_main_send_notif)
        btnNotif.setOnClickListener { openNotif() }
    }

    private fun createNotifChannel() {

        // Créer le canal de notif
        val name = "Notif Channel"
        val imp = NotificationManager.IMPORTANCE_HIGH

        val channel = NotificationChannel(CHANNEL_ID_DEMO, name, imp).apply {
            description = "Ceci est la demo :o"
            vibrationPattern = longArrayOf(200, 50, 200, 10, 200, 10, 200)
        }

        // Enregistrer auprès du systeme Android
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun openNotif() {
        val notifBuilder = Notification.Builder(this, CHANNEL_ID_DEMO).apply {
            setSmallIcon(R.mipmap.ic_launcher)
            setTitle("Demo notif ♥")
            setContentText("Ceci est la demo pour générer un notif 🍔")
        }

        val notification = notifBuilder.build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1337, notification)
    }
}