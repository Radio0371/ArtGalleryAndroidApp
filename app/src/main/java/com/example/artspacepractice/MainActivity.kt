package com.example.artspacepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspacepractice.ui.theme.ArtSpacePracticeTheme

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
    val maxImages = 4
    val galleryImage = when(imageStepSeqNumber) {
        1 -> ArtGalleryImage(
            painterResource(R.drawable.amen_phoenix_logo),
            stringResource(R.string.amen_phoenix_logo_cont_desc),
            stringResource(R.string.amen_phoenix_logo_title),
            stringResource(R.string.amen_phoenix_logo_artist),
            stringResource(R.string.amen_phoenix_logo_year)
        )
        2 -> ArtGalleryImage(
            painterResource(R.drawable.naomi_hat),
            stringResource(R.string.naomi_hat_cont_desc),
            stringResource(R.string.naomi_hat_title),
            stringResource(R.string.naomi_hat_artist),
            stringResource(R.string.naomi_hat_year)
        )
        3 -> ArtGalleryImage(
            painterResource(R.drawable.siacc23_trophies),
            stringResource(R.string.siacc23_trophies_cont_desc),
            stringResource(R.string.siacc23_trophies_title),
            stringResource(R.string.siacc23_trophies_artist),
            stringResource(R.string.siacc23_trophies_year)
        )
        4 -> ArtGalleryImage(
            painterResource(R.drawable.woodwork_presents),
            stringResource(R.string.christmas_presents_cont_desc),
            stringResource(R.string.christmas_presents_title),
            stringResource(R.string.christmas_presents_artist),
            stringResource(R.string.christmas_presents_year)
        )
        else -> ArtGalleryImage(
            painterResource(R.drawable.ic_launcher_background),
            imageDesc = "Out of Range",
            artTitle = "Unknown",
            artArtist = "Unknown",
            artYear = "Out of Range"
        )
    }

    Column(verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            //.padding(start = 10.dp, end = 10.dp)
            //.padding(all = 15.dp)
            .border(width = 4.dp, color = Color.Black, shape = RoundedCornerShape(2.dp))
            .size(width = 400.dp, height = 400.dp)
        )
        {
            Image(
                painter = galleryImage.art,
                contentDescription = galleryImage.artDesc,
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
                    //.size(width = 370.dp, height = 370.dp)
                    .padding(all = 15.dp)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        ArtworkDescriptorColumn(galleryImage)
        Spacer(modifier = Modifier.height(24.dp))
        ArtworkControl(
            { imageStepSeqNumber = previousStep(step = imageStepSeqNumber, maxStep = maxImages) },
            { imageStepSeqNumber = nextStep(step = imageStepSeqNumber, maxStep = maxImages)}
        )
    }
}

@Composable
fun ArtworkDescriptorColumn(galleryImage: ArtGalleryImage) {
    Box(
        modifier = Modifier
            .border(
                width = 3.dp,
                color = Color.Black,
                shape = RoundedCornerShape(2.dp)
            )
            .padding(all = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(2.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(all = 15.dp)
            ) {
                Text(
                    text = galleryImage.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                    //fontFamily = FontFamily.Monospace
                )
                Text(
                    text = galleryImage.artist,
                    modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
                )
                Text(
                    text = galleryImage.year,
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.wrapContentWidth(Alignment.End)
                )
            }
        }
    }
}

@Composable
fun ArtworkControl(previousImage: () -> Unit, nextImage: () -> Unit) {
    Row() {
        Button(
            onClick = previousImage,
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
            onClick = nextImage,
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

@VisibleForTesting
internal fun nextStep(step: Int, maxStep: Int): Int {
    var next = step
    next = if (next == maxStep) {
        1
    } else {
        step + 1
    }
    return next
}
@VisibleForTesting
internal fun previousStep(step: Int, maxStep: Int): Int {
    var next = step
    next = if (next == 1) {
        maxStep
    } else {
        step - 1
    }
    return next
}

class ArtGalleryImage(image: Painter, imageDesc: String, artTitle: String, artArtist: String, artYear: String) {
    val art = image
    val artDesc = imageDesc
    val title = artTitle
    val artist = artArtist
    val year = artYear
}
