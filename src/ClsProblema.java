import java.util.ArrayList;

import javax.swing.JTextField;


public class ClsProblema
{
	private byte[][] quebraCabeca;
	private ClsProblema estadoPai;
	private byte linhaZero;
	private byte colunaZero;
	private byte maiorColuna;
	private byte maiorLinha;
	private JTextField[][] visual;
	/*-------------------------------------------------------------------------------------------------*/
	public ClsProblema(byte qtdLinhas, byte qtdColunas)
	{
		quebraCabeca = new byte[qtdLinhas][qtdColunas];
		estadoPai = null;
		maiorColuna = (byte)(qtdColunas - 1);
		maiorLinha = (byte)(qtdLinhas - 1);
		visual = null;
	}
	/*-------------------------------------------------------------------------------------------------*/
	public void setEstadoPai(ClsProblema pEstadoPai)
	{
		estadoPai = pEstadoPai;
	}
	/*-------------------------------------------------------------------------------------------------*/
	public ClsProblema getEstadoPai()
	{
		return estadoPai;
	}
	/*-------------------------------------------------------------------------------------------------*/
	public void setQuebraCabeca(byte[][] pQuebraCabeca)
	{
		quebraCabeca = pQuebraCabeca;
		atualizarVisual();
	}
	/*-------------------------------------------------------------------------------------------------*/
	public byte[][] getQuebraCabeca()
	{
		return quebraCabeca;
	}
	/*-------------------------------------------------------------------------------------------------*/
	public void setLinhaZero(byte pLinha)
	{
		linhaZero = pLinha;
	}
	/*-------------------------------------------------------------------------------------------------*/
	public byte getLinhaZero()
	{
		return linhaZero;
	}
	/*-------------------------------------------------------------------------------------------------*/
	public void setColunaZero(byte pColuna)
	{
		colunaZero = pColuna;
	}
	/*-------------------------------------------------------------------------------------------------*/
	public byte getColunaZero()
	{
		return colunaZero;
	}
	/*-------------------------------------------------------------------------------------------------*/
	public void setMaiorLinha(byte pLinha)
	{
		maiorLinha = pLinha;
	}
	/*-------------------------------------------------------------------------------------------------*/
	public byte getMaiorLinha()
	{
		return maiorLinha;
	}
	/*-------------------------------------------------------------------------------------------------*/
	public void setMaiorColuna(byte pColuna)
	{
		maiorColuna = pColuna;
	}
	/*-------------------------------------------------------------------------------------------------*/
	public byte getMaiorColuna()
	{
		return maiorColuna;
	}
	/*-------------------------------------------------------------------------------------------------*/
	public void setVisual(JTextField[][] pVisual)
	{
		visual = pVisual;
	}
	/*-------------------------------------------------------------------------------------------------*/
	public JTextField[][] getVisual()
	{
		return visual;
	}
	/*-------------------------------------------------------------------------------------------------*/
	private void resetarPossibilidade(byte[][] pPossibilidade)
	{
		for (int y = 0; y < maiorLinha + 1; y++)
		{
			for(int x = 0; x < maiorColuna + 1; x++)
			{
				pPossibilidade[y][x] = quebraCabeca[y][x];
			}
		}
	}
	/*-------------------------------------------------------------------------------------------------*/
	public ArrayList<ClsProblema> obterProximosEstadosPossiveis()
	{
		ArrayList<ClsProblema> retorno = new ArrayList<ClsProblema>();
		ClsProblema auxiliar;
		byte[][] possibilidade;
		if (linhaZero == 0)
		{
			if (colunaZero == 0)
			{
				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero + 1][colunaZero];
				possibilidade[linhaZero + 1][colunaZero] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero(colunaZero);
				auxiliar.setLinhaZero((byte)(linhaZero + 1));
				retorno.add(auxiliar.getCopia());

				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero][colunaZero + 1];
				possibilidade[linhaZero][colunaZero + 1] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero((byte)(colunaZero + 1));
				auxiliar.setLinhaZero(linhaZero);
				retorno.add(auxiliar.getCopia());
			}
			else if (colunaZero == maiorColuna)
			{
				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero + 1][colunaZero];
				possibilidade[linhaZero + 1][colunaZero] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero(colunaZero);
				auxiliar.setLinhaZero((byte)(linhaZero + 1));
				retorno.add(auxiliar.getCopia());

				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero][colunaZero - 1];
				possibilidade[linhaZero][colunaZero - 1] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero((byte)(colunaZero - 1));
				auxiliar.setLinhaZero(linhaZero);
				retorno.add(auxiliar.getCopia());
			}
			else
			{
				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero + 1][colunaZero];
				possibilidade[linhaZero + 1][colunaZero] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero(colunaZero);
				auxiliar.setLinhaZero((byte)(linhaZero + 1));
				retorno.add(auxiliar.getCopia());

				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero][colunaZero - 1];
				possibilidade[linhaZero][colunaZero - 1] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero((byte)(colunaZero - 1));
				auxiliar.setLinhaZero(linhaZero);
				retorno.add(auxiliar.getCopia());

				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero][colunaZero + 1];
				possibilidade[linhaZero][colunaZero + 1] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero((byte)(colunaZero + 1));
				auxiliar.setLinhaZero(linhaZero);
				retorno.add(auxiliar.getCopia());
			}
		}

		else if (linhaZero == maiorLinha)
		{
			if (colunaZero == 0)
			{
				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero - 1][colunaZero];
				possibilidade[linhaZero - 1][colunaZero] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero(colunaZero);
				auxiliar.setLinhaZero((byte)(linhaZero - 1));
				retorno.add(auxiliar.getCopia());

				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero][colunaZero + 1];
				possibilidade[linhaZero][colunaZero + 1] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero((byte)(colunaZero + 1));
				auxiliar.setLinhaZero(linhaZero);
				retorno.add(auxiliar.getCopia());
			}
			else if (colunaZero == maiorColuna)
			{
				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero - 1][colunaZero];
				possibilidade[linhaZero - 1][colunaZero] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero(colunaZero);
				auxiliar.setLinhaZero((byte)(linhaZero - 1));
				retorno.add(auxiliar.getCopia());

				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero][colunaZero - 1];
				possibilidade[linhaZero][colunaZero - 1] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1),(byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero((byte)(colunaZero - 1));
				auxiliar.setLinhaZero(linhaZero);
				retorno.add(auxiliar.getCopia());
			}
			else
			{
				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero - 1][colunaZero];
				possibilidade[linhaZero - 1][colunaZero] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero(colunaZero);
				auxiliar.setLinhaZero((byte)(linhaZero - 1));
				retorno.add(auxiliar.getCopia());

				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero][colunaZero - 1];
				possibilidade[linhaZero][colunaZero - 1] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero((byte)(colunaZero - 1));
				auxiliar.setLinhaZero(linhaZero);
				retorno.add(auxiliar.getCopia());

				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero][colunaZero + 1];
				possibilidade[linhaZero][colunaZero + 1] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero((byte)(colunaZero + 1));
				auxiliar.setLinhaZero(linhaZero);
				retorno.add(auxiliar.getCopia());
			}
		}
		else
		{
			if (colunaZero == 0)
			{
				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero - 1][colunaZero];
				possibilidade[linhaZero - 1][colunaZero] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero(colunaZero);
				auxiliar.setLinhaZero((byte)(linhaZero - 1));
				retorno.add(auxiliar.getCopia());

				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero][colunaZero + 1];
				possibilidade[linhaZero][colunaZero + 1] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero((byte)(colunaZero + 1));
				auxiliar.setLinhaZero(linhaZero);
				retorno.add(auxiliar.getCopia());

				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero + 1][colunaZero];
				possibilidade[linhaZero + 1][colunaZero] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero(colunaZero);
				auxiliar.setLinhaZero((byte)(linhaZero + 1));
				retorno.add(auxiliar.getCopia());
			}
			else if (colunaZero == maiorColuna)
			{
				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero - 1][colunaZero];
				possibilidade[linhaZero - 1][colunaZero] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero(colunaZero);
				auxiliar.setLinhaZero((byte)(linhaZero - 1));
				retorno.add(auxiliar.getCopia());

				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero][colunaZero - 1];
				possibilidade[linhaZero][colunaZero - 1] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero((byte)(colunaZero - 1));
				auxiliar.setLinhaZero(linhaZero);
				retorno.add(auxiliar.getCopia());

				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero + 1][colunaZero];
				possibilidade[linhaZero + 1][colunaZero] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero(colunaZero);
				auxiliar.setLinhaZero((byte)(linhaZero + 1));
				retorno.add(auxiliar.getCopia());
			}
			else
			{
				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero - 1][colunaZero];
				possibilidade[linhaZero - 1][colunaZero] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero(colunaZero);
				auxiliar.setLinhaZero((byte)(linhaZero - 1));
				retorno.add(auxiliar.getCopia());

				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero][colunaZero - 1];
				possibilidade[linhaZero][colunaZero - 1] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero((byte)(colunaZero - 1));
				auxiliar.setLinhaZero(linhaZero);
				retorno.add(auxiliar.getCopia());

				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero][colunaZero + 1];
				possibilidade[linhaZero][colunaZero + 1] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero((byte)(colunaZero + 1));
				auxiliar.setLinhaZero(linhaZero);
				retorno.add(auxiliar.getCopia());

				possibilidade = new byte[maiorLinha + 1][maiorColuna + 1];
				resetarPossibilidade(possibilidade);
				possibilidade[linhaZero][colunaZero] = possibilidade[linhaZero + 1][colunaZero];
				possibilidade[linhaZero + 1][colunaZero] = 0;
				auxiliar = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
				auxiliar.setEstadoPai(this);
				auxiliar.setQuebraCabeca(possibilidade);
				auxiliar.setColunaZero(colunaZero);
				auxiliar.setLinhaZero((byte)(linhaZero + 1));
				retorno.add(auxiliar.getCopia());
			}
		}
		return retorno;
	}
	/*-------------------------------------------------------------------------------------------------*/
	public boolean comparar(byte[][] pQuebraCabeca)
	{
		boolean retorno = true;
		for(byte y = 0; y < (maiorLinha + 1); y++)
		{
			for(byte x = 0; x < (maiorColuna + 1); x++)
			{
				if(pQuebraCabeca[y][x] != quebraCabeca[y][x])
				{
					retorno = false;
					break;
				}
			}
			if(!retorno)
			{
				break;
			}
		}
		return retorno;
	}
	/*-------------------------------------------------------------------------------------------------*/
	public ClsProblema getCopia()
	{
		ClsProblema retorno = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
		retorno.setColunaZero(colunaZero);
		retorno.setEstadoPai(estadoPai);
		retorno.setLinhaZero(linhaZero);
		retorno.setMaiorColuna(maiorColuna);
		retorno.setMaiorLinha(maiorLinha);
		retorno.setQuebraCabeca(quebraCabeca.clone());
		retorno.setVisual(visual);
		return retorno;
	}
	/*-------------------------------------------------------------------------------------------------*/
	public ClsProblema getCopiaSemVisual()
	{
		ClsProblema retorno = new ClsProblema((byte)(maiorLinha + 1), (byte)(maiorColuna + 1));
		retorno.setColunaZero(colunaZero);
		retorno.setEstadoPai(estadoPai);
		retorno.setLinhaZero(linhaZero);
		retorno.setMaiorColuna(maiorColuna);
		retorno.setMaiorLinha(maiorLinha);
		retorno.setQuebraCabeca(quebraCabeca.clone());
		return retorno;
	}
	/*-------------------------------------------------------------------------------------------------*/
	public void gravarCopiaSemVisual(ClsProblema pProblema)
	{
		setColunaZero(pProblema.getColunaZero());
		setEstadoPai(pProblema.getEstadoPai());
		setLinhaZero(pProblema.getLinhaZero());
		setMaiorColuna(pProblema.getMaiorColuna());
		setMaiorLinha(pProblema.getMaiorLinha());
		setQuebraCabeca(pProblema.getQuebraCabeca());
	}
	/*-------------------------------------------------------------------------------------------------*/
	public void gravarCopia(ClsProblema pProblema)
	{
		setColunaZero(pProblema.getColunaZero());
		setEstadoPai(pProblema.getEstadoPai());
		setLinhaZero(pProblema.getLinhaZero());
		setMaiorColuna(pProblema.getMaiorColuna());
		setMaiorLinha(pProblema.getMaiorLinha());
		setVisual(pProblema.getVisual());
		setQuebraCabeca(pProblema.getQuebraCabeca());
	}
	/*-------------------------------------------------------------------------------------------------*/
	public void atualizarVisual()
	{
		if (visual != null)
		{
			for(int y = 0; y < maiorLinha + 1; y++)
			{
				for(int x = 0; x < maiorColuna + 1; x++)
				{
					if(quebraCabeca[y][x] != 0)
					{
						visual[y][x].setText(Byte.toString(quebraCabeca[y][x]));
					}
					else
					{
						visual[y][x].setText("");
					}
				}
			}
		}
	}
	/*-------------------------------------------------------------------------------------------------*/
	public ArrayList<ClsProblema> obterSolucao()
	{
		ArrayList<ClsProblema> retorno = new ArrayList<ClsProblema>();
		ClsProblema auxiliar = this;
		retorno.add(auxiliar.getCopia());
		while(auxiliar.estadoPai != null)
		{
			auxiliar.gravarCopia(auxiliar.getEstadoPai());
			retorno.add(auxiliar.getCopia());
		}
		return retorno;
	}
}
