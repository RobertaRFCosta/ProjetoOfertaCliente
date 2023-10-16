package arvores;

import banco.Cliente;

public class ABBPessoaJ {
	private class ARVORE {
		Cliente cliente;
		ARVORE esq, dir;
	}

	public ARVORE root = null;

	public ARVORE inserir(ARVORE p, Cliente novoCliente) {
		if (p == null) {
			p = new ARVORE();
			p.cliente = novoCliente;
			p.esq = null;
			p.dir = null;
		} else if (novoCliente.getSaldoAplicacao() < p.cliente.getSaldoAplicacao())
			p.esq = inserir(p.esq, novoCliente);
		else
			p.dir = inserir(p.dir, novoCliente);
		return p;
	}

	public int contaNos(ARVORE p, int cont) {
		if (p != null) {
			cont++;
			cont = contaNos(p.esq, cont);
			cont = contaNos(p.dir, cont);
		}
		return cont;
	}

	public Cliente consulta(ARVORE p, String nDocumento) {
		if (p != null) {
			if (nDocumento.equalsIgnoreCase(p.cliente.getnDocumento())) {
				return p.cliente;
			}else {
				Cliente clienteEsq = consulta(p.esq, nDocumento);
				if (clienteEsq == null) {
					return consulta(p.dir, nDocumento);
				}
				return clienteEsq;
			}
		}
		return null;
		
	}

	/*
	 * public int contaConsulta(ARVORE p, int info, int cont) { if (p != null) {
	 * cont++; if (info == p.cliente) return cont; else { if (info < p.cliente) cont
	 * = contaConsulta(p.esq, info, cont); else cont = contaConsulta(p.dir, info,
	 * cont); } } return cont; }
	 */

	public ARVORE removeValor(ARVORE p, Cliente cliente) {
		if (p != null) {
			if (cliente.getSaldoAplicacao() == p.cliente.getSaldoAplicacao()) {
				if (p.esq == null && p.dir == null) // nó a ser removido é nó folha
					return null;
				if (p.esq == null) { // se não há sub-árvore esquerda o ponteiro passa apontar para a sub-árvore
										// direita
					return p.dir;
				} else {
					if (p.dir == null) { // se não há sub-árvore direita o ponteiro passa apontar para a sub-árvore
											// esquerda
						return p.esq;
					} else { /*
								 * o nó a ser retirado possui sub-arvore esquerda e direita, então o nó que será
								 * retirado deve-se encontrar o menor valor na sub-árvore á direita
								 */
						ARVORE aux, ref;
						ref = p.dir;
						aux = p.dir;
						while (aux.esq != null)
							aux = aux.esq;
						aux.esq = p.esq;
						return ref;
					}
				}
			} else { // procura dado a ser removido na ABB
				if (cliente.getSaldoAplicacao() < p.cliente.getSaldoAplicacao())
					p.esq = removeValor(p.esq, cliente);
				else
					p.dir = removeValor(p.dir, cliente);
			}
		}
		return p;
	}

	public void listaEmOrdem(ARVORE p) {
		if (p != null) {
			listaEmOrdem(p.esq);
			System.out.print(" " + p.cliente);
			listaEmOrdem(p.dir);
		}
	}

}
