class Exp2b{
  public static void main(String args[]){
  byte b = 10;
  short s = b;
  int i = s;
  long l = i;
  float f = l;
  double d = f;
  System.out.println(d);
  double dd = 125.4;
  float ff = (float) d;
  long ll = (long) f;
  int ii = (int) l;
  short ss = (short) i;
  byte bb = (byte) s;
  System.out.println(b);
  }
}
