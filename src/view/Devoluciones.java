package view;

public class Devoluciones extends javax.swing.JFrame {

    private java.awt.event.ActionListener accionDevolver;
    private java.awt.event.ActionListener accionConsultar;
    private java.awt.event.ActionListener accionSeleccion;

    public Devoluciones() {
        initComponents();
        limpiarDetalle();
        tblPrestamos.getTableHeader().setReorderingAllowed(false);
        tblPrestamos.getTableHeader().setFont(new java.awt.Font("SansSerif", 1, 12));
        tblPrestamos.getColumnModel().getColumn(0).setPreferredWidth(48);
        tblPrestamos.getColumnModel().getColumn(1).setPreferredWidth(145);
        tblPrestamos.getColumnModel().getColumn(2).setPreferredWidth(180);
        tblPrestamos.getColumnModel().getColumn(3).setPreferredWidth(115);
        tblPrestamos.getColumnModel().getColumn(4).setPreferredWidth(100);
        setLocationRelativeTo(null);
    }

    public void configurarAcciones(java.awt.event.ActionListener devolver,
                                   java.awt.event.ActionListener consultar) {
        accionDevolver = devolver;
        accionConsultar = consultar;
        btnDevolver.setEnabled(devolver != null);
        btnConsultar.setEnabled(consultar != null);
    }

    public void configurarCambioSeleccion(java.awt.event.ActionListener accion) {
        accionSeleccion = accion;
    }

    public int getIndicePrestamo() {
        int posicion = cmbPrestamos.getSelectedIndex();
        return posicion > 0 ? posicion - 1 : -1;
    }

    public String getFechaDevolucion() {
        return txtFechaDevolucion.getText().trim();
    }

    public void cargarPrestamos(String[] descripciones) {
        cmbPrestamos.removeAllItems();
        cmbPrestamos.addItem("Seleccione un préstamo");
        for (String descripcion : descripciones) {
            cmbPrestamos.addItem(descripcion);
        }
        cmbPrestamos.setSelectedIndex(0);
    }

    public void mostrarDatosPrestamo(String usuario, String recurso, java.time.LocalDate fechaLimite) {
        txtUsuario.setText(usuario);
        txtRecurso.setText(recurso);
        txtFechaLimite.setText(fechaLimite == null ? "" : fechaLimite.toString());
    }

    public void mostrarFechaDevolucion(java.time.LocalDate fecha) {
        txtFechaDevolucion.setText(fecha == null ? "" : fecha.toString());
    }

    public void mostrarResultado(int diasRetraso, java.math.BigDecimal multa) {
        txtDiasRetraso.setText(String.valueOf(diasRetraso));
        txtMulta.setText(multa == null ? "" : multa.toPlainString());
    }

    public void mostrarPrestamos(Object[][] filas) {
        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel) tblPrestamos.getModel();
        modelo.setRowCount(0);
        for (Object[] fila : filas) {
            modelo.addRow(fila);
        }
    }

    public void mostrarEstado(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarFormulario() {
        cmbPrestamos.setSelectedIndex(0);
        limpiarDetalle();
        cmbPrestamos.requestFocusInWindow();
    }

    private void limpiarDetalle() {
        txtUsuario.setText("");
        txtRecurso.setText("");
        txtFechaLimite.setText("");
        txtFechaDevolucion.setText(java.time.LocalDate.now().toString());
        txtDiasRetraso.setText("");
        txtMulta.setText("");
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
        pnlFechaLimite = new javax.swing.JPanel();
        lblFechaLimite = new javax.swing.JLabel();
        txtFechaLimite = new javax.swing.JTextField();
        pnlFechaDevolucion = new javax.swing.JPanel();
        lblFechaDevolucion = new javax.swing.JLabel();
        txtFechaDevolucion = new javax.swing.JTextField();
        pnlResultados = new javax.swing.JPanel();
        pnlDiasRetraso = new javax.swing.JPanel();
        lblDiasRetraso = new javax.swing.JLabel();
        txtDiasRetraso = new javax.swing.JTextField();
        pnlMulta = new javax.swing.JPanel();
        lblMulta = new javax.swing.JLabel();
        txtMulta = new javax.swing.JTextField();
        pnlAcciones = new javax.swing.JPanel();
        btnDevolver = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        pnlListado = new javax.swing.JPanel();
        pnlCabeceraTabla = new javax.swing.JPanel();
        lblListado = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        scrPrestamos = new javax.swing.JScrollPane();
        tblPrestamos = new javax.swing.JTable();
        lblAyudaTabla = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        pnlPie = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Biblioteca | Gestión de devoluciones");
        setMinimumSize(new java.awt.Dimension(1000, 680));
        setPreferredSize(new java.awt.Dimension(1100, 700));

        pnlEncabezado.setBackground(new java.awt.Color(22, 79, 91));
        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 26, 18, 26));
        pnlEncabezado.setLayout(new java.awt.BorderLayout(0, 8));

        lblTitulo.setText("GESTIÓN DE DEVOLUCIONES");
        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 25)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        pnlEncabezado.add(lblTitulo, java.awt.BorderLayout.NORTH);

        lblSubtitulo.setText("Devolución de libros y equipos | Sistema de biblioteca");
        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(216, 237, 239));
        pnlEncabezado.add(lblSubtitulo, java.awt.BorderLayout.SOUTH);

        pnlContenido.setBackground(new java.awt.Color(242, 248, 249));
        pnlContenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 26, 18, 26));

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormulario.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 22, 22, 22));
        pnlFormulario.setPreferredSize(new java.awt.Dimension(360, 440));

        lblDatos.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblDatos.setForeground(new java.awt.Color(23, 99, 108));
        lblDatos.setText("Registrar devolución");

        pnlCampos.setOpaque(false);

        pnlSeleccion.setOpaque(false);
        pnlSeleccion.setPreferredSize(new java.awt.Dimension(316, 56));

        lblPrestamo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblPrestamo.setForeground(new java.awt.Color(36, 58, 65));
        lblPrestamo.setText("Préstamo pendiente");

        cmbPrestamos.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cmbPrestamos.setForeground(new java.awt.Color(22, 79, 91));
        cmbPrestamos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un préstamo" }));
        cmbPrestamos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPrestamosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSeleccionLayout = new javax.swing.GroupLayout(pnlSeleccion);
        pnlSeleccion.setLayout(pnlSeleccionLayout);
        pnlSeleccionLayout.setHorizontalGroup(
            pnlSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cmbPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlSeleccionLayout.setVerticalGroup(
            pnlSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSeleccionLayout.createSequentialGroup()
                .addComponent(lblPrestamo)
                .addGap(6, 6, 6)
                .addComponent(cmbPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlDetalle.setOpaque(false);

        pnlUsuario.setOpaque(false);

        lblUsuario.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(36, 58, 65));
        lblUsuario.setText("Usuario");

        txtUsuario.setEditable(false);
        txtUsuario.setBackground(new java.awt.Color(242, 248, 249));
        txtUsuario.setColumns(10);
        txtUsuario.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(22, 79, 91));
        txtUsuario.setMargin(new java.awt.Insets(2, 7, 2, 7));
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlUsuarioLayout = new javax.swing.GroupLayout(pnlUsuario);
        pnlUsuario.setLayout(pnlUsuarioLayout);
        pnlUsuarioLayout.setHorizontalGroup(
            pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlUsuarioLayout.setVerticalGroup(
            pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuarioLayout.createSequentialGroup()
                .addComponent(lblUsuario)
                .addGap(4, 4, 4)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlRecurso.setOpaque(false);
        pnlRecurso.setLayout(new java.awt.BorderLayout(0, 4));

        lblRecurso.setText("Recurso");
        lblRecurso.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblRecurso.setForeground(new java.awt.Color(36, 58, 65));
        pnlRecurso.add(lblRecurso, java.awt.BorderLayout.NORTH);

        txtRecurso.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtRecurso.setForeground(new java.awt.Color(22, 79, 91));
        txtRecurso.setBackground(new java.awt.Color(242, 248, 249));
        txtRecurso.setEditable(false);
        txtRecurso.setColumns(10);
        txtRecurso.setMargin(new java.awt.Insets(2, 7, 2, 7));
        pnlRecurso.add(txtRecurso, java.awt.BorderLayout.CENTER);

        pnlFechas.setOpaque(false);

        pnlFechaLimite.setOpaque(false);

        lblFechaLimite.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblFechaLimite.setForeground(new java.awt.Color(36, 58, 65));
        lblFechaLimite.setText("Fecha límite");

        txtFechaLimite.setEditable(false);
        txtFechaLimite.setBackground(new java.awt.Color(242, 248, 249));
        txtFechaLimite.setColumns(10);
        txtFechaLimite.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtFechaLimite.setForeground(new java.awt.Color(22, 79, 91));
        txtFechaLimite.setMargin(new java.awt.Insets(2, 7, 2, 7));

        javax.swing.GroupLayout pnlFechaLimiteLayout = new javax.swing.GroupLayout(pnlFechaLimite);
        pnlFechaLimite.setLayout(pnlFechaLimiteLayout);
        pnlFechaLimiteLayout.setHorizontalGroup(
            pnlFechaLimiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFechaLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(txtFechaLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlFechaLimiteLayout.setVerticalGroup(
            pnlFechaLimiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFechaLimiteLayout.createSequentialGroup()
                .addComponent(lblFechaLimite)
                .addGap(4, 4, 4)
                .addComponent(txtFechaLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlFechaDevolucion.setOpaque(false);
        pnlFechaDevolucion.setLayout(new java.awt.BorderLayout(0, 4));

        lblFechaDevolucion.setText("Fecha devolución");
        lblFechaDevolucion.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblFechaDevolucion.setForeground(new java.awt.Color(36, 58, 65));
        pnlFechaDevolucion.add(lblFechaDevolucion, java.awt.BorderLayout.NORTH);

        txtFechaDevolucion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtFechaDevolucion.setForeground(new java.awt.Color(22, 79, 91));
        txtFechaDevolucion.setBackground(new java.awt.Color(242, 248, 249));
        txtFechaDevolucion.setEditable(false);
        txtFechaDevolucion.setColumns(10);
        txtFechaDevolucion.setMargin(new java.awt.Insets(2, 7, 2, 7));
        pnlFechaDevolucion.add(txtFechaDevolucion, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pnlFechasLayout = new javax.swing.GroupLayout(pnlFechas);
        pnlFechas.setLayout(pnlFechasLayout);
        pnlFechasLayout.setHorizontalGroup(
            pnlFechasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFechasLayout.createSequentialGroup()
                .addComponent(pnlFechaLimite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pnlFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlFechasLayout.setVerticalGroup(
            pnlFechasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFechaLimite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlResultados.setOpaque(false);
        pnlResultados.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        pnlDiasRetraso.setOpaque(false);
        pnlDiasRetraso.setLayout(new java.awt.BorderLayout(0, 4));

        lblDiasRetraso.setText("Días de retraso");
        lblDiasRetraso.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblDiasRetraso.setForeground(new java.awt.Color(36, 58, 65));
        pnlDiasRetraso.add(lblDiasRetraso, java.awt.BorderLayout.NORTH);

        txtDiasRetraso.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtDiasRetraso.setForeground(new java.awt.Color(22, 79, 91));
        txtDiasRetraso.setBackground(new java.awt.Color(242, 248, 249));
        txtDiasRetraso.setEditable(false);
        txtDiasRetraso.setColumns(10);
        txtDiasRetraso.setMargin(new java.awt.Insets(2, 7, 2, 7));
        pnlDiasRetraso.add(txtDiasRetraso, java.awt.BorderLayout.CENTER);

        pnlResultados.add(pnlDiasRetraso);

        pnlMulta.setOpaque(false);
        pnlMulta.setLayout(new java.awt.BorderLayout(0, 4));

        lblMulta.setText("Multa");
        lblMulta.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblMulta.setForeground(new java.awt.Color(36, 58, 65));
        pnlMulta.add(lblMulta, java.awt.BorderLayout.NORTH);

        txtMulta.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtMulta.setForeground(new java.awt.Color(22, 79, 91));
        txtMulta.setBackground(new java.awt.Color(242, 248, 249));
        txtMulta.setEditable(false);
        txtMulta.setColumns(10);
        txtMulta.setMargin(new java.awt.Insets(2, 7, 2, 7));
        pnlMulta.add(txtMulta, java.awt.BorderLayout.CENTER);

        pnlResultados.add(pnlMulta);

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlRecurso, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlFechas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(pnlUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(pnlRecurso, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(pnlFechas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(pnlResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlCamposLayout = new javax.swing.GroupLayout(pnlCampos);
        pnlCampos.setLayout(pnlCamposLayout);
        pnlCamposLayout.setHorizontalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlCamposLayout.setVerticalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addComponent(pnlSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlAcciones.setOpaque(false);
        pnlAcciones.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        btnDevolver.setText("Devolver");
        btnDevolver.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnDevolver.setBackground(new java.awt.Color(224, 239, 241));
        btnDevolver.setForeground(new java.awt.Color(22, 79, 91));
        btnDevolver.setEnabled(false);
        btnDevolver.setFocusPainted(false);
        btnDevolver.setContentAreaFilled(false);
        btnDevolver.setOpaque(true);
        btnDevolver.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 16, 9, 16));
        btnDevolver.setPreferredSize(new java.awt.Dimension(112, 38));
        btnDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolverActionPerformed(evt);
            }
        });
        pnlAcciones.add(btnDevolver);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLimpiar.setBackground(new java.awt.Color(224, 239, 241));
        btnLimpiar.setForeground(new java.awt.Color(22, 79, 91));
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
        pnlCabeceraTabla.setLayout(new java.awt.BorderLayout(12, 0));

        lblListado.setText("Préstamos pendientes");
        lblListado.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblListado.setForeground(new java.awt.Color(23, 99, 108));
        pnlCabeceraTabla.add(lblListado, java.awt.BorderLayout.CENTER);

        btnConsultar.setText("Consultar");
        btnConsultar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnConsultar.setBackground(new java.awt.Color(224, 239, 241));
        btnConsultar.setForeground(new java.awt.Color(22, 79, 91));
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

        scrPrestamos.setPreferredSize(new java.awt.Dimension(600, 320));

        tblPrestamos.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        tblPrestamos.setForeground(new java.awt.Color(36, 58, 65));
        tblPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Usuario", "Recurso", "Fecha límite", "Estado"
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
        tblPrestamos.setRowHeight(28);
        tblPrestamos.setFillsViewportHeight(true);
        tblPrestamos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPrestamos.setSelectionBackground(new java.awt.Color(216, 237, 239));
        tblPrestamos.setSelectionForeground(new java.awt.Color(22, 79, 91));
        tblPrestamos.setGridColor(new java.awt.Color(222, 233, 236));
        scrPrestamos.setViewportView(tblPrestamos);

        lblAyudaTabla.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblAyudaTabla.setForeground(new java.awt.Color(36, 58, 65));
        lblAyudaTabla.setText("Selecciona un préstamo para revisar su devolución.");

        btnCerrar.setBackground(new java.awt.Color(224, 239, 241));
        btnCerrar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(22, 79, 91));
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

        javax.swing.GroupLayout pnlListadoLayout = new javax.swing.GroupLayout(pnlListado);
        pnlListado.setLayout(pnlListadoLayout);
        pnlListadoLayout.setHorizontalGroup(
            pnlListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListadoLayout.createSequentialGroup()
                .addComponent(lblAyudaTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlListadoLayout.createSequentialGroup()
                .addGroup(pnlListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCabeceraTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlListadoLayout.setVerticalGroup(
            pnlListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListadoLayout.createSequentialGroup()
                .addComponent(pnlCabeceraTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(scrPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(pnlListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAyudaTabla)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addComponent(pnlFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pnlListado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlListado, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlPie.setBackground(new java.awt.Color(242, 248, 249));
        pnlPie.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 26, 18, 26));

        javax.swing.GroupLayout pnlPieLayout = new javax.swing.GroupLayout(pnlPie);
        pnlPie.setLayout(pnlPieLayout);
        pnlPieLayout.setHorizontalGroup(
            pnlPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 964, Short.MAX_VALUE)
        );
        pnlPieLayout.setVerticalGroup(
            pnlPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlPie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlPie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolverActionPerformed
        if (accionDevolver != null) {
            accionDevolver.actionPerformed(evt);
        }
    }//GEN-LAST:event_btnDevolverActionPerformed

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

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Devoluciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnDevolver;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbPrestamos;
    private javax.swing.JLabel lblAyudaTabla;
    private javax.swing.JLabel lblDatos;
    private javax.swing.JLabel lblDiasRetraso;
    private javax.swing.JLabel lblFechaDevolucion;
    private javax.swing.JLabel lblFechaLimite;
    private javax.swing.JLabel lblListado;
    private javax.swing.JLabel lblMulta;
    private javax.swing.JLabel lblPrestamo;
    private javax.swing.JLabel lblRecurso;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlCabeceraTabla;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlDiasRetraso;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPanel pnlFechaDevolucion;
    private javax.swing.JPanel pnlFechaLimite;
    private javax.swing.JPanel pnlFechas;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlListado;
    private javax.swing.JPanel pnlMulta;
    private javax.swing.JPanel pnlPie;
    private javax.swing.JPanel pnlRecurso;
    private javax.swing.JPanel pnlResultados;
    private javax.swing.JPanel pnlSeleccion;
    private javax.swing.JPanel pnlUsuario;
    private javax.swing.JScrollPane scrPrestamos;
    private javax.swing.JTable tblPrestamos;
    private javax.swing.JTextField txtDiasRetraso;
    private javax.swing.JTextField txtFechaDevolucion;
    private javax.swing.JTextField txtFechaLimite;
    private javax.swing.JTextField txtMulta;
    private javax.swing.JTextField txtRecurso;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
