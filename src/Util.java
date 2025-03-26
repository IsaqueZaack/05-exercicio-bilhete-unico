import java.text.DecimalFormat;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Double.parseDouble;

public class Util {
    private Bilhete[] bilhete = new Bilhete[2];
    private int index = 0;

    public void menuPrincipal() {
        int opcao;
        String menu = "1. Administrador\n2. Usuário\n3. Finalizar\n";

        do {
            opcao = parseInt(showInputDialog(menu));
            if (opcao < 1 || opcao > 3) {
                // null -- alinhamento de uma janela com relção a outra
                showMessageDialog(null, "Opção inválida");
            }
            else {
                switch (opcao) {
                    case 1:
                        menuAdministrador();
                        break;
                    case 2:
                        menuUsuario();
                        break; // no último case o break é opcional;
            }
            }

        } while(opcao != 3);

    }

    // menu com as funcionalidades do admin
    private void menuAdministrador() {
        int opcao;
        String menu = "1. Emitir bilhete\n2. Listar bilhetes" +
                "\n3. Remover\n4. Sair";
        do {
            opcao = parseInt(showInputDialog(menu));
            if (opcao < 1 || opcao > 4) {
                showMessageDialog(null, "Opção inválida");
            }
            else {
                switch (opcao) {
                    case 1:
                        emitirBilhete();
                        break;
                    case 2:
                        listarBilhetes();
                        break;
                    case 3:
                        remover();
                }

            }
        } while(opcao != 4);
    }

    // método com as funcionalidades do úsuario do bilhete
    private void menuUsuario() {
        int opcao;
        String menu = "1. Consultar saldo\n2. Carregar bilhete\n" +
                "3. Passar na catraca\n4. Sair";
        do {
            opcao = parseInt(showInputDialog(menu));
            if (opcao < 1 || opcao > 4) {
                showMessageDialog(null, "Opção inválida");
            }
            else {

                switch (opcao) {
                    case 1:
                        consultarSaldo();
                        break;
                    case 2:
                        carregarBilhete();
                        break;
                    case 3:
                        passarNaCatraca();
                }
            }
        } while(opcao != 4);

    }

    // método para emitir um bilhete --> gerar um objeto e armazenar no vetor
    private void emitirBilhete() {
        String nome, perfil;
        long cpf;

        if (index < bilhete.length) {
            nome = showInputDialog("Nome");
            cpf = parseLong(showInputDialog("CPF"));
            perfil = showInputDialog("Tipo de tarifa (perfil) --> Professor ou Estudante ou Comum");
            bilhete[index] = new Bilhete(new Usuario(cpf, nome, perfil));
            index++;
        }
        else {
            showMessageDialog(null, "Procure a administração");
        }
    }

    // método para listar os dados do bilhete --> nome, cpf, saldo e perfil
    private void listarBilhetes() {
        DecimalFormat df = new DecimalFormat("0.00");
        String aux = "";
        for (int i = 0; i < index; i++) {
            aux += "Nome do usuário: " + bilhete[i].usuario.nome + "\n";
            aux += "CPF: " + bilhete[i].usuario.cpf + "\n";
            aux += "Número do bilhete: " + bilhete[i].numero + "\n";
            aux += "Saldo: R$" + df.format(bilhete[i].saldo) + "\n";
            aux += "Perfil (tipo de tarifa): " + bilhete[i].usuario.perfil + "\n";
            aux += "------------------------------------------------\n";
        }
        showMessageDialog(null, aux);


    }

    // método para carregar o bilhete --> com o valor informado pelo o usuário
    private void carregarBilhete() {
        double valor;
        int posicao = pesquisar();
        if (posicao != -1) {
            valor = parseDouble(showInputDialog("Valor para carregar o bilhete"));
            bilhete[posicao].carregar(valor);
        }
    }

    // método para cunsultar o saldo
    private void consultarSaldo() {
        int posicao = pesquisar();
        if (posicao != -1) {
            showMessageDialog(null, "Saldo = R$ " + bilhete[posicao].consultarSaldo());
        }
        }

    // método para passar na catraca --> simulação
    private void passarNaCatraca() {
        DecimalFormat df = new DecimalFormat("0.00");
        int posicao = pesquisar();
        if (posicao != -1) {
            showMessageDialog(null, bilhete[posicao].passarNaCatraca());
        }
    }

    private void remover() {
        int posicao = pesquisar();
        for (int i = 0; i < index; i++) {
                bilhete[posicao] = bilhete[bilhete.length];
        }
        // algum erro de lógica
    }

    // método auxiliar para os outros métodos da aplicação
    private int pesquisar() {
        long cpf = parseLong(showInputDialog("CPF: "));

        for (int i = 0; i < index; i++) {
            if (bilhete[i].usuario.cpf == cpf) {
                return i;
            }
        }
        showMessageDialog(null, cpf + " não encontrado");
        return -1;
    }
}


