1. ก็อปโค้ดในไฟล์ชื่อ SevenSegmentClass.ino ทั้งหมดไปวางข้างบนไฟล์โปรเจค

2. สร้าง int array ที่เป็นลำดับ pin เรียง segment ตามด้วยหลัก {A,B,C,D,E,F,G,C1,C2,C3,C4}
	เช่น int sevsegPin[] = {11, 10, 9, 8, 7, 6, 5, A0, A1, A2, A3};

3. สร้าง Object เป็น Global variable เช่น
	SevenSegment mildSevSeg(sevsegPin,false);
	พารามีเตอร์แรกใส่ int array ของลำดับ pin 
	พารามีเตอร์ที่สองใส่ false เพราะไม่ต้องการใช้ dot บน sevensegment

4. ลองใช้คำสั่้ง writeNumberAtDigit(3,1); ใน loop() เพื่อเขียนเลข 3 บนหลักที่ 1 

void selectDigit(int digit); ใช้ในการเลือกหลักที่จะแสดงผล 1 - 4
void drawNumber(int number); ใช้ในการวาดตัวเลขบนหลักที่เลือก ปล.ต้องเลือกหลักก่อน
void writeNumber(int number); ใช้เขียนตัวเลขจำนวนเต็ม เช่น 5300
void writeNumberFromArray(int numbers[], int amount); ใช้ในการเขียน seven segment จากตัวเลขใน array *อันนี้ทีเด็ดเลย

ตัวอย่างการใช้ writeNumberFromArray
int numbersToWrite[] = {2,0,0,2};

void loop() {
mildSevSeg.writeNumberFromArray(numbersToWrite,4);  /* 4 คือ จำนวนตัวใน array ว่ามี 4 ตัว */
}


สอบถามการใช้งานเพิ่มเติม พบปัญหา โทร. 0852029098 TSphere