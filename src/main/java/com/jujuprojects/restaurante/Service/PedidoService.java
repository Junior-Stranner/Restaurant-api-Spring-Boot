package com.jujuprojects.restaurante.Service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.jujuprojects.restaurante.Model.Cardapio;
import com.jujuprojects.restaurante.Model.Cliente;
import com.jujuprojects.restaurante.Model.Pedido;
import com.jujuprojects.restaurante.Repository.CardapioReposiyory;
import com.jujuprojects.restaurante.Repository.ClienteRepository;
import com.jujuprojects.restaurante.Repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class PedidoService {
    private PedidoRepository pedidoRepository;
    private CardapioReposiyory cardapioReposiyory;

    private ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository, CardapioReposiyory cardapioReposiyory, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.cardapioReposiyory = cardapioReposiyory;
        this.clienteRepository = clienteRepository;
    }
    public void menu() {
        Scanner in = new Scanner(System.in);
        boolean isTrue = true;
        int op = 0;

        do {
            System.out.println("\n MENU " +
                    "\n 0 - Voltar " +
                    "\n 1 - Realizar Pedido " +
                    "\n 2 - Atualizar Pedido" +
                    "\n 3 - Cancelar o Pedido " +
                    "\n 4 - vizualizar Cardápios/Pedidos feitos" +
                    "\n  ");
            op = Integer.parseInt(in.nextLine());

            switch (op) {


                case 1: realizarPedido(in);break;

                case 2: atualizarPedido(in);break;

                case 3:
                    cancelarPedido(in);
                    break;

                case 4:
                    visualizaPedidosFeitos(in);
                    break;

                default:
                    isTrue = false;
            }

        } while (isTrue);
    }



    private void visualizaPedidosFeitos(Scanner in) {
        System.out.println("\n ==============================" +
                "\n Pedidos" +
                "\n ==============================");
        Iterable<Pedido>pedidos = this.pedidoRepository.findAll();

        for (Pedido pedido : pedidos) {
            System.out.println(pedidos);
        }
    }
    private void cancelarPedido(Scanner in) {

        System.out.println("Digite o Id  do  Pedido");
        long idPedido = Long.parseLong(in.nextLine());

        Optional<Pedido> pedidoOptional = this.pedidoRepository.findById(idPedido);
        if(pedidoOptional.isPresent()){
            Pedido pedido = pedidoOptional.get();
             pedido.setId(idPedido);
             this.pedidoRepository.deleteById(idPedido);
             System.out.println("Pedido Cancelado com sucesso !");
        }else{
            System.out.println("Não foi Possível Cancelar o Pedido !");
        }
    }
    private void atualizarPedido(Scanner in) {

        boolean isTrue = true;

        System.out.println("Digite o id do Pedido ");
        long idPedido = Long.parseLong(in.nextLine());

        Optional<Pedido> pedidoOptional = this.pedidoRepository.findById(idPedido);
        if(pedidoOptional.isPresent()){
            Pedido atualizaPedido = pedidoOptional.get();

            while(isTrue){
                System.out.println("================================" +
                        "\n O que deseje alterar ?" +
                        "\n 0 - Voltar " +
                        "\n 1 - Cardapio " +
                        "\n 2 - informaçoes do cliente ");
                int op = Integer.parseInt(in.nextLine());

                switch(op){

                    case 1: System.out.println("Digite o id do cardapio");
                            long idCardapio = Long.parseLong(in.nextLine());

                            Optional<Cardapio> optionalCardapio =this.cardapioReposiyory.findById(idCardapio);
                        if (optionalCardapio.isPresent()) {
                            Cardapio cardapio = optionalCardapio.get();
                            Pedido pedido = new Pedido();
                            pedido.setCardapio(cardapio);
                            pedidoRepository.save(pedido);
                            System.out.println("Pedido salvo com sucesso!");
                        } else {
                            System.out.println("Cardápio com ID " + idCardapio + " inexistente");
                        }

                    case 2: System.out.println("Digite o id do cliente!");
                            long idCliente = Long.parseLong(in.nextLine());

                        Optional<Cliente> optionalCliente = this.clienteRepository.findById(idCliente);
                        if (optionalCliente.isPresent()) {
                            Cliente cliente = optionalCliente.get();
                            Pedido pedido = new Pedido();
                            pedido.setCliente(cliente);
                            pedidoRepository.save(pedido);
                            System.out.println("Pedido salvo com sucesso!");
                        } else {
                            System.out.println("Cliente com ID " + idCliente + " inexistente");
                        }
                }
            }
        }
    }

    private void realizarPedido(Scanner in) {
        try {
            System.out.println("Digite o número da mesa onde o pedido será feito:");
            int mesa = Integer.parseInt(in.nextLine());

            System.out.println("Digite o Id do cliente");
            long idCliente = Long.parseLong(in.nextLine());

            System.out.println("Digite o ID do cardápio:");
            long idCardapio = Long.parseLong(in.nextLine());


            Optional<Cliente> clienteOptional = this.clienteRepository.findById(idCliente);
            if (clienteOptional.isPresent()) {
                  Cliente cliente = clienteOptional.get();

                Optional<Cardapio> optionalCardapio = cardapioReposiyory.findById(idCardapio);
                if (optionalCardapio.isPresent()) {
                    Cardapio cardapio = optionalCardapio.get();
                    Pedido pedido = new Pedido(mesa, cardapio,cliente);
                    pedidoRepository.save(pedido);
                    System.out.println("Pedido salvo com sucesso!");
                } else {
                    System.out.println("Cliente com ID " + idCliente + " inexistente");
                    System.out.println("Cardápio com ID " + idCardapio + " inexistente");
                  }
                }
            } catch(NumberFormatException e){
                System.out.println("Erro: Entrada inválida. Certifique-se de que o número da mesa e o ID do cardápio sejam números válidos.");
            }
        }
    }
