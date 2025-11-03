package org.example.uam_fia.acciones;

import org.openxava.actions.*;
import org.openxava.model.MapFacade;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class OnChangeProductoDetalle extends OnChangePropertyBaseAction {

    @Override
    public void execute() throws Exception {
        Object productoId = getNewValue();
        if (productoId != null) {
            try {
                Map<String, Object> keyValues = new HashMap<String, Object>();
                keyValues.put("id", productoId);
                Map<String, Object> propertiesToGet = new HashMap<String, Object>();
                Map<String, Object> productoValues = (Map<String, Object>) MapFacade.getValues("Producto", keyValues, propertiesToGet);
                Object precio = productoValues.get("precio");
                if (precio != null) {
                    BigDecimal precioProducto = precio instanceof BigDecimal 
                        ? (BigDecimal) precio 
                        : new BigDecimal(precio.toString());
                    getView().setValue("precioUnitario", precioProducto);
                }
            } catch (Exception e) {
            }
        }
    }
}
