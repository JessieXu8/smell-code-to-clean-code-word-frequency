import java.util.*;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {
    public List<Input> splitTheInputStr(String inputStr){
        String[] arr = inputStr.split("\\s+");
        List<Input> inputList = new ArrayList<>();
        for (String s : arr) {
            Input input = new Input(s, 1);
            inputList.add(input);
        }
        return inputList;
    }


    public String getResult(String inputStr) {

        if (inputStr.split("\\s+").length == 1) {//如果只有一个词的话
            return inputStr + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
//                String[] arr = inputStr.split("\\s+");
//
//                List<Input> inputList = new ArrayList<>();
//                for (String s : arr) {
//                    Input input = new Input(s, 1);
//                    inputList.add(input);
//                }
                List<Input> inputList = new ArrayList<>();
                inputList = splitTheInputStr(inputStr);

                //get the map for the next step of sizing the same word
                Map<String, List<Input>> map = getListMap(inputList);

                List<Input> list = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                inputList = list;

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (Input w : inputList) {
                    String s = w.getValue() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}
