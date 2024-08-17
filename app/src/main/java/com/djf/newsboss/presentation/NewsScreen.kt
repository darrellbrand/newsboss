package com.djf.newsboss.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.ui.unit.dp

import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.djf.newsboss.util.APILatestResultItem
import com.djf.newsboss.util.calculateTimeSince

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsScreen(list: State<List<APILatestResultItem>>) {
    Scaffold {
        Surface {
            NewsList(list = list)
        }
    }

}

@Composable
fun NewsList(list: State<List<APILatestResultItem>>) {
    LazyColumn {
        items(list.value) { item ->
            NewsItem(newsItem = item)
        }
    }
}


@Composable
fun NewsItem(newsItem: APILatestResultItem) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(15.dp)
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
                Text(text = newsItem.source_id, modifier = Modifier.padding(5.dp))
                Text(text = calculateTimeSince(newsItem.pubDate), modifier = Modifier.padding(5.dp))
            }
            Text(text = newsItem.title)
            Spacer(modifier = Modifier.size(5.dp))
            Text(text = newsItem.description)
            HorizontalDivider(modifier = Modifier.padding(5.dp))

        }
}