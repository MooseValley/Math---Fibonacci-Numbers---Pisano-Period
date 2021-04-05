// *** Copied from my: 00__common_code/StringAndCount.java

import java.util.ArrayList;

class StringAndCount
{
   private String itemStr;
   private int    itemCount;

   public StringAndCount (String itemStr)
   {
      this.itemStr   = itemStr;
      this.itemCount = 1;
   }

   public void incrementItemCount ()
   {
      itemCount++;
   }

   public int getItemCount ()
   {
      return itemCount;
   }

   public String getItemStr ()
   {
      return itemStr;
   }

   @Override
   public String toString ()
   {
      return itemStr + ": " + itemCount;
   }

}


public class StringAndCounter
{
   // Class data:
   private ArrayList<StringAndCount> stringAndCountArrayList  = new ArrayList<StringAndCount>();
   private boolean isListSorted;
   private boolean isCaseSensitive;

   public StringAndCounter (boolean sorted, boolean caseSensitive)
   {
      stringAndCountArrayList.clear ();   // Remove ALL items in the ArrayList.
      isListSorted    = sorted;
      isCaseSensitive = caseSensitive;
   }

   private void addStringToSortedList (String itemStr)
   {
      boolean alreadyInArray = false;
      boolean addToArray     = false;
      int     foundLocation  = 0;

      //System.out.println ("\n" + itemStr + ":");
      for (int k = 0; k < stringAndCountArrayList.size (); k++)
      {
         if (alreadyInArray == false)
         {
            /*
            if (isCaseSensitive == true)
               System.out.println ("* vs array [" + k + "]: " + stringAndCountArrayList.get(k).getItemStr () +
                                    " = " + itemStr.compareTo  (stringAndCountArrayList.get(k).getItemStr ()));
            else
               System.out.println ("* vs array [" + k + "]: " + stringAndCountArrayList.get(k).getItemStr () +
                                   " = " + itemStr.compareToIgnoreCase (stringAndCountArrayList.get(k).getItemStr ()));
            */

            if ((isCaseSensitive == true)       && (itemStr.compareTo           (stringAndCountArrayList.get(k).getItemStr ()) < 0))
            {
               addToArray = true;
            }
            else if ((isCaseSensitive == false) && (itemStr.compareToIgnoreCase (stringAndCountArrayList.get(k).getItemStr ()) < 0))
            {
               addToArray = true;
            }

            if (addToArray == true)
            {
               //System.out.println ("inserting at array [" + k + "]: " + itemStr);
               stringAndCountArrayList.add (k, new StringAndCount (itemStr));
               alreadyInArray = true;
            }
         }
      }

      if (alreadyInArray == false)
      {
         //System.out.println ("adding to end of the array: " + itemStr);
         stringAndCountArrayList.add (new StringAndCount (itemStr));
      }
   }

   public void addStringAndCount (String itemStr)
   {
      boolean alreadyInArray = false;
      int     foundLocation  = 0;

      for (int k = 0; k < stringAndCountArrayList.size (); k++)
      {
         if ((isCaseSensitive == true)       && (itemStr.compareTo           (stringAndCountArrayList.get(k).getItemStr ()) == 0))
         {
            foundLocation  = k;
            alreadyInArray = true; // itemStrs match.
         }
         else if ((isCaseSensitive == false) && (itemStr.compareToIgnoreCase (stringAndCountArrayList.get(k).getItemStr ()) == 0))
         {
            foundLocation  = k;
            alreadyInArray = true; // itemStrs match.
         }
      }

      if (alreadyInArray == true)
      {
          // Item is already in the array - so increment count.
          stringAndCountArrayList.get(foundLocation).incrementItemCount ();
      }
      else
      {
         if (isListSorted == true)
         {
            addStringToSortedList (itemStr);
         }
         else
         {
            //System.out.println ("adding to end of the array: " + itemStr);
            stringAndCountArrayList.add (new StringAndCount (itemStr));
         }
      }
   }

   @Override
   public String toString ()
   {
      String outString = "";

      for (int k = 0; k < stringAndCountArrayList.size (); k++)
      {
         outString = outString + stringAndCountArrayList.get(k).toString () + "\n";
      }

      return outString;
   }

// ProGuard: need to comment out the main - otherwise
// ProGuard wont fully process / anonymise this class.
/*
   public static void main (String args[])
   {
      // For testing:
      StringAndCounter array = new StringAndCounter (true, false); // Sorted list, NOT case sensitive.
      array.addStringAndCount ("Mike");
      array.addStringAndCount ("Frankie");
      array.addStringAndCount ("Hankel");
      array.addStringAndCount ("Hankel");
      array.addStringAndCount ("Frankie");
      array.addStringAndCount ("Hankel");
      array.addStringAndCount ("ABC");
      array.addStringAndCount ("abc");

      System.out.println (array.toString ());
   } // public static void main
*/
}