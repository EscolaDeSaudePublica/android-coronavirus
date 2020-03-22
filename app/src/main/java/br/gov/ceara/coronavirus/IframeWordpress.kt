package br.gov.ceara.coronavirus

import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject


class IframeWordpress : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iframe_wordpress)
        val webView = findViewById(R.id.webview_wordpress) as WebView
        val url = "https://coronavirus.ceara.gov.br"
        loadWebView(webView, url)

//        webView.webViewClient = object : WebViewClient() {
//            override fun onPageFinished(view: WebView, url: String) {
//                super.onPageFinished(view, url)
//                val statusText =
//                    findViewById(R.id.text_webpageloadtest_load_status) as TextView
//                statusText.text = "done!"
//            }
//        }
//        webView.setWillNotCacheDrawing(true)
//        webView.isDrawingCacheEnabled = false
//
//        webView.loadUrl(url)
    }

    private fun loadWebView(webView: WebView, url: String) = with(webView) {
        settings.javaScriptEnabled = true
        addJavascriptInterface(WebAppInterface(), "MyJSInterface")
        loadData(htmlString(url), "text/html", "utf-8")
        loadUrl(url)
    }

    private fun htmlString(url: String): String {
        return """
            <iframe src='$url'></iframe>
          """
    }

    public class WebAppInterface {
        @JavascriptInterface
        fun handleMyEvent(jsonString: String) {
            val data: JSONObject = JSONObject(jsonString)
            // Do stuff with event data
        }
    }
}
