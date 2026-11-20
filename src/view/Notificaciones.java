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
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Biblioteca | Notificaciones");
        setMinimumSize(new java.awt.Dimension(1000, 680));

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

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormulario.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 22, 22, 22));
        pnlFormulario.setPreferredSize(new java.awt.Dimension(360, 440));

        lblDatos.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblDatos.setForeground(new java.awt.Color(83, 105, 52));
        lblDatos.setText("Consultar avisos");

        pnlCampos.setOpaque(false);

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

        pnlMensaje.setOpaque(false);

        lblMensaje.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblMensaje.setForeground(new java.awt.Color(44, 56, 36));
        lblMensaje.setText("Mensaje completo");

        txtMensaje.setEditable(false);
        txtMensaje.setBackground(new java.awt.Color(247, 250, 242));
        txtMensaje.setColumns(20);
        txtMensaje.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtMensaje.setForeground(new java.awt.Color(44, 56, 36));
        txtMensaje.setLineWrap(true);
        txtMensaje.setRows(4);
        txtMensaje.setWrapStyleWord(true);
        txtMensaje.setMargin(new java.awt.Insets(6, 8, 6, 8));
        scrMensaje.setViewportView(txtMensaje);

        javax.swing.GroupLayout pnlMensajeLayout = new javax.swing.GroupLayout(pnlMensaje);
        pnlMensaje.setLayout(pnlMensajeLayout);
        pnlMensajeLayout.setHorizontalGroup(
            pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(scrMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlMensajeLayout.setVerticalGroup(
            pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMensajeLayout.createSequentialGroup()
                .addComponent(lblMensaje)
                .addGap(6, 6, 6)
                .addComponent(scrMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlCamposLayout = new javax.swing.GroupLayout(pnlCampos);
        pnlCampos.setLayout(pnlCamposLayout);
        pnlCamposLayout.setHorizontalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlCamposLayout.setVerticalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addComponent(pnlFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addComponent(lblDatos)
                .addGap(14, 14, 14)
                .addComponent(pnlCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(pnlAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlListado.setBackground(new java.awt.Color(255, 255, 255));
        pnlListado.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pnlCabeceraTabla.setOpaque(false);

        lblListado.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblListado.setForeground(new java.awt.Color(83, 105, 52));
        lblListado.setText("Avisos del usuario");

        btnConsultar.setBackground(new java.awt.Color(233, 240, 220));
        btnConsultar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnConsultar.setForeground(new java.awt.Color(65, 79, 46));
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

        javax.swing.GroupLayout pnlCabeceraTablaLayout = new javax.swing.GroupLayout(pnlCabeceraTabla);
        pnlCabeceraTabla.setLayout(pnlCabeceraTablaLayout);
        pnlCabeceraTablaLayout.setHorizontalGroup(
            pnlCabeceraTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabeceraTablaLayout.createSequentialGroup()
                .addComponent(lblListado, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlCabeceraTablaLayout.setVerticalGroup(
            pnlCabeceraTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabeceraTablaLayout.createSequentialGroup()
                .addGroup(pnlCabeceraTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblListado, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

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
        tblNotificaciones.setFillsViewportHeight(true);
        tblNotificaciones.setGridColor(new java.awt.Color(226, 234, 215));
        tblNotificaciones.setRowHeight(28);
        tblNotificaciones.setSelectionBackground(new java.awt.Color(226, 236, 208));
        tblNotificaciones.setSelectionForeground(new java.awt.Color(65, 79, 46));
        tblNotificaciones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrNotificaciones.setViewportView(tblNotificaciones);

        lblAyudaTabla.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblAyudaTabla.setForeground(new java.awt.Color(44, 56, 36));
        lblAyudaTabla.setText("Selecciona un aviso para leer el mensaje completo.");

        jButton2.setBackground(new java.awt.Color(0, 102, 51));
        jButton2.setText("Cerrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlListadoLayout = new javax.swing.GroupLayout(pnlListado);
        pnlListado.setLayout(pnlListadoLayout);
        pnlListadoLayout.setHorizontalGroup(
            pnlListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListadoLayout.createSequentialGroup()
                .addGroup(pnlListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlListadoLayout.createSequentialGroup()
                        .addComponent(lblAyudaTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(218, 218, 218)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlCabeceraTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(scrNotificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 87, Short.MAX_VALUE))
        );
        pnlListadoLayout.setVerticalGroup(
            pnlListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListadoLayout.createSequentialGroup()
                .addComponent(pnlCabeceraTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(scrNotificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlListadoLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lblAyudaTabla)
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(pnlListadoLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(pnlListado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlListado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(pnlContenido, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        if (accionConsultar != null) {
            accionConsultar.actionPerformed(evt);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
    private javax.swing.JButton jButton2;
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
