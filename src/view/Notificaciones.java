package view;

public class Notificaciones extends javax.swing.JFrame {

    private java.awt.event.ActionListener accionConsultar;
    private java.awt.event.ActionListener accionMarcarLeida;
    private java.awt.event.ActionListener accionFiltro;

    public Notificaciones() {
        initComponents();
        tblNotificaciones.getTableHeader().setReorderingAllowed(false);
        tblNotificaciones.getTableHeader().setFont(new java.awt.Font("SansSerif", 1, 12));
        tblNotificaciones.getColumnModel().getColumn(0).setPreferredWidth(45);
        tblNotificaciones.getColumnModel().getColumn(1).setPreferredWidth(165);
        tblNotificaciones.getColumnModel().getColumn(2).setPreferredWidth(325);
        tblNotificaciones.getColumnModel().getColumn(3).setPreferredWidth(70);
        tblNotificaciones.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                if (!evt.getValueIsAdjusting()) {
                    actualizarDetalle();
                }
            }
        });
        actualizarAcciones();
        setLocationRelativeTo(null);
    }

    public void configurarAcciones(java.awt.event.ActionListener consultar,
                                   java.awt.event.ActionListener marcarLeida) {
        accionConsultar = consultar;
        accionMarcarLeida = marcarLeida;
        actualizarAcciones();
    }

    public void configurarCambioFiltro(java.awt.event.ActionListener accion) {
        accionFiltro = accion;
    }

    public int getIndiceUsuario() {
        int posicion = cmbUsuarios.getSelectedIndex();
        return posicion > 0 ? posicion - 1 : -1;
    }

    public int getIndiceFiltro() {
        return cmbEstado.getSelectedIndex();
    }

    public Long getIdNotificacionSeleccionada() {
        int fila = getFilaSeleccionada();
        if (fila < 0) {
            return null;
        }
        Object id = tblNotificaciones.getModel().getValueAt(fila, 0);
        return id instanceof Number ? ((Number) id).longValue() : null;
    }

    public void cargarUsuarios(String[] nombres) {
        cmbUsuarios.removeAllItems();
        cmbUsuarios.addItem("Seleccione un usuario");
        for (String nombre : nombres) {
            cmbUsuarios.addItem(nombre);
        }
        cmbUsuarios.setSelectedIndex(0);
        limpiarAvisos();
        actualizarAcciones();
    }

    public void mostrarNotificaciones(Object[][] filas) {
        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel) tblNotificaciones.getModel();
        modelo.setRowCount(0);
        for (Object[] fila : filas) {
            modelo.addRow(fila);
        }
        limpiarDetalle();
        actualizarAcciones();
    }

    public void mostrarEstado(String mensaje) {
    javax.swing.JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarFormulario() {
        cmbUsuarios.setSelectedIndex(0);
        cmbEstado.setSelectedIndex(0);
        limpiarAvisos();
        actualizarAcciones();
        cmbUsuarios.requestFocusInWindow();
    }

    private int getFilaSeleccionada() {
        int fila = tblNotificaciones.getSelectedRow();
        return fila < 0 ? -1 : tblNotificaciones.convertRowIndexToModel(fila);
    }

    private void limpiarDetalle() {
        txtFechaCreacion.setText("");
        txtMensaje.setText("");
    }

    private void limpiarAvisos() {
        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel) tblNotificaciones.getModel();
        modelo.setRowCount(0);
        limpiarDetalle();
    }

    private void actualizarDetalle() {
        int fila = getFilaSeleccionada();
        if (fila < 0) {
            limpiarDetalle();
        } else {
            Object fecha = tblNotificaciones.getModel().getValueAt(fila, 1);
            Object mensaje = tblNotificaciones.getModel().getValueAt(fila, 2);
            txtFechaCreacion.setText(fecha == null ? "" : fecha.toString());
            txtMensaje.setText(mensaje == null ? "" : mensaje.toString());
            txtMensaje.setCaretPosition(0);
        }
        actualizarAcciones();
    }

    private void actualizarAcciones() {
        int fila = getFilaSeleccionada();
        boolean noLeida = fila >= 0 && Boolean.FALSE.equals(tblNotificaciones.getModel().getValueAt(fila, 3));
        btnConsultar.setEnabled(accionConsultar != null && getIndiceUsuario() >= 0);
        btnMarcarLeida.setEnabled(accionMarcarLeida != null && getIdNotificacionSeleccionada() != null && noLeida);
    }

    private void notificarCambioFiltro(java.awt.event.ActionEvent evt) {
        limpiarAvisos();
        actualizarAcciones();
        if (accionFiltro != null && getIndiceUsuario() >= 0) {
            accionFiltro.actionPerformed(evt);
        }
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
        pnlFiltros = new javax.swing.JPanel();
        pnlUsuario = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        cmbUsuarios = new javax.swing.JComboBox<>();
        pnlEstadoLectura = new javax.swing.JPanel();
        lblEstadoLectura = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        pnlFechaCreacion = new javax.swing.JPanel();
        lblFechaCreacion = new javax.swing.JLabel();
        txtFechaCreacion = new javax.swing.JTextField();
        pnlMensaje = new javax.swing.JPanel();
        lblMensaje = new javax.swing.JLabel();
        scrMensaje = new javax.swing.JScrollPane();
        txtMensaje = new javax.swing.JTextArea();
        pnlAcciones = new javax.swing.JPanel();
        btnMarcarLeida = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        pnlListado = new javax.swing.JPanel();
        pnlCabeceraTabla = new javax.swing.JPanel();
        lblListado = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        scrNotificaciones = new javax.swing.JScrollPane();
        tblNotificaciones = new javax.swing.JTable();
        lblAyudaTabla = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Biblioteca | Notificaciones");
        setMinimumSize(new java.awt.Dimension(1000, 680));
        setPreferredSize(new java.awt.Dimension(1100, 700));

        pnlEncabezado.setBackground(new java.awt.Color(65, 79, 46));
        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 26, 18, 26));
        pnlEncabezado.setLayout(new java.awt.BorderLayout(0, 8));

        lblTitulo.setText("NOTIFICACIONES");
        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 25)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        pnlEncabezado.add(lblTitulo, java.awt.BorderLayout.NORTH);

        lblSubtitulo.setText("Avisos internos de disponibilidad | Sistema de biblioteca");
        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(226, 236, 208));
        pnlEncabezado.add(lblSubtitulo, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlEncabezado, java.awt.BorderLayout.NORTH);

        pnlContenido.setBackground(new java.awt.Color(247, 250, 242));
        pnlContenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 26, 18, 26));
        pnlContenido.setLayout(new java.awt.BorderLayout(20, 0));

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormulario.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 22, 22, 22));
        pnlFormulario.setPreferredSize(new java.awt.Dimension(360, 440));
        pnlFormulario.setLayout(new java.awt.BorderLayout(0, 14));

        lblDatos.setText("Consultar avisos");
        lblDatos.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblDatos.setForeground(new java.awt.Color(83, 105, 52));
        pnlFormulario.add(lblDatos, java.awt.BorderLayout.NORTH);

        pnlCampos.setOpaque(false);
        pnlCampos.setLayout(new java.awt.BorderLayout(0, 12));

        pnlFiltros.setOpaque(false);
        pnlFiltros.setPreferredSize(new java.awt.Dimension(316, 190));
        pnlFiltros.setLayout(new java.awt.GridLayout(3, 1, 0, 12));

        pnlUsuario.setOpaque(false);
        pnlUsuario.setLayout(new java.awt.BorderLayout(0, 6));

        lblUsuario.setText("Usuario");
        lblUsuario.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(44, 56, 36));
        pnlUsuario.add(lblUsuario, java.awt.BorderLayout.NORTH);

        cmbUsuarios.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cmbUsuarios.setForeground(new java.awt.Color(65, 79, 46));
        cmbUsuarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un usuario" }));
        cmbUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUsuariosActionPerformed(evt);
            }
        });
        pnlUsuario.add(cmbUsuarios, java.awt.BorderLayout.CENTER);

        pnlFiltros.add(pnlUsuario);

        pnlEstadoLectura.setOpaque(false);
        pnlEstadoLectura.setLayout(new java.awt.BorderLayout(0, 6));

        lblEstadoLectura.setText("Estado de lectura");
        lblEstadoLectura.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblEstadoLectura.setForeground(new java.awt.Color(44, 56, 36));
        pnlEstadoLectura.add(lblEstadoLectura, java.awt.BorderLayout.NORTH);

        cmbEstado.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cmbEstado.setForeground(new java.awt.Color(65, 79, 46));
        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "No leídas", "Leídas" }));
        cmbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoActionPerformed(evt);
            }
        });
        pnlEstadoLectura.add(cmbEstado, java.awt.BorderLayout.CENTER);

        pnlFiltros.add(pnlEstadoLectura);

        pnlFechaCreacion.setOpaque(false);
        pnlFechaCreacion.setLayout(new java.awt.BorderLayout(0, 6));

        lblFechaCreacion.setText("Fecha del aviso");
        lblFechaCreacion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblFechaCreacion.setForeground(new java.awt.Color(44, 56, 36));
        pnlFechaCreacion.add(lblFechaCreacion, java.awt.BorderLayout.NORTH);

        txtFechaCreacion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtFechaCreacion.setForeground(new java.awt.Color(65, 79, 46));
        txtFechaCreacion.setBackground(new java.awt.Color(247, 250, 242));
        txtFechaCreacion.setEditable(false);
        txtFechaCreacion.setColumns(20);
        txtFechaCreacion.setMargin(new java.awt.Insets(0, 7, 0, 7));
        pnlFechaCreacion.add(txtFechaCreacion, java.awt.BorderLayout.CENTER);

        pnlFiltros.add(pnlFechaCreacion);

        pnlCampos.add(pnlFiltros, java.awt.BorderLayout.NORTH);

        pnlMensaje.setOpaque(false);
        pnlMensaje.setLayout(new java.awt.BorderLayout(0, 6));

        lblMensaje.setText("Mensaje completo");
        lblMensaje.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblMensaje.setForeground(new java.awt.Color(44, 56, 36));
        pnlMensaje.add(lblMensaje, java.awt.BorderLayout.NORTH);

        txtMensaje.setEditable(false);
        txtMensaje.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtMensaje.setForeground(new java.awt.Color(44, 56, 36));
        txtMensaje.setBackground(new java.awt.Color(247, 250, 242));
        txtMensaje.setRows(4);
        txtMensaje.setColumns(20);
        txtMensaje.setLineWrap(true);
        txtMensaje.setWrapStyleWord(true);
        txtMensaje.setMargin(new java.awt.Insets(6, 8, 6, 8));
        scrMensaje.setViewportView(txtMensaje);

        pnlMensaje.add(scrMensaje, java.awt.BorderLayout.CENTER);

        pnlCampos.add(pnlMensaje, java.awt.BorderLayout.CENTER);

        pnlFormulario.add(pnlCampos, java.awt.BorderLayout.CENTER);

        pnlAcciones.setOpaque(false);
        pnlAcciones.setLayout(new java.awt.GridLayout(2, 1, 0, 8));

        btnMarcarLeida.setText("Marcar como leída");
        btnMarcarLeida.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnMarcarLeida.setBackground(new java.awt.Color(233, 240, 220));
        btnMarcarLeida.setForeground(new java.awt.Color(65, 79, 46));
        btnMarcarLeida.setEnabled(false);
        btnMarcarLeida.setFocusPainted(false);
        btnMarcarLeida.setContentAreaFilled(false);
        btnMarcarLeida.setOpaque(true);
        btnMarcarLeida.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 16, 9, 16));
        btnMarcarLeida.setPreferredSize(new java.awt.Dimension(112, 38));
        btnMarcarLeida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcarLeidaActionPerformed(evt);
            }
        });
        pnlAcciones.add(btnMarcarLeida);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLimpiar.setBackground(new java.awt.Color(233, 240, 220));
        btnLimpiar.setForeground(new java.awt.Color(65, 79, 46));
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

        lblListado.setText("Avisos del usuario");
        lblListado.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblListado.setForeground(new java.awt.Color(83, 105, 52));
        pnlCabeceraTabla.add(lblListado, java.awt.BorderLayout.CENTER);

        btnConsultar.setText("Consultar");
        btnConsultar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnConsultar.setBackground(new java.awt.Color(233, 240, 220));
        btnConsultar.setForeground(new java.awt.Color(65, 79, 46));
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

        scrNotificaciones.setPreferredSize(new java.awt.Dimension(600, 320));

        tblNotificaciones.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        tblNotificaciones.setForeground(new java.awt.Color(44, 56, 36));
        tblNotificaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "Mensaje", "Leída"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNotificaciones.setRowHeight(28);
        tblNotificaciones.setFillsViewportHeight(true);
        tblNotificaciones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblNotificaciones.setSelectionBackground(new java.awt.Color(226, 236, 208));
        tblNotificaciones.setSelectionForeground(new java.awt.Color(65, 79, 46));
        tblNotificaciones.setGridColor(new java.awt.Color(226, 234, 215));
        scrNotificaciones.setViewportView(tblNotificaciones);

        pnlListado.add(scrNotificaciones, java.awt.BorderLayout.CENTER);

        lblAyudaTabla.setText("Selecciona un aviso para leer el mensaje completo.");
        lblAyudaTabla.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblAyudaTabla.setForeground(new java.awt.Color(44, 56, 36));
        pnlListado.add(lblAyudaTabla, java.awt.BorderLayout.SOUTH);

        pnlContenido.add(pnlListado, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlContenido, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        if (accionConsultar != null) {
            accionConsultar.actionPerformed(evt);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnMarcarLeidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcarLeidaActionPerformed
        if (accionMarcarLeida != null) {
            accionMarcarLeida.actionPerformed(evt);
        }
    }//GEN-LAST:event_btnMarcarLeidaActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void cmbUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUsuariosActionPerformed
        notificarCambioFiltro(evt);
    }//GEN-LAST:event_cmbUsuariosActionPerformed

    private void cmbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoActionPerformed
        notificarCambioFiltro(evt);
    }//GEN-LAST:event_cmbEstadoActionPerformed

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Notificaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnMarcarLeida;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbUsuarios;
    private javax.swing.JLabel lblAyudaTabla;
    private javax.swing.JLabel lblDatos;
    private javax.swing.JLabel lblEstadoLectura;
    private javax.swing.JLabel lblFechaCreacion;
    private javax.swing.JLabel lblListado;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlCabeceraTabla;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPanel pnlEstadoLectura;
    private javax.swing.JPanel pnlFechaCreacion;
    private javax.swing.JPanel pnlFiltros;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlListado;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JPanel pnlUsuario;
    private javax.swing.JScrollPane scrMensaje;
    private javax.swing.JScrollPane scrNotificaciones;
    private javax.swing.JTable tblNotificaciones;
    private javax.swing.JTextField txtFechaCreacion;
    private javax.swing.JTextArea txtMensaje;
    // End of variables declaration//GEN-END:variables
}
