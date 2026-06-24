// 1 = odd
// 0 = even
#define ODD 1
#define EVEN 0

int maximumLength(int* nums, int numsSize) {
    if(numsSize == 2) { return numsSize; }
    
    int oddCnt = 0;
    int evenCnt = 0;
    int altCnt = 0;

    const int firstItem = 0;
    int expectOdd = ((nums[firstItem] & 1) == ODD);

    for(int i = 0; i < numsSize; ++i) {
        const int n = (nums[i] & 1);
        if(n == expectOdd) {
            expectOdd = !expectOdd;
            ++altCnt;
        }

        if (n == ODD) {
            ++oddCnt;
        } else {
            ++evenCnt;
        }
    }  

    int greaterOddOrEvenCnt = oddCnt > evenCnt ? oddCnt : evenCnt;
    return altCnt > greaterOddOrEvenCnt ? altCnt : greaterOddOrEvenCnt;
}
