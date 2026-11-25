package com.example.tarotoneinviewmode.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast

object ShareUtils {
    fun shareText(context: Context, text: String, title: String = "Compartilhar com") {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, title)
        context.startActivity(shareIntent)
    }

    fun copyToClipboard(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Oráculo", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Copiado para a área de transferência ✨", Toast.LENGTH_SHORT).show()
    }
}
