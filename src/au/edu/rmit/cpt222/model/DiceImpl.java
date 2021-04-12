// code adapted from my 2017 SADI A1
package au.edu.rmit.cpt222.model;

import java.util.concurrent.ThreadLocalRandom;

import au.edu.rmit.cpt222.model.interfaces.Dice;

public class DiceImpl implements Dice {

   private int face;

   // default constructor generating dice with a random face value (1-6)
   public DiceImpl() {
      setFace(ThreadLocalRandom.current().nextInt(1, NUM_OF_FACES + 1));
   }

   @Override
   public int getFace() {
      return this.face;
   }

   @Override
   public void setFace(int currentFace) {
      // check valid with assertion or exception
      if ((currentFace < 1) || (currentFace > Dice.NUM_OF_FACES))
         throw new IllegalArgumentException(
                  "Error! Dice face value is out of range");
      this.face = currentFace;
   }

   @Override
   public String toString() {
      return String.valueOf(this.face);
   }

}
