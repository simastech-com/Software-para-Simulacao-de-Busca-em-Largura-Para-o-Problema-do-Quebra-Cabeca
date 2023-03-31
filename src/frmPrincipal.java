import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Rectangle;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;

public class frmPrincipal extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel pnlOpcoes = null;
	private JLabel lblQtdLinhas = null;
	private JTextField tfdQtdLinhas = null;
	private JLabel lblQtdColunas = null;
	private JTextField tfdQtdColunas = null;
	private JPanel pnlEstadoInicial = null;
	private JPanel pnlPuzzleInicial = null;
	private JLabel lblTituloInicial = null;
	private JPanel pnlEstadoFinal = null;
	private JPanel pnlEstadoCorrente = null;
	private JLabel lblEstadoFinal = null;
	private JPanel pnlPuzzleFinal = null;
	private JPanel pnlPuzzleCorrente = null;
	private JLabel lblEstadoCorrente = null;
	private JPanel pnlStatus = null;
	private JLabel lblStatus = null;
	private JPanel plnEstatisticas = null;
	private JPanel plnSolucoes = null;
	private JLabel lblSolucoes = null;
	private JLabel lblEstatisticas = null;
	private GridLayout layoutInicial = null;
	private GridLayout layoutFinal = null;
	private GridLayout layoutCorrente = null;
	private byte qtdLinhas;
	private byte qtdColunas;
	private JTextField[][] camposIniciais;
	private JTextField[][] camposFinais;
	private JTextField[][] camposCorrentes;
	private JLabel lblDelay = null;
	private JTextField tfdDelay = null;
	private JButton btnSolucionar = null;
	private JPanel pnlDadosEstatisticas = null;
	private JLabel lblTituloTempoDecorrido = null;
	private JLabel lblQtdTempoDecorrido = null;
	private JLabel lblTituloQtdNos = null;
	private JLabel lblQtdNos = null;
	private JLabel lblTituloQtdNiveis = null;
	private JLabel lblQuantidadeDeNiveis = null;
	private JPanel pnlTempo = null;
	private JPanel pnlQtdNos = null;
	private JPanel pnlNiveis = null;
	private JButton btnExecutarSolucao = null;
	private JScrollPane scrSolucoes = null;
	private JList lstSolucoes = null;
	private int delay;
	private JButton btnPararSolucao = null;
	private ClsArvore objArvore;
	private JButton btnLimpar = null;
	private int vazioNoEstadoInicial;
	private int vazioNoEstadoFinal;
	private JFrame tela;
	private JLabel lblCreditos = null;
	/*-------------------------------------------------------------------------------------------------------*/
	public frmPrincipal()
	{
		super();
		layoutInicial = new GridLayout();
		layoutFinal = new GridLayout();
		layoutCorrente = new GridLayout();
		initialize();
		qtdLinhas = 3;
		qtdColunas = 3;
		definirLayoutDoEstadoInicial();
		definirLayoutDoEstadoFinal();
		definirLayoutDoEstadoCorrente();
		delay = 0;
		tela = this;
		objArvore = new ClsArvore(lblQuantidadeDeNiveis, lblQtdNos, tela, lstSolucoes, lblQtdTempoDecorrido);
		vazioNoEstadoInicial = 0;
		vazioNoEstadoFinal = 0;

	}
	/*-------------------------------------------------------------------------------------------------------*/
	private void initialize()
	{
		this.setContentPane(getJContentPane());
		this.setTitle("Software Para Simulação de Busca em Largura Para o Problema do Quebra-Cabeça");
		this.setBounds(new Rectangle(0, 0, 800, 600));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	/*-------------------------------------------------------------------------------------------------------*/
	private JPanel getJContentPane()
	{
		if (jContentPane == null)
		{
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPnlOpcoes(), BorderLayout.NORTH);
			jContentPane.add(getPnlEstadoInicial(), BorderLayout.WEST);
			jContentPane.add(getPnlEstadoFinal(), BorderLayout.CENTER);
			jContentPane.add(getPnlEstadoCorrente(), BorderLayout.EAST);
			jContentPane.add(getPnlStatus(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}
	/*-------------------------------------------------------------------------------------------------------*/
	private JPanel getPnlOpcoes()
	{
		if (pnlOpcoes == null)
		{
			lblDelay = new JLabel();
			lblDelay.setText("Delay(ms)");
			lblQtdColunas = new JLabel();
			lblQtdColunas.setText("Quantidade de colunas");
			lblQtdLinhas = new JLabel();
			lblQtdLinhas.setText("Quantidade de linhas");
			pnlOpcoes = new JPanel();
			pnlOpcoes.setLayout(new FlowLayout());
			pnlOpcoes.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			pnlOpcoes.add(lblQtdLinhas, null);
			pnlOpcoes.add(getTfdQtdLinhas(), null);
			pnlOpcoes.add(lblQtdColunas, null);
			pnlOpcoes.add(getTfdQtdColunas(), null);
			pnlOpcoes.add(lblDelay, null);
			pnlOpcoes.add(getTfdDelay(), null);
			pnlOpcoes.add(getBtnSolucionar(), null);
			pnlOpcoes.add(getBtnPararSolucao(), null);
			pnlOpcoes.add(getBtnLimpar(), null);
		}
		return pnlOpcoes;
	}
	/*-------------------------------------------------------------------------------------------------------*/
	private JTextField getTfdQtdLinhas()
	{
		if (tfdQtdLinhas == null)
		{
			tfdQtdLinhas = new JTextField();
			tfdQtdLinhas.setPreferredSize(new Dimension(40, 20));
			tfdQtdLinhas.addFocusListener(new java.awt.event.FocusAdapter()
			{
				public void focusLost(java.awt.event.FocusEvent e)
				{
					definirLayout();
				}
			});
		}
		return tfdQtdLinhas;
	}
	/*-------------------------------------------------------------------------------------------------------*/
	private JTextField getTfdQtdColunas()
	{
		if (tfdQtdColunas == null)
		{
			tfdQtdColunas = new JTextField();
			tfdQtdColunas.setPreferredSize(new Dimension(40, 20));
			tfdQtdColunas.addFocusListener(new java.awt.event.FocusAdapter()
			{
				public void focusLost(java.awt.event.FocusEvent e)
				{
					definirLayout();
				}
			});
		}
		return tfdQtdColunas;
	}
	/*-------------------------------------------------------------------------------------------------------*/
	private JPanel getPnlEstadoInicial()
	{
		if (pnlEstadoInicial == null)
		{
			lblTituloInicial = new JLabel();
			lblTituloInicial.setText("Estado inicial");
			lblTituloInicial.setHorizontalAlignment(SwingConstants.CENTER);
			pnlEstadoInicial = new JPanel();
			pnlEstadoInicial.setLayout(new BorderLayout());
			pnlEstadoInicial.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			pnlEstadoInicial.setPreferredSize(new Dimension(266, 266));
			pnlEstadoInicial.add(lblTituloInicial, BorderLayout.NORTH);
			pnlEstadoInicial.add(getPnlPuzzleInicial(), BorderLayout.CENTER);
		}
		return pnlEstadoInicial;
	}
	/*-------------------------------------------------------------------------------------------------------*/
	private JPanel getPnlPuzzleInicial()
	{
		if (pnlPuzzleInicial == null)
		{
			pnlPuzzleInicial = new JPanel();
			pnlPuzzleInicial.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			pnlPuzzleInicial.setPreferredSize(new Dimension(266, 266));
			pnlPuzzleInicial.setLayout(layoutInicial);
		}
		return pnlPuzzleInicial;
	}
	/*-------------------------------------------------------------------------------------------------------*/
	private JPanel getPnlEstadoFinal()
	{
		if (pnlEstadoFinal == null)
		{
			lblEstadoFinal = new JLabel();
			lblEstadoFinal.setText("Estado final");
			lblEstadoFinal.setHorizontalAlignment(SwingConstants.CENTER);
			pnlEstadoFinal = new JPanel();
			pnlEstadoFinal.setLayout(new BorderLayout());
			pnlEstadoFinal.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			pnlEstadoFinal.setPreferredSize(new Dimension(266, 266));
			pnlEstadoFinal.add(lblEstadoFinal, BorderLayout.NORTH);
			pnlEstadoFinal.add(getPnlPuzzleFinal(), BorderLayout.CENTER);
		}
		return pnlEstadoFinal;
	}
	/*-------------------------------------------------------------------------------------------------------*/
	private JPanel getPnlEstadoCorrente()
	{
		if (pnlEstadoCorrente == null)
		{
			lblEstadoCorrente = new JLabel();
			lblEstadoCorrente.setText("Estado corrente");
			lblEstadoCorrente.setHorizontalAlignment(SwingConstants.CENTER);
			pnlEstadoCorrente = new JPanel();
			pnlEstadoCorrente.setLayout(new BorderLayout());
			pnlEstadoCorrente.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			pnlEstadoCorrente.setPreferredSize(new Dimension(266, 266));
			pnlEstadoCorrente.add(lblEstadoCorrente, BorderLayout.NORTH);
			pnlEstadoCorrente.add(getPnlPuzzleCorrente(), BorderLayout.CENTER);
		}
		return pnlEstadoCorrente;
	}
	/*-------------------------------------------------------------------------------------------------------*/
	private JPanel getPnlPuzzleFinal()
	{
		if (pnlPuzzleFinal == null)
		{
			pnlPuzzleFinal = new JPanel();
			pnlPuzzleFinal.setLayout(layoutFinal);
			pnlPuzzleFinal.setPreferredSize(new Dimension(266, 266));
			pnlPuzzleFinal.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		}
		return pnlPuzzleFinal;
	}
	/*-------------------------------------------------------------------------------------------------------*/
	private JPanel getPnlPuzzleCorrente()
	{
		if (pnlPuzzleCorrente == null)
		{
			pnlPuzzleCorrente = new JPanel();
			pnlPuzzleCorrente.setLayout(layoutCorrente);
			pnlPuzzleCorrente.setPreferredSize(new Dimension(266, 266));
			pnlPuzzleCorrente.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		}
		return pnlPuzzleCorrente;
	}
	/*-------------------------------------------------------------------------------------------------------*/
	private JPanel getPnlStatus()
	{
		if (pnlStatus == null)
		{
			lblCreditos = new JLabel();
			lblCreditos.setText("William Cardoso Simas - RA 200452 - Engenharia da Computação - 9º termo - Inteligência Artificial - Orientador: James Clauton da Silva");
			lblCreditos.setHorizontalAlignment(SwingConstants.CENTER);
			lblStatus = new JLabel();
			lblStatus.setText("Status");
			lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
			pnlStatus = new JPanel();
			pnlStatus.setLayout(new BorderLayout());
			pnlStatus.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			pnlStatus.setPreferredSize(new Dimension(41, 250));
			pnlStatus.add(lblStatus, BorderLayout.NORTH);
			pnlStatus.add(getPlnEstatisticas(), BorderLayout.WEST);
			pnlStatus.add(getPlnSolucoes(), BorderLayout.CENTER);
			pnlStatus.add(lblCreditos, BorderLayout.SOUTH);
		}
		return pnlStatus;
	}
	/*-------------------------------------------------------------------------------------------------------*/
	private JPanel getPlnEstatisticas()
	{
		if (plnEstatisticas == null)
		{
			lblEstatisticas = new JLabel();
			lblEstatisticas.setText("Estatísticas");
			lblEstatisticas.setHorizontalAlignment(SwingConstants.CENTER);
			plnEstatisticas = new JPanel();
			plnEstatisticas.setLayout(new BorderLayout());
			plnEstatisticas.setPreferredSize(new Dimension(250, 0));
			plnEstatisticas.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			plnEstatisticas.add(lblEstatisticas, BorderLayout.NORTH);
			plnEstatisticas.add(getPnlDadosEstatisticas(), BorderLayout.CENTER);
		}
		return plnEstatisticas;
	}
	/*-------------------------------------------------------------------------------------------------------*/
	private JPanel getPlnSolucoes()
	{
		if (plnSolucoes == null)
		{
			lblSolucoes = new JLabel();
			lblSolucoes.setText("Soluções");
			lblSolucoes.setHorizontalAlignment(SwingConstants.CENTER);
			plnSolucoes = new JPanel();
			plnSolucoes.setLayout(new BorderLayout());
			plnSolucoes.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			plnSolucoes.add(lblSolucoes, BorderLayout.NORTH);
			plnSolucoes.add(getBtnExecutarSolucao(), BorderLayout.SOUTH);
			plnSolucoes.add(getScrSolucoes(), BorderLayout.CENTER);
		}
		return plnSolucoes;
	}
	/**
	 * This method initializes tfdDelay
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTfdDelay() {
		if (tfdDelay == null) {
			tfdDelay = new JTextField();
			tfdDelay.setPreferredSize(new Dimension(60, 20));
		}
		return tfdDelay;
	}
	/**
	 * This method initializes btnSolucionar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnSolucionar() {
		if (btnSolucionar == null) {
			btnSolucionar = new JButton();
			btnSolucionar.setText("Solucionar");
			btnSolucionar.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					vazioNoEstadoInicial = 0;
					vazioNoEstadoFinal = 0;
					ClsProblema estadoInicial = new ClsProblema(qtdLinhas, qtdColunas);
					ClsProblema estadoCorrente = new ClsProblema(qtdLinhas, qtdColunas);
					byte[][] auxiliar = new byte[qtdLinhas][qtdColunas];
					for(byte y = 0; y < qtdLinhas; y++)
					{
						for(byte x = 0; x < qtdColunas; x++)
						{
							try
							{
								auxiliar[y][x] = Byte.parseByte(camposIniciais[y][x].getText());
							}
							catch(Exception erro)
							{
								auxiliar[y][x] = 0;
								estadoInicial.setColunaZero(x);
								estadoInicial.setLinhaZero(y);
								estadoCorrente.setLinhaZero(y);
								estadoCorrente.setColunaZero(x);
								vazioNoEstadoInicial++;
							}
						}
					}
					estadoInicial.setQuebraCabeca(auxiliar);
					estadoCorrente.setVisual(camposCorrentes);
					estadoCorrente.setQuebraCabeca(auxiliar);

					ClsProblema estadoFinal = new ClsProblema(qtdLinhas, qtdColunas);
					auxiliar = new byte[qtdLinhas][qtdColunas];
					for(byte y = 0; y < qtdLinhas; y++)
					{
						for(byte x = 0; x < qtdColunas; x++)
						{
							try
							{
								auxiliar[y][x] = Byte.parseByte(camposFinais[y][x].getText());
							}
							catch(Exception erro)
							{
								auxiliar[y][x] = 0;
								estadoFinal.setColunaZero(x);
								estadoFinal.setLinhaZero(y);
								vazioNoEstadoFinal++;
							}
						}
					}
					estadoFinal.setQuebraCabeca(auxiliar);
					estadoFinal.setVisual(camposCorrentes);

					try
					{
						delay = Integer.parseInt(tfdDelay.getText());
					}
					catch(Exception erro)
					{
						delay = 0;
					}

					objArvore = new ClsArvore(lblQuantidadeDeNiveis, lblQtdNos, tela, lstSolucoes, lblQtdTempoDecorrido);
					if((vazioNoEstadoInicial == 1) && (vazioNoEstadoFinal == 1))
					{
						objArvore.executarBuscaEmLargura(estadoInicial, estadoFinal, estadoCorrente, delay);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Deve haver um e somente um campo vazio nos estados inicial e final", "Falha", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return btnSolucionar;
	}
	/**
	 * This method initializes pnlDadosEstatisticas
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlDadosEstatisticas() {
		if (pnlDadosEstatisticas == null) {
			lblQuantidadeDeNiveis = new JLabel();
			lblQuantidadeDeNiveis.setText("0");
			lblTituloQtdNiveis = new JLabel();
			lblTituloQtdNiveis.setText("Níveis percorridos: ");
			lblQtdNos = new JLabel();
			lblQtdNos.setText("0");
			lblTituloQtdNos = new JLabel();
			lblTituloQtdNos.setText("Nós percorridos:");
			lblQtdTempoDecorrido = new JLabel();
			lblQtdTempoDecorrido.setText("0");
			lblTituloTempoDecorrido = new JLabel();
			lblTituloTempoDecorrido.setText("Tempo decorrido:");
			pnlDadosEstatisticas = new JPanel();
			pnlDadosEstatisticas.setLayout(new GridLayout(3,1));
			pnlDadosEstatisticas.add(getPnlTempo(), null);
			pnlDadosEstatisticas.add(getPnlQtdNos(), null);
			pnlDadosEstatisticas.add(getPnlNiveis(), null);
		}
		return pnlDadosEstatisticas;
	}
	/**
	 * This method initializes pnlTempo
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlTempo() {
		if (pnlTempo == null) {
			pnlTempo = new JPanel();
			pnlTempo.setLayout(new FlowLayout(FlowLayout.LEFT));
			pnlTempo.add(lblTituloTempoDecorrido, null);
			pnlTempo.add(lblQtdTempoDecorrido, null);
		}
		return pnlTempo;
	}
	/**
	 * This method initializes pnlQtdNos
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlQtdNos() {
		if (pnlQtdNos == null) {
			pnlQtdNos = new JPanel();
			pnlQtdNos.setLayout(new FlowLayout(FlowLayout.LEFT));
			pnlQtdNos.add(lblTituloQtdNos, null);
			pnlQtdNos.add(lblQtdNos, null);
		}
		return pnlQtdNos;
	}
	/**
	 * This method initializes pnlNiveis
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlNiveis() {
		if (pnlNiveis == null) {
			pnlNiveis = new JPanel();
			pnlNiveis.setLayout(new FlowLayout(FlowLayout.LEFT));
			pnlNiveis.add(lblTituloQtdNiveis, null);
			pnlNiveis.add(lblQuantidadeDeNiveis, null);
		}
		return pnlNiveis;
	}
	/**
	 * This method initializes btnExecutarSolucao
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnExecutarSolucao()
	{
		if (btnExecutarSolucao == null)
		{
			btnExecutarSolucao = new JButton();
			btnExecutarSolucao.setText("Executar solução");
			btnExecutarSolucao.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					int selecao;
					try
					{
						selecao = lstSolucoes.getSelectedIndex();
						if(selecao >= 0)
						{
							ClsProblema estadoCorrente = new ClsProblema(qtdLinhas, qtdColunas);
							estadoCorrente.setVisual(camposCorrentes);
							objArvore.setEstadoCorrente(estadoCorrente);
							objArvore.executarSolucao(selecao);
						}
					}
					catch(Exception erro)
					{

					}
				}
			});
		}
		return btnExecutarSolucao;
	}
	/**
	 * This method initializes scrSolucoes
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getScrSolucoes() {
		if (scrSolucoes == null) {
			scrSolucoes = new JScrollPane();
			scrSolucoes.setViewportView(getLstSolucoes());
		}
		return scrSolucoes;
	}
	/**
	 * This method initializes lstSolucoes
	 *
	 * @return javax.swing.JList
	 */
	private JList getLstSolucoes()
	{
		if (lstSolucoes == null)
		{
			lstSolucoes = new JList();
			lstSolucoes.setModel(new DefaultListModel());
		}
		return lstSolucoes;
	}
	/**
	 * This method initializes btnPararSolucao
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnPararSolucao() {
		if (btnPararSolucao == null) {
			btnPararSolucao = new JButton();
			btnPararSolucao.setText("Parar");
			btnPararSolucao.setEnabled(true);
			btnPararSolucao.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					objArvore.pararBuscaEmLargura();
				}
			});
		}
		return btnPararSolucao;
	}
	/**
	 * This method initializes btnLimpar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnLimpar() {
		if (btnLimpar == null) {
			btnLimpar = new JButton();
			btnLimpar.setText("Limpar");
			btnLimpar.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					definirLayout();
				}
			});
		}
		return btnLimpar;
	}
	/*-------------------------------------------------------------------------------------------------------*/
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				frmPrincipal thisClass = new frmPrincipal();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}
	/*-------------------------------------------------------------------------------------------------------*/
	private void definirLayoutDoEstadoInicial()
	{
		ClsPuzzleInicial objPuzzleInicial = new ClsPuzzleInicial();
		objPuzzleInicial.start();
	}
	/*-------------------------------------------------------------------------------------------------------*/
	private void definirLayoutDoEstadoFinal()
	{
		ClsPuzzleFinal objPuzzleFinal = new ClsPuzzleFinal();
		objPuzzleFinal.start();
	}
	/*-------------------------------------------------------------------------------------------------------*/
	private void definirLayoutDoEstadoCorrente()
	{
		ClsPuzzleCorrente objPuzzleCorrente = new ClsPuzzleCorrente();
		objPuzzleCorrente.start();
	}
	/*-------------------------------------------------------------------------------------------------------*/
	private void definirQtdLinhasEColunas()
	{
		if (tfdQtdColunas.getText().length() == 0)
		{
			qtdColunas = 3;
		}
		else
		{
			try
			{
				qtdColunas = Byte.parseByte(tfdQtdColunas.getText());
				if (qtdColunas < 3)
				{
					qtdColunas = 3;
					tfdQtdColunas.setText(Integer.toString(qtdColunas));
				}
				if (qtdColunas > 8)
				{
					qtdColunas = 8;
					tfdQtdColunas.setText(Integer.toString(qtdColunas));
				}
			}
			catch(Exception e)
			{
				qtdColunas = 3;
			}
		}

		if (tfdQtdLinhas.getText().length() == 0)
		{
			qtdLinhas = 3;
		}
		else
		{
			try
			{
				qtdLinhas = Byte.parseByte(tfdQtdLinhas.getText());
				if (qtdLinhas < 3)
				{
					qtdLinhas = 3;
					tfdQtdLinhas.setText(Integer.toString(qtdLinhas));
				}
				if (qtdLinhas > 8)
				{
					qtdLinhas = 8;
					tfdQtdLinhas.setText(Integer.toString(qtdLinhas));
				}
			}
			catch(Exception e)
			{
				qtdLinhas = 3;
			}
		}
	}
	/*-------------------------------------------------------------------------------------------------------*/
	public class ClsPuzzleInicial extends Thread
	{
		public void run()
		{
			pnlPuzzleInicial.removeAll();
			layoutInicial.setRows(qtdLinhas);
			layoutInicial.setColumns(qtdColunas);
			camposIniciais = new JTextField[qtdLinhas][qtdColunas];
			for(int y = 0; y < qtdLinhas; y++)
			{
				for(int x = 0; x < qtdColunas; x++)
				{
					camposIniciais[y][x] = new JTextField();
					camposIniciais[y][x].setHorizontalAlignment(SwingConstants.CENTER);
					pnlPuzzleInicial.add(camposIniciais[y][x]);
				}
			}
			pnlPuzzleInicial.updateUI();
		}
	}
	/*-------------------------------------------------------------------------------------------------------*/
	public class ClsPuzzleFinal extends Thread
	{
		public void run()
		{
			pnlPuzzleFinal.removeAll();
			layoutFinal.setRows(qtdLinhas);
			layoutFinal.setColumns(qtdColunas);
			camposFinais = new JTextField[qtdLinhas][qtdColunas];
			for(int y = 0; y < qtdLinhas; y++)
			{
				for(int x = 0; x < qtdColunas; x++)
				{
					camposFinais[y][x] = new JTextField();
					camposFinais[y][x].setHorizontalAlignment(SwingConstants.CENTER);
					pnlPuzzleFinal.add(camposFinais[y][x]);
				}
			}
			pnlPuzzleFinal.updateUI();
		}
	}
	/*-------------------------------------------------------------------------------------------------------*/
	public class ClsPuzzleCorrente extends Thread
	{
		public void run()
		{
			pnlPuzzleCorrente.removeAll();
			layoutCorrente.setRows(qtdLinhas);
			layoutCorrente.setColumns(qtdColunas);
			camposCorrentes = new JTextField[qtdLinhas][qtdColunas];
			for(int y = 0; y < qtdLinhas; y++)
			{
				for(int x = 0; x < qtdColunas; x++)
				{
					camposCorrentes[y][x] = new JTextField();
					camposCorrentes[y][x].setHorizontalAlignment(SwingConstants.CENTER);
					camposCorrentes[y][x].setEnabled(false);
					pnlPuzzleCorrente.add(camposCorrentes[y][x]);
				}
			}
			pnlPuzzleCorrente.updateUI();
		}
	}
	/*-------------------------------------------------------------------------------------------------------*/
	public void definirLayout()
	{
		definirQtdLinhasEColunas();
		definirLayoutDoEstadoInicial();
		definirLayoutDoEstadoFinal();
		definirLayoutDoEstadoCorrente();
		lstSolucoes.setModel(new DefaultListModel());
		lblQtdNos.setText(Integer.toString(0));
		lblQtdTempoDecorrido.setText(Integer.toString(0));
		lblQuantidadeDeNiveis.setText(Integer.toString(0));
	}
}
