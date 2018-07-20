package com.example.asus.Core.peer;


import java.io.Serializable;
import java.util.Comparator;

/**
 * A data exchange rate representation.

 * This is a utility class to keep track, and compare, of the data exchange
 * rate (either download or upload) with a peer.

 */

public class Rate implements Comparable<Rate>{

   public static final Comparator<Rate> RATE_COMPARATOR =
      new RateComparator();


   private long bytes = 0;
   private long reset = 0;
   private long last = 0;

   /**
    * Add a byte count to the current measurement.

    *count The number of bytes exchanged since the last reset.
    */

   public synchronized void add(long count) {
      this.bytes += count;
      if (this.reset == 0) {
         this.reset = System.currentTimeMillis();
      }
      this.last = System.currentTimeMillis();
   }

   /**
    * Get the current rate.

    * The exchange rate is the number of bytes exchanged since the last
    * reset and the last input.

    */


   public synchronized float get() {
      if (this.last - this.reset == 0) {
         return 0;
      }

      return this.bytes / ((this.last - this.reset) / 1000.0f);
   }



   /**
    * Reset the measurement.
    */
   public synchronized void reset() {
      this.bytes = 0;
      this.reset = System.currentTimeMillis();
      this.last = this.reset;
   }




   @Override
   public int compareTo(Rate o) {
      return RATE_COMPARATOR.compare(this, o);

   }

   private static class RateComparator
      implements Comparator<Rate>, Serializable {

      private static final long serialVersionUID = 72460233003600L;


      @Override
      public int compare(Rate a, Rate b) {
         if (a.get() > b.get()) {
            return 1;
         }

         return -1;
      }
   }

}