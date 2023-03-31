
public class ClsTempo
{
	private long inicio;
	private long fim;

	public ClsTempo()
	{
		inicio = 0;
		fim = 0;
	}

	public void iniciarCronometro()
	{
		inicio = System.currentTimeMillis();
	}

	public void pararCronometro()
	{
		fim = System.currentTimeMillis();
	}

	public long obterDuracao()
	{
		return (fim - inicio);
	}

	public String obterDuracaoFormatada()
	{
		long duracao = obterDuracao();
		String milissegundos = String.format("%03d",(long)(duracao%1000)) + "ms";
		String segundos = String.format("%02d", (long)((duracao/1000)%60)) + "s";
		String minutos = String.format("%02d", (long)((duracao/60000)%60)) + "min";
		String horas = String.format("%02d", (long)(duracao/3600000)) + "h";
		String duracaoFormatada = horas + minutos + segundos + milissegundos;
		return duracaoFormatada;
	}
}
