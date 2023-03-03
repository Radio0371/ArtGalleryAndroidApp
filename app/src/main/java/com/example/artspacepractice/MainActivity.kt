package com.example.artspacepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspacepractice.ui.theme.ArtSpacePracticeTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpacePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun ArtSpaceApp() {
    ArtSpacePracticeTheme() {
        Surface(
            modifier = Modifier
            .fillMaxSize()
        ) {
            ArtSpaceStructure()
        }
    }
}

@Composable
fun ArtSpaceStructure() {
    var imageStepSeqNumber by remember { mutableStateOf(1) }
    val image = painterResource(R.drawable.amen_phoenix_logo)
    Column(verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .border(width = 4.dp, color = Color.Black, shape = RoundedCornerShape(2.dp))
            .size(width = 400.dp, height = 400.dp)
        )
        {
            Image(
                painter = image,
                contentDescription = "null",
                modifier = Modifier
                    .size(width = 370.dp, height = 370.dp)
                    .padding(all = 15.dp)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        ArtworkDescriptorColumn(imageStepSeqNumber)
        Spacer(modifier = Modifier.height(24.dp))
        ArtworkControl(imageStepSeqNumber)
    }
}

@Composable
fun ArtworkDescriptorColumn(imageSeqNum: Int) {
    Box(modifier = Modifier.border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(2.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(all = 15.dp)
        ) {
            Text(
                text = stringResource(R.string.siacc23_trophies_title),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
                //fontFamily = FontFamily.Monospace
            )
            Text(
                text = stringResource(R.string.siacc23_trophies_artist),
                modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(R.string.siacc23_trophies_year),
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.wrapContentWidth(Alignment.End)
            )
        }
    }
}

@Composable
fun ArtworkControl(imageSeqNum: Int) {
    Row() {
        Button(
            onClick = { /*imageSeqNum--*/ },
            colors = ButtonDefaults.buttonColors(Color.Gray),
            shape = CutCornerShape(10.dp),
            modifier = Modifier.size(width = 150.dp, height = 50.dp)
        ) {
            Text(
                text = stringResource(R.string.previous),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = { /*imageSeqNum++*/ },
            colors = ButtonDefaults.buttonColors(Color.Gray),
            shape = CutCornerShape(10.dp),
            modifier = Modifier.size(width = 150.dp, height = 50.dp)
        ) {
            Text(
                text = stringResource(R.string.next),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
        }
    }
}