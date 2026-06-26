package com.example.contactsjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewNotFavoriteWithPhoto()
        }
    }
}

@Composable
fun ContactDetails(contact: Contact) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ContactAvatar(contact = contact)
        NameBlock(contact = contact)

        InfoRow(label = stringResource(id = R.string.phone), value = contact.phone)
        InfoRow(label = stringResource(id = R.string.address), value = contact.address)

        if (contact.email != null) {
            InfoRow(label = stringResource(id = R.string.email), value = contact.email)
        }
    }
}

@Composable
fun ContactAvatar(contact: Contact) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .padding(top = 24.dp, bottom = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (contact.imageRes == null) {
            Icon(
                painter = painterResource(id = R.drawable.circle),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                tint = Color.LightGray
            )
            val initials = contact.name.take(1) + contact.familyName.take(1)
            Text(
                text = initials.uppercase(),
                style = MaterialTheme.typography.h5,
                color = Color.White
            )
        } else {
            Image(
                painter = painterResource(id = contact.imageRes),
                contentDescription = "Contact Photo",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun NameBlock(contact: Contact) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 24.dp)
    ) {
        val nameAndSurname = "${contact.name} ${contact.surname.orEmpty()}".trim()
        Text(
            text = nameAndSurname,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = contact.familyName,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )

            if (contact.isFavorite) {
                Image(
                    painter = painterResource(id = android.R.drawable.star_big_on),
                    contentDescription = "Favorite",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(28.dp)
                )
            }
        }
    }
}
@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = "$label:",
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.body1,
        )
        Text(
            text = value,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.body1
        )
    }
}
@Preview(showBackground = true, name = "Favorite with Initials")
@Composable
fun PreviewFavoriteWithInitials() {
    val contact = Contact(
        name = "Евгений",
        surname = "Андреевич",
        familyName = "Лукашин",
        imageRes = null,
        isFavorite = true,
        phone = "+7 495 495 95 95",
        address = "г. Москва, 3-я улица Строителей, д. 25, кв.12",
        email = "Elukashin@practicum.ru"
    )
    ContactDetails(contact = contact)
}

@Preview(showBackground = true, name = "Not Favorite with Photo")
@Composable
fun PreviewNotFavoriteWithPhoto() {
    val contact = Contact(
        name = "Василий",
        surname = null,
        familyName = "Кузякин",
        imageRes = R.drawable.test_image,
        isFavorite = false,
        phone = "---",
        address = "Ивановская область, дер. Крутово, д. 4",
        email = null
    )
    ContactDetails(contact = contact)
}