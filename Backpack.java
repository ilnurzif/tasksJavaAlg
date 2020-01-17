package com.company;

import java.util.ArrayList;
import java.util.List;

public class Backpack {

    private List<Thing> bestThings = null;
    private double maxW;
    private double bestSumm;

    public Backpack(double maxWeight) {
        maxW = maxWeight;
    }

    private double calcWeigth(List<Thing> things) {
        double sumW = 0;
        for (Thing i : things) {
            sumW += i.weigth;
        }
        return sumW;
    }


    public List<Thing> getBestCombination()
    {
        return bestThings;
    }

    private double getPrice(List<Thing> things) {
        double sumPrice = 0;
        for (Thing i : things)
            {
                sumPrice += i.price;
            }
            return sumPrice;
    }

    private void checkThings(List<Thing> things)
    {
        if (bestThings == null)
        {
            if (calcWeigth(things) <= maxW)
            {
                bestThings = things;
                bestSumm = getPrice(things);
            }
        }
        else
        {
            if(calcWeigth(things) <= maxW && getPrice(things) > bestSumm)
            {
                bestThings = things;
                bestSumm = getPrice(things);
            }
        }
    }

 public void checkCombinations(List<Thing> things)
    {
        if (things.size()> 0)
            checkThings(things);

        for (int i = 0; i < things.size(); i++)
        {
            List<Thing> newSet = new ArrayList<Thing>(things);
            newSet.remove(i);
            checkCombinations(newSet);
        }
    }

}

