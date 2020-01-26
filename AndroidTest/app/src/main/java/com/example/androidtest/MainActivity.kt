package com.example.androidtest

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Document
import java.io.InputStream
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory


class MainActivity : AppCompatActivity() {

    val myHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val browser: WebView = findViewById(R.id.testwebView) as WebView
        val settings = browser.settings
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.javaScriptEnabled = true
        settings.allowFileAccess = true
        settings.domStorageEnabled = true
        val repeated =
            String.format(String.format("%%0%dd", 80000), 0).replace("0", ".") + "xx"
        browser.loadUrl("file:///android_asset/musicnotes.html")
        val str ="e/4"
        browser.post {
            run {
                browser.loadUrl("javascript:musictest(\"$str\")")
            }
        }

//        browser.evaluateJavascript("javascript:musictest();",object : ValueCallback<String> {
//                override fun onReceiveValue(value: String) {
//
//                }
//
//            })

        //        var musictest = CallJavaScript.callFunction(this)

    }


//    object CallJavaScript {
//
//        fun callFunction(mContext: android.content.Context): Any? {
//
//            var jsResult: Any? = null
//
//            val params = arrayOf<Any>("")
//
//            // Every Rhino VM begins with the enter()
//            // This Context is not Android's Context
//            val rhino = org.mozilla.javascript.Context.enter()
//
//            // Turn off optimization to make Rhino Android compatible
//            rhino.optimizationLevel = -1
//            try {
//                val scope = rhino.initStandardObjects()
//
//                // Note the forth argument is 1, which means the JavaScript source has
//                // been compressed to only one line using something like YUI
//
//                val assetManager = mContext.assets
//                try {
//                    val input = assetManager.open("music.js")
//                    val targetReader = InputStreamReader(input)
//                    rhino.evaluateReader(scope, targetReader, "JavaScript", 1, null)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//
//
//                // Get the functionName defined in JavaScriptCode
//                val obj = scope.get("musictest", scope)
//
//                if (obj is org.mozilla.javascript.Function) {
//
//                    // Call the function with params
//                    jsResult = obj.call(rhino, scope, scope, params)
//                    // Parse the jsResult object to a String
//                    val result = org.mozilla.javascript.Context.toString(jsResult)
//                }
//            } finally {
//                org.mozilla.javascript.Context.exit()
//            }
//            return jsResult
//        }
//    }

    fun openFileClick(view: View) {
        val intent = Intent()
            .setType("*/*")
            .setAction(Intent.ACTION_GET_CONTENT)

        startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == RESULT_OK) {
            val selectedFile = data?.data
            val inputStream = contentResolver.openInputStream(selectedFile)
            var doc = parseXml(inputStream)
            println(doc)
//            readXml("content://com.android.providers.downloads.documents"+selectedFile!!.path)
        }
    }

    fun parseXml(inputStream: InputStream, builder: DocumentBuilder = defaultDocumentBuilder()): Document {
        return builder.parse(inputStream)!!
    }

    fun defaultDocumentBuilder(builderFactory: DocumentBuilderFactory = defaultDocumentBuilderFactory()): DocumentBuilder {
        return builderFactory.newDocumentBuilder()
    }

    fun defaultDocumentBuilderFactory(): DocumentBuilderFactory {
        return DocumentBuilderFactory.newInstance()!!
    }

//    fun readXml(path:String): Document {
//        val xmlFile = File(path)
//
//        val dbFactory = DocumentBuilderFactory.newInstance()
//        val dBuilder = dbFactory.newDocumentBuilder()
//        val xmlInput = InputSource(StringReader(xmlFile.readText(Charsets.UTF_8)))
//        val doc = dBuilder.parse(xmlInput)
//
//        return doc
//    }
}
