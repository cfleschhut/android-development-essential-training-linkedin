package com.christianfleschhut.firstappbasicactivityapi22

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.christianfleschhut.firstappbasicactivityapi22.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycle.addObserver(MyObserver())

        setSupportActionBar(binding.toolbar)

        supportActionBar?.let {
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayUseLogoEnabled(true)
            it.setLogo(R.drawable.ic_logo)
        }

//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        viewModel.info.observe(this) {
            displaySnackbar(it)
        }

        binding.fab.setOnClickListener {
            viewModel.loadData()
        }

        supportFragmentManager.commit {
            add<StocksFragment>(R.id.container, null)
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> goToHome()
                R.id.action_first -> goToFirst()
                R.id.action_second -> goToSecond()
                else -> false
            }
        }
    }

    private fun goToSecond(): Boolean {
        supportFragmentManager.commit {
            replace<SecondFragment>(R.id.container, null, null)
        }
        return true
    }

    private fun goToFirst(): Boolean {
        supportFragmentManager.commit {
            replace<FirstFragment>(R.id.container, null, null)
        }
        return true
    }

    private fun goToHome(): Boolean {
        supportFragmentManager.commit {
            replace<StocksFragment>(R.id.container, null, null)
        }
        return true
    }

    private fun displaySnackbar(count: Int) {
        Snackbar.make(binding.root, "Current value: $count", Snackbar.LENGTH_LONG)
            .setAction("Show info") {}
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_share -> handleShare()
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun handleShare(): Boolean {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "${viewModel.info.value}")
        }
        startActivity(intent)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}