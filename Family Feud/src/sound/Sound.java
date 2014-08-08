package sound;

/**
 * Class that represents a sound.  This class is used by the students
 * to extend the capabilities of SimpleSound. 
 * 
 * Copyright Georgia Institute of Technology 2004
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Sound extends SimpleSound
{
  
  /////////////// consructors ////////////////////////////////////
  
  /**
   * Constructor that takes a file name
   * @param fileName the name of the file to read the sound from
   */
  public Sound(String fileName)
  {
    // let the parent class handle setting the file name
    super(fileName);
  }
  
  /**
   * Constructor that takes the number of samples in
   * the sound
   * @param numSamples the number of samples desired
   */
  public Sound (int numSamples)
  {
    // let the parent class handle this
    super(numSamples);
  }
  
  /**
   * Constructor that takes the number of samples that this
   * sound will have and the sample rate
   * @param numSamples the number of samples desired
   * @param sampleRate the number of samples per second
   */
  public Sound (int numSamples, int sampleRate)
  {
    // let the parent class handle this
    super(numSamples,sampleRate);
  }
  
  /**
   * Constructor that takes a sound to copy
   */
  public Sound (Sound copySound)
  {
    // let the parent class handle this
    super(copySound);
  }
  
  ////////////////// methods ////////////////////////////////////
  
  /**
   * Method to return the string representation of this sound
   * @return a string with information about this sound
   */
  public String toString()
  {
    String output = "Sound";
    String fileName = getFileName();
    
    // if there is a file name then add that to the output
    if (fileName != null)
      output = output + " file: " + fileName;
    
    // add the length in frames
    output = output + " number of samples: " + getLengthInFrames();
    
    return output;
  }
 
  public static void main(String[] args)
  {
    Sound sound1 = new Sound(FileChooser.pickAFile());
    sound1.explore();
  }

    //********CUT the following methods into Sound.java
  /*
  public void clipInto(Sound mySound, int endingIndex)
  {
    for (int i = 0; i < endingIndex; i++)
    {
      int value = mySound.getSampleValueAt(i);
      this.setSampleValueAt(i,value);
    }
  }
  */
  
  
  public void clipInto(Sound mySound, int endingIndex)
  {
    SoundSample[] source = mySound.getSamples();
    SoundSample[] result = this.getSamples();
    for (int i = 0; i < endingIndex; i++)
    {
       int value = source[i].getValue();
       result[i].setValue(value);
    }
  }
  /*
  public int maximum()
  {
    SoundSample[] array = this.getSamples();
    int maxIndex = -1; // start at negative one to denote an empty array
    int max = 0;
    // if the array is not empty, set the current max to be the first element in the array
    if(array.length>=1)
    {
      max = array[0].getValue();
      maxIndex = 0;
    }
    for(int i =0;i<array.length;i++)
    {
      int currVal = array[i].getValue();
      if(Math.abs(currVal) > Math.abs(max))
      {
        max = currVal;
        maxIndex = i;
      }
    }
    return maxIndex;
  }
  */
  /*
  public void echo(int delay)
  {
   Sound s = new Sound(this.getFileName());
   int value = 0;
   for (int i = delay; i < this.getLength(); i++)
   {
     value = (int) (s.getSampleValueAt(i-delay) * 0.6);
     this.setSampleValueAt(i,this.getSampleValueAt(i) + value);
   }
   
  }
*/
  public void decreasePitch(double factor)
  {
   Sound s = new Sound(this.getFileName());
   SoundSample[] source = s.getSamples();
   SoundSample[] target = this.getSamples();
   for (double sourceIndex=0, targetIndex=0;
        targetIndex < this.getLength();
        sourceIndex=sourceIndex+factor, targetIndex++)
   {
              target[(int)targetIndex].setValue(source[(int)sourceIndex].getValue());
   }
  }
public void reverseInto(Sound mySound)
{
    SoundSample[] source = mySound.getSamples();
    SoundSample[] result = this.getSamples();
    //System.out.println("The last indexable value in result is: " + (result.length-1));
    //System.out.println("The last indexable value in source is: " + (source.length-1));
    
    for (int resultIndex = 0, sourceIndex = source.length-1; 
         sourceIndex >= 0 && resultIndex < result.length;
         resultIndex++, sourceIndex--)
    {
       int value = source[sourceIndex].getValue();
       result[resultIndex].setValue(value);
    }
}
  

public void echo(int delay, int startAt, int endAt)
  {
   Sound s = new Sound(this.getFileName());
   int value = 0;
   for (int i = startAt; i < endAt && i<s.getLength(); i++)
   {
     value = (int) (s.getSampleValueAt(i) * 0.6);
     //value = (int) (this.getSampleValueAt(i-delay) * 0.6);
     this.setSampleValueAt(i+delay,this.getSampleValueAt(i+delay) + value);
   }
   
 }

public Sound lowerPitch(double factor)
{
   Sound result = new Sound((int)(this.getLength()*factor));
   System.out.println("original length = "+this.getLength()+" result length = "+result.getLength());
   double resultIndex = 0.0;
   int sourceIndex = 0;
   while(((int)resultIndex)<result.getLength() && sourceIndex<this.getLength())
   {
     int tempResultIndex = (int)resultIndex;
     while((tempResultIndex < (int)resultIndex+factor))
     {
       if(tempResultIndex< 100)
       {
       System.out.println("Setting index "+tempResultIndex+" with "+sourceIndex);
       }
       result.setSampleValueAt(tempResultIndex, this.getSampleValueAt(sourceIndex));
       tempResultIndex++;
     }
     sourceIndex++;
     resultIndex+=factor;
   }
   System.out.println("Finishing - Setting index "+resultIndex+" with "+sourceIndex);
   return result;
}
  
public double maximum()
  {     
     int maxSound = 0;
      int maxIndex = -1;
     SoundSample[] soundArray = this.getSamples();
     for (int i = 0; i < soundArray.length; i++)
     {
       
        //int maxSound = 0;
        int currSound = this.getSampleValueAt(i);
        if (Math.abs(maxSound) < Math.abs(currSound))
        {
           maxSound = currSound;
           maxIndex = i;
        }
     }
     
     double timeOfMax = (double)maxSound / 15000;
     //double timeOfMax = (double)maxIndex / 15000;
     return timeOfMax;
  }

public void changeLoudness(int start, int end, double factor)
{
  SoundSample[] soundArray = this.getSamples();
  for (int i = start; i < soundArray.length && i<end; i++)
  {
    int value = (int)(this.getSampleValueAt(i)*factor);
    this.setSampleValueAt(i,value);
  }
}

public void playLoudest(double amount)
{
SoundSample[] soundArray = this.getSamples();     
int maxSound = this.getSampleValueAt(0);
for (int i = 0; i < soundArray.length; i++)
{
  int currSound = this.getSampleValueAt(i);
if (Math.abs(maxSound) < Math.abs(currSound))      
{maxSound = currSound;
}
}

int range = Math.abs(maxSound)-0;  //Find range
int minLevel = maxSound - (int)(range*amount);  //Find minLevel from maxSound

for (int i = 0; i < soundArray.length; i++)
{
  int currSound = this.getSampleValueAt(i);
  if (Math.abs(currSound) < minLevel)
  {
    this.setSampleValueAt(i,0);
  }
}
}
public void makeExtreme()
{
SoundSample[] soundArray = this.getSamples();
for(int i = 0; i<soundArray.length;i++)
{
  if(soundArray[i].getValue()>=0)
  {
    soundArray[i].setValue(32767);
  }
  else
  {
    soundArray[i].setValue(-32767);
  }
}
}




  
  /*
   int maxSound = this.getSampleValueAt(0);
   SoundSample[] soundArray = this.getSamples();
   for (int i = 0; i < soundArray.length; i++)
   {
     int currSound = this.getSampleValueAt(i);
     if (Math.abs(maxSound) < currSound)      
     {
       maxSound = currSound;
     }
     //else if (minSound > currSound)
     {
       //minSound = currSound;
     }
   }
   int range = maxSound-0;  //Find range
   int minLevel = maxSound - (int)(range*amount);  //Find minLevel from maxSound

   for (int i = 0; i < soundArray.length; i++)
   {
     int currSound = this.getSampleValueAt(i);
     if (Math.abs(currSound) < minLevel)
     {
       this.setSampleValueAt(i,0);
     }
   }
   */
public void f8mystery()
{
  SoundSample[] noiseArray = this.getSamples();
  int x = 0; int y = 0;
  for (int i=0; i<noiseArray.length; i++)
  {
    SoundSample sample = noiseArray[i];
    int bar = sample.getValue();
    if (bar >= y)
    { 
      y = bar;
      x = i;
    }
  }
  System.out.println(x + " , "+y);
}


public void mystery3 (int a, int b)
{
  SoundSample[] noiseArray = this.getSamples();
  for (int i = a; i < b ;i++) 
  {
    SoundSample sample = noiseArray[i];
    int foo = sample.getValue();
    sample.setValue(foo/2);
  } 

}

public Sound mystery4()
{
  Sound answer = new Sound(this);
  SoundSample[] dest = answer.getSamples();
  SoundSample[] source = this.getSamples();  
  int bar = source.length-1;
  
  for (int foo = 0; foo < source.length; foo++)
  {
    dest[bar] = source[foo];
    bar--;
  }
  return answer;
}


}
//**********END CUT the following methods into Sound.java
  
//} // this } is the end of class Sound, put all new methods before this