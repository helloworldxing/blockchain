package com.blockchain;

import com.sun.javaws.jnl.LaunchDesc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Block {

    private String hash;
    private String previousHash;
    private List<Transaction> transactions = new ArrayList<>();//初始化transactions,避免空指针
    private String data;
    private long timeStamp;
    private int nonce;//随机数,用于调整哈希值以满足挖矿难度，一个只被使用一次的任意或非重复的随机数值
    private int difficulty = 5;//挖矿难度

    StringUtil stringUtil = new StringUtil();

    public Block(String data,String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public Block(String hash) {
    }

    public String calculateHash(){
        String calculatehash = previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data + transactions.toString();
        return stringUtil.applySha256(calculatehash);
    }

    public void mineBlock(int difficulty){
        String target = new String(new char[difficulty]).replace('\0','0');
        while(!hash.substring(difficulty).equals(target)){
            nonce++;
            hash = calculateHash();
        }
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
        this.hash = calculateHash();
    }

}
