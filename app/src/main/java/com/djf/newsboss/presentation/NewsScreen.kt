package com.djf.newsboss.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.BottomAppBar
//import androidx.compose.ui.unit.dp

import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.djf.newsboss.R
import com.djf.newsboss.util.APILatestResultItem
import com.djf.newsboss.util.calculateTimeSince

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsScreen(
    list: State<List<APILatestResultItem>>,
    clickNews: () -> Unit,
    clickCrypto: () -> Unit,
    screen: State<Screen>
) {
    Scaffold(
        bottomBar = {
            BottomAppBar(actions = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    NavigationBarItem(selected = screen.value == Screen.NEWS, onClick = { clickNews() }, icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.news_24dp_e8eaed_fill0_wght400_grad0_opsz24),
                            modifier = Modifier,
                            contentDescription = ""
                        )
                    }, label = {
                        Text(text = "news")
                    })
                    NavigationBarItem(selected = screen.value == Screen.CRYPTO, onClick = { clickCrypto() }, icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.account_balance_24dp_e8eaed_fill0_wght400_grad0_opsz24),
                            modifier = Modifier,
                            contentDescription = ""
                        )
                    }, label = {
                        Text(text = "crypto")
                    })
                }
            })
        }
    ) {
        Surface {
            NewsList(list = list)
        }

    }

}

@Composable
fun NewsList(list: State<List<APILatestResultItem>>) {
    LazyColumn() {
        items(list.value) { item ->
            NewsItem(newsItem = item)
        }
    }
}


@Composable
fun NewsItem(newsItem: APILatestResultItem) {
    val context = LocalContext.current
    fun openLink(link: String, context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        context.startActivity(intent)
    }

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(15.dp)
            .clickable {
                openLink(newsItem.link ?: "", context)
            }
    ) {
        AsyncImage(
            model = newsItem.image_url,
            contentDescription = "news photo",
            modifier = Modifier
                .padding(5.dp)
                .clip(RoundedCornerShape(5.dp))
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = newsItem.source_icon,
                contentDescription = "source icon",
                modifier = Modifier
                    .size(30.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(5.dp))
            )
            Text(
                text = newsItem.source_id ?: "",
                modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = calculateTimeSince(newsItem.pubDate ?: ""),
                modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Text(
            text = newsItem.title ?: "",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(text = newsItem.description ?: "", style = MaterialTheme.typography.bodyMedium)
        HorizontalDivider(modifier = Modifier.padding(5.dp))

    }
}