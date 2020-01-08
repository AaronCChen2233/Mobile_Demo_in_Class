package com.example.androidtest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Document
import org.xml.sax.InputSource
import java.io.File
import java.io.InputStream
import java.io.StringReader
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

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
