package com.example.finnishmembersofparliament

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finnishmembersofparliament.ui.theme.FinnishMembersOfParliamentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinnishMembersOfParliamentTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    FinnishMPApp()
                }
            }
        }
    }
}

@Composable
fun FinnishMPApp(modifier: Modifier = Modifier) {
    var currentMember by remember { mutableStateOf(ParliamentMembersData.members.random()) }

    val partyLogos = mapOf(
        "kesk" to R.drawable.keskusta,
        "ps" to R.drawable.peruss,
        "sd" to R.drawable.sdp,
        "kok" to R.drawable.kokoomus,
        "r" to R.drawable.sfp_rkp,
        "vas" to R.drawable.life_alliance,
        "vihr" to R.drawable.vihre_t,
        "kd" to R.drawable.kristian_party,
        "liik" to R.drawable.liike_nyt
    )

    Column(
        modifier = modifier.fillMaxSize().wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Member's Image
        Image(
            painter = painterResource(R.drawable.image_placeholder),
            contentDescription = null,
            modifier = Modifier.size(150.dp).clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Member's Name
        Text(
            text = "${currentMember.firstname} ${currentMember.lastname}",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Party Logo Row
        partyLogos[currentMember.party]?.let { logo ->
            Image(
                painter = painterResource(logo),
                contentDescription = "Party Logo",
                modifier = Modifier.size(80.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        InfoRow(label = "Minister:", value = if (currentMember.minister) "Yes" else "No")
        InfoRow(label = "Seat #:", value = currentMember.seatNumber.toString())
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            currentMember = ParliamentMembersData.members.random()
            Log.d("Member", currentMember.firstname)
        }) {
            Text(text = "Random")
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, Modifier.width(100.dp), fontWeight = FontWeight.Bold)
        Text(text = value)
    }
}

@Preview(showBackground = true)
@Composable
fun FinnishMPAppPreview() {
    FinnishMPApp()
}
