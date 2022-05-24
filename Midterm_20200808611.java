import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Midterm_20200808611 {

    static double accumulator;
    static double[] memory = new double[256];
    static int F =0;
    static int PC=0;
    static boolean halt=true;

    public static void main(String[] args){

        execute(args[0]);
    }
    static void execute(String args0){
        Map<Integer,HashMap<String,Double>> instructions = new HashMap<>();
        try {
            FileInputStream file = new FileInputStream(args0);
            Scanner myReader = new Scanner(file);


            while(myReader.hasNextLine()){

                String line = myReader.nextLine();
                Scanner scanner = new Scanner(line);
                String[] lineValues = new String[3];
                int i = 0;

                while (scanner.hasNext()) {
                    lineValues[i] = scanner.next();
                    i++;
                }

                HashMap<String,Double> inside=new HashMap<>();

                if(lineValues[2]==null){
                    inside.put(lineValues[1],null);
                }else{
                    inside.put(lineValues[1],Double.parseDouble(lineValues[2]));
                }

                instructions.put(Integer.parseInt(lineValues[0]),inside);
            }
            /*
            for (int i = 0; i < instructions.size(); i++) {
                System.out.println(instructions.get(i));
            }*/
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        while (halt) {
            String state="";
            double methodParameter=0.0;
            for (String s:instructions.get(PC).keySet()) {
                state=s;
                if(instructions.get(PC).get(s)!=null)
                    methodParameter=instructions.get(PC).get(s);
            }
            switch (state) {
                case "START" -> PC++;
                case "LOAD" -> load(methodParameter);
                case "LOADM" -> loadM(methodParameter);
                case "STORE" -> store(methodParameter);
                case "CMPM" -> cmpm(methodParameter);
                case "CJMP" -> cjmp(methodParameter);
                case "JMP" -> jmp(methodParameter);
                case "ADD" -> addX(methodParameter);
                case "ADDM" -> addMX(methodParameter);
                case "SUBM" -> submMX(methodParameter);
                case "SUB" -> subX(methodParameter);
                case "MUL" -> mulN(methodParameter);
                case "MULM" -> mulmN(methodParameter);
                case "DISP" -> disp();
                case "HALT" ->halt();
            }
        }
    }
    public static void load(double value) {
        accumulator=value;
        PC++;
    }
    public static void loadM(double value){
        accumulator=memory[(int)value];
        PC++;
    }
    public static void store(double value){
        memory[(int)value]=accumulator;
        PC++;
    }
    public static void cmpm(double value){
        F= Double.compare(accumulator, memory[(int)value]);
        PC++;
    }
    public static void cjmp(double value){
        if(F>0)
            PC =(int)value;
        else PC++;

    }
    public static void jmp(double value){
        PC=(int)value;
    }
    public static void addX(double value){
        accumulator+=value;
        PC++;
    }
    public static void addMX(double value){
        accumulator+=memory[(int)value];
        PC++;
    }
    public static void submMX(double value){
        accumulator-=memory[(int)value];
        PC++;
    }
    public static void subX(double value){
        accumulator-=value;
        PC++;
    }
    public static void mulN(double value){
        accumulator*=value;
        PC++;
    }
    public static void mulmN(double value){
        accumulator*=memory[(int)value];
        PC++;
    }
    public static void disp(){
        System.out.println(accumulator);
        PC++;
    }
    public static void halt(){
        halt=false;
        PC++;
    }
}




