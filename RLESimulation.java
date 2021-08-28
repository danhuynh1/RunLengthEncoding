
import java.util.ArrayList;
import java.util.HashSet;

/**
 * author github.com/danhuynh1
 * Image Process - Run Length Encoding (RLE)
 * RLE algorithm to compress bitmap images. it is a lossless comparison algorithm which means the output can be turned back to the original output
 * 
 * Here is a sequence of bytes (8 bits) representing color information for a picture
 * 0 0 0 0 76 113 113 24 ...[298x] 24
 * 
 * The encoding is as follows:
 * First byte is the color (0-255), 0 as Black and 255 as White
 * Second byte is the length of the run
 * If the run is longer than 255,t hen repeat the encoding as a series of shorter runs, the last being shorter than 255
 * 
 * For the above sequence the output becomes
 * C n C  n C   n C  n   C  n
 * 0 4 76 1 113 2 24 255 24 45
 */


public class RLESimulation{
	public static void main(String[] args) {
    //Test integer Array Value
    int[] initialArray = {0, 0, 0, 0, 76, 76, 76, 113, 113, 113, 113, 113, 0, 0, 0, 1};

    //Encoding.
    ArrayList<Integer> compressedCount = encodePixelArray(initialArray);
    System.out.println(compressedCount);

    //Decoding.
    System.out.println(decodePixelArray(compressedCount));
    
  }

  /*
  encodePixelArray Counts unique integer frequencies and adds to Array list
  Ex. {0,0,0,0,1,1,2} -> {0 4 1 2 2 1}
  Where the an entry to the ArrayList is the unique integer, followed by the frequency of the it.
  @edge case:
  if the count exceeds 255, another entry will be placed such that 0*255 0 0 0 =/= {0 258} but {0 255 0 3}
  @params pixelArray is a "Picture" in pixels represented as an array of integers
  @returns "encoded" arraylist
  */

  public static ArrayList<Integer> encodePixelArray(int[] pixelArray){
    ArrayList<Integer> compressedCount = new ArrayList<Integer>();
    //gets the first pixel to be handled.
    int currentPixel = pixelArray[0];
    int count = 1;

    for (int i = 1; i < pixelArray.length;i++){

      //if the pixel being looked at is the same as the handled currentPixel, the count is increased.
        if (currentPixel == pixelArray[i]){
          count+=1;
          //edge case: if the count is now 255, an entry is made into the ArrayList, and then the count is reset to cover the following.
          if (count >4){
              myEncode(compressedCount, currentPixel, count-1);
              count = 1;
          }
        }

        //If the pixel being looked at is different from what is being handled, an encoded entry is made, and the encountered pixel is now being handled and counted.
        else {
          myEncode(compressedCount, currentPixel, count);
          currentPixel = pixelArray[i];
          count = 1;
        }
      }

      //The last entry will be added.
      myEncode(compressedCount, currentPixel, count);
    return compressedCount;
}

  /*
  Overloading encodePixelArray 
  */
  public static ArrayList<Integer> encodePixelArray(ArrayList<Integer> pixelArrayList){
    int[] arr = pixelArrayList.stream().mapToInt(i -> i).toArray();
    return encodePixelArray(arr);
  }

  /*
  encode is a private helper method that inserts an entry {pixel, count} into an arraylist in form [pixel array]
  */
  private static void myEncode(ArrayList<Integer> compressedArray, int pixel, int count){
    compressedArray.add(pixel);
    compressedArray.add(count);
    return;
  }

  /*
  decodePixelArray is a method that takes an "encoded" ArrayList, in format {a n b m}
  where a and b are  pixel integers, n and m are the frequency of the integers respectfully

  @params compressedArray is an ArrayList of Integers in the format beforementioned
  @returns decodedPixelArray that is the exploded/decoded arrayList.
  */
  public static ArrayList<Integer> decodePixelArray(ArrayList<Integer> compressedArray){
    ArrayList<Integer> decodedPixelArray = new ArrayList<Integer>();
    //HashSet<Integer> uniquePixels = new HashSet<>();

    int currentPixel = compressedArray.get(0);
    //uniquePixels.add(currentPixel);
    //ArrayListFill(decodedPixelArray, compressedArray.get(0), compressedArray.get(1));


    //Every new pixel entry is in the array list on the second index
    for (int i = 0; i < compressedArray.size();i+=2){
      // if (uniquePixels.contains(compressedArray.get(i))){
      //   ArrayListFill(decodedPixelArray, compressedArray.get(i), compressedArray.get(i+1));
      // }
      // else{
      //   currentPixel = compressedArray.get(i);
      //   ArrayListFill(decodedPixelArray, compressedArray.get(i), compressedArray.get(i+1));
      // }
        ArrayListFill(decodedPixelArray, compressedArray.get(i), compressedArray.get(i+1));

    }
    
    return decodedPixelArray;
  }

  /*
  ArrayListFill is a helper method that adds an int element val to an ArrayList<Integer>, n times
  @params myArray, an arrayList of Integers to be added to.
  @params val, an integer that is desired to be added to myArray
  @params n, the amount of times val is to be added to myArray
  */
  private static void ArrayListFill(ArrayList<Integer> myArray,int val, int n){
    for (int i = 0; i < n; i++){
      myArray.add(val);
    }
    return;
  }
}



