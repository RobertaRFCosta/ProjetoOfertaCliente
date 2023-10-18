package main;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import arvores.ABBCliente;
import banco.Cliente;

public class DivulgaOfertas {

	/*
	 * Anna Cristina RM 94795 Arthur Mesquita RM 93186 Felipe Palmeira RM 92835
	 * Roberta Costa RM 93803 Rodrigo Ronchi RM 93262
	 */

	public static void main(String[] args) {
		Scanner le = new Scanner(System.in);

		ABBCliente abbF = new ABBCliente();
		ABBCliente abbJ = new ABBCliente();

		int opcao, op, numeroConta;
		String nome, cpfCnpj;
		String tipoConta = null;
		double saldo = 0;

		do {
			System.out.println("\n------- BEM VINDO AO MENU DE OPÇÕES -------");
			System.out.println(" 0 - Encerrar o programa");
			System.out.println(" 1 - Inscrição cliente");
			System.out.println(" 2 - Oferta de novo serviço e/ou aplicação");
			System.out.println(" 3 – Entrar no Sub-Menu ");
			System.out.println("-------------------------------------------");
			opcao = le.nextInt();
			switch (opcao) {
			case 1:
				System.out.print("Digite nome: ");
				nome = le.next();
				System.out.print("Digite cpf: ");
				cpfCnpj = le.next();
				System.out.print("Digite número da conta: ");
				numeroConta = le.nextInt();
				do {
					System.out.print("Digite 1- Pessoa Física 2- Pessoa Jurídica: ");
					op = le.nextInt();
					switch (op) {
					case 1:
						tipoConta = "Física";
						break;
					case 2:
						tipoConta = "Jurídica";
						break;
					default:
						System.out.println("Opção inválida ");
						op = -1;
					}
				} while (op == -1);

				System.out.print("Informe saldo em aplicações R$: ");
				saldo = le.nextDouble();
				Cliente cliente = new Cliente(nome, cpfCnpj, numeroConta, tipoConta, saldo);

				if (op == 1) {
					abbF.root = abbF.inserir(abbF.root, cliente);
				} else if (op == 2) {
					abbJ.root = abbJ.inserir(abbJ.root, cliente);
				}

				break;
			case 2:
				System.out.print("Qual tipo de conta a oferta se destina? ");
				do {
					System.out.print("Digite 1- Pessoa Física 2- Pessoa Jurídica: ");
					op = le.nextInt();
					switch (op) {
					case 1:
						tipoConta = "Física";
						break;
					case 2:
						tipoConta = "Jurídica";
						break;
					default:
						System.out.println("Opção inválida ");
						op = -1;
					}
				} while (op == -1);

				System.out.print("Qual o valor de saldo mínimo exigido: R$ ");
				saldo = le.nextDouble();

				List<Cliente> listaPF = new LinkedList<>();
				List<Cliente> listaPJ = new LinkedList<>();

				if (op == 1) {
					abbF.clienteOferta(abbF.root, saldo, listaPF);
					while (!listaPF.isEmpty()) {
						System.out.println("Cliente "+ listaPF.get(0).getNome());
						System.out.print("Deseja aceitar a oferta? - 1. Sim  2. Não: ");
						int resp = le.nextInt();

						if (resp == 1) {
							System.out.println("Você aceitou a oferta! Agradeçemos a preferência.");
							abbF.removeValor(abbF.root, listaPF.get(0));
							listaPF.remove(0);
						} else if (resp == 2) {
							System.out.println("Você não aceitou a oferta!");
							listaPF.remove(0);
						} else {
							System.out.println("Resposta inválida, digite novamente!");
						}
					}

				} else if (op == 2) {
					abbJ.clienteOferta(abbJ.root, saldo, listaPJ);
					while (!listaPJ.isEmpty()) {
						System.out.println("Cliente "+ listaPJ.get(0).getNome());
						System.out.print("Deseja aceitar a oferta? - 1. Sim 2. Não: ");
						int resp = le.nextInt();

						if (resp == 1) {
							System.out.println("Você aceitou a oferta! Agradeçemos a preferência.");
							abbF.removeValor(abbF.root, listaPJ.get(0));
							listaPJ.remove(0);
						} else if (resp == 2) {
							System.out.println("Você não aceitou a oferta!");
							listaPJ.remove(0);
						} else {
							System.out.println("Resposta inválida, digite novamente!");
						}
					}
				}

				break;
			case 3:
				int opcaoSubMenu = -1;
				do {
					System.out.println("----------- SUBMENU DE PESQUISA -----------");
					System.out.println(" 1 - Consultar cliente");
					System.out.println(" 2 - Atualizar saldo");
					System.out.println(" 3 - Quantidade de clientes cadastrados");
					System.out.println(" 4 – Quantidade de clientes com para a oferta ");
					System.out.println(" 5 – Voltar para o Menu Principal ");
					System.out.println("-------------------------------------------");
					opcaoSubMenu = le.nextInt();

					switch (opcaoSubMenu) {

					case 1:
						System.out.print("Digite 1. PF ou 2. PJ: ");
						int tipoPessoa = le.nextInt();
						if (tipoPessoa == 1) {
							System.out.print("Digite o seu CPF: ");
							String nDocumento = le.next();
							Cliente cF = abbF.consulta(abbF.root, nDocumento);
							System.out.println(cF);

						} else if (tipoPessoa == 2) {
							System.out.print("Digite o seu CNPJ: ");
							String nDocumento = le.next();
							Cliente cJ = abbJ.consulta(abbJ.root, nDocumento);
							System.out.println(cJ);
						} else {
							System.out.println("Resposta inválida, digite novamente!");
						}
						break;

					case 2:
						System.out.print("Digite 1. PF ou 2. PJ: ");
						tipoPessoa = le.nextInt();
						if (tipoPessoa == 1) {
							System.out.print("Digite o número da conta: ");
							numeroConta = le.nextInt();

							Cliente cF = abbF.consultaConta(abbF.root, numeroConta);
							System.out.print("Digite o novo saldo: R$ ");
							saldo = le.nextDouble();
							abbF.atualizaSaldo(abbF.root, numeroConta, saldo);
							System.out.println("Saldo atualizado com sucesso!");
							System.out.println(cF);

						} else if (tipoPessoa == 2) {
							System.out.print("Digite o número da conta: ");
							numeroConta = le.nextInt();
							Cliente cJ = abbJ.consultaConta(abbJ.root, numeroConta);
							System.out.print("Digite o novo saldo: R$ ");
							saldo = le.nextDouble();
							abbJ.atualizaSaldo(abbJ.root, numeroConta, saldo);
							System.out.println("Saldo atualizado com sucesso!");
							System.out.println(cJ);

						} else {
							System.out.println("Resposta inválida, digite novamente!");
						}

						break;

					case 3:
						System.out.print("Digite 1. PF ou 2. PJ: ");
						tipoPessoa = le.nextInt();
						if (tipoPessoa == 1) {
							System.out.println(
									"O total de Clientes cadastrados é de: " + abbF.contaClientes(abbF.root, 0));
						} else if (tipoPessoa == 2) {
							System.out.println(
									"O total de Clientes cadastrados é de: " + abbJ.contaClientes(abbJ.root, 0));
						} else {
							System.out.println("Resposta inválida, digite novamente!");
						}
						break;

					case 4:
						System.out.print("Digite 1. PF ou 2. PJ: ");
						tipoPessoa = le.nextInt();
						System.out.print("Digite o valor da oferta: R$");
						double valorOferta = le.nextDouble();
						if (tipoPessoa == 1) {
							System.out.println("O total de Clientes com saldo positivo para a oferta é de: "
									+ abbF.contaSaldoOferta(abbF.root, 0, valorOferta));
						} else if (tipoPessoa == 2) {
							System.out.println("O total de Clientes com saldo positivo para a oferta é de: "
									+ abbJ.contaSaldoOferta(abbJ.root, 0, valorOferta));
						} else {
							System.out.println("Resposta inválida, digite novamente!");
						}
						break;

					case 5:
						break;

					default:
						System.out.println("Opção digitada Inválida!");
						opcaoSubMenu = -1;
					}

				} while (opcaoSubMenu == -1);
				break;
			}

		} while (opcao != 0);
		System.out.println("Clientes que não aceitaram ou não estavam adequados para a oferta");

		abbF.removeTodosCliente(abbF.root);
		abbJ.removeTodosCliente(abbJ.root);

		le.close();

	}
}
