#include <stdio.h>

int main() {
int n;
scanf("%d", &n);
int a[n], b[n];
int rep = 0;
for(int i = 0; i < n; i++) {
scanf("%d", &a[i]);
}
for(int i = 0; i < n; i++) {
for(int k = i + 1; k < n; k++) {
if(a[i] == a[k]) {
int already = 0;
for(int j = 0; j < rep; j++) {
if(b[j] == a[i]) {
already = 1;
break;
}
}
if(!already) {
b[rep++] = a[i];
}
break;
}
}
}
printf("No of numbers repeated: %d\n", rep);
printf("Numbers repeated: ");
for(int i = 0; i < rep; i++) {
printf("%d ", b[i]);
}
return 0;
}
