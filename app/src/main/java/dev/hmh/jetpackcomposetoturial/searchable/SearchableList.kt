package dev.hmh.jetpackcomposetoturial.searchable

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.hmh.jetpackcomposetoturial.ui.theme.Purple200
import dev.hmh.jetpackcomposetoturial.ui.theme.Purple500
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun SearchAbleList() {
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = {
                TopBar()
            },
            backgroundColor = Purple200
        ) {
            CountryNavigation()
        }
    }


}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(
            text = "Country List",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        ) },
        backgroundColor = Purple500,
        contentColor = Color.White
    )
}

@Composable
fun CountryNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "country_list"
    ) {
        composable("country_list") {
            CountryListScreen(navController)
        }
    }
}

@Composable
fun CountryListScreen(
    navigator: NavHostController
) {
    val textVal = remember { mutableStateOf(TextFieldValue("")) }
    Column {
        SearchCountryList(textVal)
        CountryList(textVal)
    }
}

@Composable
fun SearchCountryList(
    textVal: MutableState<TextFieldValue>
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textVal.value,
        onValueChange = { it ->
            textVal.value = it
        },
        placeholder = { Text(text = "Search Country Name") },
        textStyle = TextStyle(
            Color.Black,
            fontSize = 18.sp
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (textVal.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        textVal.value = TextFieldValue("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "close",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            cursorColor = Color.Black,
            leadingIconColor = Color.Black,
            trailingIconColor = Color.Black,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )

    )
}

@Composable
fun CountryList(
    textVale: MutableState<TextFieldValue>
) {
    val context = LocalContext.current
    val countries = getListOfCountries()
    var filteredCountries: ArrayList<String>
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        val searchText = textVale.value.text
        filteredCountries = if (searchText.isEmpty()) {
            countries
        } else {
            val resultlList = ArrayList<String>()
            for (country in countries) {
                if (country.lowercase(locale = Locale.getDefault())
                        .contains(searchText.lowercase(Locale.getDefault()))
                ) {
                    resultlList.add(country)
                }
            }
            resultlList
        }
        items(filteredCountries) { filteredCountries ->
            CountryListItem(
                countryText = filteredCountries,
                onItemClick = { selectedCountry ->
                    Toast.makeText(context, "$selectedCountry", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

}

@Composable
fun CountryListItem(
    countryText: String,
    onItemClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable {
                onItemClick(countryText)
            }
            .background(Color.White)
            .height(60.dp)
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = countryText,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

/*@Composable
fun getListOfCountries(): ArrayList<String> {
    val isoCountryCodes = Locale.getISOCountries()
    val countryListWithEmojis = ArrayList<String>()
    for (countryCode in isoCountryCodes) {
        val locale = Locale("", countryCode)
        val countryName = locale.displayCountry
        val flagOffset = 0x1F1E6
        val asciiOffset = 0x41
        val firstChar = Character.codePointAt(countryCode, 0) - asciiOffset + flagOffset
        val secondChar = Character.codePointAt(countryCode, 1) - asciiOffset + flagOffset
        val flag = (String(Character.toChars(firstChar)) + String(Character.toChars(secondChar)))
        countryListWithEmojis.add("$countryName (${locale.country}) $flag")
    }
    return countryListWithEmojis
}*/

@Composable
fun getListOfCountries(): ArrayList<String> {
    val isoCountryCode = Locale.getISOCountries()
    val countryListWithEmojis = ArrayList<String>()
    for (countryCode in isoCountryCode) {
        val locale = Locale("", countryCode)
        val countryName = locale.displayCountry
        val flagOffset = 0X1F1E6
        val asciiOffset = 0X41
        val firstChar = Character.codePointAt(countryCode, 0) - asciiOffset + flagOffset
        val secondChar = Character.codePointAt(countryCode, 1)  - asciiOffset + flagOffset
        val flag =
            (String(Character.toChars(firstChar)) + String(Character.toChars(secondChar)))
        countryListWithEmojis.add("$countryName (${locale.country}) $flag")
        /*val flagOffset = 0x1F1E6
          val asciiOffset = 0x41
          val firstChar = Character.codePointAt(countryCode, 0) - asciiOffset + flagOffset
          val secondChar = Character.codePointAt(countryCode, 1) - asciiOffset + flagOffset
          val flag = (String(Character.toChars(firstChar)) + String(Character.toChars(secondChar)))
          countryListWithEmojis.add("$countryName (${locale.country}) $flag")*/

    }
    return countryListWithEmojis
}
