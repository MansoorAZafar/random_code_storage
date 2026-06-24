#define MAX(a,b) (a) > (b) ? (a) : (b)

int maximumLength(int* nums, int numsSize, int k) {
    int dp[k][k];
    int ans = 0;
    memset(dp, 0, sizeof(dp));

    for(int i = 0; i < numsSize; ++i) {
        const int x = nums[i] % k;

        for(int y = 0; y < k; ++y) {
            dp[x][y] = dp[y][x] + 1;
            ans = MAX(ans, dp[x][y]);
        }
    }
    return ans;
}
