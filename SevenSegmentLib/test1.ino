int sevsegPin[] = {7, 6, 5, 4, 3, 2, 1, 11,10, 9, 8};
SevenSegment sevseg(sevsegPin, false);
void setup() {
}

int numbersToWrite1[1] = {8};
int numbersToWrite2[2] = {2,0};
int numbersToWrite3[3] = {2,0,0};
int numbersToWrite4[4] = {2,0,0,2};
int numbersToWrite5[4] = {0,0,7,4};

int amount_to_write =1;
void loop() {

int a = (int) random(0,10);
int b = (int) random(0,10);
int c = (int) random(0,10);
int d = (int) random(0,10);

int number_array[] = {a,b,c,d};

for(int i = 0 ; i < 100 ; i++ ) {
  sevseg.writeNumberFromArray(number_array,amount_to_write);
  delay(4);
}
if(amount_to_write == 4) amount_to_write = 0;
amount_to_write++;

delay(4);
}
