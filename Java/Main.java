package Java;

import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Toy toy1 = new Toy("1", "Конструктор", 20);
        Toy toy2 = new Toy("2", "Робот", 30);
        Toy toy3 = new Toy("3", "Кукла", 50);

        PriorityQueue<Toy> toyQueue = new PriorityQueue<>((t1, t2) -> t2.getProbability() - t1.getProbability());
        toyQueue.add(toy1);
        toyQueue.add(toy2);
        toyQueue.add(toy3);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            Toy randomToy = getRandomToy(toyQueue);
            result.append(randomToy.getId()).append(" ").append(randomToy.getName()).append("\n");
        }

        writeToFile("output.txt", result.toString());
    }

    private static Toy getRandomToy(PriorityQueue<Toy> toyQueue) {
        int randomNumber = (int) (Math.random() * 100);
        int cumulativeProbability = 0;

        for (Toy toy : toyQueue) {
            cumulativeProbability += toy.getProbability();
            if (randomNumber < cumulativeProbability) {
                return toy;
            }
        }

        return null;
    }

    private static void writeToFile(String filename, String content) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}