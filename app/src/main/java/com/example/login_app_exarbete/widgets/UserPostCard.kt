package com.example.login_app_exarbete.widgets

import android.media.Image
import android.os.Build
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.login_app_exarbete.models.UserPost

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserPostcard(item: UserPost) {
    val imageLink = "http://i.imgur.com/DvpvklR.png"
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.Bottom)
            ) {
                Row() {
                    ImageLoader(imageLink = imageLink)
                    Spacer(modifier = Modifier.fillMaxWidth(0.5F))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = item.title, style = typography.h6)
                        Text(text = item.body, style = typography.caption)
                    }
                }
                Text(text = item.userName)
                Text(text = item.createdAt)
            }
        }
    }
}

@Composable
fun ImageLoader(imageLink: String) {
    val context = LocalContext.current
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(context).data(imageLink).crossfade(true).transformations(
            CircleCropTransformation()
        ).build(),
        contentDescription = null,
        loading = {
            ImageLoadingAnimation()
        },
        modifier = Modifier
            .height(70.dp)
            .width(70.dp),
    )
}




