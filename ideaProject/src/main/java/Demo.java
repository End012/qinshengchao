import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Demo {
    public static List<String> resultList = new ArrayList<>();
    public static final String LOWER_CHAR_REGEX = "[a-z]+";
    public static final int DUPLICATE_COUNT = 3;
    public static final char  CHAR_A = 'a';
    public static final String ERROR_MESSAGE = "Please enter a valid string that can only contain a~z";

    public static void main(String[] args) {
        String inputString = "aabcccbbadd";
        Demo demo = new Demo();
        String result = demo.resultString(inputString);
        System.out.println("input:"+ inputString + "-> result:" + result);


    }

    public String resultString(String inputString){
        //get the number of consecutive occurrences of each letter in the input string
        List<Integer> countList = getDuplicateCharCount(inputString, new ArrayList<>());

        //remove characters with consecutive repetitions greater than 3 count
        removeDuplicateChar(inputString, countList);
        String result = (resultList.size() == 0? "": resultList.get(resultList.size() - 1));
        resultList.clear();
        return result;
    }


    public boolean removeDuplicateChar (String str, List<Integer> countList){
        boolean continueFlag = false;
        Optional<String> optional = Optional.ofNullable(str);
        if(!optional.isPresent()){
            return continueFlag;
        }
        if(!str.matches(LOWER_CHAR_REGEX)){
            System.out.println(ERROR_MESSAGE);
            return continueFlag;
        }

        StringBuffer stringBuffer = new StringBuffer();
        int charIndex = 0;
        for (int i = 0; i < countList.size(); i++) {
            charIndex += countList.get(i);
            //if the number of repetitions is less than 3 count, it will added to the result string
            if(DUPLICATE_COUNT > countList.get(i)){
                for (int j = 0; j < countList.get(i); j++) {
                    stringBuffer.append(str.charAt(charIndex - 1));
                }
            }
            //if the number of repetitions is greater than 3 count, start Stage 2 part
            if(DUPLICATE_COUNT <= countList.get(i) && !continueFlag){
                //check if the char is a, because a is the first digit in alphabetical order
                if(CHAR_A != str.charAt(charIndex - 1)){
                    stringBuffer.append(beforeChar(str.charAt(charIndex - 1)));
                }
                continueFlag = true;
            }
        }
        resultList.add(stringBuffer.toString());
        //Because Stage 2 part replace the character char, check duplicate again
        if(continueFlag){
            removeDuplicateChar(stringBuffer.toString(), getDuplicateCharCount(stringBuffer.toString(), new ArrayList<>()));
        }
        return continueFlag;
    }


    public List<Integer> getDuplicateCharCount(String str, List<Integer> charCountList){
        Optional<String> optional = Optional.ofNullable(str);
        if(!optional.isPresent()){
            return charCountList;
        }
        if(!str.matches(LOWER_CHAR_REGEX)){
            System.out.println(ERROR_MESSAGE);
        }
        char[] charArray  = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            int j = 1;
            //loop to obtain the number of consecutive repetitions
            while(i != charArray.length - 1 && charArray[i] == charArray[i+1]){
                i ++ ;
                j++;
            }
            charCountList.add(j);
        }
        return charCountList;

    }


    public char beforeChar(char targetChar){
        int charInt = targetChar;
        return (char) (charInt - 1);
    }
}
