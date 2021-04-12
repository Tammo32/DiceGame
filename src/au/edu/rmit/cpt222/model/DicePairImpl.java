//code adapted from my 2017 SADI A1
package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.model.interfaces.Dice;
import au.edu.rmit.cpt222.model.interfaces.DicePair;

public class DicePairImpl implements DicePair {

   private Dice dice1;
   private Dice dice2;

   public DicePairImpl() {
      this(new DiceImpl(), new DiceImpl());
   }

   public DicePairImpl(Dice dice1, Dice dice2) {
      this.dice1 = dice1;
      this.dice2 = dice2;
   }

   @Override
   public Dice getDice1() {
      return this.dice1;
   }

   @Override
   public Dice getDice2() {
      return this.dice2;
   }

   @Override
   public int getTotalScore() {
      return this.dice1.getFace() + this.dice2.getFace();
   }

   @Override
   public String toString() {
      // return "Dice 1: " + dice1.getFace() + ", Dice 2: " + dice2.getFace() +
      // " ... Total = " + getTotalScore();

      StringBuilder resultString = new StringBuilder();
      resultString.append("Dice 1: ");
      resultString.append(this.dice1);
      resultString.append(", Dice 2: ");
      resultString.append(this.dice2);
      resultString.append(" ... Total = ");
      resultString.append(getTotalScore());
      return resultString.toString();
   }

}
