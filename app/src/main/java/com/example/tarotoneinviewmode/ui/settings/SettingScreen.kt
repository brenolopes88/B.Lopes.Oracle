package com.example.tarotoneinviewmode.ui.settings

import android.content.Intent
import android.net.Uri
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.tarotoneinviewmode.util.AnalyticsHelper
import com.example.tarotoneinviewmode.ui.theme.TarotDesignTokens
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tarotoneinviewmode.BuildConfig
import com.example.tarotoneinviewmode.R

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var mostrarConfirmacaoLimpar by remember { mutableStateOf(false) }
    var mostrarConfirmacaoImportar by remember { mutableStateOf(false) }
    var mostrarDialogoExplicacaoPermission by remember { mutableStateOf(false) }
    var mostrarDialogoNegadoPermission by remember { mutableStateOf(false) }
    var pendingNotifType by remember { mutableStateOf<String?>(null) }
    var pendingHora by remember { mutableStateOf(7) }
    var pendingMinuto by remember { mutableStateOf(0) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            AnalyticsHelper.logNotificationPermissionResult(AnalyticsHelper.RESULT_GRANTED)
            when (pendingNotifType) {
                "MANHA" -> viewModel.salvarNotifManha(context, true, pendingHora, pendingMinuto)
                "NOITE" -> viewModel.salvarNotifNoite(context, true, pendingHora, pendingMinuto)
            }
        } else {
            AnalyticsHelper.logNotificationPermissionResult(AnalyticsHelper.RESULT_DENIED)
            mostrarDialogoNegadoPermission = true
            when (pendingNotifType) {
                "MANHA" -> viewModel.salvarNotifManha(context, false, pendingHora, pendingMinuto)
                "NOITE" -> viewModel.salvarNotifNoite(context, false, pendingHora, pendingMinuto)
            }
        }
        pendingNotifType = null
    }

    LaunchedEffect(uiState.notifManhaAtiva, uiState.notifNoiteAtiva) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val hasPermission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!hasPermission) {
                if (uiState.notifManhaAtiva) {
                    viewModel.salvarNotifManha(context, false, uiState.notifManhaHora, uiState.notifManhaMinuto)
                }
                if (uiState.notifNoiteAtiva) {
                    viewModel.salvarNotifNoite(context, false, uiState.notifNoiteHora, uiState.notifNoiteMinuto)
                }
            }
        }
    }

    val launcherExport = rememberLauncherForActivityResult(
        ActivityResultContracts.CreateDocument("application/json")
    ) { uri ->
        uri?.let { viewModel.exportarBackup(context, it) }
    }

    val launcherImport = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let { viewModel.importarBackup(context, it) }
    }

    LaunchedEffect(uiState.statusBackup) {
        uiState.statusBackup?.let {
            // Mostrar um toast ou snackbar simples
            android.widget.Toast.makeText(context, it, android.widget.Toast.LENGTH_LONG).show()
            viewModel.limparStatus()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // ── header ──
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Configurações",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Light,
                letterSpacing = 2.sp
            )
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f))

        Spacer(modifier = Modifier.height(24.dp))

        // ── notificações ──
        SettingsSection(titulo = "Notificações") {
            NotifSection(
                titulo = "✦ Carta do Dia",
                descricao = "Sua carta do dia está esperando.",
                ativa = uiState.notifManhaAtiva,
                hora = uiState.notifManhaHora,
                minuto = uiState.notifManhaMinuto,
                onSalvar = { ativa, hora, minuto ->
                    if (ativa && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        val hasPermission = ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.POST_NOTIFICATIONS
                        ) == PackageManager.PERMISSION_GRANTED

                        if (!hasPermission) {
                            pendingNotifType = "MANHA"
                            pendingHora = hora
                            pendingMinuto = minuto
                            mostrarDialogoExplicacaoPermission = true
                            return@NotifSection
                        }
                    }
                    viewModel.salvarNotifManha(context, ativa, hora, minuto)
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            NotifSection(
                titulo = "✦ Reflexão do Dia",
                descricao = "E aí, ressoou?",
                ativa = uiState.notifNoiteAtiva,
                hora = uiState.notifNoiteHora,
                minuto = uiState.notifNoiteMinuto,
                onSalvar = { ativa, hora, minuto ->
                    if (ativa && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        val hasPermission = ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.POST_NOTIFICATIONS
                        ) == PackageManager.PERMISSION_GRANTED

                        if (!hasPermission) {
                            pendingNotifType = "NOITE"
                            pendingHora = hora
                            pendingMinuto = minuto
                            mostrarDialogoExplicacaoPermission = true
                            return@NotifSection
                        }
                    }
                    viewModel.salvarNotifNoite(context, ativa, hora, minuto)
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ── privacidade e dados ──
        SettingsSection(titulo = stringResource(id = R.string.section_privacy)) {
            AnalyticsToggleSection(
                titulo = stringResource(id = R.string.analytics_toggle_title),
                descricao = stringResource(id = R.string.analytics_toggle_desc),
                ativa = uiState.analyticsEnabled,
                onSalvar = { ativa ->
                    viewModel.setAnalyticsEnabled(context, ativa)
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        // ── dados ──
        SettingsSection(titulo = "Backup e Dados") {
            SettingsItem(
                label = "Exportar Backup (JSON)",
                onClick = { launcherExport.launch("oraculo_backup.json") }
            )
            SettingsItem(
                label = "Importar Backup (JSON)",
                onClick = { mostrarConfirmacaoImportar = true }
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f))
            SettingsItem(
                label = "Limpar histórico de leituras",
                cor = TarotDesignTokens.Colors.DestructiveText,
                onClick = { mostrarConfirmacaoLimpar = true }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Spacer(modifier = Modifier.height(20.dp))

        // ── sobre ──
        SettingsSection(titulo = "Sobre") {
            SettingsItem(
                label = "Canal no TikTok",
                valor = "@b.lopes.oraculo",
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tiktok.com/@b.lopes.oraculo"))
                    context.startActivity(intent)
                }
            )
            SettingsItem(
                label = "Canal no Insta",
                valor = "@b.lopes.oraculo",
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/b.lopes.holistic/"))
                    context.startActivity(intent)
                }
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f))
            SettingsItem(
                label = "Versão",
                valor = BuildConfig.VERSION_NAME
            )
        }

        Spacer(modifier = Modifier.height(20.dp))



        // ── legal ──
        SettingsSection(titulo = "Legal") {
            SettingsItem(
                label = "Política de Privacidade",
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://blopes-oracle.github.io/oracle/privacy.html"))
                    context.startActivity(intent)
                }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }

    // ── confirmação limpar histórico ──
    if (mostrarConfirmacaoLimpar) {
        AlertDialog(
            onDismissRequest = { mostrarConfirmacaoLimpar = false },
            title = {
                Text(
                    "Limpar histórico",
                    style = MaterialTheme.typography.titleMedium
                )
            },
            text = {
                Text(
                    "Todas as leituras e notas serão apagadas permanentemente. Esta ação não pode ser desfeita.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.limparHistorico()
                        mostrarConfirmacaoLimpar = false
                    }
                ) {
                    Text(
                        "Limpar",
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Medium
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarConfirmacaoLimpar = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    // ── confirmação importar backup ──
    if (mostrarConfirmacaoImportar) {
        AlertDialog(
            onDismissRequest = { mostrarConfirmacaoImportar = false },
            title = {
                Text(
                    text = stringResource(id = R.string.dialog_import_title),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.dialog_import_desc),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarConfirmacaoImportar = false
                        launcherImport.launch(arrayOf("application/json"))
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.action_restore),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarConfirmacaoImportar = false }) {
                    Text(text = stringResource(id = R.string.action_cancel))
                }
            }
        )
    }

    // ── diálogo explicação permissão de notificação ──
    if (mostrarDialogoExplicacaoPermission) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoExplicacaoPermission = false },
            title = {
                Text(
                    text = stringResource(id = R.string.dialog_notif_permission_title),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.dialog_notif_permission_desc),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarDialogoExplicacaoPermission = false
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                    }
                ) {
                    Text(
                        text = "Continuar",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    mostrarDialogoExplicacaoPermission = false
                    pendingNotifType = null
                }) {
                    Text(text = stringResource(id = R.string.action_cancel))
                }
            }
        )
    }

    // ── diálogo permissão negada (ir para configurações) ──
    if (mostrarDialogoNegadoPermission) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoNegadoPermission = false },
            title = {
                Text(
                    text = stringResource(id = R.string.dialog_notif_permission_title),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.dialog_notif_denied_desc),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarDialogoNegadoPermission = false
                        val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                            putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                        }
                        context.startActivity(intent)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.action_open_settings),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogoNegadoPermission = false }) {
                    Text(text = stringResource(id = R.string.action_cancel))
                }
            }
        )
    }
}

// ── componentes ───────────────────────────────────────────────────────────────

@Composable
private fun SettingsSection(
    titulo: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Text(
            text = titulo.uppercase(),
            style = MaterialTheme.typography.labelMedium,
            fontSize = 12.sp,
            color = TarotDesignTokens.Colors.LabelGold,
            letterSpacing = 2.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface.copy(alpha = TarotDesignTokens.Alpha.CardSurface),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            content()
        }
    }
}

@Composable
private fun SettingsItem(
    label: String,
    valor: String? = null,
    cor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurface,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = cor,
            modifier = Modifier.weight(1f)
        )
        if (valor != null) {
            Text(
                text = valor,
                style = MaterialTheme.typography.bodySmall,
                color = TarotDesignTokens.Colors.TextSupport
            )
        }
        if (onClick != null && valor == null) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
            )
        }
    }
}

@Composable
private fun AnalyticsToggleSection(
    titulo: String,
    descricao: String,
    ativa: Boolean,
    onSalvar: (Boolean) -> Unit
) {
    var ativaLocal by remember(ativa) { mutableStateOf(ativa) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f).padding(end = 16.dp)) {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = descricao,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
                )
            }
            Switch(
                checked = ativaLocal,
                onCheckedChange = {
                    ativaLocal = it
                    onSalvar(it)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                )
            )
        }
    }
}

@Composable
private fun NotifSection(
    titulo: String,
    descricao: String,
    ativa: Boolean,
    hora: Int,
    minuto: Int,
    onSalvar: (Boolean, Int, Int) -> Unit
) {
    val context = LocalContext.current
    var ativaLocal  by remember(ativa)   { mutableStateOf(ativa) }
    var horaLocal   by remember(hora)    { mutableStateOf(hora) }
    var minutoLocal by remember(minuto)  { mutableStateOf(minuto) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = descricao,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
                )
            }
            Switch(
                checked = ativaLocal,
                onCheckedChange = {
                    ativaLocal = it
                    onSalvar(it, horaLocal, minutoLocal)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                )
            )
        }

        if (ativaLocal) {
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Horário",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                OutlinedButton(
                    onClick = {
                        android.app.TimePickerDialog(
                            context,
                            { _, h, m ->
                                horaLocal   = h
                                minutoLocal = m
                                onSalvar(ativaLocal, h, m)
                            },
                            horaLocal,
                            minutoLocal,
                            true
                        ).show()
                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "%02d:%02d".format(horaLocal, minutoLocal),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 2.sp
                    )
                }
            }
        }
    }
}