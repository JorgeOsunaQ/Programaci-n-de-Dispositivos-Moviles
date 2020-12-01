package com.itcuandroid.mycamera

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Camera
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.params.OutputConfiguration
import android.hardware.camera2.params.SessionConfiguration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private val REQUEST_CAMERA = 10
    private val executorCamera= Executors.newSingleThreadExecutor()
    private lateinit var surfacePreview: SurfaceView
    private lateinit var cameraId: String
    private var cameraDevice: CameraDevice? =null
    private var isFront: Boolean=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        surfacePreview= findViewById(R.id.surface_preview)


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)  == PackageManager.PERMISSION_GRANTED){
            configCamera()
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if(requestCode == REQUEST_CAMERA){
            if(grantResults.contains(PackageManager.PERMISSION_GRANTED)){
                configCamera()
            }else{
                Toast.makeText(this, "Es necesario aceptar el permiso de la cámara para este ejercicio", Toast.LENGTH_SHORT)
            }
        }
    }


    @SuppressLint("MissingPermission")
    fun configCamera(){

        surfacePreview.holder.addCallback(object: SurfaceHolder.Callback{

            override fun surfaceCreated(holder: SurfaceHolder) {
                val cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
                cameraId= getCameraByDirection(isFront)
                cameraManager.openCamera(cameraId, stateCallback,null)
            }

            override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

            }

            override fun surfaceDestroyed(p0: SurfaceHolder) {

            }

        })
    }

    private val stateCallback= object : CameraDevice.StateCallback(){

            override fun onOpened(camera: CameraDevice) {
                cameraDevice=camera
                createCameraPreviewSession()
            }

            override fun onDisconnected(p0: CameraDevice) {
                TODO("Not yet implemented")
            }

            override fun onError(camera: CameraDevice, p1: Int) {
                TODO("Not yet implemented")
            }

    }

    private fun createCameraPreviewSession() {
        val previewRequestBuilder = cameraDevice!!.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
        previewRequestBuilder.addTarget(surfacePreview.holder.surface)

        val captureRequest = previewRequestBuilder.build()

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.P){
            cameraDevice?.createCaptureSession(
                listOf(surfacePreview.holder.surface),
                object: CameraCaptureSession.StateCallback(){
                    override fun onConfigured(session: CameraCaptureSession) {
                        session.setRepeatingRequest(captureRequest,null,null)
                    }

                    override fun onConfigureFailed(p0: CameraCaptureSession) {
                        TODO("Not yet implemented")
                    }

                }, null)
        }else{
            val sessionConfiguration = SessionConfiguration(
                SessionConfiguration.SESSION_REGULAR,
                listOf(
                    OutputConfiguration(surfacePreview.holder.surface)
                ),
                executorCamera,
                object: CameraCaptureSession.StateCallback(){
                    override fun onConfigured(session: CameraCaptureSession) {
                        session.setRepeatingRequest(captureRequest,null,null)
                    }

                    override fun onConfigureFailed(p0: CameraCaptureSession) {
                        TODO("Not yet implemented")
                    }

                }
            )
            cameraDevice?.createCaptureSession(sessionConfiguration)
        }
    }

    @SuppressLint("MissingPermission")
    fun flipCamera(view: View) {
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        this.isFront=!this.isFront

        this.cameraId=getCameraByDirection(this.isFront)
        cameraDevice?.close()
        cameraManager.openCamera(cameraId, stateCallback,null)

    }

    private fun getCameraByDirection(isFront: Boolean): String{
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        for (cameraId in cameraManager.cameraIdList) {
            val characteristics = cameraManager.getCameraCharacteristics(cameraId)
            val cameraDirection = characteristics.get(CameraCharacteristics.LENS_FACING)

            Log.d("idList","Current ID" +cameraDirection)
            Log.d("idList","Front ID" + CameraCharacteristics.LENS_FACING_FRONT)
            Log.d("idList","isFront" + isFront)

            if(cameraDirection == CameraCharacteristics.LENS_FACING_FRONT && isFront)
                return cameraId;

            if(cameraDirection == CameraCharacteristics.LENS_FACING_BACK && !isFront)
                return cameraId
        }

        return "0";
    }


}