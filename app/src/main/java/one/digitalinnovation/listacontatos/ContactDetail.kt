package one.digitalinnovation.listacontatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ContactDetail : AppCompatActivity() {
   private var contact: contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getExtras()
        bindViews()
    }

    private fun getExtras(){
        contact = intent.getParcelableExtra(extraContact)
    }

    private fun bindViews(){
        findViewById<TextView>(R.id.tv_name).text = contact?.name
        findViewById<TextView>(R.id.tv_phone).text = contact?.phone
    }

    companion object{
        const val extraContact: String = "EXTRA_CONTACT"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}