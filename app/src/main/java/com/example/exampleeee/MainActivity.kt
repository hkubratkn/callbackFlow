package com.example.exampleeee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.size.Scale
import com.example.exampleeee.ui.theme.*
import dev.chrisbanes.accompanist.coil.CoilImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleeeeTheme {
                CardScreen()
            }
        }
    }
}

@Composable
fun CardScreen(){
    Surface(color = background, elevation = 4.dp) {
        Column {
            Row(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 24.dp,
                            bottom = 8.dp
                        )
                )
            }

            Card(
                modifier = Modifier
                    .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
                    .clip(RoundedCornerShape(20.dp)),
                elevation = 8.dp
            ) {
                Row {
                    Column {
                        Row {
                            AgeChip()
                            GenderIcon()
                        }
                        BehaviourChip()
                        Text(
                            text = "ooıoıoı",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                                .width(120.dp),
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "ppppepepepepep",
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 0.dp)
                        )
                        Text(
                            text = "sjksksksksksksl",
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 8.dp)
                        )
                        Text(
                            text = "kksksksjdknjd",
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 8.dp)
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(
                                RoundedCornerShape(
                                    topStart = 120.dp,
                                    topEnd = 20.dp,
                                    bottomStart = 0.dp,
                                    bottomEnd = 20.dp
                                )
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun AgeChip() {
    Chip(
        text = "Puppy",
        color = purpleButtonLight,
        textColor = Purple500,
        modifier = Modifier
            .padding(start = 8.dp, top = 24.dp)
            .width(60.dp)
    )
}


@Composable
fun Chip(
    text: String,
    color: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(24.dp)
            .clip(
                RoundedCornerShape(
                    CornerSize(12.dp),
                    CornerSize(12.dp),
                    CornerSize(12.dp),
                    CornerSize(12.dp)
                )
            )
            .background(color),
        contentAlignment = Alignment.Center

    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 4.dp),
            style = MaterialTheme.typography.caption,
            color = textColor
        )
    }
}

@Composable
fun GenderIcon() {
    Image(
        painter = painterResource(R.drawable.ic_baseline_menu_24),
        contentDescription = "male",
        modifier = Modifier
            .size(42.dp)
            .padding(start = 8.dp, end = 8.dp, top = 30.dp, bottom = 0.dp)
    )
}

@Composable
fun BehaviourChip() {
    Chip(
        text = "Loyal",
        color = behaviourBackground,
        textColor = behaviourTextColor,
        modifier = Modifier.padding(start = 8.dp, top = 4.dp)
    )
}



@Preview(showBackground = true)
@Composable
fun CardPreview(){
    ExampleeeeTheme {
        CardScreen()
    }
}


/**




@Composable
fun PetDetailsScreen(navController: NavController, petId: String, viewModel: PetDetailsViewModel) {
LaunchedEffect(petId) {
viewModel.loadPetInfo(petId = petId)
}
Surface(color = MaterialTheme.colors.background) {
val petState = viewModel.petData.observeAsState()
if (petState.value != null) {
val pet = petState.value!!
PetDetails(pet = pet, onBackPress = { navController.popBackStack() })
}
}
}

class PetDetailsViewModel : ViewModel() {

private val petRepository = PetRepository()
private val petLiveData = MutableLiveData<Pet>()
val petData: LiveData<Pet>
get() = petLiveData

fun loadPetInfo(petId: String) {
viewModelScope.launch {
petLiveData.value = petRepository.getPetById(petId)
}
}
}

@Composable
fun PetDetails(pet: Pet, onBackPress: () -> Unit) {
Column(
modifier = Modifier.verticalScroll(rememberScrollState())
) {
CoilImage(
data = pet.imageUrl,
contentDescription = null,
fadeIn = true,
contentScale = ContentScale.Crop,
modifier = Modifier
.height(350.dp)
.clip(
RoundedCornerShape(
topStart = CornerSize(0.dp),
topEnd = CornerSize(0.dp),
bottomEnd = CornerSize(32.dp),
bottomStart = CornerSize(32.dp)
)
)
)
PetCardInformation(pet = pet)
NameBreedSection(pet = pet)
Location(pet = pet)
AboutSection(pet = pet)
}
Icon(
Icons.Filled.ArrowBack, "back",
modifier = Modifier
.size(48.dp)
.clickable {
onBackPress()
}
.padding(12.dp)
)
Column(
modifier = Modifier.fillMaxSize(),
verticalArrangement = Arrangement.Bottom
) {
AdoptButtonBar()
}
}

@Composable
fun NameBreedSection(pet: Pet) {
Row(
modifier = Modifier.fillMaxWidth(),
horizontalArrangement = Arrangement.SpaceBetween
) {
Text(
text = pet.name,
fontWeight = FontWeight.SemiBold,
style = MaterialTheme.typography.h6,
modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 0.dp)
)
Text(
text = pet.breed,
style = MaterialTheme.typography.h6,
modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 0.dp),
textAlign = TextAlign.End
)
}
}

@Composable
fun AboutSection(pet: Pet) {
Text(
text = stringResource(id = R.string.app_name),
style = MaterialTheme.typography.h6,
fontWeight = FontWeight.SemiBold,
modifier = Modifier.padding(start = 16.dp, end = 16.dp)
)
Text(
text = pet.description,
textAlign = TextAlign.Justify,
style = MaterialTheme.typography.body1,
modifier = Modifier.padding(16.dp)
)
Spacer(modifier = Modifier.height(64.dp))
}

@Composable
fun Location(pet: Pet) {
Row(
modifier = Modifier.padding(16.dp),
verticalAlignment = Alignment.CenterVertically
) {
Icon(
Icons.Outlined.Place, null,
modifier = Modifier
.width(16.dp)
.padding(end = 2.dp, top = 2.dp)
)
Text(
text = pet.location,
style = MaterialTheme.typography.body1,
)
}
}

@Composable
fun PetCardInformation(pet: Pet) {
Row(
modifier = Modifier.fillMaxWidth(),
horizontalArrangement = Arrangement.Center
) {
InfoCard(
title = R.drawable.ic_launcher_foreground,
text = pet.age.toString() + " Years"
)
InfoCard(
title = R.drawable.ic_launcher_background,
text = "${pet.weightKg}kg"
)
InfoCard(
title = R.drawable.ic_launcher_foreground,
text = pet.gender.name.capitalize()
)
InfoCard(
title = R.drawable.ic_launcher_foreground,
text = pet.health.capitalize()
)
}
}

@Composable
fun InfoCard(title: Int, text: String) {
Card(
modifier = Modifier
.padding(start = 8.dp, end = 8.dp, top = 8.dp)
.size(80.dp, 64.dp)
.clip(RoundedCornerShape(12.dp)),
elevation = 8.dp,
backgroundColor = behaviourBackground
) {
Column(
verticalArrangement = Arrangement.Center,
horizontalAlignment = Alignment.CenterHorizontally
) {
Icon(
painter = painterResource(id = title), null,
modifier = Modifier
.width(24.dp)
.padding(end = 2.dp, top = 2.dp),

)
Text(
text = text,
modifier = Modifier.fillMaxWidth(),
textAlign = TextAlign.Center,
fontWeight = FontWeight.SemiBold,
style = MaterialTheme.typography.body1
)
}
}
}

@Composable
fun AdoptButtonBar() {
Row(
modifier = Modifier.fillMaxWidth(),
horizontalArrangement = Arrangement.Start
) {
Button(
elevation = null,
modifier = Modifier
.padding(16.dp)
.weight(4f)
.height(52.dp),
onClick = { },
colors = ButtonDefaults.buttonColors(behaviourBackground)
) {
Row(
horizontalArrangement = Arrangement.Center,
verticalAlignment = Alignment.CenterVertically
) {
Image(
painter = painterResource(id=R.drawable.ic_launcher_foreground),
contentDescription = null,
alignment = Alignment.Center,
modifier = Modifier
.size(16.dp)
.padding(top = 2.dp, end = 2.dp)
)
Text(
textAlign = TextAlign.Center,
fontWeight = FontWeight.SemiBold,
color = behaviourTextColor,
text = stringResource(id = R.string.app_name)
)
}
}
Button(
elevation = null,
colors = ButtonDefaults.buttonColors(backgroundColor = behaviourBackground),
modifier = Modifier
.padding(top = 16.dp, bottom = 16.dp, end = 16.dp)
.weight(1f)
.wrapContentWidth()
.height(52.dp),
onClick = {}
) {
Icon(
Icons.Filled.Phone, "phone",
tint = behaviourTextColor,
modifier = Modifier
.size(48.dp)
.padding(4.dp)
)
}
}
}



class PetRepository {
private val listOfPets = listOf(
dog1, dog2, dog3, dog4, dog5, dog6, dog7,
dog8, dog9, dog10
)

fun getListPets(): List<Pet> {
return listOfPets
}

fun getPetById(id: String): Pet? {
return listOfPets.find { it.id == id }
}
}

val dog1 = Pet(
"1",
"Brodie",
"Brodie is gorgeous, 4 year old female (whelped 4/22/17) German Shepherd in desperate need of a good home with an active lifestyle, experienced owner . She has been living outside but is in great physical condition. Brodie is housebroken, current on vaccinations and was spayed on 1/27/21. She is wary of new people, prefers women over men and needs some socialization and work on her leash manners. Brodie has tested positive for heartworms, but we have already begun her treatment and she is doing great ! We believe once socialized, Brodie will be OK with other dogs, but for now she needs to be the only pet in the home.",
"https://images.unsplash.com/photo-1589941013453-ec89f33b5e95?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1003&q=80",
Gender.Male,
39.4f,
"German Sheppard",
"Iverness, Florida",
4,
PetLabel.Young,
Behaviour.Loyal,
Vaccination.Vaccinated,
"Healthy"
)

val dog2 = Pet(
"2",
"Nelson",
"Nelson is a young sweet Akita that loves people. He may get along with a female dog of his size, with proper introduction. Nelson needs a secure home and yard and some basic obedience training. He is currently boarding in Memphis TN.",
"https://images.unsplash.com/photo-1523171067267-bb96f975c4bb?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1000&q=80",
Gender.Female,
29.3f,
"Akita Inu",
"JacksonVille, Florida",
2,
PetLabel.Young,
Behaviour.Happy,
Vaccination.Unvaccinated,
"Healthy"
)

val dog3 = Pet(
"3",
"Herman",
"Herman has been patiently waiting in a foster home where he cannot get enough playtime with other dogs or annoying the family cat. He foster mom says he's crate and house-trained, a goofy cuddler and a bit vocal when he really wants the treat in your hand. You could never tell Joker was a throw-away since he is constantly wanting to give you kisses.",
"https://images.unsplash.com/photo-1547407139-3c921a66005c?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80",
Gender.Male,
32.2f,
"Husky",
"Colorado Springs, Colorado",
9,
PetLabel.Elder,
Behaviour.Stubborn,
Vaccination.Vaccinated,
"Healthy"
)

val dog4 = Pet(
"4",
"Daisy",
"Daisy is a bright, sensitive dog who enjoys play with his human family and responds well to training. As herders bred to move cattle, they are fearless and independent. They are vigilant watchdogs, with acute senses and a “big dog” bark. Families who can meet their bold but kindly Pembroke’s need for activity and togetherness will never have a more loyal, loving pet.",
"https://images.unsplash.com/photo-1611170874942-4f4bed56a14a?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1600&q=80",
Gender.Female,
9.8f,
"Golden Retriever",
"Whitewright, Texas",
1,
PetLabel.Puppy,
Behaviour.Excited,
Vaccination.Vaccinated,
"Healthy"
)

val dog5 = Pet(
"5",
"Micy",
"Micy is a bright, sensitive dog who enjoys play with his human family and responds well to training. As herders bred to move cattle, they are fearless and independent. They are vigilant watchdogs, with acute senses and a “big dog” bark. Families who can meet their bold but kindly Pembroke’s need for activity and togetherness will never have a more loyal, loving pet.",
"https://images.unsplash.com/photo-1587790311640-50b019663f01?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1100&q=80",
Gender.Female,
19.5f,
"Pitbull",
"Starkville, Mississippi",
12,
PetLabel.Elder,
Behaviour.Angry,
Vaccination.Vaccinated,
"Disabled"
)
val dog6 = Pet(
"6",
"Skippy",
"Skippy came to Mutt Love as a stray from NC. Skippy is about three years old and weighs 12kg. Skippy is heartworm positive and will need to undergo heartworm treatment. He would benefit from a foster home while recouping from the treatment.",
"https://images.unsplash.com/photo-1543466835-00a7907e9de1?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1567&q=80",
Gender.Female,
12.3f,
"Beagle",
"Fairfax, Virginia",
2,
PetLabel.Young,
Behaviour.Friendly,
Vaccination.Unvaccinated,
"Diarrhoea"
)

val dog7 = Pet(
"7",
"Yuki",
"Yuki is a 3 year old Samoyed breed, who just wants all the attention and affection he can get! He is dog friendly but NOT cat friendly (unsure of how he is with smaller dogs). He is very vocal and treat motivated and will let you know when he doesn't like something. He loves to run around outside and go for walks but mostly wants to stay right next to his humans. Yuki is an Alpha, but plays well with others.",
"https://images.unsplash.com/photo-1529429617124-95b109e86bb8?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=975&q=80",
Gender.Male,
22.4f,
"Samoyed",
"Quinlan, Texas",
3,
PetLabel.Young,
Behaviour.Friendly,
Vaccination.Unvaccinated,
"Heartworm"
)

val dog8 = Pet(
"8",
"Lilly",
"Lilly has been patiently waiting in a foster home where she cannot get enough playtime with other dogs or annoying (and taking naps next to) the family cat. Her foster mom says she's crate and house-trained, a goofy cuddler and a bit vocal when she really wants the treat in your hand. You could never tell Lilly was a throw-away since she is constantly wanting to give you kisses.",
"https://images.unsplash.com/photo-1478991031579-5f22c0ee9c9f?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1650&q=80",
Gender.Female,
4.7f,
"Yorkshire Terrier",
"Sharon, Connecticut",
1,
PetLabel.Puppy,
Behaviour.Stubborn,
Vaccination.Unvaccinated,
"Healthy"
)

val dog9 = Pet(
"9",
"Joker",
"Joker has been patiently waiting in a foster home where he cannot get enough playtime with other dogs or annoying the family cat. He foster mom says he's crate and house-trained, a goofy cuddler and a bit vocal when he really wants the treat in your hand. You could never tell Joker was a throw-away since he is constantly wanting to give you kisses.",
"https://images.unsplash.com/photo-1516131537578-9ca049cc6b59?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1650&q=80",
Gender.Male,
35.8f,
"Alaskan Malamute",
"Norco, California",
10,
PetLabel.Elder,
Behaviour.Stubborn,
Vaccination.Vaccinated,
"Arthritis"
)

val dog10 = Pet(
"10",
"Libby",
"Libby has been patiently waiting in a foster home where she cannot get enough playtime with other dogs or annoying (and taking naps next to) the family cat. Her foster mom says she's crate and house-trained, a goofy cuddler and a bit vocal when she really wants the treat in your hand. You could never tell Libby was a throw-away since she is constantly wanting to give you kisses.",
"https://images.unsplash.com/photo-1599765625577-61a6e65e3567?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1077&q=80",
Gender.Female,
8.3f,
"Dalmatian",
"Houston, Texas",
1,
PetLabel.Puppy,
Behaviour.Excited,
Vaccination.Vaccinated,
"Healthy"
)


data class Pet(
val id: String,
val name: String,
val description: String,
val imageUrl: String,
val gender: Gender,
val weightKg: Float,
val breed: String,
val location: String,
val age: Int,
val label: PetLabel,
val behavior: Behaviour,
val vaccination: Vaccination,
val health: String

)



enum class Behaviour {
Friendly,
Stubborn,
Angry,
Happy,
Excited,
Lazy,
Loyal
}

enum class Gender {
Male,
Female,
NotSpecified
}

enum class Vaccination {
Vaccinated,
Unvaccinated
}


 */






/**
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.example.exampleeee.ui.theme.*
import dev.chrisbanes.accompanist.coil.CoilImage

class MainActivity : ComponentActivity() {
override fun onCreate(savedInstanceState: Bundle?) {
super.onCreate(savedInstanceState)
setContent {
ExampleeeeTheme {
ComposeAdoptionApp()
}
}
}
}






@Composable
fun ComposeAdoptionApp() {
val navController = rememberNavController()
NavHost(navController, startDestination = "list") {
composable("list") { PetMainScreen(navController, viewModel = viewModel()) }
composable(
"details/{petId}",
arguments = listOf(navArgument("petId") { type = NavType.StringType })
) {
PetDetailsScreen(
navController,
petId = it.arguments?.getString("petId")!!,
viewModel = viewModel()
)
}
}
}

class PetMainScreenViewModel : ViewModel() {
private val petRepository = PetRepository()
private val petLiveData = MutableLiveData<List<Pet>>()
val petsData: LiveData<List<Pet>>
get() = petLiveData

init {
viewModelScope.launch {
petLiveData.value = petRepository.getListPets()
}
}
}

@Composable
fun PetMainScreen(navController: NavController, viewModel: PetMainScreenViewModel) {
val pets = viewModel.petsData.observeAsState(emptyList())
PetListScreenContent(pets = pets.value) { pet ->
val petId = pet.id
navController.navigate(route = "details/$petId")
}
}

@Composable
fun PetListScreenContent(pets: List<Pet>, onPetSelected: (Pet) -> Unit) {
Surface(color = background, elevation = 4.dp) {
Column {
Row(
modifier = Modifier
.height(80.dp)
.fillMaxWidth()
.padding(end = 16.dp),
horizontalArrangement = Arrangement.SpaceBetween,
verticalAlignment = Alignment.CenterVertically
) {
Text(
text = stringResource(id = R.string.app_name),
style = MaterialTheme.typography.h5,
textAlign = TextAlign.Center,
fontWeight = FontWeight.SemiBold,
modifier = Modifier
.fillMaxHeight()
.padding(
start = 24.dp,
end = 24.dp,
top = 24.dp,
bottom = 8.dp
)
)
}

PetLazyColumn(
modifier = Modifier.padding(
start = 12.dp,
end = 12.dp
),
pets
) { pet ->
onPetSelected(pet)
}
}
}
}

@Composable
fun PetCardListItem(pet: Pet, onPetClick: (Pet) -> Unit) {
Card(
modifier = Modifier
.padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
.clip(RoundedCornerShape(20.dp))
.clickable {
onPetClick(pet)
},
elevation = 8.dp
) {

Row {
Column {
Row {
AgeChip(pet = pet)
GenderIcon(pet = pet)
}
BehaviourChip(pet)
Text(
pet.name,
style = MaterialTheme.typography.h6,
modifier = Modifier
.padding(start = 8.dp, end = 8.dp)
.width(120.dp),
fontWeight = FontWeight.SemiBold
)
Text(
pet.breed,
style = MaterialTheme.typography.body2,
modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 0.dp)
)
Text(
pet.weightKg.toString() + " kg",
style = MaterialTheme.typography.body2,
modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 8.dp)
)
Text(
pet.vaccination.name,
style = MaterialTheme.typography.body2,
modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 8.dp)
)
}
CoilImage(
fadeIn = true,
data = pet.imageUrl,
contentDescription = null,
contentScale = ContentScale.Crop,
modifier = Modifier
.fillMaxWidth()
.height(180.dp)
.clip(
RoundedCornerShape(
topStart = 120.dp,
topEnd = 20.dp,
bottomStart = 0.dp,
bottomEnd = 20.dp
)
)
)
}
}
}

@Composable
fun AgeChip(pet: Pet) {
when (pet.label) {
PetLabel.Puppy -> {
Chip(
"Puppy",
color = purpleButtonLight,
textColor = Purple500,
modifier = Modifier
.padding(start = 8.dp, top = 24.dp)
.width(60.dp)
)
}
PetLabel.Young -> {
Chip(
"Young",
color = orangeButtonLight,
textColor = orangeText,
modifier = Modifier
.padding(start = 8.dp, top = 24.dp)
.width(60.dp)
)
}
PetLabel.Elder -> {
Chip(
"Elder",
color = elderBackground,
textColor = elderTextColor,
modifier = Modifier
.padding(start = 8.dp, top = 24.dp)
.width(60.dp)
)
}
}
}

@Composable
fun GenderIcon(pet: Pet) {
if (pet.gender == Gender.Male) {
Image(
painterResource(R.drawable.ic_launcher_foreground),
"male",
modifier = Modifier
.size(42.dp)
.padding(start = 8.dp, end = 8.dp, top = 30.dp, bottom = 0.dp)
)
} else if (pet.gender == Gender.Female) {
Image(
painterResource(R.drawable.ic_launcher_background),
contentDescription = "female",
modifier = Modifier
.size(42.dp)
.padding(start = 8.dp, end = 8.dp, top = 30.dp, bottom = 0.dp)
)
}
}

@Composable
fun BehaviourChip(pet: Pet) {
when (pet.behavior) {
Behaviour.Loyal -> {
Chip(
"Loyal",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
Behaviour.Angry -> {
Chip(
"Angry",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
Behaviour.Excited -> {
Chip(
"Excited",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
Behaviour.Friendly -> {
Chip(
"Friendly",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
Behaviour.Happy -> {
Chip(
"Happy",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
Behaviour.Lazy -> {
Chip(
"Lazy",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
Behaviour.Stubborn -> {
Chip(
"Stubborn",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
}
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PetLazyColumn(modifier: Modifier, pets: List<Pet>, onPetClick: (Pet) -> Unit) {
val scrollState = rememberLazyListState()
LazyColumn(
modifier = modifier, scrollState,
content = {

items(pets.size) { index ->
PetCardListItem(pet = pets[index], onPetClick = onPetClick)
}
},
)
}

@Composable
fun PetDetailsScreen(navController: NavController, petId: String, viewModel: PetDetailsViewModel) {
LaunchedEffect(petId) {
viewModel.loadPetInfo(petId = petId)
}
Surface(color = MaterialTheme.colors.background) {
val petState = viewModel.petData.observeAsState()
if (petState.value != null) {
val pet = petState.value!!
PetDetails(pet = pet, onBackPress = { navController.popBackStack() })
}
}
}

class PetDetailsViewModel : ViewModel() {

private val petRepository = PetRepository()
private val petLiveData = MutableLiveData<Pet>()
val petData: LiveData<Pet>
get() = petLiveData

fun loadPetInfo(petId: String) {
viewModelScope.launch {
petLiveData.value = petRepository.getPetById(petId)
}
}
}

@Composable
fun PetDetails(pet: Pet, onBackPress: () -> Unit) {
Column(
modifier = Modifier.verticalScroll(rememberScrollState())
) {
CoilImage(
data = pet.imageUrl,
contentDescription = null,
fadeIn = true,
contentScale = ContentScale.Crop,
modifier = Modifier
.height(350.dp)
.clip(
RoundedCornerShape(
topStart = CornerSize(0.dp),
topEnd = CornerSize(0.dp),
bottomEnd = CornerSize(32.dp),
bottomStart = CornerSize(32.dp)
)
)
)
PetCardInformation(pet = pet)
NameBreedSection(pet = pet)
Location(pet = pet)
AboutSection(pet = pet)
}
Icon(
Icons.Filled.ArrowBack, "back",
modifier = Modifier
.size(48.dp)
.clickable {
onBackPress()
}
.padding(12.dp)
)
Column(
modifier = Modifier.fillMaxSize(),
verticalArrangement = Arrangement.Bottom
) {
AdoptButtonBar()
}
}

@Composable
fun NameBreedSection(pet: Pet) {
Row(
modifier = Modifier.fillMaxWidth(),
horizontalArrangement = Arrangement.SpaceBetween
) {
Text(
text = pet.name,
fontWeight = FontWeight.SemiBold,
style = MaterialTheme.typography.h6,
modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 0.dp)
)
Text(
text = pet.breed,
style = MaterialTheme.typography.h6,
modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 0.dp),
textAlign = TextAlign.End
)
}
}

@Composable
fun AboutSection(pet: Pet) {
Text(
text = stringResource(id = R.string.app_name),
style = MaterialTheme.typography.h6,
fontWeight = FontWeight.SemiBold,
modifier = Modifier.padding(start = 16.dp, end = 16.dp)
)
Text(
text = pet.description,
textAlign = TextAlign.Justify,
style = MaterialTheme.typography.body1,
modifier = Modifier.padding(16.dp)
)
Spacer(modifier = Modifier.height(64.dp))
}

@Composable
fun Location(pet: Pet) {
Row(
modifier = Modifier.padding(16.dp),
verticalAlignment = Alignment.CenterVertically
) {
Icon(
Icons.Outlined.Place, null,
modifier = Modifier
.width(16.dp)
.padding(end = 2.dp, top = 2.dp)
)
Text(
text = pet.location,
style = MaterialTheme.typography.body1,
)
}
}

@Composable
fun PetCardInformation(pet: Pet) {
Row(
modifier = Modifier.fillMaxWidth(),
horizontalArrangement = Arrangement.Center
) {
InfoCard(
title = R.drawable.ic_launcher_foreground,
text = pet.age.toString() + " Years"
)
InfoCard(
title = R.drawable.ic_launcher_background,
text = "${pet.weightKg}kg"
)
InfoCard(
title = R.drawable.ic_launcher_foreground,
text = pet.gender.name.capitalize()
)
InfoCard(
title = R.drawable.ic_launcher_foreground,
text = pet.health.capitalize()
)
}
}

@Composable
fun InfoCard(title: Int, text: String) {
Card(
modifier = Modifier
.padding(start = 8.dp, end = 8.dp, top = 8.dp)
.size(80.dp, 64.dp)
.clip(RoundedCornerShape(12.dp)),
elevation = 8.dp,
backgroundColor = behaviourBackground
) {
Column(
verticalArrangement = Arrangement.Center,
horizontalAlignment = Alignment.CenterHorizontally
) {
Icon(
painter = painterResource(id = title), null,
modifier = Modifier
.width(24.dp)
.padding(end = 2.dp, top = 2.dp),

)
Text(
text = text,
modifier = Modifier.fillMaxWidth(),
textAlign = TextAlign.Center,
fontWeight = FontWeight.SemiBold,
style = MaterialTheme.typography.body1
)
}
}
}

@Composable
fun AdoptButtonBar() {
Row(
modifier = Modifier.fillMaxWidth(),
horizontalArrangement = Arrangement.Start
) {
Button(
elevation = null,
modifier = Modifier
.padding(16.dp)
.weight(4f)
.height(52.dp),
onClick = { },
colors = ButtonDefaults.buttonColors(behaviourBackground)
) {
Row(
horizontalArrangement = Arrangement.Center,
verticalAlignment = Alignment.CenterVertically
) {
Image(
painter = painterResource(id=R.drawable.ic_launcher_foreground),
contentDescription = null,
alignment = Alignment.Center,
modifier = Modifier
.size(16.dp)
.padding(top = 2.dp, end = 2.dp)
)
Text(
textAlign = TextAlign.Center,
fontWeight = FontWeight.SemiBold,
color = behaviourTextColor,
text = stringResource(id = R.string.app_name)
)
}
}
Button(
elevation = null,
colors = ButtonDefaults.buttonColors(backgroundColor = behaviourBackground),
modifier = Modifier
.padding(top = 16.dp, bottom = 16.dp, end = 16.dp)
.weight(1f)
.wrapContentWidth()
.height(52.dp),
onClick = {}
) {
Icon(
Icons.Filled.Phone, "phone",
tint = behaviourTextColor,
modifier = Modifier
.size(48.dp)
.padding(4.dp)
)
}
}
}


@Composable
fun Chip(
text: String,
color: Color,
textColor: Color,
modifier: Modifier = Modifier
) {
Box(
modifier = modifier
.height(24.dp)
.clip(
RoundedCornerShape(
CornerSize(12.dp),
CornerSize(12.dp),
CornerSize(12.dp),
CornerSize(12.dp)
)
)
.background(color),
contentAlignment = Alignment.Center

) {
Text(
text = text,
textAlign = TextAlign.Center,
modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 4.dp),
style = MaterialTheme.typography.caption,
color = textColor
)
}
}


class PetRepository {
private val listOfPets = listOf(
dog1, dog2, dog3, dog4, dog5, dog6, dog7,
dog8, dog9, dog10
)

fun getListPets(): List<Pet> {
return listOfPets
}

fun getPetById(id: String): Pet? {
return listOfPets.find { it.id == id }
}
}

val dog1 = Pet(
"1",
"Brodie",
"Brodie is gorgeous, 4 year old female (whelped 4/22/17) German Shepherd in desperate need of a good home with an active lifestyle, experienced owner . She has been living outside but is in great physical condition. Brodie is housebroken, current on vaccinations and was spayed on 1/27/21. She is wary of new people, prefers women over men and needs some socialization and work on her leash manners. Brodie has tested positive for heartworms, but we have already begun her treatment and she is doing great ! We believe once socialized, Brodie will be OK with other dogs, but for now she needs to be the only pet in the home.",
"https://images.unsplash.com/photo-1589941013453-ec89f33b5e95?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1003&q=80",
Gender.Male,
39.4f,
"German Sheppard",
"Iverness, Florida",
4,
PetLabel.Young,
Behaviour.Loyal,
Vaccination.Vaccinated,
"Healthy"
)

val dog2 = Pet(
"2",
"Nelson",
"Nelson is a young sweet Akita that loves people. He may get along with a female dog of his size, with proper introduction. Nelson needs a secure home and yard and some basic obedience training. He is currently boarding in Memphis TN.",
"https://images.unsplash.com/photo-1523171067267-bb96f975c4bb?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1000&q=80",
Gender.Female,
29.3f,
"Akita Inu",
"JacksonVille, Florida",
2,
PetLabel.Young,
Behaviour.Happy,
Vaccination.Unvaccinated,
"Healthy"
)

val dog3 = Pet(
"3",
"Herman",
"Herman has been patiently waiting in a foster home where he cannot get enough playtime with other dogs or annoying the family cat. He foster mom says he's crate and house-trained, a goofy cuddler and a bit vocal when he really wants the treat in your hand. You could never tell Joker was a throw-away since he is constantly wanting to give you kisses.",
"https://images.unsplash.com/photo-1547407139-3c921a66005c?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80",
Gender.Male,
32.2f,
"Husky",
"Colorado Springs, Colorado",
9,
PetLabel.Elder,
Behaviour.Stubborn,
Vaccination.Vaccinated,
"Healthy"
)

val dog4 = Pet(
"4",
"Daisy",
"Daisy is a bright, sensitive dog who enjoys play with his human family and responds well to training. As herders bred to move cattle, they are fearless and independent. They are vigilant watchdogs, with acute senses and a “big dog” bark. Families who can meet their bold but kindly Pembroke’s need for activity and togetherness will never have a more loyal, loving pet.",
"https://images.unsplash.com/photo-1611170874942-4f4bed56a14a?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1600&q=80",
Gender.Female,
9.8f,
"Golden Retriever",
"Whitewright, Texas",
1,
PetLabel.Puppy,
Behaviour.Excited,
Vaccination.Vaccinated,
"Healthy"
)

val dog5 = Pet(
"5",
"Micy",
"Micy is a bright, sensitive dog who enjoys play with his human family and responds well to training. As herders bred to move cattle, they are fearless and independent. They are vigilant watchdogs, with acute senses and a “big dog” bark. Families who can meet their bold but kindly Pembroke’s need for activity and togetherness will never have a more loyal, loving pet.",
"https://images.unsplash.com/photo-1587790311640-50b019663f01?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1100&q=80",
Gender.Female,
19.5f,
"Pitbull",
"Starkville, Mississippi",
12,
PetLabel.Elder,
Behaviour.Angry,
Vaccination.Vaccinated,
"Disabled"
)
val dog6 = Pet(
"6",
"Skippy",
"Skippy came to Mutt Love as a stray from NC. Skippy is about three years old and weighs 12kg. Skippy is heartworm positive and will need to undergo heartworm treatment. He would benefit from a foster home while recouping from the treatment.",
"https://images.unsplash.com/photo-1543466835-00a7907e9de1?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1567&q=80",
Gender.Female,
12.3f,
"Beagle",
"Fairfax, Virginia",
2,
PetLabel.Young,
Behaviour.Friendly,
Vaccination.Unvaccinated,
"Diarrhoea"
)

val dog7 = Pet(
"7",
"Yuki",
"Yuki is a 3 year old Samoyed breed, who just wants all the attention and affection he can get! He is dog friendly but NOT cat friendly (unsure of how he is with smaller dogs). He is very vocal and treat motivated and will let you know when he doesn't like something. He loves to run around outside and go for walks but mostly wants to stay right next to his humans. Yuki is an Alpha, but plays well with others.",
"https://images.unsplash.com/photo-1529429617124-95b109e86bb8?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=975&q=80",
Gender.Male,
22.4f,
"Samoyed",
"Quinlan, Texas",
3,
PetLabel.Young,
Behaviour.Friendly,
Vaccination.Unvaccinated,
"Heartworm"
)

val dog8 = Pet(
"8",
"Lilly",
"Lilly has been patiently waiting in a foster home where she cannot get enough playtime with other dogs or annoying (and taking naps next to) the family cat. Her foster mom says she's crate and house-trained, a goofy cuddler and a bit vocal when she really wants the treat in your hand. You could never tell Lilly was a throw-away since she is constantly wanting to give you kisses.",
"https://images.unsplash.com/photo-1478991031579-5f22c0ee9c9f?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1650&q=80",
Gender.Female,
4.7f,
"Yorkshire Terrier",
"Sharon, Connecticut",
1,
PetLabel.Puppy,
Behaviour.Stubborn,
Vaccination.Unvaccinated,
"Healthy"
)

val dog9 = Pet(
"9",
"Joker",
"Joker has been patiently waiting in a foster home where he cannot get enough playtime with other dogs or annoying the family cat. He foster mom says he's crate and house-trained, a goofy cuddler and a bit vocal when he really wants the treat in your hand. You could never tell Joker was a throw-away since he is constantly wanting to give you kisses.",
"https://images.unsplash.com/photo-1516131537578-9ca049cc6b59?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1650&q=80",
Gender.Male,
35.8f,
"Alaskan Malamute",
"Norco, California",
10,
PetLabel.Elder,
Behaviour.Stubborn,
Vaccination.Vaccinated,
"Arthritis"
)

val dog10 = Pet(
"10",
"Libby",
"Libby has been patiently waiting in a foster home where she cannot get enough playtime with other dogs or annoying (and taking naps next to) the family cat. Her foster mom says she's crate and house-trained, a goofy cuddler and a bit vocal when she really wants the treat in your hand. You could never tell Libby was a throw-away since she is constantly wanting to give you kisses.",
"https://images.unsplash.com/photo-1599765625577-61a6e65e3567?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1077&q=80",
Gender.Female,
8.3f,
"Dalmatian",
"Houston, Texas",
1,
PetLabel.Puppy,
Behaviour.Excited,
Vaccination.Vaccinated,
"Healthy"
)


data class Pet(
val id: String,
val name: String,
val description: String,
val imageUrl: String,
val gender: Gender,
val weightKg: Float,
val breed: String,
val location: String,
val age: Int,
val label: PetLabel,
val behavior: Behaviour,
val vaccination: Vaccination,
val health: String

)

enum class PetLabel {
Puppy,
Young,
Elder
}

enum class Behaviour {
Friendly,
Stubborn,
Angry,
Happy,
Excited,
Lazy,
Loyal
}

enum class Gender {
Male,
Female,
NotSpecified
}

enum class Vaccination {
Vaccinated,
Unvaccinated
}


 */
/**
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.example.exampleeee.ui.theme.*
import dev.chrisbanes.accompanist.coil.CoilImage

class MainActivity : ComponentActivity() {
override fun onCreate(savedInstanceState: Bundle?) {
super.onCreate(savedInstanceState)
setContent {
ExampleeeeTheme {
ComposeAdoptionApp()
}
}
}
}






@Composable
fun ComposeAdoptionApp() {
val navController = rememberNavController()
NavHost(navController, startDestination = "list") {
composable("list") { PetMainScreen(navController, viewModel = viewModel()) }
composable(
"details/{petId}",
arguments = listOf(navArgument("petId") { type = NavType.StringType })
) {
PetDetailsScreen(
navController,
petId = it.arguments?.getString("petId")!!,
viewModel = viewModel()
)
}
}
}

class PetMainScreenViewModel : ViewModel() {
private val petRepository = PetRepository()
private val petLiveData = MutableLiveData<List<Pet>>()
val petsData: LiveData<List<Pet>>
get() = petLiveData

init {
viewModelScope.launch {
petLiveData.value = petRepository.getListPets()
}
}
}

@Composable
fun PetMainScreen(navController: NavController, viewModel: PetMainScreenViewModel) {
val pets = viewModel.petsData.observeAsState(emptyList())
PetListScreenContent(pets = pets.value) { pet ->
val petId = pet.id
navController.navigate(route = "details/$petId")
}
}

@Composable
fun PetListScreenContent(pets: List<Pet>, onPetSelected: (Pet) -> Unit) {
Surface(color = background, elevation = 4.dp) {
Column {
Row(
modifier = Modifier
.height(80.dp)
.fillMaxWidth()
.padding(end = 16.dp),
horizontalArrangement = Arrangement.SpaceBetween,
verticalAlignment = Alignment.CenterVertically
) {
Text(
text = stringResource(id = R.string.app_name),
style = MaterialTheme.typography.h5,
textAlign = TextAlign.Center,
fontWeight = FontWeight.SemiBold,
modifier = Modifier
.fillMaxHeight()
.padding(
start = 24.dp,
end = 24.dp,
top = 24.dp,
bottom = 8.dp
)
)
}

PetLazyColumn(
modifier = Modifier.padding(
start = 12.dp,
end = 12.dp
),
pets
) { pet ->
onPetSelected(pet)
}
}
}
}

@Composable
fun PetCardListItem(pet: Pet, onPetClick: (Pet) -> Unit) {
Card(
modifier = Modifier
.padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
.clip(RoundedCornerShape(20.dp))
.clickable {
onPetClick(pet)
},
elevation = 8.dp
) {

Row {
Column {
Row {
AgeChip(pet = pet)
GenderIcon(pet = pet)
}
BehaviourChip(pet)
Text(
pet.name,
style = MaterialTheme.typography.h6,
modifier = Modifier
.padding(start = 8.dp, end = 8.dp)
.width(120.dp),
fontWeight = FontWeight.SemiBold
)
Text(
pet.breed,
style = MaterialTheme.typography.body2,
modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 0.dp)
)
Text(
pet.weightKg.toString() + " kg",
style = MaterialTheme.typography.body2,
modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 8.dp)
)
Text(
pet.vaccination.name,
style = MaterialTheme.typography.body2,
modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 8.dp)
)
}
CoilImage(
fadeIn = true,
data = pet.imageUrl,
contentDescription = null,
contentScale = ContentScale.Crop,
modifier = Modifier
.fillMaxWidth()
.height(180.dp)
.clip(
RoundedCornerShape(
topStart = 120.dp,
topEnd = 20.dp,
bottomStart = 0.dp,
bottomEnd = 20.dp
)
)
)
}
}
}

@Composable
fun AgeChip(pet: Pet) {
when (pet.label) {
PetLabel.Puppy -> {
Chip(
"Puppy",
color = purpleButtonLight,
textColor = Purple500,
modifier = Modifier
.padding(start = 8.dp, top = 24.dp)
.width(60.dp)
)
}
PetLabel.Young -> {
Chip(
"Young",
color = orangeButtonLight,
textColor = orangeText,
modifier = Modifier
.padding(start = 8.dp, top = 24.dp)
.width(60.dp)
)
}
PetLabel.Elder -> {
Chip(
"Elder",
color = elderBackground,
textColor = elderTextColor,
modifier = Modifier
.padding(start = 8.dp, top = 24.dp)
.width(60.dp)
)
}
}
}

@Composable
fun GenderIcon(pet: Pet) {
if (pet.gender == Gender.Male) {
Image(
painterResource(R.drawable.ic_launcher_foreground),
"male",
modifier = Modifier
.size(42.dp)
.padding(start = 8.dp, end = 8.dp, top = 30.dp, bottom = 0.dp)
)
} else if (pet.gender == Gender.Female) {
Image(
painterResource(R.drawable.ic_launcher_background),
contentDescription = "female",
modifier = Modifier
.size(42.dp)
.padding(start = 8.dp, end = 8.dp, top = 30.dp, bottom = 0.dp)
)
}
}

@Composable
fun BehaviourChip(pet: Pet) {
when (pet.behavior) {
Behaviour.Loyal -> {
Chip(
"Loyal",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
Behaviour.Angry -> {
Chip(
"Angry",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
Behaviour.Excited -> {
Chip(
"Excited",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
Behaviour.Friendly -> {
Chip(
"Friendly",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
Behaviour.Happy -> {
Chip(
"Happy",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
Behaviour.Lazy -> {
Chip(
"Lazy",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
Behaviour.Stubborn -> {
Chip(
"Stubborn",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
}
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PetLazyColumn(modifier: Modifier, pets: List<Pet>, onPetClick: (Pet) -> Unit) {
val scrollState = rememberLazyListState()
LazyColumn(
modifier = modifier, scrollState,
content = {

items(pets.size) { index ->
PetCardListItem(pet = pets[index], onPetClick = onPetClick)
}
},
)
}

@Composable
fun PetDetailsScreen(navController: NavController, petId: String, viewModel: PetDetailsViewModel) {
LaunchedEffect(petId) {
viewModel.loadPetInfo(petId = petId)
}
Surface(color = MaterialTheme.colors.background) {
val petState = viewModel.petData.observeAsState()
if (petState.value != null) {
val pet = petState.value!!
PetDetails(pet = pet, onBackPress = { navController.popBackStack() })
}
}
}

class PetDetailsViewModel : ViewModel() {

private val petRepository = PetRepository()
private val petLiveData = MutableLiveData<Pet>()
val petData: LiveData<Pet>
get() = petLiveData

fun loadPetInfo(petId: String) {
viewModelScope.launch {
petLiveData.value = petRepository.getPetById(petId)
}
}
}

@Composable
fun PetDetails(pet: Pet, onBackPress: () -> Unit) {
Column(
modifier = Modifier.verticalScroll(rememberScrollState())
) {
CoilImage(
data = pet.imageUrl,
contentDescription = null,
fadeIn = true,
contentScale = ContentScale.Crop,
modifier = Modifier
.height(350.dp)
.clip(
RoundedCornerShape(
topStart = CornerSize(0.dp),
topEnd = CornerSize(0.dp),
bottomEnd = CornerSize(32.dp),
bottomStart = CornerSize(32.dp)
)
)
)
PetCardInformation(pet = pet)
NameBreedSection(pet = pet)
Location(pet = pet)
AboutSection(pet = pet)
}
Icon(
Icons.Filled.ArrowBack, "back",
modifier = Modifier
.size(48.dp)
.clickable {
onBackPress()
}
.padding(12.dp)
)
Column(
modifier = Modifier.fillMaxSize(),
verticalArrangement = Arrangement.Bottom
) {
AdoptButtonBar()
}
}

@Composable
fun NameBreedSection(pet: Pet) {
Row(
modifier = Modifier.fillMaxWidth(),
horizontalArrangement = Arrangement.SpaceBetween
) {
Text(
text = pet.name,
fontWeight = FontWeight.SemiBold,
style = MaterialTheme.typography.h6,
modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 0.dp)
)
Text(
text = pet.breed,
style = MaterialTheme.typography.h6,
modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 0.dp),
textAlign = TextAlign.End
)
}
}

@Composable
fun AboutSection(pet: Pet) {
Text(
text = stringResource(id = R.string.app_name),
style = MaterialTheme.typography.h6,
fontWeight = FontWeight.SemiBold,
modifier = Modifier.padding(start = 16.dp, end = 16.dp)
)
Text(
text = pet.description,
textAlign = TextAlign.Justify,
style = MaterialTheme.typography.body1,
modifier = Modifier.padding(16.dp)
)
Spacer(modifier = Modifier.height(64.dp))
}

@Composable
fun Location(pet: Pet) {
Row(
modifier = Modifier.padding(16.dp),
verticalAlignment = Alignment.CenterVertically
) {
Icon(
Icons.Outlined.Place, null,
modifier = Modifier
.width(16.dp)
.padding(end = 2.dp, top = 2.dp)
)
Text(
text = pet.location,
style = MaterialTheme.typography.body1,
)
}
}

@Composable
fun PetCardInformation(pet: Pet) {
Row(
modifier = Modifier.fillMaxWidth(),
horizontalArrangement = Arrangement.Center
) {
InfoCard(
title = R.drawable.ic_launcher_foreground,
text = pet.age.toString() + " Years"
)
InfoCard(
title = R.drawable.ic_launcher_background,
text = "${pet.weightKg}kg"
)
InfoCard(
title = R.drawable.ic_launcher_foreground,
text = pet.gender.name.capitalize()
)
InfoCard(
title = R.drawable.ic_launcher_foreground,
text = pet.health.capitalize()
)
}
}

@Composable
fun InfoCard(title: Int, text: String) {
Card(
modifier = Modifier
.padding(start = 8.dp, end = 8.dp, top = 8.dp)
.size(80.dp, 64.dp)
.clip(RoundedCornerShape(12.dp)),
elevation = 8.dp,
backgroundColor = behaviourBackground
) {
Column(
verticalArrangement = Arrangement.Center,
horizontalAlignment = Alignment.CenterHorizontally
) {
Icon(
painter = painterResource(id = title), null,
modifier = Modifier
.width(24.dp)
.padding(end = 2.dp, top = 2.dp),

)
Text(
text = text,
modifier = Modifier.fillMaxWidth(),
textAlign = TextAlign.Center,
fontWeight = FontWeight.SemiBold,
style = MaterialTheme.typography.body1
)
}
}
}

@Composable
fun AdoptButtonBar() {
Row(
modifier = Modifier.fillMaxWidth(),
horizontalArrangement = Arrangement.Start
) {
Button(
elevation = null,
modifier = Modifier
.padding(16.dp)
.weight(4f)
.height(52.dp),
onClick = { },
colors = ButtonDefaults.buttonColors(behaviourBackground)
) {
Row(
horizontalArrangement = Arrangement.Center,
verticalAlignment = Alignment.CenterVertically
) {
Image(
painter = painterResource(id=R.drawable.ic_launcher_foreground),
contentDescription = null,
alignment = Alignment.Center,
modifier = Modifier
.size(16.dp)
.padding(top = 2.dp, end = 2.dp)
)
Text(
textAlign = TextAlign.Center,
fontWeight = FontWeight.SemiBold,
color = behaviourTextColor,
text = stringResource(id = R.string.app_name)
)
}
}
Button(
elevation = null,
colors = ButtonDefaults.buttonColors(backgroundColor = behaviourBackground),
modifier = Modifier
.padding(top = 16.dp, bottom = 16.dp, end = 16.dp)
.weight(1f)
.wrapContentWidth()
.height(52.dp),
onClick = {}
) {
Icon(
Icons.Filled.Phone, "phone",
tint = behaviourTextColor,
modifier = Modifier
.size(48.dp)
.padding(4.dp)
)
}
}
}


@Composable
fun Chip(
text: String,
color: Color,
textColor: Color,
modifier: Modifier = Modifier
) {
Box(
modifier = modifier
.height(24.dp)
.clip(
RoundedCornerShape(
CornerSize(12.dp),
CornerSize(12.dp),
CornerSize(12.dp),
CornerSize(12.dp)
)
)
.background(color),
contentAlignment = Alignment.Center

) {
Text(
text = text,
textAlign = TextAlign.Center,
modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 4.dp),
style = MaterialTheme.typography.caption,
color = textColor
)
}
}


class PetRepository {
private val listOfPets = listOf(
dog1, dog2, dog3, dog4, dog5, dog6, dog7,
dog8, dog9, dog10
)

fun getListPets(): List<Pet> {
return listOfPets
}

fun getPetById(id: String): Pet? {
return listOfPets.find { it.id == id }
}
}

val dog1 = Pet(
"1",
"Brodie",
"Brodie is gorgeous, 4 year old female (whelped 4/22/17) German Shepherd in desperate need of a good home with an active lifestyle, experienced owner . She has been living outside but is in great physical condition. Brodie is housebroken, current on vaccinations and was spayed on 1/27/21. She is wary of new people, prefers women over men and needs some socialization and work on her leash manners. Brodie has tested positive for heartworms, but we have already begun her treatment and she is doing great ! We believe once socialized, Brodie will be OK with other dogs, but for now she needs to be the only pet in the home.",
"https://images.unsplash.com/photo-1589941013453-ec89f33b5e95?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1003&q=80",
Gender.Male,
39.4f,
"German Sheppard",
"Iverness, Florida",
4,
PetLabel.Young,
Behaviour.Loyal,
Vaccination.Vaccinated,
"Healthy"
)

val dog2 = Pet(
"2",
"Nelson",
"Nelson is a young sweet Akita that loves people. He may get along with a female dog of his size, with proper introduction. Nelson needs a secure home and yard and some basic obedience training. He is currently boarding in Memphis TN.",
"https://images.unsplash.com/photo-1523171067267-bb96f975c4bb?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1000&q=80",
Gender.Female,
29.3f,
"Akita Inu",
"JacksonVille, Florida",
2,
PetLabel.Young,
Behaviour.Happy,
Vaccination.Unvaccinated,
"Healthy"
)

val dog3 = Pet(
"3",
"Herman",
"Herman has been patiently waiting in a foster home where he cannot get enough playtime with other dogs or annoying the family cat. He foster mom says he's crate and house-trained, a goofy cuddler and a bit vocal when he really wants the treat in your hand. You could never tell Joker was a throw-away since he is constantly wanting to give you kisses.",
"https://images.unsplash.com/photo-1547407139-3c921a66005c?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80",
Gender.Male,
32.2f,
"Husky",
"Colorado Springs, Colorado",
9,
PetLabel.Elder,
Behaviour.Stubborn,
Vaccination.Vaccinated,
"Healthy"
)

val dog4 = Pet(
"4",
"Daisy",
"Daisy is a bright, sensitive dog who enjoys play with his human family and responds well to training. As herders bred to move cattle, they are fearless and independent. They are vigilant watchdogs, with acute senses and a “big dog” bark. Families who can meet their bold but kindly Pembroke’s need for activity and togetherness will never have a more loyal, loving pet.",
"https://images.unsplash.com/photo-1611170874942-4f4bed56a14a?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1600&q=80",
Gender.Female,
9.8f,
"Golden Retriever",
"Whitewright, Texas",
1,
PetLabel.Puppy,
Behaviour.Excited,
Vaccination.Vaccinated,
"Healthy"
)

val dog5 = Pet(
"5",
"Micy",
"Micy is a bright, sensitive dog who enjoys play with his human family and responds well to training. As herders bred to move cattle, they are fearless and independent. They are vigilant watchdogs, with acute senses and a “big dog” bark. Families who can meet their bold but kindly Pembroke’s need for activity and togetherness will never have a more loyal, loving pet.",
"https://images.unsplash.com/photo-1587790311640-50b019663f01?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1100&q=80",
Gender.Female,
19.5f,
"Pitbull",
"Starkville, Mississippi",
12,
PetLabel.Elder,
Behaviour.Angry,
Vaccination.Vaccinated,
"Disabled"
)
val dog6 = Pet(
"6",
"Skippy",
"Skippy came to Mutt Love as a stray from NC. Skippy is about three years old and weighs 12kg. Skippy is heartworm positive and will need to undergo heartworm treatment. He would benefit from a foster home while recouping from the treatment.",
"https://images.unsplash.com/photo-1543466835-00a7907e9de1?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1567&q=80",
Gender.Female,
12.3f,
"Beagle",
"Fairfax, Virginia",
2,
PetLabel.Young,
Behaviour.Friendly,
Vaccination.Unvaccinated,
"Diarrhoea"
)

val dog7 = Pet(
"7",
"Yuki",
"Yuki is a 3 year old Samoyed breed, who just wants all the attention and affection he can get! He is dog friendly but NOT cat friendly (unsure of how he is with smaller dogs). He is very vocal and treat motivated and will let you know when he doesn't like something. He loves to run around outside and go for walks but mostly wants to stay right next to his humans. Yuki is an Alpha, but plays well with others.",
"https://images.unsplash.com/photo-1529429617124-95b109e86bb8?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=975&q=80",
Gender.Male,
22.4f,
"Samoyed",
"Quinlan, Texas",
3,
PetLabel.Young,
Behaviour.Friendly,
Vaccination.Unvaccinated,
"Heartworm"
)

val dog8 = Pet(
"8",
"Lilly",
"Lilly has been patiently waiting in a foster home where she cannot get enough playtime with other dogs or annoying (and taking naps next to) the family cat. Her foster mom says she's crate and house-trained, a goofy cuddler and a bit vocal when she really wants the treat in your hand. You could never tell Lilly was a throw-away since she is constantly wanting to give you kisses.",
"https://images.unsplash.com/photo-1478991031579-5f22c0ee9c9f?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1650&q=80",
Gender.Female,
4.7f,
"Yorkshire Terrier",
"Sharon, Connecticut",
1,
PetLabel.Puppy,
Behaviour.Stubborn,
Vaccination.Unvaccinated,
"Healthy"
)

val dog9 = Pet(
"9",
"Joker",
"Joker has been patiently waiting in a foster home where he cannot get enough playtime with other dogs or annoying the family cat. He foster mom says he's crate and house-trained, a goofy cuddler and a bit vocal when he really wants the treat in your hand. You could never tell Joker was a throw-away since he is constantly wanting to give you kisses.",
"https://images.unsplash.com/photo-1516131537578-9ca049cc6b59?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1650&q=80",
Gender.Male,
35.8f,
"Alaskan Malamute",
"Norco, California",
10,
PetLabel.Elder,
Behaviour.Stubborn,
Vaccination.Vaccinated,
"Arthritis"
)

val dog10 = Pet(
"10",
"Libby",
"Libby has been patiently waiting in a foster home where she cannot get enough playtime with other dogs or annoying (and taking naps next to) the family cat. Her foster mom says she's crate and house-trained, a goofy cuddler and a bit vocal when she really wants the treat in your hand. You could never tell Libby was a throw-away since she is constantly wanting to give you kisses.",
"https://images.unsplash.com/photo-1599765625577-61a6e65e3567?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1077&q=80",
Gender.Female,
8.3f,
"Dalmatian",
"Houston, Texas",
1,
PetLabel.Puppy,
Behaviour.Excited,
Vaccination.Vaccinated,
"Healthy"
)


data class Pet(
val id: String,
val name: String,
val description: String,
val imageUrl: String,
val gender: Gender,
val weightKg: Float,
val breed: String,
val location: String,
val age: Int,
val label: PetLabel,
val behavior: Behaviour,
val vaccination: Vaccination,
val health: String

)

enum class PetLabel {
Puppy,
Young,
Elder
}

enum class Behaviour {
Friendly,
Stubborn,
Angry,
Happy,
Excited,
Lazy,
Loyal
}

enum class Gender {
Male,
Female,
NotSpecified
}

enum class Vaccination {
Vaccinated,
Unvaccinated
}


 */
/**
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.example.exampleeee.ui.theme.*
import dev.chrisbanes.accompanist.coil.CoilImage

class MainActivity : ComponentActivity() {
override fun onCreate(savedInstanceState: Bundle?) {
super.onCreate(savedInstanceState)
setContent {
ExampleeeeTheme {
ComposeAdoptionApp()
}
}
}
}






@Composable
fun ComposeAdoptionApp() {
val navController = rememberNavController()
NavHost(navController, startDestination = "list") {
composable("list") { PetMainScreen(navController, viewModel = viewModel()) }
composable(
"details/{petId}",
arguments = listOf(navArgument("petId") { type = NavType.StringType })
) {
PetDetailsScreen(
navController,
petId = it.arguments?.getString("petId")!!,
viewModel = viewModel()
)
}
}
}

class PetMainScreenViewModel : ViewModel() {
private val petRepository = PetRepository()
private val petLiveData = MutableLiveData<List<Pet>>()
val petsData: LiveData<List<Pet>>
get() = petLiveData

init {
viewModelScope.launch {
petLiveData.value = petRepository.getListPets()
}
}
}

@Composable
fun PetMainScreen(navController: NavController, viewModel: PetMainScreenViewModel) {
val pets = viewModel.petsData.observeAsState(emptyList())
PetListScreenContent(pets = pets.value) { pet ->
val petId = pet.id
navController.navigate(route = "details/$petId")
}
}

@Composable
fun PetListScreenContent(pets: List<Pet>, onPetSelected: (Pet) -> Unit) {
Surface(color = background, elevation = 4.dp) {
Column {
Row(
modifier = Modifier
.height(80.dp)
.fillMaxWidth()
.padding(end = 16.dp),
horizontalArrangement = Arrangement.SpaceBetween,
verticalAlignment = Alignment.CenterVertically
) {
Text(
text = stringResource(id = R.string.app_name),
style = MaterialTheme.typography.h5,
textAlign = TextAlign.Center,
fontWeight = FontWeight.SemiBold,
modifier = Modifier
.fillMaxHeight()
.padding(
start = 24.dp,
end = 24.dp,
top = 24.dp,
bottom = 8.dp
)
)
}

PetLazyColumn(
modifier = Modifier.padding(
start = 12.dp,
end = 12.dp
),
pets
) { pet ->
onPetSelected(pet)
}
}
}
}

@Composable
fun PetCardListItem(pet: Pet, onPetClick: (Pet) -> Unit) {
Card(
modifier = Modifier
.padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
.clip(RoundedCornerShape(20.dp))
.clickable {
onPetClick(pet)
},
elevation = 8.dp
) {

Row {
Column {
Row {
AgeChip(pet = pet)
GenderIcon(pet = pet)
}
BehaviourChip(pet)
Text(
pet.name,
style = MaterialTheme.typography.h6,
modifier = Modifier
.padding(start = 8.dp, end = 8.dp)
.width(120.dp),
fontWeight = FontWeight.SemiBold
)
Text(
pet.breed,
style = MaterialTheme.typography.body2,
modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 0.dp)
)
Text(
pet.weightKg.toString() + " kg",
style = MaterialTheme.typography.body2,
modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 8.dp)
)
Text(
pet.vaccination.name,
style = MaterialTheme.typography.body2,
modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 8.dp)
)
}
CoilImage(
fadeIn = true,
data = pet.imageUrl,
contentDescription = null,
contentScale = ContentScale.Crop,
modifier = Modifier
.fillMaxWidth()
.height(180.dp)
.clip(
RoundedCornerShape(
topStart = 120.dp,
topEnd = 20.dp,
bottomStart = 0.dp,
bottomEnd = 20.dp
)
)
)
}
}
}

@Composable
fun AgeChip(pet: Pet) {
when (pet.label) {
PetLabel.Puppy -> {
Chip(
"Puppy",
color = purpleButtonLight,
textColor = Purple500,
modifier = Modifier
.padding(start = 8.dp, top = 24.dp)
.width(60.dp)
)
}
PetLabel.Young -> {
Chip(
"Young",
color = orangeButtonLight,
textColor = orangeText,
modifier = Modifier
.padding(start = 8.dp, top = 24.dp)
.width(60.dp)
)
}
PetLabel.Elder -> {
Chip(
"Elder",
color = elderBackground,
textColor = elderTextColor,
modifier = Modifier
.padding(start = 8.dp, top = 24.dp)
.width(60.dp)
)
}
}
}

@Composable
fun GenderIcon(pet: Pet) {
if (pet.gender == Gender.Male) {
Image(
painterResource(R.drawable.ic_launcher_foreground),
"male",
modifier = Modifier
.size(42.dp)
.padding(start = 8.dp, end = 8.dp, top = 30.dp, bottom = 0.dp)
)
} else if (pet.gender == Gender.Female) {
Image(
painterResource(R.drawable.ic_launcher_background),
contentDescription = "female",
modifier = Modifier
.size(42.dp)
.padding(start = 8.dp, end = 8.dp, top = 30.dp, bottom = 0.dp)
)
}
}

@Composable
fun BehaviourChip(pet: Pet) {
when (pet.behavior) {
Behaviour.Loyal -> {
Chip(
"Loyal",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
Behaviour.Angry -> {
Chip(
"Angry",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
Behaviour.Excited -> {
Chip(
"Excited",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
Behaviour.Friendly -> {
Chip(
"Friendly",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
Behaviour.Happy -> {
Chip(
"Happy",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
Behaviour.Lazy -> {
Chip(
"Lazy",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
Behaviour.Stubborn -> {
Chip(
"Stubborn",
color = behaviourBackground,
textColor = behaviourTextColor,
modifier = Modifier.padding(start = 8.dp, top = 4.dp)
)
}
}
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PetLazyColumn(modifier: Modifier, pets: List<Pet>, onPetClick: (Pet) -> Unit) {
val scrollState = rememberLazyListState()
LazyColumn(
modifier = modifier, scrollState,
content = {

items(pets.size) { index ->
PetCardListItem(pet = pets[index], onPetClick = onPetClick)
}
},
)
}

@Composable
fun PetDetailsScreen(navController: NavController, petId: String, viewModel: PetDetailsViewModel) {
LaunchedEffect(petId) {
viewModel.loadPetInfo(petId = petId)
}
Surface(color = MaterialTheme.colors.background) {
val petState = viewModel.petData.observeAsState()
if (petState.value != null) {
val pet = petState.value!!
PetDetails(pet = pet, onBackPress = { navController.popBackStack() })
}
}
}

class PetDetailsViewModel : ViewModel() {

private val petRepository = PetRepository()
private val petLiveData = MutableLiveData<Pet>()
val petData: LiveData<Pet>
get() = petLiveData

fun loadPetInfo(petId: String) {
viewModelScope.launch {
petLiveData.value = petRepository.getPetById(petId)
}
}
}

@Composable
fun PetDetails(pet: Pet, onBackPress: () -> Unit) {
Column(
modifier = Modifier.verticalScroll(rememberScrollState())
) {
CoilImage(
data = pet.imageUrl,
contentDescription = null,
fadeIn = true,
contentScale = ContentScale.Crop,
modifier = Modifier
.height(350.dp)
.clip(
RoundedCornerShape(
topStart = CornerSize(0.dp),
topEnd = CornerSize(0.dp),
bottomEnd = CornerSize(32.dp),
bottomStart = CornerSize(32.dp)
)
)
)
PetCardInformation(pet = pet)
NameBreedSection(pet = pet)
Location(pet = pet)
AboutSection(pet = pet)
}
Icon(
Icons.Filled.ArrowBack, "back",
modifier = Modifier
.size(48.dp)
.clickable {
onBackPress()
}
.padding(12.dp)
)
Column(
modifier = Modifier.fillMaxSize(),
verticalArrangement = Arrangement.Bottom
) {
AdoptButtonBar()
}
}

@Composable
fun NameBreedSection(pet: Pet) {
Row(
modifier = Modifier.fillMaxWidth(),
horizontalArrangement = Arrangement.SpaceBetween
) {
Text(
text = pet.name,
fontWeight = FontWeight.SemiBold,
style = MaterialTheme.typography.h6,
modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 0.dp)
)
Text(
text = pet.breed,
style = MaterialTheme.typography.h6,
modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 0.dp),
textAlign = TextAlign.End
)
}
}

@Composable
fun AboutSection(pet: Pet) {
Text(
text = stringResource(id = R.string.app_name),
style = MaterialTheme.typography.h6,
fontWeight = FontWeight.SemiBold,
modifier = Modifier.padding(start = 16.dp, end = 16.dp)
)
Text(
text = pet.description,
textAlign = TextAlign.Justify,
style = MaterialTheme.typography.body1,
modifier = Modifier.padding(16.dp)
)
Spacer(modifier = Modifier.height(64.dp))
}

@Composable
fun Location(pet: Pet) {
Row(
modifier = Modifier.padding(16.dp),
verticalAlignment = Alignment.CenterVertically
) {
Icon(
Icons.Outlined.Place, null,
modifier = Modifier
.width(16.dp)
.padding(end = 2.dp, top = 2.dp)
)
Text(
text = pet.location,
style = MaterialTheme.typography.body1,
)
}
}

@Composable
fun PetCardInformation(pet: Pet) {
Row(
modifier = Modifier.fillMaxWidth(),
horizontalArrangement = Arrangement.Center
) {
InfoCard(
title = R.drawable.ic_launcher_foreground,
text = pet.age.toString() + " Years"
)
InfoCard(
title = R.drawable.ic_launcher_background,
text = "${pet.weightKg}kg"
)
InfoCard(
title = R.drawable.ic_launcher_foreground,
text = pet.gender.name.capitalize()
)
InfoCard(
title = R.drawable.ic_launcher_foreground,
text = pet.health.capitalize()
)
}
}

@Composable
fun InfoCard(title: Int, text: String) {
Card(
modifier = Modifier
.padding(start = 8.dp, end = 8.dp, top = 8.dp)
.size(80.dp, 64.dp)
.clip(RoundedCornerShape(12.dp)),
elevation = 8.dp,
backgroundColor = behaviourBackground
) {
Column(
verticalArrangement = Arrangement.Center,
horizontalAlignment = Alignment.CenterHorizontally
) {
Icon(
painter = painterResource(id = title), null,
modifier = Modifier
.width(24.dp)
.padding(end = 2.dp, top = 2.dp),

)
Text(
text = text,
modifier = Modifier.fillMaxWidth(),
textAlign = TextAlign.Center,
fontWeight = FontWeight.SemiBold,
style = MaterialTheme.typography.body1
)
}
}
}

@Composable
fun AdoptButtonBar() {
Row(
modifier = Modifier.fillMaxWidth(),
horizontalArrangement = Arrangement.Start
) {
Button(
elevation = null,
modifier = Modifier
.padding(16.dp)
.weight(4f)
.height(52.dp),
onClick = { },
colors = ButtonDefaults.buttonColors(behaviourBackground)
) {
Row(
horizontalArrangement = Arrangement.Center,
verticalAlignment = Alignment.CenterVertically
) {
Image(
painter = painterResource(id=R.drawable.ic_launcher_foreground),
contentDescription = null,
alignment = Alignment.Center,
modifier = Modifier
.size(16.dp)
.padding(top = 2.dp, end = 2.dp)
)
Text(
textAlign = TextAlign.Center,
fontWeight = FontWeight.SemiBold,
color = behaviourTextColor,
text = stringResource(id = R.string.app_name)
)
}
}
Button(
elevation = null,
colors = ButtonDefaults.buttonColors(backgroundColor = behaviourBackground),
modifier = Modifier
.padding(top = 16.dp, bottom = 16.dp, end = 16.dp)
.weight(1f)
.wrapContentWidth()
.height(52.dp),
onClick = {}
) {
Icon(
Icons.Filled.Phone, "phone",
tint = behaviourTextColor,
modifier = Modifier
.size(48.dp)
.padding(4.dp)
)
}
}
}


@Composable
fun Chip(
text: String,
color: Color,
textColor: Color,
modifier: Modifier = Modifier
) {
Box(
modifier = modifier
.height(24.dp)
.clip(
RoundedCornerShape(
CornerSize(12.dp),
CornerSize(12.dp),
CornerSize(12.dp),
CornerSize(12.dp)
)
)
.background(color),
contentAlignment = Alignment.Center

) {
Text(
text = text,
textAlign = TextAlign.Center,
modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 4.dp),
style = MaterialTheme.typography.caption,
color = textColor
)
}
}


class PetRepository {
private val listOfPets = listOf(
dog1, dog2, dog3, dog4, dog5, dog6, dog7,
dog8, dog9, dog10
)

fun getListPets(): List<Pet> {
return listOfPets
}

fun getPetById(id: String): Pet? {
return listOfPets.find { it.id == id }
}
}

val dog1 = Pet(
"1",
"Brodie",
"Brodie is gorgeous, 4 year old female (whelped 4/22/17) German Shepherd in desperate need of a good home with an active lifestyle, experienced owner . She has been living outside but is in great physical condition. Brodie is housebroken, current on vaccinations and was spayed on 1/27/21. She is wary of new people, prefers women over men and needs some socialization and work on her leash manners. Brodie has tested positive for heartworms, but we have already begun her treatment and she is doing great ! We believe once socialized, Brodie will be OK with other dogs, but for now she needs to be the only pet in the home.",
"https://images.unsplash.com/photo-1589941013453-ec89f33b5e95?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1003&q=80",
Gender.Male,
39.4f,
"German Sheppard",
"Iverness, Florida",
4,
PetLabel.Young,
Behaviour.Loyal,
Vaccination.Vaccinated,
"Healthy"
)

val dog2 = Pet(
"2",
"Nelson",
"Nelson is a young sweet Akita that loves people. He may get along with a female dog of his size, with proper introduction. Nelson needs a secure home and yard and some basic obedience training. He is currently boarding in Memphis TN.",
"https://images.unsplash.com/photo-1523171067267-bb96f975c4bb?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1000&q=80",
Gender.Female,
29.3f,
"Akita Inu",
"JacksonVille, Florida",
2,
PetLabel.Young,
Behaviour.Happy,
Vaccination.Unvaccinated,
"Healthy"
)

val dog3 = Pet(
"3",
"Herman",
"Herman has been patiently waiting in a foster home where he cannot get enough playtime with other dogs or annoying the family cat. He foster mom says he's crate and house-trained, a goofy cuddler and a bit vocal when he really wants the treat in your hand. You could never tell Joker was a throw-away since he is constantly wanting to give you kisses.",
"https://images.unsplash.com/photo-1547407139-3c921a66005c?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80",
Gender.Male,
32.2f,
"Husky",
"Colorado Springs, Colorado",
9,
PetLabel.Elder,
Behaviour.Stubborn,
Vaccination.Vaccinated,
"Healthy"
)

val dog4 = Pet(
"4",
"Daisy",
"Daisy is a bright, sensitive dog who enjoys play with his human family and responds well to training. As herders bred to move cattle, they are fearless and independent. They are vigilant watchdogs, with acute senses and a “big dog” bark. Families who can meet their bold but kindly Pembroke’s need for activity and togetherness will never have a more loyal, loving pet.",
"https://images.unsplash.com/photo-1611170874942-4f4bed56a14a?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1600&q=80",
Gender.Female,
9.8f,
"Golden Retriever",
"Whitewright, Texas",
1,
PetLabel.Puppy,
Behaviour.Excited,
Vaccination.Vaccinated,
"Healthy"
)

val dog5 = Pet(
"5",
"Micy",
"Micy is a bright, sensitive dog who enjoys play with his human family and responds well to training. As herders bred to move cattle, they are fearless and independent. They are vigilant watchdogs, with acute senses and a “big dog” bark. Families who can meet their bold but kindly Pembroke’s need for activity and togetherness will never have a more loyal, loving pet.",
"https://images.unsplash.com/photo-1587790311640-50b019663f01?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1100&q=80",
Gender.Female,
19.5f,
"Pitbull",
"Starkville, Mississippi",
12,
PetLabel.Elder,
Behaviour.Angry,
Vaccination.Vaccinated,
"Disabled"
)
val dog6 = Pet(
"6",
"Skippy",
"Skippy came to Mutt Love as a stray from NC. Skippy is about three years old and weighs 12kg. Skippy is heartworm positive and will need to undergo heartworm treatment. He would benefit from a foster home while recouping from the treatment.",
"https://images.unsplash.com/photo-1543466835-00a7907e9de1?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1567&q=80",
Gender.Female,
12.3f,
"Beagle",
"Fairfax, Virginia",
2,
PetLabel.Young,
Behaviour.Friendly,
Vaccination.Unvaccinated,
"Diarrhoea"
)

val dog7 = Pet(
"7",
"Yuki",
"Yuki is a 3 year old Samoyed breed, who just wants all the attention and affection he can get! He is dog friendly but NOT cat friendly (unsure of how he is with smaller dogs). He is very vocal and treat motivated and will let you know when he doesn't like something. He loves to run around outside and go for walks but mostly wants to stay right next to his humans. Yuki is an Alpha, but plays well with others.",
"https://images.unsplash.com/photo-1529429617124-95b109e86bb8?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=975&q=80",
Gender.Male,
22.4f,
"Samoyed",
"Quinlan, Texas",
3,
PetLabel.Young,
Behaviour.Friendly,
Vaccination.Unvaccinated,
"Heartworm"
)

val dog8 = Pet(
"8",
"Lilly",
"Lilly has been patiently waiting in a foster home where she cannot get enough playtime with other dogs or annoying (and taking naps next to) the family cat. Her foster mom says she's crate and house-trained, a goofy cuddler and a bit vocal when she really wants the treat in your hand. You could never tell Lilly was a throw-away since she is constantly wanting to give you kisses.",
"https://images.unsplash.com/photo-1478991031579-5f22c0ee9c9f?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1650&q=80",
Gender.Female,
4.7f,
"Yorkshire Terrier",
"Sharon, Connecticut",
1,
PetLabel.Puppy,
Behaviour.Stubborn,
Vaccination.Unvaccinated,
"Healthy"
)

val dog9 = Pet(
"9",
"Joker",
"Joker has been patiently waiting in a foster home where he cannot get enough playtime with other dogs or annoying the family cat. He foster mom says he's crate and house-trained, a goofy cuddler and a bit vocal when he really wants the treat in your hand. You could never tell Joker was a throw-away since he is constantly wanting to give you kisses.",
"https://images.unsplash.com/photo-1516131537578-9ca049cc6b59?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1650&q=80",
Gender.Male,
35.8f,
"Alaskan Malamute",
"Norco, California",
10,
PetLabel.Elder,
Behaviour.Stubborn,
Vaccination.Vaccinated,
"Arthritis"
)

val dog10 = Pet(
"10",
"Libby",
"Libby has been patiently waiting in a foster home where she cannot get enough playtime with other dogs or annoying (and taking naps next to) the family cat. Her foster mom says she's crate and house-trained, a goofy cuddler and a bit vocal when she really wants the treat in your hand. You could never tell Libby was a throw-away since she is constantly wanting to give you kisses.",
"https://images.unsplash.com/photo-1599765625577-61a6e65e3567?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1077&q=80",
Gender.Female,
8.3f,
"Dalmatian",
"Houston, Texas",
1,
PetLabel.Puppy,
Behaviour.Excited,
Vaccination.Vaccinated,
"Healthy"
)


data class Pet(
val id: String,
val name: String,
val description: String,
val imageUrl: String,
val gender: Gender,
val weightKg: Float,
val breed: String,
val location: String,
val age: Int,
val label: PetLabel,
val behavior: Behaviour,
val vaccination: Vaccination,
val health: String

)

enum class PetLabel {
Puppy,
Young,
Elder
}

enum class Behaviour {
Friendly,
Stubborn,
Angry,
Happy,
Excited,
Lazy,
Loyal
}

enum class Gender {
Male,
Female,
NotSpecified
}

enum class Vaccination {
Vaccinated,
Unvaccinated
}


 */