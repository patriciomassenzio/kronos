package com.kronos.presentation.ui.views.home.inventario

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import io.scanbot.pdf.model.*
import io.scanbot.sdk.ScanbotSDK
import io.scanbot.sdk.docprocessing.Document
import io.scanbot.sdk.entity.Language
import io.scanbot.sdk.ocr.OcrEngine
import io.scanbot.sdk.ocr.OcrSettings
import io.scanbot.sdk.ocr.model.Block
import io.scanbot.sdk.ocr.model.Line
import io.scanbot.sdk.ocr.model.Word
import io.scanbot.sdk.ui_v2.common.ScanbotColor
import io.scanbot.sdk.ui_v2.document.DocumentScannerActivity
import io.scanbot.sdk.ui_v2.document.configuration.DocumentScanningFlow
import io.scanbot.sdk.ui_v2.document.configuration.PageSnapFeedbackMode
import io.scanbot.sdk.ui_v2.document.configuration.UserGuidanceVisibility

@Composable
fun InventarioView() {
    val context = LocalContext.current
    val activity = context as Activity
    val scanbotSDK = remember { ScanbotSDK(context.applicationContext) }

    // createOcrEngine(...)
    val ocrRecognizer = scanbotSDK.createOcrEngine()

    // enableBinarizationInOcrSettingsSnippet(...)
    ScanbotSDKInitializer()
        .useOcrSettings(OcrSettings.Builder().binarizeImage(true).build())
        .initialize(activity.application)

    // engineModeTesseractSnippet(...)
    val languages = mutableSetOf<Language>()
    languages.add(Language.SPA)
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
            val document = result.result?.result
            if (document != null) {
                val ocrResult = ocrRecognizer.recognizeFromDocument(document)
                val text: String = ocrResult.recognizedText
                val firstBlock: List<Block> = ocrResult.ocrPages[0].blocks
                val linesInFirstBlock: List<Line> = ocrResult.ocrPages[0].blocks[0].lines
                val wordsInFirstLine: List<Word> = ocrResult.ocrPages[0].blocks[0].lines[0].words

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
                if (success) {
                    Log.d("PDF", "PDF con OCR generado correctamente")
                } else {
                    Log.e("PDF", "Fallo al generar PDF con OCR")
                }
            }
        }
    }

    Button(onClick = {
        scannerLauncher.launch(DocumentScanningFlow())
    }) {
        Text("Escanear y procesar OCR")
    }

    // Botón temporal para evitar función vacía
    Button(onClick = { /* Aquí podrías lanzar el escáner u otra acción */ }) {
        Text("Iniciar flujo OCR")
    }
}

fun createPdfWithOcrFromDocument(scanbotSDK: ScanbotSDK, document: Document): File? {
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
        resamplingMethod = ResamplingMethod.NONE
    )

    val ocrConfig = OcrEngine.OcrConfig(
        engineMode = OcrEngine.EngineMode.TESSERACT,
        languages = setOf(Language.SPA)
    )

    val success = pdfGenerator.generateWithOcrFromDocument(
        document = document,
        pdfConfig = pdfConfig,
        ocrConfig = ocrConfig
    )

    val pdfFile = document.pdfUri?.toFile()
    return if (success && pdfFile != null && pdfFile.exists()) {
        pdfFile
    } else {
        null
    }
}

fun createPdfWithOcrFromImages(scanbotSDK: ScanbotSDK, imageUris: List<Uri>): File? {
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
        resamplingMethod = ResamplingMethod.NONE
    )

    val ocrConfig = OcrEngine.OcrConfig(
        engineMode = OcrEngine.EngineMode.TESSERACT,
        languages = setOf(Language.SPA)
    )

    val pdfFile = pdfGenerator.generateWithOcrFromUris(
        imageFileUris = imageUris,
        pdfConfig = pdfConfig,
        ocrConfig = ocrConfig
    )

    return if (pdfFile != null && pdfFile.exists()) {
        pdfFile
    } else {
        null
    }
}
