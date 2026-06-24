# Without the if 
#   But assumes word is not None
class Solution:
    def compressedString(self, word: str) -> str:        
        res = ""
        count = 0
        prev = word[0]

        for i in range(len(word)):
            if word[i] == prev and count < 9:
                count += 1
            else:
                res += str(count) + prev
                count = 1
                
            if i+1 == len(word):
                res += str(count) + word[i]
            
            prev = word[i]

        return res
        


# Needs the if
class Solution:
    def compressedString(self, word: str) -> str:
        if ((n:= len(word)) == 1):
            return str(1) + word
        
        res = ""
        count = 1

        for i in range(n - 1):
            if word[i] == word[i+1] and count < 9:
                count += 1
            else:
                res += str(count) + word[i]
                count = 1

            if i+1 == (n - 1):
                res += str(count) + word[i+1]

        return res
        
