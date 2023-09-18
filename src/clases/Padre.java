package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.undo.UndoManager;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Padre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel, panel_1;
	private JTextArea texto;
	private JPanel barra_estado;
	private JScrollPane scroll;
	private String nombre_archivo = "Sin nombre.txt";
	private JLabel dlinea;
	private JFileChooser ch;
	private FileInputStream fis;
	private JLabel codificasao_1;
	private Color color;
	private JLabel guarda;
	private int guardado = 0;
	private Font f = new Font("Arial", 0, 12);
	private JComboBox<String> comboFuente;
	private JComboBox<String> comboEstilo;
	private JComboBox<String> comboTamano;
	private JScrollPane scrollPane;
	private JPanel panel_2;
	private String cortado = "";
	private final UndoManager um0 = new UndoManager();
	private JMenuItem pegar;
	private JLabel dcolumn;
	private JPanel panel_3;
	private JTextField tbuscar;
	private JMenuItem rehacer;
	private JMenuItem deshacer;
	private JMenuItem mcaracteres;
	private JPanel panel_caracteres;
	private JButton b_unmedio;
	private JButton b_c_circulo;
	private JButton b_ñ_pequena;
	private JButton b_ñ;
	private JButton b_euro;
	private JButton b_punto;
	private JButton b_flechad;
	private JButton b_flechai;
	private JButton b_arroba;
	private JButton b_cedilla;
	private JButton b_cedilla_grande;
	private JButton b_á_grande;
	private JMenuItem Mayusc;
	private JButton b_excl;
	private JButton b_euro_1;
	private JButton b_á_grande_1;
	private JButton b_á_grande_2;
	private JButton b_á_grande_3;
	private JButton b_á_grande_4;
	private JButton b_á_grande_5;
	private JButton b_á_grande_6;
	private JButton b_á_grande_7;
	private JButton b_á_grande_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Padre frame = new Padre();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public boolean exist() {
		try {
			if (texto.getText().isEmpty() || texto.getText().isBlank()) {
				return true;// Con este metodo veo si existe este objeto y asi en la clase Tutores puedo
							// poner el JTextfield enabled o no
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	private void guardarArchivo() {
		JFileChooser jfguardar = new JFileChooser();// Creo un objeto de selector de archivos
		jfguardar.setAcceptAllFileFilterUsed(false);// Le obligo a no poder usar otros filtros NO especificado
		// Filtro para solo .txt
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos .txt", "txt");// Creo el filtro el cual
																								// SI quiero que use y
																								// solo pueda ver

		jfguardar.setFileFilter(filter);// Le aplico el filtro
		jfguardar.setSelectedFile(new File(nombre_archivo));// Esta linea me pone el nombre del archivo que este en el
															// momento en el nombre del archivo al guardarlo

		int p = jfguardar.showSaveDialog(null);// Me abre el menu de guardado

		if (p == JFileChooser.APPROVE_OPTION) {// Si ha hecho el guardado entra aqui
			if (!nombre_archivo.equals("Sin nombre")) {
				nombre_archivo = jfguardar.getSelectedFile().getName();// Me coge el nombre que haya puesto
				// En el caso de que el usuario haya renombrado el archivo, se sobreescribe el
				// nombre de por defecto
			}
			try {
				String arc = jfguardar.getCurrentDirectory() + "\\" + nombre_archivo + ".txt";// Me permite recoger la
																								// direccion exacta del
																								// archivo para usarla
																								// mas tarde

				File fc = new File(arc);// Creo el archivo en esa ruta guardada para poder comprobar si existe ANTES de
										// guardarlo
				if (fc.exists()) {// En el caso de que exista
					String[] options = { "Sobreescribir", "Renombrar y guardar", "Cancelar" };

					int z = JOptionPane.showOptionDialog(null,
							"Ya existe el archivo " + nombre_archivo + "\n¿Que desea hacer?", "Bloc de Jorge",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
					// Le propongo las opciones que tiene disponible en el caso de que exista el
					// documento
					if (z == 0) {// Si elije sobreescribir
						FileWriter ficheroP = new FileWriter(// Me guarda el txt
								arc, false);// Me sobreescribe al especificar "false" y me usa la direccion guardada de
											// antes para ponerlo en esa ruta

						PrintWriter pwP = new PrintWriter(ficheroP);
						ficheroP.write(texto.getText());
						pwP.println();
						pwP.flush();
						ficheroP.flush();
						pwP.close();
						ficheroP.close();// Escritura finalizada
						guardado = 1;// Si se ha escrito quiere decir que se ha guardado, uso esto mas tarde para que
										// no pregunte que quiere hacer con el documente
						nombre_archivo = jfguardar.getSelectedFile().getName();
						
						setTitle(nombre_archivo + " - Bloc de Jorge");
					} else if (z == 1) {// Si elije renombrar y guardar

						nombre_archivo = JOptionPane
								.showInputDialog("Porfavor, introduzca el nuevo nombre del archivo:");// Pido el nombre
																										// para
																										// guardarlo
						String arc2 = jfguardar.getCurrentDirectory() + "\\" + nombre_archivo + ".txt";// lo mismo de
																										// comprobacion

						File ck2 = new File(arc2);// Otra comprobacion por si acaso
						if (ck2.exists()) {
							while (ck2.exists()) {// De este bucle no sale a no ser que introduzca un nombre que no
													// exista en esa ruta
								nombre_archivo = JOptionPane
										.showInputDialog("Ese nombre ya está en uso. Porfavor, introduzca otro");
								arc2 = jfguardar.getCurrentDirectory() + "\\" + nombre_archivo + ".txt";
								ck2 = new File(arc2);

							}
						}

						FileWriter ficheroP = new FileWriter(// Me escribe el nuevo documento
								arc2, false);

						PrintWriter pwP = new PrintWriter(ficheroP);
						ficheroP.write(texto.getText());
						pwP.println();
						pwP.flush();
						ficheroP.flush();
						pwP.close();
						ficheroP.close();// Documento escrito
						guardado = 1;
						setTitle(nombre_archivo + " - Bloc de Jorge");
					}

				} else {// En el caso de que no exista ese documento en la ruta
					FileWriter ficheroP = new FileWriter(arc, true);

					PrintWriter pwP = new PrintWriter(ficheroP);
					ficheroP.write(texto.getText());
					pwP.println();
					pwP.flush();
					ficheroP.flush();
					pwP.close();
					ficheroP.close();// Documento escrito
					guardado = 1;
					setTitle(nombre_archivo + " - Bloc de Jorge");

				}
			} catch (IOException e1) {
				// Control de errores
			}

		}
		if (guardado == 1) {// Para que el usuario sepa que se ha guardado se lo muestro
			guarda.setText("Sí");
		} else {
			guarda.setText("No");
		}

	}

	/**
	 * Create the frame.
	 */
	private void abrirArchivo() {
		// Antes de abrirlo, miro si hay algo escrito y si es así pregunto si quiere
		// conservarlo
		if (texto.getText().isBlank() || guardado == 1) {// Compruebo si no hay nada escrito
			ch = new JFileChooser();
			texto.setText(null);

			FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos .txt", "txt");// Filtro para mas
																									// tarde de solo
																									// .txt

			ch.setAcceptAllFileFilterUsed(false);// Niego filtros que no sean aplicados
			ch.setFileFilter(filter);// Le obligo a usar mi filtro

			ch.setVisible(true);

			int returnVal = ch.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {// Si confirma la apertura
				nombre_archivo = ch.getSelectedFile().getName();// Guardo el nombre de ese archivo para tratarlo despues
				setTitle(nombre_archivo.replace(".txt", "") + " - Bloc de Jorge");// Para mostrar el nombre al usuario

				File file = new File(ch.getSelectedFile().toString());// Me creo el archivo con ese nombre y extension

				try {
					@SuppressWarnings("resource")
					Scanner sc = new Scanner(file);// Le paso el archivo al escaner
					String data = "";
					while (sc.hasNextLine()) {
						data+= sc.nextLine() + "\n";// Esto hace que por cada salto de linea del documento, lo
															// muestre en el areaText
					// Me inserta la fila de texto correspondiente en la siguiente fila
					}
					texto.insert(data, 0);
					cargarBarraEstado();// Actualiza la barra de estado para los nuevos datos
					guardado = 1;// Si el archivo se acaba de abrir es porque esta guardado

				} catch (FileNotFoundException e1) {
					// Errores...
				}

			}
		} else {// En el caso de que haya texto en el textArea y vamos a intentar abrir otro
				// documento
			String[] options = { "Guardar", "No guardar", "Cancelar" };

			int z = JOptionPane.showOptionDialog(null, "¿Quiere guardar los cambios de " + nombre_archivo + "?",
					"Bloc de Jorge", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
					options[0]);// Pregunto que se quiere hacer
			if (z == 0) {// Si se quiere guardar le inicio el metodo del guardado
				guardarArchivo();
			} else if (z == 1) {// Si quiere No guardarlo pues simplemente hago la apertura normal
				JFileChooser ch = new JFileChooser();

				FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos .txt", "txt");

				ch.setAcceptAllFileFilterUsed(false);
				ch.setFileFilter(filter);
				ch.setVisible(true);

				int returnVal = ch.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = new File(ch.getSelectedFile().toString());
					try {
						@SuppressWarnings("resource")
						Scanner sc = new Scanner(file);
						texto.setText(null);
						while (sc.hasNextLine()) {
							String data = sc.nextLine();
							texto.insert(data, 0);

						}
						dlinea.setText(Integer.toString(texto.getLineCount()));

					} catch (FileNotFoundException e1) {

					}

				}
			}
		}
		if (guardado == 1) {
			guarda.setText("Sí");
		} else {
			guarda.setText("No");
		}

	}

	private void confirmarSalir() {// Metodo para salir
		if (texto.getText().isBlank() || guardado == 1) {// En el caso de que no haya texto escrito o el documento este
															// guardado
			dispose();
		} else {// De lo contrario
			String[] options = { "Guardar", "No guardar", "Cancelar" };
			int x = JOptionPane.showOptionDialog(null, "¿Quiere guardar los cambios de " + nombre_archivo + "?",
					"Bloc de Jorge", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
					options[0]);// Pregunto...
			if (x == 1) {// Si no quiere guardar lo cierro
				dispose();
			} else if (x == 0) {// Si quiere guardar recurro al metodo de guardado
				guardarArchivo();
			}
		}
	}

	private void crearJMenu() {// Me crea el JMenuBar con todos sus componentes, listeners...
		JMenuBar menuBar = new JMenuBar();
		menuBar.setSize(getWidth() / 6, getHeight() / 6);
		setJMenuBar(menuBar);// Lo añado a mi Frame

		JMenu archivo = new JMenu("Archivo");
		archivo.setMnemonic('A');
		archivo.setFont(new Font("Dialog", Font.BOLD, 13));
		menuBar.add(archivo);

		JMenuItem nuevo = new JMenuItem("Nuevo \t          Alt+N\r\n");
		nuevo.setMnemonic('n');
		nuevo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {// En el caso de que se quiera hacer un nuevo documento

				if (texto.getText().isBlank()) {// Si no hay nada escrito hace uno nuevo y ya
					dispose();
					new Padre().setVisible(true);
				} else {// De lo contrario
					String[] options = { "Guardar", "No guardar", "Cancelar" };

					int x = JOptionPane.showOptionDialog(null, "¿Quiere guardar los cambios de " + nombre_archivo + "?",
							"Bloc de Jorge", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
							options[0]);// Pregunto...
					if (x == 1) {// Si no quiere guardar lo que esta escrito
						dispose();
						new Padre().setVisible(true);
					} else if (x == 0) {// Si quiere guardarlo invoco al metodo de guardar
						guardarArchivo();

					}

				}

			}
		});

		nuevo.setFont(new Font("Dialog", Font.BOLD, 12));
		archivo.add(nuevo);

		JMenuItem abrir = new JMenuItem("Abrir             Alt+A");
		abrir.setMnemonic('a');
		abrir.setFont(new Font("Dialog", Font.BOLD, 12));
		abrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirArchivo();// Para abrir un archivo invoco el metodo de abrir
			}
		});
		archivo.add(abrir);

		JMenuItem renombrar = new JMenuItem("Renombrar           Alt+R");
		renombrar.setMnemonic('r');
		renombrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombre_archivo = JOptionPane.showInputDialog("Introduzca el nombre que desee");// Para cambiar el nombre
																								// del archivo

				setTitle(nombre_archivo + " - Bloc de Jorge");

			}
		});
		renombrar.setFont(new Font("Dialog", Font.BOLD, 12));
		archivo.add(renombrar);

		JMenuItem guardar = new JMenuItem("Guardar Como...       Alt+G");
		guardar.setMnemonic('g');
		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarArchivo();// Guardar...
			}
		});
		guardar.setFont(new Font("Dialog", Font.BOLD, 12));
		archivo.add(guardar);

		JMenuItem salir = new JMenuItem("Salir              Atl+S");
		salir.setMnemonic('s');
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmarSalir();// No le permito salir directamente, antes invoco a este metodo para comprobar
									// si hay cambios
			}
		});
		salir.setFont(new Font("Dialog", Font.BOLD, 12));
		archivo.add(salir);

		JMenu editar = new JMenu("Edición");
		editar.setMnemonic('E');
		editar.setFont(new Font("Dialog", Font.BOLD, 13));
		menuBar.add(editar);

		deshacer = new JMenuItem("Deshacer       Alt+Z");
		deshacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (um0.canUndo()) {
					um0.undo();
					deshacer.setEnabled(true);// Si se puede deshacer el ultimo cambio del textArea lo hace
				}

			}
		});
		deshacer.setMnemonic('Z');
		deshacer.setEnabled(false);
		deshacer.setFont(new Font("Dialog", Font.BOLD, 12));
		editar.add(deshacer);

		JMenuItem cortar = new JMenuItem("Cortar             Alt+X");
		cortar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cortado = texto.getSelectedText();// Guardo el texto seleccionado
					texto.setText(texto.getText().replace(texto.getSelectedText(), ""));// Ese mismo texto
																						// seleccionado
																						// lo elimino del textArea
					pegar.setEnabled(true);// Puedes pegar el texto

				} catch (Exception e2) {

				}
			}
		});

		rehacer = new JMenuItem("Rehacer        Alt+Y");
		rehacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (um0.canRedo()) {
					um0.redo();// Me rehace el ultimo cambio hecho, por cada vez usado retrocede un cambio, en
								// este caso un caracter
					rehacer.setEnabled(true);
				}
			}
		});
		rehacer.setMnemonic('Z');
		rehacer.setEnabled(false);
		rehacer.setFont(new Font("Dialog", Font.BOLD, 12));
		editar.add(rehacer);
		cortar.setMnemonic('X');
		cortar.setFont(new Font("Dialog", Font.BOLD, 12));
		editar.add(cortar);

		JMenuItem copiar = new JMenuItem("Copiar             Alt+C");
		copiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Es exactamente igual que cortar a excepcion de que no elimino el texto
					// seleccionado
					cortado = texto.getSelectedText();
					pegar.setEnabled(true);
				} catch (Exception e2) {

				}
			}
		});
		copiar.setMnemonic('C');
		copiar.setFont(new Font("Dialog", Font.BOLD, 12));
		editar.add(copiar);

		pegar = new JMenuItem("Pegar              Alt+V");
		pegar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// El metodo insert me permite añadir texto en una posicion concreta, actua
				// similar al metodo append porque NO me sobreescribe el texto
				texto.insert(cortado, texto.getCaretPosition());
				// El metodo getCaretPosition, el cual es muy util, me 'recoge' en que parte del
				// texto del texArea está mi raton focuseado:
				// De otra manera explicado:
				// Me recoge DONDE esta la seleccion de mi raton para justo en esa posicion del
				// textArea me inserte (de ahi insert()), el texto deseado

			}
		});
		if (cortado.isEmpty() || cortado.isBlank()) {// Si no hay texto copiado o cortado no puedes usar esta opcion
			pegar.setEnabled(false);
		} else {
			pegar.setEnabled(true);

		}
		;
		pegar.setMnemonic('V');
		pegar.setFont(new Font("Dialog", Font.BOLD, 12));
		editar.add(pegar);

		JMenuItem eliminar = new JMenuItem("Eliminar         Supr");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {// Simplemente me elimina el texto seleccionado
					texto.setText(texto.getText().replace(texto.getSelectedText(), null));
				} catch (Exception e2) {

				}
			}
		});
		eliminar.setFont(new Font("Dialog", Font.BOLD, 12));
		editar.add(eliminar);

		JMenuItem buscar = new JMenuItem("Buscar / Reemplazar  Alt+B");
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel_3.isVisible()) {
					panel_3.setVisible(false);
					scrollPane.setRowHeaderView(null);
				} else {
					panel_3.setVisible(true);
					scrollPane.setRowHeaderView(panel_3);
				}

			}
		});
		buscar.setMnemonic('B');
		buscar.setFont(new Font("Dialog", Font.BOLD, 12));
		editar.add(buscar);

		JMenuItem sel_todo = new JMenuItem("Seleccionar todo     Alt+A");
		sel_todo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				texto.selectAll();// Selecciona todo el texto del textArea
			}
		});
		sel_todo.setMnemonic('A');
		sel_todo.setFont(new Font("Dialog", Font.BOLD, 12));
		editar.add(sel_todo);

		Mayusc = new JMenuItem("Mayusc          Alt+M");
		Mayusc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				texto.replaceSelection(texto.getSelectedText().toUpperCase());
			}
		});
		Mayusc.setMnemonic('C');
		Mayusc.setFont(new Font("Dialog", Font.BOLD, 12));
		editar.add(Mayusc);

		JMenuItem Minusc = new JMenuItem("Minusc           Alt+I");
		Minusc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				texto.replaceSelection(texto.getSelectedText().toLowerCase());
			}
		});
		Minusc.setMnemonic('I');
		Minusc.setFont(new Font("Dialog", Font.BOLD, 12));
		editar.add(Minusc);

		JMenu formato = new JMenu("Formato");
		formato.setMnemonic('F');
		formato.setFont(new Font("Dialog", Font.BOLD, 13));
		menuBar.add(formato);

		JMenuItem fuente = new JMenuItem("Fuente           Alt+U");
		fuente.setMnemonic('U');
		fuente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Me muestra o oculta el menu de personalizacion de la fuente dependiendo del
				// caso
				if (panel_2.isVisible()) {
					panel_2.setVisible(false);
					scrollPane.setColumnHeaderView(null);

				} else {
					panel_2.setVisible(true);
					scrollPane.setColumnHeaderView(panel_2);

				}

			}
		});
		fuente.setFont(new Font("Dialog", Font.BOLD, 12));
		formato.add(fuente);

		JMenuItem itemcolor = new JMenuItem("Color...           Alt+C");
		itemcolor.setMnemonic('C');
		itemcolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestra un seleccionador de colores para poner ese color al texto del
				// textArea
				color = JColorChooser.showDialog(null, "Seleccione un Color", Color.BLUE);
				texto.setForeground(color);
			}
		});
		itemcolor.setFont(new Font("Dialog", Font.BOLD, 12));
		formato.add(itemcolor);

		JMenu ver = new JMenu("Ver");
		ver.setMnemonic('V');
		ver.setFont(new Font("Dialog", Font.BOLD, 13));
		menuBar.add(ver);

		JMenuItem fuente_2_2 = new JMenuItem("Barra de estado       Alt+B");
		fuente_2_2.setMnemonic('B');
		fuente_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Me muestra/oculta la barra de estado
				if (barra_estado.isVisible()) {
					barra_estado.setVisible(false);
					scroll.setSize(panel.getWidth(), panel.getHeight());

					// Si no hay barra de estado el textArea se auto ajusta a las dimensiones
					// maximas posibles
				} else {

					barra_estado.setVisible(true);
					panel_caracteres.setVisible(false);
					scroll.setSize(panel.getWidth(), panel.getHeight() - barra_estado.getHeight());
					contentPane.add(barra_estado, BorderLayout.SOUTH);
					// Si hay barra de estado se ajusta a las nuevas dimensiones
				}
			}
		});
		fuente_2_2.setFont(new Font("Dialog", Font.BOLD, 12));
		ver.add(fuente_2_2);

		mcaracteres = new JMenuItem("Carácteres especiales      Alt+E");
		mcaracteres.setVisible(true);
		mcaracteres.addActionListener(new ActionListener() {
			// De esta manera se alterna la barra de estado con el panel de caracteres
			// especiales
			public void actionPerformed(ActionEvent e) {
				if (!panel_caracteres.isVisible()) {
					panel_caracteres.setVisible(true);
					barra_estado.setVisible(false);
					contentPane.add(panel_caracteres, BorderLayout.SOUTH);

				} else {
					panel_caracteres.setVisible(false);

				}

			}
		});
		mcaracteres.setMnemonic('E');
		mcaracteres.setFont(new Font("Dialog", Font.BOLD, 12));
		ver.add(mcaracteres);

		JMenu menuayuda = new JMenu("Ayuda");
		menuayuda.setMnemonic('Y');
		menuayuda.setFont(new Font("Dialog", Font.BOLD, 13));
		menuBar.add(menuayuda);

		JMenuItem verayuda = new JMenuItem("Ver ayuda      Alt+V");
		verayuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File docu = new File("src/aplicaciones/codigo_documentado_editor_texto.txt");
				try {
					Desktop.getDesktop().open(docu);
				} catch (IOException e1) {

				}
			}
		});
		verayuda.setMnemonic('V');
		verayuda.setFont(new Font("Dialog", Font.BOLD, 12));
		menuayuda.add(verayuda);
	}

	private void cargarBarraEstado() {
		try {

			fis = new FileInputStream(ch.getSelectedFile());// Guardo el tipo de stream de entrada del archivo
			InputStreamReader inft = new InputStreamReader(fis);// Lo leo para poder tratarlo
			codificasao_1.setText(inft.getEncoding());// Le extraigo el tipo de codificacion
		} catch (Exception e) {
			if (guardado == 1) {// Si no se puede
				JOptionPane.showMessageDialog(null, "No se ha podido identificar la codificación del archivo");
			}

		}
		if (guardado == 1) {// Comprobacion de guardado
			guarda.setText("Sí");
		} else {
			guarda.setText("No");
		}
		dlinea.setText(Integer.toString(texto.getLineCount()));
		// Me permite sabes cuantas lineas existen
	}

	private void cargarFuentes() {

		panel_2 = new JPanel();

		JLabel lblNewLabel = new JLabel("Fuente:");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		panel_2.add(lblNewLabel);

		comboFuente = new JComboBox<String>();
		comboFuente.setFont(new Font("Dialog", Font.PLAIN, 10));
		panel_2.add(comboFuente);

		JLabel lblEstiloDeFuente = new JLabel("Estilo de fuente:");
		lblEstiloDeFuente.setFont(new Font("Dialog", Font.PLAIN, 12));
		panel_2.add(lblEstiloDeFuente);

		comboEstilo = new JComboBox<String>();
		comboEstilo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel_2.add(comboEstilo);

		JLabel lblTamao = new JLabel("Tamaño:");
		lblTamao.setFont(new Font("Dialog", Font.PLAIN, 12));
		panel_2.add(lblTamao);

		comboTamano = new JComboBox<String>();
		panel_2.add(comboTamano);

		// Me permite almacenar todos los posibles fonts que Eclipse puede procesar
		String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fonts[0] = "Arial";
		comboFuente.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Extraigo el font seleccionado del comboBox
				f = new Font(comboFuente.getSelectedItem().toString(), comboEstilo.getSelectedIndex(),
						Integer.parseInt(comboTamano.getSelectedItem().toString()));
				// Se lo aplico a el textArea
				texto.setFont(f);
			}
		});
		comboFuente.setModel(new DefaultComboBoxModel<String>(fonts));// Le paso el array de fonts
		comboEstilo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lo mismo que el anterior
				f = new Font(comboFuente.getSelectedItem().toString(), comboEstilo.getSelectedIndex(),
						Integer.parseInt(comboTamano.getSelectedItem().toString()));
				texto.setFont(f);

			}
		});
		comboEstilo.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Normal", "Negrita", "Cursiva", "Negrita Cursiva" }));

		comboTamano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f = new Font(comboFuente.getSelectedItem().toString(), comboEstilo.getSelectedIndex(),
						Integer.parseInt(comboTamano.getSelectedItem().toString()));
				texto.setFont(f);
				// Aqui hay suerte, en Eclipse los tipos de letra se almacenan en ingles pero en
				// el mismo orden que en español asi que puedo usar el getSelectedIndex
			}
		});
		int arr[] = new int[50];
		for (int i = 14; i < arr.length; i++) {
			comboTamano.addItem(Integer.toString(i));// Solo quería los primero 34 tamanos
		}

		panel_2.setVisible(false);// Que no se vea por defecto
	}

	private void cargarBuscar() {
		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Buscar",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setVisible(false);
		scrollPane.setRowHeaderView(null);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("Buscar:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_1);

		tbuscar = new JTextField();
		tbuscar.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_3.add(tbuscar);
		tbuscar.setColumns(10);

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tbuscar.getText().isEmpty()) {

					String s = texto.getText();
					if (!s.contains(tbuscar.getText())) {
						JOptionPane.showMessageDialog(null, "No se ha encontrado: \"" + tbuscar.getText() + "\"");
					} else {

						int n = s.indexOf(tbuscar.getText());
						texto.requestFocus();
						texto.select(n, n + tbuscar.getText().length());
					}

				}
			}
		});
		panel_3.add(btnNewButton);

		JLabel lblNewLabel_2 = new JLabel("Reemplazar por:");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_3.add(lblNewLabel_2);

		JTextField treemplazar = new JTextField();
		treemplazar.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_3.add(treemplazar);
		treemplazar.setColumns(10);

		JButton btnNewButton_1 = new JButton("Reemplazar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tbuscar.getText().isEmpty() && !treemplazar.getText().isEmpty()) {

					String s = texto.getText();
					if (!s.contains(tbuscar.getText())) {
						JOptionPane.showMessageDialog(null, "No se ha encontrado: \"" + tbuscar.getText() + "\"");
					} else {

						int n = s.indexOf(tbuscar.getText());
						texto.requestFocus();
						texto.select(n, n + tbuscar.getText().length());
						texto.replaceSelection(treemplazar.getText());
						texto.select(n, n + treemplazar.getText().length());

					}

				}
			}
		});
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		panel_3.add(btnNewButton_1);
	}

	public Padre() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Padre.class.getResource("/clases/Icono_Bloc_Jorge.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// Que no se cierre al cerrar xd
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				confirmarSalir();// Si se quiere cerrar que pregunte antes y compruebe

			}
		});

		panel = new JPanel();
		// Con esto puedo calcular la resolucion por pantalla y asi ponerle el tamaño
		// que quiera sin que haya descompensaciones
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();

		setMinimumSize(new Dimension((int) d.getWidth() / 2, (int) d.getHeight() / 2));// Para que haya un tamano minimo
																						// de ventana
		int ancho = (int) d.getWidth() / 2;
		int alto = (int) d.getHeight() / 2;
		setSize(ancho, alto);

		panel.setLayout(new BorderLayout());
		crearJMenu();// Creacion del MenuBar
		scroll = new JScrollPane();// Me ayudara a que sea deslizable el textArea en el caso que lo requira
		panel.add(scroll, BorderLayout.CENTER);

		texto = new JTextArea();
		texto.setLineWrap(true);// Hace que el texto salte de linea cuando llegue al final de una linea
		scroll.setViewportView(texto);

		barra_estado = new JPanel();

		barra_estado.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Barra de estado", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		barra_estado.setVisible(true);
		// contentPane.add(barra_estado, BorderLayout.SOUTH);
		barra_estado.setBounds(0, 254, ancho, alto);
		// contentPane.add(barra_estado, BorderLayout.SOUTH);

		panel_1 = new JPanel();
		barra_estado.add(panel_1);

		JLabel linea = new JLabel("Línea:");
		panel_1.add(linea);

		dlinea = new JLabel("0");
		panel_1.add(dlinea);

		JLabel lblColumna = new JLabel("Columna:");
		panel_1.add(lblColumna);

		dcolumn = new JLabel("1");
		panel_1.add(dcolumn);

		String n = nombre_archivo.replace(".txt", "");
		setTitle(n + " - Bloc de Jorge");

		JPanel panel_1_2 = new JPanel();
		barra_estado.add(panel_1_2);

		JLabel lguardado = new JLabel("Guardado:");
		panel_1_2.add(lguardado);

		guarda = new JLabel("");
		if (guardado == 1) {// Por si en un futuro es una app externa y permite que archivos txt se abran
							// con el, asi detectaria si esta guardado
			guarda.setText("Sí");
		} else {
			guarda.setText("No");
		}
		panel_1_2.add(guarda);

		JPanel panel_1_3 = new JPanel();
		barra_estado.add(panel_1_3);

		JLabel codificasao = new JLabel("Codificación:");
		panel_1_3.add(codificasao);

		codificasao_1 = new JLabel("Ninguna");
		panel_1_3.add(codificasao_1);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);// Asi nunca sale la
																								// barra horizontal
		contentPane.add(scrollPane, BorderLayout.CENTER);

		texto = new JTextArea();
		texto.setLineWrap(true);
		scrollPane.setViewportView(texto);

		panel_caracteres = new JPanel();
		panel_caracteres.setBorder(new TitledBorder(null, "Car\u00E1cteres especiales", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_caracteres.setVisible(false);// ppp
		contentPane.add(barra_estado, BorderLayout.SOUTH);

		b_unmedio = new JButton("½");
		b_unmedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "½", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}

			}
		});
		b_unmedio.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_unmedio);

		b_c_circulo = new JButton("º");
		b_c_circulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "º", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_c_circulo.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_c_circulo);

		b_ñ = new JButton("Ñ");
		b_ñ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "Ñ", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});

		b_á_grande = new JButton("Á");
		b_á_grande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "Á", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}

		});
		b_á_grande.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_á_grande);

		JButton b_á_ = new JButton("á");
		b_á_.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "á", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_á_.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_á_);

		b_á_grande_1 = new JButton("Í");
		b_á_grande_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "Í", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_á_grande_1.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_á_grande_1);

		b_á_grande_2 = new JButton("í");
		b_á_grande_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "í", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_á_grande_2.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_á_grande_2);

		b_á_grande_3 = new JButton("Ú");
		b_á_grande_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "Ú", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_á_grande_3.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_á_grande_3);

		b_á_grande_4 = new JButton("ú");
		b_á_grande_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "ú", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_á_grande_4.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_á_grande_4);

		b_á_grande_5 = new JButton("É");
		b_á_grande_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "É", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_á_grande_5.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_á_grande_5);

		b_á_grande_6 = new JButton("é");
		b_á_grande_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "é", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_á_grande_6.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_á_grande_6);
		
		b_á_grande_7 = new JButton("Ó");
		b_á_grande_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "Ó", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_á_grande_7.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_á_grande_7);
		
		b_á_grande_8 = new JButton("ó");
		b_á_grande_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "ó", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_á_grande_8.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_á_grande_8);
		b_ñ.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_ñ);

		b_ñ_pequena = new JButton("ñ");
		b_ñ_pequena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "ñ", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_ñ_pequena.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_ñ_pequena);

		b_euro = new JButton("€");
		b_euro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "€", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_euro.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_euro);

		b_punto = new JButton("•");
		b_punto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "•", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_punto.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_punto);

		b_flechad = new JButton("→");
		b_flechad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "→", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_flechad.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_flechad);

		b_flechai = new JButton("←");
		b_flechai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "←", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_flechai.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_flechai);

		b_arroba = new JButton("@");
		b_arroba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "@", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_arroba.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_arroba);

		b_cedilla = new JButton("ç");
		b_cedilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "ç", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_cedilla.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_cedilla);

		b_cedilla_grande = new JButton("Ç");
		b_cedilla_grande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "Ç", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_cedilla_grande.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_cedilla_grande);

		b_excl = new JButton("¡");
		b_excl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "¡", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}
			}
		});
		b_excl.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_excl);

		b_euro_1 = new JButton("¿");
		b_euro_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					texto.getDocument().insertString(texto.getCaretPosition(), "¿", null);
					texto.requestFocus();
				} catch (BadLocationException e1) {

				}

			}
		});
		b_euro_1.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_caracteres.add(b_euro_1);

		texto.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

				guardado = 0;
				guarda.setText("No");

			}

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

		});
		cargarBarraEstado();// Cargar barra estado...
		cargarFuentes();// Cargar fuentes...
		cargarBuscar();// Carga el menu buscar...
		texto.addCaretListener(new CaretListener() {// Listener de cambio de focus del raton
			public void caretUpdate(CaretEvent e) {
				texto = (JTextArea) e.getSource();// Me recoge que el cambio haya sido dentro del textArea
				if (um0.canRedo()) {
					rehacer.setEnabled(true);// Si puedes rehacer te permite
				} else {
					rehacer.setEnabled(false);
				}
				if (um0.canUndo()) {
					deshacer.setEnabled(true);// Si puedes deshacer te permite
				} else {
					deshacer.setEnabled(false);
				}
				int linenum = 1;// Valor inicial de linea
				int columnnum = 1;// Valor inicial de columna
				try {
					int caretpos = texto.getCaretPosition();// Me pilla la posicion en la que esta el raton
					linenum = texto.getLineOfOffset(caretpos);// Guardo el numero de linea en la que esta el raton

					columnnum = caretpos - texto.getLineStartOffset(linenum);// Lo mismo para la columna

					linenum += 1;// Incrementa
				} catch (Exception ex) {
				}

				dlinea.setText(Integer.toString(linenum));// Muestro
				dcolumn.setText(Integer.toString(columnnum));// Muestro
			}
		});
		texto.getDocument().addUndoableEditListener(um0);// Un escuchador que me permite detecatar cambios de los que
															// puedes retroceder o avanzar

	}

}