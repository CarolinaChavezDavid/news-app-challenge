package com.example.newsAppChallenge.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsAppChallenge.ui.theme.Platinum
import com.example.newsAppChallenge.ui.viewmodel.NewsViewModel
import com.example.news_app_challenge.R
import kotlinx.coroutines.launch

@Composable
fun SearchComponent(viewModel: NewsViewModel = hiltViewModel()) {
    var query by remember { mutableStateOf("") }

    var isFilterSelected by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            modifier =
                Modifier
                    .weight(5f)
                    .padding(end = 8.dp)
                    .onFocusChanged {
                        isFilterSelected = !isFilterSelected
                    },
            shape = RoundedCornerShape(20.dp),
            value = query,
            onValueChange = { newText ->
                query = newText
                coroutineScope.launch {
                    if (query.isNotEmpty()) {
                        viewModel.searchNews(query)
                    } else {
                        viewModel.getNews()
                    }
                }
            },
            trailingIcon = {
                Icon(
                    painter =
                        if (isFilterSelected) {
                            painterResource(
                                id = R.drawable.baseline_search_24,
                            )
                        } else {
                            painterResource(id = R.drawable.baseline_cancel_24)
                        },
                    contentDescription = "clear text",
                    modifier =
                        Modifier
                            .clickable {
                                coroutineScope.launch {
                                    query = ""
                                    viewModel.getNews()
                                    isFilterSelected = !isFilterSelected
                                }
                            },
                )
            },
            singleLine = true,
            colors =
                TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Platinum,
                ),
        )
    }
}
