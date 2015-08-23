package br.com.opencv.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class TelaTecnicas extends JFrame {

	private static final long serialVersionUID = 1L;

	private DefaultListModel<String> listaEscolhaPi, listaSelecaoPi;
	private JList<String> componenteListaEscolhaPi, componenteListaSelecaoPi;
	private JScrollPane rolagemListaEscolhaPi, rolagemListaSelecaoPi;
	private JButton botaoAdiciona, botaoRemove;
	private GridBagConstraints gbc;

	public TelaTecnicas() {
		super("Processamento de Imagem");
		carregaComponentes();
		adicionaLayouts();
		customizaComponentes();
		adicionaComponentes();
		adicionaComandos();
		setVisible(true);
	}

	private void carregaComponentes() {
		gbc = new GridBagConstraints();
		listaEscolhaPi = new DefaultListModel<String>();
		listaSelecaoPi = new DefaultListModel<String>();
		componenteListaEscolhaPi = new JList<String>(listaEscolhaPi);
		componenteListaSelecaoPi = new JList<String>(listaSelecaoPi);
		rolagemListaEscolhaPi = new JScrollPane(componenteListaEscolhaPi);
		rolagemListaSelecaoPi = new JScrollPane(componenteListaSelecaoPi);
		botaoAdiciona = new JButton(">");
		botaoRemove = new JButton("<");
	}

	private void adicionaLayouts() {
		getContentPane().setLayout(new GridBagLayout());
	}

	private void customizaComponentes() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(550, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		componenteListaEscolhaPi.setBorder(BorderFactory
				.createTitledBorder("Tecnicas Disponiveis"));
		componenteListaSelecaoPi.setBorder(BorderFactory
				.createTitledBorder("Tecnicas Adicionadas"));
	}

	private void adicionaComponentes() {

		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.NORTHWEST;

		gbc.weightx = 50;
		gbc.weighty = 3;
		gbc.gridwidth = 2;
		gbc.gridheight = 3;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(8, 8, 8, 8);
		getContentPane().add(rolagemListaEscolhaPi, gbc);

		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.insets = new Insets(50, 8, 8, 8);
		getContentPane().add(botaoAdiciona, gbc);

		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.insets = new Insets(8, 8, 50, 8);
		getContentPane().add(botaoRemove, gbc);

		gbc.weightx = 50;
		gbc.weighty = 3;
		gbc.gridwidth = 2;
		gbc.gridheight = 3;
		gbc.gridx = 4;
		gbc.gridy = 1;
		gbc.insets = new Insets(8, 8, 8, 8);
		getContentPane().add(rolagemListaSelecaoPi, gbc);

	}

	private void adicionaComandos() {
		botaoAdiciona.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				adicionaMetodo();
			}
		});
		botaoRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				removeMetodo();
			}
		});
		componenteListaEscolhaPi.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2)
					adicionaMetodo();
			}
		});
		componenteListaSelecaoPi.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2)
					removeMetodo();
			}
		});
	}

	private void adicionaMetodo() {
		int indice = componenteListaEscolhaPi.getSelectedIndex();
		if (indice == -1) {
			JOptionPane.showMessageDialog(null, "Selecione algum item..");
			return;
		}
		listaSelecaoPi.addElement(listaEscolhaPi.elementAt(indice));
		listaEscolhaPi.remove(indice);
		componenteListaEscolhaPi.setSelectedIndex(indice);
	}

	private void removeMetodo() {
		int indice = componenteListaSelecaoPi.getSelectedIndex();
		if (indice == -1) {
			JOptionPane.showMessageDialog(null, "Selecione algum item..");
			return;
		}
		listaEscolhaPi.addElement(listaSelecaoPi.elementAt(indice));
		listaSelecaoPi.remove(indice);
		componenteListaSelecaoPi.setSelectedIndex(indice);
	}

	public String getMetodoEscolhaPi(int indice) {
		return listaEscolhaPi.getElementAt(indice);
	}

	public void adicionaMetodoEscolhaPi(String metodo) {
		listaEscolhaPi.addElement(metodo);
	}

	public String getMetodoSelecaoPi(int indice) {
		return listaSelecaoPi.getElementAt(indice);
	}

	public void adicionaMetodoSelecaoPi(String metodo) {
		listaSelecaoPi.addElement(metodo);
	}

	public Enumeration<String> getListaMetodosEscolhaPi() {
		return listaEscolhaPi.elements();
	}

	public Enumeration<String> getListaMetodosSelecaoPi() {
		return listaSelecaoPi.elements();
	}

	public boolean isListaEscolhaPiEmpty() {
		return listaEscolhaPi.isEmpty();
	}

}
