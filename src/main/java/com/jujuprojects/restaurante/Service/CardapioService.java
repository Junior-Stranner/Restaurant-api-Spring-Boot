package com.jujuprojects.restaurante.Service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.jujuprojects.restaurante.Model.Cardapio;
import com.jujuprojects.restaurante.Model.Pedido;
import com.jujuprojects.restaurante.Repository.CardapioReposiyory;
import com.jujuprojects.restaurante.Repository.PedidoRepository;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CardapioService {

    private CardapioReposiyory cardapioReposiyory;

    public CardapioService(PedidoRepository pedidoRepository, CardapioReposiyory cardapioReposiyory) {
        this.cardapioReposiyory = cardapioReposiyory;
    }

    public void menu(){
        Scanner in = new Scanner(System.in);
        boolean isTrue = true;
        int op = 0;

        do{
            System.out.println("\n MENU " +
                    "\n 0 - Voltar "+
                    "\n 1 - Cadastrar Cardápios " +
                    "\n 2 - vizualizar Cardápios cadastrados" +
                    "\n 3 - Atualizar Cardapio " +
                    "\n 4 - Excluir o Cardaápio ");
             op = Integer.parseInt(in.nextLine());

             switch (op){

                 case 1: cadastroCardapio(in);break;

                 case 2: visualizaCardapio(in);break;
                 
                 case 3: atualizaCardapio(in);break;

                 case 4: excluirCardapio(in); break;

                 default : isTrue = false;
             }

        }while(isTrue);
    }

    private void atualizaCardapio(Scanner in) {
        double desconto = 0;

        System.out.println("Digite o id do Cardapio ");
        long idCardapio = Long.parseLong(in.nextLine());

        Optional<Cardapio> cardapioOptional = this.cardapioReposiyory.findById(idCardapio);
        if(cardapioOptional.isPresent()){
          Cardapio atualizaCardaoio = cardapioOptional.get();

            System.out.println("Digite a comida alterado ");
            String comida = in.nextLine();

            System.out.println("Digite a bebida");
            String bebida = in.nextLine();

            System.out.println("Digite o seu combo alterado ");
            String combo = in.nextLine();

            if(atualizaCardaoio.getPreco() > 50){
                desconto = 0.90;
            }else {
                desconto = 0;
            }
            double  totalPreco = atualizaCardaoio.getPreco() * desconto;
            atualizaCardaoio.setPercentualDesconto((atualizaCardaoio.getPreco()  - totalPreco) / atualizaCardaoio.getPreco()  * 100) ;

            System.out.println("\n Digite o preço com o decsonto :"+totalPreco);

            atualizaCardaoio.setComida(comida);
            atualizaCardaoio.setBebida(bebida);
            atualizaCardaoio.setCombo(combo);
            atualizaCardaoio.setPreco(atualizaCardaoio.getPreco());
            atualizaCardaoio.setPercentualDesconto(atualizaCardaoio.getPercentualDesconto());
            atualizaCardaoio.setTotalPreco(totalPreco);

            this.cardapioReposiyory.save(atualizaCardaoio);
            System.out.println("\n **************************************\n Cardapio  Atualizado com sucesso! \n**************************************");


        }
    }

    private void excluirCardapio(Scanner in) {

        System.out.println("Digite o id para exclusão !");
        long id = Long.parseLong(in.nextLine());

        cardapioReposiyory.deleteById(id);
        System.out.println("\n id : "+id+" encontrado !!");
    }


    private void visualizaCardapio(Scanner in) {

        System.out.println("\n ==============================" +
                              "\n Cardápios" +
                           "\n ==============================");
            Iterable<Cardapio> cardapios = cardapioReposiyory.findAll();
            for (Cardapio cardapio : cardapios) {
           System.out.println(cardapio);
        }
    }

    private void cadastroCardapio(Scanner in) {
        double  desconto = 0;
        double percentualDesconto = 0;

        System.out.println("\n Digite o nome da comida");
        String comida = in.nextLine();
        System.out.println("\n Digite o nome da Bebida");
        String bebida = in.nextLine();
        System.out.println("\n Digite o nome do Combo");
        String combo = in.nextLine();
        System.out.println("\n Digite o Preço");
        double preco = Double.parseDouble(in.nextLine());
    /*    System.out.println("\n Desconto  :");
        double percentualDesconto = Double.parseDouble(in.nextLine());*/

       if(preco > 50){
            desconto = 0.90;
        }else {
            desconto = 0;
        }
       double  totalPreco = preco * desconto;
        percentualDesconto = (preco - totalPreco) / preco * 100;

        System.out.println("\n Digite o preço com o desconto :"+totalPreco);

        Cardapio cardapio = new Cardapio();
        cardapio.setComida(comida);
        cardapio.setBebida(bebida);
        cardapio.setCombo(combo);
        cardapio.setPreco(preco);
        cardapio.setPercentualDesconto(percentualDesconto);
        cardapio.setTotalPreco(totalPreco);

       this.cardapioReposiyory.save(cardapio);
        System.out.println("\n *********************** \n Cardapio  Salvo com sucesso! \n ***********************");
    }
}
