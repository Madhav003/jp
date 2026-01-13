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
  float ff = (float) dd;
  long ll = (long) ff;
  int ii = (int) ll;
  short ss = (short) ii;
  byte bb = (byte) ss;
  System.out.println(bb);
  }
}
