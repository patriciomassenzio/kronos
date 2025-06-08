package com.kronos.presentation.ui.views.home.inventario

import android.app.Activity
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import io.scanbot.sdk.ui_v2.common.ScanbotColor
import io.scanbot.sdk.ui_v2.document.DocumentScannerActivity
import io.scanbot.sdk.ui_v2.document.configuration.DocumentScanningFlow
import io.scanbot.sdk.ui_v2.document.configuration.PageSnapFeedbackMode
import io.scanbot.sdk.ui_v2.document.configuration.UserGuidanceVisibility
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat


@Composable
fun InventarioView() {
    val context = LocalContext.current
    val activity = context as Activity

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val documentScannerResult = rememberLauncherForActivityResult(
        contract = DocumentScannerActivity.ResultContract()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.result?.let {
                Toast.makeText(context, "Escaneo exitoso", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasCameraPermission = granted
        if (granted) {
            launchDocumentScanner(documentScannerResult)
        } else {
            Toast.makeText(context, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        if (hasCameraPermission) {
            launchDocumentScanner(documentScannerResult)
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
}

private fun launchDocumentScanner(
    launcher: ActivityResultLauncher<DocumentScanningFlow>
) {
    val configuration = DocumentScanningFlow().apply {
        outputSettings.pagesScanLimit = 30
        documentUuid = null
        cleanScanningSession = true

        appearance.bottomBarBackgroundColor = ScanbotColor("#C8193C")

        screens.camera.apply {
            topUserGuidance.visible = true
            topUserGuidance.background.fillColor = ScanbotColor("#4A000000")
            topUserGuidance.title.text = "Scan your document"

            userGuidance.visibility = UserGuidanceVisibility.ENABLED
            userGuidance.background.fillColor = ScanbotColor("#4A000000")
            userGuidance.title.text = "Hold your device steady"

            bottomBar.importButton.visible = true
            bottomBar.importButton.title.visible = true
            bottomBar.importButton.title.text = "Importar"

            bottomBar.autoSnappingModeButton.title.visible = true
            bottomBar.autoSnappingModeButton.title.text = "Auto"

            bottomBar.manualSnappingModeButton.title.visible = true
            bottomBar.manualSnappingModeButton.title.text = "Manual"

            bottomBar.torchOnButton.title.visible = true
            bottomBar.torchOnButton.title.text = "On"
            bottomBar.torchOffButton.title.visible = true
            bottomBar.torchOffButton.title.text = "Off"

            captureFeedback.cameraBlinkEnabled = true
            captureFeedback.snapFeedbackMode = PageSnapFeedbackMode.pageSnapCheckMarkAnimation()
        }
    }

    launcher.launch(configuration)
}
