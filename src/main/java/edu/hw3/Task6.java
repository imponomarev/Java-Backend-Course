package edu.hw3;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Task6 {

    interface StockMarket {

        void add(Stock stock);

        void remove(Stock stock);

        Stock mostValuableStock();
    }

    public class Stock implements Comparable<Stock> {
        private String name;

        private int cost;

        public Stock(String name, int cost) {
            this.name = name;
            this.cost = cost;
        }

        @Override
        public int compareTo(Stock stock) {
            return this.cost - stock.cost;
        }

        @Override
        public String toString() {
            return name + " " + cost;
        }

        public int getCost() {
            return cost;
        }
    }

    class StockExchange implements StockMarket {

        List<Stock> stocks;

        public StockExchange(List<Stock> stocks) {
            this.stocks = stocks;
        }

        @Override
        public void add(Stock stock) {
            stocks.add(stock);
        }

        @Override
        public void remove(Stock stock) {
            stocks.remove(stock);
        }

        @Override
        public Stock mostValuableStock() {

            Queue<Stock> queue = new PriorityQueue<>(Comparator.comparingInt(Stock::getCost).reversed());

            queue.addAll(stocks);

            return queue.peek();

        }
    }
}
