int a = (true) ? 1 : 2; // (condition) ？if true value : else value;

String.format("%1.2f", Math.PI);
// %fieldwidth.precision
// d = int, f / e = double, s = String, boolean = b

// str1 and str2 takes up String type
void boolean equals(Object a, Object b) {
    if (a instanceof String str1 && b instanceof String str2) { 
        return str1.length() > str2.length();
    }
}

