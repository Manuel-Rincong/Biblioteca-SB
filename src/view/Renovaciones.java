package view;

public class Renovaciones extends javax.swing.JFrame {

    private java.awt.event.ActionListener accionRenovar;
    private java.awt.event.ActionListener accionConsultar;
    private java.awt.event.ActionListener accionSeleccion;

    public Renovaciones() {
        initComponents();
        limpiarDetalle();
        tblRenovaciones.getTableHeader().setReorderingAllowed(false);
        tblRenovaciones.getTableHeader().setFont(new java.awt.Font("SansSerif", 1, 12));
        tblRenovaciones.getColumnModel().getColumn(0).setPreferredWidth(46);
        tblRenovaciones.getColumnModel().getColumn(1).setPreferredWidth(64);
        tblRenovaciones.getColumnModel().getColumn(2).setPreferredWidth(140);
        tblRenovaciones.getColumnModel().getColumn(3).setPreferredWidth(140);
        tblRenovaciones.getColumnModel().getColumn(4).setPreferredWidth(160);
        setLocationRelativeTo(null);
    }

    public void configurarAcciones(java.awt.event.ActionListener renovar,
                                   java.awt.event.ActionListener consultar) {
        accionRenovar = renovar;
        accionConsultar = consultar;
        btnRenovar.setEnabled(false);
        btnConsultar.setEnabled(consultar != null);
    }

    public void configurarCambioSeleccion(java.awt.event.ActionListener accion) {
        accionSeleccion = accion;
    }

    public void habilitarRenovacion(boolean habilitar) {
        btnRenovar.setEnabled(habilitar && accionRenovar != null);
    }

    public int getIndicePrestamo() {
        int posicion = cmbPrestamos.getSelectedIndex();
        return posicion > 0 ? posicion - 1 : -1;
    }

    public void cargarPrestamos(String[] descripciones) {
        cmbPrestamos.removeAllItems();
        cmbPrestamos.addItem("Seleccione un préstamo");
        for (String descripcion : descripciones) {
            cmbPrestamos.addItem(descripcion);
        }
        cmbPrestamos.setSelectedIndex(0);
    }

    public void mostrarDatosPrestamo(String usuario, String recurso,
            java.time.LocalDate fechaLimiteActual, int realizadas, int maximas) {
        txtUsuario.setText(usuario);
        txtRecurso.setText(recurso);
        txtFechaLimiteActual.setText(fechaLimiteActual == null ? "" : fechaLimiteActual.toString());
        txtRenovacionesRealizadas.setText(String.valueOf(realizadas));
        txtMaxRenovaciones.setText(String.valueOf(maximas));
    }

    public void mostrarNuevaFechaLimite(java.time.LocalDate fecha) {
        txtNuevaFechaLimite.setText(fecha == null ? "" : fecha.toString());
    }

    public void mostrarRenovaciones(Object[][] filas) {
        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel) tblRenovaciones.getModel();
        modelo.setRowCount(0);
        for (Object[] fila : filas) {
            modelo.addRow(fila);
        }
    }

    public void mostrarEstado(String mensaje) {
        lblEstado.setText(mensaje);
    }

    public void limpiarFormulario() {
        cmbPrestamos.setSelectedIndex(0);
        limpiarDetalle();
        lblEstado.setText("Formulario limpio.");
        cmbPrestamos.requestFocusInWindow();
    }

    private void limpiarDetalle() {
        txtUsuario.setText("");
        txtRecurso.setText("");
        txtFechaLimiteActual.setText("");
        txtNuevaFechaLimite.setText("");
        txtRenovacionesRealizadas.setText("");
        txtMaxRenovaciones.setText("");
        habilitarRenovacion(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEncabezado = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        pnlContenido = new javax.swing.JPanel();
        pnlFormulario = new javax.swing.JPanel();
        lblDatos = new javax.swing.JLabel();
        pnlCampos = new javax.swing.JPanel();
        pnlSeleccion = new javax.swing.JPanel();
        lblPrestamo = new javax.swing.JLabel();
        cmbPrestamos = new javax.swing.JComboBox<>();
        pnlDetalle = new javax.swing.JPanel();
        pnlUsuario = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        pnlRecurso = new javax.swing.JPanel();
        lblRecurso = new javax.swing.JLabel();
        txtRecurso = new javax.swing.JTextField();
        pnlFechas = new javax.swing.JPanel();
        pnlFechaActual = new javax.swing.JPanel();
        lblFechaActual = new javax.swing.JLabel();
        txtFechaLimiteActual = new javax.swing.JTextField();
        pnlNuevaFecha = new javax.swing.JPanel();
        lblNuevaFecha = new javax.swing.JLabel();
        txtNuevaFechaLimite = new javax.swing.JTextField();
        pnlLimites = new javax.swing.JPanel();
        pnlRealizadas = new javax.swing.JPanel();
        lblRealizadas = new javax.swing.JLabel();
        txtRenovacionesRealizadas = new javax.swing.JTextField();
        pnlMaximas = new javax.swing.JPanel();
        lblMaximas = new javax.swing.JLabel();
        txtMaxRenovaciones = new javax.swing.JTextField();
        pnlAcciones = new javax.swing.JPanel();
        btnRenovar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        pnlListado = new javax.swing.JPanel();
        pnlCabeceraTabla = new javax.swing.JPanel();
        lblListado = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        scrRenovaciones = new javax.swing.JScrollPane();
        tblRenovaciones = new javax.swing.JTable();
        lblAyudaTabla = new javax.swing.JLabel();
        pnlPie = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Biblioteca | Gestión de renovaciones");
        setMinimumSize(new java.awt.Dimension(1000, 680));
        setPreferredSize(new java.awt.Dimension(1100, 700));

        pnlEncabezado.setBackground(new java.awt.Color(107, 63, 42));
        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 26, 18, 26));
        pnlEncabezado.setLayout(new java.awt.BorderLayout(0, 8));

        lblTitulo.setText("GESTIÓN DE RENOVACIONES");
        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 25)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        pnlEncabezado.add(lblTitulo, java.awt.BorderLayout.NORTH);

        lblSubtitulo.setText("Ampliación del plazo de préstamo | Sistema de biblioteca");
        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(245, 226, 210));
        pnlEncabezado.add(lblSubtitulo, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlEncabezado, java.awt.BorderLayout.NORTH);

        pnlContenido.setBackground(new java.awt.Color(251, 247, 242));
        pnlContenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 26, 18, 26));
        pnlContenido.setLayout(new java.awt.BorderLayout(20, 0));

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormulario.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 22, 22, 22));
        pnlFormulario.setPreferredSize(new java.awt.Dimension(360, 440));
        pnlFormulario.setLayout(new java.awt.BorderLayout(0, 14));

        lblDatos.setText("Renovar un préstamo");
        lblDatos.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblDatos.setForeground(new java.awt.Color(143, 72, 37));
        pnlFormulario.add(lblDatos, java.awt.BorderLayout.NORTH);

        pnlCampos.setOpaque(false);
        pnlCampos.setLayout(new java.awt.BorderLayout(0, 14));

        pnlSeleccion.setOpaque(false);
        pnlSeleccion.setPreferredSize(new java.awt.Dimension(316, 56));
        pnlSeleccion.setLayout(new java.awt.BorderLayout(0, 6));

        lblPrestamo.setText("Préstamo activo");
        lblPrestamo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblPrestamo.setForeground(new java.awt.Color(65, 48, 39));
        pnlSeleccion.add(lblPrestamo, java.awt.BorderLayout.NORTH);

        cmbPrestamos.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cmbPrestamos.setForeground(new java.awt.Color(107, 63, 42));
        cmbPrestamos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un préstamo" }));
        cmbPrestamos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPrestamosActionPerformed(evt);
            }
        });
        pnlSeleccion.add(cmbPrestamos, java.awt.BorderLayout.CENTER);

        pnlCampos.add(pnlSeleccion, java.awt.BorderLayout.NORTH);

        pnlDetalle.setOpaque(false);
        pnlDetalle.setLayout(new java.awt.GridLayout(4, 1, 0, 8));

        pnlUsuario.setOpaque(false);
        pnlUsuario.setLayout(new java.awt.BorderLayout(0, 4));

        lblUsuario.setText("Usuario");
        lblUsuario.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(65, 48, 39));
        pnlUsuario.add(lblUsuario, java.awt.BorderLayout.NORTH);

        txtUsuario.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(107, 63, 42));
        txtUsuario.setBackground(new java.awt.Color(251, 247, 242));
        txtUsuario.setEditable(false);
        txtUsuario.setColumns(10);
        txtUsuario.setMargin(new java.awt.Insets(2, 7, 2, 7));
        pnlUsuario.add(txtUsuario, java.awt.BorderLayout.CENTER);

        pnlDetalle.add(pnlUsuario);

        pnlRecurso.setOpaque(false);
        pnlRecurso.setLayout(new java.awt.BorderLayout(0, 4));

        lblRecurso.setText("Recurso");
        lblRecurso.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblRecurso.setForeground(new java.awt.Color(65, 48, 39));
        pnlRecurso.add(lblRecurso, java.awt.BorderLayout.NORTH);

        txtRecurso.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtRecurso.setForeground(new java.awt.Color(107, 63, 42));
        txtRecurso.setBackground(new java.awt.Color(251, 247, 242));
        txtRecurso.setEditable(false);
        txtRecurso.setColumns(10);
        txtRecurso.setMargin(new java.awt.Insets(2, 7, 2, 7));
        pnlRecurso.add(txtRecurso, java.awt.BorderLayout.CENTER);

        pnlDetalle.add(pnlRecurso);

        pnlFechas.setOpaque(false);
        pnlFechas.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        pnlFechaActual.setOpaque(false);
        pnlFechaActual.setLayout(new java.awt.BorderLayout(0, 4));

        lblFechaActual.setText("Fecha límite actual");
        lblFechaActual.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblFechaActual.setForeground(new java.awt.Color(65, 48, 39));
        pnlFechaActual.add(lblFechaActual, java.awt.BorderLayout.NORTH);

        txtFechaLimiteActual.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtFechaLimiteActual.setForeground(new java.awt.Color(107, 63, 42));
        txtFechaLimiteActual.setBackground(new java.awt.Color(251, 247, 242));
        txtFechaLimiteActual.setEditable(false);
        txtFechaLimiteActual.setColumns(10);
        txtFechaLimiteActual.setMargin(new java.awt.Insets(2, 7, 2, 7));
        pnlFechaActual.add(txtFechaLimiteActual, java.awt.BorderLayout.CENTER);

        pnlFechas.add(pnlFechaActual);

        pnlNuevaFecha.setOpaque(false);
        pnlNuevaFecha.setLayout(new java.awt.BorderLayout(0, 4));

        lblNuevaFecha.setText("Nueva fecha límite");
        lblNuevaFecha.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblNuevaFecha.setForeground(new java.awt.Color(65, 48, 39));
        pnlNuevaFecha.add(lblNuevaFecha, java.awt.BorderLayout.NORTH);

        txtNuevaFechaLimite.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtNuevaFechaLimite.setForeground(new java.awt.Color(107, 63, 42));
        txtNuevaFechaLimite.setBackground(new java.awt.Color(251, 247, 242));
        txtNuevaFechaLimite.setEditable(false);
        txtNuevaFechaLimite.setColumns(10);
        txtNuevaFechaLimite.setMargin(new java.awt.Insets(2, 7, 2, 7));
        pnlNuevaFecha.add(txtNuevaFechaLimite, java.awt.BorderLayout.CENTER);

        pnlFechas.add(pnlNuevaFecha);

        pnlDetalle.add(pnlFechas);

        pnlLimites.setOpaque(false);
        pnlLimites.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        pnlRealizadas.setOpaque(false);
        pnlRealizadas.setLayout(new java.awt.BorderLayout(0, 4));

        lblRealizadas.setText("Renovaciones realizadas");
        lblRealizadas.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblRealizadas.setForeground(new java.awt.Color(65, 48, 39));
        pnlRealizadas.add(lblRealizadas, java.awt.BorderLayout.NORTH);

        txtRenovacionesRealizadas.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtRenovacionesRealizadas.setForeground(new java.awt.Color(107, 63, 42));
        txtRenovacionesRealizadas.setBackground(new java.awt.Color(251, 247, 242));
        txtRenovacionesRealizadas.setEditable(false);
        txtRenovacionesRealizadas.setColumns(10);
        txtRenovacionesRealizadas.setMargin(new java.awt.Insets(2, 7, 2, 7));
        pnlRealizadas.add(txtRenovacionesRealizadas, java.awt.BorderLayout.CENTER);

        pnlLimites.add(pnlRealizadas);

        pnlMaximas.setOpaque(false);
        pnlMaximas.setLayout(new java.awt.BorderLayout(0, 4));

        lblMaximas.setText("Límite de renovaciones");
        lblMaximas.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblMaximas.setForeground(new java.awt.Color(65, 48, 39));
        pnlMaximas.add(lblMaximas, java.awt.BorderLayout.NORTH);

        txtMaxRenovaciones.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtMaxRenovaciones.setForeground(new java.awt.Color(107, 63, 42));
        txtMaxRenovaciones.setBackground(new java.awt.Color(251, 247, 242));
        txtMaxRenovaciones.setEditable(false);
        txtMaxRenovaciones.setColumns(10);
        txtMaxRenovaciones.setMargin(new java.awt.Insets(2, 7, 2, 7));
        pnlMaximas.add(txtMaxRenovaciones, java.awt.BorderLayout.CENTER);

        pnlLimites.add(pnlMaximas);

        pnlDetalle.add(pnlLimites);

        pnlCampos.add(pnlDetalle, java.awt.BorderLayout.CENTER);

        pnlFormulario.add(pnlCampos, java.awt.BorderLayout.CENTER);

        pnlAcciones.setOpaque(false);
        pnlAcciones.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        btnRenovar.setText("Renovar");
        btnRenovar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnRenovar.setBackground(new java.awt.Color(245, 232, 216));
        btnRenovar.setForeground(new java.awt.Color(107, 63, 42));
        btnRenovar.setEnabled(false);
        btnRenovar.setFocusPainted(false);
        btnRenovar.setContentAreaFilled(false);
        btnRenovar.setOpaque(true);
        btnRenovar.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 16, 9, 16));
        btnRenovar.setPreferredSize(new java.awt.Dimension(112, 38));
        btnRenovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenovarActionPerformed(evt);
            }
        });
        pnlAcciones.add(btnRenovar);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLimpiar.setBackground(new java.awt.Color(245, 232, 216));
        btnLimpiar.setForeground(new java.awt.Color(107, 63, 42));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.setOpaque(true);
        btnLimpiar.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 16, 9, 16));
        btnLimpiar.setPreferredSize(new java.awt.Dimension(112, 38));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        pnlAcciones.add(btnLimpiar);

        pnlFormulario.add(pnlAcciones, java.awt.BorderLayout.SOUTH);

        pnlContenido.add(pnlFormulario, java.awt.BorderLayout.WEST);

        pnlListado.setBackground(new java.awt.Color(255, 255, 255));
        pnlListado.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlListado.setLayout(new java.awt.BorderLayout(0, 16));

        pnlCabeceraTabla.setOpaque(false);
        pnlCabeceraTabla.setLayout(new java.awt.BorderLayout(12, 0));

        lblListado.setText("Historial de renovaciones");
        lblListado.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblListado.setForeground(new java.awt.Color(143, 72, 37));
        pnlCabeceraTabla.add(lblListado, java.awt.BorderLayout.CENTER);

        btnConsultar.setText("Consultar");
        btnConsultar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnConsultar.setBackground(new java.awt.Color(245, 232, 216));
        btnConsultar.setForeground(new java.awt.Color(107, 63, 42));
        btnConsultar.setEnabled(false);
        btnConsultar.setFocusPainted(false);
        btnConsultar.setContentAreaFilled(false);
        btnConsultar.setOpaque(true);
        btnConsultar.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 16, 9, 16));
        btnConsultar.setPreferredSize(new java.awt.Dimension(112, 38));
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        pnlCabeceraTabla.add(btnConsultar, java.awt.BorderLayout.EAST);

        pnlListado.add(pnlCabeceraTabla, java.awt.BorderLayout.NORTH);

        scrRenovaciones.setPreferredSize(new java.awt.Dimension(600, 320));

        tblRenovaciones.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        tblRenovaciones.setForeground(new java.awt.Color(65, 48, 39));
        tblRenovaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Préstamo", "Límite anterior", "Nuevo límite", "Fecha renovación"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRenovaciones.setRowHeight(28);
        tblRenovaciones.setFillsViewportHeight(true);
        tblRenovaciones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblRenovaciones.setSelectionBackground(new java.awt.Color(245, 226, 210));
        tblRenovaciones.setSelectionForeground(new java.awt.Color(107, 63, 42));
        tblRenovaciones.setGridColor(new java.awt.Color(235, 224, 212));
        scrRenovaciones.setViewportView(tblRenovaciones);

        pnlListado.add(scrRenovaciones, java.awt.BorderLayout.CENTER);

        lblAyudaTabla.setText("Consulta los cambios de fecha registrados en cada préstamo.");
        lblAyudaTabla.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblAyudaTabla.setForeground(new java.awt.Color(65, 48, 39));
        pnlListado.add(lblAyudaTabla, java.awt.BorderLayout.SOUTH);

        pnlContenido.add(pnlListado, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlContenido, java.awt.BorderLayout.CENTER);

        pnlPie.setBackground(new java.awt.Color(251, 247, 242));
        pnlPie.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 26, 18, 26));
        pnlPie.setLayout(new java.awt.BorderLayout(20, 0));

        lblEstado.setText("Selecciona un préstamo para consultar las condiciones de renovación.");
        lblEstado.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(65, 48, 39));
        pnlPie.add(lblEstado, java.awt.BorderLayout.CENTER);

        btnCerrar.setText("Cerrar");
        btnCerrar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCerrar.setBackground(new java.awt.Color(245, 232, 216));
        btnCerrar.setForeground(new java.awt.Color(107, 63, 42));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setOpaque(true);
        btnCerrar.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 16, 9, 16));
        btnCerrar.setPreferredSize(new java.awt.Dimension(112, 38));
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        pnlPie.add(btnCerrar, java.awt.BorderLayout.EAST);

        getContentPane().add(pnlPie, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRenovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenovarActionPerformed
        if (accionRenovar != null) {
            accionRenovar.actionPerformed(evt);
        }
    }//GEN-LAST:event_btnRenovarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        if (accionConsultar != null) {
            accionConsultar.actionPerformed(evt);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void cmbPrestamosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPrestamosActionPerformed
        limpiarDetalle();
        if (accionSeleccion != null && getIndicePrestamo() >= 0) {
            accionSeleccion.actionPerformed(evt);
        }
    }//GEN-LAST:event_cmbPrestamosActionPerformed

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Renovaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRenovar;
    private javax.swing.JComboBox<String> cmbPrestamos;
    private javax.swing.JLabel lblAyudaTabla;
    private javax.swing.JLabel lblDatos;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaActual;
    private javax.swing.JLabel lblListado;
    private javax.swing.JLabel lblMaximas;
    private javax.swing.JLabel lblNuevaFecha;
    private javax.swing.JLabel lblPrestamo;
    private javax.swing.JLabel lblRealizadas;
    private javax.swing.JLabel lblRecurso;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlCabeceraTabla;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPanel pnlFechaActual;
    private javax.swing.JPanel pnlFechas;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlLimites;
    private javax.swing.JPanel pnlListado;
    private javax.swing.JPanel pnlMaximas;
    private javax.swing.JPanel pnlNuevaFecha;
    private javax.swing.JPanel pnlPie;
    private javax.swing.JPanel pnlRealizadas;
    private javax.swing.JPanel pnlRecurso;
    private javax.swing.JPanel pnlSeleccion;
    private javax.swing.JPanel pnlUsuario;
    private javax.swing.JScrollPane scrRenovaciones;
    private javax.swing.JTable tblRenovaciones;
    private javax.swing.JTextField txtFechaLimiteActual;
    private javax.swing.JTextField txtMaxRenovaciones;
    private javax.swing.JTextField txtNuevaFechaLimite;
    private javax.swing.JTextField txtRecurso;
    private javax.swing.JTextField txtRenovacionesRealizadas;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
