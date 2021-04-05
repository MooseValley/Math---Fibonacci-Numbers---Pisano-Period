/*
https://www.youtube.com/watch?v=Nu-lW-Ifyec
Fibonacci Mystery - Numberphile
MoosesValley
Mon, 05-Apr-2021, 11:19 AM
The Fibonacci Sequence sure has hidden depths.  Fascinating video !  Thank you !!


https://en.wikipedia.org/wiki/Fibonacci
Fibonacci (c. 1170 – 1240–50) also known as Leonardo Bonacci, Leonardo of Pisa, or Leonardo Bigollo Pisano.

Pisano Period: you can divide by any positive integer > 1 and still get a repeating pattern of digits - but the length of the pattern would vary.

Fibonacci Numbers:
1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, 2971215073, 4807526976, 7778742049, 12586269025, 20365011074, 32951280099, 53316291173, 86267571272, 139583862445, 225851433717, 365435296162, 591286729879, 956722026041, 1548008755920, 2504730781961, 4052739537881, 6557470319842, 10610209857723, 17167680177565, 27777890035288, 44945570212853, 72723460248141, 117669030460994, 190392490709135, 308061521170129, 498454011879264, 806515533049393, 1304969544928657, 2111485077978050, 3416454622906707, 5527939700884757, 8944394323791464, 14472334024676221, 23416728348467685, 37889062373143906, 61305790721611591, 99194853094755497, 160500643816367088, 259695496911122585, 420196140727489673, 679891637638612258, 1100087778366101931, 1779979416004714189, 2880067194370816120,

Mod 7 (period length = 16):   1, 1, 2, 3, 5, 1, 6, 0, 6, 6, 5, 4, 2, 6, 1, 0,
Mod 5 (period length = 20):   1, 1, 2, 3, 0, 3, 3, 1, 4, 0, 4, 4, 3, 2, 0, 2, 2, 4, 1, 0,
Mod 10 (i.e. get last digit, period length = 60):   1, 1, 2, 3, 5, 8, 3, 1, 4, 5, 9, 4, 3, 7, 0, 7, 7, 4, 1, 5, 6, 1, 7, 8, 5, 3, 8, 1, 9, 0, 9, 9, 8, 7, 5, 2, 7, 9, 6, 5, 1, 6, 7, 3, 0, 3, 3, 6, 9, 5, 4, 9, 3, 2, 5, 7, 2, 9, 1, 0,
MOD 100 (last 2 digits, period length = 300)


The period (the repeating pattern of digits) can only have 1 x 0, 2 x 0's, or 4 x 0's.

Fn | Fm iff n | m
The nth Fibonacci number "exactly divides" (no remainder) the mth Fibonacci number if and only if n "exactly divides" (no remainder) m.
Every 5th Fibonacci number (which just happens to be a 5) is evenly divisable by 5 !
Every 6th Fibonacci number (6) is evenly divisable by 8 !


The Fibonacci rule still holds for the repeating period sets above.
Fn = Fn-1 + Fn-2

When the digits go back to 1, 1, then you are at the start of the Fibonacci sequence and at the start of a period (the repeating pattern of digits) !

There is no general formula for the length of the period.

*/
import java.util.ArrayList;


public class FibonacciMystery
{
   static final int MAX_SIZE = 90; // After this, they exceed the MAX Long integer.  i.e. overflow.
   static ArrayList<Long> fibonacciSequenceArrayList = new ArrayList<Long> ();


   public static void displayFibonacciSequenceModN (int modValue)
   {
      int periodLength = 0;

      // Display all MOD modValue:
      System.out.println ("\n" + "Fibonacci Numbers MOD " + modValue + ":");
      for (int k = 0; k < MAX_SIZE; k++)
      {
         System.out.print ( (fibonacciSequenceArrayList.get (k) % modValue) + ", ");

         if ((periodLength == 0) &&   // We have not found the period yet.
             (k             > 2) &&   // We are NOT at the start of the Fibonacci sequence
             (fibonacciSequenceArrayList.get (k - 1) % modValue == 1) && // The prior 2 digits were 1
             (fibonacciSequenceArrayList.get (k - 2) % modValue == 1) )
         {
            periodLength = k - 2;
         }
      }
      System.out.println ();
      System.out.println ("-> Period Length: " + periodLength);
   }

   public static void main (String[] args)
   {
      fibonacciSequenceArrayList.add (1L );
      fibonacciSequenceArrayList.add (1L );

      for (int k = 2; k < MAX_SIZE; k++)
      {
         fibonacciSequenceArrayList.add (fibonacciSequenceArrayList.get(k - 1) +
                                         fibonacciSequenceArrayList.get(k - 2) );
      }

      System.out.println ("\n" + "Fibonacci Numbers:");
      for (int k = 0; k < MAX_SIZE; k++)
      {
         System.out.print (fibonacciSequenceArrayList.get (k) + ", ");
      }
      System.out.println ();


      displayFibonacciSequenceModN (7);
      displayFibonacciSequenceModN (5);
      displayFibonacciSequenceModN (10);

   }
}