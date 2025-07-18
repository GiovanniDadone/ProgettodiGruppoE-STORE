package com.example;
import java.util.Scanner;

public class GlobalScanner
{
    private static Scanner numScanner = null;
    private static Scanner stringScanner = null;

    public static void InitScannerInputs()
    {
        numScanner = new Scanner(System.in);
        stringScanner = new Scanner(System.in);
    }

    //#region int input
    public static int readIntInput(String infoMsg, boolean negativeCheck)
    {
        return readIntInput(infoMsg, "Insert a valid number", negativeCheck);
    }

    public static int readIntInput(String infoMsg, String errMsg, boolean negativeCheck)
    {
        boolean exc = true;
        int numInput = -1;

        while(exc)
        {
            //checks if the input goes in exception
            exc = false;

            try
            {
                //numInput input
                System.out.println(infoMsg);
                numInput = readIntInput();

                if(negativeCheck)
                {
                    //if the inserted number is negative
                    while(numInput < 0)
                    {
                        System.out.println(errMsg);
                        numInput = readIntInput();
                    }
                }
            }
            
            catch (Exception e)
            {
                System.out.println("Inserted format not correct.\n" + errMsg);
                exc = true;
            }
        }

        return numInput;
    }

    public static int readIntInput()
    {
        return numScanner == null ? null : numScanner.nextInt();
    }
    //#endregion


    //#region float input
    public static float readFloatInput(String infoMsg, boolean negativeCheck)
    {
        return readFloatInput(infoMsg, "Insert a valid number", negativeCheck);
    }

    public static float readFloatInput(String infoMsg, String errMsg, boolean negativeCheck)
    {
        boolean exc = true;
        float numInput = -1.0f;

        while(exc)
        {
            //checks if the input goes in exception
            exc = false;

            try
            {
                //numInput input
                System.out.println(infoMsg);
                numInput = readFloatInput();

                if(negativeCheck)
                {
                    //if the inserted number is negative
                    while(numInput < 0)
                    {
                        System.out.println(errMsg);
                        numInput = readFloatInput();
                    }
                }
            }
            
            catch (Exception e)
            {
                System.out.println("Inserted format not correct.\n" + errMsg);
                exc = true;
            }
        }

        return numInput;
    }

    public static float readFloatInput()
    {
        return numScanner == null ? null : numScanner.nextFloat();
    }
    //#endregion


    //#region string input
    public static String readStringInput(String infoMsg, boolean emptyCheck)
    {
        return readStringInput(infoMsg, "Insert a valid value", emptyCheck);
    }

    public static String readStringInput(String infoMsg, String errMsg, boolean emptyCheck)
    {
        boolean exc = true;
        String stringInput = "";

        while(exc)
        {
            //checks if the input goes in exception
            exc = false;

            try
            {
                //stringInput input
                System.out.println(infoMsg);
                stringInput = readStringInput();

                if(emptyCheck)
                {
                    //if the inserted number is negative
                    while(stringInput.isEmpty())
                    {
                        System.out.println(errMsg);
                        stringInput = readStringInput();
                    }
                }
            }
            
            catch (Exception e)
            {
                System.out.println("Inserted format not correct.\n" + errMsg);
                exc = true;
            }
        }

        return stringInput;
    }

    public static String readStringInput()
    {
        return stringScanner == null ? null : stringScanner.nextLine();
    }
    //#endregion

    public static void CloseScannerInputs()
    {
        numScanner.close();
        stringScanner.close();
    }
}