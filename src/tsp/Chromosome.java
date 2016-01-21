package tsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author oguzcagiran
 */
public class Chromosome {

    private List<Integer> chromosomeListFormat = new ArrayList<>();
    private static Random randomNumberGenerator = new Random();

    public Chromosome(List<Integer> chromosomeList) {
        this.chromosomeListFormat = chromosomeList;
    }

    public Chromosome() {

        Set<Integer> chromosome = new LinkedHashSet<>();

        while (chromosome.size() != 10) {

            int a = randomNumberGenerator.nextInt(11);

            if (a == 0) {
                continue;
            }

            chromosome.add(a);
        }

        Iterator iterator = chromosome.iterator();

        while (iterator.hasNext()) {

            chromosomeListFormat.add((Integer) iterator.next());

        }

    }

    public List<Integer> getChromosome() {

        return chromosomeListFormat;

    }

    public int calculateTourDistance() {

        Integer[] cities = chromosomeListFormat.toArray(new Integer[chromosomeListFormat.size()]);
        CityDistance distance = new CityDistance();
        int result = 0;

        for (int i = 0; i < cities.length - 1; i++) {

            int from = cities[i];
            int to = cities[i + 1];

            result += distance.getDistance(from, to);

        }

        return result;

    }
}