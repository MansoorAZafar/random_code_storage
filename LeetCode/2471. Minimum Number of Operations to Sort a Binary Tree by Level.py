"""
You are given the root of a binary tree with unique values.

In one operation, you can choose any two nodes at the same level and swap their values.

Return the minimum number of operations needed to make the values at each level sorted in a strictly increasing order.

The level of a node is the number of edges along the path between it and the root node.
"""
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def minimumOperations(self, root: Optional[TreeNode]) -> int:
        que = []
        steps = 0
        if root:
            que.append(root)
        
        while que:
            level = []

            for _ in range(len(que)):
                curr = que.pop(0)
                level.append(curr.val)

                if curr.left:
                    que.append(curr.left)
                if curr.right:
                    que.append(curr.right)
        
            steps += self.count_swaps(level)
        
        return steps

    def count_swaps(self, nums):
        swaps = 0
        idx_map = {n: i for i, n in enumerate(nums)}
        sorted_nums = sorted(nums)

        for i in range(len(nums)):
            if nums[i] != sorted_nums[i]:
                swaps += 1

                j = idx_map[sorted_nums[i]] # get the index of the actual value
                nums[i], nums[j] = nums[j], nums[i] # Swap the proper value and the old one

                # Fix the hashmap
                idx_map[nums[i]] = i
                idx_map[nums[j]] = j

        return swaps
            
        
