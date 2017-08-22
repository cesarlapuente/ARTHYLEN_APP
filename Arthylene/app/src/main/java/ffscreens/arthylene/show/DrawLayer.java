//////////////////////////////////////////////////////////////////////////
package ffscreens.arthylene.show;


//////////////////////////////////////////////////////////////////////

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


//////////////////////////////////////////////////////////////////////////
// DRAW_LAYER
// -----------------------------------------------------------------------

/**
 * Clase que pinta en una vista.
 *
 * @version 1.0.0 (8 de Junio de 2017)
 * @author Miguel Vinyas <miguel@cvc.uab.es>
 * <p>
 * Centro de Vision por Computador
 * Edifici O - Universitat Autonoma de Barcelona.
 * 08193 Cerdanyola del Valles, Barcelona, (SPAIN).
 * Tel. +(34) 93.581.18.28
 * Fax. +(34) 93.581.16.70
 */
//////////////////////////////////////////////////////////////////////////
public class DrawLayer extends View {
    public String message;
    // ---------------------------------------------------------------
    // ATTRIBUTES
    // ---------------------------------------------------------------
    protected Paint paintBorder;
    protected Paint paintMessage;


    // ===================================================================
    // DRAW_LAYER
    // -------------------------------------------------------------------

    /**
     * Constructor de la clase 'DrawLayer'.
     *
     * @param context Contexto en el que se encuentra.
     * @param attrs   Atributos para el constructor.
     */
    // ===================================================================
    public DrawLayer(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintBorder = new Paint();
        paintBorder.setColor(Color.BLACK);
        paintBorder.setStyle(Paint.Style.FILL);
        paintBorder.setStrokeWidth(0);

        paintMessage = new Paint();
        paintMessage.setColor(Color.RED);
        paintMessage.setStyle(Paint.Style.FILL);
        paintMessage.setFlags(Paint.ANTI_ALIAS_FLAG);
        paintMessage.setTextAlign(Align.LEFT);
        paintMessage.setTextSize(40);

        message = new String();
    }
    // DrawLayer


    // ===================================================================
    // ON_DRAW
    // -------------------------------------------------------------------

    /**
     * Funcion que se ejecuta para pintar la Vista.
     *
     * @param canvas Canvas de la Vista.
     */
    // ===================================================================
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Make borders opaque
        final int borderWidth = (canvas.getWidth() - canvas.getHeight()) / 2;
        RectF borderLeft = new RectF(0, 0, borderWidth, canvas.getHeight());
        RectF borderRight = new RectF(canvas.getWidth() - borderWidth, 0, canvas.getWidth(), canvas.getHeight());

        canvas.drawRect(borderLeft, paintBorder);
        canvas.drawRect(borderRight, paintBorder);

        // Draw messsage
        Rect r = new Rect();
        paintMessage.getTextBounds(message, 0,message.length(),r );
        int posx = 10;
        int posy = canvas.getHeight()- r.height()*10;


        for (String messageLine : message.split("\n")) {
            canvas.drawText(messageLine, posx, posy, paintMessage);
            posy += paintMessage.descent() - paintMessage.ascent();
        }
    }
    // onDraw	
}
// DrawLayer

