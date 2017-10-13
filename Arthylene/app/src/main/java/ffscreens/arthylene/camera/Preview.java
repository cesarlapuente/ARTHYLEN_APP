//////////////////////////////////////////////////////////////////////////
package ffscreens.arthylene.camera;


//////////////////////////////////////////////////////////////////////////

import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.List;


//////////////////////////////////////////////////////////////////////////
// PREVIEW
// -----------------------------------------------------------------------

/**
 * Clase para realizar un Previsualizacion de la Camara.
 *
 * @version 1.0.0 (1 de Junio de 2017)
 * @author Miguel Vinyas <miguel@cvc.uab.es>
 * <p>
 * Centro de Vision por Computador
 * Edifici O - Universitat Autonoma de Barcelona.
 * 08193 Cerdanyola del Valles, Barcelona, (SPAIN).
 * Tel. +(34) 93.581.18.28
 * Fax. +(34) 93.581.16.70
 */
//////////////////////////////////////////////////////////////////////////
public class Preview extends SurfaceView implements SurfaceHolder.Callback {
    public ByteBuffer mFrameRGB1;
    public IntBuffer mFrameRGB3;
    public Size sizeFrame;
    public Size sizePreview;
    public Size sizePicture;
    // ---------------------------------------------------------------
    // ATTRIBUTES
    // ---------------------------------------------------------------
    protected SurfaceHolder mHolder;
    protected Camera mCamera;


    // ===================================================================
    // PREVIEW
    // -------------------------------------------------------------------

    /**
     * Constructor de la clase Preview.
     *
     * @param context Contexto en el que se encuentra.
     * @param attrs   Atributos para el constructor.
     */
    // ===================================================================
    public Preview(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Initialize Surface holder
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }
    // Preview


    // ===================================================================
    // SET_CAMERA
    // -------------------------------------------------------------------

    /**
     * Funcion que asocia una camara al Preview.
     *
     * @paran camera        Camara para realizar el preview.
     * @paran sizeFrame        Tamaño del frame del preview.
     * @paran sizePicturte    Tamaño de la foto que se adquiere.
     */
    // ===================================================================
    public void setCamera(Camera camera, Size sizeFrame, Size sizePicture) {
        // Initialize camera
        mCamera = camera;

        // Sizes
        this.sizeFrame = sizeFrame;
        this.sizePicture = sizePicture;
    }
    // setCamera


    // ===================================================================
    // SURFACE_CREATED
    // -------------------------------------------------------------------

    /**
     * Funcion que gestiona la creacion del Surface.
     *
     * @param holder Surface holder del preview.
     */
    // ===================================================================
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            // Check Camera object
            if (mCamera == null) return;

            // Set preview display
            mCamera.setPreviewDisplay(holder);

            // Allocate frame buffer
            mFrameRGB1 = ByteBuffer.allocateDirect((sizeFrame.width * sizeFrame.height) << 2);
            mFrameRGB1.order(ByteOrder.LITTLE_ENDIAN);
            mFrameRGB3 = mFrameRGB1.asIntBuffer();
        } catch (IOException exception) {
        }
    }
    // surfaceCreated


    // ===================================================================
    // SURFACE_DESTROYED
    // -------------------------------------------------------------------

    /**
     * Funcion que gestiona la destruccion del Surface.
     *
     * @param holder Surface holder del preview.
     */
    // ===================================================================
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Check Camera object
        if (mCamera == null) return;

        // Surface will be destroyed when we return, so stop the preview.
        // Because the Camera object is not a shared resource, it's very
        // important to release it when the activity is paused.
        mCamera.setPreviewCallback(null);
        mCamera.stopPreview();
    }
    // surfaceDestroyed


    // ===================================================================
    // SURFACE_CHANGED
    // -------------------------------------------------------------------

    /**
     * Funcion que gestiona los cambios del Surface.
     *
     * @param holder Surface holder del preview.
     * @param format Formato del preview.
     * @param width  Ancho del preview.
     * @param height Alto del preview.
     */
    // ===================================================================
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Check Camera object
        if (mCamera == null) return;

        // Get the best preview layout
        sizePreview = getBestPreviewSize(width, height, sizeFrame.width, sizeFrame.height);
        //sizePreview = mCamera.new Size(480, 640);
        setPreviewLayout(sizePreview.width, sizePreview.height);

        // Change camera parameters
        Camera.Parameters params = mCamera.getParameters();
        params.setPreviewSize(sizeFrame.width, sizeFrame.height);

        // Preview Format
        List<Integer> previewSupported = params.getSupportedPreviewFormats();
        if ((previewSupported != null) && (previewSupported.isEmpty() == false))
            params.setPreviewFormat(getSupportedValue(previewSupported, ImageFormat.NV21));

        // Picture Format
        List<Integer> pictureSupported = params.getSupportedPictureFormats();
        if ((pictureSupported != null) && (pictureSupported.isEmpty() == false))
            params.setPictureFormat(getSupportedValue(pictureSupported, ImageFormat.JPEG));

        // Scene Mode
        List<String> sceneSupported = params.getSupportedSceneModes();
        if ((sceneSupported != null) && (sceneSupported.isEmpty() == false))
            params.setSceneMode(getSupportedValue(sceneSupported, Camera.Parameters.SCENE_MODE_AUTO));

        // Autofocus
        List<String> focusSupported = params.getSupportedFocusModes();
        if ((focusSupported != null) && (focusSupported.isEmpty() == false))
            params.setFocusMode(getSupportedValue(focusSupported, Camera.Parameters.FOCUS_MODE_AUTO));

        // White Balance
        List<String> wbSupported = params.getSupportedWhiteBalance();
        if ((wbSupported != null) && (wbSupported.isEmpty() == false))
            params.setWhiteBalance(getSupportedValue(wbSupported, Camera.Parameters.WHITE_BALANCE_FLUORESCENT));
//            params.setWhiteBalance(getSupportedValue(wbSupported, Camera.Parameters.WHITE_BALANCE_DAYLIGHT));


        // Frame rate
        List<Integer> fpsSupported = params.getSupportedPreviewFrameRates();
        if ((fpsSupported != null) && (fpsSupported.isEmpty() == false))
            params.setPreviewFrameRate(getSupportedValue(fpsSupported, 30));


        System.out.println("white balances!! " + params.getSupportedWhiteBalance());
        mCamera.setParameters(params);
        mCamera.startPreview();
    }
    // surfaceChanged


    // ===================================================================
    // GET_BEST_CAMERA_SIZE
    // -------------------------------------------------------------------

    /**
     * Funcion que devuelve el tamaño valido que mas se parece al tamaño
     * requerido.
     *
     * @param sizes  Lista de tamaños soportados por el dispositivo.
     * @param width  Ancho de la camara.
     * @param height Alto de la camara.
     * @return Tamaño valido que mas se parece al requerido.
     */
    // ===================================================================
    public Size getBestCameraSize(List<Size> sizes, int width, int height) {
        // Find similar compatible sizes for device
        double minDiffX = Double.MAX_VALUE;
        double minDiffY = Double.MAX_VALUE;
        Size sizeOut = null;

        for (Size size : sizes) {
            if ((Math.abs(size.width - width) <= minDiffX) && (Math.abs(size.height - height) <= minDiffY)) {
                sizeOut = size;
                minDiffX = Math.abs(size.width - width);
                minDiffY = Math.abs(size.height - height);
            }
        }
        return sizeOut;
    }
    // getBestCameraSize


    // ===================================================================
    // GET_BEST_PREVIEW_SIZE
    // -------------------------------------------------------------------

    /**
     * Funcion que devuelve el tamaño maximo del preview en el dispositivo,
     * manteniendo el 'aspect ratio'.
     *
     * @param deviceWidth   Ancho del dispositivo.
     * @param deviceHeight  Alto del dispositivo.
     * @param previewWidth  Ancho del preview.
     * @param previewHeight Alto del preview.
     * @return Tamaño maximo del preview en el dispositivo.
     */
    // ===================================================================
    public Size getBestPreviewSize(int deviceWidth, int deviceHeight,
                                   int previewWidth, int previewHeight) {
        // Calculate aspect ratio
        double deviceRatio = (double) deviceWidth / (double) deviceHeight;
        double previewRatio = (double) previewWidth / (double) previewHeight;
        int width;
        int height;

        // Calculate maximize preview
        Size sizeOut = null;
        sizeOut = mCamera.new Size(0, 0);

        if (deviceRatio >= previewRatio) {
            width = (int) Math.round((double) deviceHeight * previewRatio);
            height = deviceHeight;
        } else {
            width = deviceWidth;
            height = (int) Math.round((double) deviceWidth / previewRatio);
        }
        sizeOut = mCamera.new Size(1200, 1600);

        return sizeOut;
    }
    // getBestPreviewSize


    // ===================================================================
    // GET_SUPPORTED_VALUE
    // ===================================================================

    /**
     * Funcion que obtiene los valores soportados.
     *
     * @param modes Devuelve los modos soportados.
     * @param value Valor requerido.
     * @return Devuelve el valor requerido o el primero por defecto.
     */
    // ===================================================================
    public String getSupportedValue(List<String> modes, String value) {
        if (modes.indexOf(value) == -1)
            value = modes.get(0);

        return value;
    }
    // getSupportedValue


    // ===================================================================
    // GET_SUPPORTED_VALUE
    // ===================================================================

    /**
     * Funcion que obtiene los valores soportados.
     *
     * @param modes Devuelve los modos soportados.
     * @param value Valor requerido.
     * @return Devuelve el valor requerido o el primero por defecto.
     */
    // ===================================================================
    public Integer getSupportedValue(List<Integer> modes, Integer value) {
        if (modes.indexOf(value) == -1)
            value = modes.get(0);

        return value;
    }
    // getSupportedValue


    // ===================================================================
    // SET_PREVIEW_LAYOUT
    // -------------------------------------------------------------------

    /**
     * Funcion que centra el Preview en el Layout.
     *
     * @param width  Ancho del preview.
     * @param height Alto del preview.
     */
    // ===================================================================
    protected void setPreviewLayout(int width, int height) {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = width;
        params.height = height;

        // Linear Layout...
        if (params.getClass() == LinearLayout.LayoutParams.class) {
            ((LinearLayout.LayoutParams) params).gravity = Gravity.CENTER_VERTICAL |
                    Gravity.CENTER_HORIZONTAL;
        }
        // Frame Layout...
        else if (params.getClass() == FrameLayout.LayoutParams.class) {
            ((FrameLayout.LayoutParams) params).gravity = Gravity.CENTER_VERTICAL |
                    Gravity.CENTER_HORIZONTAL;
        }
        // Relative Layout...
        else if (params.getClass() == RelativeLayout.LayoutParams.class) {
            ((RelativeLayout.LayoutParams) params).addRule(RelativeLayout.CENTER_VERTICAL |
                    RelativeLayout.CENTER_HORIZONTAL);
        }

        setLayoutParams(params);
    }
    // setPreviewLayout
}
// Preview

