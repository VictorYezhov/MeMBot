package com.bot.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 21.11.2017.
 */
public class FileSaver {

    static {
        f_IDs = new ArrayList<String>();
        file = new File("file_IDs.txt");
    }

    private static int ammountOfMem = computeAmountofMemsAvailable();
    private static List<String> f_IDs;
    private static File file;

    public static boolean saveMeMId(String f_id){

        if(!file.exists())
        {
            try {
                file.createNewFile();

            }catch (IOException e)
            {
                System.err.println("Can`t create file");
                return false;
            }
        }
        try {
            BufferedWriter  fw;
            fw = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true));
            fw.write(f_id);
            fw.append(System.getProperty("line.separator"));
            fw.flush();
            ammountOfMem++;
            fw.close();
        }catch (FileNotFoundException e) {
            System.err.println("File not found");
            return false;
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.out.println("Can`t write to file");
            return false;
        }
        System.out.println(ammountOfMem);
        return true;
    }

    public  static  String getRandomId(){
        String f_id;
        int index = (int)(Math.random()*ammountOfMem);
        int counter =0;


        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while ((f_id = reader.readLine()) != null) {
                if(index == counter++){
                    return f_id;
                }
            }
            reader.close();
        } catch (IOException e) {
           e.printStackTrace();
        }

        return f_IDs.get((int)(Math.random()*ammountOfMem));
    }

    public static int getAmmountOfMem() {
        return ammountOfMem;
    }

    public static void setAmmountOfMem(int ammountOfMem) {
        FileSaver.ammountOfMem = ammountOfMem;
    }

    private static int computeAmountofMemsAvailable(){
        int counter=0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            while (reader.readLine() != null) counter++;
            reader.close();

        }catch (FileNotFoundException e){
            System.out.println("FILE NOT FOUND");
        }catch (IOException e) {
            System.out.println("IO EXEPTION");
        }
        return counter;


    }



}
