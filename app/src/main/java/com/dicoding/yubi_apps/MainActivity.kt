package com.dicoding.yubi_apps

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dicoding.yubi_apps.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        binding.navView.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.articleFragment, R.id.analyzeFragment -> {
                    binding.navView.visibility = View.GONE // Hide BottomNavigationView
                }
                else -> {
                    binding.navView.visibility = View.VISIBLE // Show BottomNavigationView
                }
            }
        }

//        val navView: BottomNavigationView = binding.navView
//
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
//        navController = navHostFragment.navController
//
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home,
//                R.id.navigation_History
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

//        // Mengakses elemen UI melalui binding
//        binding.articleCard.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragmentContainer, ArticleFragment())
//                .addToBackStack(null)
//                .commit()
//        }
//
//        binding.analyzeCard.setOnClickListener {
//            replaceFragment(AnalyzeFragment())
//        }
    }

//    // Fungsi untuk mengganti fragment
//    private fun replaceFragment(fragment: Fragment) {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragmentContainer, fragment) // Pastikan fragmentContainer ada di layout
//        transaction.addToBackStack(null) // Tambahkan ke back stack
//        transaction.commit()
//    }
//
//    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
//    override fun onBackPressed() {
//        if (supportFragmentManager.backStackEntryCount > 0) {
//            supportFragmentManager.popBackStack() // Kembali ke fragment sebelumnya
//        } else {
//            super.onBackPressed() // Keluar dari aplikasi
//        }
//    }
//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }

}

