package tema;

public class FabricaTemaClaro implements FabricaTema {

    @Override
    public EstiloBoton crearEstiloBoton() {
        return new EstiloBotonClaro();
    }

    @Override
    public EstiloPanel crearEstiloPanel() {
        return new EstiloPanelClaro();
    }
}