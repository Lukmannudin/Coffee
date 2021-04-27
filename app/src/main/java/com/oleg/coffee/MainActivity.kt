package com.oleg.coffee

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.oleg.coffee.databinding.ActivityMainBinding
import com.oleg.coffee.helper.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
        requestLocationPermission()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bnvMain, navHostFragment.navController)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun requestLocationPermission() {
        val locationPermissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) || ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        ) {
            ActivityCompat.requestPermissions(
                this,
                locationPermissions,
                Constant.PermissionRequestCode.PERMISSION_REQUEST_ACCESS_LOCATION
            )
        } else {
            Toast.makeText(this, "Some Feature Not Available", Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(
                this,
                locationPermissions,
                Constant.PermissionRequestCode.PERMISSION_REQUEST_ACCESS_LOCATION
            )
        }
    }
}