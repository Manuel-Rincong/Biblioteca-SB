package view;

public class Consultas extends javax.swing.JFrame {

    private java.awt.event.ActionListener accionConsultar;
    private boolean cargandoFiltros;

    public Consultas() {
        initComponents();
        tblPrestamos.getTableHeader().setReorderingAllowed(false);
        tblPrestamos.getTableHeader().setFont(new java.awt.Font("SansSerif", 1, 12));
        int[] anchos = {50, 190, 200, 135, 115, 150, 110, 100};
        for (int columna = 0; columna < anchos.length; columna++) {
            tblPrestamos.getColumnModel().getColumn(columna).setPreferredWidth(anchos[columna]);
        }
        actualizarFiltros();
        setLocationRelativeTo(null);
    }

    public void configurarAccionConsulta(java.awt.event.ActionListener consultar) {
        accionConsultar = consultar;
        actualizarFiltros();
    }

    public int getTipoConsulta() {
        return cmbTipoConsulta.getSelectedIndex();
    }

    public int getIndiceUsuario() {
        int posicion = cmbUsuarios.getSelectedIndex();
        return getTipoConsulta() == 2 && posicion > 0 ? posicion - 1 : -1;
    }

    public Long getIdPrestamoSeleccionado() {
        int fila = tblPrestamos.getSelectedRow();
        if (fila < 0) {
            return null;
        }
        fila = tblPrestamos.convertRowIndexToModel(fila);
        Object id = tblPrestamos.getModel().getValueAt(fila, 0);
        return id instanceof Number ? ((Number) id).longValue() : null;
    }

    public void cargarUsuarios(String[] nombres) {
        cargandoFiltros = true;
        try {
            cmbUsuarios.removeAllItems();
            cmbUsuarios.addItem("Seleccione un usuario");
            for (String nombre : nombres) {
                cmbUsuarios.addItem(nombre);
            }
            cmbUsuarios.setSelectedIndex(0);
        } finally {
            cargandoFiltros = false;
        }
        limpiarResultados();
        actualizarFiltros();
    }

    public void mostrarPrestamos(Object[][] filas) {
        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel) tblPrestamos.getModel();
        modelo.setRowCount(0);
        for (Object[] fila : filas) {
            modelo.addRow(fila);
        }
        tblPrestamos.clearSelection();
    }

    public void limpiarFormulario() {
        cargandoFiltros = true;
        try {
            cmbTipoConsulta.setSelectedIndex(0);
            cmbUsuarios.setSelectedIndex(0);
        } finally {
            cargandoFiltros = false;
        }
        limpiarResultados();
        actualizarFiltros();
        cmbTipoConsulta.requestFocusInWindow();
    }

    private void limpiarResultados() {
        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel) tblPrestamos.getModel();
        modelo.setRowCount(0);
    }

    private void actualizarFiltros() {
        boolean historial = getTipoConsulta() == 2;
        cmbUsuarios.setEnabled(historial);
        btnConsultar.setEnabled(accionConsultar != null
                && (!historial || getIndiceUsuario() >= 0));
    }

    private void cambiarFiltro() {
        if (!cargandoFiltros) {
            limpiarResultados();
            actualizarFiltros();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEncabezado = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlContenido = new javax.swing.JPanel();
        pnlFiltros = new javax.swing.JPanel();
        pnlCampos = new javax.swing.JPanel();
        pnlTipoConsulta = new javax.swing.JPanel();
        lblTipoConsulta = new javax.swing.JLabel();
        cmbTipoConsulta = new javax.swing.JComboBox<>();
        pnlUsuario = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        cmbUsuarios = new javax.swing.JComboBox<>();
        pnlAcciones = new javax.swing.JPanel();
        btnConsultar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        pnlListado = new javax.swing.JPanel();
        lblListado = new javax.swing.JLabel();
        scrPrestamos = new javax.swing.JScrollPane();
        tblPrestamos = new javax.swing.JTable();
        pnlPie = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Biblioteca | Consultas e historial");
        setMinimumSize(new java.awt.Dimension(1040, 600));
        setPreferredSize(new java.awt.Dimension(1180, 680));

        pnlEncabezado.setBackground(new java.awt.Color(255, 255, 0));
        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 26, 22, 26));
        pnlEncabezado.setLayout(new java.awt.BorderLayout());

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 25)); // NOI18N
        lblTitulo.setText("CONSULTAS E HISTORIAL");
        pnlEncabezado.add(lblTitulo, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlEncabezado, java.awt.BorderLayout.NORTH);

        pnlContenido.setBackground(new java.awt.Color(247, 250, 242));
        pnlContenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 26, 18, 26));
        pnlContenido.setLayout(new java.awt.BorderLayout(0, 18));

        pnlFiltros.setBackground(new java.awt.Color(255, 255, 255));
        pnlFiltros.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20));
        pnlFiltros.setLayout(new java.awt.BorderLayout(18, 0));

        pnlCampos.setOpaque(false);
        pnlCampos.setLayout(new java.awt.GridLayout(1, 2, 18, 0));

        pnlTipoConsulta.setOpaque(false);
        pnlTipoConsulta.setLayout(new java.awt.BorderLayout(0, 6));

        lblTipoConsulta.setText("Tipo de consulta");
        lblTipoConsulta.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblTipoConsulta.setForeground(new java.awt.Color(44, 56, 36));
        pnlTipoConsulta.add(lblTipoConsulta, java.awt.BorderLayout.NORTH);

        cmbTipoConsulta.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cmbTipoConsulta.setForeground(new java.awt.Color(65, 79, 46));
        cmbTipoConsulta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Préstamos activos", "Préstamos vencidos", "Historial por usuario" }));
        cmbTipoConsulta.setPreferredSize(new java.awt.Dimension(250, 38));
        cmbTipoConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoConsultaActionPerformed(evt);
            }
        });
        pnlTipoConsulta.add(cmbTipoConsulta, java.awt.BorderLayout.CENTER);

        pnlCampos.add(pnlTipoConsulta);

        pnlUsuario.setOpaque(false);
        pnlUsuario.setLayout(new java.awt.BorderLayout(0, 6));

        lblUsuario.setText("Usuario");
        lblUsuario.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(44, 56, 36));
        pnlUsuario.add(lblUsuario, java.awt.BorderLayout.NORTH);

        cmbUsuarios.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cmbUsuarios.setForeground(new java.awt.Color(255, 255, 0));
        cmbUsuarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un usuario" }));
        cmbUsuarios.setEnabled(false);
        cmbUsuarios.setPreferredSize(new java.awt.Dimension(250, 38));
        cmbUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUsuariosActionPerformed(evt);
            }
        });
        pnlUsuario.add(cmbUsuarios, java.awt.BorderLayout.CENTER);

        pnlCampos.add(pnlUsuario);

        pnlFiltros.add(pnlCampos, java.awt.BorderLayout.CENTER);

        pnlAcciones.setOpaque(false);
        pnlAcciones.setBorder(javax.swing.BorderFactory.createEmptyBorder(23, 0, 0, 0));
        pnlAcciones.setPreferredSize(new java.awt.Dimension(244, 62));
        pnlAcciones.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        btnConsultar.setBackground(new java.awt.Color(233, 240, 220));
        btnConsultar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnConsultar.setForeground(new java.awt.Color(255, 255, 0));
        btnConsultar.setText("Consultar");
        btnConsultar.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 16, 9, 16));
        btnConsultar.setContentAreaFilled(false);
        btnConsultar.setEnabled(false);
        btnConsultar.setFocusPainted(false);
        btnConsultar.setOpaque(true);
        btnConsultar.setPreferredSize(new java.awt.Dimension(112, 38));
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        pnlAcciones.add(btnConsultar);

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

        pnlFiltros.add(pnlAcciones, java.awt.BorderLayout.EAST);

        pnlContenido.add(pnlFiltros, java.awt.BorderLayout.NORTH);

        pnlListado.setBackground(new java.awt.Color(255, 255, 255));
        pnlListado.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 20, 20, 20));
        pnlListado.setLayout(new java.awt.BorderLayout(0, 14));

        lblListado.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblListado.setText("Préstamos");
        pnlListado.add(lblListado, java.awt.BorderLayout.NORTH);

        scrPrestamos.setPreferredSize(new java.awt.Dimension(1060, 340));

        tblPrestamos.setAutoCreateRowSorter(true);
        tblPrestamos.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        tblPrestamos.setForeground(new java.awt.Color(44, 56, 36));
        tblPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Usuario", "Recurso", "Fecha de préstamo", "Fecha límite", "Fecha de devolución", "Renovaciones", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPrestamos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblPrestamos.setFillsViewportHeight(true);
        tblPrestamos.setGridColor(new java.awt.Color(226, 234, 215));
        tblPrestamos.setRowHeight(28);
        tblPrestamos.setSelectionBackground(new java.awt.Color(226, 236, 208));
        tblPrestamos.setSelectionForeground(new java.awt.Color(65, 79, 46));
        tblPrestamos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrPrestamos.setViewportView(tblPrestamos);

        pnlListado.add(scrPrestamos, java.awt.BorderLayout.CENTER);

        pnlContenido.add(pnlListado, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlContenido, java.awt.BorderLayout.CENTER);

        pnlPie.setBackground(new java.awt.Color(247, 250, 242));
        pnlPie.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 26, 18, 26));
        pnlPie.setLayout(new java.awt.BorderLayout());

        btnCerrar.setBackground(new java.awt.Color(233, 240, 220));
        btnCerrar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 16, 9, 16));
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setOpaque(true);
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

    private void cmbTipoConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoConsultaActionPerformed
        cambiarFiltro();
    }//GEN-LAST:event_cmbTipoConsultaActionPerformed

    private void cmbUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUsuariosActionPerformed
        cambiarFiltro();
    }//GEN-LAST:event_cmbUsuariosActionPerformed

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Consultas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbTipoConsulta;
    private javax.swing.JComboBox<String> cmbUsuarios;
    private javax.swing.JLabel lblListado;
    private javax.swing.JLabel lblTipoConsulta;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPanel pnlFiltros;
    private javax.swing.JPanel pnlListado;
    private javax.swing.JPanel pnlPie;
    private javax.swing.JPanel pnlTipoConsulta;
    private javax.swing.JPanel pnlUsuario;
    private javax.swing.JScrollPane scrPrestamos;
    private javax.swing.JTable tblPrestamos;
    // End of variables declaration//GEN-END:variables
}
