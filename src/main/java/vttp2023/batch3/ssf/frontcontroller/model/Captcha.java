package vttp2023.batch3.ssf.frontcontroller.model;

import java.util.Random;

public class Captcha {

    private Integer num1;
    private Integer num2;
    private String operator;
    private Integer answer;
    private String question;
    private Integer captchaInput; //to validate

    private String[] operatorArr = {"+", "-", "*", "/"};

    public Captcha() {
        Random r = new Random();
        this.num1 = r.nextInt(50) + 1;
        this.num2 = r.nextInt(50) + 1;
        this.operator = operatorArr[r.nextInt(4)];
        setAnswer();
        setQuestion("What is " + num1 + " " + operator + " " + num2 + "?");
    }

    // @Override
    // public String toString() {
    //     return "What is" + num1 + " " + operator + " " + num2 + "?";
    // }

    public Integer getNum1() {
        return num1;
    }

    public void setNum1(Integer num1) {
        this.num1 = num1;
    }

    public Integer getNum2() {
        return num2;
    }

    public void setNum2(Integer num2) {
        this.num2 = num2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer() {
        Integer calcAnswer = 0;
        switch(operator){
            case "+":
            calcAnswer = num1 + num2;
            break;
            case "-":
            calcAnswer = num1 - num2;
            break;
            case "*":
            calcAnswer = num1 * num2;
            break;
            case "/":
            calcAnswer =  Math.round(num1 + num2);
            break;
        }
        this.answer = calcAnswer;
    }

    public Integer getCaptchaInput() {
        return captchaInput;
    }

    public void setCaptchaInput(Integer captchaInput) {
        this.captchaInput = captchaInput;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
