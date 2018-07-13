import java.util.*;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {
    public String getResult(String inputStr) {

        if (inputStr.split("\\s+").length == 1) {
            return inputStr + " 1";
        } else {

            List<Input> inputList = formatInputStr(inputStr);

            List<Word> list = getModel(inputList);

            list.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            return getView(list).toString();
        }
    }

    private StringJoiner getView(List<Word> list) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Word w : list) {
            String s = w.getValue() + " " + w.getWordCount();
            joiner.add(s);
        }
        return joiner;
    }

    private List<Word> getModel(List<Input> inputList) {
        Map<String, List<Input>> map = getListMap(inputList);

        List<Word> list = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
            Word input = new Word(entry.getKey(), entry.getValue().size());
            list.add(input);
        }
        return list;
    }

    private List<Input> formatInputStr(String inputStr) {
        String[] arr = inputStr.split("\\s+");

        List<Input> inputList = new ArrayList<>();
        for (String s : arr) {
            Input input = new Input(s);
            inputList.add(input);
        }
        return inputList;
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