package com.rockbass2560.mycamera

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.params.OutputConfiguration
import android.hardware.camera2.params.SessionConfiguration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private val REQUEST_CAMERA = 10
    private val executorCamera = Executors.newSingleThreadExecutor()

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CAMERA) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                configCamera()
            } else {
                Toast.makeText(this, "Es necesario aceptar el permiso de la camara para este ejercicio", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            configCamera()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA)
        }
    }

    @SuppressLint("MissingPermission")
    fun configCamera() {
        val surfacePreview = findViewById<SurfaceView>(R.id.surface_preview)

        surfacePreview.holder.addCallback(object: SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {

                val cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager

                cameraManager.openCamera(cameraManager.cameraIdList.first(),
                    object: CameraDevice.StateCallback() {
                        override fun onOpened(camera: CameraDevice) {
                            val builderCapture  = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
                            builderCapture.addTarget(holder.surface)

                            val captureRequest = builderCapture.build()

                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                                camera.createCaptureSession(
                                    listOf(holder.surface),
                                    object: CameraCaptureSession.StateCallback() {
                                        override fun onConfigured(session: CameraCaptureSession) {
                                            session.setRepeatingRequest(captureRequest, null, null)
                                        }

                                        override fun onConfigureFailed(session: CameraCaptureSession) { }

                                    },
                                    null
                                )
                            } else {
                                val sessionConfiguration = SessionConfiguration(
                                    SessionConfiguration.SESSION_REGULAR,
                                    listOf(
                                        OutputConfiguration(holder.surface)
                                    ),
                                    executorCamera,
                                    object: CameraCaptureSession.StateCallback() {
                                        override fun onConfigured(session: CameraCaptureSession) {
                                            session.setRepeatingRequest(captureRequest, null, null)
                                        }

                                        override fun onConfigureFailed(session: CameraCaptureSession) { }

                                    }
                                )

                                camera.createCaptureSession(sessionConfiguration)
                            }
                        }

                        override fun onDisconnected(camera: CameraDevice) { }

                        override fun onError(camera: CameraDevice, error: Int) { }

                    }, null)
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) { }

            override fun surfaceDestroyed(holder: SurfaceHolder) { }

        })
    }
}