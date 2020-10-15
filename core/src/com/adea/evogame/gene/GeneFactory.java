package com.adea.evogame.gene;

import java.util.ArrayList;
import java.util.List;

public class GeneFactory {

    public static final int GENOTYPE = 15;

    private static GeneFactory factory = null;

    private GeneFactory(){
    }

    public static GeneFactory getFactory(){
        if (factory == null){
            factory = new GeneFactory();
        }
        return factory;
    }

    public List<Gene> createGenotype(){
        ArrayList<Gene> genes = new ArrayList<>();
        for (int i = 0; i < GENOTYPE; i++) {
            genes.add(generateRandomGene());
        }
        return genes;
    }

    public Gene generateRandomGene() {
        int[] generatedGenes = new int[GeneCommand.values().length];
        for (int j = 0; j < generatedGenes.length; j++) {
            generatedGenes[j] = (int)(Math.random() * 26);
        }
        return new Gene(generatedGenes);
    }


}
