package view;

public class Recursos extends javax.swing.JFrame {

    private java.awt.event.ActionListener accionGuardar;
    private java.awt.event.ActionListener accionConsultar;

    public Recursos() {
        initComponents();
        tblRecursos.getTableHeader().setReorderingAllowed(false);
        tblRecursos.getTableHeader().setFont(new java.awt.Font("SansSerif", 1, 12));
        tblRecursos.getColumnModel().getColumn(0).setPreferredWidth(42);
        tblRecursos.getColumnModel().getColumn(1).setPreferredWidth(105);
        tblRecursos.getColumnModel().getColumn(2).setPreferredWidth(155);
        tblRecursos.getColumnModel().getColumn(3).setPreferredWidth(80);
        tblRecursos.getColumnModel().getColumn(4).setPreferredWidth(85);
        tblRecursos.getColumnModel().getColumn(5).setPreferredWidth(180);
        setLocationRelativeTo(null);
    }

    public void configurarAcciones(java.awt.event.ActionListener guardar,
                                   java.awt.event.ActionListener consultar) {
        accionGuardar = guardar;
        accionConsultar = consultar;
        btnGuardar.setEnabled(guardar != null);
        btnConsultar.setEnabled(consultar != null);
    }

    public String getIdentificador() {
        return txtIdentificador.getText().trim();
    }

    public String getNombreRecurso() {
        return txtNombre.getText().trim();
    }

    public String getDescripcion() {
        return txtDescripcion.getText().trim();
    }

    public int getIndiceTipoRecurso() {
        return cmbTipoRecurso.getSelectedIndex() - 1;
    }

    public void cargarTiposRecurso(String[] nombres) {
        cmbTipoRecurso.removeAllItems();
        cmbTipoRecurso.addItem("Seleccione un tipo");
        for (String nombre : nombres) {
            cmbTipoRecurso.addItem(nombre);
        }
        cmbTipoRecurso.setSelectedIndex(0);
    }

    public void mostrarRecursos(Object[][] filas) {
        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel) tblRecursos.getModel();
        modelo.setRowCount(0);
        for (Object[] fila : filas) {
            modelo.addRow(fila);
        }
    }

    public void mostrarEstado(String mensaje) {
        lblEstado.setText(mensaje);
    }

    public void limpiarFormulario() {
        txtIdentificador.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        cmbTipoRecurso.setSelectedIndex(0);
        lblEstado.setText("Formulario limpio.");
        txtIdentificador.requestFocusInWindow();
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
        pnlDatosBasicos = new javax.swing.JPanel();
        pnlIdentificador = new javax.swing.JPanel();
        lblIdentificador = new javax.swing.JLabel();
        txtIdentificador = new javax.swing.JTextField();
        pnlNombre = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        pnlTipo = new javax.swing.JPanel();
        lblTipo = new javax.swing.JLabel();
        cmbTipoRecurso = new javax.swing.JComboBox<>();
        pnlDescripcion = new javax.swing.JPanel();
        lblDescripcion = new javax.swing.JLabel();
        scrDescripcion = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        pnlAcciones = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        pnlListado = new javax.swing.JPanel();
        pnlCabeceraTabla = new javax.swing.JPanel();
        lblListado = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        scrRecursos = new javax.swing.JScrollPane();
        tblRecursos = new javax.swing.JTable();
        lblAyudaTabla = new javax.swing.JLabel();
        pnlPie = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Biblioteca | Libros y equipos");
        setMinimumSize(new java.awt.Dimension(980, 620));
        setPreferredSize(new java.awt.Dimension(1100, 680));

        pnlEncabezado.setBackground(new java.awt.Color(0, 153, 0));
        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 26, 22, 26));
        pnlEncabezado.setLayout(new java.awt.BorderLayout(0, 8));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 25)); // NOI18N
        lblTitulo.setText("LIBROS Y EQUIPOS");
        pnlEncabezado.add(lblTitulo, java.awt.BorderLayout.NORTH);

        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblSubtitulo.setText("Registro y consulta de recursos | Sistema de biblioteca");
        pnlEncabezado.add(lblSubtitulo, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlEncabezado, java.awt.BorderLayout.NORTH);

        pnlContenido.setBackground(new java.awt.Color(243, 246, 249));
        pnlContenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 26, 20, 26));
        pnlContenido.setLayout(new java.awt.BorderLayout(20, 0));

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormulario.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 22, 22, 22));
        pnlFormulario.setPreferredSize(new java.awt.Dimension(340, 440));
        pnlFormulario.setLayout(new java.awt.BorderLayout(0, 18));

        lblDatos.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblDatos.setForeground(new java.awt.Color(0, 113, 121));
        lblDatos.setText("Datos del recurso");
        pnlFormulario.add(lblDatos, java.awt.BorderLayout.NORTH);

        pnlCampos.setOpaque(false);
        pnlCampos.setLayout(new java.awt.BorderLayout(0, 16));

        pnlDatosBasicos.setOpaque(false);
        pnlDatosBasicos.setPreferredSize(new java.awt.Dimension(296, 192));
        pnlDatosBasicos.setLayout(new java.awt.GridLayout(3, 1, 0, 12));

        pnlIdentificador.setOpaque(false);
        pnlIdentificador.setLayout(new java.awt.BorderLayout(0, 6));

        lblIdentificador.setText("Identificador");
        lblIdentificador.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblIdentificador.setForeground(new java.awt.Color(61, 79, 96));
        pnlIdentificador.add(lblIdentificador, java.awt.BorderLayout.NORTH);

        txtIdentificador.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtIdentificador.setForeground(new java.awt.Color(24, 43, 66));
        txtIdentificador.setColumns(18);
        txtIdentificador.setMargin(new java.awt.Insets(5, 8, 5, 8));
        pnlIdentificador.add(txtIdentificador, java.awt.BorderLayout.CENTER);

        pnlDatosBasicos.add(pnlIdentificador);

        pnlNombre.setOpaque(false);
        pnlNombre.setLayout(new java.awt.BorderLayout(0, 6));

        lblNombre.setText("Nombre");
        lblNombre.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(61, 79, 96));
        pnlNombre.add(lblNombre, java.awt.BorderLayout.NORTH);

        txtNombre.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(24, 43, 66));
        txtNombre.setColumns(18);
        txtNombre.setMargin(new java.awt.Insets(5, 8, 5, 8));
        pnlNombre.add(txtNombre, java.awt.BorderLayout.CENTER);

        pnlDatosBasicos.add(pnlNombre);

        pnlTipo.setOpaque(false);
        pnlTipo.setLayout(new java.awt.BorderLayout(0, 6));

        lblTipo.setText("Tipo de recurso");
        lblTipo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblTipo.setForeground(new java.awt.Color(61, 79, 96));
        pnlTipo.add(lblTipo, java.awt.BorderLayout.NORTH);

        cmbTipoRecurso.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cmbTipoRecurso.setForeground(new java.awt.Color(24, 43, 66));
        cmbTipoRecurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un tipo", "Libro", "Equipo" }));
        pnlTipo.add(cmbTipoRecurso, java.awt.BorderLayout.CENTER);

        pnlDatosBasicos.add(pnlTipo);

        pnlCampos.add(pnlDatosBasicos, java.awt.BorderLayout.NORTH);

        pnlDescripcion.setOpaque(false);
        pnlDescripcion.setLayout(new java.awt.BorderLayout(0, 6));

        lblDescripcion.setText("Descripción (opcional)");
        lblDescripcion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(61, 79, 96));
        pnlDescripcion.add(lblDescripcion, java.awt.BorderLayout.NORTH);

        txtDescripcion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(24, 43, 66));
        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(4);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setMargin(new java.awt.Insets(6, 8, 6, 8));
        scrDescripcion.setViewportView(txtDescripcion);

        pnlDescripcion.add(scrDescripcion, java.awt.BorderLayout.CENTER);

        pnlCampos.add(pnlDescripcion, java.awt.BorderLayout.CENTER);

        pnlFormulario.add(pnlCampos, java.awt.BorderLayout.CENTER);

        pnlAcciones.setOpaque(false);
        pnlAcciones.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        btnGuardar.setBackground(new java.awt.Color(0, 153, 0));
        btnGuardar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setToolTipText("");
        btnGuardar.setEnabled(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setPreferredSize(new java.awt.Dimension(112, 38));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlAcciones.add(btnGuardar);

        btnLimpiar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(24, 43, 66));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setFocusPainted(false);
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

        lblListado.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblListado.setForeground(new java.awt.Color(0, 113, 121));
        lblListado.setText("Catálogo de recursos");
        pnlCabeceraTabla.add(lblListado, java.awt.BorderLayout.CENTER);

        btnConsultar.setText("Consultar");
        btnConsultar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnConsultar.setForeground(new java.awt.Color(24, 43, 66));
        btnConsultar.setEnabled(false);
        btnConsultar.setFocusPainted(false);
        btnConsultar.setPreferredSize(new java.awt.Dimension(112, 38));
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        pnlCabeceraTabla.add(btnConsultar, java.awt.BorderLayout.EAST);

        pnlListado.add(pnlCabeceraTabla, java.awt.BorderLayout.NORTH);

        scrRecursos.setPreferredSize(new java.awt.Dimension(620, 320));

        tblRecursos.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        tblRecursos.setForeground(new java.awt.Color(24, 43, 66));
        tblRecursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Identificador", "Nombre", "Tipo", "Disponible", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRecursos.setFillsViewportHeight(true);
        tblRecursos.setGridColor(new java.awt.Color(229, 234, 239));
        tblRecursos.setRowHeight(28);
        tblRecursos.setSelectionBackground(new java.awt.Color(218, 237, 239));
        tblRecursos.setSelectionForeground(new java.awt.Color(24, 43, 66));
        tblRecursos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrRecursos.setViewportView(tblRecursos);

        pnlListado.add(scrRecursos, java.awt.BorderLayout.CENTER);

        lblAyudaTabla.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblAyudaTabla.setForeground(new java.awt.Color(61, 79, 96));
        lblAyudaTabla.setText("Revisa el catálogo y la disponibilidad de libros y equipos.");
        pnlListado.add(lblAyudaTabla, java.awt.BorderLayout.SOUTH);

        pnlContenido.add(pnlListado, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlContenido, java.awt.BorderLayout.CENTER);

        pnlPie.setBackground(new java.awt.Color(243, 246, 249));
        pnlPie.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 26, 18, 26));
        pnlPie.setLayout(new java.awt.BorderLayout(20, 0));

        lblEstado.setText("Gestión de libros y equipos");
        lblEstado.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(61, 79, 96));
        pnlPie.add(lblEstado, java.awt.BorderLayout.CENTER);

        btnCerrar.setText("Cerrar");
        btnCerrar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(24, 43, 66));
        btnCerrar.setFocusPainted(false);
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (accionGuardar != null) {
            accionGuardar.actionPerformed(evt);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

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

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Recursos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbTipoRecurso;
    private javax.swing.JLabel lblAyudaTabla;
    private javax.swing.JLabel lblDatos;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblIdentificador;
    private javax.swing.JLabel lblListado;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlCabeceraTabla;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlDatosBasicos;
    private javax.swing.JPanel pnlDescripcion;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlIdentificador;
    private javax.swing.JPanel pnlListado;
    private javax.swing.JPanel pnlNombre;
    private javax.swing.JPanel pnlPie;
    private javax.swing.JPanel pnlTipo;
    private javax.swing.JScrollPane scrDescripcion;
    private javax.swing.JScrollPane scrRecursos;
    private javax.swing.JTable tblRecursos;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtIdentificador;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
