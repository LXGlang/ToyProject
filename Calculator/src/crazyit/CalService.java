package crazyit;

public class CalService {
    private double store = 0;
    private String firstNum = null;
    private String lastOp = null;
    private String secondNum = null;
    private boolean isSecondNum = false;

    private String numString = "0123456789.";
    private String opString = "+-*/";

    public CalService() {
        super();
    }

    public String callMethod(String cmd, String text) throws Exception {
        if (cmd.equals("C")) {
            return clearAll();
        } else if (cmd.equals("CE")) {
            return clear(text);
        } else if (cmd.equals("Back")) {
            return backSpace(text);
        } else if (numString.contains(cmd)) {
            return catNum(cmd, text);
        } else if (opString.contains(cmd)) {
            return setOp(cmd, text);
        } else if (cmd.equals("=")) {
            return cal(text, false);
        } else if (cmd.equals("+/-")) {
            return setNegative(text);
        } else if (cmd.equals("1/x")) {
            return setReciprocal(text);
        } else if (cmd.equals("sqrt")) {
            return sqrt(text);
        } else if (cmd.equals("%")) {
            return cal(text, true);
        } else {
            return mCmd(cmd, text);
        }
    }

    public String cal(String text, boolean isPercent) throws Exception {
        double secondResult = secondNum == null
                ? Double.valueOf(text)
                : Double.valueOf(secondNum).doubleValue();
        if (secondResult == 0 && this.lastOp.equals("/")) {
            return "0";
        }
        if (isPercent) {
            secondResult = MyMath.multiply(Double.valueOf(firstNum), MyMath
                    .divide(secondResult, 100));
        }
        switch (this.lastOp) {
            case "+":
                firstNum = String.valueOf(MyMath.add(Double.valueOf(firstNum),
                        secondResult));
                break;
            case "-":
                firstNum = String.valueOf(MyMath.subtract(Double.valueOf(firstNum),
                        secondResult));
                break;
            case "*":
                firstNum = String.valueOf(MyMath.multiply(Double.valueOf(firstNum),
                        secondResult));
                break;
            case "/":
                firstNum = String.valueOf(MyMath.divide(Double.valueOf(firstNum),
                        secondResult));
                break;
        }
        secondNum = secondNum == null ? text : secondNum;
        this.isSecondNum = true;
        return firstNum;
    }

    public String setReciprocal(String text) {
        if (text.equals("0")) {
            return text;
        } else {
            this.isSecondNum = true;
            return String.valueOf(MyMath.divide(1, Double.valueOf(text)));
        }
    }

    public String sqrt(String text) {
        this.isSecondNum = true;
        return String.valueOf(Math.sqrt(Double.valueOf(text)));
    }

    public String setOp(String cmd, String text) {
        this.lastOp = cmd;
        this.firstNum = text;
        this.secondNum = null;
        this.isSecondNum = true;
        return null;
    }

    public String setNegative(String text) {
        if (text.indexOf("-") == 0) {
            return text.substring(1, text.length());
        }
        return text.equals("0") ? text : "-" + text;
    }

    public String catNum(String cmd, String text) {
        String result = cmd;
        if (!text.equals("0")) {
            if (isSecondNum) {
                isSecondNum = false;
            } else {
                result = text + cmd;
            }
        }
        if (result.indexOf(".") == 0) {
            result = "0" + result;
        }
        return result;
    }


    public String backSpace(String text) {
        return text.equals("0") || text.equals("") ? "0" : text.substring(0,
                text.length() - 1);
    }

    public String mCmd(String cmd, String text) {
        if (cmd.equals("M+")) {
            store = MyMath.add(store, Double.valueOf(text));
        } else if (cmd.equals("MC")) {
            store = 0;
        } else if (cmd.equals("MR")) {
            isSecondNum = true;
            return String.valueOf(store);
        } else if (cmd.equals("MS")) {
            store = Double.valueOf(text);
        }
        return null;
    }

    public String clearAll() {
        this.firstNum = "0";
        this.secondNum = null;
        return this.firstNum;
    }

    public String clear(String text) {
        return "0";
    }


    public double getStore() {
        return this.store;
    }

}