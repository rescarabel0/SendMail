package br.edu.ifsp.ads.pdm.sendmail

import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.ads.pdm.sendmail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.clearBtn.setOnClickListener {
            with(amb) {
                toEt.setText("")
                ccEt.setText("")
                bccEt.setText("")
                subjectEt.setText("")
                messageEt.setText("")
            }
        }

        amb.sendBtn.setOnClickListener {
            val sendMailIntent = Intent(ACTION_SENDTO)

            with(sendMailIntent) {
                with(amb) {
                    putExtra(EXTRA_EMAIL, arrayOf(toEt.text.toString()))
                    putExtra(EXTRA_CC, arrayOf(ccEt.text.toString()))
                    putExtra(EXTRA_BCC, arrayOf(bccEt.text.toString()))
                    putExtra(EXTRA_SUBJECT, arrayOf(subjectEt.text.toString()))
                    putExtra(EXTRA_TEXT, arrayOf(messageEt.text.toString()))
                }
                type = "message/rfc822"
                data = Uri.parse("mailto:")
            }

            val chooserIntent = Intent(ACTION_CHOOSER)
            chooserIntent.putExtra(EXTRA_INTENT, sendMailIntent)

            startActivity(chooserIntent)
        }
    }
}