import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ClsArvore
{
	private ArrayList<ClsNiveis> listaDeNiveis;
	private int contadorDeNiveis;
	private int contadorDeNos;
	private ClsProblema estadoInicial;
	private ClsProblema estadoFinal;
	private ClsProblema estadoCorrente;
	private JLabel lblQtdNos;
	private JLabel lblQtdNiveis;
	private int delay;
	private ClsBuscaEmLargura objBuscaEmLargura;
	private JFrame tela;
	private ArrayList<ArrayList<ClsProblema>> listaDeSolucoes;
	private JList lstSolucoes;
	private DefaultListModel listModel;
	private ClsExecutarSolucao solucao;
	private JLabel lblTempo;
	/*-------------------------------------------------------------------------------------------------*/
	public ClsArvore(JLabel pLblQtdNiveis, JLabel pLblQtdNos, JFrame pTela, JList pLstSolucoes, JLabel pLblTempo)
	{
		tela = pTela;
		lblQtdNiveis = pLblQtdNiveis;
		lblQtdNos = pLblQtdNos;
		listaDeNiveis = new ArrayList<ClsNiveis>();
		contadorDeNiveis = 0;
		listaDeSolucoes = new ArrayList<ArrayList<ClsProblema>>();
		lstSolucoes = pLstSolucoes;
		listModel = new DefaultListModel();
		lstSolucoes.setModel(listModel);
		lblTempo = pLblTempo;
	}
	/*-------------------------------------------------------------------------------------------------*/
	public void executarBuscaEmLargura(ClsProblema pEstadoInicial, ClsProblema pEstadoFinal, ClsProblema pEstadoCorrente, int pDelay)
	{
		delay = pDelay;
		estadoInicial = pEstadoInicial;
		estadoFinal = pEstadoFinal;
		estadoCorrente = pEstadoCorrente;
		objBuscaEmLargura = new ClsBuscaEmLargura();
		listModel = new DefaultListModel();
		objBuscaEmLargura.start();
	}
	/*-------------------------------------------------------------------------------------------------*/
	public void pararBuscaEmLargura()
	{
		objBuscaEmLargura.stop();
	}
	/*-------------------------------------------------------------------------------------------------*/
	public void executarSolucao(int pCodigoDaSolucao)
	{
		solucao = new ClsExecutarSolucao();
		solucao.setCodigoSolucao(pCodigoDaSolucao);
		solucao.start();
	}
	/*-------------------------------------------------------------------------------------------------*/
	public void setEstadoCorrente(ClsProblema pEstado)
	{
		estadoCorrente = pEstado;
	}
	/*-------------------------------------------------------------------------------------------------*/
	/*----------------------------------------------N�VEIS---------------------------------------------*/
	/*-------------------------------------------------------------------------------------------------*/
	public class ClsNiveis
	{
		private ArrayList<ClsProblema> nivel;
		private int qtdNos;

		public ClsNiveis()
		{
			nivel = new ArrayList<ClsProblema>();
			qtdNos = 0;
		}
		public void adicionarNo(ClsProblema no)
		{
			nivel.add(no);
			qtdNos++;
		}
		public ArrayList<ClsProblema> getNos()
		{
			return nivel;
		}
		public int getQtdNos()
		{
			return qtdNos;
		}
	}
	/*-------------------------------------------------------------------------------------------------*/
	/*-----------------------------------------BUSCA EM LARGURA----------------------------------------*/
	/*-------------------------------------------------------------------------------------------------*/
	public class ClsBuscaEmLargura extends Thread
	{
		boolean terminou = false;
		public void run()
		{
			ClsTempo cronometro = new ClsTempo();
			cronometro.iniciarCronometro();
			tela.setTitle("Procurando solu��o...");
			ClsNiveis nivelAuxiliar;
			while(!terminou)
			{
				contadorDeNiveis++;
				lblQtdNiveis.setText(Long.toString(contadorDeNiveis));
				if (contadorDeNiveis == 1)
				{
					nivelAuxiliar = new ClsNiveis();
					nivelAuxiliar.adicionarNo(estadoInicial.getCopia());
					listaDeNiveis.add(nivelAuxiliar);
				}
				else
				{

					nivelAuxiliar = new ClsNiveis();
					ArrayList<ClsProblema> auxiliarEstados;
					for(int x = 0; x < listaDeNiveis.get(contadorDeNiveis - 2).getQtdNos(); x++)
					{
						auxiliarEstados = new ArrayList<ClsProblema>();
						auxiliarEstados = listaDeNiveis.get(contadorDeNiveis - 2).getNos().get(x).obterProximosEstadosPossiveis();
						for(int y = 0; y < auxiliarEstados.size(); y++)
						{
							nivelAuxiliar.adicionarNo(auxiliarEstados.get(y).getCopia());
						}
					}
					listaDeNiveis.add(nivelAuxiliar);
				}

				for(int x = 0; x < listaDeNiveis.get(contadorDeNiveis - 1).getQtdNos(); x++)
				{
					contadorDeNos++;
					lblQtdNos.setText(Integer.toString(contadorDeNos));
					estadoCorrente.gravarCopiaSemVisual(listaDeNiveis.get(contadorDeNiveis - 1).getNos().get(x));
					if(estadoCorrente.comparar(estadoFinal.getQuebraCabeca()))
					{
						terminou = true;
						listaDeSolucoes.add(estadoCorrente.obterSolucao());
						listModel.add(listaDeSolucoes.size() - 1, "Solu��o " + listaDeSolucoes.size());
						lstSolucoes.setModel(listModel);
					}
					try
					{
						sleep(delay);
						cronometro.pararCronometro();
						lblTempo.setText(cronometro.obterDuracaoFormatada());
					}
					catch(Exception e)
					{

					}
				}
			}
			tela.setTitle("Software Para Simula��o de Busca em Largura Para o Problema do Quebra-Cabe�a");
			JOptionPane.showMessageDialog(null, "Terminou", "Terminou", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	/*-------------------------------------------------------------------------------------------------*/
	/*----------------------------------------------SOLU��O--------------------------------------------*/
	/*-------------------------------------------------------------------------------------------------*/
	public class ClsExecutarSolucao extends Thread
	{
		private int codigoSolucao;
		public void setCodigoSolucao(int pCodigo)
		{
			codigoSolucao = pCodigo;
		}
		public void run()
		{
			tela.setTitle("Exibindo solu��o...");
			for(int x = (listaDeSolucoes.get(codigoSolucao).size() - 1); x >= 0; x--)
			{
				estadoCorrente.gravarCopiaSemVisual(listaDeSolucoes.get(codigoSolucao).get(x));
				try
				{
					sleep(1000);
				}
				catch(Exception e)
				{

				}
			}
			tela.setTitle("Software Para Simula��o de Busca em Largura Para o Problema do Quebra-Cabe�a");
			JOptionPane.showMessageDialog(null, "Terminou", "Terminou", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
