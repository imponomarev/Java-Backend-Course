package edu.hw3;


import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class Task6Test {

    private Task6 task6;

    @BeforeEach
    public void setup() {
        task6 = new Task6();
    }

    @Test
    @DisplayName("Stock exchange test: mostValuableStock function test")
    void testStockExchange1(){

        //Given
        Task6.Stock stock1 = task6.new Stock("Stock1", 100);
        Task6.Stock stock2 = task6.new Stock("Stock2", 200);
        Task6.Stock stock3 = task6.new Stock("Stock3", 150);

        //When
        Task6.StockExchange stockExchange = task6.new StockExchange(new ArrayList<Task6.Stock>());
        stockExchange.add(stock1);
        stockExchange.add(stock2);
        stockExchange.add(stock3);

        Task6.Stock mostValuableStock = stockExchange.mostValuableStock();

        //Then
        assertEquals(stock2, mostValuableStock);
    }

    @Test
    @DisplayName("Stock exchange test: add function test")
    void testStockExchange2() {

        //Given
        Task6.Stock stock1 = task6.new Stock("Stock1", 100);
        Task6.Stock stock2 = task6.new Stock("Stock2", 200);

        //When
        Task6.StockExchange stockExchange = task6.new StockExchange(new ArrayList<Task6.Stock>());
        stockExchange.add(stock1);
        stockExchange.add(stock2);

        //Then
        assertEquals(2, stockExchange.stocks.size());
    }


    @Test
    @DisplayName("Stock exchange test: remove function test")
    void testStockExchange3() {

        //Given
        Task6.Stock stock1 = task6.new Stock("Stock1", 100);
        Task6.Stock stock2 = task6.new Stock("Stock2", 200);

        //When
        Task6.StockExchange stockExchange = task6.new StockExchange(new ArrayList<Task6.Stock>());
        stockExchange.add(stock1);
        stockExchange.add(stock2);

        stockExchange.remove(stock1);

        //Then
        assertEquals(1, stockExchange.stocks.size());
        assertFalse(stockExchange.stocks.contains(stock1));
    }
}
