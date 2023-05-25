package managers;

import exceptions.*;

import java.io.*;

public class FileManager {
    private String path;
    public FileManager(String path){
        this.path = path;
    }

    public String read(){
        String str="";
        FileReader fileReader;
        try{
            if(path.isEmpty())throw new EmptyPathException();
            File file = new File(path);
            if(!file.exists()) throw new FileNotFoundException("File doesn't exist");
            if(!file.canRead()) throw new FileWrongPermissionsException("cannot read file");
            fileReader = new FileReader(file);
            int i ;
           while((i = fileReader.read()) != -1){
                str += (char)i;
            }
           fileReader.close();
        }catch (FileException exception){
            System.err.println(exception.getMessage());
        }catch (IOException e) {
            System.err.println("cannot access file");
        }
        return str;
    }
    public boolean write(String json){
        boolean success = false;
        BufferedWriter writer;
        try{
            writer = new BufferedWriter(new FileWriter(path));
            writer.write(json);
            writer.close();
            success = true;
        }catch (IOException exception){
            System.err.println("");
        }
        return success;
    }
    public String getFileName(String path){
        return new File(path).getName();
    }
}
