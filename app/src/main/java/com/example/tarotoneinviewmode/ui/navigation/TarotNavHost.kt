package com.example.tarotoneinviewmode.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tarotoneinviewmode.OraculoApp
import com.example.tarotoneinviewmode.ui.archive.ArchiveScreen
import com.example.tarotoneinviewmode.ui.archive.ArchiveTab
import com.example.tarotoneinviewmode.ui.archive.ArchiveViewModel
import com.example.tarotoneinviewmode.ui.detail.CardDetailScreen
import com.example.tarotoneinviewmode.ui.detail.CardDetailViewModel
import com.example.tarotoneinviewmode.ui.draw.DrawMode
import com.example.tarotoneinviewmode.ui.draw.DrawScreen
import com.example.tarotoneinviewmode.ui.draw.DrawViewModel
import com.example.tarotoneinviewmode.ui.draw.SpreadSelectionScreen
import com.example.tarotoneinviewmode.ui.history.ReadingHistoryScreen
import com.example.tarotoneinviewmode.ui.history.ReadingHistoryViewModel
import com.example.tarotoneinviewmode.ui.home.HomeScreen
import com.example.tarotoneinviewmode.ui.home.HomeViewModel
import com.example.tarotoneinviewmode.ui.iching.IChingArchiveScreen
import com.example.tarotoneinviewmode.ui.iching.IChingArchiveViewModel
import com.example.tarotoneinviewmode.ui.iching.IChingDrawScreen
import com.example.tarotoneinviewmode.ui.iching.IChingDrawViewModel
import com.example.tarotoneinviewmode.ui.iching.HexagramDetailScreen
import com.example.tarotoneinviewmode.ui.iching.HexagramDetailViewModel
import com.example.tarotoneinviewmode.ui.guide.GuideScreen
import com.example.tarotoneinviewmode.ui.onboarding.OnboardingScreen
import com.example.tarotoneinviewmode.ui.onboarding.OnboardingViewModel
import com.example.tarotoneinviewmode.ui.main.RootViewModel
import com.example.tarotoneinviewmode.ui.sadhana.SadhanaScreen
import com.example.tarotoneinviewmode.ui.sadhana.SadhanaViewModel
import com.example.tarotoneinviewmode.ui.settings.SettingsScreen
import com.example.tarotoneinviewmode.ui.settings.SettingsViewModel
import androidx.compose.material3.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import com.example.tarotoneinviewmode.R

@Composable
fun TarotNavHost(
    navController: NavHostController,
    onTabChanged: (ArchiveTab) -> Unit = {}
) {
    val rootViewModel: RootViewModel = hiltViewModel()
    val onboardingCompleted by rootViewModel.onboardingCompleted.collectAsStateWithLifecycle()
    val privacyNoticeSeen by rootViewModel.privacyNoticeSeen.collectAsStateWithLifecycle()

    if (onboardingCompleted == null || privacyNoticeSeen == null) return // Aguarda carregar o estado

    val context = LocalContext.current
    val privacyUrl = stringResource(id = R.string.privacy_policy_url)

    if (privacyNoticeSeen == false) {
        AlertDialog(
            onDismissRequest = { rootViewModel.dismissPrivacyNotice(context) },
            title = {
                Text(
                    text = stringResource(id = R.string.dialog_privacy_notice_title),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            text = {
                Column {
                    Text(
                        text = stringResource(id = R.string.dialog_privacy_notice_desc),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    TextButton(
                        onClick = {
                            val intent = android.content.Intent(
                                android.content.Intent.ACTION_VIEW,
                                android.net.Uri.parse(privacyUrl)
                            )
                            context.startActivity(intent)
                        },
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.action_privacy_policy) + " →",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = { rootViewModel.dismissPrivacyNotice(context) }
                ) {
                    Text(
                        text = stringResource(id = R.string.action_got_it),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        rootViewModel.dismissPrivacyNotice(context)
                        navController.navigate(NavRoute.Settings.route)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.action_adjust_settings),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        )
    }

    NavHost(
        navController = navController,
        startDestination = if (onboardingCompleted == true) NavRoute.Home.route else NavRoute.Onboarding.route,
        enterTransition = { fadeIn(animationSpec = tween(150)) },
        exitTransition = { fadeOut(animationSpec = tween(150)) }
    ) {
        composable(NavRoute.Onboarding.route) {
            val onboardingViewModel: OnboardingViewModel = hiltViewModel()
            OnboardingScreen(
                viewModel = onboardingViewModel,
                onNavigateToHome = {
                    navController.navigate(NavRoute.Home.route) {
                        popUpTo(NavRoute.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoute.Archive.route) {
            val archiveViewModel: ArchiveViewModel = hiltViewModel()

            // observa mudança de tab e notifica o scaffold
            val uiState by archiveViewModel.uiState.collectAsState()
            LaunchedEffect(uiState.selectedTab) {
                onTabChanged(uiState.selectedTab)
            }

            ArchiveScreen(
                viewModel = archiveViewModel,
                onCardClick = { cardId ->
                    navController.navigate(NavRoute.CardDetail.createRoute(cardId))
                }
            )
        }

        composable(
            route = NavRoute.CardDetail.route,
            arguments = listOf(navArgument("cardId") { type = NavType.StringType })
        ) { _ ->
            val cardDetailViewModel: CardDetailViewModel = hiltViewModel()
            CardDetailScreen(
                viewModel = cardDetailViewModel,
                onBack = { navController.popBackStack() },
                onShowGuide = { topic ->
                    navController.navigate(NavRoute.Guide.createRoute(topic))
                }
            )
        }

        composable(
            route = NavRoute.Draw.route,
            arguments = listOf(navArgument("mode") { type = NavType.StringType })
        ) { _ ->
            val drawViewModel: DrawViewModel = hiltViewModel()
            DrawScreen(
                viewModel = drawViewModel,
                onBack = { navController.popBackStack() },
                onShowGuide = { topic ->
                    navController.navigate(NavRoute.Guide.createRoute(topic))
                }
            )
        }

        composable(NavRoute.History.route) {
            val historyViewModel: ReadingHistoryViewModel = hiltViewModel()
            ReadingHistoryScreen(
                viewModel = historyViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(NavRoute.SpreadSelection.route) {
            SpreadSelectionScreen(
                onBack = { navController.popBackStack() },
                onNavigateToDraw = { mode ->
                    navController.navigate(NavRoute.Draw.createRoute(mode))
                },
                onNavigateToIChing = { mode ->
                    navController.navigate(NavRoute.IChing.createRoute(mode))
                }
            )
        }

        composable(NavRoute.Settings.route) {
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            SettingsScreen(
                viewModel = settingsViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(NavRoute.Home.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = homeViewModel,
                onDrawDayCard = {
                    navController.navigate(NavRoute.Draw.createRoute(NavRoute.Draw.DAY_CARD))
                },
                onVerCartaDoDia = {
                    navController.navigate(NavRoute.Draw.createRoute(NavRoute.Draw.DAY_CARD))
                },
                onVerIChing = { mode ->
                    navController.navigate(NavRoute.IChing.createRoute(mode))
                },
                onVerHexagramaDoDia = {
                    navController.navigate(NavRoute.IChing.createRoute(NavRoute.IChing.MODE_DAILY))
                },
                onVerHistorico = {
                    navController.navigate(NavRoute.History.route)
                },
                onPPF = {
                    navController.navigate(NavRoute.Draw.createRoute(NavRoute.Draw.PPF))
                },
                onSimNao = {
                    navController.navigate(NavRoute.Draw.createRoute(NavRoute.Draw.SIM_NAO))
                },
                onDesafio = {
                    navController.navigate(NavRoute.Draw.createRoute(NavRoute.Draw.DESAFIO))
                },
                onRelacionamento = {
                    navController.navigate(NavRoute.Draw.createRoute(NavRoute.Draw.RELATION))
                }
            )
        }

        composable(
            route = NavRoute.IChing.route,
            arguments = listOf(navArgument("mode") { type = NavType.StringType })
        ) {
            val ichingDrawViewModel: IChingDrawViewModel = hiltViewModel()

            IChingDrawScreen(
                viewModel = ichingDrawViewModel,
                onBack = { navController.popBackStack() },
                onShowGuide = { topic ->
                    navController.navigate(NavRoute.Guide.createRoute(topic))
                }
            )
        }

        composable(NavRoute.IChingArchive.route) {
            val ichingArchiveViewModel: IChingArchiveViewModel = hiltViewModel()
            val uiState by ichingArchiveViewModel.uiState.collectAsState()

            IChingArchiveScreen(
                hexagrams = uiState.hexagrams,
                selectedTrigram = uiState.selectedTrigram,
                showOnlyFavorites = uiState.showOnlyFavorites,
                onToggleFavorites = { ichingArchiveViewModel.toggleOnlyFavorites() },
                onTrigramSelected = { trigram ->
                    ichingArchiveViewModel.selecionarTrigram(trigram)
                },
                onHexagramClick = { number ->
                    navController.navigate(NavRoute.HexagramDetail.createRoute(number))
                }
            )
        }

        composable(NavRoute.Sadhana.route) {
            val sadhanaViewModel: SadhanaViewModel = hiltViewModel()
            SadhanaScreen(
                viewModel = sadhanaViewModel,
                onShowGuide = { topic ->
                    navController.navigate(NavRoute.Guide.createRoute(topic))
                }
            )
        }

        composable(
            route = NavRoute.Guide.route,
            arguments = listOf(navArgument("topic") { type = NavType.StringType })
        ) { backStackEntry ->
            val topic = backStackEntry.arguments?.getString("topic") ?: NavRoute.Guide.TOPIC_TAROT
            GuideScreen(
                topic = topic,
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            route = NavRoute.HexagramDetail.route,
            arguments = listOf(navArgument("number") { type = NavType.IntType })
        ) {
            val hexagramDetailViewModel: HexagramDetailViewModel = hiltViewModel()
            HexagramDetailScreen(
                viewModel = hexagramDetailViewModel,
                onBack = { navController.popBackStack() },
                onShowGuide = { topic ->
                    navController.navigate(NavRoute.Guide.createRoute(topic))
                }
            )
        }
    }
}