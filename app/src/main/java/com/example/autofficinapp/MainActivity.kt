package com.example.autofficinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.autofficinapp.databases.GarageDatabase
import com.example.autofficinapp.services.ClientService
import com.example.autofficinapp.services.JobService
import com.example.autofficinapp.services.VehicleService
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Creo il database e poi passo le varie istanze di Dao ai vari servizi
         */
        val db = GarageDatabase.getDatabase(applicationContext)
        ClientService.setDao(db.getClientDao())
        JobService.setDao(db.getJobDao())
        VehicleService.setDao(db.getVehicleDao())

        // Inizializza il Navigation Controller e associa il grafo di navigazione
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_activity_navfragment) as NavHostFragment
        navController = navHostFragment.navController

        // Inizializza il Drawer Layout
        drawerLayout = findViewById(R.id.drawer_layout)

        // Inizializza il Navigation View
        navView = findViewById(R.id.navigation_view)

        // Aggiungi il listener per il click del menu laterale
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_new_client -> {
                    navController.navigate(R.id.newClientFragment)
                }
                R.id.menu_new_job -> {
                    navController.navigate(R.id.newJobFragment)
                }
                R.id.menu_new_car -> {
                    navController.navigate(R.id.newVehicleFragment)
                }
                R.id.menu_list_client -> {
                    navController.navigate(R.id.clientListFragment)
                }

                R.id.menu_all_job -> {
                    navController.navigate(R.id.jobListFragment)
                }

                R.id.menu_list_car -> {
                    navController.navigate(R.id.vehicleListFragment)
                }
            }
            // Chiudi il Drawer Layout dopo aver selezionato una voce del menu
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Aggiorna il titolo della ActionBar con il label della destinazione corrente del NavController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.title = destination.label
        }

        // Abilita l'icona del menu laterale nell'action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Aggiungi il listener per l'apertura del menu laterale
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

    }

    // Aggiungi il listener per il click sull'icona del menu laterale nella action bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else
                drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    // Chiudi il menu laterale se l'utente preme il pulsante indietro
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}