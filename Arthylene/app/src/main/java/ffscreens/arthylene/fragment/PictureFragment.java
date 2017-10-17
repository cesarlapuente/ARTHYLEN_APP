package ffscreens.arthylene.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.uab.cvc.scanfruits.ScanFruitsSDK;
import ffscreens.arthylene.Constants;
import ffscreens.arthylene.R;
import ffscreens.arthylene.camera.Preview;
import ffscreens.arthylene.enumeration.EtatEnum;
import ffscreens.arthylene.objects.ScanResult;
import ffscreens.arthylene.show.DrawLayer;
import ffscreens.arthylene.show.Drawing;
import ffscreens.arthylene.utils.ResultFormat;

/**
 * Arthylene
 * Created by Thibault Nougues on 29/06/2017.
 */

public class PictureFragment extends Fragment implements Camera.AutoFocusCallback,
        Camera.ShutterCallback,
        Camera.PictureCallback,
        Camera.PreviewCallback,
        View.OnClickListener {

    private static final String TAG = "Picture_Fragment";
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    String json = "test";
    private Preview mPreview;
    private Camera mCamera;
    private Camera.Size sizeFrame;
    private Camera.Size sizePicture;
    private EtatEnum etat;
    private TextureView textureView;
    private SharedPreferences preferences;
    private PictureFragmentCallback pictureFragmentCallback;
//    private Boolean grayInit = false;

    /*****************************************/

    /*  api camera2 version  */

    //now it's possible to contract this unused part
    /**
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    protected byte[] byteScan;
    private CameraDevice device;
    private CameraCaptureSession session;
    private CaptureRequest.Builder builder;
    private Size imageDimension;
    private ImageReader imageReader;
    private File file;
    private Handler mBackgroundHandler;
    private final CameraDevice.StateCallback callback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            device = cameraDevice;
            createCameraPreview();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            device.close();
        }

        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int i) {
            device.close();
            device = null;
        }
    };
    final CameraCaptureSession.CaptureCallback captureCallback = new CameraCaptureSession.CaptureCallback() {
        @Override
        public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
            super.onCaptureCompleted(session, request, result);
            createCameraPreview();
        }
    };
    private HandlerThread mBackgroundThread;
    private TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
            openCamera();
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
            // nothing
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            CameraManager manager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);
            try {
                CameraCharacteristics characteristics = manager.getCameraCharacteristics(device.getId());
                Size[] jpegSizes = null;
                if (characteristics != null) {
                    jpegSizes = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP).getOutputSizes(ImageFormat.JPEG);
                }
                int width = 640;
                int height = 480;
                if (jpegSizes != null && 0 < jpegSizes.length) {
                    width = jpegSizes[0].getWidth();
                    height = jpegSizes[0].getHeight();
                }
                ImageReader reader = ImageReader.newInstance(width, height, ImageFormat.JPEG, 1);
                List<Surface> outputSurfaces = new ArrayList<Surface>(2);
                outputSurfaces.add(reader.getSurface());
                outputSurfaces.add(new Surface(textureView.getSurfaceTexture()));
                final CaptureRequest.Builder captureBuilder = device.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
                captureBuilder.addTarget(reader.getSurface());
                captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
                ImageReader.OnImageAvailableListener readerListener = new ImageReader.OnImageAvailableListener() {
                    @Override
                    public void onImageAvailable(ImageReader reader) {
                        Image image = null;
                        try {
                            image = reader.acquireLatestImage();
                            ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                            byte[] bytes = new byte[buffer.capacity()];

                            buffer.get(bytes);

                            ByteBuffer buffers = ByteBuffer.allocateDirect((640 * 480) << 2);
                            buffers.order(ByteOrder.LITTLE_ENDIAN);
                            mFrameRGB3 = buffer.asIntBuffer();
                            ScanFruitsSDK.convertYUV420sp2BGRA(bytes, 640, 480, mFrameRGB3);
                            mFrameRGB3.rewind();
                            final int offset = (640 - 480 )/2;
                            final int sizeRoi = 480;

                            result = ScanFruitsSDK.processImageROI(mFrameRGB3, 640, 480, offset, 0 , offset + sizeRoi, sizeRoi);


                            Log.e("*-+", result);

                            //save(bytes);
                        } finally {
                            if (image != null) {
                                image.close();
                            }
                        }
                    }
                };
                reader.setOnImageAvailableListener(readerListener, null);
                final CameraCaptureSession.CaptureCallback captureListener = new CameraCaptureSession.CaptureCallback() {
                    @Override
                    public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result2) {
                        super.onCaptureCompleted(session, request, result2);

                        createCameraPreview();
                    }
                };
                device.createCaptureSession(outputSurfaces, new CameraCaptureSession.StateCallback() {
                    @Override
                    public void onConfigured(CameraCaptureSession session) {
                        try {
                            session.capture(captureBuilder.build(), captureListener, null);
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onConfigureFailed(CameraCaptureSession session) {
                    }
                }, null);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
            // nothing
        }
    };
    private String result;
    private IntBuffer mFrameRGB3;



    private void startBackgroundThread() {
        //mBackgroundThread = new HandlerThread("Camera Background");
        //mBackgroundThread.start();
        //mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }

    protected void stopBackgroundThread() {
        //mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void takePicture() {
        if (null == device) {
            Log.e(TAG, "cameraDevice is null");
            return;
        }
        CameraManager manager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);
        try {
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(device.getId());
            Size[] jpegSizes = null;
            if (characteristics != null) {
                jpegSizes = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP).getOutputSizes(ImageFormat.JPEG);
            }
            int width = 640;
            int height = 480;
            if (jpegSizes != null && 0 < jpegSizes.length) {
                width = jpegSizes[0].getWidth();
                height = jpegSizes[0].getHeight();
            }
            ImageReader reader = ImageReader.newInstance(width, height, ImageFormat.JPEG, 1);
            List<Surface> outputSurfaces = new ArrayList<Surface>(2);
            outputSurfaces.add(reader.getSurface());
            outputSurfaces.add(new Surface(textureView.getSurfaceTexture()));
            final CaptureRequest.Builder captureBuilder = device.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            captureBuilder.addTarget(reader.getSurface());
            captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
            // Orientation
            int rotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
            captureBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATIONS.get(rotation));
            final File file = new File(Environment.getExternalStorageDirectory() + "/pic.jpg");
            ImageReader.OnImageAvailableListener readerListener = new ImageReader.OnImageAvailableListener() {
                @Override
                public void onImageAvailable(ImageReader reader) {
                    Image image = null;
                    try {
                        image = reader.acquireLatestImage();
                        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                        byte[] bytes = new byte[buffer.capacity()];

                        buffer.get(bytes);

                        save(bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (image != null) {
                            image.close();
                        }
                    }
                }
                private void save(byte[] bytes) throws IOException {
                    OutputStream output = null;
                    try {
                        byteScan = bytes.clone();

                        ByteBuffer buffer = ByteBuffer.allocateDirect((640 * 480) << 2);
                        buffer.order(ByteOrder.LITTLE_ENDIAN);
                        mFrameRGB3 = buffer.asIntBuffer();
                        ScanFruitsSDK.convertYUV420sp2BGRA(bytes, 640, 480, mFrameRGB3);
                        mFrameRGB3.rewind();
                        final int offset = (640 - 480 )/2;
                        final int sizeRoi = 480;

                        result = ScanFruitsSDK.processImageROI(mFrameRGB3, 640, 480, offset, 0 , offset + sizeRoi, sizeRoi);


                        Log.e("*-+", result);


                        pictureFragmentCallback.onPictureResult(true, result);

//                        output = new FileOutputStream(file);
//                        output.write(bytes);
                    } finally {
                        if (null != output) {
                            output.close();
                        }
                    }
                }
            };
            reader.setOnImageAvailableListener(readerListener, null);
            final CameraCaptureSession.CaptureCallback captureListener = new CameraCaptureSession.CaptureCallback() {
                @Override
                public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result2) {
                    super.onCaptureCompleted(session, request, result2);

                    //createCameraPreview();
                }
            };
            device.createCaptureSession(outputSurfaces, new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(CameraCaptureSession session) {
                    try {
                        session.capture(captureBuilder.build(), captureListener, null);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onConfigureFailed(CameraCaptureSession session) {
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    protected void createCameraPreview() {
        try {
            SurfaceTexture texture = textureView.getSurfaceTexture();
            assert texture != null;
            texture.setDefaultBufferSize(imageDimension.getWidth(), imageDimension.getHeight());
            Surface surface = new Surface(texture);
            builder = device.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            builder.addTarget(surface);
            device.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    //The camera is already closed
                    if (null == device) {
                        return;
                    }
                    // When the session is ready, we start displaying the preview.
                    session = cameraCaptureSession;
                    updatePreview();
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                    Toast.makeText(getActivity(), "Configuration change", Toast.LENGTH_SHORT).show();
                }
            }, null);
        } catch (CameraAccessException e) {
            Log.e(TAG, "createCameraPreview: ", e);
        }
    }

    private void openCamera() {
        CameraManager manager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = manager.getCameraIdList()[0];
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
            StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            assert map != null;
            imageDimension = map.getOutputSizes(SurfaceTexture.class)[0];
            // Add permission for camera and let user grant the permission
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CAMERA_PERMISSION);
                return;
            }
            manager.openCamera(cameraId, callback, null);
        } catch (CameraAccessException e) {
            Log.e(TAG, "openCamera: ", e);
        }
    }

    protected void updatePreview() {
        if (null == device) {
            Log.e("Picture fragment", "updatePreview error, return");
        }
        try {
            builder.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, preferences.getInt("white", 0));
            session.setRepeatingRequest(builder.build(), null, null);

        } catch (CameraAccessException e) {
            Log.e(TAG, "updatePreview: ", e);
        }
    }

    private void closeCamera() {
        if (null != device) {
            device.close();
            device = null;
        }
        if (null != imageReader) {
            imageReader.close();
            imageReader = null;
        }
    }
    **/


    /*
     * function to create new instance au this fragment with param
     */
    public static PictureFragment newInstance(EtatEnum etatEnum) {
        PictureFragment fragment = new PictureFragment();
        Bundle args = new Bundle();
        args.putSerializable("EtatEnum", etatEnum);
        fragment.setArguments(args);
        return fragment;
    }

    /****************************/

    /*
     * check the permission of the app
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION && grantResults[0] == PackageManager.PERMISSION_DENIED) {
            // close the app
            Toast.makeText(getActivity(), "Sorry!!!, you can't use this app without granting permission", Toast.LENGTH_LONG).show();
            getActivity().finish();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        /*//startBackgroundThread();
        if (textureView.isAvailable()) {
            openCamera();
        } else {
            textureView.setSurfaceTextureListener(textureListener);
        }*/

        //Prepare Camera
        prepareCamera();

        // Force to show the Preview
        mPreview.setVisibility(View.INVISIBLE);
        mPreview.setVisibility(View.VISIBLE);
        mCamera.setPreviewCallback(this);
    }

    @Override
    public void onPause() {
        //closeCamera();
        // stopBackgroundThread();
        releaseCamera();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        //ScanFruitsSDK.processDestroy();
        super.onDestroy();
    }

    /*
     * camera api version
     */
    public Camera getCameraInstance() {
        Camera camera = null;

        try {
            // Attempt to get a Camera instance
            camera = Camera.open();
            Camera.Parameters params = camera.getParameters();
            //params.set("orientation", "portrait");
            //params.set("rotation", 90);
            camera.setDisplayOrientation(90);

            // Frame Size...
            List<Camera.Size> sizesPreview = params.getSupportedPreviewSizes();
            sizeFrame = mPreview.getBestCameraSize(sizesPreview, Constants.PREVIEW_WIDTH, Constants.PREVIEW_HEIGHT);

            // Picture Size...
            List<Camera.Size> sizesPicture = params.getSupportedPictureSizes();
            sizePicture = mPreview.getBestCameraSize(sizesPicture, Constants.PICTURE_WIDTH, Constants.PICTURE_HEIGHT);
            //params.setPictureSize(Constants.PREVIEW_WIDTH, Constants.PREVIEW_HEIGHT);

            // Jpeg Quality...
            params.setJpegQuality(Constants.PICTURE_QUALITY);

            camera.setParameters(params);
        } catch (Exception e) {
            Log.e(TAG, "getCameraInstance: ", e);
            // Camera is not available (in use or does not exist)
        }

        return camera;        // returns null if camera is unavailable
    }

    protected void prepareCamera() {
        // Get Camera instance...
        mCamera = getCameraInstance();
        mCamera.setPreviewCallback(this);
        mPreview.setCamera(mCamera, sizeFrame, sizePicture);
    }

    protected void releaseCamera() {
        if (mCamera != null) {
            // Release the camera for other applications
            mPreview.setCamera(null, null, null);
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
//            grayInit = false;
        }
    }

    public void onClick(View v) {
        if (v == mPreview) {
            // Camera AutoFocus...
            mCamera.autoFocus(this);
        }
    }

    @Override
    public synchronized void onPreviewFrame(byte[] data, Camera camera) {
        // Check ScanFruitsSDK
        if (ScanFruitsSDK.processIsCreated() == false)
            return;

//        if(!grayInit)
//        {
//            gray test only on camera start
//            final int offsetX = (sizeFrame.width - sizeFrame.height) / 2 + (sizeFrame.height * 4) / 10;
//            final int offsetY = (sizeFrame.height * 4) / 10;
//            final int sizeROI2 = (sizeFrame.height * 2) / 10;
//            double factors[] = ScanFruitsSDK.processGetWhiteBalanceROI(mPreview.mFrameRGB3, mPreview.sizeFrame.width, mPreview.sizeFrame.height, offsetX, offsetY, offsetX + sizeROI2, offsetY + sizeROI2);
//            String result2 = String.format("Factors = R(%.3f), G(%.3f), B(%.3f)", factors[0], factors[1], factors[2]);
//            Log.d("ScanFruits", result2);
//
//            ScanFruitsSDK.processSetWhiteBalance(factors[0], factors[1], factors[2]);
//            grayInit = true;
//        }



        // Convert YUV420sp data to BGRA data
        ScanFruitsSDK.convertYUV420sp2BGRA(data, sizeFrame.width, sizeFrame.height, mPreview.mFrameRGB3);
        mPreview.mFrameRGB3.rewind();

        // Debug API
        final int offset = (sizeFrame.width - sizeFrame.height) / 2;
        final int sizeROI = sizeFrame.height;
        String result = ScanFruitsSDK.processImageROI(mPreview.mFrameRGB3, mPreview.sizeFrame.width, mPreview.sizeFrame.height,
                offset, 0, offset + sizeROI, sizeROI);

        Log.d("ScanFruits", result);
        json = result;

        ArrayList<ScanResult> results = ResultFormat.stringToScanResultArray(result);
        StringBuilder redMessage = new StringBuilder();

        for (ScanResult detectedFruit : results)
        {
            int idNomProduit = getActivity().getResources().getIdentifier(detectedFruit.getName().toLowerCase(), "string", getActivity().getPackageName());

            if(idNomProduit != 0)
                redMessage.append(getActivity().getString(idNomProduit) + " " + getActivity().getString(R.string.maturity) + " " + detectedFruit.getMaturity() + " : " + ((int)(detectedFruit.getConvidence() * 100)) + "% " + "\n");
            else
                redMessage.append(detectedFruit.getName() + " " + getActivity().getString(R.string.maturity) + " " + detectedFruit.getMaturity() + " : " + ((int)(detectedFruit.getConvidence() * 100)) + "% " + "\n");

        }

        Drawing.getInstance().viewLayer.message = redMessage.toString();
        Drawing.getInstance().viewLayer.invalidate();//write message on picture
    }

    public void onPictureTaken(final byte[] data, Camera camera) {
        // Stop Preview...
        mCamera.stopPreview();
        // Create Picture...
    }

    /****************************/


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_picture, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button back = getActivity().findViewById(R.id.buttonReturn);
        back.setVisibility(View.INVISIBLE);

        /*
         * part for camera2 api version
         */
        Button picture = view.findViewById(R.id.picture);
        pictureFragmentCallback = (PictureFragmentCallback) getActivity();
        /*textureView = view.findViewById(R.id.previsualisation);
        textureView.setSurfaceTextureListener(textureListener);
        final SeekBar balance = view.findViewById(R.id.balance);

        preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        */

        /*balance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int j = (-12 + (i / 4));
                preferences.edit().putInt("white", j).apply();
                builder.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, j);
                try {
                    session.setRepeatingRequest(builder.build(), null, null);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //nothing
            }
        });*/

        /* show or mask the seedbar for the white  */
        /*rl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int pointerIndex = ((motionEvent.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT);
                int pointCnt = motionEvent.getPointerCount();

                if ((pointCnt == 3) && (pointerIndex <= 1)) {
                    if (balance.getVisibility() == View.VISIBLE) {
                        balance.setVisibility(View.INVISIBLE);
                    } else {
                        balance.setVisibility(View.VISIBLE);
                    }
                }

                return true;
            }
        });*/


        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (etat.equals(EtatEnum.ASSISTANCE)) { //old assistance feature
                if (etat.equals(EtatEnum.CONDITION)) {
                    //takePicture();
                    pictureFragmentCallback.onPictureResult(true, json);
                } else {
                    pictureFragmentCallback.onPictureResult(true, "contenu");
                }
            }
        });

        /************************/

        // Debug Load Library
        String processInitOk = String.format("ScanFruitsSDK.processIsCreated() = %s", ScanFruitsSDK.processIsCreated() ? "TRUE" : "FALSE");
        Log.d("ScanFruits", processInitOk);

       /* RelativeLayout rl = view.findViewById(R.id.rlPicture);
        balance.setProgress((12 + preferences.getInt("white", 0)) * 4);
        balance.setMax(99);*/

        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Preview camera
        mPreview = (Preview) view.findViewById(R.id.camera_preview);
        mPreview.setOnClickListener(this);

        // Draw views
        Drawing.getInstance().viewLayer = (DrawLayer) view.findViewById(R.id.camera_drawlayer);

        // Initialize Variables
        sizeFrame = null;
        sizePicture = null;


        /* Indicates the state of the app when calling this fragment */
        if (getArguments() != null) {
            Bundle args = getArguments();
            if (args.containsKey(getString(R.string.etat))) {
                etat = (EtatEnum) args.get(getString(R.string.etat));
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof PictureFragmentCallback) {
            pictureFragmentCallback = (PictureFragmentCallback) context;
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
       /* closeCamera();
        pictureFragmentCallback = null;*/
    }

    @Override
    public void onAutoFocus(boolean b, Camera camera) {

    }

    @Override
    public void onShutter() {

    }

    /*
     * interface for communication with parent of this fragment
     */
    public interface PictureFragmentCallback {
        void onPictureResult(boolean valid, String result);
    }
}
