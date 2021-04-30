/*
 Author: Mike O'Malley
 Source File: FibonacciMysteryBigIntegers.java
 Description: Explore the Fibonacci Mystery and more.
 Inspiration: Fibonacci Mystery - Numberphile, https://www.youtube.com/watch?v=Nu-lW-Ifyec
 GitHub:      https://github.com/MooseValley/Math---Fibonacci-Numbers---Pisano-Period

Ammendment History
Ver   Date        Author    Details
----- ----------- --------  ----------------------------------------------------
0.001 05-Apr-2021  Mike O    Created.  Works great, but limited to Long integers.
0.002 05-Apr-2021  Mike O    Change over to BigIntegers.
0.003 05-Apr-2021  Mike O    Calculate frequency of Period (Pattern Length) numbers.
0.004 05-Apr-2021  Mike O    Write all output to file: 'output.txt'.

*/
import java.util.ArrayList;
import java.math.BigInteger;
import java.io.*;


public class FibonacciMysteryBigIntegers
{
   static final int MAX_SIZE = 20_000; // After this, they exceed the MAX Long integer.  i.e. overflow.

   static ArrayList<BigInteger> fibonacciSequenceArrayList = new ArrayList<BigInteger> ();


   // *** Copied from my: 00__common_code/Moose_Utils.java
   // strToFile, StringToFile
   // Example use:
   //   Moose_Utils.writeOrAppendStringToFile ("names.dat", "Mike\nFrankie\nBella", false);
   public static boolean writeOrAppendStringToFile (String fileName, String dataToWrite, boolean appendToFile)
   {
      BufferedWriter bw = null;
      FileWriter     fw = null;
      boolean result    = false; // ERROR / No data written.

      try
      {
         File file = new File(fileName);

         // if file doesnt exist, then create it:
         if (file.exists() == false)
         {
            file.createNewFile();
         }

         // if appendToFile is true, then append file.
         fw = new FileWriter(file.getAbsoluteFile(), appendToFile);
         bw = new BufferedWriter(fw);

         bw.write(dataToWrite);

         bw.close();
         fw.close();

         //System.out.println("Write to '" + fileName + "' = Done.");

         result  = true; // Data written.
      }
      catch (IOException e)
      {
         e.printStackTrace();
         result  = false; // ERROR / No data written.
      }
      finally
      {
         // Is this *really* necessary ?
         try
         {
            if (bw != null)
               bw.close();

            if (fw != null)
               fw.close();

         }
         catch (IOException ex)
         {
            ex.printStackTrace();
            result  = false; // ERROR / No data written.
         }
      }

      return result;
   } // public static boolean writeOrAppendStringToFile



   public static long displayFibonacciSequenceModN (int modValue)
   {
      long periodLength = -1; // None found !

      int priorNumberModded    = 0;
      int prepriorNumberModded = 0;

      BigInteger modValueBigInteger = new BigInteger ("" + modValue);

      // Display all MOD modValue:
      //System.out.print ("\n" + "Fibonacci Numbers MOD " + modValue + ":");
      for (int k = 0; k < MAX_SIZE; k++)
      {
         //System.out.print ( (fibonacciSequenceArrayList.get (k) % modValue) + ", ");
         //System.out.print ( fibonacciSequenceArrayList.get (k).mod (modValueBigInteger) + ", ");
         /*
         if (k             > 2)    // We are NOT at the start of the Fibonacci sequence
         {
            System.out.print ( fibonacciSequenceArrayList.get (k).mod (modValueBigInteger) +
               " ( " +
               fibonacciSequenceArrayList.get (k - 1).mod (modValueBigInteger) +
               ", " +
               fibonacciSequenceArrayList.get (k - 2).mod (modValueBigInteger) +
               ")" + ", ");
         }
         */

         if ((periodLength  < 0) &&   // We have not found the period yet.
             (k             > 2) &&   // We are NOT at the start of the Fibonacci sequence
             (fibonacciSequenceArrayList.get (k - 1).mod (modValueBigInteger).compareTo (BigInteger.ONE) == 0 ) && // The prior 2 digits were 1
             (fibonacciSequenceArrayList.get (k - 2).mod (modValueBigInteger).compareTo (BigInteger.ONE) == 0 ) )
         {
            periodLength = k - 2;
         }

      }
      //System.out.println ();
      //System.out.println (" Period Length = " + periodLength);

      return periodLength;
   }

   public static void main (String[] args)
   {
      // NOTE: This program needs to use x64 Java runtime.
      // Data too big for x32 bit.  StringBuilder runs out of space.

      StringBuilder sb    = new StringBuilder();

      sb.append ("\n");
      sb.append ("----------------------------------------------------------------" + "\n");
      sb.append ("Fibonacci Mystery Analysis - by Moose OMalley" + "\n");
      sb.append ("v0.04" + "\n");
      sb.append ("\n");
      sb.append ("GitHub: https://github.com/MooseValley/Math---Fibonacci-Mystery"  + "\n");
      sb.append ("----------------------------------------------------------------" + "\n");
      sb.append ("\n");

      sb.append ("NOTE: This program needs to use x64 Java runtime." + "\n");
      sb.append ("      Data too big for x32 bit.  StringBuilder runs out of space." + "\n");
      sb.append ("\n");

      sb.append ("java.version:    " + System.getProperty("java.version")           + "\n");
      sb.append ("32/64 bit:        x" + System.getProperty ("sun.arch.data.model") + "\n");
      sb.append ("java.vendor:     " + System.getProperty("java.vendor")            + "\n");
      sb.append ("java.vendor.url: " + System.getProperty("java.vendor.url")        + "\n");
      System.out.println(sb.toString() );



      // *** Part 1: generate Fibonacci Sequence numbers

      System.out.println ("\n" + "Fibonacci Sequence: generating the first " + MAX_SIZE + " numbers ...");
      sb.append ("\n");
      sb.append ("\n");
      sb.append ("Fibonacci Sequence: generating the first " + MAX_SIZE + " numbers ..." + "\n");

      fibonacciSequenceArrayList.add (new BigInteger ("1") );
      fibonacciSequenceArrayList.add (new BigInteger ("1") );

      for (int k = 2; k < MAX_SIZE; k++)
      {
         if (k % 1000 == 0)
            System.out.print ("."); // Print "." every 1,000 so I know all is OK, because things slow down for big numbers.

         BigInteger nextNum = new BigInteger ("" + fibonacciSequenceArrayList.get(k - 1) );
         nextNum = nextNum.add (fibonacciSequenceArrayList.get(k - 2) );

         fibonacciSequenceArrayList.add (nextNum );

         //sb.append (nextNum.toString() + ", ");
      }
      System.out.println ("\n" + "-> DONE !");
      //sb.append ("\n");
      sb.append ("-> Not output to reduce 'output.txt' file size." + "\n");
      sb.append ("-> DONE !" + "\n");


/*
      System.out.println ("\n" + "Fibonacci Numbers:");
      for (int k = 0; k < MAX_SIZE; k++)
      {
         System.out.print (fibonacciSequenceArrayList.get (k) + ", ");
      }
      System.out.println ();

      displayFibonacciSequenceModN (7);
      displayFibonacciSequenceModN (5);
      displayFibonacciSequenceModN (10);
*/


      // *** Part 2: determine Period Lengths (Repeating Pattern Lengths)

      StringAndCounter frequencyArray = new StringAndCounter (true, false); // Sorted list, NOT case sensitive.

      System.out.println ("\n" + "Fibonacci Sequence Repeating Pattern Lengths for Mod 2, 3, 4, 5, ... " + (MAX_SIZE / 2) );
      sb.append ("\n");
      sb.append ("Fibonacci Sequence Repeating Pattern Lengths for Mod 2, 3, 4, 5, ... " + (MAX_SIZE / 2) + "\n");
      for (int k = 2; k < MAX_SIZE / 2; k++)
      {
         if (k % 1000 == 0)
            System.out.print ("."); // Print "." every 1,000 so I know all is OK, because things slow down for big numbers.

         long patternLength = displayFibonacciSequenceModN (k);

         frequencyArray.addStringAndCount ("" + patternLength);

         //System.out.print (patternLength + ", ");
         sb.append (patternLength + ", ");
      }
      sb.append ("\n");
      sb.append ("-> DONE !" + "\n");
      System.out.println ("\n" + "-> DONE !");


      // *** Part 3: Period Lengths (Repeating Pattern Lengths) Frequency analysis

      System.out.println ("\n" + "Pattern Length Frequency:" );
      sb.append ("\n");
      sb.append ("Pattern Length Frequency:" + "\n");
      //System.out.println (frequencyArray.toString ());
      sb.append ("\n");
      sb.append (frequencyArray.toString () + "\n");
      sb.append ("\n");
      sb.append ("-> DONE !" + "\n");
      sb.append ("\n");
      System.out.println ("-> DONE !");
      System.out.println ();


      // *** Part 99: Write all output to file.

      writeOrAppendStringToFile ("output.txt", sb.toString(), false);
      System.out.println ();
      System.out.println ("All output written to file: 'output.txt'.");
      System.out.println ();

   }
}