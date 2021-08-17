package one.digitalinnovation.listacontatos

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.edit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import one.digitalinnovation.listacontatos.ContactDetail.Companion.extraContact

class MainActivity : AppCompatActivity(), ClickItem {
    private val rvList:RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv_list)
    }
    private val adapter = contactAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchListContact()
        bindViews()
    }

    private fun fetchListContact() {
        val list = arrayListOf(
            contact(
                "Vinicius Correia",
                "(00) 00000-0000",
                "img.png"
            ),
            contact(
                "Igor Ferrani",
                "(99) 99999-9999",
                "img2.png"
            )
        )
        getInstanceSharedPreferences().edit {
            putString("contacts", Gson().toJson(list))
        }
    }

    private fun getInstanceSharedPreferences(): SharedPreferences{
        return getSharedPreferences("one.digitalinnovation.listacontatos.PREFERENCES", Context.MODE_PRIVATE)
    }

    private fun bindViews(){
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
        updateList()
    }

    private fun getListContacts(): List<contact> {
        val list = getInstanceSharedPreferences().getString("contacts", "[]")
        val turnsType = object : TypeToken<List<contact>>(){}.type
        return Gson().fromJson(list, turnsType)
    }

    private fun updateList(){
        adapter.updateList(getListContacts())
    }

    private fun showToast(message: String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.item_menu_1 ->{
                showToast("Exibindo item de menu 1")
                true
            }
            R.id.item_menu_2 ->{
                showToast("Exibindo item de menu 2")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun clickItemContact(contact: contact) {
        val intent = Intent(this, ContactDetail::class.java)
        intent.putExtra(extraContact, contact)
        startActivity(intent)
    }
}