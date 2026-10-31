package com.cpm.exchange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DarkThemeApp {
                MainScreen()
            }
        }
    }
}

@Composable
fun DarkThemeApp(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color(0xFF6200EE),
            background = Color(0xFF121212),
            surface = Color(0xFF1E1E1E),
            onBackground = Color.White,
            onSurface = Color.White
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "CPM Exchange",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFBB86FC),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { selectedTab = 0 },
                colors = ButtonDefaults.buttonColors(containerColor = if (selectedTab == 0) Color(0xFF6200EE) else Color(0xFF2C2C2C))
            ) {
                Text("قائمة المهام")
            }
            Button(
                onClick = { selectedTab = 1 },
                colors = ButtonDefaults.buttonColors(containerColor = if (selectedTab == 1) Color(0xFF6200EE) else Color(0xFF2C2C2C))
            ) {
                Text("لوحة المسؤول")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (selectedTab == 0) {
            UserTasksSection()
        } else {
            AdminDashboardSection()
        }
    }
}

@Composable
fun UserTasksSection() {
    val tasks = listOf(
        Triple("مهمة إنشاء إيميل (1)", "قم بإنشاء بريد إلكتروني جديد بالمعلومات المطلوبة", "صور توضيحية: ضع بيانات الـ Gmail هنا..."),
        Triple("مهمة إنشاء إيميل (2)", "أنشئ بريداً آخر وتأكد من تفعيل الأمان", "صور توضيحية: خطوات حفظ كلمة المرور...")
    )

    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(tasks) { task ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = task.first, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = task.second, fontSize = 14.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF2A2A2A), RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    ) {
                        Text(text = task.third, fontSize = 12.sp, color = Color(0xFFFFB74D))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = { /* إرسال المهمة للمراجعة */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("أنهيت المهمة (إرسال للمراجعة)")
                    }
                }
            }
        }
    }
}

@Composable
fun AdminDashboardSection() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "لوحة تحكم المسؤول (المخزون والطلبات)", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "حسابات Car Parking المتوفرة: 5 حسابات", fontSize = 14.sp, color = Color.Green)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { /* إضافة حساب جديد */ }, modifier = Modifier.fillMaxWidth()) {
                    Text("إضافة حساب جديد للمخزون")
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "طلبات المستخدمين المعلقة: طلب واحد قيد المراجعة", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(onClick = { /* قبول وصرف المكافأة */ }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))) {
                        Text("موافقة وصرف المكافأة")
                    }
                    Button(onClick = { /* رفض الطلب */ }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC62828))) {
                        Text("رفض")
                    }
                }
            }
        }
    }
}
