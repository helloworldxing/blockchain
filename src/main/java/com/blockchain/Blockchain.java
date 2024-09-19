package com.blockchain;

import java.util.ArrayList;
import java.util.List;


public class Blockchain
{
    private List<Block> blocks;
    public Blockchain(){
        this.blocks = new ArrayList<>();
        this.initBlock();
    }

    private void initBlock() {
        blocks.add(new Block("创世区块","0"));
    }

    public void addBlock(Block block){
        block.setPreviousHash(this.getLastBlock().getHash());
        block.setHash(block.calculateHash());
        blocks.add(block);
    }

   public boolean isChainValid(){
       for (int i = 1; i < blocks.size(); i++) {
           Block currentBlock = blocks.get(i);
           Block previousBlock = blocks.get(i-1);
           if(!currentBlock.getHash().equals(currentBlock.calculateHash())){
               System.out.println("当前区块的哈希值不正确");
               return false;
           }
           if(!previousBlock.getHash().equals(currentBlock.getPreviousHash())){
               System.out.println("前一个区块的哈希值不正确");
               return false;
           }
       }
       return true;
   }

   public List<Transaction> findTransaction(String from,String to){
        List<Transaction> transactions = new ArrayList<>();
        for(Block block:blocks){
            for(Transaction transaction:block.getTransactions()){
                if(transaction.getFrom().equals(from) && transaction.getTo().equals(to)){
                    transactions.add(transaction);
                }
            }
        }
        return transactions;
   }

    public List<Block> getBlocks(){
        return blocks;
    }

    public Block getLastBlock() {
        return blocks.get(blocks.size()-1);
    }

}
