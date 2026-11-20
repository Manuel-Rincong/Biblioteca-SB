package view;


public class Usuarios extends javax.swing.JFrame {

    private java.awt.event.ActionListener accionGuardar;
    private java.awt.event.ActionListener accionConsultar;

    public Usuarios() {
        initComponents();
        tblUsuarios.getTableHeader().setReorderingAllowed(false);
        tblUsuarios.getTableHeader().setFont(new java.awt.Font("SansSerif", 1, 12));
        tblUsuarios.getColumnModel().getColumn(0).setPreferredWidth(45);
        tblUsuarios.getColumnModel().getColumn(1).setPreferredWidth(110);
        tblUsuarios.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblUsuarios.getColumnModel().getColumn(3).setPreferredWidth(180);
        tblUsuarios.getColumnModel().getColumn(4).setPreferredWidth(110);
        setLocationRelativeTo(null);
    }

    public void configurarAcciones(java.awt.event.ActionListener guardar,
                                   java.awt.event.ActionListener consultar) {
        accionGuardar = guardar;
        accionConsultar = consultar;
        btnGuardar.setEnabled(guardar != null);
        btnConsultar.setEnabled(consultar != null);
    }

    public String getIdentificacion() {
        return txtIdentificacion.getText().trim();
    }

    public String getNombreUsuario() {
        return txtNombre.getText().trim();
    }

    public String getCorreo() {
        return txtCorreo.getText().trim();
    }


    public int getIndiceTipoUsuario() {
        return cmbTipoUsuario.getSelectedIndex() - 1;
    }

    public void cargarTiposUsuario(String[] nombres) {
        cmbTipoUsuario.removeAllItems();
        cmbTipoUsuario.addItem("Seleccione un tipo");
        for (String nombre : nombres) {
            cmbTipoUsuario.addItem(nombre);
        }
        cmbTipoUsuario.setSelectedIndex(0);
    }

    // Orden: ID, identificación, nombre, correo y tipo de usuario.
    public void mostrarUsuarios(Object[][] filas) {
        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel) tblUsuarios.getModel();
        modelo.setRowCount(0);
        for (Object[] fila : filas) {
            modelo.addRow(fila);
        }
    }

    public void mostrarEstado(String mensaje) {
        lblEstado.setText(mensaje);
    }

    public void limpiarFormulario() {
        txtIdentificacion.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
        cmbTipoUsuario.setSelectedIndex(0);
        lblEstado.setText("Formulario limpio.");
        txtIdentificacion.requestFocusInWindow();
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
        pnlIdentificacion = new javax.swing.JPanel();
        lblIdentificacion = new javax.swing.JLabel();
        txtIdentificacion = new javax.swing.JTextField();
        pnlNombre = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        pnlCorreo = new javax.swing.JPanel();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        pnlTipo = new javax.swing.JPanel();
        lblTipo = new javax.swing.JLabel();
        cmbTipoUsuario = new javax.swing.JComboBox<>();
        pnlAcciones = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        pnlListado = new javax.swing.JPanel();
        pnlCabeceraTabla = new javax.swing.JPanel();
        lblListado = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        scrUsuarios = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        lblAyudaTabla = new javax.swing.JLabel();
        pnlPie = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Biblioteca | Gestión de usuarios");
        setMinimumSize(new java.awt.Dimension(940, 600));
        setPreferredSize(new java.awt.Dimension(1040, 660));

        pnlEncabezado.setBackground(new java.awt.Color(24, 43, 66));
        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 26, 22, 26));
        pnlEncabezado.setLayout(new java.awt.BorderLayout(0, 8));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 25)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 255, 204));
        lblTitulo.setText("GESTIÓN DE USUARIOS");
        pnlEncabezado.add(lblTitulo, java.awt.BorderLayout.NORTH);

        lblSubtitulo.setText("Estudiantes y docentes | Sistema de biblioteca");
        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(202, 223, 231));
        pnlEncabezado.add(lblSubtitulo, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlEncabezado, java.awt.BorderLayout.NORTH);

        pnlContenido.setBackground(new java.awt.Color(243, 246, 249));
        pnlContenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 26, 20, 26));
        pnlContenido.setLayout(new java.awt.BorderLayout(20, 0));

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormulario.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 22, 22, 22));
        pnlFormulario.setPreferredSize(new java.awt.Dimension(318, 420));
        pnlFormulario.setLayout(new java.awt.BorderLayout(0, 20));

        lblDatos.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblDatos.setForeground(new java.awt.Color(0, 113, 121));
        lblDatos.setText("Datos del usuario");
        pnlFormulario.add(lblDatos, java.awt.BorderLayout.NORTH);

        pnlCampos.setOpaque(false);
        pnlCampos.setLayout(new java.awt.GridLayout(4, 1, 0, 16));

        pnlIdentificacion.setOpaque(false);
        pnlIdentificacion.setLayout(new java.awt.BorderLayout(0, 6));

        lblIdentificacion.setText("Identificación");
        lblIdentificacion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblIdentificacion.setForeground(new java.awt.Color(61, 79, 96));
        pnlIdentificacion.add(lblIdentificacion, java.awt.BorderLayout.NORTH);

        txtIdentificacion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtIdentificacion.setForeground(new java.awt.Color(24, 43, 66));
        txtIdentificacion.setColumns(18);
        txtIdentificacion.setMargin(new java.awt.Insets(5, 8, 5, 8));
        pnlIdentificacion.add(txtIdentificacion, java.awt.BorderLayout.CENTER);

        pnlCampos.add(pnlIdentificacion);

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

        pnlCampos.add(pnlNombre);

        pnlCorreo.setOpaque(false);
        pnlCorreo.setLayout(new java.awt.BorderLayout(0, 6));

        lblCorreo.setText("Correo");
        lblCorreo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblCorreo.setForeground(new java.awt.Color(61, 79, 96));
        pnlCorreo.add(lblCorreo, java.awt.BorderLayout.NORTH);

        txtCorreo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(24, 43, 66));
        txtCorreo.setColumns(18);
        txtCorreo.setMargin(new java.awt.Insets(5, 8, 5, 8));
        pnlCorreo.add(txtCorreo, java.awt.BorderLayout.CENTER);

        pnlCampos.add(pnlCorreo);

        pnlTipo.setOpaque(false);
        pnlTipo.setLayout(new java.awt.BorderLayout(0, 6));

        lblTipo.setText("Tipo de usuario");
        lblTipo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblTipo.setForeground(new java.awt.Color(61, 79, 96));
        pnlTipo.add(lblTipo, java.awt.BorderLayout.NORTH);

        cmbTipoUsuario.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cmbTipoUsuario.setForeground(new java.awt.Color(24, 43, 66));
        cmbTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un tipo" }));
        pnlTipo.add(cmbTipoUsuario, java.awt.BorderLayout.CENTER);

        pnlCampos.add(pnlTipo);

        pnlFormulario.add(pnlCampos, java.awt.BorderLayout.CENTER);

        pnlAcciones.setOpaque(false);
        pnlAcciones.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        btnGuardar.setText("Guardar");
        btnGuardar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnGuardar.setBackground(new java.awt.Color(0, 113, 121));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setEnabled(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setPreferredSize(new java.awt.Dimension(112, 38));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlAcciones.add(btnGuardar);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(24, 43, 66));
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

        lblListado.setText("Usuarios registrados");
        lblListado.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblListado.setForeground(new java.awt.Color(0, 113, 121));
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

        scrUsuarios.setPreferredSize(new java.awt.Dimension(540, 300));

        tblUsuarios.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        tblUsuarios.setForeground(new java.awt.Color(24, 43, 66));
        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Identificación", "Nombre", "Correo", "Tipo de usuario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tblUsuarios.setRowHeight(28);
        tblUsuarios.setFillsViewportHeight(true);
        tblUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblUsuarios.setSelectionBackground(new java.awt.Color(218, 237, 239));
        tblUsuarios.setSelectionForeground(new java.awt.Color(24, 43, 66));
        tblUsuarios.setGridColor(new java.awt.Color(229, 234, 239));
        scrUsuarios.setViewportView(tblUsuarios);

        pnlListado.add(scrUsuarios, java.awt.BorderLayout.CENTER);

        lblAyudaTabla.setText("Consulta los estudiantes y docentes de la biblioteca.");
        lblAyudaTabla.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblAyudaTabla.setForeground(new java.awt.Color(61, 79, 96));
        pnlListado.add(lblAyudaTabla, java.awt.BorderLayout.SOUTH);

        pnlContenido.add(pnlListado, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlContenido, java.awt.BorderLayout.CENTER);

        pnlPie.setBackground(new java.awt.Color(243, 246, 249));
        pnlPie.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 26, 18, 26));
        pnlPie.setLayout(new java.awt.BorderLayout(20, 0));

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
                new Usuarios().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbTipoUsuario;
    private javax.swing.JLabel lblAyudaTabla;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDatos;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblIdentificacion;
    private javax.swing.JLabel lblListado;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlCabeceraTabla;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlCorreo;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlIdentificacion;
    private javax.swing.JPanel pnlListado;
    private javax.swing.JPanel pnlNombre;
    private javax.swing.JPanel pnlPie;
    private javax.swing.JPanel pnlTipo;
    private javax.swing.JScrollPane scrUsuarios;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
