/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.trainrunner.presentation.ui

//class HomePage : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        installSplashScreen()
//        super.onCreate(savedInstanceState)
//        setTheme(android.R.style.Theme_DeviceDefault)
//
//        setContent {
//            TrainRunnerTheme {
//                val navController = rememberSwipeDismissableNavController()
//
//                // NavHost contains all of the screens of the app
//                NavHost(
//                    navController = navController,
//                    startDestination = Route.homeScreen
//                ){
//                    composable(route = Route.homeScreen){
//                        HomeScreen(
//                            greetingName = "Player 1",
//                            navigateToSettingsScreen = {
//                                navController.navigate(Route.settingsScreen)
//                            }
//                        )
//                    }
//                    composable(route = Route.settingsScreen){
//                        SettingsScreen(
//                            navigateToEditScreen = {
//                               navController.navigate(Route.editScreen)
//                            },
//                            navigateBack = {
//                                navController.popBackStack()
//                            }
//                        )
//                    }
//                    composable(route = Route.editScreen){
//                        EditScreen(
//                            navigateToHomeScreen = {
//                                navController.popBackStack(
//                                    route = Route.homeScreen,
//                                    // if set to true, would remove home screen from backstack and would be empty screen
//                                    inclusive = false
//                                )
//                            },
//                            navigateBack = {
//                                navController.popBackStack()
//                            }
//                        )
//                    }
//                }
//
//            }
//        }
//    }
//}
//
//// good example of using resources
//@Composable
//fun Greeting(greetingName: String, count: Int) {
//    Text(
//        modifier = Modifier.fillMaxWidth(),
//        textAlign = TextAlign.Center,
//        color = MaterialTheme.colors.primary,
//        text = stringResource(R.string.hello_world, greetingName, count)
//    )
//}
//
//object Route {
//    const val homeScreen = "homeScreen"
//    const val settingsScreen = "settingsScreen"
//    const val editScreen = "editScreen"
//}
//
////@Preview(device = "id:wearos_small_round")
//@Composable
//fun HomeScreen(
//    greetingName: String="test",
//    navigateToSettingsScreen: () -> Unit
//) {
//    var number = remember { mutableStateOf(0)}
//
//    TrainRunnerTheme {
//        Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(MaterialTheme.colors.background),
//                contentAlignment = Alignment.Center
//        ) {
//            TimeText()
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//
//                Greeting(greetingName = greetingName, count = number.value)
//
//                Button(
//                    onClick = {
//                        number.value++
//                    },
//                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
//                    modifier = Modifier
//                        .width(width=160.dp)
//                ) {
//                    Text(text = "Increment")
//                }
//
//                Button(
//                    onClick = {
//                        number.value = 0
//                    },
//                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
//                    modifier = Modifier
//                        .width(width=160.dp)
//                ) {
//                    Text(text = "Reset")
//                }
//
//                Button(
//                    onClick = {
//                        navigateToSettingsScreen()
//                    },
//                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
//                ) {
//                    Text(text = "Settings")
//                }
//
//
//
//            }
//        }
//    }
//}

//@Composable
//fun SettingsScreen(
//    navigateToEditScreen: () -> Unit,
//    navigateBack: () -> Unit
//){
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ){
//        Text(text = "Settings")
//        Button(onClick = { navigateToEditScreen() }) {
//            Text(text = "Edit Screen")
//        }
//        Button(onClick = { navigateBack() }) {
//            Text(text = "Go back")
//        }
//    }
//}
//
//@Composable
//fun EditScreen(
//    navigateToHomeScreen: () -> Unit,
//    navigateBack: () -> Unit
//){
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ){
//        Text(text = "Edit")
//        Button(onClick = { navigateBack() }) {
//            Text(text = "Go back")
//        }
//        Button(onClick = { navigateToHomeScreen() }) {
//            Text(text = "Back to Home Screen")
//        }
//    }
//}