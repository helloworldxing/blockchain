package com.blockchain;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main( String[] args ) {

        Scanner sc = new Scanner(System.in);

        Blockchain blockchain = new Blockchain();

        //创建交易
        while(true){
            System.out.println("请输入from,to,amount:");
            String from1 = sc.next();
            String to1 = sc.next();
            Integer amount1 = sc.nextInt();

            Transaction transaction = new Transaction(from1,to1,amount1);

            Block block1 = new Block(blockchain.getLastBlock().getHash());
            block1.addTransaction(transaction);

            blockchain.addBlock(block1);
            System.out.println("是否继续输入：0为退出，1为继续");
            Integer n = sc.nextInt();
            if (n==0)break;
        }

        System.out.println("查找特定交易-------------------------------");
        System.out.println("请输入from和to:");
        List<Transaction> transactions = blockchain.findTransaction(sc.next(), sc.next());
        for(Transaction transaction:transactions){
            System.out.println("from:"+transaction.getFrom()+" to:"+transaction.getTo()+" amount:"+transaction.getAmount());
        }

        //打印区块链
        System.out.println("是否打印区块链：0为否，1为是");
        Integer m = sc.nextInt();
        if(m==1){
            showBlockchain();
        }

        //检查区块链有效性
        System.out.println("该区块链是否有效:"+blockchain.isChainValid());

    }

    public static void showBlockchain(){
        Blockchain blockchain = new Blockchain();

        System.out.println("打印区块链-------------------------------");
        for(Block block:blockchain.getBlocks()){
            System.out.println("上一个区块的哈希值:"+block.getPreviousHash());
            //注意一下，为什么会那么长,hash错误，未调用
            System.out.println("当前区块的哈希值:"+block.getHash());
            System.out.println("交易信息:");
            for(Transaction transaction1:block.getTransactions()){
                System.out.println("from:"+transaction1.getFrom()+" to:"+transaction1.getTo()+" amount:"+transaction1.getAmount());
            }
        }
    }
}
