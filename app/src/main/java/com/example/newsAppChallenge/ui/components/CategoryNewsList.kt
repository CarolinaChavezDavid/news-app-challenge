package com.example.newsAppChallenge.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsAppChallenge.data.newsCategories
import com.example.newsAppChallenge.ui.theme.Platinum
import com.example.newsAppChallenge.ui.theme.RussianViolet
import com.example.newsAppChallenge.ui.theme.titleMediumStyle
import com.example.newsAppChallenge.ui.viewmodel.NewsUiState
import com.example.newsAppChallenge.ui.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryNewsList(viewModel: NewsViewModel = hiltViewModel()) {
    var selectedButtonIndex by remember { mutableIntStateOf(0) }

    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(vertical = 24.dp)) {
        itemsIndexed(newsCategories) { index, item ->
            Card(
                onClick = {
                    selectedButtonIndex = index
                    viewModel.reloadPage()
                    viewModel.getNews()
                },
                colors =
                    CardDefaults.cardColors(
                        containerColor = if (selectedButtonIndex == index) RussianViolet else Platinum,
                    ),
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = item,
                    style =
                        titleMediumStyle.copy(
                            color = if (selectedButtonIndex == index) Platinum else RussianViolet,
                        ),
                )
            }
        }
    }
}
