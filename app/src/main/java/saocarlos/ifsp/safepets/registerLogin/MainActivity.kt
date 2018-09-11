package saocarlos.ifsp.safepets.registerLogin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import saocarlos.ifsp.safepets.posts.FeedActivity
import saocarlos.ifsp.safepets.R

class MainActivity: AppCompatActivity() {


    companion object {
        val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        login_button_login.setOnClickListener {
            performLogin()
        }

        new_account_register_text_view.setOnClickListener {
            Log.d(TAG, "Try to show login activity")

            // launch the login activity somehow
            val intent = Intent(this, NewAccountActivity::class.java)
            startActivity(intent)
        }

    }
      /*  new_account_register_text_view.setOnClickListener{
            finish()
        }
    } */

    private fun performLogin() {
        val email = email_edittext_login.text.toString()
        val password = password_edittext_login.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Insira E-mail e senha", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener

                    Log.d("Login", "Login efetuado com sucesso: ${it.result.user.uid}")

                    val intent = Intent(this, FeedActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Login falhou: ${it.message}", Toast.LENGTH_SHORT).show()
                }
    }

}