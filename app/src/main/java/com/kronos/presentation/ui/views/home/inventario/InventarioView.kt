package com.kronos.presentation.ui.views.home.inventario

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import io.scanbot.pdf.model.*
import io.scanbot.sdk.ScanbotSDK
import io.scanbot.sdk.docprocessing.Document
import io.scanbot.sdk.entity.Language
import io.scanbot.sdk.ocr.OcrEngine
import io.scanbot.sdk.ui_v2.document.DocumentScannerActivity
import io.scanbot.sdk.ui_v2.document.configuration.DocumentScanningFlow
import java.io.File

@Composable
fun InventarioView() {
    val context = LocalContext.current
    context as Activity
    val scanbotSDK = remember { ScanbotSDK(context.applicationContext) }

    var pendingPdfFile: File? by remember { mutableStateOf(null) }


    // Lanzador para elegir ubicación de guardado
    val saveLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/pdf")
    ) { uri: Uri? ->
        uri?.let {
            pendingPdfFile.let { file ->
                context.contentResolver.openOutputStream(uri)?.use { output ->
                    file?.inputStream().use { input ->
                        input?.copyTo(output)
                    }
                    Toast.makeText(context, "PDF guardado correctamente", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // Configurar OCR
    val ocrRecognizer = scanbotSDK.createOcrEngine()
    val languages = mutableSetOf(Language.SPA)
    ocrRecognizer.setOcrConfig(
        OcrEngine.OcrConfig(
            engineMode = OcrEngine.EngineMode.TESSERACT,
            languages = languages,
        )
    )

    val scannerLauncher = rememberLauncherForActivityResult(
        contract = DocumentScannerActivity.ResultContract()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val document = result.result
            if (document != null) {
                val ocrResult = ocrRecognizer.recognizeFromDocument(document)
                val text: String = ocrResult.recognizedText
                ocrResult.ocrPages[0].blocks
                ocrResult.ocrPages[0].blocks[0].lines
                ocrResult.ocrPages[0].blocks[0].lines[0].words

                Toast.makeText(context, "OCR detectado: ${text.take(100)}...", Toast.LENGTH_LONG).show()

                val pdfGenerator = scanbotSDK.createPdfGenerator()
                val pdfConfig = PdfConfiguration(
                    attributes = PdfAttributes(
                        author = "",
                        title = "",
                        subject = "",
                        keywords = "",
                        creator = ""
                    ),
                    pageSize = PageSize.A4,
                    pageDirection = PageDirection.AUTO,
                    dpi = 200,
                    jpegQuality = 100,
                    pageFit = PageFit.NONE,
                    resamplingMethod = ResamplingMethod.NONE,
                )
                val ocrConfig = OcrEngine.OcrConfig(engineMode = OcrEngine.EngineMode.SCANBOT_OCR)
                val success = pdfGenerator.generateWithOcrFromDocument(document, pdfConfig, ocrConfig)
                val pdfFile = document.pdfUri.toFile()
                if (success && pdfFile.exists()) {
                    pendingPdfFile = pdfFile
                    saveLauncher.launch("Documento_OCR.pdf")
                } else {
                    Toast.makeText(context, "No se pudo generar el PDF", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Button(onClick = {
        scannerLauncher.launch(DocumentScanningFlow())
    }) {
        Text("Escanear y procesar OCR")
    }

    Button(onClick = { /* Botón adicional si querés usarlo */ }) {
        Text("Iniciar flujo OCR")
    }
}
