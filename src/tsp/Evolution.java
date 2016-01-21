package tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author oguzcagiran
 */
public class Evolution {

    private static final Random RANDOM = new Random();
    private int bestDistance = 999999;
    private Chromosome bestChromosome;
    private double crossoverRate;
    private double mutationRate;

    public Evolution(double crossoverRate, double mutationRate) {
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
    }

    public void start(int iteration) {

        List<Chromosome> chromosomeList = new ArrayList<>();
        List<Double> fitnessList = new ArrayList<>();
        List<Double> rouletteWheelTable = new ArrayList<>();
        List<Chromosome> selectedChromosomeList = new ArrayList<>();
        List<Double> crossoverRateList = new ArrayList<>();
        List<Chromosome> crossoverChromosomeList = new ArrayList<>();
        
        createPopulation(chromosomeList);
        
        for (int i = 0; i < iteration; i++) {

            createFitnessList(chromosomeList, fitnessList);
            double sumOfFitness = calculateSumOfFitness(chromosomeList, fitnessList);
            createRouletteWheelTable(sumOfFitness, chromosomeList, fitnessList, rouletteWheelTable);
            selectFromPopulation(chromosomeList, selectedChromosomeList, rouletteWheelTable);
            createCrossoverRateList(crossoverRateList);
            createCrossoverChromosomeList(selectedChromosomeList, crossoverChromosomeList, crossoverRateList);
            makeCrossover(selectedChromosomeList, crossoverChromosomeList);
            makeMutation(selectedChromosomeList);
            
            crossoverChromosomeList.clear();
            crossoverRateList.clear();
            rouletteWheelTable.clear();
            fitnessList.clear();
            chromosomeList.clear();
            
            for (int j = 0; j < selectedChromosomeList.size(); j++) {
                
                Chromosome c = selectedChromosomeList.get(j);
                chromosomeList.add(c);
                int distance = c.calculateTourDistance();
                
                if(distance <= bestDistance) {
                    bestDistance = distance;
                    bestChromosome = c;
                }
                
            }
            
            selectedChromosomeList.clear();
        }
        
        System.out.println("Best Tour = " + bestChromosome.getChromosome());
        System.out.println("Distance = " + bestDistance);
    }
  
    /**
     * Makes mutation on genes
     */
    public void makeMutation(List<Chromosome> selectedChromosomeList) {
        
        double[] mutationRate = new double[80];
        
        for (int i = 0; i < 80; i++) {
            double rate = RANDOM.nextDouble();
            mutationRate[i] = rate;   
        }
        
        for (int i = 0; i < 80; i++) {
            if(mutationRate[i] <= this.mutationRate) {
                
                int chromosomeNumber = i / 10;
                int genNumber = i % 10;
                
                List<Integer> chromosome = selectedChromosomeList.get(chromosomeNumber).getChromosome();
                int mutuationGen = RANDOM.nextInt(9) + 1;
                
                int first = chromosome.get(genNumber);
                int second = chromosome.get(mutuationGen);
                
                chromosome.set(genNumber, second);
                chromosome.set(mutuationGen, first);
                
            }
        }
    }
    

    /**
     * Makes crossover and add 
     * new Chromosomes to population
     *
     */
    private void makeCrossover(List<Chromosome> selectedChromosomeList ,List<Chromosome> crossoverChromosomeList) {

        int size = crossoverChromosomeList.size();

        /**
         * If size is odd delete an
         * element for crossover pair
         */
        if (size % 2 != 0) {
            crossoverChromosomeList.remove(0);
        }

        for (int i = 0; i < crossoverChromosomeList.size(); i = i + 2) {

            List<Integer> firstParent = crossoverChromosomeList.get(i).getChromosome();
            List<Integer> secondParent = crossoverChromosomeList.get(i + 1).getChromosome();
            List<Integer> firstnewChromosome = new ArrayList<>();
            List<Integer> secondnewChromosome = new ArrayList<>();
           
            int crossoverPoint = RANDOM.nextInt(9) + 1;

            for (int j = 0; j < crossoverPoint; j++) {
                firstnewChromosome.add(firstParent.get(j));
            }

            for (int j = 0; j < secondParent.size(); j++) {
                if (!firstnewChromosome.contains(secondParent.get(j))) {
                    firstnewChromosome.add(secondParent.get(j));
                }
            }

            for (int j = crossoverPoint; j < secondParent.size(); j++) {
                secondnewChromosome.add(secondParent.get(j));  
            }
            
          
            for (int j = 0; j < firstParent.size(); j++) {
                if(!secondnewChromosome.contains(firstParent.get(j))) {
                    secondnewChromosome.add(firstParent.get(j));
                }
                
            }

            selectedChromosomeList.remove(crossoverChromosomeList.get(i));
            selectedChromosomeList.remove(crossoverChromosomeList.get(i+1));
            selectedChromosomeList.add(new Chromosome(firstnewChromosome));
            selectedChromosomeList.add(new Chromosome(secondnewChromosome));

        }
        
    }

    /**
     * Creates list which contains 
     * chromosomes for crossover
     */
    private void createCrossoverChromosomeList(List<Chromosome> selectedChromosomeList,
                                               List<Chromosome> crossoverChromosomeList, 
                                               List<Double> crossoverRateList) {
        for (int j = 0; j < crossoverRateList.size(); j++) {
            if (crossoverRateList.get(j) <= crossoverRate) {
                Chromosome chromosome = selectedChromosomeList.get(j);
                crossoverChromosomeList.add(chromosome);
            }
        }
    }

    /**
     * Creates crossover rate o
     * f each chromosome
     */
    private void createCrossoverRateList(List<Double> crossoverRateList) {
        for (int j = 0; j < 6; j++) {
            double probability = RANDOM.nextDouble();
            crossoverRateList.add(probability);
        }
    }

    /**
     * Selects chromosome from 
     * population using roulette wheel
     */
    private void selectFromPopulation(List<Chromosome> chromosomeList, 
                                      List<Chromosome> selectedChromosomeList, 
                                      List<Double> rouletteWheelTable) {

        for (int chromosome = 0; chromosome < 8; chromosome++) {

            double rand = RANDOM.nextDouble();

            if(selectedChromosomeList.size() == 8){
                break;
            } 

            for (int j = 0; j < 8; j++) {

                double probabilityLower = rouletteWheelTable.get(j);
                double probabilityUpper = rouletteWheelTable.get(j + 1);

                if (rand >= probabilityLower && rand < probabilityUpper) {
                    Chromosome chromosomeSelected = chromosomeList.get(j);
                    selectedChromosomeList.add(chromosomeSelected);
                }

            }
        }

    }

    /**
     * Calculates each chromosome 
     * roulette wheel probability and add them
     * probability list
     *
     * @param sumOfFitness
     */
    private void createRouletteWheelTable(double sumOfFitness, 
                                          List<Chromosome> chromosomeList,
                                          List<Double> fitnessList ,
                                          List<Double> rouletteWheelTable) {
        double sum = 0;
        rouletteWheelTable.add(0d);
        
        for (int chromosome = 0; chromosome < chromosomeList.size(); chromosome++) {
            double probability = (double) fitnessList.get(chromosome) / (double) sumOfFitness;
            sum += probability;
            rouletteWheelTable.add(sum);
        }

    }

    /**
     * Add all Fitness values
     *
     * @return sumAllFitness
     */
    private double calculateSumOfFitness(List<Chromosome> chromosomeList, List<Double> fitnessList) {

        double sumOfFitness = 0;

        for (int chromosome = 0; chromosome < chromosomeList.size(); chromosome++) {
            sumOfFitness += fitnessList.get(chromosome);
        }

        return sumOfFitness;
    }

    /**
     * Gets fitness of each chromosome 
     * Add them fitnessList Fitness function
     * f(x) = 1 / distance
     */
    private void createFitnessList(List<Chromosome> chromosomeList, List<Double> fitnessList) {

        for (int chromosome = 0; chromosome < chromosomeList.size(); chromosome++) {
            double fitness = 1d / chromosomeList.get(chromosome).calculateTourDistance();
            fitnessList.add(fitness);
        }

    }

    /**
     * Creates initial population
     */
    private void createPopulation(List<Chromosome> chromosomeList) {

        for (int i = 0; i < 8; i++) {
            chromosomeList.add(new Chromosome());
        }

    }

}