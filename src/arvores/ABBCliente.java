package arvores;

import java.util.List;

import banco.Cliente;

public class ABBCliente {
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

	public int contaClientes(ARVORE p, int cont) {
		if (p != null) {
			cont++;
			cont = contaClientes(p.esq, cont);
			cont = contaClientes(p.dir, cont);
		}
		return cont;
	}
	
	public int contaSaldoOferta(ARVORE p, int cont, double saldo) {
		if (p != null) {
			if(p.cliente.getSaldoAplicacao() >= saldo) {
				cont++;
				
			}
			cont = contaClientes(p.esq, cont);
			cont = contaClientes(p.dir, cont);
		}
		return cont;
	}

	public Cliente consulta(ARVORE p, String nDocumento) {
		if (p != null) {
			if (nDocumento.equalsIgnoreCase(p.cliente.getnDocumento())) {
				return p.cliente;
			} else {
				Cliente clienteEsq = consulta(p.esq, nDocumento);
				if (clienteEsq == null) {
					return consulta(p.dir, nDocumento);
				}
				return clienteEsq;
			}
		}
		return null;

	}

	public Cliente consultaConta(ARVORE p, int nConta) {
		if (p != null) {
			if (nConta == p.cliente.getnConta()) {
				return p.cliente;
			} else {
				Cliente clienteEsq = consultaConta(p.esq, nConta);
				if (clienteEsq == null) {
					return consultaConta(p.dir, nConta);
				}
				return clienteEsq;
			}
		}
		return null;

	}

	public ARVORE removeValor(ARVORE p, Cliente cliente) {
		if (p != null) {
			if (cliente.getSaldoAplicacao() == p.cliente.getSaldoAplicacao()) {
				if (p.esq == null && p.dir == null)
					return null;
				if (p.esq == null) {
					return p.dir;
				} else {
					if (p.dir == null) {
						return p.esq;
					} else {
						ARVORE aux, ref;
						ref = p.dir;
						aux = p.dir;
						while (aux.esq != null)
							aux = aux.esq;
						aux.esq = p.esq;
						return ref;
					}
				}
			} else {
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

	public void clienteOferta(ARVORE p, double oferta, List<Cliente> lista) {

		if (p != null) {
			if (p.dir != null) {
				clienteOferta(p.dir, oferta, lista);
			}
			if (p.cliente.getSaldoAplicacao() >= oferta) {
				lista.add(p.cliente);
			} else {
				return;
			}

			if (p.esq != null) {
				clienteOferta(p.esq, oferta, lista);
			}

		}
	}

	public Cliente atualizaSaldo(ARVORE p, int nConta, double saldo) {
		Cliente cliente = null;
		if (p != null) {
			cliente = consultaConta(p, nConta);
			cliente.setSaldoAplicacao(saldo);
		}
		return cliente;

	}
}
