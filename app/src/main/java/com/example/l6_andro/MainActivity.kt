package com.example.l6_andro

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.l6_andro.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navHostFragment:NavHostFragment
    lateinit var navController: NavController
    lateinit var navView: NavigationView
    lateinit var toolbarMain: androidx.appcompat.widget.Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var bottomNav: BottomNavigationView
    lateinit var appBarConfig: AppBarConfiguration

    private val REQUEST_CODE = 1
    private val REQUIRED_PERMISSIONS = mutableListOf (
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.RECORD_AUDIO,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION)
        .apply {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            } else {
                add(android.Manifest.permission.READ_MEDIA_IMAGES)
                add(android.Manifest.permission.READ_MEDIA_VIDEO)
            }
        }.toTypedArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbarMain = binding.toolbar1
        setSupportActionBar(toolbarMain)


        navHostFragment = supportFragmentManager.findFragmentById(R.id.sfcontainer) as NavHostFragment
        navController = navHostFragment.navController

        drawerLayout=binding.drawerLayout
        navView = binding.navView
        bottomNav = binding.bottomMenu


        appBarConfig =
            AppBarConfiguration(setOf(R.id.imageListFragment,R.id.main_frag,R.id.list_frag), drawerLayout)
        // old config:
        // AppBarConfiguration(setOf(R.id.swipe_frag,R.id.main_frag,R.id.list_frag), drawerLayout)
        setupActionBarWithNavController(navController,appBarConfig)
        navView.setupWithNavController(navController)
        bottomNav.setupWithNavController(navController)

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE)
        }

    }

    fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        return ContextCompat.checkSelfPermission(baseContext, it) ==
                PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (!allPermissionsGranted()) {
                Toast.makeText(this,"Permissions not granted.", Toast.LENGTH_SHORT).show()
                finish()
            } } }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }
}