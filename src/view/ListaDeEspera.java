package view;

public class ListaDeEspera extends javax.swing.JFrame {

    private java.awt.event.ActionListener accionInscribir;
    private java.awt.event.ActionListener accionRetirar;
    private java.awt.event.ActionListener accionConsultar;
    private java.awt.event.ActionListener accionSeleccion;

    public ListaDeEspera() {
        initComponents();
        tblListaEspera.getTableHeader().setReorderingAllowed(false);
        tblListaEspera.getTableHeader().setFont(new java.awt.Font("SansSerif", 1, 12));
        tblListaEspera.getColumnModel().getColumn(0).setPreferredWidth(45);
        tblListaEspera.getColumnModel().getColumn(1).setPreferredWidth(60);
        tblListaEspera.getColumnModel().getColumn(2).setPreferredWidth(160);
        tblListaEspera.getColumnModel().getColumn(3).setPreferredWidth(190);
        tblListaEspera.getColumnModel().getColumn(4).setPreferredWidth(110);
        tblListaEspera.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                actualizarAcciones();
            }
        });
        actualizarAcciones();
        setLocationRelativeTo(null);
    }

    public void configurarAcciones(java.awt.event.ActionListener inscribir,
            java.awt.event.ActionListener retirar, java.awt.event.ActionListener consultar) {
        accionInscribir = inscribir;
        accionRetirar = retirar;
        accionConsultar = consultar;
        actualizarAcciones();
    }

    public void configurarCambioSeleccion(java.awt.event.ActionListener accion) {
        accionSeleccion = accion;
    }

    public int getIndiceRecurso() {
        int posicion = cmbRecursos.getSelectedIndex();
        return posicion > 0 ? posicion - 1 : -1;
    }

    public int getIndiceUsuario() {
        int posicion = cmbUsuarios.getSelectedIndex();
        return posicion > 0 ? posicion - 1 : -1;
    }

    public Long getIdInscripcionSeleccionada() {
        int fila = tblListaEspera.getSelectedRow();
        if (fila < 0) {
            return null;
        }
        fila = tblListaEspera.convertRowIndexToModel(fila);
        Object id = tblListaEspera.getModel().getValueAt(fila, 0);
        return id instanceof Number ? ((Number) id).longValue() : null;
    }

    public void cargarRecursos(String[] nombres) {
        cmbRecursos.removeAllItems();
        cmbRecursos.addItem("Seleccione un recurso");
        for (String nombre : nombres) {
            cmbRecursos.addItem(nombre);
        }
        cmbRecursos.setSelectedIndex(0);
        limpiarDatosRecurso();
        actualizarAcciones();
    }

    public void cargarUsuarios(String[] nombres) {
        cmbUsuarios.removeAllItems();
        cmbUsuarios.addItem("Seleccione un usuario");
        for (String nombre : nombres) {
            cmbUsuarios.addItem(nombre);
        }
        cmbUsuarios.setSelectedIndex(0);
        actualizarAcciones();
    }

    public void mostrarDatosRecurso(String estado, int totalEnEspera) {
        txtEstadoRecurso.setText(estado);
        txtTotalEnEspera.setText(String.valueOf(totalEnEspera));
    }

    public void mostrarListaEspera(Object[][] filas) {
        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel) tblListaEspera.getModel();
        modelo.setRowCount(0);
        for (Object[] fila : filas) {
            modelo.addRow(fila);
        }
        actualizarAcciones();
    }

    public void mostrarEstado(String mensaje) {
        lblEstado.setText(mensaje);
    }

    public void limpiarFormulario() {
        cmbRecursos.setSelectedIndex(0);
        cmbUsuarios.setSelectedIndex(0);
        limpiarDatosRecurso();
        actualizarAcciones();
        lblEstado.setText("Formulario limpio.");
        cmbRecursos.requestFocusInWindow();
    }

    private void limpiarDatosRecurso() {
        txtEstadoRecurso.setText("");
        txtTotalEnEspera.setText("");
        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel) tblListaEspera.getModel();
        modelo.setRowCount(0);
    }

    private void actualizarAcciones() {
        btnInscribir.setEnabled(accionInscribir != null && getIndiceRecurso() >= 0 && getIndiceUsuario() >= 0);
        btnRetirar.setEnabled(accionRetirar != null && getIdInscripcionSeleccionada() != null);
        btnConsultar.setEnabled(accionConsultar != null);
    }

    private void notificarSeleccion(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == cmbRecursos) {
            limpiarDatosRecurso();
        }
        actualizarAcciones();
        if (accionSeleccion != null && getIndiceRecurso() >= 0) {
            accionSeleccion.actionPerformed(evt);
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
        pnlRecurso = new javax.swing.JPanel();
        lblRecurso = new javax.swing.JLabel();
        cmbRecursos = new javax.swing.JComboBox<>();
        pnlUsuario = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        cmbUsuarios = new javax.swing.JComboBox<>();
        pnlEstadoRecurso = new javax.swing.JPanel();
        lblEstadoRecurso = new javax.swing.JLabel();
        txtEstadoRecurso = new javax.swing.JTextField();
        pnlTotalEnEspera = new javax.swing.JPanel();
        lblTotalEnEspera = new javax.swing.JLabel();
        txtTotalEnEspera = new javax.swing.JTextField();
        pnlAcciones = new javax.swing.JPanel();
        pnlInscripcion = new javax.swing.JPanel();
        btnInscribir = new javax.swing.JButton();
        btnRetirar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        pnlListado = new javax.swing.JPanel();
        pnlCabeceraTabla = new javax.swing.JPanel();
        lblListado = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        scrListaEspera = new javax.swing.JScrollPane();
        tblListaEspera = new javax.swing.JTable();
        lblAyudaTabla = new javax.swing.JLabel();
        pnlPie = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Biblioteca | Lista de espera");
        setMinimumSize(new java.awt.Dimension(1000, 680));
        setPreferredSize(new java.awt.Dimension(1100, 700));

        pnlEncabezado.setBackground(new java.awt.Color(45, 65, 88));
        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 26, 18, 26));
        pnlEncabezado.setLayout(new java.awt.BorderLayout(0, 8));

        lblTitulo.setText("LISTA DE ESPERA");
        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 25)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        pnlEncabezado.add(lblTitulo, java.awt.BorderLayout.NORTH);

        lblSubtitulo.setText("Inscripción y turnos por recurso | Sistema de biblioteca");
        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(219, 231, 244));
        pnlEncabezado.add(lblSubtitulo, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlEncabezado, java.awt.BorderLayout.NORTH);

        pnlContenido.setBackground(new java.awt.Color(244, 248, 252));
        pnlContenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 26, 18, 26));
        pnlContenido.setLayout(new java.awt.BorderLayout(20, 0));

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormulario.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 22, 22, 22));
        pnlFormulario.setPreferredSize(new java.awt.Dimension(360, 440));
        pnlFormulario.setLayout(new java.awt.BorderLayout(0, 14));

        lblDatos.setText("Gestionar inscripción");
        lblDatos.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblDatos.setForeground(new java.awt.Color(48, 87, 125));
        pnlFormulario.add(lblDatos, java.awt.BorderLayout.NORTH);

        pnlCampos.setOpaque(false);
        pnlCampos.setLayout(new java.awt.GridLayout(4, 1, 0, 14));

        pnlRecurso.setOpaque(false);
        pnlRecurso.setLayout(new java.awt.BorderLayout(0, 6));

        lblRecurso.setText("Recurso");
        lblRecurso.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblRecurso.setForeground(new java.awt.Color(43, 56, 73));
        pnlRecurso.add(lblRecurso, java.awt.BorderLayout.NORTH);

        cmbRecursos.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cmbRecursos.setForeground(new java.awt.Color(45, 65, 88));
        cmbRecursos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un recurso" }));
        cmbRecursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRecursosActionPerformed(evt);
            }
        });
        pnlRecurso.add(cmbRecursos, java.awt.BorderLayout.CENTER);

        pnlCampos.add(pnlRecurso);

        pnlUsuario.setOpaque(false);
        pnlUsuario.setLayout(new java.awt.BorderLayout(0, 6));

        lblUsuario.setText("Usuario");
        lblUsuario.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(43, 56, 73));
        pnlUsuario.add(lblUsuario, java.awt.BorderLayout.NORTH);

        cmbUsuarios.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cmbUsuarios.setForeground(new java.awt.Color(45, 65, 88));
        cmbUsuarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un usuario" }));
        cmbUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUsuariosActionPerformed(evt);
            }
        });
        pnlUsuario.add(cmbUsuarios, java.awt.BorderLayout.CENTER);

        pnlCampos.add(pnlUsuario);

        pnlEstadoRecurso.setOpaque(false);
        pnlEstadoRecurso.setLayout(new java.awt.BorderLayout(0, 6));

        lblEstadoRecurso.setText("Estado del recurso");
        lblEstadoRecurso.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblEstadoRecurso.setForeground(new java.awt.Color(43, 56, 73));
        pnlEstadoRecurso.add(lblEstadoRecurso, java.awt.BorderLayout.NORTH);

        txtEstadoRecurso.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtEstadoRecurso.setForeground(new java.awt.Color(45, 65, 88));
        txtEstadoRecurso.setBackground(new java.awt.Color(244, 248, 252));
        txtEstadoRecurso.setEditable(false);
        txtEstadoRecurso.setColumns(18);
        txtEstadoRecurso.setMargin(new java.awt.Insets(2, 7, 2, 7));
        pnlEstadoRecurso.add(txtEstadoRecurso, java.awt.BorderLayout.CENTER);

        pnlCampos.add(pnlEstadoRecurso);

        pnlTotalEnEspera.setOpaque(false);
        pnlTotalEnEspera.setLayout(new java.awt.BorderLayout(0, 6));

        lblTotalEnEspera.setText("Personas en espera");
        lblTotalEnEspera.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblTotalEnEspera.setForeground(new java.awt.Color(43, 56, 73));
        pnlTotalEnEspera.add(lblTotalEnEspera, java.awt.BorderLayout.NORTH);

        txtTotalEnEspera.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTotalEnEspera.setForeground(new java.awt.Color(45, 65, 88));
        txtTotalEnEspera.setBackground(new java.awt.Color(244, 248, 252));
        txtTotalEnEspera.setEditable(false);
        txtTotalEnEspera.setColumns(18);
        txtTotalEnEspera.setMargin(new java.awt.Insets(2, 7, 2, 7));
        pnlTotalEnEspera.add(txtTotalEnEspera, java.awt.BorderLayout.CENTER);

        pnlCampos.add(pnlTotalEnEspera);

        pnlFormulario.add(pnlCampos, java.awt.BorderLayout.CENTER);

        pnlAcciones.setOpaque(false);
        pnlAcciones.setLayout(new java.awt.BorderLayout(0, 8));

        pnlInscripcion.setOpaque(false);
        pnlInscripcion.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        btnInscribir.setText("Inscribir");
        btnInscribir.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnInscribir.setBackground(new java.awt.Color(228, 237, 247));
        btnInscribir.setForeground(new java.awt.Color(45, 65, 88));
        btnInscribir.setEnabled(false);
        btnInscribir.setFocusPainted(false);
        btnInscribir.setContentAreaFilled(false);
        btnInscribir.setOpaque(true);
        btnInscribir.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 16, 9, 16));
        btnInscribir.setPreferredSize(new java.awt.Dimension(112, 38));
        btnInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInscribirActionPerformed(evt);
            }
        });
        pnlInscripcion.add(btnInscribir);

        btnRetirar.setText("Retirar");
        btnRetirar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnRetirar.setBackground(new java.awt.Color(228, 237, 247));
        btnRetirar.setForeground(new java.awt.Color(45, 65, 88));
        btnRetirar.setEnabled(false);
        btnRetirar.setFocusPainted(false);
        btnRetirar.setContentAreaFilled(false);
        btnRetirar.setOpaque(true);
        btnRetirar.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 16, 9, 16));
        btnRetirar.setPreferredSize(new java.awt.Dimension(112, 38));
        btnRetirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetirarActionPerformed(evt);
            }
        });
        pnlInscripcion.add(btnRetirar);

        pnlAcciones.add(pnlInscripcion, java.awt.BorderLayout.NORTH);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLimpiar.setBackground(new java.awt.Color(228, 237, 247));
        btnLimpiar.setForeground(new java.awt.Color(45, 65, 88));
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
        pnlAcciones.add(btnLimpiar, java.awt.BorderLayout.SOUTH);

        pnlFormulario.add(pnlAcciones, java.awt.BorderLayout.SOUTH);

        pnlContenido.add(pnlFormulario, java.awt.BorderLayout.WEST);

        pnlListado.setBackground(new java.awt.Color(255, 255, 255));
        pnlListado.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlListado.setLayout(new java.awt.BorderLayout(0, 16));

        pnlCabeceraTabla.setOpaque(false);
        pnlCabeceraTabla.setLayout(new java.awt.BorderLayout(12, 0));

        lblListado.setText("Orden de espera");
        lblListado.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblListado.setForeground(new java.awt.Color(48, 87, 125));
        pnlCabeceraTabla.add(lblListado, java.awt.BorderLayout.CENTER);

        btnConsultar.setText("Consultar");
        btnConsultar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnConsultar.setBackground(new java.awt.Color(228, 237, 247));
        btnConsultar.setForeground(new java.awt.Color(45, 65, 88));
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

        scrListaEspera.setPreferredSize(new java.awt.Dimension(600, 320));

        tblListaEspera.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        tblListaEspera.setForeground(new java.awt.Color(43, 56, 73));
        tblListaEspera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Posición", "Usuario", "Inscripción", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tblListaEspera.setRowHeight(28);
        tblListaEspera.setFillsViewportHeight(true);
        tblListaEspera.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblListaEspera.setSelectionBackground(new java.awt.Color(219, 231, 244));
        tblListaEspera.setSelectionForeground(new java.awt.Color(45, 65, 88));
        tblListaEspera.setGridColor(new java.awt.Color(225, 233, 242));
        scrListaEspera.setViewportView(tblListaEspera);

        pnlListado.add(scrListaEspera, java.awt.BorderLayout.CENTER);

        lblAyudaTabla.setText("Para retirar una inscripción, selecciona su fila en la tabla.");
        lblAyudaTabla.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblAyudaTabla.setForeground(new java.awt.Color(43, 56, 73));
        pnlListado.add(lblAyudaTabla, java.awt.BorderLayout.SOUTH);

        pnlContenido.add(pnlListado, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlContenido, java.awt.BorderLayout.CENTER);

        pnlPie.setBackground(new java.awt.Color(244, 248, 252));
        pnlPie.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 26, 18, 26));
        pnlPie.setLayout(new java.awt.BorderLayout(20, 0));

        lblEstado.setText("Las solicitudes se atienden según el orden de inscripción de cada recurso.");
        lblEstado.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(43, 56, 73));
        pnlPie.add(lblEstado, java.awt.BorderLayout.CENTER);

        btnCerrar.setText("Cerrar");
        btnCerrar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCerrar.setBackground(new java.awt.Color(228, 237, 247));
        btnCerrar.setForeground(new java.awt.Color(45, 65, 88));
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

    private void btnInscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInscribirActionPerformed
        if (accionInscribir != null) {
            accionInscribir.actionPerformed(evt);
        }
    }//GEN-LAST:event_btnInscribirActionPerformed

    private void btnRetirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetirarActionPerformed
        if (accionRetirar != null) {
            accionRetirar.actionPerformed(evt);
        }
    }//GEN-LAST:event_btnRetirarActionPerformed

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

    private void cmbRecursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRecursosActionPerformed
        notificarSeleccion(evt);
    }//GEN-LAST:event_cmbRecursosActionPerformed

    private void cmbUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUsuariosActionPerformed
        notificarSeleccion(evt);
    }//GEN-LAST:event_cmbUsuariosActionPerformed

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ListaDeEspera().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnInscribir;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRetirar;
    private javax.swing.JComboBox<String> cmbRecursos;
    private javax.swing.JComboBox<String> cmbUsuarios;
    private javax.swing.JLabel lblAyudaTabla;
    private javax.swing.JLabel lblDatos;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblEstadoRecurso;
    private javax.swing.JLabel lblListado;
    private javax.swing.JLabel lblRecurso;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotalEnEspera;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlCabeceraTabla;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPanel pnlEstadoRecurso;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlInscripcion;
    private javax.swing.JPanel pnlListado;
    private javax.swing.JPanel pnlPie;
    private javax.swing.JPanel pnlRecurso;
    private javax.swing.JPanel pnlTotalEnEspera;
    private javax.swing.JPanel pnlUsuario;
    private javax.swing.JScrollPane scrListaEspera;
    private javax.swing.JTable tblListaEspera;
    private javax.swing.JTextField txtEstadoRecurso;
    private javax.swing.JTextField txtTotalEnEspera;
    // End of variables declaration//GEN-END:variables
}
