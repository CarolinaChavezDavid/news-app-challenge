package com.example.newsAppChallenge.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsAppChallenge.data.UsersData
import com.example.newsAppChallenge.data.userExample
import com.example.newsAppChallenge.ui.components.CategoryItem
import com.example.newsAppChallenge.ui.components.UsersImageComponent
import com.example.newsAppChallenge.ui.theme.Platinum
import com.example.newsAppChallenge.ui.theme.labelSmallStyle
import com.example.newsAppChallenge.ui.theme.titleLargeStyle
import com.example.newsAppChallenge.ui.theme.titleMediumStyle
import com.example.news_app_challenge.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun UserDetailScreen(userData: UsersData) {
    Surface(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
    ) {
        LazyColumn {
            item { Spacer(modifier = Modifier.height(50.dp)) }
            item {
                ProfileHeader(user = userData)
            }
        }
    }
}

@Composable
fun ProfileHeader(user: UsersData) {
    OverlappingBoxes(modifier = Modifier.fillMaxWidth()) {
        UsersImageComponent(100.dp)
        UserInfoContainer(user)
    }
}

@Composable
fun ProfileItemItem(info: Pair<String, String>) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = info.first, style = titleMediumStyle)
        Text(text = info.second, style = labelSmallStyle)
    }
}

@Composable
fun UserInfoContainer(userData: UsersData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape =
            RoundedCornerShape(
                topStart = 40.dp,
                topEnd = 40.dp,
            ),
        colors =
            CardDefaults.cardColors(
                containerColor = Platinum,
            ),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Spacer(
                modifier = Modifier.height(50.dp),
            )
            Text(text = "${userData.firstname} ${userData.lastname}", style = titleLargeStyle)

            CategoryItem(stringResource(id = R.string.follow_user))
            FollowersRow()
            ProfileContent(userData)
        }
    }
}

@Composable
fun ProfileContent(user: UsersData) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Text(
            text = stringResource(id = R.string.about_me_user_title),
            style = titleLargeStyle,
            modifier = Modifier.padding(vertical = 12.dp),
        )
        Text(
            text = stringResource(id = R.string.about_me_user),
            modifier = Modifier.padding(vertical = 12.dp),
        )
        ProfileItemItem(Pair("Web site:", user.website))
        ProfileItemItem(Pair("Phone number:", user.phone))
        ProfileItemItem(Pair("Birth date:", user.birthDate))

        Text(
            text = stringResource(id = R.string.location_user_title),
            style = titleLargeStyle,
            modifier = Modifier.padding(vertical = 12.dp),
        )
        UserLocationCard(user)
    }
}

@Composable
fun UserLocationCard(userData: UsersData) {
    val recipeLocation = LatLng(userData.address.geo.lat.toDouble(), userData.address.geo.lng.toDouble())
    val cameraPositionState =
        rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(recipeLocation, 10f)
        }

    Card(
        modifier =
            Modifier.fillMaxWidth().height(400.dp).padding(vertical = 12.dp),
        shape = RoundedCornerShape(40.dp),
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
        ) {
            Marker(
                state = MarkerState(position = recipeLocation),
                title = userData.address.city,
                snippet = userData.address.street,
            )
        }
    }
}

@Composable
fun FollowersRow() {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        FollowersItem("123", "Following")
        Divider(
            color = MaterialTheme.colorScheme.primary,
            modifier =
                Modifier
                    .height(30.dp)
                    .width(1.dp),
        )
        FollowersItem("10", "Followers")
        Divider(
            color = MaterialTheme.colorScheme.primary,
            modifier =
                Modifier
                    .height(30.dp)
                    .width(1.dp),
        )
        FollowersItem("1.6M", "Likes")
    }
}

@Composable
fun FollowersItem(
    amount: String,
    title: String,
) {
    Column(
        modifier = Modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = amount, style = titleLargeStyle)
        Text(text = title, style = labelSmallStyle)
    }
}

@Composable
fun OverlappingBoxes(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content,
    ) { measure, constraints ->
        val smallBox = measure[0]
        val largeBox = measure[1]
        val looseConstraints =
            constraints.copy(
                minWidth = 0,
                minHeight = 0,
            )
        val largePlaceable = largeBox.measure(looseConstraints)
        val smallPlaceable = smallBox.measure(looseConstraints)
        layout(
            width = constraints.maxWidth,
            height = largePlaceable.height + smallPlaceable.height / 2,
        ) {
            largePlaceable.placeRelative(
                x = 0,
                y = smallPlaceable.height / 2,
            )
            smallPlaceable.placeRelative(
                x = (constraints.maxWidth - smallPlaceable.width) / 2,
                y = 0,
            )
        }
    }
}

@Preview
@Composable
fun PreviewUsersDetailScreen() {
    UserDetailScreen(userExample)
}
