package banco;

public class Cliente {

	String nome;
	String nDocumento;
	int nConta;
	String tipoConta;
	double saldoAplicacao;
	
	public Cliente(String nome, String nDocumento, int nConta, String tipoConta, double saldoAplicacao) {
		super();
		this.nome = nome;
		this.nDocumento = nDocumento;
		this.nConta = nConta;
		this.tipoConta = tipoConta;
		this.saldoAplicacao = saldoAplicacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getnDocumento() {
		return nDocumento;
	}

	public void setnDocumento(String nDocumento) {
		this.nDocumento = nDocumento;
	}

	public int getnConta() {
		return nConta;
	}

	public void setnConta(int nConta) {
		this.nConta = nConta;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public double getSaldoAplicacao() {
		return saldoAplicacao;
	}

	public void setSaldoAplicacao(double saldoAplicacao) {
		this.saldoAplicacao = saldoAplicacao;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", nDocumento=" + nDocumento + ", nConta=" + nConta + ", tipoConta="
				+ tipoConta + ", saldoAplicacao=" + saldoAplicacao + "]";
	}
	
}
