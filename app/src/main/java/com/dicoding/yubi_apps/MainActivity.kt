package com.dicoding.yubi_apps

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//            OnboardingScreen()
            MainApp()
        }
    }

    private val poppins = FontFamily(
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold),
        Font(R.font.poppins_light, FontWeight.Light),
    )
    @Composable
    fun MainApp() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "onboarding1" // Halaman pertama
        ) {
            composable("onboarding1") {
                OnboardingScreen1(navController)
            }
            composable("onboarding2") {
                OnboardingScreen2(navController)
            }
            composable("onboarding3") {
                OnboardingScreen3(navController)
            }
            composable("onboarding4") {
                OnboardingScreen4(navController)
            }
        }
    }

    //onboarding 1
    @SuppressLint("Range")
    @Composable
    fun OnboardingScreen1(navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.size(100.dp))

            // Image
            Image(
                painter = painterResource(id = R.drawable.onboarding1),
                contentDescription = "com.dicoding.yubi_apps.Onboarding Image",
                modifier = Modifier
                    .fillMaxWidth(1.30f)
                    .height(280.dp)
                    .clip(RoundedCornerShape(16.dp)),

            )
            Spacer(modifier = Modifier.size(10.dp))
            // Judul
            Text(
                text = "Solusi pintar untuk\nPertanian lebih baik",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                textAlign = TextAlign.Center,
                color = Color(0xFF179C5B),
                modifier = Modifier
                    .padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            // Deskripsi
            Text(
                text = "Aplikasi ini dirancang untuk membantu petani\nmendeteksi hama pada tanaman umbi-umbian\nsecara cepat dan akurat, menggunakan teknologi\npembelajaran mesin.",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = poppins,
                textAlign = TextAlign.Center,
                color = Color(0xFF000000),
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            // Indikator halaman
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                repeat(4) { index ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(if (index == 0) 20.dp else 15.dp)
                            .clip(RoundedCornerShape(50))
                            .background(
                                if (index == 0) Color(0xFF179C5B)
                                else Color(0xFFD9D9D9)
                            )
                    )
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            // Tombol Mulai
            Button(
                onClick = { navController.navigate("onboarding2") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF179C5B)),
                modifier = Modifier
                    .fillMaxWidth(0.90f)
                    .height(60.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Mulai",
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppins,
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }
    }

    //onboarding 2
    @SuppressLint("Range")
    @Composable
    fun OnboardingScreen2(navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.size(100.dp))

            // Image
            Image(
                painter = painterResource(id = R.drawable.onboarding2),
                contentDescription = "com.dicoding.yubi_apps.Onboarding Image",
                modifier = Modifier
                    .fillMaxWidth(1.30f)
                    .height(280.dp)
                    .clip(RoundedCornerShape(16.dp)),

                )
            Spacer(modifier = Modifier.size(10.dp))
            // Judul
            Text(
                text = "Deteksi hama dengan\nTeknologi",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                textAlign = TextAlign.Center,
                color = Color(0xFF179C5B),
                modifier = Modifier
                    .padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            // Deskripsi
            Text(
                text = "Temukan hama yang menyerang tanaman\numbi-umbian Anda dan dapatkan informasi\nhama yang akurat dari aplikasi.",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = poppins,
                textAlign = TextAlign.Center,
                color = Color(0xFF000000),
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.size(30.dp))
            // Indikator halaman
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(4) { index ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(if (index == 1) 20.dp else 15.dp) // Sorot posisi halaman aktif
                            .clip(RoundedCornerShape(50))
                            .background(
                                if (index == 1) Color(0xFF179C5B)
                                else Color(0xFFD9D9D9)
                            )
                    )
                }
            }
            Spacer(modifier = Modifier.size(10.dp))

            // Tombol
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Tombol Selanjutnya
                Button(
                    onClick = { navController.navigate("onboarding3") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF179C5B)),
                    modifier = Modifier
                        .fillMaxWidth(0.90f)
                        .height(65.dp)
                        .padding(bottom = 8.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Selanjutnya",
                        color = Color.White,
                        fontFamily = poppins,
                        fontSize = 20.sp
                    )
                }

                // Tombol Lewati
                Button(
                    onClick = { navController.navigate("onboarding4") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth(0.90f)
                        .height(60.dp),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(2.dp, Color(0xFF179C5B)), // Tambahkan garis tepi hijau
                    elevation = ButtonDefaults.buttonElevation(0.dp) // Hilangkan bayangan
                ) {
                    Text(
                        text = "Lewati",
                        fontFamily = poppins,
                        color = Color(0xFF179C5B),
                        fontSize = 20.sp
                    )
                }

            }
        }
    }

    //onboarding 3
    @SuppressLint("Range")
    @Composable
    fun OnboardingScreen3(navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.size(100.dp))

            // Image
            Image(
                painter = painterResource(id = R.drawable.onboarding3),
                contentDescription = "com.dicoding.yubi_apps.Onboarding Image",
                modifier = Modifier
                    .fillMaxWidth(1.30f)
                    .height(280.dp)
                    .clip(RoundedCornerShape(16.dp)),

                )
            Spacer(modifier = Modifier.size(10.dp))
            // Judul
            Text(
                text = "Cepat dan mudah\ndigunakan",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                textAlign = TextAlign.Center,
                color = Color(0xFF179C5B),
                modifier = Modifier
                    .padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            // Deskripsi
            Text(
                text = "Cukup unggah foto, dan biarkan teknologi\ncerdas kami mengidentifikasi hama dengan\ncepat.",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = poppins,
                textAlign = TextAlign.Center,
                color = Color(0xFF000000),
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.size(30.dp))
            // Indikator halaman
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(4) { index ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(if (index == 2) 20.dp else 15.dp) // Sorot posisi halaman aktif
                            .clip(RoundedCornerShape(50))
                            .background(
                                if (index == 2) Color(0xFF179C5B)
                                else Color(0xFFD9D9D9)
                            )
                    )
                }
            }
            Spacer(modifier = Modifier.size(10.dp))

            // Tombol
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Tombol Selanjutnya
                Button(
                    onClick = { navController.navigate("onboarding4") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF179C5B)),
                    modifier = Modifier
                        .fillMaxWidth(0.90f)
                        .height(65.dp)
                        .padding(bottom = 8.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Selanjutnya",
                        fontFamily = poppins,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }

                // Tombol Lewati
                Button(
                    onClick = { navController.navigate("onboarding4") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth(0.90f)
                        .height(60.dp),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(2.dp, Color(0xFF179C5B)), // Tambahkan garis tepi hijau
                    elevation = ButtonDefaults.buttonElevation(0.dp) // Hilangkan bayangan
                ) {
                    Text(
                        text = "Lewati",
                        fontFamily = poppins,
                        color = Color(0xFF179C5B),
                        fontSize = 20.sp
                    )
                }
            }
        }
    }

    //onboarding 4
    @SuppressLint("Range")
    @Composable
    fun OnboardingScreen4(navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.size(100.dp))

            // Image
            Image(
                painter = painterResource(id = R.drawable.onboarding4),
                contentDescription = "com.dicoding.yubi_apps.Onboarding Image",
                modifier = Modifier
                    .fillMaxWidth(1.30f)
                    .height(280.dp)
                    .clip(RoundedCornerShape(16.dp)),

                )
            Spacer(modifier = Modifier.size(10.dp))
            // Judul
            Text(
                text = "Dirancang untuk\nPetani Indonesia",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                textAlign = TextAlign.Center,
                color = Color(0xFF179C5B),
                modifier = Modifier
                    .padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            // Deskripsi
            Text(
                text = "Mitra terbaik petani Indonesia untuk mengenali\nancaman pada tanaman dan meningkatkan\nketahanan pangan nasional",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = poppins,
                textAlign = TextAlign.Center,
                color = Color(0xFF000000),
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.size(30.dp))
            // Indikator halaman
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(4) { index ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(if (index == 3) 20.dp else 15.dp) // Sorot posisi halaman aktif
                            .clip(RoundedCornerShape(50))
                            .background(
                                if (index == 3) Color(0xFF179C5B)
                                else Color(0xFFD9D9D9)
                            )
                    )
                }
            }
            Spacer(modifier = Modifier.size(10.dp))

            // Tombol
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Tombol Selanjutnya
                Button(
                    onClick = { navController.navigate("login") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF179C5B)),
                    modifier = Modifier
                        .fillMaxWidth(0.90f)
                        .height(65.dp)
                        .padding(bottom = 8.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Masuk",
                        fontFamily = poppins,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }

                // Tombol Lewati
                Button(
                    onClick = { navController.navigate("SignUp") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth(0.90f)
                        .height(60.dp),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(2.dp, Color(0xFF179C5B)), // Tambahkan garis tepi hijau
                    elevation = ButtonDefaults.buttonElevation(0.dp) // Hilangkan bayangan
                ) {
                    Text(
                        text = "Daftar",
                        fontFamily = poppins,
                        color = Color(0xFF179C5B),
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

