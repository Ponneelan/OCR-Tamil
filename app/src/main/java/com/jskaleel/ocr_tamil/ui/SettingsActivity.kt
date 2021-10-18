package com.jskaleel.ocr_tamil.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.jskaleel.ocr_tamil.BuildConfig
import com.jskaleel.ocr_tamil.R
import com.jskaleel.ocr_tamil.databinding.ActivitySettingsBinding
import com.jskaleel.ocr_tamil.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {
    private val binding: ActivitySettingsBinding by lazy {
        ActivitySettingsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.txtAppVersion.text =
            String.format(getString(R.string.version), BuildConfig.VERSION_NAME)

        binding.txtKaniyamDesc.text = HtmlCompat.fromHtml(
            getString(R.string.kaniyam_foundation_desc),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        binding.txtVglugDesc.text = HtmlCompat.fromHtml(
            getString(R.string.vglug_foundation_desc),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        binding.layoutKaniyam.setOnClickListener {
            openUrl("http://www.kaniyam.com/")
        }

        binding.layoutVglug.setOnClickListener {
            openUrl("https://vglug.org")
        }

        binding.rlSourceCodeLayout.setOnClickListener {
            openUrl("https://github.com/khaleeljageer/OCR-Tamil")
        }

        binding.llShareApp.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    String.format(getString(R.string.share_text), Constants.STORE_URL)
                )
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, getString(R.string.share_app))
            startActivity(shareIntent)
        }
    }

    private fun openUrl(url: String) {
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(url)
        }
        startActivity(shareIntent)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }
}