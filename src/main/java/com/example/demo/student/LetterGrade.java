package com.example.demo.student;

public enum LetterGrade {

    NOT_ALLOWED("grade cannot be above 100"),
    A_PLUS("A+"),
    A("A"),
    A_MINUS("A-"),
    B_PLUS("B+"),
    B("B"),
    C("C"),
    D("D"),
    F("F");

    private final String label;
    LetterGrade(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static LetterGrade calculateGarde(int grade){
        if (grade >100){
            return NOT_ALLOWED;
        }else if(grade >= 90){
            return A_PLUS;
        } else if (grade >= 85) {
            return A;
        } else if (grade >= 80) {
            return A_MINUS;
        } else if (grade >= 75) {
            return B_PLUS;
        } else if (grade >= 60) {
            return B;
        } else if (grade >= 50) {
            return C;
        } else if (grade >= 40) {
            return D;
        } else{
            return F;
        }

    }
}
