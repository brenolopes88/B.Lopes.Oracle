package com.example.tarotoneinviewmode.ui.main

import android.os.Build
import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tarotoneinviewmode.R
import com.example.tarotoneinviewmode.ui.navigation.NavRoute
import com.example.tarotoneinviewmode.ui.theme.TarotDesignTokens
import com.example.tarotoneinviewmode.ui.theme.DeepIndigo
import com.example.tarotoneinviewmode.util.RemoteConfigHelper

sealed class BottomNavItem(
    val route: String,
    val titleRes: Int,
    val iconRes: Int
) {
    object Home   : BottomNavItem(NavRoute.Home.route,    R.string.nav_home,   R.drawable.ic_menu_home)
    object IChing : BottomNavItem("iching_archive",  R.string.nav_iching, R.drawable.ic_menu_iching)
    object Cards  : BottomNavItem(NavRoute.Archive.route, R.string.nav_tarot,   R.drawable.ic_menu_tarot)
    object Sadhana : BottomNavItem("sadhana", R.string.nav_sadhana, R.drawable.ic_menu_sadhana)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    navController: NavHostController,
    content: @Composable (Modifier) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isOnboarding = currentRoute?.startsWith("onboarding") == true

    // 🚀 Mantemos a simetria fixa (2 + 1 + 2) para o FluidBottomBar não quebrar
    val leftItems = listOf(BottomNavItem.Home, BottomNavItem.IChing)
    val rightItems = listOf(BottomNavItem.Cards, BottomNavItem.Sadhana)

    val mainRoutes = listOf(NavRoute.Home.route, "iching_archive", NavRoute.Archive.route, "sadhana", "history")
    val isMainTab = mainRoutes.any { currentRoute?.startsWith(it) == true }
    
    val isSpreadSelection = currentRoute?.startsWith("spread_selection") == true
    val showBottomBar = isMainTab && !isSpreadSelection

    val showTopBar = currentRoute != null && !listOf("draw/", "iching/", "card_detail/", "hexagram_detail/", "guide/", "onboarding/").any { currentRoute.startsWith(it) }

    val topImageRes: Int? = when {
        currentRoute?.startsWith(NavRoute.Home.route) == true -> R.drawable.tp_home
        currentRoute?.startsWith("iching_archive") == true -> R.drawable.tp_iching
        currentRoute?.startsWith(NavRoute.Archive.route) == true -> R.drawable.tp_tarot
        currentRoute?.startsWith("spread_selection") == true -> R.drawable.tp_tiragens
        else -> R.drawable.tp_sadhana
    }

    Box(modifier = Modifier.fillMaxSize().background(DeepIndigo)) {
        Image(
            painter = painterResource(id = R.drawable.bg_default),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = TarotDesignTokens.Alpha.BgImageDefault
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent, 
            topBar = {
                if (showTopBar) {
                    Box(modifier = Modifier.fillMaxWidth().height(110.dp)) {
                        topImageRes?.let { resId ->
                            Image(
                                painter = painterResource(id = resId),
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth().height(110.dp),
                                contentScale = ContentScale.Crop,
                                //alignment = Alignment.BottomCenter
                            )
                        }

                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                            title = { },
                            navigationIcon = {
                                if (!isMainTab && !isOnboarding) {
                                    IconButton(onClick = { navController.popBackStack() }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack, 
                                            contentDescription = stringResource(R.string.nav_back), 
                                            tint = Color.White
                                        )
                                    }
                                }
                            }
                        )

                        if (isMainTab) {
                            Row(
                                modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 8.dp, end = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                val guideTopic = if (currentRoute?.startsWith("iching") == true) NavRoute.Guide.TOPIC_ICHING else NavRoute.Guide.TOPIC_TAROT
                                IconButton(onClick = { navController.navigate(NavRoute.Guide.createRoute(guideTopic)) }) {
                                    Icon(imageVector = Icons.Filled.Info, contentDescription = "Info", tint = MaterialTheme.colorScheme.primary)
                                }
                                IconButton(onClick = { navController.navigate(NavRoute.Settings.route) }) {
                                    Icon(imageVector = Icons.Filled.Settings, contentDescription = stringResource(R.string.nav_settings), tint = Color.White.copy(alpha = 0.8f))
                                }
                            }
                        }
                    }
                }
            },
            bottomBar = {
                if (showBottomBar) {
                    FluidBottomBar(
                        currentRoute = currentRoute,
                        navController = navController,
                        leftItems = leftItems,
                        rightItems = rightItems
                    )
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                content(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun AppTabItem(
    item: BottomNavItem,
    currentRoute: String?,
    navController: NavHostController,
    modifier: Modifier
) {
    val selected = currentRoute?.startsWith(item.route) == true
    val context = LocalContext.current
    val isSadhanaEnabled = false // Sadhana fora da v1
    val itemTitle = stringResource(item.titleRes)
    val sadhanaSoonMsg = stringResource(R.string.msg_sadhana_soon)
    
    Column(
        modifier = modifier
            .fillMaxHeight()
            .clickable {
                if (item is BottomNavItem.Sadhana && !isSadhanaEnabled) {
                    Toast.makeText(context, sadhanaSoonMsg, Toast.LENGTH_SHORT).show()
                } else if (!selected) {
                    navController.navigate(item.route) {
                        popUpTo(NavRoute.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.size(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = item.iconRes),
                contentDescription = itemTitle,
                modifier = Modifier.size(36.dp),
                tint = if (selected) MaterialTheme.colorScheme.onPrimary else Color.White.copy(alpha = 0.5f)
            )
        }
        
        if (!selected) {
            Text(
                text = itemTitle,
                style = MaterialTheme.typography.labelSmall,
                fontSize = 12.sp,
                color = Color.White.copy(alpha = TarotDesignTokens.Alpha.TabUnselectedText)
            )
        }
    }
}

@Composable
fun FluidBottomBar(
    currentRoute: String?,
    navController: NavHostController,
    leftItems: List<BottomNavItem>,
    rightItems: List<BottomNavItem>,
    modifier: Modifier = Modifier
) {
    val allItems = leftItems + rightItems
    val selectedIndexInAll = allItems.indexOfFirst { currentRoute?.startsWith(it.route) == true }
    
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .height(72.dp)
            .background(DeepIndigo.copy(alpha = 0.95f)),
        contentAlignment = Alignment.Center
    ) {
        val width = maxWidth
        val totalWeight = 5.2f // 1+1+1.2+1+1 (Sempre fixo para manter a simetria)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        renderEffect = createGooeyRenderEffect(blurRadius = 35f)
                    }
                }
        ) {
            // Hub Central
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = (-24).dp)
                    .size(60.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
            )

            if (selectedIndexInAll != -1) {
                // Cálculo de precisão baseado no peso das colunas
                val targetFraction = when (selectedIndexInAll) {
                    0 -> 0.5f / totalWeight
                    1 -> 1.5f / totalWeight
                    2 -> 3.7f / totalWeight 
                    3 -> 4.7f / totalWeight
                    else -> 0.5f
                }
                
                val targetOffset = width * (targetFraction - 0.5f)
                val animOffset by animateDpAsState(targetValue = targetOffset, animationSpec = tween(450))

                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(x = animOffset, y = (-4).dp)
                        .size(44.dp)
                        .background(MaterialTheme.colorScheme.primary, CircleShape)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leftItems.forEach { item ->
                AppTabItem(item, currentRoute, navController, Modifier.weight(1f))
            }

            Box(modifier = Modifier.weight(1.2f), contentAlignment = Alignment.Center) {
                Surface(
                    onClick = { navController.navigate(NavRoute.SpreadSelection.route) },
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    shadowElevation = 8.dp,
                    modifier = Modifier.size(58.dp).offset(y = (-28).dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = "Tiragens",
                            modifier = Modifier.size(32.dp),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }

            rightItems.forEach { item ->
                AppTabItem(item, currentRoute, navController, Modifier.weight(1f))
            }
        }
    }
}
