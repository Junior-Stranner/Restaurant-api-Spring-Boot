package com.jujuprojects.restaurante.Service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.jujuprojects.restaurante.Model.Cliente;
import com.jujuprojects.restaurante.Model.Pedido;
import com.jujuprojects.restaurante.Repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class ClienteService {
    ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void menu(){
        Scanner in = new Scanner(System.in);
        boolean isTrue = true;
        int op = 0;

        do{
            System.out.println("\n MENU " +
                    "\n 0 - Voltar "+
                    "\n 1 - Cadastrar Cliente " +
                    "\n 2 - Atualizar Cliente" +
                    "\n 4 - Excluir o Cardaápio ");
            op = Integer.parseInt(in.nextLine());

            switch (op){

                case 1: cadastroCliente(in);break;

                case 2: visualizarClientes(in);break;

                case 3: atualizaCliente(in); break;

                default : isTrue = false;
            }

        }while(isTrue);
    }

    private void atualizaCliente(Scanner in) {

        System.out.println("Digite o id do cliente ");
        long idCliente = Long.parseLong(in.nextLine());

        Optional<Cliente> clienteOptional = this.clienteRepository.findById(idCliente);
        if(clienteOptional.isPresent()){
            Cliente atualizaCliente = clienteOptional.get();
        }
    }

    private void visualizarClientes(Scanner in) {
        try {
            System.out.println("\n ==============================" +
                    "\n Clientes" +
                    "\n ==============================");
            Iterable<Cliente> clientes = this.clienteRepository.findAll();

            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void cadastroCliente(Scanner in) {

        System.out.println("Digite o Nome ");
        String nome = in.nextLine();

        System.out.println("Digite o Endereço  ");
        String endereco = in.nextLine();

        System.out.println("Digite o bairro  ");
        String bairro = in.nextLine();

        System.out.println("Digite a Numero da Rua  ");
        String rua = in.nextLine();

        Cliente cliente = new Cliente(nome,endereco,bairro,rua);
        this.clienteRepository.save(cliente);
        System.out.println("Salvo com sucesso !");

    }
}
