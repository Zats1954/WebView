package ru.zatsoft.webview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import ru.zatsoft.webview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var list = mutableListOf(
        GridViewModal("GMail.com","https://www.gmail.com", R.drawable.gmail),
        GridViewModal("Yandex.ru", "https://www.yandex.ru", R.drawable.yandex),
        GridViewModal("Rbc.ru", "https://www.rbc.ru", R.drawable.rbc),
        GridViewModal("Dzen.ru", "https://www.dzen.ru", R.drawable.dzen),
        GridViewModal("UrbanUniversity.ru","https://www.urban-university.ru", R.drawable.urban),
        GridViewModal("LinkedIn.com", "https://www.linkedIn.com", R.drawable.linkedin)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMain)
        title = " "

        var intent = Intent(this, WebActivity::class.java)
        val adapter = GridViewAdapter(list,this)
        binding.gridView.adapter = adapter
        binding.gridView.onItemClickListener = AdapterView.OnItemClickListener{
            _,_, position, _ ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).uri)))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.exit)
            finish()
        return super.onOptionsItemSelected(item)
    }
}