package facebook;

import java.util.*;

/**
Prettify JSON: 输入[1,2,3, {"id": 1, "name": "wang", "tag":[1,"home",2], "price":234}]
 */
public class JsonParser {

    boolean isInBracket(String str, int ind){
        int leftBracket = 0;
        int rightBracket = 0;
        int leftCurlyBracket = 0;
        int rightCurlyBracket = 0;
        for(int i=1;i<ind;i++){
            if (str.charAt(i)=='{'){
            leftCurlyBracket++;
            }
            if (str.charAt(i)=='}'){
            rightCurlyBracket++;
            }
            if (str.charAt(i)=='['){
            leftBracket++;
            }
            if (str.charAt(i)==']'){
            rightBracket++;
            }
        }
        if(str.charAt(ind)==','){
            if (leftBracket>rightBracket||leftCurlyBracket>rightCurlyBracket){
            return true;
            }
        }
        return false;
    }
    boolean containsJsonObj(String line){
    int i=0;
    for(i=0;i<line.length();i++){
        if(line.charAt(i)==':'){
        break;
        }
    }
    while(i<line.length()-1&&line.charAt(i+1)==' '){
        i++;
    }
    if(i<line.length()-1){
        if(line.charAt(i+1)=='['||line.charAt(i+1)=='{'){
        return true;
        }
    }
    return false;
    }
    char firstBracket(String line){
    for(int i=0;i<line.length();i++){
        if(line.charAt(i)=='['||line.charAt(i)=='{'){
        return line.charAt(i);
        }
    }
    return 'X';
    }
    char lastBracket(String line){
        for(int i=line.length()-1;i>=0;i--){
            if(line.charAt(i)==']'||line.charAt(i)=='}'){
            return line.charAt(i);
            }
        }
        return 'X';
    }
    public List<String> split(String line, int level){
        List<Integer> splitInd = new ArrayList<Integer>();
        splitInd.add(0);
        for(int i=1;i<line.length()-1;i++){
            if(line.charAt(i)==','){
                if(isInBracket(line,i)){
                    
                }else{
                    splitInd.add(i);
                }
            }
        }
        splitInd.add(line.length()-1);
        for(int i=0;i<splitInd.size()-1;i++){
            String section = line.substring(splitInd.get(i)+1,splitInd.get(i+1));
            for(int j=0;j<level;j++){
            System.out.print("\t");
            }
            if (containsJsonObj(section)){
            String array[]=section.split(":",2);
            System.out.println(array[0]+":"+firstBracket(array[1]));
            split(array[1],level+1);
            }else{
            System.out.println(section);
            }
            if (lastBracket(section)!='X'){
            for(int j=0;j<level;j++){
                System.out.print("\t");
            }
            System.out.println(lastBracket(section));
            }
        }
        return new ArrayList<String>();
    }
    public void print(String json){
    List<String> lines = split(json,0);
        for(int i=0;i<lines.size();i++){
            System.out.println(lines.get(i));
        }
    }

    public static void main(String args[]){
    JsonParser j = new JsonParser();
    String line =
"{\"hello\":\"world\",\"key1\":20,\"key2\":20.3,\"foo\":{\"hello1\":\"world1\",\"hello5\":500,\"key3\":[150,200,300,\"hello\"],\"key4\":{\"hello1\":\"world1\",\"key3\":[200,300]}}}";
    String ss = "{ \"firstName\": \"John\", \"lastName\": \"Smith\", \"isAlive\": true, \"age\": 25, \"address\": { \"streetAddress\": \"21 2nd Street\", \"city\": \"New York\", \"state\": \"NY\", \"postalCode\": \"10021-3100\" }, \"phoneNumbers\": [ { \"type\": \"home\", \"number\": \"212 555-1234\" }, { \"type\": \"office\", \"number\": \"646 555-4567\" }, { \"type\": \"mobile\", \"number\": \"123 456-7890\" } ], \"children\": [], \"spouse\": nul }";
    j.print(ss);

    }

}
