//////////////////////////////////////////////////////////////////////////
package ffscreens.arthylene.show;


//////////////////////////////////////////////////////////////////////////
// DRAWING
// -----------------------------------------------------------------------

/**
 * Clase global para dibujar informacion en una vista.
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
public class Drawing {
    // ---------------------------------------------------------------
    // ATTRIBUTES
    // ---------------------------------------------------------------
    private static Drawing mInstance = null;
    public DrawLayer viewLayer;


    // ===================================================================
    // DRAWING
    // -------------------------------------------------------------------

    /**
     * Constructor de la clase 'Drawing'.
     */
    // ===================================================================
    protected Drawing() {
    }
    // Drawing


    // ===================================================================
    // GET_INSTANCE
    // -------------------------------------------------------------------

    /**
     * Funcion que obtiene la instancia unica de la clase 'GlobalVar'.
     *
     * @return Instancia 'GlobalVar'.
     */
    // ===================================================================
    public static synchronized Drawing getInstance() {
        if (mInstance == null)
            mInstance = new Drawing();

        return mInstance;
    }
    // getInstance
}
// Drawing
