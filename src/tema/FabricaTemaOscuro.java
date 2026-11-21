package tema;

public class FabricaTemaOscuro implements FabricaTema {

    @Override
    public EstiloBoton crearEstiloBoton() {
        return new EstiloBotonOscuro();
    }

    @Override
    public EstiloPanel crearEstiloPanel() {
        return new EstiloPanelOscuro();
    }
}