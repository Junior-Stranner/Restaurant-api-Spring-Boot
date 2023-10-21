package com.jujuprojects.restaurante;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.jujuprojects.restaurante.Service.CardapioService;
import com.jujuprojects.restaurante.Service.ClienteService;
import com.jujuprojects.restaurante.Service.PedidoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RestauranteApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RestauranteApplication.class, args);
    }

    private CardapioService cardapioService;
    private PedidoService pedidoService;
    private ClienteService clienteService;

    public RestauranteApplication(CardapioService cardapioService, PedidoService pedidoService, ClienteService clienteService) {
        this.cardapioService = cardapioService;
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner in = new Scanner(System.in);
        boolean isTrue = true;
        int op = 0;

        do{
            System.out.println("\n MENU " +
                    "\n 0 - sair " +
                    "\n 1 - Cardapio" +
                    "\n 2 - Pedido" +
                    "\n 3 - Cliente ");
            op = Integer.parseInt(in.nextLine());

            switch (op){
                case 1:this.cardapioService.menu(); break;

                case 2:this.pedidoService.menu(); break;

                case 3: this.clienteService.menu();break;

                default: isTrue = false;
            }

        }while(isTrue);
    }
}
